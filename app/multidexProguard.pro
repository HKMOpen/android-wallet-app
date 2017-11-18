-keep com.zyntauri.gdnet.materialsteps.ticketing.** { *; }
-keep class com.zyntauri.gdnet.materialactivities.TicketingDetail.** { *; }
-keep com.zyntauri.gdnet.dialog.** { *; }
-keep com.zyntauri.gdnet.materialactivities.uievents.PaymentEvent{*;}
-keep com.zyntauri.gdnet.Appliance
-keep com.bumptech.glide.integration.okhttp3.OkHttpLibraryGlideModule
-keep com.zyntauri.gdnet.coverflows.flex.**
-keep com.zyntauri.gdnet.materialsteps.vipmembership.**
-keep android.icu.util.Calendar
-keep class com.zyntauri.gdnet.materialactivities.InitialPage
-keep class com.zyntauri.gdnet.materialactivities.InitialPage.** { *; }
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
-keep com.zyntauri.gdnet.** { *; }