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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.google.common.io.Files;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;

public class ConfigManager {
    public static Config getConfigFromFile(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        Config config = mapper.readValue(new File(filePath), Config.class);
        if (StringUtils.isEmpty(config.getOutputDir())) {
            config.setOutputDir(Files.createTempDir().getAbsolutePath());
        }
        return config;
    }
}
