/*
 * Copyright 2020 the original author or authors.
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

package org.mallfoundry.data.jpa.convert;

import org.mallfoundry.util.JsonUtils;

import javax.persistence.AttributeConverter;
import java.util.Map;
import java.util.Objects;

public class StringStringMapConverter implements AttributeConverter<Map<String, String>, String> {
    @Override
    public String convertToDatabaseColumn(Map<String, String> attribute) {
        return Objects.isNull(attribute) ? null : JsonUtils.stringify(attribute);
    }

    @Override
    public Map<String, String> convertToEntityAttribute(String dbData) {
        return Objects.isNull(dbData) ? null : JsonUtils.parse(dbData, Map.class, String.class, String.class);
    }
}