package com.example.pixabay;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Network {
    public static HttpClient http;
    public static final String URL = "https://pixabay.com/api/?key=19361841-0c00edc5f00ee97ab06affe4b";

    private Network() {
        http = new DefaultHttpClient();
        ClientConnectionManager mgr = http.getConnectionManager();
        HttpParams params = http.getParams();
        http = new DefaultHttpClient(new ThreadSafeClientConnManager(params, mgr.getSchemeRegistry()), params);
        http.getConnectionManager().getSchemeRegistry().register(
                new Scheme("https", SSLSocketFactory.getSocketFactory(), 443)
        );
    }

    public static Network getInstance() {
        synchronized (Network.class) {
            if (UILApplication.restInstance == null) {
                UILApplication.restInstance = new Network();
            }
        }
        return UILApplication.restInstance;
    }

    public static List<ItemHits> getLisHits(int pageHits, String categoreHits) {
        List<ItemHits> result = new ArrayList<>();
        HttpGet request = new HttpGet(URL + "&category=" + categoreHits + "&page=" + pageHits);

        try {
            HttpResponse response = http.execute(request);
            String jsonStr = streamToString(response.getEntity().getContent());

            if (!jsonStr.equals("")) {
                JSONObject jsonObject = new JSONObject(jsonStr);
                JSONArray jsonArray = jsonObject.getJSONArray("hits");
                if (jsonArray.length() > 0) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        ItemHits item = ItemHits.parseJson(jsonArray.getJSONObject(i));
                        if (item != null) {
                            result.add(item);
                        }
                    }
                }
            }
            else {
                return null;
            }
            return result;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static String streamToString(InputStream stream) {

        InputStreamReader reader = new InputStreamReader(stream);
        BufferedReader buff = new BufferedReader(reader);
        StringBuffer strBuff = new StringBuffer();

        String s;
        try {
            while ((s = buff.readLine()) != null) {
                strBuff.append(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strBuff.toString();
    }
}
