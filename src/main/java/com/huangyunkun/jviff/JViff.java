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

import com.google.common.collect.Lists;
import com.google.common.io.Files;
import com.huangyunkun.jviff.config.Config;
import com.huangyunkun.jviff.config.ConfigManager;
import com.huangyunkun.jviff.core.Comparator;
import com.huangyunkun.jviff.core.impl.OverlapDiffGenerator;
import com.huangyunkun.jviff.core.impl.PixelComparator;
import com.huangyunkun.jviff.modal.Step;
import com.huangyunkun.jviff.modal.StepResult;
import com.huangyunkun.jviff.service.StepContainer;
import com.huangyunkun.jviff.service.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JViff {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final String filePath;
    private Comparator comparator;
    private File tempDir;
    private StepContainer stepContainer;
    private WebDriver driver;
    private OverlapDiffGenerator reporter;

    public JViff(String filePath) {
        this.filePath = filePath;
    }

    public void run() throws IOException {
        Config config = prepareEnv();
        TakesScreenshot takesScreenshot = prepareWebDriver(config);

        int stepId;
        int hostId = 0;
        List<StepResult> stepResults = Lists.newArrayList();
        for (Step step : stepContainer.getSteps()) {
            stepResults.add(new StepResult(step));
        }

        for (String host : config.getEnvHosts()) {
            stepContainer.setHost(host);
            stepId = 0;
            for (int i = 0; i < stepContainer.getSteps().size(); i++) {
                Step step = stepContainer.getSteps().get(i);
                stepContainer.runStep(driver, step);
                if (step.getRecord()) {
                    File screenShot = takesScreenshot.getScreenshotAs(OutputType.FILE);
                    String fileName = String.format("%d-%d.png", hostId, stepId);
                    stepResults.get(i).addImage(fileName);
                    Files.copy(screenShot, new File(tempDir, fileName));
                    stepId++;
                }
            }
            hostId++;
        }

        for (int i = 0; i < stepResults.size(); i++) {
            StepResult stepResult = stepResults.get(i);
            if (stepResult.getStep().getRecord()) {
                File one = new File(tempDir, stepResult.getFirstImage());
                File two = new File(tempDir, stepResult.getSecondImage());
                comparator.compare(one, two, stepResult);
                if (!stepResult.getSuccess()) {
                    reporter.report(one, two, tempDir.getAbsolutePath(), stepResult);
                    logger.error("Image is diff. " + stepContainer.getSteps().get(i));
                }
            }
        }

        driver.quit();
    }

    private TakesScreenshot prepareWebDriver(Config config) {
        WebDriverManager.setFirefoxPath(config.getFirefox());
        driver = WebDriverManager.getWebDriver();
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        return takesScreenshot;
    }

    private Config prepareEnv() throws IOException {
        comparator = new PixelComparator();
        reporter = new OverlapDiffGenerator();
        Config config = ConfigManager.getConfigFromFile(filePath);
        tempDir = new File(config.getOutputDir());
        logger.info("Folder path: " + tempDir.getAbsolutePath());
        stepContainer = new StepContainer();
        stepContainer.add(config.getScripts());
        return config;
    }
}
