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
package com.huangyunkun.jviff.runner;

import org.mockito.Answers;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.net.URL;

import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

public abstract class RunnerTest {
    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    protected WebDriver webDriver;
    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    protected WebElement foundElement;
    protected URL url;
    protected BaseRunner runner;

    protected void initRunner(Class<? extends BaseRunner> clazz) throws IllegalAccessException, InstantiationException {
        runner = clazz.newInstance();
    }

    public RunnerTest() {
        initMocks(this);
        given(webDriver.findElement(Matchers.<By>any())).willReturn(foundElement);
    }
}
