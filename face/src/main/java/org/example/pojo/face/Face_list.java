package org.example.pojo.face;

public class Face_list {
    private Angle angle;
    private String face_token;
    private Location location;
    private int face_probability;

    public Angle getAngle() {
        return angle;
    }

    public void setAngle(Angle angle) {
        this.angle = angle;
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

    public int getFace_probability() {
        return face_probability;
    }

    public void setFace_probability(int face_probability) {
        this.face_probability = face_probability;
    }
}
