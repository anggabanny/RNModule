package com.reactnativeblumodule

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.blusdk.BluSdk
import com.blusdk.BluView
import com.blusdk.log.BluLogType
import com.blusdk.webview.BluWebView
import android.view.View

import android.widget.ImageButton
import androidx.appcompat.app.ActionBar


/**
 * Created by Seline on 15/03/2022 11:04
 */
class BluView : AppCompatActivity(), BluWebView.IBluWebViewListener {

  private lateinit var bluView: BluView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.bluview_activity)

    //set custom action bar
    supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
    supportActionBar?.setDisplayShowCustomEnabled(true)
    supportActionBar?.setCustomView(R.layout.custom_action_bar_layout)
    supportActionBar?.elevation = 0F
    val view: View? = supportActionBar?.customView

    view?.findViewById<ImageButton>(R.id.action_bar_back)?.setOnClickListener {
      onBackPressed()
    }

    //set blu webview
    bluView = findViewById(R.id.blu_view)

    BluSdk.initialize(this, BluSdk.ENV_DEV, "app-blu-binusstudent-dev")
    bluView.setWebViewListener(this)
    bluView.setContextParent(this)
  }

  override fun onBackPressed() {
    if (bluView.hasHandleBackPress()) bluView.onBackPressed()
    else super.onBackPressed()
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    bluView.handleActivityResult(requestCode, resultCode, data)
  }

  override fun onRequestPermissionsResult(
    requestCode: Int,
    permissions: Array<out String>,
    grantResults: IntArray
  ) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    bluView.onRequestPermissionsResult(requestCode, permissions, grantResults)
  }

  override fun onDestroy() {
    BluSdk.destroy()
    super.onDestroy()
  }

  override fun bluLog(type: BluLogType, message: String?, error: Throwable?) {
    Log.d("<LOG>", "Type: " + type.toString() + " " + message.toString())
  }

  override fun onClose() {
    TODO("Not yet implemented")
  }

  override fun onLinkageFailed() {
    TODO("Not yet implemented")
  }

}
