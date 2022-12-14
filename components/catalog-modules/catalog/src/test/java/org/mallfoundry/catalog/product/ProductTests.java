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


import org.junit.jupiter.api.Test;
import org.mallfoundry.keygen.PrimaryKeyHolder;
import org.mallfoundry.test.StandaloneTest;
import org.mallfoundry.test.StaticUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@StandaloneTest
public class ProductTests {

    private static final String PRODUCT_VARIANT_ID_VALUE_NAME = "store.product.variant.id";

    private static final String PRODUCT_OPTION_ID_VALUE_NAME = "store.product.option.id";

    private static final String PRODUCT_OPTION_VALUE_ID_VALUE_NAME = "store.product.option.value.id";

    @Autowired
    private ProductService productService;

    @Test
    @Rollback(false)
    @Transactional
    public void testGetProduct() {
        System.out.println("");
    }

    @Test
    @Rollback(false)
    @Transactional
    public void testSaveProduct() {
        this.newProduct4();
    }

    @WithUserDetails("mf_1")
    @Test
    @Rollback(false)
    @Transactional
    public void testSaveProducts() throws InterruptedException {
        productService.addProduct(this.newProduct1());
        Thread.sleep(1000 * 2);
        productService.addProduct(this.newProduct2());
        Thread.sleep(1000 * 2);
        productService.addProduct(this.newProduct2_1());
        Thread.sleep(1000 * 2);
        productService.addProduct(this.newProduct2_redmi_k30());
        Thread.sleep(1000 * 2);
        productService.addProduct(this.newProduct2_redmi9());
        Thread.sleep(1000 * 2);
        productService.addProduct(this.newProduct3());
        Thread.sleep(1000 * 2);
        productService.addProduct(this.newProduct4());
        Thread.sleep(1000 * 2);
        productService.addProduct(this.newProduct5());
    }

    private String resolveImageUrl(String id) {
        return StaticUtils.resolve("/images/" + id);
    }

    private Product newProduct1() {
        double price = 0.01;
        return this.productService.createProduct(null)
                .toBuilder()
                .storeId("huawei")
                .name("?????? HUAWEI Mate 30 Pro 5G ??????990 OLED????????????4000???????????????????????????")
                .type(ProductType.PHYSICAL)
                .status(ProductStatus.ACTIVE)
                .categories(List.of("1"))
                .brandId("1")
                .imageUrl(resolveImageUrl("e070a0bc693efc85.jpg"))
                .imageUrl(resolveImageUrl("cd96fb7761beeb9e.jpg"))
                .imageUrl(resolveImageUrl("c78c80a4116ee57d.jpg"))
                .imageUrl(resolveImageUrl("777b12adea1822f6.jpg"))
                //
                .freeShipping()
                //
                .origin(product -> product.createOrigin().toBuilder()
                        .provinceId("90").province("?????????")
                        .cityId("1427").city("?????????")
                        .countyId("11191").county("?????????")
                        .build())
                //
                .monthlySales(10)
                .totalSales(10)
                //
                .option(product1 -> product1.createOption(PrimaryKeyHolder.next(PRODUCT_OPTION_ID_VALUE_NAME)).toBuilder()
                        .name("??????")
                        .value(option -> option.createValue(PrimaryKeyHolder.next(PRODUCT_OPTION_VALUE_ID_VALUE_NAME)).toBuilder().label("?????????").build())
                        .value(option -> option.createValue(PrimaryKeyHolder.next(PRODUCT_OPTION_VALUE_ID_VALUE_NAME)).toBuilder().label("?????????").build())
                        .build())
                .option(product1 -> product1.createOption(PrimaryKeyHolder.next(PRODUCT_OPTION_ID_VALUE_NAME))
                        .toBuilder()
                        .name("??????")
                        .value(option -> option.createValue(PrimaryKeyHolder.next(PRODUCT_OPTION_VALUE_ID_VALUE_NAME)).toBuilder().label("8GB 128GB").build())
                        .value(option -> option.createValue(PrimaryKeyHolder.next(PRODUCT_OPTION_VALUE_ID_VALUE_NAME)).toBuilder().label("12GB 256GB").build())
                        .build())
                //
                .variant(product1 -> product1.createVariant(PrimaryKeyHolder.next(PRODUCT_VARIANT_ID_VALUE_NAME)).toBuilder().retailPrice(price).price(price).adjustInventoryQuantity(100)
                        .imageUrls(List.of(resolveImageUrl("e070a0bc693efc85.jpg"), resolveImageUrl("cd96fb7761beeb9e.jpg")))
                        .optionSelections(List.of(
                                product1.selectOption("??????", "?????????").orElseThrow(),
                                product1.selectOption("??????", "8GB 128GB").orElseThrow()))
                        .position(0).build())
                .variant(product1 -> product1.createVariant(PrimaryKeyHolder.next(PRODUCT_VARIANT_ID_VALUE_NAME)).toBuilder().retailPrice(price).price(price).adjustInventoryQuantity(100)
                        .imageUrls(List.of(resolveImageUrl("e070a0bc693efc85.jpg"), resolveImageUrl("cd96fb7761beeb9e.jpg")))
                        .optionSelections(List.of(
                                product1.selectOption("??????", "?????????").orElseThrow(),
                                product1.selectOption("??????", "12GB 256GB").orElseThrow()))
                        .position(0).build())
                .variant(product1 -> product1.createVariant(PrimaryKeyHolder.next(PRODUCT_VARIANT_ID_VALUE_NAME)).toBuilder().retailPrice(price).price(price).adjustInventoryQuantity(100)
                        .imageUrls(List.of(resolveImageUrl("c78c80a4116ee57d.jpg"), resolveImageUrl("777b12adea1822f6.jpg")))
                        .optionSelections(List.of(
                                product1.selectOption("??????", "?????????").orElseThrow(),
                                product1.selectOption("??????", "12GB 256GB").orElseThrow()))
                        .position(1).build())
                .variant(product1 -> product1.createVariant(PrimaryKeyHolder.next(PRODUCT_VARIANT_ID_VALUE_NAME)).toBuilder().retailPrice(price).price(price).adjustInventoryQuantity(100)
                        .imageUrls(List.of(resolveImageUrl("c78c80a4116ee57d.jpg"), resolveImageUrl("777b12adea1822f6.jpg")))
                        .optionSelections(List.of(
                                product1.selectOption("??????", "?????????").orElseThrow(),
                                product1.selectOption("??????", "12GB 256GB").orElseThrow()))
                        .position(1).build())
                //attributes
                .attribute(product1 -> product1.createAttribute("??????", "??????"))
                .attribute(product1 -> product1.createAttribute("????????????", "2018-10-11"))
                .attribute(product1 -> product1.createAttribute("??????", "???"))
                .create()
                .build();
    }

