package org.mallfoundry.catalog.product.repository.jpa;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import org.mallfoundry.catalog.product.DefaultProductAttribute;
import org.mallfoundry.catalog.product.InternalProductOption;
import org.mallfoundry.catalog.product.Product;
import org.mallfoundry.catalog.product.ProductAttribute;
import org.mallfoundry.catalog.product.ProductOption;
import org.mallfoundry.catalog.product.ProductStatus;
import org.mallfoundry.catalog.product.ProductSupport;
import org.mallfoundry.catalog.product.ProductType;
import org.mallfoundry.catalog.product.ProductVariant;
import org.mallfoundry.catalog.product.repository.jpa.convert.ProductAttributeListConverter;
import org.mallfoundry.data.jpa.convert.StringListConverter;
import org.mallfoundry.data.jpa.convert.StringSetConverter;
import org.mallfoundry.inventory.InventoryStatus;
import org.springframework.beans.BeanUtils;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "mf_catalog_product")
public class JpaProduct extends ProductSupport {

    @Id
    @Column(name = "id_")
    private String id;

    @Column(name = "store_id_")
    private String storeId;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "type_")
    private ProductType type;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status_")
    private ProductStatus status;

    @Column(name = "name_")
    private String name;

    @Column(name = "description_")
    private String description;

    @Column(name = "collections_")
    @Convert(converter = StringSetConverter.class)
    private Set<String> collections = new HashSet<>();

    @Column(name = "price_")
    private BigDecimal price;

    @Column(name = "market_price_")
    private BigDecimal marketPrice;

    @OneToMany(targetEntity = InternalProductOption.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "product_id_")
    @JsonDeserialize(contentAs = InternalProductOption.class)
    private List<ProductOption> options = new ArrayList<>();

    @Column(name = "attributes_", length = 2048)
    @Convert(converter = ProductAttributeListConverter.class)
    @JsonDeserialize(contentAs = DefaultProductAttribute.class)
    private List<ProductAttribute> attributes = new ArrayList<>();

    @OneToMany(targetEntity = JpaProductVariant.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonDeserialize(contentAs = JpaProductVariant.class)
    @JoinColumn(name = "product_id_")
    private List<ProductVariant> variants = new ArrayList<>();

    @Column(name = "image_urls_", length = 2048)
    @Convert(converter = StringListConverter.class)
    private List<String> imageUrls = new ArrayList<>();

    @Column(name = "video_urls_", length = 2048)
    @Convert(converter = StringListConverter.class)
    private List<String> videoUrls = new ArrayList<>();

    @Column(name = "shipping_origin_")
    private String shippingOrigin;

    @Column(name = "free_shipping_")
    private boolean freeShipping;

    @Column(name = "fixed_shipping_cost_")
    private BigDecimal fixedShippingCost;

    @Column(name = "shipping_rate_id_")
    private String shippingRateId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "created_time_")
    private Date createdTime;

    public JpaProduct() {
        super(null);
    }

    public JpaProduct(String id) {
        super(id);
    }

    public static JpaProduct of(Product product) {
        if (product instanceof JpaProduct) {
            return (JpaProduct) product;
        }
        var target = new JpaProduct(product.getId());
        BeanUtils.copyProperties(product, target);
        return target;
    }

    @Transient
    @Override
    public BigDecimal getPrice() {
        return super.getPrice();
    }

    @Transient
    @Override
    public int getInventoryQuantity() {
        return super.getInventoryQuantity();
    }

    @Transient
    @Override
    public InventoryStatus getInventoryStatus() {
        return super.getInventoryStatus();
    }

    @Override
    public ProductVariant createVariant(String id) {
        return new JpaProductVariant(id);
    }

    @Override
    public ProductAttribute createAttribute(String name, String value) {
        return new DefaultProductAttribute(name, value);
    }

    @Override
    public ProductAttribute createAttribute(String namespace, String name, String value) {
        return new DefaultProductAttribute(namespace, name, value);
    }
}
