package sg.edu.rp.c346.id21020263.ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etSong;
    EditText etSingers;
    EditText etYear;
    RadioGroup stars;
    RadioButton oneStar;
    RadioButton twoStar;
    RadioButton threeStar;
    RadioButton fourStar;
    RadioButton fiveStar;
    Button insert;
    Button display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etSong = findViewById(R.id.etSong);
        etSingers = findViewById(R.id.etSingers);
        etYear = findViewById(R.id.etYear);
        stars = findViewById(R.id.Ratings);
        oneStar = findViewById(R.id.rb1);
        twoStar = findViewById(R.id.rb2);
        threeStar = findViewById(R.id.rb3);
        fourStar = findViewById(R.id.rb4);
        fiveStar = findViewById(R.id.rb5);
        insert = findViewById(R.id.buttonInsert);
        display = findViewById(R.id.buttonDisplay);

        //
        ArrayList<Song> al = new ArrayList<Song>();
        ArrayAdapter<Song> aa = new ArrayAdapter<Song>(this, android.R.layout.simple_list_item_1, al);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String songTitle = etSong.getText().toString();
                String singers = etSingers.getText().toString();
                String year = etYear.getText().toString();
                String ratings = "";
                int checkedRadioID = stars.getCheckedRadioButtonId();
                if(checkedRadioID == R.id.rb1) {
                    ratings = "⭐";
                } else if (checkedRadioID == R.id.rb2) {
                    ratings = "⭐⭐";
                } else if (checkedRadioID == R.id.rb3) {
                    ratings = "⭐⭐⭐";
                } else if (checkedRadioID == R.id.rb4) {
                    ratings = "⭐⭐⭐⭐";
                } else if (checkedRadioID == R.id.rb5) {
                    ratings = "⭐⭐⭐⭐⭐";
                }
                DBhelper dbh = new DBhelper(MainActivity.this);
                long inserted_id = dbh.insertTitle(songTitle);
                long inserted_id2 = dbh.insertSinger(singers);
                long inserted_id3 = dbh.insertYear(year);
                long inserted_id4 = dbh.insertRatings(ratings);

                if (inserted_id != -1 && inserted_id2 != -1 && inserted_id3 != -1 && inserted_id4 != -1) {
                    al.clear();
                    al.addAll(dbh.getAllSongs());
                    aa.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Insert successful", Toast.LENGTH_SHORT).show();
                }
            }
        });

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ListActivity.class);
                startActivity(i);
            }
        });

    }
}