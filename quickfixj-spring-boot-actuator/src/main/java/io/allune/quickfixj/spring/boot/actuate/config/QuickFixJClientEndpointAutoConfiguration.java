/*
 * Copyright 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.allune.quickfixj.spring.boot.actuate.config;

import io.allune.quickfixj.spring.boot.actuate.endpoint.QuickFixJClientEndpoint;
import io.allune.quickfixj.spring.boot.starter.autoconfigure.client.QuickFixJClientAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.endpoint.condition.ConditionalOnEnabledEndpoint;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import quickfix.Initiator;
import quickfix.SessionSettings;

/**
 * {@link EnableAutoConfiguration Auto-configuration} for {@link QuickFixJClientEndpoint}.
 *
 * @author Eduardo Sanchez-Ros
 */
@Configuration
@AutoConfigureAfter(QuickFixJClientAutoConfiguration.class)
public class QuickFixJClientEndpointAutoConfiguration {

    @Bean
    @ConditionalOnBean(name = {"clientInitiator", "clientSessionSettings"})
    @ConditionalOnClass({Initiator.class, SessionSettings.class})
    @ConditionalOnMissingBean
	@ConditionalOnEnabledEndpoint
    public QuickFixJClientEndpoint quickfixjClientEndpoint(Initiator clientInitiator, SessionSettings clientSessionSettings) {
        return new QuickFixJClientEndpoint(clientInitiator, clientSessionSettings);
    }
}