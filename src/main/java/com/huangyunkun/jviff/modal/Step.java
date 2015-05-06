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

import com.google.common.base.MoreObjects;
import org.openqa.selenium.By;

public class Step {
    private By target;
    private StepAction action;
    private String content;
    private Boolean record = false;
    private String originScript;

    public By getTarget() {
        return target;
    }

    public void setTarget(By target) {
        this.target = target;
    }

    public StepAction getAction() {
        return action;
    }

    public void setAction(StepAction action) {
        this.action = action;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getRecord() {
        return record;
    }

    public void setRecord(Boolean record) {
        this.record = record;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(Step.class)
                .omitNullValues()
                .add("Action", this.getAction().getKeyword())
                .add("Target", this.getTarget())
                .add("Content", this.getContent())
                .toString();
    }

    public void setOriginScript(String originScript) {
        this.originScript = originScript;
    }

    public String getOriginScript() {
        return originScript;
    }
}
