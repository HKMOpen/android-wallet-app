# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\dev\Android\sdk/tools/proguard/proguard-android.txt
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
-keep interface org.parceler.Parcel
-keep @org.parceler.Parcel class * { *; }
-keep class **$$Parcelable { *; }
-keep class org.parceler.Parceler$$Parcels



-keep @interface your.package.path.deeplink.** { *; }
-keepclasseswithmembers class * {
    @your.package.path.deeplink.* <methods>;
}

# Add any project specific keep options here:
-keep class com.zyntauri.gdnet.topstac.base.RegistrationBas.**{ protected void bindControlLocation(...); }
# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

#-keepnames com.zyntauri.gdnet.materialsteps.ticketing.**
#-keepnames com.zyntauri.gdnet.coverflows.flex.**
-dontwarn com.balram.locker.**
-keepnames public class * extends io.realm.RealmObject
-keep class io.realm.** { *; }
-keep class com.hkm.ezwebview.Util.Fx9C
-keep class com.hkm.ezwebview.webviewclients.**{*;}
-keep class com.hkm.ezwebview.webviewclients.IstPaymentGatewayClient{*;}
-keepclassmembernames class com.hkm.ezwebview.webviewclients.** {*;}
-dontwarn javax.**
-dontwarn io.realm.**
-keep class !android.support.v7.internal.view.menu.**,android.support.** {*;}
-keep class !android.support.v7.internal.widget.**,android.support.** {*;}
-keep public class com.google.android.gms.ads.** {
   public *;
}
-keep class org.ocpsoft.prettytime.i18n.**
-keep public class com.google.ads.** {
   public *;
}
-dontwarn com.marshalchen.ultimaterecyclerview.**
-keep class com.zyntauri.gdnet.materialactivities.uievents.PaymentEvent
# It's OK not to exist SyncObjectServerFacade in base library.
#-dontnote io.realm.internal.SyncObjectServerFacade

-keep class io.realm.annotations.RealmModule
-keep @io.realm.annotations.RealmModule class *

-keep class io.realm.internal.Keep
-keep @io.realm.internal.Keep class * { *; }

-keep class io.realm.internal.KeepMember
-keep @io.realm.internal.KeepMember class * { @io.realm.internal.KeepMember *; }

-dontwarn javax.**
#-dontwarn io.realm.**
#-keep class io.realm.RealmCollection
#-keep class io.realm.OrderedRealmCollection
#-keepclasseswithmembernames class io.realm.** {
#    native <methods>;
#}

-dontnote rx.Observable
-dontnote android.security.KeyStore
-dontwarn okio.Okio
-dontwarn okio.DeflaterSink

-dontnote com.android.org.conscrypt.SSLParametersImpl
-dontnote org.apache.harmony.xnet.provider.jsse.SSLParametersImpl
-dontnote sun.security.ssl.SSLContextImpl

-dontwarn de.madcyph3r.materialnavigationdrawer.ripple.**

-keep public class com.google.ads.mediation.** { public *; }

# Proguard config for project using GMS

-keepnames @com.google.android.gms.common.annotation.KeepName class  com.google.android.gms.**,  com.google.ads.**

-keepclassmembernames class com.google.android.gms.**,  com.google.ads.** {
    @com.google.android.gms.common.annotation.KeepName *;
}

# Called by introspection
-keep class com.google.android.gms.**, com.google.ads.** extends java.util.ListResourceBundle {
    protected java.lang.Object[][] getContents();
}


# This keeps the class name as well as the creator field, because the
# "safe parcelable" can require them during unmarshalling.
-keepnames class com.google.android.gms.**,  com.google.ads.**  implements android.os.Parcelable {
    public static final ** CREATOR;
}

-keep public class com.google.android.gms.* { public *; }
-dontwarn com.google.android.gms.**

-keep class * extends java.util.ListResourceBundle {
    protected Object[][] getContents();
}

