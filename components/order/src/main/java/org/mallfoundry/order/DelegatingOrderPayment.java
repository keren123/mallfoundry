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

import lombok.Getter;
import lombok.Setter;
import org.mallfoundry.finance.Payment;
import org.mallfoundry.finance.PaymentInstrument;
import org.mallfoundry.finance.PaymentMethod;

import java.util.Set;

@Getter
@Setter
public class DelegatingOrderPayment extends OrderPaymentSupport {

    private Set<String> orderIds;

    private final Payment payment;

    public DelegatingOrderPayment(Payment payment) {
        this.payment = payment;
    }

    @Override
    public PaymentInstrument createInstrument(PaymentMethod type) {
        return this.payment.createInstrument(type);
    }

    @Override
    public PaymentInstrument getInstrument() {
        return this.payment.getInstrument();
    }

    @Override
    public void setInstrument(PaymentInstrument instrument) {
        this.payment.setInstrument(instrument);
    }

    @Override
    public String getReturnUrl() {
        return this.payment.getReturnUrl();
    }

    @Override
    public void setReturnUrl(String returnUrl) {
        this.payment.setReturnUrl(returnUrl);
    }

    @Override
    public Payment toPayment() {
        return this.payment;
    }
}
