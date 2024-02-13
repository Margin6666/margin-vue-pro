package cn.margin.jz.module.member.controller.app.social;

import cn.margin.jz.framework.common.enums.UserTypeEnum;
import cn.margin.jz.framework.common.pojo.CommonResult;
import cn.margin.jz.framework.common.util.object.BeanUtils;
import cn.margin.jz.framework.security.core.annotations.PreAuthenticated;
import cn.margin.jz.module.member.controller.app.social.vo.AppSocialUserBindReqVO;
import cn.margin.jz.module.member.controller.app.social.vo.AppSocialUserRespVO;
import cn.margin.jz.module.member.controller.app.social.vo.AppSocialUserUnbindReqVO;
import cn.margin.jz.module.system.api.social.SocialUserApi;
import cn.margin.jz.module.system.api.social.dto.SocialUserBindReqDTO;
import cn.margin.jz.module.system.api.social.dto.SocialUserRespDTO;
import cn.margin.jz.module.system.api.social.dto.SocialUserUnbindReqDTO;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import static cn.margin.jz.framework.common.pojo.CommonResult.success;
import static cn.margin.jz.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

@Tag(name = "用户 App - 社交用户")
@RestController
@RequestMapping("/member/social-user")
@Validated
public class AppSocialUserController {

    @Resource
    private SocialUserApi socialUserApi;

    @PostMapping("/bind")
    @Operation(summary = "社交绑定，使用 code 授权码")
    public CommonResult<String> socialBind(@RequestBody @Valid AppSocialUserBindReqVO reqVO) {
        SocialUserBindReqDTO reqDTO = new SocialUserBindReqDTO(getLoginUserId(), UserTypeEnum.MEMBER.getValue(),
                reqVO.getType(), reqVO.getCode(), reqVO.getState());
        String openid = socialUserApi.bindSocialUser(reqDTO);
        return success(openid);
    }

    @DeleteMapping("/unbind")
    @Operation(summary = "取消社交绑定")
    @PreAuthenticated
    public CommonResult<Boolean> socialUnbind(@RequestBody AppSocialUserUnbindReqVO reqVO) {
        SocialUserUnbindReqDTO reqDTO = new SocialUserUnbindReqDTO(getLoginUserId(), UserTypeEnum.MEMBER.getValue(),
                reqVO.getType(), reqVO.getOpenid());
        socialUserApi.unbindSocialUser(reqDTO);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得社交用户")
    @Parameter(name = "type", description = "社交平台的类型，参见 SocialTypeEnum 枚举值", required = true, example = "10")
    @PreAuthenticated
    public CommonResult<AppSocialUserRespVO> getSocialUser(@RequestParam("type") Integer type) {
        SocialUserRespDTO socialUser = socialUserApi.getSocialUserByUserId(UserTypeEnum.MEMBER.getValue(), getLoginUserId(), type);
        return success(BeanUtils.toBean(socialUser, AppSocialUserRespVO.class));
    }

}
