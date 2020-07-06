package org.mallfoundry.catalog.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mallfoundry.catalog.DefaultOptionSelection;
import org.mallfoundry.catalog.OptionSelection;
import org.mallfoundry.inventory.InventoryStatus;
import org.mallfoundry.util.Positions;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

@Getter
@Setter
@NoArgsConstructor
public abstract class ProductSupport implements MutableProduct {

    public ProductSupport(String id) {
        this.setId(id);
    }

    @Override
    public void freeShipping() {
        this.setFreeShipping(true);
        this.setFixedShippingCost(null);
        this.setShippingRateId(null);
    }

    private void setMinPrice() {
        double minPrice = this.getVariants().stream()
                .map(ProductVariant::getPrice)
                .mapToDouble(BigDecimal::doubleValue)
                .min()
                .orElseThrow();
        this.setPrice(BigDecimal.valueOf(minPrice));
    }

    private void checkInventoryQuantity() {
        this.setInventoryQuantity(
                this.getVariants().stream().mapToInt(ProductVariant::getInventoryQuantity).sum());
        this.setInventoryStatus(
                this.getInventoryQuantity() == 0
                        ? InventoryStatus.OUT_OF_STOCK : InventoryStatus.IN_STOCK);
    }

    @Override
    public ProductShippingOrigin createShippingOrigin() {
        return new DefaultProductShippingOrigin();
    }

    @Override
    public void adjustTotalSales(long sales) {
        this.setTotalSales(this.getTotalSales() + sales);
    }

    @Override
    public void adjustMonthlySales(long sales) {
        this.setMonthlySales(this.getMonthlySales() + sales);
    }

    @Override
    public void addVariant(ProductVariant variant) {
        variant.setProductId(this.getId());
        variant.setStoreId(this.getStoreId());
        this.getVariants().add(variant);
        Positions.sort(this.getVariants());
        this.checkInventoryQuantity();
        this.setMinPrice();
    }

    @Override
    public Optional<ProductVariant> getVariant(String id) {
        return this.getVariants()
                .stream()
                .filter(variant -> Objects.equals(variant.getId(), id))
                .findFirst();
    }

    @Override
    public void removeVariant(ProductVariant variant) {
        var removed =
                Objects.requireNonNull(this.getVariants(), ProductMessages.notEmpty("variants"))
                        .remove(variant);
        Assert.isTrue(removed, ProductMessages.variantNotFound());
        this.checkInventoryQuantity();
        this.setMinPrice();
    }

    @Override
    public void adjustInventoryQuantity(String variantId, int quantityDelta) {
        this.getVariant(variantId)
                .orElseThrow(() -> new ProductException(ProductMessages.variantNotFound().get()))
                .adjustInventoryQuantity(quantityDelta);
        this.checkInventoryQuantity();
    }

    @Override
    public void addOption(ProductOption option) {
        this.getOptions().add(option);
        Positions.sort(this.getOptions());
    }

    @Override
    public Optional<ProductOption> getOption(String name) {
        return this.getOptions().stream().filter(option -> Objects.equals(option.getName(), name)).findFirst();
    }

    @Override
    public Optional<OptionSelection> selectOption(final String name, final String label) {
        return this.getOption(name)
                .map(option -> Map.entry(option, option.getValue(label).orElseThrow()))
                .map(entry -> new DefaultOptionSelection(entry.getKey().getId(), name, entry.getValue().getId(), label));
    }

    @Override
    public void removeOption(ProductOption option) {
        var removed = this.getOptions().remove(option);
        Assert.isTrue(removed, ProductMessages.optionNotFound());
    }

    @Override
    public Optional<ProductAttribute> getAttribute(String namespace, String name) {
        return this.getAttributes()
                .stream()
                .filter(attribute ->
                        Objects.equals(attribute.getNamespace(), namespace)
                                && Objects.equals(attribute.getName(), name))
                .findFirst();
    }