    private Product newProduct2() {
        double price = 0.04;
        return this.productService.createProduct(null)
                .toBuilder()
                .storeId("mi")
                .name("??????9 Pro 5G ??????855Plus 30W??????????????????")
                .type(ProductType.PHYSICAL)
                .status(ProductStatus.ACTIVE)
                .categories(List.of("1"))
                .brandId("1")
                .collections(Set.of("12236", "12243"))
                .imageUrl(resolveImageUrl("da769739c0a75afb.jpg"))
                .imageUrl(resolveImageUrl("753768612ae90b4e.jpg"))
                .imageUrl(resolveImageUrl("cd769d2bd022de2a.jpg"))
                .imageUrl(resolveImageUrl("62edde5e1ef2fd85.jpg"))
                //
                .fixedShippingCost(10.00)
                //
                .origin(product -> product.createOrigin().toBuilder()
                        .provinceId("82").province("?????????")
                        .cityId("1353").city("?????????")
                        .countyId("10451").county("?????????")
                        .build())
                //
                .monthlySales(20)
                .totalSales(20)
                //
                .option(product -> product.createOption(PrimaryKeyHolder.next(PRODUCT_OPTION_ID_VALUE_NAME)).toBuilder()
                        .name("??????")
                        .value(option -> option.createValue(PrimaryKeyHolder.next(PRODUCT_OPTION_VALUE_ID_VALUE_NAME)).toBuilder().label("?????????").build())
                        .value(option -> option.createValue(PrimaryKeyHolder.next(PRODUCT_OPTION_VALUE_ID_VALUE_NAME)).toBuilder().label("?????????").build())
                        .build())

                .option(product -> product.createOption(PrimaryKeyHolder.next(PRODUCT_OPTION_ID_VALUE_NAME))
                        .toBuilder()
                        .name("??????")
                        .value(option -> option.createValue(PrimaryKeyHolder.next(PRODUCT_OPTION_VALUE_ID_VALUE_NAME)).toBuilder().label("8GB 128GB").build())
                        .value(option -> option.createValue(PrimaryKeyHolder.next(PRODUCT_OPTION_VALUE_ID_VALUE_NAME)).toBuilder().label("12GB 256GB").build())
                        .build())
                //
                .variant(product ->
                        product.createVariant(PrimaryKeyHolder.next(PRODUCT_VARIANT_ID_VALUE_NAME)).toBuilder().retailPrice(price).price(0.04).adjustInventoryQuantity(100)
                                .imageUrls(List.of(resolveImageUrl("da769739c0a75afb.jpg"), resolveImageUrl("753768612ae90b4e.jpg")))
                                .optionSelections(List.of(
                                        product.selectOption("??????", "?????????").orElseThrow(),
                                        product.selectOption("??????", "8GB 128GB").orElseThrow()))
                                .position(0).build())
                .variant(product ->
                        product.createVariant(PrimaryKeyHolder.next(PRODUCT_VARIANT_ID_VALUE_NAME)).toBuilder().retailPrice(price).price(0.06).adjustInventoryQuantity(100)
                                .imageUrls(List.of(resolveImageUrl("da769739c0a75afb.jpg"), resolveImageUrl("753768612ae90b4e.jpg")))
                                .optionSelections(List.of(
                                        product.selectOption("??????", "?????????").orElseThrow(),
                                        product.selectOption("??????", "12GB 256GB").orElseThrow()))
                                .position(1).build())
                .variant(product ->
                        product.createVariant(PrimaryKeyHolder.next(PRODUCT_VARIANT_ID_VALUE_NAME)).toBuilder().retailPrice(price).price(0.08).adjustInventoryQuantity(100)
                                .imageUrls(List.of(resolveImageUrl("cd769d2bd022de2a.jpg"), resolveImageUrl("62edde5e1ef2fd85.jpg")))
                                .optionSelections(List.of(
                                        product.selectOption("??????", "?????????").orElseThrow(),
                                        product.selectOption("??????", "8GB 128GB").orElseThrow()))
                                .position(2).build())
                .variant(product ->
                        product.createVariant(PrimaryKeyHolder.next(PRODUCT_VARIANT_ID_VALUE_NAME)).toBuilder().retailPrice(price).price(0.1).adjustInventoryQuantity(100)
                                .imageUrls(List.of(resolveImageUrl("cd769d2bd022de2a.jpg"), resolveImageUrl("62edde5e1ef2fd85.jpg")))
                                .optionSelections(List.of(
                                        product.selectOption("??????", "?????????").orElseThrow(),
                                        product.selectOption("??????", "12GB 256GB").orElseThrow()))
                                .position(3).build())
                //attributes
                .attribute(product -> product.createAttribute("??????", "??????"))
                .attribute(product -> product.createAttribute("????????????", "2018-10-11"))
                .attribute(product -> product.createAttribute("??????", "???"))
                .create()
                .build();
    }

