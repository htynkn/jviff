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

import com.google.common.collect.Lists;
import com.huangyunkun.jviff.helper.StepBuilder;
import com.huangyunkun.jviff.modal.Step;
import com.huangyunkun.jviff.modal.StepAction;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SelectRunnerTest extends RunnerTest {
    @Before
    public void setUp() throws Exception {
        initRunner(SelectRunner.class);
    }

    @Test
    public void shouldSelectVisibleText() throws Exception {
        Step step = new StepBuilder().withAction(StepAction.SELECT).withContent("visible-text").build();
        given(foundElement.getTagName()).willReturn("select");
        given(foundElement.findElements(By.xpath(".//option[normalize-space(.) = \"visible-text\"]")))
                .willReturn(Lists.newArrayList(mock(WebElement.class)));
        runner.run(webDriver, url, step);

        verify(foundElement).findElements(By.xpath(".//option[normalize-space(.) = \"visible-text\"]"));
    }
}