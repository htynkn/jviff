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
package com.huangyunkun.jviff.modal;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;

public class StepResultTest {
    private StepResult result;
    @Mock
    private Step step;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        result = new StepResult(step);
    }

    @Test
    public void shouldSetFirstImageForNewInstance() throws Exception {
        result.addImage("first.png");

        assertThat(result.getFirstImage(), is("first.png"));
    }

    @Test
    public void shouldSecondImage() throws Exception {
        result.addImage("first.png");
        result.addImage("second.png");

        assertThat(result.getFirstImage(), is("first.png"));
        assertThat(result.getSecondImage(), is("second.png"));
    }
}