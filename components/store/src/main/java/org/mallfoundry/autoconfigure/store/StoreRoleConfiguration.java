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

package org.mallfoundry.autoconfigure.store;

import org.mallfoundry.store.role.DefaultRoleService;
import org.mallfoundry.store.role.SmartStoreRoleValidatedProcessor;
import org.mallfoundry.store.role.RoleAuthorizeProcessor;
import org.mallfoundry.store.role.RoleIdentityProcessor;
import org.mallfoundry.store.role.RoleProcessor;
import org.mallfoundry.store.role.RoleRepository;
import org.mallfoundry.store.role.repository.jpa.JpaRoleRepository;
import org.mallfoundry.store.role.repository.jpa.JpaRoleRepositoryDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.SmartValidator;

import java.util.List;

@Configuration
public class StoreRoleConfiguration {

    @Bean
    public JpaRoleRepository jpaStoreRoleRepository(JpaRoleRepositoryDelegate roleRepositoryDelegate) {
        return new JpaRoleRepository(roleRepositoryDelegate);
    }


    @Bean
    @Autowired(required = false)
    public DefaultRoleService defaultStoreRoleService(@Lazy List<RoleProcessor> processors,
                                                      RoleRepository repository) {
        var service = new DefaultRoleService(repository);
        service.setProcessors(processors);
        return service;
    }

    @Bean
    public SmartStoreRoleValidatedProcessor smartStoreRoleValidatedProcessor(SmartValidator validator) {
        return new SmartStoreRoleValidatedProcessor(validator);
    }

    @Bean
    public RoleIdentityProcessor storeRoleIdentityProcessor() {
        return new RoleIdentityProcessor();
    }

    @Bean
    public RoleAuthorizeProcessor storeRoleAuthorizeProcessor() {
        return new RoleAuthorizeProcessor();
    }

}