-keep public class com.google.android.gms.common.internal.safeparcel.SafeParcelable {
    public static final *** NULL;
}

-keepnames @com.google.android.gms.common.annotation.KeepName class *
-keepclassmembernames class * {
    @com.google.android.gms.common.annotation.KeepName *;
}

-keepnames class * implements android.os.Parcelable {
    public static final ** CREATOR;
}
# com.google.android.gms.auth.api.signin.SignInApiOptions$Builder
# references these classes but no implementation is provided.
-dontnote com.facebook.Session
-dontnote com.facebook.FacebookSdk
-keepnames class com.facebook.Session {}
-keepnames class com.facebook.FacebookSdk {}

# android.app.Notification.setLatestEventInfo() was removed in
# Marsmallow, but is still referenced (safely)
-dontwarn com.google.android.gms.common.GooglePlayServicesUtil

-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
    **[] $VALUES;
    public *;
}
-keep class com.bumptech.glide.integration.okhttp.OkHttpGlideModule


# Retain generated class which implement Unbinder.
-keep public class * implements butterknife.Unbinder { public <init>(**, android.view.View); }

# Prevent obfuscation of types which use ButterKnife annotations since the simple name
# is used to reflectively look up the generated ViewBinding.
-keep class butterknife.*
-keepclasseswithmembernames class * { @butterknife.* <methods>; }
-keepclasseswithmembernames class * { @butterknife.* <fields>; }

#-keep class com.nguyenhoanglam.imagepicker.**
#-keepclassmembers class com.nguyenhoanglam.imagepicker.**{ *;}
-dontwarn okio.**
-dontwarn com.squareup.okhttp.**
-keep class com.squareup.okhttp.** { *; }
-keep interface com.squareup.okhttp.** { *; }

-dontwarn com.dmcapps.**
-keep class com.dmcapps.** { *; }
-keep interface com.dmcapps.** { *; }

