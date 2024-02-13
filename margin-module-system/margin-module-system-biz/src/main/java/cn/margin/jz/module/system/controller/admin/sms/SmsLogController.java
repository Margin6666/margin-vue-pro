package cn.margin.jz.module.system.controller.admin.sms;

import cn.margin.jz.framework.common.pojo.CommonResult;
import cn.margin.jz.framework.common.pojo.PageParam;
import cn.margin.jz.framework.common.pojo.PageResult;
import cn.margin.jz.framework.common.util.object.BeanUtils;
import cn.margin.jz.framework.excel.core.util.ExcelUtils;
import cn.margin.jz.framework.operatelog.core.annotations.OperateLog;
import cn.margin.jz.module.system.controller.admin.sms.vo.log.SmsLogPageReqVO;
import cn.margin.jz.module.system.controller.admin.sms.vo.log.SmsLogRespVO;
import cn.margin.jz.module.system.dal.dataobject.sms.SmsLogDO;
import cn.margin.jz.module.system.service.sms.SmsLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

import static cn.margin.jz.framework.common.pojo.CommonResult.success;
import static cn.margin.jz.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;

@Tag(name = "管理后台 - 短信日志")
@RestController
@RequestMapping("/system/sms-log")
@Validated
public class SmsLogController {

    @Resource
    private SmsLogService smsLogService;

    @GetMapping("/page")
    @Operation(summary = "获得短信日志分页")
    @PreAuthorize("@ss.hasPermission('system:sms-log:query')")
    public CommonResult<PageResult<SmsLogRespVO>> getSmsLogPage(@Valid SmsLogPageReqVO pageReqVO) {
        PageResult<SmsLogDO> pageResult = smsLogService.getSmsLogPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, SmsLogRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出短信日志 Excel")
    @PreAuthorize("@ss.hasPermission('system:sms-log:export')")
    @OperateLog(type = EXPORT)
    public void exportSmsLogExcel(@Valid SmsLogPageReqVO exportReqVO,
                                  HttpServletResponse response) throws IOException {
        exportReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<SmsLogDO> list = smsLogService.getSmsLogPage(exportReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "短信日志.xls", "数据", SmsLogRespVO.class,
                BeanUtils.toBean(list, SmsLogRespVO.class));
    }

}
