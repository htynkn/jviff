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
package com.huangyunkun.jviff.service;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.huangyunkun.jviff.modal.Step;
import com.huangyunkun.jviff.parser.BaseStepParser;
import com.huangyunkun.jviff.runner.BaseRunner;
import org.openqa.selenium.WebDriver;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.List;
import java.util.Set;

public class StepContainer {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private List<Step> steps = Lists.newArrayList();
    private static ImmutableMap<String, BaseStepParser> parserImmutableMap;
    private static ImmutableList<BaseRunner> baseRunners;
    private URL host;

    static {
        assembleParser();
        ImmutableList.Builder<BaseRunner> builder = ImmutableList.builder();
        Reflections reflections = new Reflections("com.huangyunkun.jviff.runner");
        Set<Class<? extends BaseRunner>> list = reflections.getSubTypesOf(BaseRunner.class);
        for (Class<? extends BaseRunner> runnerClass : list) {
            try {
                BaseRunner runner = runnerClass.newInstance();
                builder.add(runner);
            } catch (Exception e) {
                throw new RuntimeException();
            }
        }
        baseRunners = builder.build();
    }

    private static void assembleParser() {
        Reflections reflections = new Reflections("com.huangyunkun.jviff.parser");
        Set<Class<? extends BaseStepParser>> list = reflections.getSubTypesOf(BaseStepParser.class);
        ImmutableMap.Builder<String, BaseStepParser> builder = ImmutableMap.builder();
        for (Class<? extends BaseStepParser> parserClass : list) {
            try {
                BaseStepParser stepParser = parserClass.newInstance();
                builder.put(stepParser.getActionKey(), stepParser);
            } catch (Exception e) {
                throw new RuntimeException();
            }
        }
        parserImmutableMap = builder.build();
    }

    public Step parse(String scriptLine) {
        String[] split = scriptLine.split(" ");
        return parserImmutableMap.get(split[0]).parse(scriptLine);
    }

    public void add(String scriptLine) {
        this.steps.add(parse(scriptLine));
    }

    public void runStep(WebDriver webDriver, final Step step) {
        for (BaseRunner baseRunner : baseRunners) {
            if (baseRunner.supports(step)) {
                baseRunner.run(webDriver, host, step);
                break;
            }
        }
    }

    public void add(String[] scripts) {
        for (String script : scripts) {
            add(script);
        }
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setHost(String host) {
        try {
            this.host = new URL(host);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
