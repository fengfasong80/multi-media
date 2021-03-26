package com.ffs.music;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * 简单的音频播放
 * @author fengfasong
 * @date 2020/9/27
 */
public class MusicStudy01{
    public static void main(String[] args) {
        try {
            //得到需要处理的音乐文件（.wav,.pcm）
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File("F:/filetest/audio.wav"));
            //获取音频格式
            AudioFormat format = ais.getFormat();
            //获取指定格式音频的数据线
            DataLine.Info info = new DataLine.Info(SourceDataLine.class,format);
            //获取源数据线
            SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
            //打开行，并获取该行需要的系统资源，并开始运行
            line.open(format);
            line.start();

            int nBytesRead = 0;
            //缓冲大小
            byte[] byteBuffer = new byte[20];

            while (true){
                nBytesRead = ais.read(byteBuffer,0,byteBuffer.length);
                if(nBytesRead<=0){
                    break;
                }
                //将音频数据写入混音器
                line.write(byteBuffer,0,nBytesRead);
            }
            //System.out.println(byteBuffer);
            line.drain();
            line.close();

        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
