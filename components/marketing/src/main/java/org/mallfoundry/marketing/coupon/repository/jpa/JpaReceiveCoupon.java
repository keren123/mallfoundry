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

package org.mallfoundry.marketing.coupon.repository.jpa;

import lombok.Getter;
import lombok.Setter;
import org.mallfoundry.marketing.coupon.CouponStatus;
import org.mallfoundry.marketing.coupon.CouponType;
import org.mallfoundry.marketing.coupon.ReceiveCouponSupport;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "mf_marketing_receive_coupon")
public class JpaReceiveCoupon extends ReceiveCouponSupport {

    @Id
    @Column(name = "id_")
    private String id;

    @Column(name = "tenant_id_")
    private String tenantId;

    @Column(name = "store_id_")
    private String storeId;

    @Column(name = "coupon_id_")
    private String couponId;

    @Column(name = "receiver_id_")
    private String receiverId;

    @Column(name = "code_")
    private String code;

    @Column(name = "name_")
    private String name;

    @Column(name = "description_")
    private String description;

    @Column(name = "type_")
    private CouponType type;

    @Column(name = "status_")
    private CouponStatus status;

    @Column(name = "received_time_")
    private Date receivedTime;
}