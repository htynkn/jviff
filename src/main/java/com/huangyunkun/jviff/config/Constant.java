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
package com.huangyunkun.jviff.config;

import com.google.common.collect.Lists;
import com.huangyunkun.jviff.parser.*;
import com.huangyunkun.jviff.runner.*;

import java.util.List;

public class Constant {
    public final static List<Class<? extends BaseStepParser>> STEP_PARSER_LIST = Lists.newArrayList(ClickStepParser.class,
            InputStepParser.class, LinkStepParser.class, OpenStepParser.class, SelectStepParser.class,
            WaitForStepParser.class);
    public final static List<Class<? extends BaseRunner>> RUNNER_LIST = Lists.newArrayList(ClickRunner.class,
            InputRunner.class, LinkRunner
                    .class, OpenRunner.class, SelectRunner.class, WaitForRunner.class);
}
