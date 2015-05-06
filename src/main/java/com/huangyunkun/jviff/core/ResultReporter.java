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

import com.huangyunkun.jviff.modal.StepResult;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Huang YunKun on 2015/5/6.
 * WebSite: http://www.huangyunkun.com
 * Email: htynkn@163.com
 */
public interface ResultReporter {
    void report(List<StepResult> stepResults, File outputDir) throws IOException;
}
