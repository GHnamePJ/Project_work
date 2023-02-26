package org.example;

public class SpeechImpl implements ISpeech{
    @Override
    public void takeSpeech() {
        System.out.println("正在演讲...");
    }

    @Override
    public void printText() {
        System.out.println("打印演讲稿...");
    }
}
