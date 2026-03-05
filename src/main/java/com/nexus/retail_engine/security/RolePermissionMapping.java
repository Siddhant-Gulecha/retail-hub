package com.nexus.retail_engine.security;

import com.nexus.retail_engine.entity.enums.PermissionType;
import com.nexus.retail_engine.entity.enums.RoleType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import static com.nexus.retail_engine.entity.enums.RoleType.*;
import static com.nexus.retail_engine.entity.enums.PermissionType.*;

public class RolePermissionMapping {

    private static final Map<RoleType, Set<PermissionType>> map = Map.of(
            CUSTOMER, Set.of(
                    USER_READ, USER_UPDATE, USER_DELETE,
                    CUSTOMER_READ, CUSTOMER_UPDATE, CUSTOMER_DELETE,
                    ADDRESS_READ, ADDRESS_UPDATE, ADDRESS_CREATE, ADDRESS_DELETE,
                    SELLER_READ,
                    ORDER_CREATE, ORDER_READ, ORDER_CANCEL
            ),
            SELLER, Set.of(
                    USER_READ, USER_UPDATE, USER_DELETE,
                    SELLER_READ, SELLER_UPDATE, SELLER_DELETE,
                    ADDRESS_READ, ADDRESS_UPDATE, ADDRESS_CREATE, ADDRESS_DELETE,
                    PRODUCT_CREATE, PRODUCT_UPDATE, PRODUCT_DELETE,
                    ORDER_READ, ORDER_CANCEL, ORDER_STATUS_UPDATE
            ),
            ADMIN, Set.of(
                    USER_MANAGE,
                    ADDRESS_MANAGE,
                    CUSTOMER_MANAGE,
                    SELLER_MANAGE,
                    PRODUCT_MANAGE,
                    ORDER_MANAGE
            )
    );


    public static Set<SimpleGrantedAuthority> getAuthoritiesForRole(RoleType role) {
        return map.get(role).stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}