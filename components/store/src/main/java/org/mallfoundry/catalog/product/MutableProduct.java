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

package org.mallfoundry.catalog.product;

import org.mallfoundry.inventory.InventoryStatus;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface MutableProduct extends Product {

    void setFreeShipping(boolean freeShipping);

    void setInventoryQuantity(int quantity);

    void setInventoryStatus(InventoryStatus status);

    void setPrice(BigDecimal price);

    void setOptions(List<ProductOption> options);

    void setVariants(List<ProductVariant> variants);

    void setAttributes(List<ProductAttribute> attributes);

    void setImageUrls(List<String> imageUrls);

    void setVideoUrls(List<String> videoUrls);

    void setPublishedTime(Date publishedTime);

    void setCreatedTime(Date createdTime);
}
