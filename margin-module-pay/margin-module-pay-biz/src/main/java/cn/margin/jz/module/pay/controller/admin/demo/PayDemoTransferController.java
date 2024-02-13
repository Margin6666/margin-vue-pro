package cn.margin.jz.module.pay.controller.admin.demo;

import cn.margin.jz.framework.common.pojo.CommonResult;
import cn.margin.jz.framework.common.pojo.PageParam;
import cn.margin.jz.framework.common.pojo.PageResult;
import cn.margin.jz.framework.operatelog.core.annotations.OperateLog;
import cn.margin.jz.module.pay.api.notify.dto.PayTransferNotifyReqDTO;
import cn.margin.jz.module.pay.controller.admin.demo.vo.transfer.PayDemoTransferCreateReqVO;
import cn.margin.jz.module.pay.controller.admin.demo.vo.transfer.PayDemoTransferRespVO;
import cn.margin.jz.module.pay.convert.demo.PayDemoTransferConvert;
import cn.margin.jz.module.pay.dal.dataobject.demo.PayDemoTransferDO;
import cn.margin.jz.module.pay.service.demo.PayDemoTransferService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.validation.Valid;

import static cn.margin.jz.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 示例转账单")
@RestController
@RequestMapping("/pay/demo-transfer")
@Validated
public class PayDemoTransferController {
    @Resource
    private PayDemoTransferService demoTransferService;

    @PostMapping("/create")
    @Operation(summary = "创建示例转账订单")
    public CommonResult<Long> createDemoTransfer(@Valid @RequestBody PayDemoTransferCreateReqVO createReqVO) {
        return success(demoTransferService.createDemoTransfer(createReqVO));
    }

    @GetMapping("/page")
    @Operation(summary = "获得示例转账订单分页")
    public CommonResult<PageResult<PayDemoTransferRespVO>> getDemoTransferPage(@Valid PageParam pageVO) {
        PageResult<PayDemoTransferDO> pageResult = demoTransferService.getDemoTransferPage(pageVO);
        return success(PayDemoTransferConvert.INSTANCE.convertPage(pageResult));
    }

    @PostMapping("/update-status")
    @Operation(summary = "更新示例转账订单的转账状态") // 由 pay-module 转账服务，进行回调
    @PermitAll // 无需登录，安全由 PayDemoTransferService 内部校验实现
    @OperateLog(enable = false) // 禁用操作日志，因为没有操作人
    public CommonResult<Boolean> updateDemoTransferStatus(@RequestBody PayTransferNotifyReqDTO notifyReqDTO) {
        demoTransferService.updateDemoTransferStatus(Long.valueOf(notifyReqDTO.getMerchantTransferId()),
                notifyReqDTO.getPayTransferId());
        return success(true);
    }
}
