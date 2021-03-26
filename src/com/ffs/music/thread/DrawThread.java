package com.ffs.music.thread;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * 可视化绘制线程
 * @author fengfasong
 * @date 2020/9/28
 */
public class DrawThread extends JFrame implements Runnable{

    private byte[] musicData = null;

    private int end = 0;

    private int[] draw = null;

    @Override
    public void paint(Graphics g) {
        g.clearRect(0,0,800,800);
        if(musicData != null){
            draw = new int[musicData.length];
            for (int i = 0; i < musicData.length; i++) {
                draw[i] = Math.abs(musicData[i]);
            }
            for (int i = 0; i < draw.length-1; i++) {
                if(i % 2 == 0){
                    g.setColor(Color.BLUE);
                    g.drawRect(i*this.getWidth()/256,draw[i]+100,10,draw[i+1]+100);
                }
                if(i % 2 != 0){
                    g.setColor(Color.RED);
                    g.drawRect(i*this.getWidth()/256,draw[i]+100,10,draw[i+1]+100);
                }
            }
        }
    }

    public void play(){
        try {
            //获取音频输入流
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("F:/filetest/陈奕迅 - 让我留在你身边.mp3"));
            //获取音频格式
            AudioFormat format = audioInputStream.getFormat();
            //mp3格式不是系统默认格式，需要进行转码
            if(AudioFormat.Encoding.PCM_SIGNED != format.getEncoding()){
                format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                        format.getSampleRate(), 16, format.getChannels(),
                        format.getChannels() * 2, format.getSampleRate(), false);
                audioInputStream = AudioSystem.getAudioInputStream(format,audioInputStream);
            }
            DataLine.Info info = new DataLine.Info(DataLine.class, audioInputStream.getFormat());
            //获取数据线
            SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
            line.open(audioInputStream.getFormat(),line.getBufferSize());
            line.start();

            musicData = new byte[line.getBufferSize()];

            while (end != -1){
                end = audioInputStream.read(musicData,0,musicData.length);
                line.write(musicData,0,end);
            }
            line.drain();
            line.stop();
            line.close();
            audioInputStream.close();
        }catch (Exception e){

        }
    }

    public static void main(String[] args) {
        DrawThread drawThread = new DrawThread();
        drawThread.setSize(500,400);
        drawThread.setVisible(true);
        drawThread.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        drawThread.setLocationRelativeTo(null);

        Thread t = new Thread(drawThread);
        t.start();
        drawThread.play();


    }


    @Override
    public void run() {
        while (end != -1){
            synchronized (this){
                try {
                    this.wait(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            repaint();
        }
    }
}