    @Override
    public void removeAttribute(ProductAttribute attribute) {
        this.getAttributes().remove(attribute);
    }

    @Override
    public void addImageUrl(String imageUrl) {
        this.getImageUrls().add(imageUrl);
    }

    @Override
    public void removeImageUrl(String url) {
        this.getImageUrls().remove(url);
    }

    @Override
    public void addVideoUrl(String video) {
        this.getVideoUrls().add(video);
    }

    @Override
    public void removeVideoUrl(String url) {
        this.getVideoUrls().remove(url);
    }

    @Override
    public void addAttribute(ProductAttribute attribute) {
        this.getAttributes().add(attribute);
        Positions.sort(this.getAttributes());
    }

    @Override
    public void create() {
        this.setCreatedTime(new Date());
    }

    @Override
    public Builder toBuilder() {
        return new BuilderSupport(this) {
        };
    }

    abstract static class BuilderSupport implements Builder {

        protected final Product product;

        BuilderSupport(Product product) {
            this.product = product;
        }

        @Override
        public Builder storeId(String storeId) {
            this.product.setStoreId(storeId);
            return this;
        }

        @Override
        public Builder name(String name) {
            this.product.setName(name);
            return this;
        }

        @Override
        public Builder type(ProductType type) {
            this.product.setType(type);
            return this;
        }

        @Override
        public Builder status(ProductStatus status) {
            this.product.setStatus(status);
            return this;
        }

        @Override
        public Builder categoryId(String categoryId) {
            this.product.setCategoryId(categoryId);
            return this;
        }

        @Override
        public Builder brandId(String brandId) {
            this.product.setBrandId(brandId);
            return this;
        }

        @Override
        public Builder freeShipping() {
            this.product.freeShipping();
            return this;
        }

        @Override
        public Builder fixedShippingCost(BigDecimal fixedShippingCost) {
            this.product.setFixedShippingCost(fixedShippingCost);
            return this;
        }

        @Override
        public Builder fixedShippingCost(double fixedShippingCost) {
            return this.fixedShippingCost(BigDecimal.valueOf(fixedShippingCost));
        }

        @Override
        public Builder shippingOrigin(ProductShippingOrigin shippingOrigin) {
            this.product.setShippingOrigin(shippingOrigin);
            return this;
        }

        @Override
        public Builder shippingOrigin(Function<Product, ProductShippingOrigin> shippingOrigin) {
            return this.shippingOrigin(shippingOrigin.apply(this.product));
        }

        @Override
        public Builder adjustTotalSales(long sales) {
            this.product.adjustTotalSales(sales);
            return this;
        }

        @Override
        public Builder adjustMonthlySales(long sales) {
            this.product.adjustMonthlySales(sales);
            return this;
        }

        @Override
        public Builder collections(Set<String> collections) {
            this.product.setCollections(collections);
            return this;
        }

        @Override
        public Builder imageUrl(String image) {
            this.product.addImageUrl(image);
            return this;
        }

        @Override
        public Builder videoUrl(String video) {
            this.product.addVideoUrl(video);
            return this;
        }

        @Override
        public Builder option(ProductOption option) {
            this.product.addOption(option);
            return this;
        }

        @Override
        public Builder option(Function<Product, ProductOption> option) {
            return this.option(option.apply(this.product));
        }

        @Override
        public Builder variant(ProductVariant variant) {
            this.product.addVariant(variant);
            return this;
        }

        @Override
        public Builder variant(Function<Product, ProductVariant> variant) {
            return this.variant(variant.apply(this.product));
        }

        @Override
        public Builder attribute(ProductAttribute attribute) {
            this.product.addAttribute(attribute);
            return this;
        }

        @Override
        public Builder attribute(Function<Product, ProductAttribute> attribute) {
            return this.attribute(attribute.apply(this.product));
        }

        @Override
        public Builder create() {
            this.product.create();
            return this;
        }

        @Override
        public Product build() {
            return this.product;
        }
    }
}
