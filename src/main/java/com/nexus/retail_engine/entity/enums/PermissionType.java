package com.nexus.retail_engine.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PermissionType {


    // MANAGE -> CRUD on all records even if it doesn't belong to the user, for admin only
    // CRUD -> specific operation -> only allowed if id has

    // user
    USER_READ("user:read"),              // reading own details
    //USER_CREATE("user:create"),          // creating new account (public)
    USER_UPDATE("user:update"),          // updating own details
    USER_DELETE("user:delete"),          // delete own account
    USER_MANAGE("user:manage"),          // admin only

    // Customer
    CUSTOMER_READ("customer:read"),      // reading customer details (customer can read own details, admin can read all details, sellers should only be able to see anonymized addresses)
    //CUSTOMER_CREATE("customer:create"),  // registering as customer
    CUSTOMER_UPDATE("customer:update"),  // updating profile (customer)
    CUSTOMER_DELETE("customer:delete"),  // deleting account (customer or admin)
    CUSTOMER_MANAGE("customer:manage"),  // customer and admin

    // Address
    ADDRESS_MANAGE("address:manage"),    // adming can manage all addresses
    ADDRESS_READ("address:read"),        // for customer and seller
    ADDRESS_CREATE("address:create"),    // for customer
    ADDRESS_UPDATE("address:update"),    // for customer
    ADDRESS_DELETE("address:delete"),    // for customer

    // Product
    //PRODUCT_READ("product:read"),      // Browsing products (public)
    PRODUCT_CREATE("product:create"),     // Adding new products (Seller)
    PRODUCT_UPDATE("product:update"),    // Editing existing products (Seller)
    PRODUCT_DELETE("product:delete"),    // Removing products (Seller/Admin)
    PRODUCT_MANAGE("product:manage"),

    // Order
    ORDER_READ("order:read"),            // Viewing order history
    ORDER_CREATE("order:create"),        // Placing an order (Customer)
    ORDER_STATUS_UPDATE("order:status"), // for Seller
    ORDER_CANCEL("order:cancel"),        // for cancelling an order (Customer and Seller associated with that order)
    ORDER_MANAGE("order:manage"),        // For admin

    // Seller
    SELLER_READ("seller:read"),          // View seller profile
    //SELLER_CREATE("seller:create"),      // registering as Seller(public)
    SELLER_UPDATE("seller:update"),      // Update business details
    SELLER_DELETE("seller:delete"),      // deleting account (seller or admin)
    SELLER_MANAGE("seller:manage");

    private final String permission;
}