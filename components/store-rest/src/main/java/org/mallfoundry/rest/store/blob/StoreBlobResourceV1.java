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

package org.mallfoundry.rest.store.blob;

import org.apache.commons.lang3.StringUtils;
import org.mallfoundry.data.SliceList;
import org.mallfoundry.storage.Blob;
import org.mallfoundry.storage.BlobType;
import org.mallfoundry.store.StoreId;
import org.mallfoundry.store.StoreService;
import org.mallfoundry.store.blob.StoreBlobService;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@RequestMapping("/v1/stores")
@RestController
public class StoreBlobResourceV1 {

    private final AntPathMatcher blobPathMatcher = new AntPathMatcher();

    private final StoreService storeService;

    private final StoreBlobService storeBlobService;

    public StoreBlobResourceV1(StoreService storeService, StoreBlobService storeBlobService) {
        this.storeService = storeService;
        this.storeBlobService = storeBlobService;
    }

    private StoreId createStoreId(String storeId) {
        return this.storeService.createStoreId(storeId);
    }

    @PostMapping("/{store_id}/blobs/**")
    public Blob storeStoreBlob(@PathVariable("store_id") String storeId,
                          @RequestParam(required = false) String name,
                          @RequestParam(name = "file", required = false) MultipartFile file,
                          HttpServletRequest request) throws IOException {
        var bucket = this.storeBlobService.getStoreBucket(this.createStoreId(storeId));
        Blob storeBlob;
        if (Objects.nonNull(file)) {
            String blobPath = extractStoreBlobPath(request, file.getOriginalFilename());
            storeBlob = bucket.createBlob(blobPath, file.getInputStream());
            storeBlob.rename(StringUtils.isEmpty(name) ? file.getOriginalFilename() : name);
        } else {
            storeBlob = bucket.createBlob(extractStoreBlobPath(request, name));
            if (StringUtils.isNotBlank(name)) {
                storeBlob.rename(name);
            }
        }
        return this.storeBlobService.storeStoreBlob(storeBlob);
    }


    @DeleteMapping("/{store_id}/blobs/**")
    public void deleteStoreBlob(@PathVariable("store_id") String storeId,
                           HttpServletRequest request) {
        this.storeBlobService.deleteStoreBlob(this.createStoreId(storeId), extractStoreBlobPath(request, null));
    }

    @DeleteMapping("/{store_id}/blobs/batch")
    public void deleteStoreBlobs(@PathVariable("store_id") String storeId,
                            @RequestBody List<String> paths) {
        this.storeBlobService.deleteStoreBlobs(this.createStoreId(storeId), paths);
    }

    @GetMapping("/{store_id}/blobs")
    public SliceList<Blob> getStoreBlobs(@PathVariable("store_id") String storeId,
                                    @RequestParam(name = "page", defaultValue = "1") Integer page,
                                    @RequestParam(name = "limit", defaultValue = "20") Integer limit,
                                    String type, String path) {
        var typeUpper = StringUtils.upperCase(type);
        return this.storeBlobService.getStoreBlobs(
                this.storeBlobService.createStoreBlobQuery().toBuilder()
                        .page(page).limit(limit)
                        .type(StringUtils.isEmpty(typeUpper) ? null : BlobType.valueOf(typeUpper))
                        .storeId(storeId)
                        .path(path).build());
    }

    private String extractStoreBlobPath(HttpServletRequest request, String defaultPath) {
        String path = request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString();
        String bestMatchingPattern = request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE).toString();
        String blobPath = this.blobPathMatcher.extractPathWithinPattern(bestMatchingPattern, path);
        return StringUtils.isNotEmpty(blobPath)
                ? blobPath
                : defaultPath;
    }
}
