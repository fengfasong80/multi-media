package com.ffs.music;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * 简单的录音机
 * @author fengfasong
 * @date 2020/9/27
 */
public class MusicStudy02 {
    public static void main(String[] args) {
        try {
            //文件输出位置与文件名
            File file = new File("F:/filetest/audio.wav");
            //音频格式
            AudioFormat audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                    44100.0F, 16, 2, 4, 44100.0F,false);
            DataLine.Info info = new DataLine.Info(TargetDataLine.class,audioFormat);
            //目标数据线
            TargetDataLine targetDataLine = (TargetDataLine) AudioSystem.getLine(info);
            targetDataLine.open(audioFormat);
            targetDataLine.start();
            new Thread(){
                @Override
                public void run() {
                    //音频数据
                    AudioInputStream inputStream = new AudioInputStream(targetDataLine);
                    try {
                        AudioSystem.write(inputStream,AudioFileFormat.Type.WAVE,file);
                        System.out.println("over");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
            System.out.println("录音已经开始，说完话后请按回车键结束录音！");
            System.in.read();
            targetDataLine.close();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
