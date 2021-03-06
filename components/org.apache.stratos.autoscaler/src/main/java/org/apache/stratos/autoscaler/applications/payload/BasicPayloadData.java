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

package org.apache.stratos.autoscaler.applications.payload;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.Serializable;

/**
 * Contains basic payload data fields
 */
public class BasicPayloadData implements Serializable {

    private static Log log = LogFactory.getLog(BasicPayloadData.class);

    private String appId;
    private String groupName;
    private String serviceName;
    private String clusterId;
    private String hostName;
    private int tenantId;
    private String tenantRange;
    private String subscriptionAlias;
    private String deployment;
    private String puppetIp;
    private String puppetHostName;
    private String puppetEnvironment;
    private String subscriptionKey;
    private String applicationPath;
    private String gitRepositoryUrl;
    private String portMappings;
    private String multitenant;
    private String provider;
    private String[] dependencyAliases;
    private String[] exportingProperties;

    protected StringBuilder payloadBuilder;

    public BasicPayloadData() {

    }

    public void populatePayload () {

        payloadBuilder = new StringBuilder();

        payloadBuilder.append("APP_ID=" + getAppId());
        payloadBuilder.append(",");
        payloadBuilder.append("GROUP_NAME=" + getGroupName());
        payloadBuilder.append(",");
        payloadBuilder.append("SERVICE_NAME=" + getServiceName());
        payloadBuilder.append(",");
        payloadBuilder.append("HOST_NAME=" + getHostName());
        payloadBuilder.append(",");
        payloadBuilder.append("MULTITENANT=" + getMultitenant());
        payloadBuilder.append(",");
        payloadBuilder.append("TENANT_ID=" + getTenantId());
        payloadBuilder.append(",");
        payloadBuilder.append("TENANT_RANGE=" + getTenantRange());
        payloadBuilder.append(",");
        payloadBuilder.append("CARTRIDGE_ALIAS=" + getSubscriptionAlias());
        payloadBuilder.append(",");
        payloadBuilder.append("CLUSTER_ID=" + getClusterId());
        payloadBuilder.append(",");
        payloadBuilder.append("CARTRIDGE_KEY=" + getSubscriptionKey());
        payloadBuilder.append(",");
        //payloadBuilder.append("DEPLOYMENT=" + getDeployment());
        //payloadBuilder.append(",");
        //payloadBuilder.append("APP_PATH=" + getApplicationPath());
        //payloadBuilder.append(",");
        payloadBuilder.append("REPO_URL=" + getGitRepositoryUrl());
        payloadBuilder.append(",");
        payloadBuilder.append("PORTS=" + getPortMappings());
        payloadBuilder.append(",");
        payloadBuilder.append("PROVIDER=" + getProvider());

        //Payload Data exposed as system variables
        payloadBuilder.append(",");
        payloadBuilder.append("PUPPET_IP=" + System.getProperty("puppet.ip"));
        payloadBuilder.append(",");
        payloadBuilder.append("PUPPET_HOSTNAME=" + System.getProperty("puppet.hostname"));
        payloadBuilder.append(",");
        payloadBuilder.append("PUPPET_DNS_AVAILABLE=" + System.getProperty("puppet.env"));
        payloadBuilder.append(",");
        payloadBuilder.append("PUPPET_ENV=" + System.getProperty("puppet.dns.available"));
        payloadBuilder.append(",");
        if(getDependencyAliasesPayloadString() != null){
            payloadBuilder.append("DEPENDECNY_ALIASES=" + getDependencyAliasesPayloadString());
        }
        payloadBuilder.append(",");
        if(getExportingPropertiesPayloadString() != null){
            payloadBuilder.append("EXPORTING_PROPERTIES=" + getExportingPropertiesPayloadString());
        }

    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getClusterId() {
        return clusterId;
    }

    public void setClusterId(String clusterId) {
        this.clusterId = clusterId;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public int getTenantId() {
        return tenantId;
    }

    public void setTenantId(int tenantId) {
        this.tenantId = tenantId;
    }

    public String getTenantRange() {
        return tenantRange;
    }

    public void setTenantRange(String tenantRange) {
        this.tenantRange = tenantRange;
    }

    public String getSubscriptionAlias() {
        return subscriptionAlias;
    }

    public void setSubscriptionAlias(String subscriptionAlias) {
        this.subscriptionAlias = subscriptionAlias;
    }

    public String getDeployment() {
        return deployment;
    }

    public void setDeployment(String deployment) {
        this.deployment = deployment;
    }

    public String getPuppetIp() {
        return puppetIp;
    }

    public void setPuppetIp(String puppetIp) {
        this.puppetIp = puppetIp;
    }

    public String getSubscriptionKey() {
        return subscriptionKey;
    }

    public void setSubscriptionKey(String subscriptionKey) {
        this.subscriptionKey = subscriptionKey;
    }

    public StringBuilder getPayloadData () {

        return payloadBuilder;
    }

    public String getApplicationPath() {
        return applicationPath;
    }

    public void setApplicationPath(String applicationPath) {
        this.applicationPath = applicationPath;
    }

    public String getGitRepositoryUrl() {
        return gitRepositoryUrl;
    }

    public void setGitRepositoryUrl(String gitRepositoryUrl) {
        this.gitRepositoryUrl = gitRepositoryUrl;
    }

    public String getPortMappings() {
        return portMappings;
    }

    public void setPortMappings(String portMappings) {
        this.portMappings = portMappings;
    }

    public String getMultitenant() {
        return multitenant;
    }

    public void setMultitenant(String multitenant) {
        this.multitenant = multitenant;
    }

    public String getPuppetHostName() {
        return puppetHostName;
    }

    public void setPuppetHostName(String puppetHostName) {
        this.puppetHostName = puppetHostName;
    }

    public String getPuppetEnvironment() {
        return puppetEnvironment;
    }

    public void setPuppetEnvironment(String puppetEnvironment) {
        this.puppetEnvironment = puppetEnvironment;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String[] getDependencyAliases() {
        return dependencyAliases;
    }

    public void setDependencyAliases(String[] dependencyAliases) {
        this.dependencyAliases = dependencyAliases;
    }

    private String getDependencyAliasesPayloadString(){
        if(dependencyAliases == null){
            return null;
        }

        StringBuilder dependencyAliasesPayload = new StringBuilder();
        for(int i=0; i< dependencyAliases.length; i++){
            dependencyAliasesPayload.append(dependencyAliases[i]);
            if(i != dependencyAliases.length -1){
                dependencyAliasesPayload.append("|");
            }
        }
        log.info("testing1 getDependencyAliasesPayloadString " + dependencyAliasesPayload);
        return dependencyAliasesPayload.toString();
    }

    private String getExportingPropertiesPayloadString(){
        if(exportingProperties == null){
            return null;
        }

        StringBuilder exportingPropertiesPayload = new StringBuilder();
        for(int i=0; i< exportingProperties.length; i++){
            exportingPropertiesPayload.append(exportingProperties[i]);
            if(i != exportingProperties.length -1){
                exportingPropertiesPayload.append("|");
            }
        }
        log.info("testing1 getExportingPropertiesPayloadString " + exportingPropertiesPayload);
        return exportingPropertiesPayload.toString();
    }

    public String[] getExportingProperties() {
        return exportingProperties;
    }

    public void setExportingProperties(String[] exportingProperties) {
        this.exportingProperties = exportingProperties;
    }
}
