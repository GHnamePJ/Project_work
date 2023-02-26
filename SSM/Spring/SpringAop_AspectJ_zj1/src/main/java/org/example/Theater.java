package org.example;

import org.springframework.stereotype.Component;

@Component
public class Theater {
    ISpeech iSpeech;

    public Theater(ISpeech iSpeech) {
        this.iSpeech = iSpeech;
    }
    public void  doActivite(){
        this.iSpeech.printText();
        System.out.println("今天的演讲主题是环保");
        this.iSpeech.takeSpeak();
    }
}
