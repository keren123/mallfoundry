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

package org.mallfoundry.order.dispute;

import com.fasterxml.jackson.annotation.JsonValue;
import org.mallfoundry.customer.CustomerOwnership;
import org.mallfoundry.store.StoreOwnership;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface OrderDispute extends StoreOwnership, CustomerOwnership {

    String getId();

    void setId(String id);

    String getStoreName();

    void setStoreName(String storeName);

    String getOrderId();

    String getApplicant();

    void setApplicant(String applicant);

    String getApplicantId();

    void setApplicantId(String applicantId);

    OrderDisputeKind getKind();

    void setKind(OrderDisputeKind kind);

    ItemStatus getItemStatus();

    void itemNotReceive();

    void itemReceive();

    String getItemId();

    void setItemId(String itemId);

    BigDecimal getItemAmount();

    void setItemAmount(BigDecimal itemAmount);

    String getProductId();

    void setProductId(String productId);

    String getVariantId();

    void setVariantId(String variantId);

    String getImageUrl();

    void setImageUrl(String imageUrl);

    String getName();

    void setName(String name);

    int getQuantity();

    void setQuantity(int quantity);

    // ?????????????????????
    BigDecimal getAmount();

    void setAmount(BigDecimal refundAmount);

    String getNotes();

    void setNotes(String notes);

    List<String> getAttachments();

    void setAttachments(List<String> attachments);

    OrderDisputeStatus getStatus();

    String getReason();

    void setReason(String reason);

    OrderDisputeTransaction createTransaction(String id);

    void addTransaction(OrderDisputeTransaction transaction);

    List<OrderDisputeTransaction> getTransactions();

    int getApplyingExpires();

    void setApplyingExpires(int applyingExpires);

    Date getApplyingExpiredTime();

    Date getAppliedTime();

    /**
     * ???????????????
     */
    void apply() throws OrderRefundException;


    void reapply() throws OrderRefundException;

    Date getCancelledTime();

    /**
     * ?????????????????????
     */
    void cancel() throws OrderRefundException;

    Date getApprovedTime();

    /**
     * ???????????????
     */
    void approve() throws OrderRefundException;

    String getDisapprovalReason();

    Date getDisapprovedTime();

    void disapprove(String disapprovalReason) throws OrderRefundException;

    Date getSucceededTime();

    /**
     * ???????????????
     */
    void succeed() throws OrderRefundException;

    String getFailReason();

    Date getFailedTime();

    /**
     * ???????????????
     */
    void fail(String failReason) throws OrderRefundException;

    enum ItemStatus {
        NOT_RECEIVED /* ????????? */,
        RECEIVED /* ????????? */;

        @JsonValue
        @Override
        public String toString() {
            return this.name().toLowerCase();
        }
    }
}