    private Product newProduct2_1() {
        double price = 0.02;
        return this.productService.createProduct(null)
                .toBuilder()
                .storeId("mi")
                .name("?????????????????????????????????10 ??????5G ??????865 1?????????8K???????????? ??????????????????")
                .type(ProductType.DIGITAL)
                .status(ProductStatus.ACTIVE)
                .categories(List.of("1"))
                .brandId("1")
                .collections(Set.of("12230", "12243"))
                .imageUrl(resolveImageUrl("6626003a708ce8ef.jpg"))
                .imageUrl(resolveImageUrl("df797544001e5ba3.jpg"))
                .imageUrl(resolveImageUrl("979bde1d68c3de5b.jpg"))
                .imageUrl(resolveImageUrl("0ad4ba55c20c6903.jpg"))
                .imageUrl(resolveImageUrl("e523be52552921bf.jpg"))
                .imageUrl(resolveImageUrl("6765a52c369741ec.jpg"))
                //
                .fixedShippingCost(20.00)
                //
                .origin(product -> product.createOrigin().toBuilder()
                        .provinceId("82").province("?????????")
                        .cityId("1354").city("?????????")
                        .countyId("10462").county("?????????")
                        .build())
                //
                .monthlySales(30)
                .totalSales(30)
                //
                // options
                .option(product -> product.createOption(PrimaryKeyHolder.next(PRODUCT_OPTION_ID_VALUE_NAME)).toBuilder()
                        .name("??????")
                        .value(option -> option.createValue(PrimaryKeyHolder.next(PRODUCT_OPTION_VALUE_ID_VALUE_NAME)).toBuilder().label("?????????").build())
                        .value(option -> option.createValue(PrimaryKeyHolder.next(PRODUCT_OPTION_VALUE_ID_VALUE_NAME)).toBuilder().label("?????????").build())
                        .value(option -> option.createValue(PrimaryKeyHolder.next(PRODUCT_OPTION_VALUE_ID_VALUE_NAME)).toBuilder().label("?????????").build())
                        .build())
                .option(product -> product.createOption(PrimaryKeyHolder.next(PRODUCT_OPTION_ID_VALUE_NAME))
                        .toBuilder()
                        .name("??????")
                        .value(option -> option.createValue(PrimaryKeyHolder.next(PRODUCT_OPTION_VALUE_ID_VALUE_NAME)).toBuilder().label("8GB 128GB").build())
                        .value(option -> option.createValue(PrimaryKeyHolder.next(PRODUCT_OPTION_VALUE_ID_VALUE_NAME)).toBuilder().label("8GB 256GB").build())
                        .value(option -> option.createValue(PrimaryKeyHolder.next(PRODUCT_OPTION_VALUE_ID_VALUE_NAME)).toBuilder().label("12GB 256GB").build())
                        .build())
                //
                .variant(product ->
                        product.createVariant(PrimaryKeyHolder.next(PRODUCT_VARIANT_ID_VALUE_NAME)).toBuilder().retailPrice(price).price(0.1).adjustInventoryQuantity(100)
                                .imageUrls(List.of(resolveImageUrl("6626003a708ce8ef.jpg"), resolveImageUrl("df797544001e5ba3.jpg"), resolveImageUrl("979bde1d68c3de5b.jpg")))
                                .optionSelections(List.of(
                                        product.selectOption("??????", "?????????").orElseThrow(),
                                        product.selectOption("??????", "8GB 128GB").orElseThrow()))
                                .position(0).build())
                .variant(product ->
                        product.createVariant(PrimaryKeyHolder.next(PRODUCT_VARIANT_ID_VALUE_NAME)).toBuilder().retailPrice(price).price(0.12).adjustInventoryQuantity(100)
                                .imageUrls(List.of(resolveImageUrl("6626003a708ce8ef.jpg"), resolveImageUrl("df797544001e5ba3.jpg"), resolveImageUrl("979bde1d68c3de5b.jpg")))
                                .optionSelections(List.of(
                                        product.selectOption("??????", "?????????").orElseThrow(),
                                        product.selectOption("??????", "8GB 256GB").orElseThrow()))
                                .position(1).build())
                .variant(product ->
                        product.createVariant(PrimaryKeyHolder.next(PRODUCT_VARIANT_ID_VALUE_NAME)).toBuilder().retailPrice(price).price(0.14).adjustInventoryQuantity(100)
                                .imageUrls(List.of(resolveImageUrl("6626003a708ce8ef.jpg"), resolveImageUrl("df797544001e5ba3.jpg"), resolveImageUrl("979bde1d68c3de5b.jpg")))
                                .optionSelections(List.of(
                                        product.selectOption("??????", "?????????").orElseThrow(),
                                        product.selectOption("??????", "12GB 256GB").orElseThrow()))
                                .position(1).build())

                .variant(product ->
                        product.createVariant(PrimaryKeyHolder.next(PRODUCT_VARIANT_ID_VALUE_NAME)).toBuilder().retailPrice(price).price(0.16).adjustInventoryQuantity(100)
                                .imageUrls(List.of(resolveImageUrl("0ad4ba55c20c6903.jpg"), resolveImageUrl("e523be52552921bf.jpg"), resolveImageUrl("6765a52c369741ec.jpg")))
                                .optionSelections(List.of(
                                        product.selectOption("??????", "?????????").orElseThrow(),
                                        product.selectOption("??????", "8GB 128GB").orElseThrow()))
                                .position(0).build())
                .variant(product ->
                        product.createVariant(PrimaryKeyHolder.next(PRODUCT_VARIANT_ID_VALUE_NAME)).toBuilder().retailPrice(price).price(0.18).adjustInventoryQuantity(100)
                                .imageUrls(List.of(resolveImageUrl("0ad4ba55c20c6903.jpg"), resolveImageUrl("e523be52552921bf.jpg"), resolveImageUrl("6765a52c369741ec.jpg")))
                                .optionSelections(List.of(
                                        product.selectOption("??????", "?????????").orElseThrow(),
                                        product.selectOption("??????", "8GB 256GB").orElseThrow()))
                                .position(1).build())
                .variant(product ->
                        product.createVariant(PrimaryKeyHolder.next(PRODUCT_VARIANT_ID_VALUE_NAME)).toBuilder().retailPrice(price).price(price).adjustInventoryQuantity(100)
                                .imageUrls(List.of(resolveImageUrl("0ad4ba55c20c6903.jpg"), resolveImageUrl("e523be52552921bf.jpg"), resolveImageUrl("6765a52c369741ec.jpg")))
                                .optionSelections(List.of(
                                        product.selectOption("??????", "?????????").orElseThrow(),
                                        product.selectOption("??????", "12GB 256GB").orElseThrow()))
                                .position(1).build())

                .variant(product ->
                        product.createVariant(PrimaryKeyHolder.next(PRODUCT_VARIANT_ID_VALUE_NAME)).toBuilder().retailPrice(price).price(price).adjustInventoryQuantity(100)
                                .imageUrls(List.of(resolveImageUrl("6ec29e79b28d7c0b.jpg"), resolveImageUrl("05fdde3b515dae87.jpg"), resolveImageUrl("e7233f1b0e4b0c45.jpg")))
                                .optionSelections(List.of(
                                        product.selectOption("??????", "?????????").orElseThrow(),
                                        product.selectOption("??????", "8GB 128GB").orElseThrow()))
                                .position(0).build())
                .variant(product ->
                        product.createVariant(PrimaryKeyHolder.next(PRODUCT_VARIANT_ID_VALUE_NAME)).toBuilder().retailPrice(price).price(price).adjustInventoryQuantity(100)
                                .imageUrls(List.of(resolveImageUrl("6ec29e79b28d7c0b.jpg"), resolveImageUrl("05fdde3b515dae87.jpg"), resolveImageUrl("e7233f1b0e4b0c45.jpg")))
                                .optionSelections(List.of(
                                        product.selectOption("??????", "?????????").orElseThrow(),
                                        product.selectOption("??????", "8GB 256GB").orElseThrow()))
                                .position(1).build())
                .variant(product ->
                        product.createVariant(PrimaryKeyHolder.next(PRODUCT_VARIANT_ID_VALUE_NAME)).toBuilder().retailPrice(price).price(price).adjustInventoryQuantity(100)
                                .imageUrls(List.of(resolveImageUrl("6ec29e79b28d7c0b.jpg"), resolveImageUrl("05fdde3b515dae87.jpg"), resolveImageUrl("e7233f1b0e4b0c45.jpg")))
                                .optionSelections(List.of(
                                        product.selectOption("??????", "?????????").orElseThrow(),
                                        product.selectOption("??????", "12GB 256GB").orElseThrow()))
                                .position(1).build())
                //attributes
                .attribute(product -> product.createAttribute("??????", "??????"))
                .attribute(product -> product.createAttribute("????????????", "2018-10-11"))
                .attribute(product -> product.createAttribute("??????", "???"))
                .create()
                .build();
    }

