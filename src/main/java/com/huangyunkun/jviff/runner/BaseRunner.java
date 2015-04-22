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

import com.huangyunkun.jviff.modal.Step;
import com.huangyunkun.jviff.modal.StepAction;
import org.openqa.selenium.WebDriver;

import java.net.URL;

public abstract class BaseRunner {
    private StepAction stepAction;

    public BaseRunner(final StepAction stepAction) {
        this.stepAction = stepAction;
    }

    public abstract void run(final WebDriver webDriver, final URL host, final Step step);

    public Boolean supports(final Step step) {
        return step.getAction().equals(this.stepAction);
    }
}
