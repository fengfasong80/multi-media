package com.ffs.music;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * 简单的音频播放
 * @author fengfasong
 * @date 2020/9/27
 */
public class MusicStudy08 extends JFrame implements Runnable{

    private byte[] byteBuffer;

    private int[] point;

    public MusicStudy08() {
        init();
    }
    public void init(){
        setSize(480,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }


    @Override
    public void run() {
        while (true){
            try {
                synchronized (this) {
                    this.wait(1);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            repaint();
        }
    }
    @Override
    public void paint(Graphics g) {
        g.clearRect(0,0,480,400);
        if(byteBuffer != null){
            point = new int[byteBuffer.length];
            for (int i = 0; i < byteBuffer.length; i++) {
                point[i] = Math.abs((int) byteBuffer[i]);
            }
            for(int i=0;i<point.length-1;i++)
            {
                if(i % 2 == 0){
                    g.setColor(Color.red);
                    g.drawLine(i*this.getWidth()/256,point[i]+100,(i+1)*this.getWidth()/256,point[i+1]+100);
                    //g.drawLine(200,200,300,300);
                }
                if(i % 2 != 0){
                    g.setColor(Color.CYAN);
                    g.drawLine(i*this.getWidth()/256,point[i]+100,(i+1)*this.getWidth()/256,point[i+1]+100);
                }
            }
            //g.drawLine(200,200,300,300);
        }
    }


    public void play(){
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
            this.byteBuffer = new byte[512];

            while (true){
                nBytesRead = ais.read(byteBuffer,0,byteBuffer.length);
                if(nBytesRead<=0){
                    break;
                }
                //将音频数据写入混音器
                line.write(byteBuffer,0,nBytesRead);
                for (int i = 0; i < byteBuffer.length; i++) {
                    System.out.println(byteBuffer[i]);
                }
                System.out.println("=================");
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
    public static void main(String[] args) {

        MusicStudy08 music = new MusicStudy08();
        Thread thread = new Thread(music);
        thread.start();
        music.play();


    }


}