    private Product newProduct2_redmi_k30() {
        double price = 0.18;
        return this.productService.createProduct(null)
                .toBuilder()
                .storeId("mi")
                .categories(List.of("1"))
                .brandId("1")
                .name("Redmi K30 Pro 5G?????? ??????865??????????????? ??????????????????????????? ??????6400??????????????? 4700mAh????????? 33W??????")
                .type(ProductType.DIGITAL)
                .status(ProductStatus.ACTIVE)
                .imageUrl(resolveImageUrl("redmi_k30pro_1_0.jpg"))
                .imageUrl(resolveImageUrl("redmi_k30pro_1_1.jpg"))
                .imageUrl(resolveImageUrl("redmi_k30pro_1_2.jpg"))
                .imageUrl(resolveImageUrl("redmi_k30pro_1_3.jpg"))
                .imageUrl(resolveImageUrl("redmi_k30pro_2_0.jpg"))
                .imageUrl(resolveImageUrl("redmi_k30pro_2_1.jpg"))
                .imageUrl(resolveImageUrl("redmi_k30pro_2_2.jpg"))
                .imageUrl(resolveImageUrl("redmi_k30pro_2_3.jpg"))
                .imageUrl(resolveImageUrl("redmi_k30pro_2_4.jpg"))
                .imageUrl(resolveImageUrl("redmi_k30pro_3_0.jpg"))
                .imageUrl(resolveImageUrl("redmi_k30pro_3_1.jpg"))
                .imageUrl(resolveImageUrl("redmi_k30pro_3_2.jpg"))
                .imageUrl(resolveImageUrl("redmi_k30pro_3_3.jpg"))
                .imageUrl(resolveImageUrl("redmi_k30pro_3_4.jpg"))
                .imageUrl(resolveImageUrl("redmi_k30pro_4_0.jpg"))
                .imageUrl(resolveImageUrl("redmi_k30pro_4_0.jpg"))
                .imageUrl(resolveImageUrl("redmi_k30pro_4_1.jpg"))
                .imageUrl(resolveImageUrl("redmi_k30pro_4_2.jpg"))
                .imageUrl(resolveImageUrl("redmi_k30pro_4_3.jpg"))
                //
                .fixedShippingCost(10.00)
                //
                .origin(product -> product.createOrigin().toBuilder()
                        .provinceId("82").province("?????????")
                        .cityId("1354").city("?????????")
                        .countyId("10462").county("?????????")
                        .build())
                //
                .monthlySales(30)
                .totalSales(30)
                //
                // options
                .option(product -> product.createOption(PrimaryKeyHolder.next(PRODUCT_OPTION_ID_VALUE_NAME)).toBuilder()
                        .name("??????")
                        .value(option -> option.createValue(PrimaryKeyHolder.next(PRODUCT_OPTION_VALUE_ID_VALUE_NAME)).toBuilder().label("?????????").build())
                        .value(option -> option.createValue(PrimaryKeyHolder.next(PRODUCT_OPTION_VALUE_ID_VALUE_NAME)).toBuilder().label("?????????").build())
                        .value(option -> option.createValue(PrimaryKeyHolder.next(PRODUCT_OPTION_VALUE_ID_VALUE_NAME)).toBuilder().label("?????????").build())
                        .value(option -> option.createValue(PrimaryKeyHolder.next(PRODUCT_OPTION_VALUE_ID_VALUE_NAME)).toBuilder().label("?????????").build())
                        .build())
                .option(product -> product.createOption(PrimaryKeyHolder.next(PRODUCT_OPTION_ID_VALUE_NAME))
                        .toBuilder()
                        .name("??????")
                        .value(option -> option.createValue(PrimaryKeyHolder.next(PRODUCT_OPTION_VALUE_ID_VALUE_NAME)).toBuilder().label("?????????8GB+128GB").build())
                        .value(option -> option.createValue(PrimaryKeyHolder.next(PRODUCT_OPTION_VALUE_ID_VALUE_NAME)).toBuilder().label("?????????12GB+256GB").build())
                        .build())
                //
                .variant(product ->
                        product.createVariant(PrimaryKeyHolder.next(PRODUCT_VARIANT_ID_VALUE_NAME)).toBuilder().retailPrice(price).price(0.1).adjustInventoryQuantity(100)
                                .imageUrls(List.of(resolveImageUrl("redmi_k30pro_1_0.jpg"), resolveImageUrl("redmi_k30pro_1_1.jpg"),
                                        resolveImageUrl("redmi_k30pro_1_2.jpg"), resolveImageUrl("redmi_k30pro_1_3.jpg")))
                                .optionSelections(List.of(
                                        product.selectOption("??????", "?????????").orElseThrow(),
                                        product.selectOption("??????", "?????????8GB+128GB").orElseThrow()))
                                .position(0).build())
                .variant(product ->
                        product.createVariant(PrimaryKeyHolder.next(PRODUCT_VARIANT_ID_VALUE_NAME)).toBuilder().retailPrice(price).price(0.3).adjustInventoryQuantity(100)
                                .imageUrls(List.of(resolveImageUrl("redmi_k30pro_1_0.jpg"), resolveImageUrl("redmi_k30pro_1_1.jpg"),
                                        resolveImageUrl("redmi_k30pro_1_2.jpg"), resolveImageUrl("redmi_k30pro_1_3.jpg")))
                                .optionSelections(List.of(
                                        product.selectOption("??????", "?????????").orElseThrow(),
                                        product.selectOption("??????", "?????????12GB+256GB").orElseThrow()))
                                .position(1).build())
                //
                .variant(product ->
                        product.createVariant(PrimaryKeyHolder.next(PRODUCT_VARIANT_ID_VALUE_NAME)).toBuilder().retailPrice(price).price(0.1).adjustInventoryQuantity(100)
                                .imageUrls(List.of(resolveImageUrl("redmi_k30pro_2_0.jpg"), resolveImageUrl("redmi_k30pro_2_1.jpg"),
                                        resolveImageUrl("redmi_k30pro_2_2.jpg"), resolveImageUrl("redmi_k30pro_2_3.jpg"), resolveImageUrl("redmi_k30pro_2_4.jpg")))
                                .optionSelections(List.of(
                                        product.selectOption("??????", "?????????").orElseThrow(),
                                        product.selectOption("??????", "?????????8GB+128GB").orElseThrow()))
                                .position(2).build())
                .variant(product ->
                        product.createVariant(PrimaryKeyHolder.next(PRODUCT_VARIANT_ID_VALUE_NAME)).toBuilder().retailPrice(price).price(0.3).adjustInventoryQuantity(100)
                                .imageUrls(List.of(resolveImageUrl("redmi_k30pro_2_0.jpg"), resolveImageUrl("redmi_k30pro_2_1.jpg"),
                                        resolveImageUrl("redmi_k30pro_2_2.jpg"), resolveImageUrl("redmi_k30pro_2_3.jpg"), resolveImageUrl("redmi_k30pro_2_4.jpg")))
                                .optionSelections(List.of(
                                        product.selectOption("??????", "?????????").orElseThrow(),
                                        product.selectOption("??????", "?????????12GB+256GB").orElseThrow()))
                                .position(3).build())
                //
                .variant(product ->
                        product.createVariant(PrimaryKeyHolder.next(PRODUCT_VARIANT_ID_VALUE_NAME)).toBuilder().retailPrice(price).price(0.1).adjustInventoryQuantity(100)
                                .imageUrls(List.of(resolveImageUrl("redmi_k30pro_3_0.jpg"), resolveImageUrl("redmi_k30pro_3_1.jpg"),
                                        resolveImageUrl("redmi_k30pro_3_2.jpg"), resolveImageUrl("redmi_k30pro_3_3.jpg"), resolveImageUrl("redmi_k30pro_3_4.jpg")))
                                .optionSelections(List.of(
                                        product.selectOption("??????", "?????????").orElseThrow(),
                                        product.selectOption("??????", "?????????8GB+128GB").orElseThrow()))
                                .position(4).build())
                .variant(product ->
                        product.createVariant(PrimaryKeyHolder.next(PRODUCT_VARIANT_ID_VALUE_NAME)).toBuilder().retailPrice(price).price(0.3).adjustInventoryQuantity(100)
                                .imageUrls(List.of(resolveImageUrl("redmi_k30pro_3_0.jpg"), resolveImageUrl("redmi_k30pro_3_1.jpg"),
                                        resolveImageUrl("redmi_k30pro_3_2.jpg"), resolveImageUrl("redmi_k30pro_3_3.jpg"), resolveImageUrl("redmi_k30pro_3_4.jpg")))
                                .optionSelections(List.of(
                                        product.selectOption("??????", "?????????").orElseThrow(),
                                        product.selectOption("??????", "?????????12GB+256GB").orElseThrow()))
                                .position(5).build())
                //
                .variant(product ->
                        product.createVariant(PrimaryKeyHolder.next(PRODUCT_VARIANT_ID_VALUE_NAME)).toBuilder().retailPrice(price).price(0.1).adjustInventoryQuantity(100)
                                .imageUrls(List.of(resolveImageUrl("redmi_k30pro_4_0.jpg"), resolveImageUrl("redmi_k30pro_4_1.jpg"),
                                        resolveImageUrl("redmi_k30pro_4_2.jpg"), resolveImageUrl("redmi_k30pro_4_3.jpg")))
                                .optionSelections(List.of(
                                        product.selectOption("??????", "?????????").orElseThrow(),
                                        product.selectOption("??????", "?????????8GB+128GB").orElseThrow()))
                                .position(6).build())
                .variant(product ->
                        product.createVariant(PrimaryKeyHolder.next(PRODUCT_VARIANT_ID_VALUE_NAME)).toBuilder().retailPrice(price).price(0.3).adjustInventoryQuantity(100)
                                .imageUrls(List.of(resolveImageUrl("redmi_k30pro_4_0.jpg"), resolveImageUrl("redmi_k30pro_4_1.jpg"),
                                        resolveImageUrl("redmi_k30pro_4_2.jpg"), resolveImageUrl("redmi_k30pro_4_3.jpg")))
                                .optionSelections(List.of(
                                        product.selectOption("??????", "?????????").orElseThrow(),
                                        product.selectOption("??????", "?????????12GB+256GB").orElseThrow()))
                                .position(7).build())
                //attributes
                .attribute(product -> product.createAttribute("??????", "??????"))
                .attribute(product -> product.createAttribute("????????????", "2018-10-11"))
                .attribute(product -> product.createAttribute("??????", "???"))
                .create()
                .build();
    }

