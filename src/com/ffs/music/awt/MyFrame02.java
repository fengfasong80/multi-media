package com.ffs.music.awt;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame02 extends JFrame implements ActionListener {

    JButton button;

    public MyFrame02() {

        button = new JButton();
        //button.setSize(100,50);
        button.setBounds(100, 100, 100, 50);
        button.addActionListener(this);
        this.setVisible(true);
        this.setSize(500, 400);
        this.add(button);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            System.out.println("===================");
        }
    }
}