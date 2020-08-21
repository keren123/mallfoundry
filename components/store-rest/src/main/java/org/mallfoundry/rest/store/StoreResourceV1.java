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

package org.mallfoundry.rest.store;

import org.mallfoundry.data.SliceList;
import org.mallfoundry.store.Store;
import org.mallfoundry.store.StoreId;
import org.mallfoundry.store.StoreInitializing;
import org.mallfoundry.store.StoreService;
import org.mallfoundry.store.staff.StaffStore;
import org.mallfoundry.store.staff.StaffStoreService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/v1")
public class StoreResourceV1 {

    private final StoreService storeService;

    private final StaffStoreService staffStoreService;

    public StoreResourceV1(StoreService storeService, StaffStoreService staffStoreService) {
        this.storeService = storeService;
        this.staffStoreService = staffStoreService;
    }

    private StoreId createStoreId(String id) {
        return this.storeService.createStoreId(id);
    }

    @PostMapping("/stores")
    public Store createStore(@RequestBody StoreRequest request) {
        return this.storeService.createStore(
                request.assignToStore(
                        this.storeService.createStore(
                                this.createStoreId(request.getId()))));
    }

    @GetMapping("/staffs/{staff_id}/stores")
    public SliceList<StaffStore> getStaffStores(@RequestParam(name = "page", defaultValue = "1") Integer page,
                                                @RequestParam(name = "limit", defaultValue = "20") Integer limit,
                                                @PathVariable("staff_id") String staffId) {
        return this.staffStoreService.getStaffStores(
                this.staffStoreService.createStaffStoreQuery()
                        .toBuilder().page(page).limit(limit).staffIds(Set.of(staffId)).build());
    }

    @GetMapping("/stores/{id}")
    public Optional<Store> getStore(@PathVariable("id") String id) {
        return this.storeService.findStore(this.createStoreId(id));
    }


    @PostMapping("/stores/{id}/initialize")
    public StoreInitializing initializeStore(@PathVariable("id") String id) {
        return this.storeService.initializeStore(this.createStoreId(id));
    }

    @GetMapping("/stores/{id}/initializing")
    public Optional<StoreInitializing> getStoreInitializing(@PathVariable("id") String id) {
        return this.storeService.getStoreInitializing(this.createStoreId(id));
    }

    @GetMapping("/stores/{id}/exists")
    public boolean existsStore(@PathVariable("id") String id) {
        return this.storeService.existsStore(this.createStoreId(id));
    }

    @GetMapping("/stores")
    public SliceList<Store> getStores(@RequestParam(name = "page", defaultValue = "1") Integer page,
                                      @RequestParam(name = "limit", defaultValue = "20") Integer limit,
                                      @RequestParam(name = "owner_id", required = false) String ownerId,
                                      @RequestParam(name = "staff_id", required = false) String staffId) {
        return this.storeService.getStores(
                this.storeService.createStoreQuery().toBuilder()
                        .page(page).limit(limit)
                        .ownerId(ownerId).staffId(staffId).build());
    }

    @PatchMapping("/stores/{store_id}")
    public Store updateStore(@PathVariable("store_id") String id,
                             @RequestBody StoreRequest request) {
        return this.storeService.updateStore(
                request.assignToStore(
                        this.storeService.createStore(this.createStoreId(id))));
    }

    @DeleteMapping("/stores/{store_id}")
    public void cancelStore(@PathVariable("store_id") String id) {
        this.storeService.cancelStore(this.createStoreId(id));
    }

}
