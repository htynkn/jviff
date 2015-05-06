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

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import com.huangyunkun.jviff.core.ResultReporter;
import com.huangyunkun.jviff.modal.StepResult;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.FileTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by Huang YunKun on 2015/5/6.
 * WebSite: http://www.huangyunkun.com
 * Email: htynkn@163.com
 */
public class HtmlResultReport implements ResultReporter {
    private TemplateEngine templateEngine;

    public HtmlResultReport() {
        templateEngine = new TemplateEngine();
        TemplateResolver templateResolver = new FileTemplateResolver();
        templateResolver.setTemplateMode("HTML5");
        templateEngine.setTemplateResolver(templateResolver);
    }

    @Override
    public void report(List<StepResult> stepResults, File outputDir) throws IOException {
        Iterable<StepResult> stepResultIterable = Iterables.filter(stepResults, new Predicate<StepResult>() {
            @Override
            public boolean apply(StepResult input) {
                return input.getStep().getRecord();
            }
        });
        final Context ctx = new Context();
        ctx.setVariable("stepResults", stepResultIterable);
        String templateFile = Resources.getResource("html/index.html").getPath();
        String html = templateEngine.process(templateFile, ctx);
        File outputFile = new File(outputDir, "index.html");
        Files.write(html, outputFile, Charset.forName("utf8"));
    }
}
