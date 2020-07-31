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

import org.apache.commons.collections4.ListUtils;
import org.mallfoundry.data.SliceList;
import org.mallfoundry.processor.ProcessorsInvoker;

import java.util.List;
import java.util.function.BiFunction;

public class OrderProcessorsInvoker {

    private final List<OrderProcessor> processors;

    public OrderProcessorsInvoker(List<OrderProcessor> processors) {
        this.processors = ListUtils.emptyIfNull(processors);
    }

    private List<Order> invokeProcess(List<Order> orders, BiFunction<OrderProcessor, List<Order>, List<Order>> function) {
        return ProcessorsInvoker.invokeBiFunctionProcessors(this.processors, orders, function);
    }

    private Order invokeProcess(Order order, BiFunction<OrderProcessor, Order, Order> function) {
        return ProcessorsInvoker.invokeBiFunctionProcessors(this.processors, order, function);
    }

    private SliceList<Order> invokeProcess(SliceList<Order> orders, BiFunction<OrderProcessor, SliceList<Order>, SliceList<Order>> function) {
        return ProcessorsInvoker.invokeBiFunctionProcessors(this.processors, orders, function);
    }

    private OrderQuery invokeProcess(OrderQuery query, BiFunction<OrderProcessor, OrderQuery, OrderQuery> function) {
        return ProcessorsInvoker.invokeBiFunctionProcessors(this.processors, query, function);
    }

    public List<Order> invokePreProcessPlaceOrders(List<Order> orders) {
        return this.invokeProcess(orders, OrderProcessor::preProcessPlaceOrders);
    }

    public List<Order> invokePostProcessPlaceOrders(List<Order> orders) {
        return this.invokeProcess(orders, OrderProcessor::postProcessPlaceOrders);
    }

    public Order invokePostProcessGetOrder(Order order) {
        return this.invokeProcess(order, OrderProcessor::postProcessGetOrder);
    }

    public OrderQuery invokePreProcessGetOrders(OrderQuery query) {
        return this.invokeProcess(query, OrderProcessor::preProcessGetOrders);
    }

    public SliceList<Order> invokePostProcessGetOrders(SliceList<Order> orders) {
        return this.invokeProcess(orders, OrderProcessor::postProcessGetOrders);
    }

    public Order invokePreProcessUpdateOrder(Order order) {
        return this.invokeProcess(order, OrderProcessor::preProcessUpdateOrder);
    }

    public Order invokePreProcessFulfilOrder(Order order) {
        return this.invokeProcess(order, OrderProcessor::preProcessFulfilOrder);
    }

    public String invokePreProcessSignOrder(Order order, String message) {
        BiFunction<OrderProcessor, String, String> function = (orderProcessor, identity) -> orderProcessor.preProcessSignOrder(order, identity);
        return ProcessorsInvoker.invokeBiFunctionProcessors(this.processors, message, function);
    }

    public Order invokePreProcessReceiptOrder(Order order) {
        return this.invokeProcess(order, OrderProcessor::preProcessReceiptOrder);
    }

    public String invokePreProcessCancelOrder(Order order, String reason) {
        BiFunction<OrderProcessor, String, String> function = (orderProcessor, identity) -> orderProcessor.preProcessCancelOrder(order, reason);
        return ProcessorsInvoker.invokeBiFunctionProcessors(this.processors, reason, function);
    }

    public OrderShipment invokePreProcessAddOrderShipment(Order order, OrderShipment shipment) {
        return ProcessorsInvoker.invokeBiFunctionProcessors(this.processors, shipment, (orderProcessor, identity) -> orderProcessor.preProcessAddOrderShipment(order, identity));
    }

    public Order invokePreProcessGetOrderShipment(Order order) {
        return this.invokeProcess(order, OrderProcessor::preProcessGetOrderShipment);
    }

    public Order invokePreProcessGetOrderShipments(Order order) {
        return this.invokeProcess(order, OrderProcessor::preProcessGetOrderShipments);
    }

