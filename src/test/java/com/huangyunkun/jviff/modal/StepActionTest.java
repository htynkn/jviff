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

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class StepActionTest {
    @Test
    public void shouldParseValidName() throws Exception {
        StepAction action = StepAction.from("waitFor");

        assertThat(action, is(StepAction.WAIT_FOR));
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionForInvalidName() throws Exception {
        StepAction.from("invalid");
    }
}