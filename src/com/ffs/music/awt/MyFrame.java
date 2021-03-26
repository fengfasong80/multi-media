package com.ffs.music.awt;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MyFrame extends JFrame {

    public MyFrame()  {
        Border border = BorderFactory.createLineBorder(Color.BLUE);

        this.setSize(600,500);
//        Image image = Toolkit.getDefaultToolkit().getImage("images/icon.png");
        ImageIcon icon = new ImageIcon("images/icon.png");
        this.setIconImage(icon.getImage());
        this.setVisible(true);
        this.setResizable(true);
        this.getContentPane().setBackground(Color.white);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel();
        label.setText("JLable");
        //label.setForeground(Color.red);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP);
        label.setForeground(Color.CYAN);
        label.setFont(new Font("MV Boli",Font.PLAIN,20));
        label.setIcon(icon);
        label.setBackground(Color.BLACK);
        label.setOpaque(true);
        label.setBorder(border);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBounds(100,0,200,200);

        this.add(label);
        //this.setLayout(null);
        this.pack();
    }
}