    public OrderShipment invokePreProcessUpdateOrderShipment(Order order, OrderShipment shipment) {
        return ProcessorsInvoker.invokeBiFunctionProcessors(this.processors, shipment, (orderProcessor, identity) -> orderProcessor.preProcessUpdateOrderShipment(order, identity));
    }

    public List<OrderShipment> invokePreProcessUpdateOrderShipments(Order order, List<OrderShipment> shipments) {
        return ProcessorsInvoker.invokeBiFunctionProcessors(this.processors, shipments, (orderProcessor, identity) -> orderProcessor.preProcessUpdateOrderShipments(order, identity));
    }

    public OrderShipment invokePreProcessRemoveOrderShipment(Order order, OrderShipment shipment) {
        return ProcessorsInvoker.invokeBiFunctionProcessors(this.processors, shipment, (orderProcessor, identity) -> orderProcessor.preProcessRemoveOrderShipment(order, identity));
    }

    public List<OrderShipment> invokePreProcessRemoveOrderShipments(Order order, List<OrderShipment> shipments) {
        return ProcessorsInvoker.invokeBiFunctionProcessors(this.processors, shipments, (orderProcessor, identity) -> orderProcessor.preProcessRemoveOrderShipments(order, identity));
    }

    public OrderRefund invokePreProcessApplyOrderRefund(Order order, OrderRefund refund) {
        return ProcessorsInvoker.invokeBiFunctionProcessors(this.processors, refund, (orderProcessor, identity) -> orderProcessor.preProcessApplyOrderRefund(order, identity));
    }

    public OrderRefund invokePreProcessCancelOrderRefund(Order order, OrderRefund refund) {
        return ProcessorsInvoker.invokeBiFunctionProcessors(this.processors, refund, (orderProcessor, identity) -> orderProcessor.preProcessCancelOrderRefund(order, identity));
    }

    public OrderRefund invokePreProcessApproveOrderRefund(Order order, OrderRefund refund) {
        return ProcessorsInvoker.invokeBiFunctionProcessors(this.processors, refund, (orderProcessor, identity) -> orderProcessor.preProcessApproveOrderRefund(order, identity));
    }

    public OrderRefund invokePreProcessDisapproveOrderRefund(Order order, OrderRefund refund) {
        BiFunction<OrderProcessor, OrderRefund, OrderRefund> function = (orderProcessor, identity) -> orderProcessor.preProcessDisapproveOrderRefund(order, identity);
        return ProcessorsInvoker.invokeBiFunctionProcessors(this.processors, refund, function);
    }

    public OrderRefund invokePreProcessActiveOrderRefund(Order order, OrderRefund refund) {
        return ProcessorsInvoker.invokeBiFunctionProcessors(this.processors, refund, (orderProcessor, identity) -> orderProcessor.preProcessActiveOrderRefund(order, identity));
    }

    public OrderRefund invokePostProcessGetOrderRefund(Order order, OrderRefund refund) {
        BiFunction<OrderProcessor, OrderRefund, OrderRefund> function = (orderProcessor, identity) -> orderProcessor.postProcessGetOrderRefund(order, identity);
        return ProcessorsInvoker.invokeBiFunctionProcessors(this.processors, refund, function);
    }

    public OrderReview invokePreProcessAddOrderReview(Order order, OrderReview review) {
        return ProcessorsInvoker.invokeBiFunctionProcessors(this.processors, review, (orderProcessor, identity) -> orderProcessor.preProcessAddOrderReview(order, identity));
    }

    public List<OrderReview> invokePreProcessAddOrderReviews(Order order, List<OrderReview> reviews) {
        return ProcessorsInvoker.invokeBiFunctionProcessors(this.processors, reviews, (orderProcessor, identity) -> orderProcessor.preProcessAddOrderReviews(order, identity));
    }

    public void invokePostProcessAfterCompletion() {
        ProcessorsInvoker.invokeConsumerProcessors(this.processors, OrderProcessor::postProcessAfterCompletion);
    }
}
