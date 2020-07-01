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

package org.mallfoundry.identity.repository.jpa;

import org.mallfoundry.identity.User;
import org.mallfoundry.identity.UserRepository;
import org.springframework.data.util.CastUtils;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaUserRepository implements UserRepository {

    private final JpaUserRepositoryDelegate repository;

    public JpaUserRepository(JpaUserRepositoryDelegate repository) {
        this.repository = repository;
    }

    @Override
    public User create(String id) {
        return new JpaUser(id);
    }

    @Override
    public User save(User user) {
        return this.repository.save(JpaUser.of(user));
    }

    @Override
    public void delete(User user) {
        this.repository.delete(JpaUser.of(user));
    }

    @Override
    public Optional<User> findById(String id) {
        return CastUtils.cast(this.repository.findById(id));
    }

    @Override
    public Optional<User> findByMobile(String mobile) {
        return CastUtils.cast(this.repository.findByMobile(mobile));
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return CastUtils.cast(this.repository.findByUsername(username));
    }
}
