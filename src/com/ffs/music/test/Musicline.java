package com.ffs.music.test;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Musicline extends JFrame implements Runnable {
    private byte[] audioData=null;
    private int intBytes = 0;
    private byte[] ml=new byte[1];
    private int[] drawl=null;
    /** Creates new form Musicline */
    public Musicline()
    {
        initComponents();
    }
    @Override
    public void paint(Graphics g)
    {
        g.clearRect(0,0,900,900);
        //System.out.print(drawl.length);
        if(audioData!=null)
        {
            drawl=new int[audioData.length];
            for(int i=0;i<audioData.length;i++)
            {
                ml[0]=audioData[i];
                drawl[i]=Math.abs((int)audioData[i]);
            }
            System.out.println(drawl[0]);
            for(int i=0;i<drawl.length-1;i++)
            {
                if(i % 2 == 0){
                    g.setColor(Color.red);
                    //g.drawLine(i*this.getWidth()/256,drawl[i]+100,(i+1)*this.getWidth()/256,drawl[i+1]+100);
                    g.drawRect(i*this.getWidth()/256,drawl[i]+100,10,drawl[i+1]+100);
                }
                if(i % 2 != 0){
                    g.setColor(Color.CYAN);
                    //g.drawLine(i*this.getWidth()/256,drawl[i]+100,(i+1)*this.getWidth()/256,drawl[i+1]+100);
                    g.drawRect(i*this.getWidth()/256,drawl[i]+100,10,drawl[i+1]+100);
                }
            }
        }

    }
    @Override
    public void run()
    {
        while(intBytes!=-1)
        {
            try
            {
                synchronized(this)
                {
                    this.wait(10);
                }
            } catch (InterruptedException ex)
            {
                ex.printStackTrace();
            }
            repaint();
        }
    }
    public void play()
    {
        try
        {
            // 获得音频输入流
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File( "F:/filetest/audio.wav"));
            ais=AudioSystem.getAudioInputStream(AudioFormat.Encoding.PCM_SIGNED,ais);
            // 指定声音流中特定数据安排
            AudioFormat baseFormat = ais.getFormat();
            System.out.println("baseFormat="+baseFormat);
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, baseFormat);
            System.out.println("info="+info);
            // 从混频器获得源数据行
            SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
            System.out.println("line="+line);
            // 打开具有指定格式的行，这样可使行获得所有所需的系统资源并变得可操作。
            line.open(baseFormat);
            line.start();// 允许数据行执行数据 I/O
            int BUFFER_SIZE = 256;
            audioData = new byte[BUFFER_SIZE];
            while (intBytes != -1)
            {
                // 从音频流读取指定的最大数量的数据字节，并将其放入给定的字节数组中。
                intBytes = ais.read(audioData, 0, BUFFER_SIZE);
                if (intBytes >= 0)
                {
                    // 通过此源数据行将音频数据写入混频器。
                    int outBytes = line.write(audioData, 0, intBytes);
                }
            }
            line.drain();
            line.close();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void initComponents() {
        setSize(480, 400);
    }


    public static void main(String args[])
    {
        Musicline msl=new Musicline();
        msl.setLocationRelativeTo(null);
        msl.setVisible(true);
        msl.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Thread yh=new Thread(msl);
        yh.start();
        msl.play();
    }

}
