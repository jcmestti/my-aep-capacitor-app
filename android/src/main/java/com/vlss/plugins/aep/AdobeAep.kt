package com.vlss.plugins.aep

import com.adobe.marketing.mobile.Edge
import com.adobe.marketing.mobile.edge.identity.Identity
import com.adobe.marketing.mobile.edge.consent.Consent
import com.adobe.marketing.mobile.Assurance
import com.adobe.marketing.mobile.Lifecycle
import com.adobe.marketing.mobile.UserProfile
import com.adobe.marketing.mobile.Signal
import com.adobe.marketing.mobile.LoggingMode
import com.adobe.marketing.mobile.MobileCore
import android.app.Application
import android.util.Log
import com.adobe.marketing.mobile.ExperienceEvent


class AdobeAep {

    fun configure(application: Application, appId: String, onComplete: () -> Unit) {
        MobileCore.setApplication(application)
        MobileCore.setLogLevel(LoggingMode.DEBUG)

        try {
            // Register ALL necessary extensions
            val extensions = listOf(
                Identity.EXTENSION,
                Consent.EXTENSION,
                Edge.EXTENSION,          // ✅ Added Edge
                Lifecycle.EXTENSION,
                UserProfile.EXTENSION,
                Signal.EXTENSION,
                Assurance.EXTENSION
            )

            MobileCore.registerExtensions(extensions) {
                Log.d("AdobeAep", "✅ AEP SDK initialized with Edge")
                MobileCore.configureWithAppID(appId)
                MobileCore.lifecycleStart(null)
                onComplete()
            }

        } catch (e: Exception) {
            Log.e("AdobeAep", "❌ Failed to register extensions", e)
        }
    }

    /*
    fun trackExperienceEvent(xdmData: Map<String, Any>) {
        Log.d("AEP_EVENT", "XDM Payload: ${xdmData.toString()}")
        val event = com.adobe.marketing.mobile.ExperienceEvent.Builder()
            .setXdmSchema(xdmData)
            .build()
        
        Edge.sendEvent(event, null)
    }
    */
    
    fun trackScreenView() {
    val xdmData = mapOf(
        "eventType" to "screen_view",
        "timestamp" to "2025-08-15T01:46:38Z",
        "_telefonicavivo" to mapOf(
            "eventDetails" to mapOf(
                "screenName" to "screenName",
                "event_name" to "event_name",
                "item_list_name" to "item_list_name",
                "item_variant" to "item_variant",
                "item_name" to "item_name",
                "audience_expression_1" to "audience_expression_1"
            ),
            "vivoAppUser" to mapOf(
                "vivoCustomerID" to "5b51cf71221d3f5da3f2d58eb64adceda85c91c6d39689d8f2159c2d82129f89",
                "msisdn" to "406078d03983199dbc82b574e1b6dbf354acd01f4f3ffec17064ba8c16b16aag"
            )
        )
    )

        val event = com.adobe.marketing.mobile.ExperienceEvent.Builder()
            .setXdmSchema(xdmData)
            .build()

        Edge.sendEvent(event, null)
    }

    fun setConsent(value: String) {
        val consentMap = mapOf(
            "consents" to mapOf(
                "collect" to mapOf(
                    "val" to value
                )
            )
        )
        Consent.update(consentMap)
    }

    fun startAssuranceSession(sessionUrl: String) {
        Assurance.startSession(sessionUrl)
    }
}

