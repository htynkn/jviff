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

import com.huangyunkun.jviff.modal.Step;
import com.huangyunkun.jviff.modal.StepAction;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SelectStepParserTest {
    private SelectStepParser parser;

    @Before
    public void setUp() throws Exception {
        parser = new SelectStepParser();
    }

    @Test
    public void shouldSupportKeyWord() throws Exception {
        assertThat(parser.getActionKey(), is("select"));
    }

    @Test
    public void shouldParseScript() throws Exception {
        Step step = parser.parse("select .colorSelect yellow record");

        assertThat(step.getAction(), is(StepAction.SELECT));
        assertThat(step.getContent(), is("yellow"));
        assertThat(step.getTarget(), is(By.className("colorSelect")));
        assertThat(step.getRecord(), is(true));
    }
}