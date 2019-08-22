package com.eleganzit.farmingpartner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.eleganzit.farmingpartner.adapter.NotificationsAdapter;
import com.eleganzit.farmingpartner.model.NotificationsData;

import java.util.ArrayList;

public class NotificationsActivity extends AppCompatActivity {
    RecyclerView rc_notifications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        rc_notifications=findViewById(R.id.rc_notifications);

        NotificationsData notificationsData=new NotificationsData("","","");

        ArrayList<NotificationsData> arrayList=new ArrayList<>();

        arrayList.add(notificationsData);
        arrayList.add(notificationsData);
        arrayList.add(notificationsData);
        arrayList.add(notificationsData);
        arrayList.add(notificationsData);
        arrayList.add(notificationsData);
        arrayList.add(notificationsData);
        arrayList.add(notificationsData);
        arrayList.add(notificationsData);
        rc_notifications.setAdapter(new NotificationsAdapter(arrayList,NotificationsActivity.this));
    }
}
