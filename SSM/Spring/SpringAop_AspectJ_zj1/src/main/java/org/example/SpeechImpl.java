package org.example;

import org.springframework.stereotype.Component;

@Component
public class SpeechImpl implements ISpeech{
    @Override
    public void takeSpeak() {
        System.out.println("正在演讲...");
    }

    @Override
    public void printText() {
        System.out.println("打印演讲稿...");
    }
}
