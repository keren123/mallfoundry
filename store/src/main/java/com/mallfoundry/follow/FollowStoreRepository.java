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

package com.mallfoundry.follow;

import com.mallfoundry.data.SliceList;

import java.util.Optional;

public interface FollowStoreRepository {

    Optional<InternalFollowStore> findById(InternalFollowStoreId storeId);

    InternalFollowStore save(InternalFollowStore followStore);

    boolean existsById(InternalFollowStoreId storeId);

    void delete(InternalFollowStore followStore);

    SliceList<InternalFollowStore> findAll(FollowStoreQuery query);

    long count(FollowStoreQuery query);

    long countById(String id);
}
