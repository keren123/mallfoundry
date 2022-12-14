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

package org.mallfoundry.rest.catalog;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.StringUtils;
import org.mallfoundry.catalog.CategoryService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Categories")
@RestController
@RequestMapping("/v1")
public class CategoryResourceV1 {

    private final CategoryService categoryService;

    private final CategoryRestFactory categoryRestFactory;

    public CategoryResourceV1(CategoryService categoryService, CategoryRestFactory categoryRestFactory) {
        this.categoryService = categoryService;
        this.categoryRestFactory = categoryRestFactory;
    }

    @PostMapping("/categories")
    public CategoryResponse createCategory(@RequestBody CategoryRequest request) {
        if (StringUtils.isBlank(request.getParentId())) {
            return new CategoryResponse(
                    this.categoryService.addCategory(request.assignToCategory(this.categoryService.createCategory(null))));
        } else {
            return new CategoryResponse(
                    this.categoryService.addCategory(request.getParentId(),
                            request.assignToCategory(this.categoryService.createCategory(null))));
        }
    }

    @GetMapping("/categories")
    public List<CategoryResponse> getCategories(@Parameter(description = "????????????")
                                                @RequestParam(defaultValue = "0", required = false) byte level,
                                                @RequestParam(name = "parent_id", required = false) String parentId) {
        return this.categoryRestFactory.getCategories(
                this.categoryService.createCategoryQuery().toBuilder().level(level).parentId(parentId).build());
    }

    @PatchMapping("/categories/{category_id}")
    public void updateCategory(@PathVariable("category_id") String categoryId, @RequestBody CategoryRequest request) {
        var category = this.categoryService.createCategory(categoryId);
        this.categoryService.updateCategory(request.assignToCategory(category));
    }

    @DeleteMapping("/categories/{category_id}")
    public void deleteCategories(@PathVariable("category_id") String categoryId) {
        this.categoryService.deleteCategory(categoryId);
    }
}
