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
package com.huangyunkun.jviff;

import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Runner {
    private final static Logger LOGGER = LoggerFactory.getLogger(Runner.class);

    public static void main(String args[]) {
        Options options = new Options();
        options.addOption("c", "config", true, "config file");
        CommandLineParser parser = new PosixParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            if (cmd.hasOption("c")) {
                JViff jViff = new JViff(cmd.getOptionValue("c"));
                jViff.run();
            } else {
                printHelp(options);
            }
        } catch (ParseException e) {
            printHelp(options);
        } catch (IOException e) {
            LOGGER.error("jviff face a error", e);
        }
    }

    private static void printHelp(Options options) {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("jviff", options);
    }
}
