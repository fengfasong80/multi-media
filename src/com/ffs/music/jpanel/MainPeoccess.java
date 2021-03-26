package com.ffs.music.jpanel;


import javax.swing.*;
import java.awt.*;

/**
 * 主程序
 * @author fengfasong
 * @date 2021/2/1
 */
public class MainPeoccess {

    public static void main(String[] args) throws InterruptedException {
        JPanelLeft left = new JPanelLeft(new Dimension(100,100),Color.GREEN);
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setSize(500,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(left);
        Thread.sleep(2000L);
        left.rep();
    }
}
