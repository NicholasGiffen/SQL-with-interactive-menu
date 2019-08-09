package edu.csustan.cs4950.sqlwithmenu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteActivity extends AppCompatActivity {
    private Container container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        container = new Container(this);
        setContentView(R.layout.activity_delete);
    }


    public void delete(View v) {
        //get data
        EditText inputID = findViewById(R.id.inputID);
        int id = Integer.parseInt(inputID.getText().toString());

        // insert data into table
        container.delete(id);

        // go back to main screen
        Toast.makeText(this, "deleted", Toast.LENGTH_SHORT).show();
        finish();
    }
}
