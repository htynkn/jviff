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
package com.huangyunkun.jviff.service;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ClearSystemProperties;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Huang YunKun on 2015/7/21.
 * WebSite: http://www.huangyunkun.com
 * Email: htynkn@163.com
 */
public class WebDriverManagerTest {
    @Rule
    public final ClearSystemProperties clearedProperty = new ClearSystemProperties(WEBDRIVER_FIREFOX_BIN);

    public static final String WEBDRIVER_FIREFOX_BIN = "webdriver.firefox.bin";

    @Test
    public void shouldSetFirefoxBinPathIfNotEmpty() throws Exception {
        WebDriverManager.setFirefoxPath("mocked_firefox_path");

        assertThat(System.getProperty(WEBDRIVER_FIREFOX_BIN), is("mocked_firefox_path"));
    }
}