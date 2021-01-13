// MessageReceiverListener.aidl
package com.example.android_arch;
import com.example.android_arch.aidl.origin.entity.Message;
interface MessageReceiverListener {
    void onReceiveMessage(in Message message);
}