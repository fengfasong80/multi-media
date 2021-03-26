package com.ffs.music.jpanel;

import javax.swing.*;
import java.awt.*;

/**
 * 主布局
 * @author fengfasong
 * @date 2021/2/1
 */
public class MainFrame extends JFrame {

    private JPanel top;
    private JPanel left;
    private JPanel right;
    private JPanel bottom;
    private JPanel centre;


    public MainFrame(JPanel top,JPanel left,JPanel right,JPanel bottom,JPanel centre){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        this.setSize(500,400);
        if(top != null){
            this.top = top;
            this.add(this.top,BorderLayout.NORTH);
        }
        if(left != null){
            this.left = left;
            this.add(this.left,BorderLayout.WEST);
        }
        if(right != null){
            this.right = right;
            this.add(this.right,BorderLayout.EAST);
        }
        if(bottom != null){
            this.bottom = bottom;
            this.add(this.bottom,BorderLayout.SOUTH);
        }
        if(centre != null){
            this.centre = centre;
            this.add(this.centre,BorderLayout.CENTER);
        }
    }
}
