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
package com.huangyunkun.jviff.parser;

import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SelectorFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(SelectorFactory.class);
    private static final Pattern ID_PATTERN = Pattern.compile("#(\\S+)");
    private static final Pattern CSS_PATTERN = Pattern.compile("\\.(\\S+)");
    private static final Pattern TAG_PATTERN = Pattern.compile("@(\\S+)");

    private static final ImmutableMap<Pattern, ? extends Class<? extends By>> PATTERN_IMMUTABLE_MAP = ImmutableMap.of(
            ID_PATTERN, By.ById.class,
            CSS_PATTERN, By.ByClassName.class,
            TAG_PATTERN, By.ByTagName.class
    );

    public static By create(String selector) {
        Matcher matcher;
        for (Map.Entry<Pattern, ? extends Class<? extends By>> entry : PATTERN_IMMUTABLE_MAP.entrySet()) {
            matcher = entry.getKey().matcher(selector);
            if (matcher.matches()) {
                try {
                    Constructor<? extends By> constructor = entry.getValue().getConstructor(String.class);
                    return constructor.newInstance(matcher.group(1));
                } catch (Exception e) {
                    LOGGER.error("Parse [" + selector + "] to by failed");
                }
            }
        }
        return null;
    }
}
