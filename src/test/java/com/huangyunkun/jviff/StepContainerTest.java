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
package com.huangyunkun.jviff;

import com.huangyunkun.jviff.modal.Step;
import com.huangyunkun.jviff.modal.StepAction;
import com.huangyunkun.jviff.service.StepContainer;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class StepContainerTest {
    private StepContainer stepContainer;

    @Before
    public void setUp() throws Exception {
        stepContainer = new StepContainer();
    }

    @Test
    public void shouldParseClickScript() throws Exception {
        Step step = stepContainer.parse("click #buttonId");

        assertThat(step.getAction(), is(StepAction.CLICK));
        assertThat(step.getTarget(), instanceOf(By.ById.class));
        assertThat(step.getTarget(), is(By.id("buttonId")));
        assertThat(step.getRecord(), is(false));
    }

    @Test
    public void shouldParseInputScript() throws Exception {
        Step step = stepContainer.parse("input .username name record");

        assertThat(step.getAction(), is(StepAction.INPUT));
        assertThat(step.getTarget(), instanceOf(By.ByClassName.class));
        assertThat(step.getTarget(), is(By.className("username")));
        assertThat(step.getContent(), is("name"));
        assertThat(step.getRecord(), is(true));
    }

    @Test
    public void shouldParseWaitForScript() throws Exception {
        Step step = stepContainer.parse("waitFor #rs record");

        assertThat(step.getAction(), is(StepAction.WAIT_FOR));
        assertThat(step.getTarget(), instanceOf(By.ById.class));
        assertThat(step.getTarget(), is(By.id("rs")));
        assertThat(step.getRecord(), is(true));
    }

    @Test
    public void shouldParseOpenScript() throws Exception {
        Step step = stepContainer.parse("open /");

        assertThat(step.getAction(), is(StepAction.OPEN));
        assertThat(step.getContent(), is("/"));
    }

    @Test
    public void shouldParseLinkScript() throws Exception {
        Step step = stepContainer.parse("link help");

        assertThat(step.getAction(), is(StepAction.LINK));
        assertThat(step.getContent(), is("help"));
    }

    @Test
    public void shouldParseSelectScript() throws Exception {
        Step step = stepContainer.parse("select @select Opel record");

        assertThat(step.getAction(), is(StepAction.SELECT));
        assertThat(step.getTarget(), instanceOf(By.ByTagName.class));
        assertThat(step.getTarget(), is(By.tagName("select")));
    }
}