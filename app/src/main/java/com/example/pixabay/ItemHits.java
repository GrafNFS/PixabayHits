package com.example.pixabay;

import org.json.JSONObject;

public class ItemHits {
    public int id;
    public String pageURL;
    public String type;
    public String tags;
    public String previewURL;
    public int previewWidth;
    public int previewHeight;
    public String webformatURL;
    public int webformatWidth;
    public int webformatHeight;
    public String largeImageURL;
    public String fullHDURL;
    public String imageURL;
    public int imageWidth;
    public int imageHeight;
    public int imageSize;
    public int views;
    public int downloads;
    public int favorites;
    public int likes;
    public int comments;
    public int user_id;
    public String user;
    public String userImageURL;


    public static ItemHits parseJson(JSONObject json) {
        ItemHits result = new ItemHits();

        try {
            if (json.has("id")) {
                result.id = json.getInt("id");
            }
            if (json.has("pageURL")) {
                result.pageURL = json.getString("pageURL");
            }
            if (json.has("type")) {
                result.type = json.getString("type");
            }
            if (json.has("tags")) {
                result.tags = json.getString("tags");
            }
            if (json.has("previewURL")) {
                result.previewURL = json.getString("previewURL");
            }
            if (json.has("previewWidth")) {
                result.previewWidth = json.getInt("previewWidth");
            }
            if (json.has("previewHeight")) {
                result.previewHeight = json.getInt("previewHeight");
            }
            if (json.has("webformatURL")) {
                result.webformatURL = json.getString("webformatURL");
            }
            if (json.has("webformatWidth")) {
                result.webformatWidth = json.getInt("webformatWidth");
            }
            if (json.has("webformatHeight")) {
                result.webformatHeight = json.getInt("webformatHeight");
            }
            if (json.has("largeImageURL")) {
                result.largeImageURL = json.getString("largeImageURL");
            }
            if (json.has("fullHDURL")) {
                result.fullHDURL = json.getString("fullHDURL");
            }
            if (json.has("imageURL")) {
                result.imageURL = json.getString("imageURL");
            }
            if (json.has("imageWidth")) {
                result.imageWidth = json.getInt("imageWidth");
            }
            if (json.has("imageHeight")) {
                result.imageHeight = json.getInt("imageHeight");
            }
            if (json.has("imageSize")) {
                result.imageSize = json.getInt("imageSize");
            }
            if (json.has("views")) {
                result.views = json.getInt("views");
            }
            if (json.has("downloads")) {
                result.downloads = json.getInt("downloads");
            }
            if (json.has("favorites")) {
                result.favorites = json.getInt("favorites");
            }
            if (json.has("likes")) {
                result.likes = json.getInt("likes");
            }
            if (json.has("comments")) {
                result.comments = json.getInt("comments");
            }
            if (json.has("user_id")) {
                result.user_id = json.getInt("user_id");
            }
            if (json.has("user")) {
                result.user = json.getString("user");
            }
            if (json.has("userImageURL")) {
                result.userImageURL = json.getString("userImageURL");
            }
            return result;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
