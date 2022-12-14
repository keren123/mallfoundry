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

package org.mallfoundry.config;

import org.mallfoundry.store.ImmutableStoreId;
import org.mallfoundry.store.Store;
import org.mallfoundry.store.StoreId;
import org.mallfoundry.store.security.Role;
import org.mallfoundry.store.security.RoleId;
import org.mallfoundry.util.ObjectType;

public class StoreConfigurationIdRetrievalStrategy implements ConfigurationIdRetrievalStrategy {

    @Override
    public String getConfigurationId(Object entity) {
        if (entity instanceof StoreId) {
            return this.getConfigurationId((StoreId) entity);
        } else if (entity instanceof Store) {
            var store = (Store) entity;
            return this.getConfigurationId(store.toId());
        } else if (entity instanceof Role) {
            var role = (Role) entity;
            return this.getConfigurationId(role.toId());
        } else if (entity instanceof RoleId) {
            var roleId = (RoleId) entity;
            return this.getConfigurationId(new ImmutableStoreId(roleId.getTenantId(), roleId.getStoreId()));
        }
        return null;
    }

    private String getConfigurationId(StoreId storeId) {
        return ObjectType.STORE.code() + storeId.getId();
    }
}
