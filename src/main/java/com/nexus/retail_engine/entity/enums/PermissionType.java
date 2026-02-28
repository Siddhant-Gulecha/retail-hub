package com.nexus.retail_engine.entity.enums;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PermissionType {
    ADDRESS_MANAGE("address:manage"),
    CUSTOMER_READ("customer:read"),
    CUSTOMER_WRITE("customer:write"),
    CUSTOMER_UPDATE("customer:update"),
    CUSTOMER_DELETE("customer:delete"),
    ORDER_MANAGE("order:manage"),
    PRODUCT_READ("product:read");

    private final String permission;
}