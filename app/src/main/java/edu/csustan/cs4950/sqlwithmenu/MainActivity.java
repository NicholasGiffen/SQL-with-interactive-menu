package edu.csustan.cs4950.sqlwithmenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Container container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        container = new Container(this);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        if (id == R.id.action_insert) {
            actionInsert();
            return true;
        } else if(id == R.id.action_update) {
            actionUpdate();
            return true;
        } else if(id == R.id.action_delete) {
            actionDelete();
            return true;
        } else if(id == R.id.action_delete_table) {
            actionDeleteTable();
            return true;
        } else if(id == R.id.action_create_table) {
            actionCreateTable();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void onStart() {
        super.onStart();
        showTable();
    }

    public void showTable() {
        TextView lblMsg = findViewById(R.id.lblMsg);
        if (container.existTable()) {
            ArrayList<Score> scores = container.selectAll();
            if (scores.size() > 0) {
                String result = "";
                for (Score score: scores) {
                    result += score.getId() + " " +
                            score.getName() + " " +
                            score.getPoint() + "\n";
                }
                lblMsg.setText(result);
            } else {
                lblMsg.setText("No records");
            }
        } else {
            lblMsg.setText("Table does not exist. Create table");
        }
    }

    private void actionCreateTable() {
        // table exist. show message
        if (container.existTable()) {
            Toast.makeText(this, "Table already exists",
                    Toast.LENGTH_SHORT).show();

            // table does not exist. create table
        } else {
            String msg = container.createTable();
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

            // reload current screen
            finish();
            startActivity(getIntent());

        }
    }

    private void actionDeleteTable() {
        // table exist. show message
        if (!container.existTable()) {
            Toast.makeText(this, "Table does not exists",
                    Toast.LENGTH_SHORT).show();

            // table exists. delete table
        } else {
            String msg = container.deleteTable();
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

            // reload current screen
            finish();
            startActivity(getIntent());

        }
    }

    private void actionUpdate() {
        // table exist. show message
/*        EditText inputID = findViewById(R.id.inputID);
        EditText inputPoint = findViewById(R.id.inputPoint);
        int id = Integer.parseInt(inputID.getText().toString());
        float point = Float.parseFloat(inputPoint.getText().toString());

        if (!container.existTable()) {
            Toast.makeText(this, "Table does not exists",
                    Toast.LENGTH_SHORT).show();

            // table exists. delete table
        } else {


            String msg = container.update(id, point);
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

            // reload current screen
            finish();
            startActivity(getIntent());

        }*/
        Intent intent = new Intent(this, UpdateActivity.class);
        startActivity(intent);
    }

    private void actionDelete() {
        // table exist. show message
        // table exist. show message
/*        EditText inputID = findViewById(R.id.inputID);
        int id = Integer.parseInt(inputID.getText().toString());
        if (!container.existTable()) {
            Toast.makeText(this, "Table does not exists",
                    Toast.LENGTH_SHORT).show();

            // table exists. delete table
        } else {
            String msg = container.delete(id);
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

            // reload current screen
            finish();
            startActivity(getIntent());

        }*/

        Intent intent = new Intent(this, DeleteActivity.class);
        startActivity(intent);
    }
    private void actionInsert() {
        // table exist. show message
/*        if (!container.existTable()) {
            Toast.makeText(this, "Table does not exists",
                    Toast.LENGTH_SHORT).show();

            // table exists. delete table
        } else {
            String msg = container.deleteTable();
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

            // reload current screen
            finish();
            startActivity(getIntent());*/
            Intent intent = new Intent(this, InsertActivity.class);
            startActivity(intent);


    }

    private void showRecord(ArrayList<Score> scores) {
        TextView lblMsg = findViewById(R.id.lblMsg);
        if (scores.size() >0 ) {
            String result = "";
            for (Score score : scores) {
                result += score.getId() + " " +
                        score.getName() + " " +
                        score.getPoint() + "\n";
            }
            lblMsg.setText(result);
        }else {
            lblMsg.setText("No matching results" );
        }
    }

    private void showRecord(Score score) {
        TextView lblMsg = findViewById(R.id.lblMsg);
            String result = "";
                result += score.getId() + " " +
                        score.getName() + " " +
                        score.getPoint() + "\n";
        lblMsg.setText(result);

    }

    private boolean isEmpty(EditText input) {
        if (input.getText().toString().equals("")) {
            return true;
        } else {
            return false;
        }
    }

    public void select(View v) {
        //get data
        EditText inputID = findViewById(R.id.inputID);
        EditText inputPoint = findViewById(R.id.inputPoint);

        if (isEmpty(inputID) && isEmpty(inputPoint)) { //select all
            ArrayList<Score> scores = container.selectAll();
            showRecord(scores);
        } else if (isEmpty(inputID)) {
            float point = Float.parseFloat(inputPoint.getText().toString());
            ArrayList<Score> scores = container.selectLargerThanPoint(point);
            showRecord(scores);
        } else if (isEmpty(inputPoint)) {
            int id = Integer.parseInt(inputID.getText().toString());
            Score score = container.select(id);
            showRecord(score);
        } else { // select with id and point
            int id = Integer.parseInt(inputID.getText().toString());
            float point = Float.parseFloat(inputPoint.getText().toString());
            Score score = container.selectByIDLargerThanPoint(id, point);
            showRecord(score);
        }
    }

}
