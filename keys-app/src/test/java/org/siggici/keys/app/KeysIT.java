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
package org.siggici.keys.app;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.siggici.keys.DeployKey;
import org.siggici.keys.client.KeysRestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Simple remote IT using {@link KeysRestClient} from 'keys-client'-module.
 * 
 * @author jbellmann
 *
 */
public class KeysIT {

    private final Logger logger = LoggerFactory.getLogger(KeysIT.class);
    
    @Test
    public void createKey() throws JsonProcessingException {
        KeysRestClient client = new KeysRestClient(new RestTemplate(), "http://localhost:10081");
        String keyId = client.create();
        Optional<DeployKey> dkOptional = client.byId(keyId, false);

        Assertions.assertThat(dkOptional).isPresent();
        ObjectMapper om = new ObjectMapper();
        logger.info("Got DeployKey : {}", om.writeValueAsString(dkOptional.get()));
    }

}
