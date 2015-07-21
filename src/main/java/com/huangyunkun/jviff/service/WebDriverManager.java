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

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static java.util.Collections.synchronizedList;

public class WebDriverManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebDriverManager.class);
    private static List<WebDriver> drivers = synchronizedList(Lists.<WebDriver>newLinkedList());

    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>() {
        @Override
        protected WebDriver initialValue() {
            WebDriver driver = createWebDriver();
            drivers.add(driver);
            return driver;
        }
    };
    private static String firefoxPath;

    private static WebDriver createWebDriver() {
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile.setPreference("app.update.auto", false);
        firefoxProfile.setPreference("app.update.enabled", false);
        //TODO:Disable proxy first, will add a proxy setting option later
        firefoxProfile.setPreference("network.proxy.type", 0);
        WebDriver driver = new FirefoxDriver(firefoxProfile);
        return driver;
    }

    static {
        makeSureItFinishesAllWhenShuttingDownTheJVM();
    }

    private static void makeSureItFinishesAllWhenShuttingDownTheJVM() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                finishAll();
            }
        });
    }

    private static void finishAll() {
        for (WebDriver driver : drivers) {
            try {
                driver.quit();
            } catch (Exception ex) {
                LOGGER.trace("web driver close failed", ex);
            }
        }
    }

    public static WebDriver getWebDriver() {
        return webDriver.get();
    }

    public static void setFirefoxPath(String firefoxPath) {
        if (!StringUtils.isEmpty(firefoxPath)) {
            System.setProperty("webdriver.firefox.bin", firefoxPath);
        }
    }
}
