/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.stratos.messaging.domain.topology;

import java.io.Serializable;
import java.util.Set;

public class DependencyOrder implements Serializable {

	private static final long serialVersionUID = -599600831844477527L;

	private Set<StartupOrder> startupOrders;

    private String terminationBehaviour;

    public DependencyOrder () {

    }

    public String getTerminationBehaviour() {
        return terminationBehaviour;
    }

    public void setTerminationBehaviour(String terminationBehaviour) {
        this.terminationBehaviour = terminationBehaviour;
    }

	public Set<StartupOrder> getStartupOrders() {
		return startupOrders;
	}

	public void setStartupOrders(Set<StartupOrder> startupOrders) {
		this.startupOrders = startupOrders;
	}
}
