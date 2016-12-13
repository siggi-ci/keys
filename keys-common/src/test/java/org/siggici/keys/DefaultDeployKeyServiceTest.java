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
package org.siggici.keys;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import com.jcraft.jsch.KeyPair;

public class DefaultDeployKeyServiceTest {

    private DeployKeyService service;

    @Before
    public void setUp() {
        service = new DefaultDeployKeyService(KeyPair.RSA, 1024, "comment");
    }

    @Test
    public void testDefaultDeployKeyService() {
        DeployKey key = service.create();
        Assertions.assertThat(key).isNotNull();
    }

}
