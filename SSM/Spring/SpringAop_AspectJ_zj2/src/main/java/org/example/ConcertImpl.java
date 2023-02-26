package org.example;

import org.springframework.stereotype.Component;

@Component
public class ConcertImpl implements IConcert{
    @Override
    public void playMusic(String musicName) {
        System.out.println(musicName+"演奏中...");
    }
}
