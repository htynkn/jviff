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

import com.google.common.io.Resources;
import com.huangyunkun.jviff.core.impl.PixelComparator;
import com.huangyunkun.jviff.modal.StepResult;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.io.File;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class ComparatorTest {
    private Comparator comparator;
    private StepResult stepResult;

    public ComparatorTest(Comparator comparator) {
        this.comparator = comparator;
    }

    @Parameters
    public static Iterable<Object[]> data() {
        Object[][] objects = {{new PixelComparator()}};
        return Arrays.asList(objects);
    }

    @Before
    public void setUp() throws Exception {
        stepResult = new StepResult(null);
    }

    @Test
    public void shouldReturnFalseForDifferentText() throws Exception {
        File textOne = new File(Resources.getResource("images/text-0-0.png").getFile());
        File textTwo = new File(Resources.getResource("images/text-1-0.png").getFile());

        comparator.compare(textOne, textTwo, stepResult);

        assertThat(stepResult.getSuccess(), is(false));
    }

    @Test
    public void shouldReturnTrueForSameBaiduSearchPage() throws Exception {
        File textOne = new File(Resources.getResource("images/baidu-0-0.png").getFile());
        File textTwo = new File(Resources.getResource("images/baidu-1-0.png").getFile());

        comparator.compare(textOne, textTwo, stepResult);

        assertThat(stepResult.getSuccess(), is(true));
    }

    @Test
    public void shouldReturnFalseForDiffBaiduLogoSize() throws Exception {
        File textOne = new File(Resources.getResource("images/baidu-2-0.png").getFile());
        File textTwo = new File(Resources.getResource("images/baidu-2-1.png").getFile());

        comparator.compare(textOne, textTwo, stepResult);

        assertThat(stepResult.getSuccess(), is(false));
    }

    @Test
    public void shouldReturnFalseForDiffAcgmoPageSize() throws Exception {
        File textOne = new File(Resources.getResource("images/acgmo-1-0.png").getFile());
        File textTwo = new File(Resources.getResource("images/acgmo-1-1.png").getFile());

        comparator.compare(textOne, textTwo, stepResult);

        assertThat(stepResult.getSuccess(), is(false));
    }
}