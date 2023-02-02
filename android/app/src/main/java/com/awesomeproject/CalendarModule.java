package com.awesomeproject;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.Arguments;
import java.util.Map;
import java.util.HashMap;
import android.util.Log;

public class CalendarModule extends ReactContextBaseJavaModule {
    
    private int eventCount = 0;
    CalendarModule(ReactApplicationContext context) {
        super(context);
    }

    @Override
    public String getName() {
        return "CalendarModule";
    }

    @ReactMethod
    public void createCalendarEvent(Callback callback){
        callback.invoke("Data Return from createCalendarEvent callback");
    }

    @ReactMethod
    public void createCalendarPromise(Promise promise){
        try{
            promise.resolve("Data Return from createCalendarEvent promise");
            eventCount += 1;
            sendEvent(getReactApplicationContext(), "EventCount", eventCount);
        }catch(Exception e){
            promise.reject("Data Reject from createCalendarEvent promise");
        }
    }

    private void sendEvent(ReactContext reactContext, String eventName, int params){
        reactContext
        .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
        .emit(eventName, params);
    }
}
