# === ATRUBUTY DO ZACHOWANIA ===
-keepattributes *Annotation*
-keepattributes Signature
-keepattributes InnerClasses, EnclosingMethod
-keepattributes SourceFile,LineNumberTable

-dontwarn kotlin.**
-dontwarn kotlinx.**
-dontwarn javax.**
-dontwarn sun.misc.**
-dontwarn com.google.**
-dontwarn dagger.**
-dontwarn retrofit2.**
-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn coil.**
-dontwarn androidx.**

-keepclassmembers class * {
    @androidx.compose.runtime.Composable <methods>;
}

-keepnames class * {
    *;
}

-keep class javax.inject.** { *; }
-keep class * extends androidx.lifecycle.ViewModel { <init>(...); *; }
-keep @dagger.hilt.android.lifecycle.HiltViewModel class ** { *; }

# === RETROFIT + GSON ===
-keep class com.google.gson.stream.** { *; }
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer
-keepclassmembers class * {
    @com.google.gson.annotations.* <fields>;
}

-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}
-keep class com.google.gson.reflect.TypeToken
-keep class * extends com.google.gson.reflect.TypeToken
-keep class * implements java.lang.reflect.Type

-keep class kotlinx.coroutines.internal.** { *; }
-keep class kotlinx.coroutines.debug.** { *; }
-keep class kotlinx.coroutines.CoroutineExceptionHandler { *; }
-keep class **._BOUNDARY { *; }
-keep class **.$$r8* { *; }

-keep class androidx.datastore.preferences.** { *; }
-keep class androidx.datastore.core.** { *; }

-keepclassmembers class * {
    @kotlinx.serialization.SerialName <fields>;
}
-if @kotlinx.serialization.Serializable class **
-keepclassmembers class <1> {
    static <1>$Companion Companion;
}
-if @kotlinx.serialization.Serializable class ** { static **$* *; }
-keepclassmembers class <2>$<3> {
    kotlinx.serialization.KSerializer serializer(...);
}

-dontwarn org.junit.**
-dontwarn org.mockito.**
-dontwarn androidx.test.**