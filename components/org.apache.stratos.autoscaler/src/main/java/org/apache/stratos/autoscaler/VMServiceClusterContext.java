/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.stratos.autoscaler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.stratos.autoscaler.policy.model.AutoscalePolicy;
import org.apache.stratos.autoscaler.policy.model.DeploymentPolicy;
import org.apache.stratos.messaging.domain.topology.Member;

import java.util.Map;

/*
 * It holds the runtime data of a VM service cluster
 */
public class VMServiceClusterContext extends VMClusterContext {

    private static final Log log = LogFactory.getLog(VMServiceClusterContext.class);

    protected AutoscalePolicy autoscalePolicy;

    public VMServiceClusterContext(String clusterId, String serviceId, AutoscalePolicy autoscalePolicy, DeploymentPolicy deploymentPolicy,
                                   Map<String, NetworkPartitionContext> networkPartitionCtxts) {

        super(clusterId, serviceId, autoscalePolicy, deploymentPolicy, networkPartitionCtxts);
        this.autoscalePolicy = autoscalePolicy;

    }

    public AutoscalePolicy getAutoscalePolicy() {
        return autoscalePolicy;
    }

    public void setAutoscalePolicy(AutoscalePolicy autoscalePolicy) {
        this.autoscalePolicy = autoscalePolicy;
    }


}
