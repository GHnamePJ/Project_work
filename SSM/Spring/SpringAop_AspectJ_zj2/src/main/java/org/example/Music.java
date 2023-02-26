package org.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Music {
//    @Value("yy")
    private String musicName;
//    @Value("FF")
    private String musicType;

    public String getMusicName() {
        return musicName;
    }

    public String getMusicType() {
        return musicType;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public void setMusicType(String musicType) {
        this.musicType = musicType;
    }

}