    private Product newProduct2_redmi9() {
        double price = 0.18;
        return this.productService.createProduct(null)
                .toBuilder()
                .storeId("mi")
                .name("Redmi 9 5020mAh????????? 1080P??????????????? ??????????????????????????? ?????????AI?????? ?????????????????? ?????????????????? ?????? ??????")
                .type(ProductType.DIGITAL)
                .status(ProductStatus.ACTIVE)
                .categories(List.of("1"))
                .brandId("1")
                .imageUrl(resolveImageUrl("redmi9_1_0.jpg"))
                .imageUrl(resolveImageUrl("redmi9_1_1.jpg"))
                .imageUrl(resolveImageUrl("redmi9_1_2.jpg"))
                .imageUrl(resolveImageUrl("redmi9_1_3.jpg"))
                .imageUrl(resolveImageUrl("redmi9_2_0.jpg"))
                .imageUrl(resolveImageUrl("redmi9_2_1.jpg"))
                .imageUrl(resolveImageUrl("redmi9_2_2.jpg"))
                .imageUrl(resolveImageUrl("redmi9_2_3.jpg"))
                .imageUrl(resolveImageUrl("redmi9_3_0.jpg"))
                .imageUrl(resolveImageUrl("redmi9_3_1.jpg"))
                .imageUrl(resolveImageUrl("redmi9_3_2.jpg"))
                .imageUrl(resolveImageUrl("redmi9_3_3.jpg"))
                .imageUrl(resolveImageUrl("redmi9_4_0.jpg"))
                .imageUrl(resolveImageUrl("redmi9_4_1.jpg"))
                .imageUrl(resolveImageUrl("redmi9_4_2.jpg"))
                .imageUrl(resolveImageUrl("redmi9_4_3.jpg"))
                //
                .fixedShippingCost(10.00)
                //
                .origin(product -> product.createOrigin().toBuilder()
                        .provinceId("82").province("?????????")
                        .cityId("1354").city("?????????")
                        .countyId("10462").county("?????????")
                        .build())
                //
                .monthlySales(30)
                .totalSales(30)
                //
                // options
                .option(product -> product.createOption(PrimaryKeyHolder.next(PRODUCT_OPTION_ID_VALUE_NAME)).toBuilder()
                        .name("??????")
                        .value(option -> option.createValue(PrimaryKeyHolder.next(PRODUCT_OPTION_VALUE_ID_VALUE_NAME)).toBuilder().label("?????????").build())
                        .value(option -> option.createValue(PrimaryKeyHolder.next(PRODUCT_OPTION_VALUE_ID_VALUE_NAME)).toBuilder().label("?????????").build())
                        .value(option -> option.createValue(PrimaryKeyHolder.next(PRODUCT_OPTION_VALUE_ID_VALUE_NAME)).toBuilder().label("?????????").build())
                        .value(option -> option.createValue(PrimaryKeyHolder.next(PRODUCT_OPTION_VALUE_ID_VALUE_NAME)).toBuilder().label("?????????").build())
                        .build())
                .option(product -> product.createOption(PrimaryKeyHolder.next(PRODUCT_OPTION_ID_VALUE_NAME))
                        .toBuilder()
                        .name("??????")
                        .value(option -> option.createValue(PrimaryKeyHolder.next(PRODUCT_OPTION_VALUE_ID_VALUE_NAME)).toBuilder().label("4GB+128GB").build())
                        .value(option -> option.createValue(PrimaryKeyHolder.next(PRODUCT_OPTION_VALUE_ID_VALUE_NAME)).toBuilder().label("6GB+128GB").build())
                        .build())
                //
                .variant(product ->
                        product.createVariant(PrimaryKeyHolder.next(PRODUCT_VARIANT_ID_VALUE_NAME)).toBuilder().retailPrice(price).price(0.1).adjustInventoryQuantity(100)
                                .imageUrls(List.of(resolveImageUrl("redmi9_1_0.jpg"), resolveImageUrl("redmi9_1_1.jpg"),
                                        resolveImageUrl("redmi9_1_2.jpg"), resolveImageUrl("redmi9_1_3.jpg")))
                                .optionSelections(List.of(
                                        product.selectOption("??????", "?????????").orElseThrow(),
                                        product.selectOption("??????", "4GB+128GB").orElseThrow()))
                                .position(0).build())
                .variant(product ->
                        product.createVariant(PrimaryKeyHolder.next(PRODUCT_VARIANT_ID_VALUE_NAME)).toBuilder().retailPrice(price).price(0.3).adjustInventoryQuantity(100)
                                .imageUrls(List.of(resolveImageUrl("redmi9_1_0.jpg"), resolveImageUrl("redmi9_1_1.jpg"),
                                        resolveImageUrl("redmi9_1_2.jpg"), resolveImageUrl("redmi9_1_3.jpg")))
                                .optionSelections(List.of(
                                        product.selectOption("??????", "?????????").orElseThrow(),
                                        product.selectOption("??????", "6GB+128GB").orElseThrow()))
                                .position(1).build())
                //
                .variant(product ->
                        product.createVariant(PrimaryKeyHolder.next(PRODUCT_VARIANT_ID_VALUE_NAME)).toBuilder().retailPrice(price).price(0.1).adjustInventoryQuantity(100)
                                .imageUrls(List.of(resolveImageUrl("redmi9_2_0.jpg"), resolveImageUrl("redmi9_2_1.jpg"),
                                        resolveImageUrl("redmi9_2_2.jpg"), resolveImageUrl("redmi9_2_3.jpg")))
                                .optionSelections(List.of(
                                        product.selectOption("??????", "?????????").orElseThrow(),
                                        product.selectOption("??????", "4GB+128GB").orElseThrow()))
                                .position(0).build())
                .variant(product ->
                        product.createVariant(PrimaryKeyHolder.next(PRODUCT_VARIANT_ID_VALUE_NAME)).toBuilder().retailPrice(price).price(0.3).adjustInventoryQuantity(100)
                                .imageUrls(List.of(resolveImageUrl("redmi9_2_0.jpg"), resolveImageUrl("redmi9_2_1.jpg"),
                                        resolveImageUrl("redmi9_2_2.jpg"), resolveImageUrl("redmi9_2_3.jpg")))
                                .optionSelections(List.of(
                                        product.selectOption("??????", "?????????").orElseThrow(),
                                        product.selectOption("??????", "6GB+128GB").orElseThrow()))
                                .position(1).build())
                //
                .variant(product ->
                        product.createVariant(PrimaryKeyHolder.next(PRODUCT_VARIANT_ID_VALUE_NAME)).toBuilder().retailPrice(price).price(0.1).adjustInventoryQuantity(100)
                                .imageUrls(List.of(resolveImageUrl("redmi9_3_0.jpg"), resolveImageUrl("redmi9_3_1.jpg"),
                                        resolveImageUrl("redmi9_3_2.jpg"), resolveImageUrl("redmi9_3_3.jpg")))
                                .optionSelections(List.of(
                                        product.selectOption("??????", "?????????").orElseThrow(),
                                        product.selectOption("??????", "4GB+128GB").orElseThrow()))
                                .position(0).build())
                .variant(product ->
                        product.createVariant(PrimaryKeyHolder.next(PRODUCT_VARIANT_ID_VALUE_NAME)).toBuilder().retailPrice(price).price(0.3).adjustInventoryQuantity(100)
                                .imageUrls(List.of(resolveImageUrl("redmi9_3_0.jpg"), resolveImageUrl("redmi9_3_1.jpg"),
                                        resolveImageUrl("redmi9_3_2.jpg"), resolveImageUrl("redmi9_3_3.jpg")))
                                .optionSelections(List.of(
                                        product.selectOption("??????", "?????????").orElseThrow(),
                                        product.selectOption("??????", "6GB+128GB").orElseThrow()))
                                .position(1).build())
                //
                .variant(product ->
                        product.createVariant(PrimaryKeyHolder.next(PRODUCT_VARIANT_ID_VALUE_NAME)).toBuilder().retailPrice(price).price(0.1).adjustInventoryQuantity(100)
                                .imageUrls(List.of(resolveImageUrl("redmi9_4_0.jpg"), resolveImageUrl("redmi9_4_1.jpg"),
                                        resolveImageUrl("redmi9_4_2.jpg"), resolveImageUrl("redmi9_4_3.jpg")))
                                .optionSelections(List.of(
                                        product.selectOption("??????", "?????????").orElseThrow(),
                                        product.selectOption("??????", "4GB+128GB").orElseThrow()))
                                .position(0).build())
                .variant(product ->
                        product.createVariant(PrimaryKeyHolder.next(PRODUCT_VARIANT_ID_VALUE_NAME)).toBuilder().retailPrice(price).price(0.3).adjustInventoryQuantity(100)
                                .imageUrls(List.of(resolveImageUrl("redmi9_4_0.jpg"), resolveImageUrl("redmi9_4_1.jpg"),
                                        resolveImageUrl("redmi9_4_2.jpg"), resolveImageUrl("redmi9_4_3.jpg")))
                                .optionSelections(List.of(
                                        product.selectOption("??????", "?????????").orElseThrow(),
                                        product.selectOption("??????", "6GB+128GB").orElseThrow()))
                                .position(1).build())
                //attributes
                .attribute(product -> product.createAttribute("??????", "??????"))
                .attribute(product -> product.createAttribute("????????????", "2020???"))
                .create()
                .build();
    }

