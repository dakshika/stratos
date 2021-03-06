/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.stratos.messaging.event.instance.status;

import java.io.Serializable;

public class InstanceMaintenanceModeEvent extends InstanceStatusEvent implements Serializable {
    private final String serviceName;
        private final String clusterId;
        private final String networkPartitionId;
        private final String partitionId;
        private final String memberId;
        private String groupId;

        public InstanceMaintenanceModeEvent(String serviceName, String clusterId,
                                            String networkPartitionId, String partitionId, String memberId) {
            this.serviceName = serviceName;
            this.clusterId = clusterId;
            this.networkPartitionId = networkPartitionId;
            this.partitionId = partitionId;
            this.memberId = memberId;
        }

        public String getServiceName() {
            return serviceName;
        }

        public String getClusterId() {
            return clusterId;
        }

        public String getPartitionId() {
            return partitionId;
        }

        public String getMemberId() {
            return memberId;
        }

        public String getNetworkPartitionId() {
            return networkPartitionId;
        }

		public String getGroupId() {
			return groupId;
		}

		public void setGroupId(String groupId) {
			this.groupId = groupId;
		}

}
