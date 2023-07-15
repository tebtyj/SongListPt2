package sg.edu.rp.c346.id22011050.songlistpt2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class secondActivity extends AppCompatActivity {
    Button btnShow5stars;
    Button btnback;
    ListView lv;
    ArrayList<Songs> al;
    ArrayAdapter<Songs> aa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
            btnShow5stars = findViewById(R.id.btnShow5Stars);
            lv = findViewById(R.id.lv);
            btnback = findViewById(R.id.btnBack);

        al = new ArrayList<Songs>();
        aa = new ArrayAdapter<Songs>(this,
                android.R.layout.simple_list_item_1, al);
        lv.setAdapter(aa);

        Intent i = getIntent();
        DBHelper db = new DBHelper(secondActivity.this);
        al.clear();
        al.addAll(db.getSongs());
        aa.notifyDataSetChanged();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Songs data = al.get(position);
                int ID = data.getId();
                String Title = data.getTitle();
                String Singers = data.getSingers();
                int Year = data.getYear();
                int Stars = data.getStars();
                Intent i = new Intent(secondActivity.this, thirdActivity.class);
                i.putExtra("ID", ID);
                i.putExtra("Title", Title);
                i.putExtra("Singers", Singers);
                i.putExtra("Year", Year);
                i.putExtra("Stars", Stars);
                startActivity(i);
            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(secondActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        btnShow5stars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(secondActivity.this);
                al.clear();
                String filterText = "*****";
                al.addAll(dbh.getAllSongs(String.valueOf(filterText.length())));
                aa.notifyDataSetChanged();
            }
        });
        }
    }
