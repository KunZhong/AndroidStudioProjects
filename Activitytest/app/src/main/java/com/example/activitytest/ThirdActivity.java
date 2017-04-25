package com.example.activitytest;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ThirdActivity extends BaseActivity {

    private String[] data = {"Apple","Banana","Banana","Banana",
            "Banana","Banana","Banana","Banana","Banana","Banana",
            "Banana","Banana","Banana","Banana","Banana","Banana",
            "Banana","Banana","Banana","Banana","Banana","Banana",};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                ThirdActivity.this,android.R.layout.simple_list_item_1,data);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.finishAll();
    }
}
