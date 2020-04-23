/*
 * Copyright 2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mallfoundry.store.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mallfoundry.data.jpa.convert.StringListConverter;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Each product can have multiple variations.
 * The product variant is a combination of option values.
 *
 * @author Zhi Tang
 */
@Getter
@Setter
@Entity
@Table(name = "store_product_variant")
public class ProductVariant implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id_")
    private Long id;

    @Column(name = "price_")
    private BigDecimal price;

    @Column(name = "market_price_")
    @JsonProperty("market_price")
    private BigDecimal marketPrice;

    @Column(name = "cost_price_")
    @JsonProperty("cost_price")
    private BigDecimal costPrice;

    @Column(name = "weight_")
    private String weight;

    @Column(name = "inventory_quantity_")
    @JsonProperty("inventory_quantity")
    private int inventoryQuantity;

    @JsonProperty("option_values")
    @Column(name = "option_values_")
    @Convert(converter = StringListConverter.class)
    private List<String> optionValues = new ArrayList<>();

    @JsonProperty("image_urls")
    @Lob
    @Convert(converter = StringListConverter.class)
    @Column(name = "image_urls_")
    private List<String> imageUrls;

    @Column(name = "position_")
    private Integer position;

    @JsonIgnore
    public String getFirstImageUrl() {
        return CollectionUtils.isEmpty(imageUrls) ? null : imageUrls.iterator().next();
    }

    public void decrementInventoryQuantity(int quantity) throws InventoryException {
        if (quantity > this.getInventoryQuantity()) {
            throw new InventoryException("The stock quantity is insufficient");
        }
        this.setInventoryQuantity(this.getInventoryQuantity() - quantity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductVariant that = (ProductVariant) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static class Builder {

        private ProductVariant variant;

        public Builder() {
            variant = new ProductVariant();
        }

        public Builder price(double price) {
            this.variant.setPrice(BigDecimal.valueOf(price));
            return this;
        }

        public Builder marketPrice(double marketPrice) {
            this.variant.setMarketPrice(BigDecimal.valueOf(marketPrice));
            return this;
        }

        public Builder inventoryQuantity(int inventoryQuantity) {
            this.variant.setInventoryQuantity(inventoryQuantity);
            return this;
        }

        public Builder position(int position) {
            this.variant.setPosition(position);
            return this;
        }

        public Builder optionValues(List<String> optionValues) {
            this.variant.setOptionValues(optionValues);
            return this;
        }

        public Builder imageUrls(List<String> imageUrls) {
            this.variant.setImageUrls(imageUrls);
            return this;
        }

        public ProductVariant build() {
            return this.variant;
        }
    }
}
