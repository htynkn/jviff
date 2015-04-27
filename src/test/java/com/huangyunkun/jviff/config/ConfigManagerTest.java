/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.huangyunkun.jviff.config;

import com.google.common.io.Resources;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ConfigManagerTest {
    @Test
    public void shouldParseConfigFile() throws Exception {
        Config config = ConfigManager.getConfigFromFile(Resources.getResource("config/web.yaml").getFile());

        assertThat(config.getEnvHosts().length, is(2));
        assertThat(config.getOutputDir(), not(nullValue()));
        assertThat(config.getScripts().length, is(6));
    }
}