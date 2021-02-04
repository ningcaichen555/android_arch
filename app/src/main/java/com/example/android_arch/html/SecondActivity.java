package com.example.android_arch.html;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.android_arch.R;
import com.example.android_arch.html.entity.Room;

import java.util.ArrayList;


public class SecondActivity extends Activity {

    private static final String TAG = "SecondActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_second);

        int hotelId = getIntent().getIntExtra("HotelId", -1);
        String hotelName = getIntent().getStringExtra("HotelName");

        ArrayList<Room> rooms = (ArrayList<Room>)getIntent().getSerializableExtra("Rooms");

        TextView tvHotelName = (TextView)findViewById(R.id.tvHotelName);
        tvHotelName.setText(hotelName);
    }
}
