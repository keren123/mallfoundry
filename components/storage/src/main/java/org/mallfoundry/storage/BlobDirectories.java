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

package org.mallfoundry.storage;

import org.apache.commons.io.FilenameUtils;

import java.util.Objects;

public abstract class BlobDirectories {

    public static Blob getParent(Blob blob) {
        if (Objects.nonNull(blob.getParent())) {
            return blob.getParent();
        }
        if (PathUtils.isRootPath(blob.getPath())) {
            return null;
        }
        String path =
                blob.getPath().endsWith(PathUtils.PATH_SEPARATOR)
                        ? FilenameUtils.getFullPathNoEndSeparator(blob.getPath())
                        : blob.getPath();

        String parentPath = FilenameUtils.getFullPathNoEndSeparator(path);
        if (PathUtils.isRootPath(parentPath)) {
            return null;
        }
        var parent = new InternalBlob();
        parent.setBlobId(new InternalBlobId(blob.getBucket(), parentPath));
        parent.createDirectory();
        return parent;
    }

}
