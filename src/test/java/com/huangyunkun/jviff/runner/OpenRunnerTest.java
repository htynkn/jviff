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
import com.huangyunkun.jviff.modal.StepAction;
import org.junit.Before;
import org.junit.Test;

import java.net.URL;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

public class OpenRunnerTest extends RunnerTest {
    @Before
    public void setUp() throws Exception {
        initRunner(OpenRunner.class);
    }

    @Test
    public void shouldOpenUrl() throws Exception {
        Step step = new StepBuilder().withAction(StepAction.OPEN).withContent("/").build();
        url = new URL("http://www.web.com");
        runner.run(webDriver, url, step);

        verify(webDriver).get(eq("http://www.web.com/"));
    }

    @Test
    public void shouldGetChildrenPath() throws Exception {
        Step step = new StepBuilder().withAction(StepAction.OPEN).withContent("/children").build();
        url = new URL("http://www.web.com");
        runner.run(webDriver, url, step);

        verify(webDriver).get(eq("http://www.web.com/children"));
    }
}