package com.ffs.music;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * 循环播放音频
 * @author fengfasong
 * @date 2020/9/27
 */
public class MusicStudy04 {
    public static void main(String[] args) {
        try {
            Clip clip = AudioSystem.getClip();
            //获取音频文件
            clip.open(AudioSystem.getAudioInputStream(new File("")));
            clip.start();
            clip.setLoopPoints(0,clip.getFrameLength()-1);
            //因为slip是非阻塞的，所以开启一个死循环防止主程序停止后循环停止
            while (true){

            }
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
