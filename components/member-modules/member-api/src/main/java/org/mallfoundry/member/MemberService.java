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

package org.mallfoundry.member;

import org.mallfoundry.data.SliceList;
import org.mallfoundry.store.StoreId;

import java.util.Optional;

public interface MemberService {

    MemberQuery createMemberQuery();

    MemberId createMemberId(StoreId storeId, String memberId);

    MemberId createMemberId(String storeId, String memberId);

    Member createMember(MemberId memberId);

    Member joinMember(Member member);

    Optional<Member> findMember(MemberId id);

    SliceList<Member> getMembers(MemberQuery query);

    Member updateMember(Member member);

    void deleteMember(MemberId id);

    void clearMembers(StoreId id);
}
