// IMessageInterface.aidl
package com.example.android_arch.aidl;
import com.example.android_arch.aidl.origin.entity.Message;
import com.example.android_arch.MessageReceiverListener;
interface IMessageInterface {
    void sendMessage(in Message message);

    void registerMessageReceiveListener(MessageReceiverListener listener);

    void unRegisterMessageReceiveListener(MessageReceiverListener listener);
}