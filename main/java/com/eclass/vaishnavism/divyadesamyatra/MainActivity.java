package com.eclass.vaishnavism.divyadesamyatra;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ProgressBar mprogressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHandler db = new DatabaseHandler(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mprogressBar = (ProgressBar) findViewById(R.id.circular_progress_bar);
        ObjectAnimator anim = ObjectAnimator.ofInt(mprogressBar, "progress", 0, 100);
        anim.setDuration(1500);
        anim.setInterpolator(new DecelerateInterpolator());
        anim.start();

        // Inserting Divyadesam
        Log.d("Insert: ", "Inserting ..");

        if ((db.getDivyadesam("Srirangam")).equals("Srirangam")) {
            db.updateDivyadesam(new Divyadesam("Srirangam2", ""));
        }
            else
            {
            db.addDivyadesam(new Divyadesam("Srirangam", ""));
        }
        db.addDivyadesam(new Divyadesam("Tirumala", ""));
        db.addDivyadesam(new Divyadesam("Triplicane", ""));
        db.addDivyadesam(new Divyadesam("Sholingar", ""));
        db.addDivyadesam(new Divyadesam("Tiruneermalai", ""));


        // Reading all contacts
        Log.d("Reading: ", "Reading all divyadesam..");
        List<Divyadesam> divyadesams = db.getAllDivyadesam();

        for (Divyadesam dd : divyadesams) {
            String log = " ,Name: " + dd.getDivyadesam() + " ,Date: " +
                    dd.getDateVisited();
            // Writing Divyadesams to log
            Log.d("Name: ", log);
        }
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
