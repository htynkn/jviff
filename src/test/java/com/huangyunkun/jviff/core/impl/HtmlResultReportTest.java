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
package com.huangyunkun.jviff.core.impl;

import com.google.common.collect.Lists;
import com.google.common.io.Files;
import com.huangyunkun.jviff.helper.StepBuilder;
import com.huangyunkun.jviff.modal.Step;
import com.huangyunkun.jviff.modal.StepResult;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class HtmlResultReportTest {
    private HtmlResultReport resultReport;

    @Before
    public void setUp() throws Exception {
        resultReport = new HtmlResultReport();
    }

    @Test
    public void shouldGetReport() throws Exception {
        File tempDir = Files.createTempDir();
        List<StepResult> stepResults = Lists.newArrayList();
        Step step = new StepBuilder().withContent("content").build();
        step.setRecord(true);
        StepResult stepResult = new StepResult(step);
        stepResult.addImage("http://placehold.it/350x150");
        stepResult.addImage("http://placehold.it/350x150");
        stepResult.setDiffImage("http://localhost:63342/jviff/html/index.html#");
        stepResult.setSuccess(true);
        stepResults.add(stepResult);
        resultReport.report(stepResults, tempDir.getAbsoluteFile());

        File outputHtml = new File(tempDir, "index.html");

        assertThat(outputHtml.exists(), is(true));
    }
}