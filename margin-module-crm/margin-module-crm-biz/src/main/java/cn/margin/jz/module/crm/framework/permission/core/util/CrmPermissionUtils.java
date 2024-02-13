package cn.margin.jz.module.crm.framework.permission.core.util;

import cn.hutool.extra.spring.SpringUtil;
import cn.margin.jz.module.crm.enums.permission.CrmPermissionRoleCodeEnum;
import cn.margin.jz.module.system.api.permission.PermissionApi;

import static cn.margin.jz.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

/**
 * 数据权限工具类
 *
 * @author HUIHUI
 */
public class CrmPermissionUtils {

    /**
     * 校验用户是否是 CRM 管理员
     *
     * @return 是/否
     */
    public static boolean isCrmAdmin() {
        return SingletonManager.getPermissionApi().hasAnyRoles(getLoginUserId(), CrmPermissionRoleCodeEnum.CRM_ADMIN.getCode());
    }

    /**
     * 静态内部类实现单例获取
     *
     * @author HUIHUI
     */
    private static class SingletonManager {

        private static final PermissionApi PERMISSION_API = SpringUtil.getBean(PermissionApi.class);

        public static PermissionApi getPermissionApi() {
            return PERMISSION_API;
        }

    }

}
