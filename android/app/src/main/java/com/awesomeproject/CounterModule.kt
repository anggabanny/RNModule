package com.awesomeproject

import com.facebook.react.bridge.*
import com.facebook.react.modules.core.DeviceEventManagerModule
import android.util.Log

class CounterModule(context: ReactApplicationContext) : ReactContextBaseJavaModule(context) {

    private var eventCount = 0

    override fun getName(): String {
        return "CounterModule"
    }

    @ReactMethod
    fun createCounterEvent(callback: Callback) {
        callback.invoke("Data Return from createCounterEvent callback")
    }

    @ReactMethod
    fun createCounterPromise(promise: Promise) {
        try {
            promise.resolve("Data Return from createCounterEvent promise")
            eventCount += 1
            sendEvent(reactContext, "EventCount", eventCount)
        } catch (e: Exception) {
            promise.reject("Data Reject from createCounterEvent promise")
        }
    }

    private fun sendEvent(reactContext: ReactContext, eventName: String, params: Int) {
        reactContext
            .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter::class.java)
            .emit(eventName, params)
    }
}