    private Product newProduct3() {
        double price = 0.01;
        return this.productService.createProduct(null)
                .toBuilder()
                .storeId("oppo")
                .name("OPPO Reno3 Pro ???????????????5G ??????????????? ??????765G 7.7mm??????????????????")
                .type(ProductType.PHYSICAL)
                .status(ProductStatus.ACTIVE)
                .categories(List.of("1"))
                .brandId("1")
                .imageUrl(resolveImageUrl("0ea203c122fb3dae.jpg"))
                .imageUrl(resolveImageUrl("7eac762ed4bcb66f.jpg"))
                .imageUrl(resolveImageUrl("b72e95f6953e2f3c.jpg"))
                .imageUrl(resolveImageUrl("c682d018d572c792.jpg"))
                .imageUrl(resolveImageUrl("8bf9144ad8c26840.jpg"))
                .imageUrl(resolveImageUrl("6f728a6562668d8f.jpg"))
                //
                .freeShipping()
                //
                .origin(product -> product.createOrigin().toBuilder()
                        .provinceId("80").province("?????????")
                        .cityId("1330").city("?????????")
                        .countyId("10241").county("?????????")
                        .build())
                //
                .monthlySales(40)
                .totalSales(40)
                // options
                .option(product -> product.createOption(PrimaryKeyHolder.next(PRODUCT_OPTION_ID_VALUE_NAME)).toBuilder()
                        .name("??????")
                        .value(option -> option.createValue(PrimaryKeyHolder.next(PRODUCT_OPTION_VALUE_ID_VALUE_NAME)).toBuilder().label("?????????").build())
                        .value(option -> option.createValue(PrimaryKeyHolder.next(PRODUCT_OPTION_VALUE_ID_VALUE_NAME)).toBuilder().label("????????????").build())
                        .build())

                .option(product -> product.createOption(PrimaryKeyHolder.next(PRODUCT_OPTION_ID_VALUE_NAME))
                        .toBuilder()
                        .name("??????")
                        .value(option -> option.createValue(PrimaryKeyHolder.next(PRODUCT_OPTION_VALUE_ID_VALUE_NAME)).toBuilder().label("8GB 128GB").build())
                        .value(option -> option.createValue(PrimaryKeyHolder.next(PRODUCT_OPTION_VALUE_ID_VALUE_NAME)).toBuilder().label("8GB 256GB").build())
                        .value(option -> option.createValue(PrimaryKeyHolder.next(PRODUCT_OPTION_VALUE_ID_VALUE_NAME)).toBuilder().label("12GB 256GB").build())
                        .build())
                //
                .variant(product ->
                        product.createVariant(PrimaryKeyHolder.next(PRODUCT_VARIANT_ID_VALUE_NAME)).toBuilder().retailPrice(price).price(price).adjustInventoryQuantity(100)
                                .imageUrls(List.of(resolveImageUrl("0ea203c122fb3dae.jpg"), resolveImageUrl("7eac762ed4bcb66f.jpg")))
                                .optionSelections(List.of(
                                        product.selectOption("??????", "?????????").orElseThrow(),
                                        product.selectOption("??????", "8GB 128GB").orElseThrow()))
                                .position(0).build())
                .variant(product ->
                        product.createVariant(PrimaryKeyHolder.next(PRODUCT_VARIANT_ID_VALUE_NAME)).toBuilder().retailPrice(price).price(price).adjustInventoryQuantity(100)
                                .imageUrls(List.of(resolveImageUrl("0ea203c122fb3dae.jpg"), resolveImageUrl("7eac762ed4bcb66f.jpg")))
                                .optionSelections(List.of(
                                        product.selectOption("??????", "?????????").orElseThrow(),
                                        product.selectOption("??????", "8GB 256GB").orElseThrow()))
                                .position(0).build())

                .variant(product ->
                        product.createVariant(PrimaryKeyHolder.next(PRODUCT_VARIANT_ID_VALUE_NAME)).toBuilder().retailPrice(price).price(price).adjustInventoryQuantity(100)
                                .imageUrls(List.of(resolveImageUrl("b72e95f6953e2f3c.jpg"), resolveImageUrl("c682d018d572c792.jpg")))
                                .optionSelections(List.of(
                                        product.selectOption("??????", "?????????").orElseThrow(),
                                        product.selectOption("??????", "12GB 256GB").orElseThrow()))
                                .position(1).build())

                .variant(product ->
                        product.createVariant(PrimaryKeyHolder.next(PRODUCT_VARIANT_ID_VALUE_NAME)).toBuilder().retailPrice(price).price(price).adjustInventoryQuantity(100)
                                .imageUrls(List.of(resolveImageUrl("b72e95f6953e2f3c.jpg"), resolveImageUrl("c682d018d572c792.jpg")))
                                .optionSelections(List.of(
                                        product.selectOption("??????", "????????????").orElseThrow(),
                                        product.selectOption("??????", "8GB 128GB").orElseThrow()))
                                .position(1).build())

                .variant(product ->
                        product.createVariant(PrimaryKeyHolder.next(PRODUCT_VARIANT_ID_VALUE_NAME)).toBuilder().retailPrice(price).price(price).adjustInventoryQuantity(100)
                                .imageUrls(List.of(resolveImageUrl("8bf9144ad8c26840.jpg"), resolveImageUrl("6f728a6562668d8f.jpg")))
                                .optionSelections(List.of(
                                        product.selectOption("??????", "????????????").orElseThrow(),
                                        product.selectOption("??????", "8GB 256GB").orElseThrow()))
                                .position(1).build())
                .variant(product ->
                        product.createVariant(PrimaryKeyHolder.next(PRODUCT_VARIANT_ID_VALUE_NAME)).toBuilder().retailPrice(price).price(price).adjustInventoryQuantity(100)
                                .imageUrls(List.of(resolveImageUrl("8bf9144ad8c26840.jpg"), resolveImageUrl("6f728a6562668d8f.jpg")))
                                .optionSelections(List.of(
                                        product.selectOption("??????", "????????????").orElseThrow(),
                                        product.selectOption("??????", "12GB 256GB").orElseThrow()))
                                .position(1).build())
                //attributes
                .attribute(product -> product.createAttribute("??????", "??????"))
                .attribute(product -> product.createAttribute("????????????", "2018-10-11"))
                .attribute(product -> product.createAttribute("??????", "???"))
                .create()
                .build();
    }

