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

* then build a uberjar by yourself
```
./gradlew uberjar
```

* run it
```
java -jar jviff-0.0.1-SNAPSHOT-uber.jar -c config.yaml
```

* check the result in your output dir
