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

import java.util.Date;

public interface MutableOrder extends Order {

    void setStatus(OrderStatus status);

    void setShippedItems(int items);

    void setSignMessage(String signMessage);

    void setCancelReason(String cancelReason);

    void setPlacingExpires(int placingExpires);

    void setPlacingExpiredTime(Date placingExpiredTime);

    void setPlacedTime(Date placedTime);

    void setPaidTime(Date paidTime);

    void setFulfilledTime(Date fulfilledTime);

    void setShippedTime(Date shippedTime);

    void setCancelledTime(Date cancelledTime);

    void setSignedTime(Date signedTime);

    void setReceivedTime(Date receivedTime);
}
