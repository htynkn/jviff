-printusage shrinking.output

-dontobfuscate
-dontoptimize

-keepattributes *Annotation*,EnclosingMethod

-keep public class com.huangyunkun.jviff.Runner {
    public static void main(java.lang.String[]);
}

-keep public class com.huangyunkun.jviff.**{*;}
-keep public class com.google.common.io.Resources{*;}
-keep public class org.openqa.selenium.**{*;}
-keep public class org.apache.xerces.**{*;}
-keep public class org.apache.xpath.**{*;}
-keep public class org.apache.commons.logging.impl.**{*;}

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
-dontwarn sun.org.mozilla.javascript.internal.xmlimpl.**
