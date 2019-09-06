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

package com.github.shop.fs;

import lombok.Getter;
import org.apache.commons.io.IOUtils;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileResource implements Closeable {

    @Getter
    private String filename;

    @Getter
    private InputStream inputStream;

    public FileResource(String filename, InputStream inputStream) {
        this.filename = filename;
        this.inputStream = inputStream;
    }

    public FileResource(File file) throws IOException {
        this.filename = file.getName();
        this.inputStream = IOUtils.buffer(new FileInputStream(file));
    }

    @Override
    public void close() throws IOException {
        // close input stream.
        if (this.inputStream != null) {
            this.inputStream.close();
        }
    }
}