-dontwarn retrofit.**
-dontwarn retrofit.appengine.UrlFetchClient
-keepclasseswithmembers class retrofit.** { *; }
#-keep class it.sephiroth.android.library.bottomnavigation.** { *; }
#-keepclasseswithmembers class it.sephiroth.** { *; }
-keepclasseswithmembers class jp.so.cyberagent.** { *; }
-keepclasseswithmembers class jp.shts.** { *; }
-dontwarn jp.sht
-keepclasseswithmembers class me.angrybyte.** { *; }
-dontwarn me.angrybyte
#-keep class uk.co.senab.photoview.PhotoView { *; }
-dontwarn uk.co.senab.photoview.PhotoView
-keep class uz.shift.colorpicker.LineColorPicker { *; }
-dontwarn uz.shift.colorpicker.LineColorPicker
-keepclasseswithmembers class eu.livotov.labs.** { *; }
-dontwarn eu.livotov.lab.**
-keepclasseswithmembers class in.srain.cube.** { *; }
-dontwarn in.srain.cube.**
-keep class org.ocpsoft.prettytime.i18n.**
-keep class com.cloudinary.** { *; }
-dontwarn org.codehaus.**
#-keepclasseswithmembers class com.nightonke.blurlockview.BlurLockView.**
-dontnote retrofit2.Platform
-dontnote retrofit2.Platform$IOS$MainThreadExecutor
-dontwarn retrofit2.Platform$Java8
#-keep class hx520.auction.core.api.** {*;}
#-dontwarn hx520.auction.core.api.**
-dontwarn android.security.**
-dontwarn com.google.common.**
-keep class com.google.android.gms.internal.** { *; }
-keep class * extends java.util.ListResourceBundle {
    protected Object[][] getContents();
}
-keep public class com.google.android.gms.common.internal.safeparcel.SafeParcelable {
    public static final *** NULL;
}
-keepnames @com.google.android.gms.common.annotation.KeepName class *
-keepclassmembernames class * {
    @com.google.android.gms.common.annotation.KeepName *;
}
-keepnames class * implements android.os.Parcelable {
    public static final ** CREATOR;
}
-keep class retrofit2.** { *; }
-dontwarn retrofit2.**
#-keep class okhttp3.** { *; }
#-dontwarn class okhttp3.**
-keep public class com.google.gson
-keep class com.google.common.** { *; }
-keepclasseswithmembers class * {
    @retrofit.http.* <methods>;
}
-dontwarn icepick.**
-keep class icepick.** { *; }
-keep class **$$Icepick { *; }
-keepclasseswithmembernames class * {
    @icepick.* <fields>;
}
-keep class com.airbnb.deeplinkdispatch.** { *; }
-keepclasseswithmembers class * {
     @com.airbnb.deeplinkdispatch.DeepLink <methods>;
}
-keepclassmembers class * extends com.strongloop.android.loopback.Model {
    public <methods>;
}
-assumenosideeffects class * implements org.slf4j.Logger {
    public *** trace(...);
    public *** debug(...);
    public *** info(...);
    public *** warn(...);
    public *** error(...);
}
-dontwarn javax.jcr.**
-dontwarn org.slf4j.**
# Parcel library
-keep interface org.parceler.Parcel
-keep @org.parceler.Parcel class * { *; }
-keep class **$$Parcelable { *; }
-keep class org.parceler.Parceler$$Parcels
# Stripe library
-keep class com.stripe.** { *; }
-keepclassmembers class ** {
  @com.mindorks.placeholderview.annotations.** <methods>;
}
-keepclassmembers class ** {
    @com.hwangjr.rxbus.annotation.Subscribe public *;
    @com.hwangjr.rxbus.annotation.Produce public *;
    @com.hwangjr.rxbus.annotation.Tag public *;
}
-keep class com.hwangjr.rxbus.thread.EventThread{
    *;
}
-keepclassmembers class ** {
    @com.squareup.otto.Subscribe public *;
    @com.squareup.otto.Produce public *;
}
-dontwarn rx.**
#-dontwarn rx.internal.*
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}
-dontwarn sun.misc.Unsafe
-keep class sun.misc.Unsafe { *; }
-keepnames class * { @icepick.State *;}
#-dontwarn com.squareup.haha.guava.**
#-dontwarn com.squareup.haha.perflib.**
#-dontwarn com.squareup.haha.trove.**
-dontwarn com.squareup.leakcanary.**
#-keep class com.squareup.haha.** { *; }
-keep class com.squareup.leakcanary.** { *; }
# Note that you cannot just include these flags in your own
# configuration file; if you are including this file, optimization
# will be turned off. You'll need to either edit this file, or
# duplicate the contents of this file and remove the include of this
# file from your project's proguard.config path property.
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application {
    <init>();
    void attachBaseContext(android.content.Context);
}
-keep public class * extends android.app.Instrumentation {
    <init>();
}
-keep public class * extends android.app.backup.BackupAgent {
    <init>();
}
-keep public class * extends java.lang.annotation.Annotation {
    <fields>;
    <methods>;
}
-keep class com.android.tools.fd.** {
    <fields>;
    <methods>;
}
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgent
-keep public class * extends android.preference.Preference
-keep public class * extends android.support.v4.app.Fragment
-keep public class * extends android.app.Fragment
-keep public class com.android.vending.licensing.ILicensingService
# MAS Data Models
-keep class com.mapbox.services.directions.v4.models.** { *; }
-keep class com.mapbox.services.directions.v5.models.** { *; }
-keep class com.mapbox.services.geocoding.v5.models.** { *; }
-keep class com.mapbox.services.android.** { *; }
# ======================================
-dontwarn com.mapbox.services.android.**
-dontwarn com.mapbox.services.geocoding.v5.models.**
-dontwarn com.mapbox.services.directions.v5.models.**
-dontwarn com.mapbox.services.directions.v4.models.**
# For native methods, see http://proguard.sourceforge.net/manual/examples.html#native
-keepclasseswithmembernames class * {
    native <methods>;
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}
-keep public class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
    public void set*(...);
}
# For enumeration classes, see http://proguard.sourceforge.net/manual/examples.html#enumerations
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keepclassmembers class * implements android.os.Parcelable {
    static ** CREATOR;
    static android.os.Parcelable$Creator CREATOR;
}
-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
-keepclassmembers class **.R$* {
    public static <fields>;
}

