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

import com.huangyunkun.jviff.helper.StepBuilder;
import com.huangyunkun.jviff.modal.Step;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.net.URL;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(PowerMockRunner.class)
public class WaitForRunnerTest {
    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private WebDriver webDriver;
    @Mock
    private WebDriverWait wait;
    private Step step;


    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    @PrepareForTest(WaitForRunner.class)
    public void shouldWait() throws Exception {
        step = new StepBuilder().withTarget(By.id("back")).build();
        PowerMockito.whenNew(WebDriverWait.class).withArguments(webDriver, 20l).thenReturn(wait);

        WaitForRunner waitForRunner = new WaitForRunner();

        waitForRunner.run(webDriver, new URL("http://www.com"), step);

        verify(wait).until(any(ExpectedCondition.class));
    }
}