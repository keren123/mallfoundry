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

import org.mallfoundry.payment.Payment;
import org.mallfoundry.payment.PaymentCapturedEvent;
import org.mallfoundry.payment.PaymentEvent;
import org.mallfoundry.payment.PaymentOrder;
import org.mallfoundry.payment.PaymentStartedEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import java.util.stream.Collectors;

@Configuration
public class OrderPaymentEventListener {

    private final OrderService orderService;

    public OrderPaymentEventListener(OrderService orderService) {
        this.orderService = orderService;
    }

    private OrderPaymentResult createPaymentResult(Payment payment) {
        var instrument = payment.getInstrument();
        return new DefaultOrderPaymentResult(payment.getId(), instrument.getType(), payment.getStatus());
    }

    @EventListener
    public void handlePending(PaymentStartedEvent event) {
        this.handlePaymentEvent(event);
    }

    @EventListener
    public void handleCaptured(PaymentCapturedEvent event) {
        this.handlePaymentEvent(event);
    }

    private void handlePaymentEvent(PaymentEvent event) {
        var payment = event.getPayment();
        var orderIds = payment.getOrders().stream().map(PaymentOrder::getId).collect(Collectors.toUnmodifiableSet());
        var paymentResult = this.createPaymentResult(payment);
        this.orderService.payOrders(orderIds, paymentResult);
    }
}
