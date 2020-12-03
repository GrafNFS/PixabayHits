package com.example.pixabay;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.GridView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private GridView gridHits;
    public Network network;
    private GridHitsAdapter gridHitsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        network = Network.getInstance();

        gridHits = (GridView)findViewById(R.id.gridHits);
        gridHitsAdapter = new GridHitsAdapter(MainActivity.this);
        gridHits.setAdapter(gridHitsAdapter);

        new getHits().execute();
    }

    class getHits extends AsyncTask<String, String, List<ItemHits>> {
//        Dialog dialog;
        protected void onPreExecute() {
            super.onPreExecute();
//            dialog = new Dialog(ListBookActivity.this, R.style.TransparentProgressDialog);
//            dialog.setCancelable(false);
//            dialog.setCanceledOnTouchOutside(false);
//            dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
//            dialog.setContentView(R.layout.custom_progress_dialog);
//            dialog.show();
        }

        @Override
        protected List<ItemHits> doInBackground(String... strings) {
            List<ItemHits> result = network.getLisHits();
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