    private Product newProduct4() {
        double price = 0.01;
        return this.productService.createProduct(null)
                .toBuilder()
                .storeId("vivo")
                .name("vivo NEX3 ??????????????? ????????????855Plus 6400?????????5G???????????????")
                .type(ProductType.PHYSICAL)
                .status(ProductStatus.ACTIVE)
                .categories(List.of("1"))
                .brandId("1")
                //
                .imageUrl(resolveImageUrl("3c5048ac3b93dcca.png"))
                .imageUrl(resolveImageUrl("3c5048ac3b93dccc.jpg"))
                .imageUrl(resolveImageUrl("ec48ee3a1e78a5ce.jpg"))
                .imageUrl(resolveImageUrl("178e05db88b4477e.jpg"))
                //
                .fixedShippingCost(30)
                //
                .origin(product -> product.createOrigin().toBuilder()
                        .provinceId("90").province("?????????")
                        .cityId("1428").city("?????????")
                        .countyId("11202").county("?????????")
                        .build())
                //
                .monthlySales(50)
                .totalSales(50)
                // options
                .option(product -> product.createOption(PrimaryKeyHolder.next(PRODUCT_OPTION_ID_VALUE_NAME)).toBuilder()
                        .name("??????")
                        .value(option -> option.createValue(PrimaryKeyHolder.next(PRODUCT_OPTION_VALUE_ID_VALUE_NAME)).toBuilder().label("????????????").build())
                        .value(option -> option.createValue(PrimaryKeyHolder.next(PRODUCT_OPTION_VALUE_ID_VALUE_NAME)).toBuilder().label("????????????").build())
                        .build())

                .option(product -> product.createOption(PrimaryKeyHolder.next(PRODUCT_OPTION_ID_VALUE_NAME))
                        .toBuilder()
                        .name("??????")
                        .value(option -> option.createValue(PrimaryKeyHolder.next(PRODUCT_OPTION_VALUE_ID_VALUE_NAME)).toBuilder().label("8GB 256GB").build())
                        .value(option -> option.createValue(PrimaryKeyHolder.next(PRODUCT_OPTION_VALUE_ID_VALUE_NAME)).toBuilder().label("12GB 256GB").build())
                        .build())
                //
                .variant(product ->
                        product.createVariant(PrimaryKeyHolder.next(PRODUCT_VARIANT_ID_VALUE_NAME)).toBuilder().retailPrice(price).price(price).adjustInventoryQuantity(100)
                                .imageUrls(List.of(resolveImageUrl("3c5048ac3b93dcca.png"), resolveImageUrl("3c5048ac3b93dccc.jpg")))
                                .optionSelections(List.of(
                                        product.selectOption("??????", "????????????").orElseThrow(),
                                        product.selectOption("??????", "8GB 256GB").orElseThrow()))
                                .position(0).build())
                .variant(product ->
                        product.createVariant(PrimaryKeyHolder.next(PRODUCT_VARIANT_ID_VALUE_NAME)).toBuilder().retailPrice(price).price(price).adjustInventoryQuantity(100)
                                .imageUrls(List.of(resolveImageUrl("3c5048ac3b93dcca.png"), resolveImageUrl("3c5048ac3b93dccc.jpg")))
                                .optionSelections(List.of(
                                        product.selectOption("??????", "????????????").orElseThrow(),
                                        product.selectOption("??????", "12GB 256GB").orElseThrow()))
                                .position(0).build())

                .variant(product ->
                        product.createVariant(PrimaryKeyHolder.next(PRODUCT_VARIANT_ID_VALUE_NAME)).toBuilder().retailPrice(price).price(price).adjustInventoryQuantity(100)
                                .imageUrls(List.of(resolveImageUrl("ec48ee3a1e78a5ce.jpg"), resolveImageUrl("178e05db88b4477e.jpg")))
                                .optionSelections(List.of(
                                        product.selectOption("??????", "????????????").orElseThrow(),
                                        product.selectOption("??????", "8GB 256GB").orElseThrow()))
                                .position(1).build())
                .variant(product ->
                        product.createVariant(PrimaryKeyHolder.next(PRODUCT_VARIANT_ID_VALUE_NAME)).toBuilder().retailPrice(price).price(price).adjustInventoryQuantity(100)
                                .imageUrls(List.of(resolveImageUrl("ec48ee3a1e78a5ce.jpg"), resolveImageUrl("178e05db88b4477e.jpg")))
                                .optionSelections(List.of(
                                        product.selectOption("??????", "????????????").orElseThrow(),
                                        product.selectOption("??????", "12GB 256GB").orElseThrow()))
                                .position(1).build())
                //attributes
                .attribute(product -> product.createAttribute("??????", "??????"))
                .attribute(product -> product.createAttribute("????????????", "2018-10-11"))
                .attribute(product -> product.createAttribute("??????", "???"))
                .create()
                .build();
    }

