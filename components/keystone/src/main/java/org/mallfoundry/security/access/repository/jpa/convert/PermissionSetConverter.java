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

package org.mallfoundry.security.access.repository.jpa.convert;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class PermissionSetConverter implements AttributeConverter<Set<String>, String> {

    @Override
    public String convertToDatabaseColumn(Set<String> attribute) {
        return CollectionUtils.isEmpty(attribute) ? null
                : StringUtils.collectionToCommaDelimitedString(attribute);
    }

    @Override
    public Set<String> convertToEntityAttribute(String dbData) {
        return Objects.isNull(dbData) ? new HashSet<>() : StringUtils.commaDelimitedListToSet(dbData);
    }
}
