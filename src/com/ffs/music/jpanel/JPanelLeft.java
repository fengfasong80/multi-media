package com.ffs.music.jpanel;

import javax.swing.*;
import java.awt.*;

public class JPanelLeft extends JPanel  {

    private Dimension dimension;

    private Color color;

    public JPanelLeft(Dimension dimension,Color color) {
        this.dimension = dimension;
        this.color = color;
        this.setPreferredSize(this.dimension);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(color);
        ImageIcon icon = new ImageIcon("images/icon.png");
        g.drawImage(icon.getImage(),0,0,null);

    }
    public void rep(){
        color = Color.BLUE;
        repaint();
    }
}
