package sg.edu.rp.c346.id22011050.songlistpt2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class thirdActivity extends AppCompatActivity {
    EditText etTitle, etSinger,etYear;
    Button btnCancel, btnUpdate, btnDelete;
    Songs rID, title, singers, year, stars, data;
    TextView id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        etTitle = findViewById(R.id.etTitle);
        etSinger = findViewById(R.id.etSinger);
        etYear = findViewById(R.id.etYear);
        btnCancel = findViewById(R.id.btnCancel);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        id = findViewById(R.id.etID);

        Intent i = getIntent();
        rID = (Songs) i.getSerializableExtra("ID");
        id.setText(String.valueOf(rID.getId()));
        title = (Songs) i.getSerializableExtra("Title");
        etTitle.setText(title.getTitle());
        singers = (Songs) i.getSerializableExtra("Singers");
        etSinger.setText(singers.getSingers());
        year = (Songs) i.getSerializableExtra("Year");
        etYear.setText(String.valueOf(year.getYear()));
        stars = (Songs) i.getSerializableExtra("Stars");

        data = (Songs) i.getSerializableExtra("data");
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(thirdActivity.this, secondActivity.class);
                startActivity(intent);
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(thirdActivity.this);
                dbh.deleteSongs(data.getId());
                Intent intent = new Intent(thirdActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}