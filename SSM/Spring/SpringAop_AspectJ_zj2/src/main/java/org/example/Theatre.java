package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class Theatre {
//    ①
//    @Resource(name="music")
//    ②
    @Autowired
    @Qualifier("music")
    private Music music;
    private IConcert iConcert;
    public Theatre(IConcert iConcert){
        this.iConcert=iConcert;
    }
    public void doActivite(){
//        ③
        music=new Music();
        music.setMusicName("Die Young");
        System.out.println("今天的音乐会主题是大自然");
        this.iConcert.playMusic(music.getMusicName());
    }
}
