package com.example.cinemaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MoviesDetailsActivity extends AppCompatActivity {

    TextView tvTitle,tvPrice;
    Button btnBack,btnBook;
    ImageView img;
    EditText edtName, edtNbOfTickets;
    String time, cinemaType, price;

    private Button dateButton;

    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_details);

        tvTitle = (TextView) findViewById(R.id.textViewMDTitle);
        tvPrice = (TextView) findViewById(R.id.textViewTicketPrice);
        img = (ImageView) findViewById(R.id.moviePoster);
        btnBack = (Button) findViewById(R.id.BackMDButton);
        btnBook = (Button) findViewById(R.id.BookMDButton);
        edtName = (EditText) findViewById(R.id.edtMDFullName);
        edtNbOfTickets = (EditText) findViewById(R.id.edtMDNbOfTickets);

        dateButton = (Button) findViewById(R.id.buttonMDDate);

        initDatePicker();
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });


        Intent intent=getIntent();
        tvTitle.setText(intent.getStringExtra("text1"));
        tvPrice.setText(intent.getStringExtra("text2")+"$");

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int resId = bundle.getInt("text3");
            img.setImageResource(resId);
        }

        time = intent.getStringExtra("text4");
        cinemaType = intent.getStringExtra("text5");
        price = intent.getStringExtra("text2");

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MoviesDetailsActivity.this,HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs",MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();

                Database db = new Database(getApplicationContext(),"cinema", null, 1);

                db.buyTickets(username, edtName.getText().toString(), tvTitle.getText().toString(),Integer.parseInt(edtNbOfTickets.getText().toString()), Integer.parseInt(price)*Integer.parseInt(edtNbOfTickets.getText().toString()), dateButton.getText().toString(),time,cinemaType);
                Toast.makeText(getApplicationContext(),"Your booking is successful!",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MoviesDetailsActivity.this,HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });



    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1 = i1 + 1;
                dateButton.setText(i2 + "/" + i1 + "/" + i);
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_HOLO_DARK;
        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis() + 86480000);
    }
}