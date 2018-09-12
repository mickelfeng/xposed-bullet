# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Develop/android/android-sdk-linux/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
-dontskipnonpubliclibraryclasses
-dontpreverify
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
-classobfuscationdictionary ../proguard/keywords-random.txt
-packageobfuscationdictionary ../proguard/keywords-random.txt
-obfuscationdictionary ../proguard/keywords-random.txt
-overloadaggressively

-dontwarn **

# Xposed
-keep class * implements de.robv.android.xposed.IXposedHookLoadPackage { public *;}
-keep class * implements de.robv.android.xposed.IXposedHookZygoteInit { public *;}
-keep class * implements de.robv.android.xposed.IXposedHookInitPackageResources { public *;}

#-keepnames class * implements java.io.Serializable
-keep public class * implements java.io.Serializable {
        public *;
}

#-dontobfuscate

-keep class * implements android.os.Parcelable {
public static final android.os.Parcelable$Creator *;
}

# xUtils
-keep class * extends java.lang.annotation.Annotation { *; }

-dontshrink

