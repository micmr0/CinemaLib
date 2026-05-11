package com.micmr0.cinemalib

import android.content.Context
import java.util.Locale

fun Context.withLocale(locale: Locale): Context {
    val config = resources.configuration
    config.setLocale(locale)
    return createConfigurationContext(config)
}
