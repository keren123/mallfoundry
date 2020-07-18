/*
 * Copyright (C) 2019-2020 the original author or authors.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package org.mallfoundry.order;

import org.mallfoundry.security.acl.Resource;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public class OrderAuthorizer implements OrderProcessor {

    @PreAuthorize("hasPermission(#query.customerId, '" + Resource.CUSTOMER_TYPE + "', '"
            + OrderAuthorities.ORDER_SEARCH + "," + OrderAuthorities.ORDER_MANAGE + "') or "
            + "hasPermission(#query.storeId, '" + Resource.STORE_TYPE + "', '"
            + OrderAuthorities.ORDER_SEARCH + "," + OrderAuthorities.ORDER_MANAGE + "')")
    @Override
    public OrderQuery preProcessGetOrders(OrderQuery query) {
        return query;
    }

    @PreAuthorize("hasPermission(#order.customerId, '" + Resource.CUSTOMER_TYPE + "', '"
            + OrderAuthorities.ORDER_READ + "," + OrderAuthorities.ORDER_MANAGE + "') or "
            + "hasPermission(#order.storeId, '" + Resource.STORE_TYPE + "', '"
            + OrderAuthorities.ORDER_READ + "," + OrderAuthorities.ORDER_MANAGE + "')")
    @Override
    public Order postProcessGetOrder(Order order) {
        return order;
    }

    @PreAuthorize("hasPermission(#order.customerId, '" + Resource.CUSTOMER_TYPE + "', '"
            + OrderAuthorities.ORDER_WRITE + "," + OrderAuthorities.ORDER_MANAGE + "') or "
            + "hasPermission(#order.storeId, '" + Resource.STORE_TYPE + "', '"
            + OrderAuthorities.ORDER_WRITE + "," + OrderAuthorities.ORDER_MANAGE + "')")
    @Override
    public Order preProcessUpdateOrder(Order order) {
        return order;
    }

    @PreAuthorize("hasPermission(#order.storeId, '" + Resource.STORE_TYPE + "', '"
            + OrderAuthorities.ORDER_FULFIL + "," + OrderAuthorities.ORDER_MANAGE + "')")
    @Override
    public Order preProcessFulfilOrder(Order order) {
        return order;
    }

    @PreAuthorize("hasPermission(#order.customerId, '" + Resource.CUSTOMER_TYPE + "', '"
            + OrderAuthorities.ORDER_SIGN + "," + OrderAuthorities.ORDER_MANAGE + "')")
    @Override
    public String preProcessSignOrder(Order order, String message) {
        return message;
    }

    @PreAuthorize("hasPermission(#order.customerId, '" + Resource.CUSTOMER_TYPE + "', '"
            + OrderAuthorities.ORDER_RECEIPT + "," + OrderAuthorities.ORDER_MANAGE + "')")
    @Override
    public Order preProcessReceiptOrder(Order order) {
        return order;
    }

    @PreAuthorize("hasPermission(#order.customerId, '" + Resource.CUSTOMER_TYPE + "', '"
            + OrderAuthorities.ORDER_CANCEL + "," + OrderAuthorities.ORDER_MANAGE + "') or "
            + "hasPermission(#order.storeId, '" + Resource.STORE_TYPE + "', '"
            + OrderAuthorities.ORDER_CANCEL + "," + OrderAuthorities.ORDER_MANAGE + "')")
    @Override
    public String preProcessCancelOrder(Order order, String reason) {
        return reason;
    }

    @PreAuthorize("hasPermission(#order.storeId, '" + Resource.STORE_TYPE + "', '"
            + OrderAuthorities.ORDER_SHIPMENT_ADD + ","
            + OrderAuthorities.ORDER_SHIPMENT_MANAGE + ","
            + OrderAuthorities.ORDER_MANAGE + "')")
    @Override
    public Shipment preProcessAddOrderShipment(Order order, Shipment shipment) {
        return shipment;
    }

    @PreAuthorize("hasPermission(#order.storeId, '" + Resource.STORE_TYPE + "', '"
            + OrderAuthorities.ORDER_SHIPMENT_READ + ","
            + OrderAuthorities.ORDER_SHIPMENT_MANAGE + ","
            + OrderAuthorities.ORDER_MANAGE + "')")
    @Override
    public Order preProcessGetOrderShipment(Order order) {
        return null;
    }

    @PreAuthorize("hasPermission(#order.storeId, '" + Resource.STORE_TYPE + "', '"
            + OrderAuthorities.ORDER_SHIPMENT_READ + ","
            + OrderAuthorities.ORDER_SHIPMENT_MANAGE + ","
            + OrderAuthorities.ORDER_MANAGE + "')")
    @Override
    public Order preProcessGetOrderShipments(Order order) {
        return null;
    }

    @PreAuthorize("hasPermission(#order.storeId, '" + Resource.STORE_TYPE + "', '"
            + OrderAuthorities.ORDER_SHIPMENT_WRITE + ","
            + OrderAuthorities.ORDER_SHIPMENT_MANAGE + ","
            + OrderAuthorities.ORDER_MANAGE + "')")
    @Override
    public Shipment preProcessUpdateOrderShipment(Order order, Shipment shipment) {
        return shipment;
    }

    @PreAuthorize("hasPermission(#order.storeId, '" + Resource.STORE_TYPE + "', '"
            + OrderAuthorities.ORDER_SHIPMENT_WRITE + ","
            + OrderAuthorities.ORDER_SHIPMENT_MANAGE + ","
            + OrderAuthorities.ORDER_MANAGE + "')")
    @Override
    public List<Shipment> preProcessUpdateOrderShipments(Order order, List<Shipment> shipments) {
        return shipments;
    }

    @PreAuthorize("hasPermission(#order.storeId, '" + Resource.STORE_TYPE + "', '"
            + OrderAuthorities.ORDER_SHIPMENT_REMOVE + ","
            + OrderAuthorities.ORDER_SHIPMENT_MANAGE + ","
            + OrderAuthorities.ORDER_MANAGE + "')")
    @Override
    public Shipment preProcessRemoveOrderShipment(Order order, Shipment shipment) {
        return shipment;
    }

    @PreAuthorize("hasPermission(#order.storeId, '" + Resource.STORE_TYPE + "', '"
            + OrderAuthorities.ORDER_SHIPMENT_REMOVE + ","
            + OrderAuthorities.ORDER_SHIPMENT_MANAGE + ","
            + OrderAuthorities.ORDER_MANAGE + "')")
    @Override
    public List<Shipment> preProcessRemoveOrderShipments(Order order, List<Shipment> shipments) {
        return shipments;
    }
}
