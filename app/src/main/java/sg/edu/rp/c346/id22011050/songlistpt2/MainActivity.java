package sg.edu.rp.c346.id22011050.songlistpt2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnInsert, btnShow;
    ListView lv;
    EditText etTitle, etSinger,etYear;
    RadioGroup stars;
    ArrayList<Songs> al;
    ArrayAdapter<Songs> aa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.btnUpdate);
        btnShow = findViewById(R.id.btnDelete);
        lv = findViewById(R.id.lv);
        etTitle = findViewById(R.id.etTitle);
        etSinger = findViewById(R.id.etSinger);
        etYear = findViewById(R.id.etYear);
        stars = findViewById(R.id.stars);

        al = new ArrayList<Songs>();
        aa = new ArrayAdapter<Songs>(this,
                android.R.layout.simple_list_item_1, al);
        lv.setAdapter(aa);

        btnInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                //db.insertTask("Submit RJ", "25 Apr 2021");
                String insTitle = etTitle.getText().toString();
                String insSinger = etSinger.getText().toString();
                String insYear = etYear.getText().toString();
                int finalYear = Integer.parseInt(insYear);
                int insStars = 0 ;
                int checkedStars = stars.getCheckedRadioButtonId();
                if (checkedStars == R.id.star1){
                    insStars += 1;
                }else if (checkedStars == R.id.star2){
                    insStars += 2;
                }else if (checkedStars == R.id.star3){
                    insStars += 3;
                }else if (checkedStars == R.id.star4){
                    insStars += 4;
                }else if (checkedStars == R.id.star5){
                    insStars += 5;
                }
                //insert into data
                db.insertSong(insTitle,insSinger,finalYear,insStars);
                Toast toast = Toast.makeText(btnInsert.getContext(), "Song added successfully", Toast.LENGTH_LONG);
                toast.show();
            }
        });
        btnShow.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,secondActivity.class);
                startActivity(intent);
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Songs data = al.get(position);
                Intent i = new Intent(MainActivity.this,
                        secondActivity.class);
                startActivity(i);
            }
        });
}
}