-dontwarn com.onesignal.**

-keep class com.google.android.gms.common.api.GoogleApiClient {
    void connect();
    void disconnect();
}
### OKHTTP
# Platform calls Class.forName on types which do not exist on Android to determine platform.
-dontnote okhttp3.internal.Platform
### OKIO
# java.nio.file.* usage which cannot be used at runtime. Animal sniffer annotation.
-dontwarn okio.Okio
# JDK 7-only method which is @hide on Android. Animal sniffer annotation.
-dontwarn okio.DeflaterSink

-keep public interface android.app.OnActivityPausedListener {*;}
-keep class com.onesignal.ActivityLifecycleListenerCompat** {*;}
-keep class com.zyntauri.gdnet.coverflows.flex.TimeLineRec { <init>(...); }
-keep class com.onesignal.shortcutbadger.impl.AdwHomeBadger { <init>(...); }
-keep class com.onesignal.shortcutbadger.impl.ApexHomeBadger { <init>(...); }
-keep class com.onesignal.shortcutbadger.impl.AsusHomeLauncher { <init>(...); }
-keep class com.onesignal.shortcutbadger.impl.DefaultBadger { <init>(...); }
-keep class com.onesignal.shortcutbadger.impl.HuaweiHomeBadger { <init>(...); }
-keep class com.onesignal.shortcutbadger.impl.LGHomeBadger { <init>(...); }
-keep class com.onesignal.shortcutbadger.impl.NewHtcHomeBadger { <init>(...); }
-keep class com.onesignal.shortcutbadger.impl.NovaHomeBadger { <init>(...); }
-keep class com.onesignal.shortcutbadger.impl.OPPOHomeBader { <init>(...); }
-keep class com.onesignal.shortcutbadger.impl.SamsungHomeBadger { <init>(...); }
-keep class com.onesignal.shortcutbadger.impl.SonyHomeBadger { <init>(...); }
-keep class com.onesignal.shortcutbadger.impl.XiaomiHomeBadger { <init>(...); }
-keep class com.onesignal.shortcutbadger.impl.ZukHomeBadger { <init>(...); }
#fabric
-keep public class * extends java.lang.Exception
#-keepresourcexmlelements manifest/application/meta-data@name=io.fabric.ApiKey
-dontwarn com.crashlytics.**
-keep class com.crashlytics.** { *; }

# The support library contains references to newer platform versions.
# Don't warn about those in case this app is linking against an older
# platform version.  We know about them, and they are safe.
-dontwarn android.support.**
# This is a configuration file for ProGuard.
# http://proguard.sourceforge.net/index.html#manual/usage.html
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-verbose
-dontpreverify
# For using GSON @Expose annotation
-keepattributes Signature, *Annotation*, EnclosingMethod, JNINamespace, CalledByNative, SourceFile,LineNumberTable
# If you want to enable optimization, you should include the
# following:
-optimizationpasses 4
-overloadaggressively
-useuniqueclassmembernames
-optimizations !code/simplification/arithmetic,!code/simplification/cast,!field/*,!class/merging/
#-repackageclasses 'com.gallero.go'
#-allowaccessmodification
-dump build/outputs/logs/class_files.txt
-printseeds build/outputs/logs/seeds.txt
-printusage build/outputs/logs/unused.txt
-printmapping build/outputs/logs/mapping.txt