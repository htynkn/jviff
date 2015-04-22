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
package com.huangyunkun.jviff.e2e;

import com.google.common.io.Resources;
import com.huangyunkun.jviff.Runner;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.junit.Test;

public class RunnerTest {
    @Test
    public void shouldInvokeRunner() throws Exception {
        String webPath = Resources.getResource("web/").getPath();
        Server server = new Server(7777);
        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setDirectoriesListed(true);
        resourceHandler.setResourceBase(webPath);
        server.setHandler(resourceHandler);
        server.start();

        Thread.sleep(5000);

        String configPath = Resources.getResource("config/web.yaml").getPath();
        Runner.main(new String[]{"-c", configPath});

        server.stop();
    }
}