    private Product newProduct5() {
        double price = 0.01;
        return this.productService.createProduct(null)
                .toBuilder()
                .storeId("one plus")
                .name("?????? OnePlus 7 Pro 2K+90Hz ????????? ??????855?????? 4800????????????????????????")
                .type(ProductType.PHYSICAL)
                .status(ProductStatus.ACTIVE)
                .categories(List.of("1"))
                .brandId("1")
                //
                .imageUrl(resolveImageUrl("47fdb0779e7dad8a.jpg"))
                .imageUrl(resolveImageUrl("5cdd0ce2N5852750d.jpg"))
                .imageUrl(resolveImageUrl("0d0601b02dbb38e9.jpg"))
                .imageUrl(resolveImageUrl("5cdd0d93N3d7e0776.jpg"))
                .imageUrl(resolveImageUrl("a95c82b7c278fe1a.jpg"))
                .imageUrl(resolveImageUrl("3e4ee6b91564649d.jpg"))
                //
                .freeShipping()
                //
                .origin(product -> product.createOrigin().toBuilder()
                        .provinceId("79").province("?????????")
                        .cityId("1314").city("?????????")
                        .countyId("10119").county("?????????")
                        .build())
                //
                .monthlySales(60)
                .totalSales(60)
                // options
                .option(product -> product.createOption(PrimaryKeyHolder.next(PRODUCT_OPTION_ID_VALUE_NAME)).toBuilder()
                        .name("??????")
                        .value(option -> option.createValue(PrimaryKeyHolder.next(PRODUCT_OPTION_VALUE_ID_VALUE_NAME)).toBuilder().label("?????????").build())
                        .value(option -> option.createValue(PrimaryKeyHolder.next(PRODUCT_OPTION_VALUE_ID_VALUE_NAME)).toBuilder().label("?????????").build())
                        .value(option -> option.createValue(PrimaryKeyHolder.next(PRODUCT_OPTION_VALUE_ID_VALUE_NAME)).toBuilder().label("?????????").build())
                        .build())

                .option(product -> product.createOption(PrimaryKeyHolder.next(PRODUCT_OPTION_ID_VALUE_NAME))
                        .toBuilder()
                        .name("??????")
                        .value(option -> option.createValue(PrimaryKeyHolder.next(PRODUCT_OPTION_VALUE_ID_VALUE_NAME)).toBuilder().label("6GB 128GB").build())
                        .value(option -> option.createValue(PrimaryKeyHolder.next(PRODUCT_OPTION_VALUE_ID_VALUE_NAME)).toBuilder().label("8GB 256GB").build())
                        .value(option -> option.createValue(PrimaryKeyHolder.next(PRODUCT_OPTION_VALUE_ID_VALUE_NAME)).toBuilder().label("12GB 256GB").build())
                        .build())
                //

                .variant(product -> product.createVariant(PrimaryKeyHolder.next(PRODUCT_VARIANT_ID_VALUE_NAME))
                        .toBuilder().retailPrice(price).price(price).adjustInventoryQuantity(100)
                        .imageUrls(List.of(resolveImageUrl("47fdb0779e7dad8a.jpg"), resolveImageUrl("5cdd0ce2N5852750d.jpg")))
                        .optionSelections(List.of(
                                product.selectOption("??????", "?????????").orElseThrow(),
                                product.selectOption("??????", "6GB 128GB").orElseThrow()))
                        .position(0).build())
                .variant(product ->
                        product.createVariant(PrimaryKeyHolder.next(PRODUCT_VARIANT_ID_VALUE_NAME)).toBuilder().retailPrice(price).price(price).adjustInventoryQuantity(100)
                                .imageUrls(List.of(resolveImageUrl("47fdb0779e7dad8a.jpg"), resolveImageUrl("5cdd0ce2N5852750d.jpg")))
                                .optionSelections(List.of(
                                        product.selectOption("??????", "?????????").orElseThrow(),
                                        product.selectOption("??????", "8GB 256GB").orElseThrow()))
                                .position(0).build())
                .variant(product ->
                        product.createVariant(PrimaryKeyHolder.next(PRODUCT_VARIANT_ID_VALUE_NAME)).toBuilder().retailPrice(price).price(price).adjustInventoryQuantity(100)
                                .imageUrls(List.of(resolveImageUrl("47fdb0779e7dad8a.jpg"), resolveImageUrl("5cdd0ce2N5852750d.jpg")))
                                .optionSelections(List.of(
                                        product.selectOption("??????", "?????????").orElseThrow(),
                                        product.selectOption("??????", "12GB 256GB").orElseThrow()))
                                .position(0).build())

                .variant(product ->
                        product.createVariant(PrimaryKeyHolder.next(PRODUCT_VARIANT_ID_VALUE_NAME)).toBuilder().retailPrice(price).price(price).adjustInventoryQuantity(100)
                                .imageUrls(List.of(resolveImageUrl("0d0601b02dbb38e9.jpg"), resolveImageUrl("5cdd0d93N3d7e0776.jpg")))
                                .optionSelections(List.of(
                                        product.selectOption("??????", "?????????").orElseThrow(),
                                        product.selectOption("??????", "6GB 128GB").orElseThrow()))
                                .position(0).build())
                .variant(product ->
                        product.createVariant(PrimaryKeyHolder.next(PRODUCT_VARIANT_ID_VALUE_NAME)).toBuilder().retailPrice(price).price(price).adjustInventoryQuantity(100)
                                .imageUrls(List.of(resolveImageUrl("0d0601b02dbb38e9.jpg"), resolveImageUrl("5cdd0d93N3d7e0776.jpg")))
                                .optionSelections(List.of(
                                        product.selectOption("??????", "?????????").orElseThrow(),
                                        product.selectOption("??????", "8GB 256GB").orElseThrow()))
                                .position(0).build())
                .variant(product ->
                        product.createVariant(PrimaryKeyHolder.next(PRODUCT_VARIANT_ID_VALUE_NAME)).toBuilder().retailPrice(price).price(price).adjustInventoryQuantity(100)
                                .imageUrls(List.of(resolveImageUrl("0d0601b02dbb38e9.jpg"), resolveImageUrl("5cdd0d93N3d7e0776.jpg")))
                                .optionSelections(List.of(
                                        product.selectOption("??????", "?????????").orElseThrow(),
                                        product.selectOption("??????", "12GB 256GB").orElseThrow()))
                                .position(0).build())

                .variant(product ->
                        product.createVariant(PrimaryKeyHolder.next(PRODUCT_VARIANT_ID_VALUE_NAME)).toBuilder().retailPrice(price).price(price).adjustInventoryQuantity(100)
                                .imageUrls(List.of(resolveImageUrl("a95c82b7c278fe1a.jpg"), resolveImageUrl("3e4ee6b91564649d.jpg")))
                                .optionSelections(List.of(
                                        product.selectOption("??????", "?????????").orElseThrow(),
                                        product.selectOption("??????", "6GB 128GB").orElseThrow()))
                                .position(0).build())
                .variant(product ->
                        product.createVariant(PrimaryKeyHolder.next(PRODUCT_VARIANT_ID_VALUE_NAME)).toBuilder().retailPrice(price).price(price).adjustInventoryQuantity(100)
                                .imageUrls(List.of(resolveImageUrl("a95c82b7c278fe1a.jpg"), resolveImageUrl("3e4ee6b91564649d.jpg")))
                                .optionSelections(List.of(
                                        product.selectOption("??????", "?????????").orElseThrow(),
                                        product.selectOption("??????", "8GB 256GB").orElseThrow()))
                                .position(0).build())
                .variant(product ->
                        product.createVariant(PrimaryKeyHolder.next(PRODUCT_VARIANT_ID_VALUE_NAME)).toBuilder().retailPrice(price).price(price).adjustInventoryQuantity(100)
                                .imageUrls(List.of(resolveImageUrl("a95c82b7c278fe1a.jpg"), resolveImageUrl("3e4ee6b91564649d.jpg")))
                                .optionSelections(List.of(
                                        product.selectOption("??????", "?????????").orElseThrow(),
                                        product.selectOption("??????", "12GB 256GB").orElseThrow()))
                                .position(0).build())
                //attributes
                .attribute(product -> product.createAttribute("??????", "??????"))
                .attribute(product -> product.createAttribute("????????????", "2018-10-11"))
                .attribute(product -> product.createAttribute("??????", "???"))
                .create()
                .build();
    }

}
