package com.github.mastersobg.odkl.photoGrabber;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;

/**
 * Created by yuraf_000 on 27.09.2014.
 */
public class JsonUser {
    private String uid = new String();
    private JSONArray friends = new JSONArray();
    private JSONArray markedPhotos = new JSONArray();
    private final static String PHOTOS_DIR = PhotoGrabberConfig.PHOTOS_DIR;
    private final static String JSON_EXT = PhotoGrabberConfig.JSON_EXT;

    public JsonUser(String userId){
        uid = userId;
    }
    public void setFriends(JSONArray friends) {
        if (friends!=null) this.friends = friends;
    }
    public void addMarkedPhoto(String photoId, String photoOwner, Long x, Long y){
        JSONObject addition = new JSONObject();
        addition.put("photoId",photoId);
        addition.put("photoOwner",photoOwner);
        addition.put("x",x);
        addition.put("y",y);
        markedPhotos.add(addition);
    }
    public void addMarkedPhoto(JSONArray additions){
        if(additions!=null) markedPhotos.addAll(additions);
    }

    public void writeJson() {
        JSONObject writeResult = new JSONObject();
        writeResult.put("uid", uid);
        writeResult.put("friends", friends);
        writeResult.put("markedPhotos", markedPhotos);
        File jsonFile = new File(PHOTOS_DIR + uid + "/" + uid + JSON_EXT);
        try {
            FileUtils.writeStringToFile(jsonFile, writeResult.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean isEmpty() {
        if (friends.isEmpty() && markedPhotos.isEmpty()) return true;
        return false;
    }
}