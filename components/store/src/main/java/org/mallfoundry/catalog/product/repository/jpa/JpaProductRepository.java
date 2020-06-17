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

package org.mallfoundry.catalog.product.repository.jpa;

import org.mallfoundry.catalog.product.JdbcProductRepository;
import org.mallfoundry.catalog.product.Product;
import org.mallfoundry.catalog.product.ProductQuery;
import org.mallfoundry.data.SliceList;
import org.springframework.data.util.CastUtils;

import java.util.Optional;

public class JpaProductRepository implements JdbcProductRepository {

    private final JpaProductRepositoryDelegate productRepository;

    public JpaProductRepository(JpaProductRepositoryDelegate productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product create(String id) {
        return new JpaProduct(id);
    }

    @Override
    public Product save(Product product) {
        return this.productRepository.save(JpaProduct.of(product));
    }

    @Override
    public Optional<Product> findById(String id) {
        return CastUtils.cast(this.productRepository.findById(id));
    }

    @Override
    public SliceList<Product> findAll(ProductQuery query) {
        return SliceList.emptyList();
    }
}
