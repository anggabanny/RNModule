package com.awesomeproject.KotlinVideoStreaming

import android.content.Intent
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod


class StreamManager(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

    override fun getName(): String {
        return "KotlinVideoStream"
    }

    @ReactMethod
    fun playVideoStream(videoTitle: String, videoUrl: String) {
        val intent = Intent(reactApplicationContext, VideoStreamingActivity::class.java)
        intent.putExtra("videoTitle", videoTitle)
        intent.putExtra("videoUrl", videoUrl)
        reactApplicationContext.startActivity(intent)
    }

}