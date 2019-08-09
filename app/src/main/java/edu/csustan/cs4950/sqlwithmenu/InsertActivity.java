package edu.csustan.cs4950.sqlwithmenu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {
    private Container container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        container = new Container(this);
        setContentView(R.layout.activity_insert);
    }


    public void insert(View v) {
        //get data
        EditText inputName = findViewById(R.id.inputName);
        EditText inputPoint = findViewById(R.id.inputPoint);
        String name = inputName.getText().toString();
        float point = Float.parseFloat(inputPoint.getText().toString());

        // insert data into table
        container.insert(name, point);

        // go back to main screen
        Toast.makeText(this, "inserted", Toast.LENGTH_SHORT).show();
        finish();
    }
}
