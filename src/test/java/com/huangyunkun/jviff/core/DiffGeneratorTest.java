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
package com.huangyunkun.jviff.core;

import com.google.common.io.Files;
import com.google.common.io.Resources;
import com.huangyunkun.jviff.core.impl.OverlapDiffGenerator;
import com.huangyunkun.jviff.modal.Step;
import com.huangyunkun.jviff.modal.StepResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

@RunWith(Parameterized.class)
public class DiffGeneratorTest {
    private DiffGenerator reporter;
    private StepResult stepResult;

    public DiffGeneratorTest(DiffGenerator reporter) {
        this.reporter = reporter;
        this.stepResult = new StepResult(mock(Step.class));
    }

    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        Object[][] objects = {{new OverlapDiffGenerator()}};
        return Arrays.asList(objects);
    }

    @Test
    public void shouldReport() throws Exception {
        File fileOne = new File(Resources.getResource("images/text-0-0.png").getFile());
        File fileTwo = new File(Resources.getResource("images/text-1-0.png").getFile());
        File tempFile = Files.createTempDir();
        reporter.report(fileOne, fileTwo, tempFile.getAbsolutePath(), stepResult);

        File diffFile = new File(tempFile, "text-diff-0.png");

        assertThat(diffFile.exists(), is(true));
    }
}