package com.vlss.plugins.aep

import android.app.Application
import android.util.Log
import com.adobe.marketing.mobile.Edge
import com.adobe.marketing.mobile.ExperienceEvent
import com.adobe.marketing.mobile.Identity
import com.adobe.marketing.mobile.Lifecycle
import com.adobe.marketing.mobile.LoggingMode
import com.adobe.marketing.mobile.MobileCore
import com.adobe.marketing.mobile.Signal
import com.adobe.marketing.mobile.UserProfile

class AdobeAep {
    fun configure(application: Application, appId: String, onComplete: () -> Unit) {
        MobileCore.setApplication(application)
        MobileCore.setLogLevel(LoggingMode.DEBUG)

        val extensions = listOf(
            Lifecycle.EXTENSION,
            Signal.EXTENSION,
            Identity.EXTENSION,
            UserProfile.EXTENSION
        )

        MobileCore.registerExtensions(extensions) {
            Log.d("AdobeAep", "AEP SDK initialized")
            MobileCore.configureWithAppID(appId)
            MobileCore.lifecycleStart(mapOf("contextDataKey" to "contextDataVal"))
            onComplete()
        }
    }

    fun trackExperienceEvent(xdmData: Map<String, Any>) {
        val event = ExperienceEvent.Builder()
            .setXdmSchema(xdmData)
            .build()

        Edge.sendEvent(event, null)
    }
}
