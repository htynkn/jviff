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
package com.huangyunkun.jviff.helper;

import com.huangyunkun.jviff.modal.Step;
import com.huangyunkun.jviff.modal.StepAction;
import org.openqa.selenium.By;

public class StepBuilder {
    private Step step;

    public StepBuilder() {
        this.step = new Step();
        this.step.setAction(StepAction.LINK);
        this.step.setTarget(By.id("unknown"));
    }

    public StepBuilder withAction(StepAction action) {
        step.setAction(action);
        return this;
    }

    public Step build() {
        return step;
    }

    public StepBuilder withContent(String content) {
        step.setContent(content);
        return this;
    }
}
