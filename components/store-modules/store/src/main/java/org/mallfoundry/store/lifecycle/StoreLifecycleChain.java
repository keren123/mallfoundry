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

package org.mallfoundry.store.lifecycle;

import org.mallfoundry.store.Store;
import org.mallfoundry.store.StoreLifecycle;
import org.mallfoundry.util.Positions;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class StoreLifecycleChain implements StoreLifecycle {

    private final List<StoreLifecycle> lifecycles;

    private final List<StoreLifecycle> reverseLifecycles;

    public StoreLifecycleChain(List<StoreLifecycle> lifecycles) {
        this.lifecycles = new LinkedList<>(lifecycles);
        Positions.sort(this.lifecycles);
        this.reverseLifecycles = new LinkedList<>(this.lifecycles);
        Collections.reverse(this.reverseLifecycles);
    }

    @Override
    public void doInitialize(Store store) {
        this.lifecycles.forEach(lifecycle -> lifecycle.doInitialize(store));
    }

    @Override
    public void doClose(Store store) {
        this.reverseLifecycles.forEach(lifecycle -> lifecycle.doClose(store));
    }

    @Override
    public int getPosition() {
        return 0;
    }
}
