package org.example;

public class ConcertImpl implements IConcert{
    @Override
    public void playMusic(String musicName) {
        System.out.println(musicName+"演奏中...");
    }
}
