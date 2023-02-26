package org.example.pojo.face;

import java.util.List;

public class Result {
    private int score;
    private String face_token;
    private Location location;
    private List<User_list> user_list;
    private int face_num;
    private List<Face_list> faceList;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getFace_token() {
        return face_token;
    }

    public void setFace_token(String face_token) {
        this.face_token = face_token;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<User_list> getUser_list() {
        return user_list;
    }

    public void setUser_list(List<User_list> user_list) {
        this.user_list = user_list;
    }

    public int getFace_num() {
        return face_num;
    }

    public void setFace_num(int face_num) {
        this.face_num = face_num;
    }

    public List<Face_list> getFaceList() {
        return faceList;
    }

    public void setFaceList(List<Face_list> faceList) {
        this.faceList = faceList;
    }
}
