/**
 * Copyright (C) 2016 Joerg Bellmann (joerg.bellmann@googlemail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.siggici.keys.app.config;

import org.siggici.keys.DefaultDeployKeyService;
import org.siggici.keys.DeployKeyService;
import org.siggici.keys.DeployKeyStore;
import org.siggici.keys.jpa.DeployKeyRepository;
import org.siggici.keys.jpa.JpaDeployKeyStore;
import org.siggici.keys.rest.KeysRestController;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableConfigurationProperties({ DeployKeyServiceProperties.class })
@EnableJpaRepositories(basePackageClasses={JpaDeployKeyStore.class})
@EntityScan(basePackageClasses={JpaDeployKeyStore.class})
public class DeployKeyServiceConfig {

    @Bean
    public DeployKeyStore deployKeyStore(DeployKeyServiceProperties props, DeployKeyRepository repository) {
        DeployKeyService dks = new DefaultDeployKeyService(props.getType(), props.getSize(), props.getComment());
        return new JpaDeployKeyStore(dks, repository);
    }

    @Bean
    public KeysRestController keysRestController(DeployKeyStore store) {
        return new KeysRestController(store);
    }
}