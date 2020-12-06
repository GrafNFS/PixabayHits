package com.example.pixabay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private GridView gridHits;
    public Network network;
    private GridHitsAdapter gridHitsAdapter;
    private int pageHits = 1;
    private String categoreHits;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            return;
                        }
                        String token = task.getResult();
                    }
                });

        categoreHits = "people";

        network = Network.getInstance();

        getSupportActionBar().setTitle(R.string.drawerTxtPeople);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        setupDrawerToggle();
        initButtonsNavigation();

        gridHits = (GridView)findViewById(R.id.gridHits);
        gridHitsAdapter = new GridHitsAdapter(MainActivity.this);
        gridHits.setAdapter(gridHitsAdapter);
        gridHits.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                if ((i2 - i1) == i && i2 > 0) {
                    pageHits ++;
                    new getHits().execute();
                }
            }
        });

        new getHits().execute();
    }

    void setupDrawerToggle(){
        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.app_name, R.string.app_name);
        mDrawerToggle.syncState();
    }

    private void initButtonsNavigation() {
        final TextView txtPeople = findViewById(R.id.txtPeople);
        final TextView txtAnimals = findViewById(R.id.txtAnimals);
        final TextView txtComputer = findViewById(R.id.txtComputer);
        final TextView txtNature = findViewById(R.id.txtNature);
        final TextView txtTransportation = findViewById(R.id.txtTransportation);

        txtPeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoreHits = "people";
                getSupportActionBar().setTitle(R.string.drawerTxtPeople);
                refresGrid();
            }
        });
        txtAnimals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoreHits = "animals";
                getSupportActionBar().setTitle(R.string.drawerTxtAnimals);
                refresGrid();
            }
        });
        txtComputer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoreHits = "computer";
                getSupportActionBar().setTitle(R.string.drawerTxtComputer);
                refresGrid();
            }
        });
        txtNature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoreHits = "nature";
                getSupportActionBar().setTitle(R.string.drawerTxtNature);
                refresGrid();
            }
        });
        txtTransportation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoreHits = "transportation";
                getSupportActionBar().setTitle(R.string.drawerTxtTransportation);
                refresGrid();
            }
        });
    }

    private void refresGrid() {
        if (!gridHitsAdapter.isEmpty()) {
            gridHitsAdapter.clear();
            new getHits().execute();
            mDrawerLayout.closeDrawers();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class getHits extends AsyncTask<String, String, List<ItemHits>> {

        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<ItemHits> doInBackground(String... strings) {
            List<ItemHits> result = network.getLisHits(pageHits, categoreHits);
            return result;
        }

        protected void onPostExecute(List<ItemHits> result) {
            if (result != null) {
                for (ItemHits item : result) {
                    gridHitsAdapter.add(item);
                }
                gridHitsAdapter.notifyDataSetChanged();

            }
            else {

            }
            super.onPostExecute(result);
        }
    }
}
