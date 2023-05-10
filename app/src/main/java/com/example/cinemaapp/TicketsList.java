package com.example.cinemaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class TicketsList extends AppCompatActivity {

    private String[][] order_details = {};

    Button btn;
    ListView lst;
    ArrayList list;
    SimpleAdapter sa;
    HashMap<String,String> item;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tickets_list);

        lst = (ListView) findViewById(R.id.ListViewTL);
        btn = (Button) findViewById(R.id.BackTLButton);
        tv = (TextView) findViewById(R.id.textViewTLTitle);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TicketsList.this,HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        Database db = new Database(getApplicationContext(),"cinema",null,1);
        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs",MODE_PRIVATE);
        String username = sharedPreferences.getString("username","").toString();
        ArrayList dbData = db.getOrderData(username);

        //tv.setText(dbData.get(0).toString());

        order_details = new String[dbData.size()][];
        for(int i=0;i<order_details.length; i++) {
            order_details[i] = new String[5];
            String arrData = dbData.get(i).toString();
            String[] strData = arrData.split(java.util.regex.Pattern.quote("$"));
            order_details[i][0] = strData[1];
            order_details[i][1] = strData[0];
            order_details[i][2] = strData[4];
            order_details[i][3] = strData[5];
            order_details[i][4] = "Tickets: " + strData[2] + "\n" + "Total Price: " + strData[3]+"$";
        }

        list = new ArrayList();
        for (int i=0; i<order_details.length; i++){
            item = new HashMap<String,String>();
            item.put("line1",order_details[i][0]);
            item.put("line2",order_details[i][1]);
            item.put("line3",order_details[i][2]);
            item.put("line4",order_details[i][3]);
            item.put("line5",order_details[i][4]);
            list.add(item);
        }

        sa = new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e}
        );
        lst.setAdapter(sa);
    }
}