package sg.edu.rp.c346.id21020263.ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    Button fiveStarSongs;
    ListView lv;
    ArrayList<Song> al;
    ArrayAdapter<Song> aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        lv = findViewById(R.id.lv);
        fiveStarSongs = findViewById(R.id.button5stars);

        al = new ArrayList<Song>();
        aa = new ArrayAdapter<Song>(this, android.R.layout.simple_list_item_1, al);
        lv.setAdapter(aa);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long identity) {
                Song song= al.get(position);
                Intent i = new Intent(ListActivity.this, EditActivity.class);
                i.putExtra("song", song);
                startActivity(i);
            }
        });

        fiveStarSongs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBhelper dbh = new DBhelper(ListActivity.this);
                al.clear();
                String filterText = "⭐⭐⭐⭐⭐".toString().trim();
                if (filterText.length() == 0) {
                    al.addAll(dbh.getAllSongs());
                } else {
                    al.addAll(dbh.getAllSongs(filterText));
                }
            }
        });


    }
}