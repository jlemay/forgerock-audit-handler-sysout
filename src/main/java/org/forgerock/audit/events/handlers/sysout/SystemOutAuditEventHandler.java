/*
 * The contents of this file are subject to the terms of the Common Development and
 * Distribution License (the License). You may not use this file except in compliance with the
 * License.
 *
 * You can obtain a copy of the License at legal/CDDLv1.0.txt. See the License for the
 * specific language governing permission and limitations under the License.
 *
 * When distributing Covered Software, include this CDDL Header Notice in each file and include
 * the License file at legal/CDDLv1.0.txt. If applicable, add the following below the CDDL
 * Header, with the fields enclosed by brackets [] replaced by your own identifying
 * information: "Portions copyright [year] [name of copyright owner]".
 *
 * Copyright 2015 ForgeRock AS.
 */
package org.forgerock.audit.events.handlers.sysout;

import static org.forgerock.json.JsonValue.json;
import static org.forgerock.json.JsonValue.object;
import static org.forgerock.json.resource.Responses.newResourceResponse;

import java.util.UUID;

import org.forgerock.audit.events.handlers.AuditEventHandlerBase;
import org.forgerock.json.JsonValue;
import org.forgerock.json.resource.QueryRequest;
import org.forgerock.json.resource.QueryResourceHandler;
import org.forgerock.json.resource.QueryResponse;
import org.forgerock.json.resource.ResourceException;
import org.forgerock.json.resource.ResourceResponse;
import org.forgerock.json.resource.Responses;
import org.forgerock.services.context.Context;
import org.forgerock.util.promise.Promise;

public class SystemOutAuditEventHandler extends AuditEventHandlerBase<SystemOutAuditEventHandlerConfiguration> {
    @Override
    public void configure(SystemOutAuditEventHandlerConfiguration eventHandlerConfiguration) throws ResourceException {
    }

    @Override
    public void startup() throws ResourceException {
    }

    @Override
    public void shutdown() throws ResourceException {
    }

    @Override
    public Class<SystemOutAuditEventHandlerConfiguration> getConfigurationClass() {
        return SystemOutAuditEventHandlerConfiguration.class;
    }

    @Override
    public Promise<ResourceResponse, ResourceException> publishEvent(Context context, String topic, JsonValue jsonValue) {
        System.out.println(String.format("Published Event with content %s", jsonValue.toString()));
        return newResourceResponse(UUID.randomUUID().toString(), null, jsonValue).asPromise();
    }

    @Override
    public Promise<ResourceResponse, ResourceException> readEvent(Context context, String topic, String id) {
        System.out.println(String.format("Read Event %s", id));
        return Responses.newResourceResponse(id, null, json(object())).asPromise();
    }

    @Override
    public Promise<QueryResponse, ResourceException> queryEvents(Context context, String s, QueryRequest
            queryRequest, QueryResourceHandler queryResourceHandler) {
        return Responses.newQueryResponse().asPromise();
    }
}
