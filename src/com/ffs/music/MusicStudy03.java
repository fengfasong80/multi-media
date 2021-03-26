package com.ffs.music;

import javax.sound.sampled.*;

/**
 * 简单的复读机
 * @author fengfasong
 * @date 2020/9/27
 */
public class MusicStudy03 {
    public static void main(String[] args) {
        try {
            AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                    8000, 16, 1, 2, 8000, true);
            //目标数据线
            TargetDataLine targetDataLine = AudioSystem.getTargetDataLine(format);
            targetDataLine.open();
            targetDataLine.start();
            //源数据线
            SourceDataLine sourceDataLine = AudioSystem.getSourceDataLine(format);
            sourceDataLine.open();
            sourceDataLine.start();

            while (true){
                byte[] buffer = new byte[1024];
                int cnt = targetDataLine.read(buffer,0,buffer.length);
                sourceDataLine.write(buffer,0,cnt);
            }
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
