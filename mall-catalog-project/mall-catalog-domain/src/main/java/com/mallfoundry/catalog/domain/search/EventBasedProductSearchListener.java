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

package com.mallfoundry.catalog.domain.search;

import com.mallfoundry.catalog.domain.product.ProductCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class EventBasedProductSearchListener {

    private final ProductSearchService productSearchService;

    public EventBasedProductSearchListener(ProductSearchService productSearchService) {
        this.productSearchService = productSearchService;
    }

    @EventListener(ProductCreatedEvent.class)
    public void onCreatedProduct(ProductCreatedEvent event) {
        this.productSearchService.add(event.getProduct());
    }
}
