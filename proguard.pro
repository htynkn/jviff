-printusage shrinking.output

-dontobfuscate
-dontoptimize

-keepattributes *Annotation*,EnclosingMethod

-keep public class com.huangyunkun.jviff.Runner {
    public static void main(java.lang.String[]);
}

-keep public class com.huangyunkun.jviff.**{*;}
-keep public class com.google.common.io.Resources{*;}
-keep public class org.slf4j.Logger{*;}
-keep public class org.apache.xerces.parsers.*{*;}
-keep public class org.apache.xerces.impl.**{*;}
-keep public class org.apache.xerces.jaxp.**{*;}
-keep public class org.openqa.selenium.By{*;}
-keep public class org.openqa.selenium.firefox.internal.**{*;}
-keep public class org.apache.xpath.jaxp.XPathFactoryImpl{*;}
-keep public class org.apache.commons.logging.impl.**{*;}
-keep public class org.thymeleaf.standard.expression.**{*;}
-keep public class org.thymeleaf.processor.attr.**{*;}
-keep class com.sun.jna.** {*;}

-keepclassmembers class * extends com.sun.jna.** {
    <fields>;
    <methods>;
}

-keepclassmembers,allowoptimization enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

-dontwarn  org.jboss.netty.**
-dontwarn  org.openqa.selenium.**
-dontwarn  javax.xml.**
-dontwarn  javax.swing.**
-dontwarn  javax.imageio.**
-dontwarn  com.sun.**
-dontwarn  com.thoughtworks.selenium.**
-dontwarn  net.sf.cglib.**
-dontwarn  org.apache.xerces.**
-dontwarn  org.reflections.**
-dontwarn  com.gargoylesoftware.**
-dontwarn  javassist.util.**
-dontwarn  org.apache.commons.**
-dontwarn  java.util.**
-dontwarn  sun.util.xml.**
-dontwarn org.apache.http.impl.auth.**
-dontwarn com.google.common.**
-dontwarn org.thymeleaf.**
-dontwarn sun.org.mozilla.javascript.internal.xmlimpl.**
-dontwarn  org.eclipse.jetty.**
