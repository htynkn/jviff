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
