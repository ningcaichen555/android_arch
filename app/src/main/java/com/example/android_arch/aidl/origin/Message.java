package com.example.android_arch.aidl.origin;


import android.os.Parcel;
import android.os.Parcelable;

public class Message implements Parcelable {
    private String content;
    private boolean isSendSuccess;
    public Message(){

    }
    protected Message(Parcel in) {
        content = in.readString();
        isSendSuccess = in.readByte() != 0;
    }

    public static final Creator<Message> CREATOR = new Creator<Message>() {
        @Override
        public Message createFromParcel(Parcel in) {
            return new Message(in);
        }

        @Override
        public Message[] newArray(int size) {
            return new Message[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(content);
        dest.writeByte(isSendSuccess ? (byte) 0 : (byte) 1);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isSendSuccess() {
        return isSendSuccess;
    }

    public void setSendSuccess(boolean sendSuccess) {
        isSendSuccess = sendSuccess;
    }
}
