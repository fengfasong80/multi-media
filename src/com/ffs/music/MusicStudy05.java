package com.ffs.music;

import javax.sound.sampled.*;
import java.io.*;

/**
 * mp3播放
 * @author fengfasong
 * @date 2020/9/27
 */
public class MusicStudy05 {
    public static void main(String[] args) {
        //获取音频文件输入流
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("F:/filetest/陈奕迅 - 让我留在你身边.mp3"));
            AudioFormat audioFormat = audioInputStream.getFormat();
            if(audioFormat.getEncoding() != AudioFormat.Encoding.PCM_SIGNED){
                //进行音乐格式判断，如果不是系统默认格式，则进行格式转换（解码）
                audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                        audioFormat.getSampleRate(), 16, audioFormat.getChannels(),
                        audioFormat.getChannels() * 2, audioFormat.getSampleRate(), false);
                //重新获取新格式的数据流
                audioInputStream = AudioSystem.getAudioInputStream(audioFormat,audioInputStream);
            }
            DataLine.Info info = new DataLine.Info(DataLine.class,audioInputStream.getFormat());
            //获取音频输入数据线
            SourceDataLine sourceDataLine = (SourceDataLine) AudioSystem.getLine(info);
            sourceDataLine.open(audioInputStream.getFormat(),sourceDataLine.getBufferSize());
            sourceDataLine.start();
            Control[] controls = sourceDataLine.getControls();


            int numRead = 0;
            byte[] buffer = new byte[sourceDataLine.getBufferSize()];
            System.out.println("buffer长度："+buffer.length);
            while ((numRead = audioInputStream.read(buffer,0, buffer.length))>=0){
                int offSet = 0;
                while (offSet < numRead){
                    offSet += sourceDataLine.write(buffer,offSet,numRead-offSet);
                    System.out.println("numRead:"+numRead+" offSet:"+offSet);
                }
                System.out.println("=============");

            }
            sourceDataLine.drain();
            sourceDataLine.stop();
            sourceDataLine.close();
            audioInputStream.close();

        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
