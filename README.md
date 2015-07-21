[![Build Status](https://snap-ci.com/htynkn/jviff/branch/master/build_image)](https://snap-ci.com/htynkn/jviff/branch/master)
[![Maintainer Status](http://stillmaintained.com/htynkn/jviff.png)](http://stillmaintained.com/htynkn/jviff)
[![Coverage Status](https://coveralls.io/repos/htynkn/jviff/badge.svg?branch=master)](https://coveralls.io/r/htynkn/jviff?branch=master)
[![Maven Central](https://img.shields.io/maven-central/v/com.huangyunkun/jviff.svg)](https://coveralls.io/r/htynkn/jviff?branch=master)
[![Apache 2](http://img.shields.io/badge/license-Apache%202-red.svg)](http://www.apache.org/licenses/LICENSE-2.0)
# JViff
Find visual differences between web pages in different environments.

I try to use a tool named [viff](https://github.com/winsonwq/viff) to help me find the different with two web page.

I find it's hard to use it because there are so many dependencies and some of them are very hard to install.

so I create a small tool to do this job. The only dependency of this tool is a firefox.

# How to use
* create your own config file like this one:
``` yaml
envHosts:
  - http://localhost:7777/normal/
  - http://localhost:7777/diff/

scripts:
  - "open 1.html record"

outputDir: C:\Users\user\AppData\Local\Temp\jviff
```

* download jviff standalone runner 
[Download](http://repo1.maven.org/maven2/com/huangyunkun/jviff/0.0.2/jviff-0.0.2-standalone.jar).

* run it
```
java -jar jviff-0.0.2-standalone.jar -c config.yaml
```
* check the result in your output dir
