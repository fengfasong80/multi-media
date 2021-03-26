package com.ffs.music.awt;

import javax.swing.*;
import java.awt.*;

/**
 * JPanel
 * @author fengfasong
 * @date 2021/1/29
 */
public class JPanelFrame {
    public static void main(String[] args) {

        JPanel redPanel = new JPanel();
        redPanel.setBackground(Color.red);
        redPanel.setBounds(0,0,100,100);

        JPanel yellowPanel = new JPanel();
        yellowPanel.setBackground(Color.YELLOW);
        yellowPanel.setBounds(100,0,100,100);

        JPanel greenPanel = new JPanel();
        greenPanel.setBackground(Color.GREEN);
        greenPanel.setBounds(0,100,200,100);

        JFrame frame = new JFrame();
        ImageIcon icon = new ImageIcon("images/icon.png");
        frame.setIconImage(icon.getImage());
        frame.setSize(500,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.add(redPanel);
        frame.add(greenPanel);
        frame.add(yellowPanel);
        frame.setVisible(true);

    }
}
