package com.vlss.plugins.aep

import android.app.Application
import com.getcapacitor.JSObject
import com.getcapacitor.Plugin
import com.getcapacitor.PluginCall
import com.getcapacitor.PluginMethod
import com.getcapacitor.annotation.CapacitorPlugin

fun JSObject.toMap(): Map<String, Any> {
    val map = mutableMapOf<String, Any>()
    val keys = this.keys()
    while (keys.hasNext()) {
        val key = keys.next()
        val value = this.get(key)
        map[key] = value
    }
    return map
}

@CapacitorPlugin(name = "AdobeAep")
class AdobeAepPlugin : Plugin() {

    private val implementation = AdobeAep()

    @PluginMethod
    fun configure(call: PluginCall) {
        val appId = call.getString("appId")
        if (appId.isNullOrBlank()) {
            call.reject("appId is required")
            return
        }

        implementation.configure(context.applicationContext as Application, appId) {
            val ret = JSObject()
            ret.put("success", true)
            call.resolve(ret)
        }
    }

    /*
    @PluginMethod
    fun trackEvent(call: PluginCall) {
        val xdmJson = call.getObject("xdm") ?: run {
            call.reject("`xdm` must be an object")
            return
        }

        val xdmMap = xdmJson.toMap()
        implementation.trackExperienceEvent(xdmMap)

        val ret = JSObject()
        ret.put("success", true)
        call.resolve(ret)
    }
    */

    @PluginMethod
    fun setConsent(call: PluginCall) {
        val consentValue = call.getString("consent")
        if (consentValue.isNullOrBlank()) {
            call.reject("Missing 'consent' value")
            return
        }

        implementation.setConsent(consentValue)

        val ret = JSObject()
        ret.put("success", true)
        call.resolve(ret)
    }

    @PluginMethod
    fun trackScreenView(call: PluginCall) {
        // Call the implementation method
        implementation.trackScreenView()

        val ret = JSObject()
        ret.put("success", true)
        call.resolve(ret)
    }

    @PluginMethod
    fun startAssurance(call: PluginCall) {
        val url = call.getString("url")
        if (url.isNullOrBlank()) {
            call.reject("Missing Assurance session URL")
            return
        }

        implementation.startAssuranceSession(url)
        val ret = JSObject()
        ret.put("success", true)
        call.resolve(ret)
    }
}
