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

import org.apache.commons.lang3.StringUtils;

public class StepResult {
    private final Step step;
    private String firstImage;
    private String secondImage;

    public StepResult(Step step) {
        this.step = step;
    }

    private Boolean success = true;
    private String diffImage;

    public Step getStep() {
        return step;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getDiffImage() {
        return diffImage;
    }

    public void setDiffImage(String diffImage) {
        this.diffImage = diffImage;
    }

    public String getFirstImage() {
        return firstImage;
    }

    public String getSecondImage() {
        return secondImage;
    }

    public void addImage(String fileName) {
        if (StringUtils.isEmpty(firstImage)) {
            firstImage = fileName;
        } else {
            secondImage = fileName;
        }
    }
}
