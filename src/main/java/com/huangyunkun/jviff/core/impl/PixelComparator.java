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

import com.huangyunkun.jviff.core.Comparator;
import com.huangyunkun.jviff.modal.StepResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class PixelComparator implements Comparator {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void compare(File i, File j, StepResult result) {
        BufferedImage imageOne = null;
        BufferedImage imageTwo = null;
        Boolean theSame = true;
        try {
            imageOne = ImageIO.read(i);
            imageTwo = ImageIO.read(j);
            int width1 = imageOne.getWidth(null);
            int width2 = imageTwo.getWidth(null);
            int height1 = imageOne.getHeight(null);
            int height2 = imageTwo.getHeight(null);
            if ((width1 == width2) && (height1 == height2)) {
                for (int y = 0; y < height1; y++) {
                    for (int x = 0; x < width1; x++) {
                        int rgb1 = imageOne.getRGB(x, y);
                        int rgb2 = imageTwo.getRGB(x, y);
                        int r1 = (rgb1 >> 16) & 0xff;
                        int g1 = (rgb1 >> 8) & 0xff;
                        int b1 = rgb1 & 0xff;
                        int r2 = (rgb2 >> 16) & 0xff;
                        int g2 = (rgb2 >> 8) & 0xff;
                        int b2 = rgb2 & 0xff;
                        int diff = Math.abs(r1 - r2) + Math.abs(g1 - g2) + Math.abs(b1 - b2);
                        if (diff > 0) {
                            theSame = false;
                            break;
                        }
                    }
                }
            } else {
                theSame = false;
            }
        } catch (Exception e) {
            logger.error("Exception happened when compare two image", e);
        } finally {
            imageOne = null;
            imageTwo = null;
        }
        result.setSuccess(theSame);
    }
}
