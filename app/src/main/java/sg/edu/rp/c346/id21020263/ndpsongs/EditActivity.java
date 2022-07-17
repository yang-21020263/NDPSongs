package sg.edu.rp.c346.id21020263.ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class EditActivity extends AppCompatActivity {

    EditText etID, etEditTitle, etEditSingers, etEditYear;
    RadioGroup editStars;
    Button update, delete, cancel;
    Song song;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        etID = findViewById(R.id.editTextID);
        etEditTitle = findViewById(R.id.etEditSong);
        etEditSingers = findViewById(R.id.etEditSingers);
        etEditYear = findViewById(R.id.etYear);
        editStars = findViewById(R.id.editStars);
        update = findViewById(R.id.buttonUpdate);
        delete = findViewById(R.id.buttonDelete);
        cancel = findViewById(R.id.buttonCancel);

        //
        Intent i = getIntent();
        song = (Song) i.getSerializableExtra("song");

        etID.setText(song.getId());
        etID.setEnabled(false);
        etEditTitle.setText(song.getTitle());
        String editRatings = "";
        int checkedRadioID = editStars.getCheckedRadioButtonId();
        if(checkedRadioID == R.id.rb1) {
            editRatings = "⭐";
        } else if (checkedRadioID == R.id.rb2) {
            editRatings = "⭐⭐";
        } else if (checkedRadioID == R.id.rb3) {
            editRatings = "⭐⭐⭐";
        } else if (checkedRadioID == R.id.rb4) {
            editRatings = "⭐⭐⭐⭐";
        } else if (checkedRadioID == R.id.rb5) {
            editRatings = "⭐⭐⭐⭐⭐";
        }

        String finalEditRatings = editRatings;
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBhelper dbh = new DBhelper(EditActivity.this);
                song.setTitle(etEditTitle.getText().toString());
                song.setSingers(etEditSingers.getText().toString());
                song.setYear(etEditYear.getText().toString());
                song.setRatings(finalEditRatings.toString());
                dbh.updateSong(song);
                dbh.close();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBhelper dbh = new DBhelper(EditActivity.this);
                dbh.deleteSong(song.getId());
            }
        });

    }
}