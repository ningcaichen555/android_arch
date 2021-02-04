package com.example.android_arch.html;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.android_arch.R;
import com.example.android_arch.html.entity.Room;

import java.util.ArrayList;


public class FirstActivity extends Activity {

    private static final String TAG = "FirstActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_first);

        findViewById(R.id.btnGotoSecond).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                intent.putExtra("HotelId", 44);
                intent.putExtra("HotelName", "郭郭大酒店");

                ArrayList<Room> rooms = new ArrayList<Room>();
                rooms.add(new Room("大床房", 100));
                rooms.add(new Room("双床房", 200));
                intent.putExtra("Rooms", rooms);

                startActivity(intent);
            }
        });
    }
}
