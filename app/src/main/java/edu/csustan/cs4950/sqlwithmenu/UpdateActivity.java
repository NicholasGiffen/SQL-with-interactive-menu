package edu.csustan.cs4950.sqlwithmenu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    private Container container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        container = new Container(this);
        setContentView(R.layout.activity_update);
    }


    public void update(View v) {
        //get data
        EditText inputID = findViewById(R.id.inputID);
        EditText inputPoint = findViewById(R.id.inputPoint);
        int id = Integer.parseInt(inputID.getText().toString());
        float point = Float.parseFloat(inputPoint.getText().toString());

        // insert data into table
        container.update(id, point);

        // go back to main screen
        Toast.makeText(this, "updated", Toast.LENGTH_SHORT).show();
        finish();
    }
}
