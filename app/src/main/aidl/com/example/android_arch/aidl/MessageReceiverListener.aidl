// MessageReceiverListener.aidl
package com.example.android_arch.aidl;
import com.example.android_arch.aidl.origin.Message;
interface MessageReceiverListener {
    void onReceiveMessage(in Message message);
}