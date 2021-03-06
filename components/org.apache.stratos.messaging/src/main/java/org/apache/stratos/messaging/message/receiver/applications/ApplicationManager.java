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

package org.apache.stratos.messaging.message.receiver.applications;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.stratos.messaging.domain.applications.Applications;
import org.apache.stratos.messaging.domain.applications.locking.ApplicationLock;
import org.apache.stratos.messaging.domain.applications.locking.ApplicationLockHierarchy;

public class ApplicationManager {

    private static final Log log = LogFactory.getLog(ApplicationManager.class);

    private static volatile Applications applications;
    private static volatile ApplicationLockHierarchy applicationLockHierarchy =
            ApplicationLockHierarchy.getInstance();

    public static Applications getApplications () {

        if (applications == null) {
            synchronized (ApplicationManager.class){
                if (applications == null) {
                    applications = new Applications();
                    if(log.isDebugEnabled()) {
                        log.debug("Applications object created");
                    }
                }
            }
        }
        return applications;
    }

    /**
     * Acquires read lock for all Applications
     */
    public static void acquireReadLockForApplications() {
        if(log.isDebugEnabled()) {
            log.debug("Read lock acquired for Applications");
        }
        applicationLockHierarchy.getApplicationLock().acquireReadLock();
    }

    /**
     * Releases read lock for all Applications
     */
    public static void releaseReadLockForApplications() {
        if(log.isDebugEnabled()) {
            log.debug("Read lock released for Applications");
        }
        applicationLockHierarchy.getApplicationLock().releaseReadLock();
    }

    /**
     * Acquires read lock for an Application
     *
     * @param applicationId Application Id to acquire read lock
     */
    public static void acquireReadLockForApplication(String applicationId) {

        // acquire read lock for all Applications
        acquireReadLockForApplications();

        ApplicationLock applicationLock = applicationLockHierarchy.getLockForApplication(applicationId);
        if (applicationLock == null) {
            handleLockNotFound("Application lock not found for Application " + applicationId);

        } else {
            applicationLock.acquireReadLock();
            if(log.isDebugEnabled()) {
                log.debug("Read lock acquired for Application " + applicationId);
            }
        }
    }

    /**
     * Releases read lock for an Application
     *
     * @param applicationId Application Id to release read lock
     */
    public static void releaseReadLockForApplication(String applicationId) {

        ApplicationLock applicationLock = applicationLockHierarchy.getLockForApplication(applicationId);
        if (applicationLock == null) {
            handleLockNotFound("Application lock not found for Application " + applicationId);

        } else {
            applicationLock.releaseReadLock();
            if(log.isDebugEnabled()) {
                log.debug("Read lock released for Application " + applicationId);
            }
        }

        // release read lock for all Applications
        releaseReadLockForApplications();
    }

    private static void handleLockNotFound (String errorMsg) {
        log.warn(errorMsg);
        //throw new RuntimeException(errorMsg);
    }
}
