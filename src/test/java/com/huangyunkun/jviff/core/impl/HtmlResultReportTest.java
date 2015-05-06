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