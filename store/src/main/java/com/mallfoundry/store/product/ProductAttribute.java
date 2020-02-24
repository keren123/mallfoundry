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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class ProductAttribute implements Serializable {

    @Column(name = "namespace_")
    private String namespace;

    @Column(name = "name_")
    private String name;

    @Column(name = "value_")
    private String value;


    @Column(name = "position_")
    private Integer position;

    public ProductAttribute(String name, String value, Integer position) {
        this.name = name;
        this.value = value;
        this.position = position;
    }

    public ProductAttribute(String namespace, String name, String value, Integer position) {
        this.namespace = namespace;
        this.name = name;
        this.value = value;
        this.position = position;
    }
}
