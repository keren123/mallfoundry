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

package org.mallfoundry.store.customer;

import org.mallfoundry.identity.Gender;

import java.util.Date;
import java.util.List;

public interface StoreCustomer {

    String getId();

    String getStoreId();

    String getCountryCode();

    void setCountryCode(String countryCode);

    String getMobile();

    void setMobile(String mobile);

    Gender getGender();

    void setGender(Gender gender);

    Date getBirthdate();

    void setBirthdate(Date birthdate);

    List<String> getTags();

    void setTags(List<String> tags);

    String getNotes();

    void setNotes(String notes);
}
