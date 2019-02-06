    # This is a configuration file for ProGuard.
    # http://proguard.sourceforge.net/index.html#manual/usage.html

    # Optimizations: If you don't want to optimize, use the
    # proguard-android.txt configuration file instead of this one, which
    # turns off the optimization flags.  Adding optimization introduces
    # certain risks, since for example not all optimizations performed by
    # ProGuard works on all versions of Dalvik.  The following flags turn
    # off various optimizations known to have issues, but the list may not
    # be complete or up to date. (The "arithmetic" optimization can be
    # used if you are only targeting Android 2.0 or later.)  Make sure you
    # test thoroughly if you go this route.
    -optimizations !field/*,!class/merging/*
    -optimizationpasses 1
    -dontskipnonpubliclibraryclassmembers
    -dontskipnonpubliclibraryclasses
    -allowaccessmodification
    -repackageclasses ''
    -dontpreverify
    -verbose


    -dontwarn com.google.firebase.appindexing.**
    -dontwarn com.android.installreferrer.api.**
    -keep class com.google.android.gms.ads.identifier.AdvertisingIdClient {
     com.google.android.gms.ads.identifier.AdvertisingIdClient$Info getAdvertisingIdInfo(android.content.Context);
     }
     -keep class com.google.android.gms.ads.identifier.AdvertisingIdClient$Info {
      java.lang.String getId(); boolean isLimitAdTrackingEnabled();
      }

    -dontwarn org.jetbrains.anko.internals.AnkoInternals
    -keep public class * extends android.app.Activity

    #=========== Base Android ===========
    -keep class * implements android.os.Parcelable {
      public static final android.os.Parcelable$Creator *;
    }

    -keepnames class * implements android.os.Parcelable {
        public static final ** CREATOR;
    }


    ## For native methods, see http://proguard.sourceforge.net/manual/examples.html#native
    -keepclasseswithmembernames class * {
        native <methods>;
    }


    # keep setters in Views so that animations can still work.
    # see http://proguard.sourceforge.net/manual/examples.html#beans
    -keepclassmembers public class * extends android.view.View {
       void set*(***);
       *** get*();
    }

    # We want to keep methods in Activity that could be used in the XML attribute onClick
    -keepclassmembers class * extends android.app.Activity {
       public void *(android.view.View);
    }

    #=========== Base Android ===========

    #=========== Fabric ===========
    -keepattributes *Annotation*
    -keepattributes LineNumberTable
    #=========== Fabric ===========

    #=========== AndroidX ===========
    -dontwarn androidx.media.**
    #=========== AndroidX ===========

    #=========== Glide ===========
    -keep public class * implements com.bumptech.glide.module.GlideModule
    -keep public class * extends com.bumptech.glide.module.AppGlideModule
    -keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
      **[] $VALUES;
      public *;
    }
    #=========== Glide ===========


    #=========== Logging ===========
    -assumenosideeffects class android.util.Log {
        public static boolean isLoggable(java.lang.String, int);
        public static int v(...);
        public static int i(...);
        public static int w(...);
        public static int d(...);
        public static int e(...);
    }

    -assumenoexternalsideeffects class java.lang.StringBuilder {
        public java.lang.StringBuilder();
        public java.lang.StringBuilder(int);
        public java.lang.StringBuilder(java.lang.String);
        public java.lang.StringBuilder append(java.lang.Object);
        public java.lang.StringBuilder append(java.lang.String);
        public java.lang.StringBuilder append(java.lang.StringBuffer);
        public java.lang.StringBuilder append(char[]);
        public java.lang.StringBuilder append(char[], int, int);
        public java.lang.StringBuilder append(boolean);
        public java.lang.StringBuilder append(char);
        public java.lang.StringBuilder append(int);
        public java.lang.StringBuilder append(long);
        public java.lang.StringBuilder append(float);
        public java.lang.StringBuilder append(double);
        public java.lang.String toString();
    }

    -assumenoexternalreturnvalues public final class java.lang.StringBuilder {
        public java.lang.StringBuilder append(java.lang.Object);
        public java.lang.StringBuilder append(java.lang.String);
        public java.lang.StringBuilder append(java.lang.StringBuffer);
        public java.lang.StringBuilder append(char[]);
        public java.lang.StringBuilder append(char[], int, int);
        public java.lang.StringBuilder append(boolean);
        public java.lang.StringBuilder append(char);
        public java.lang.StringBuilder append(int);
        public java.lang.StringBuilder append(long);
        public java.lang.StringBuilder append(float);
        public java.lang.StringBuilder append(double);
    }
    #=========== Logging ===========

    #=========== V13 ===========
    -dontwarn android.support.v13.**
    #=========== V13 ===========

    -dontwarn kotlin.reflect.jvm.internal.**
    -keep class kotlin.reflect.jvm.internal.** { *; }

    #===========  ===========