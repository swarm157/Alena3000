package ru.alena;

import javax.swing.*;
import java.awt.*;

public class Draw extends JPanel {
    World world = new World();
    @Override
    protected void paintComponent(Graphics g) {
        Point p =Boot.window.getMousePosition();
        for (int i = 0; i < 100; i++) {
            for(int o = 0; o < 100; o++) {
                if ((o==50&&(!(i>40&&i<60))))
                    g.setColor(Color.white);
                else
                    //if ((i*6>p.x&&i*6+6<p.x)&&(o*6>p.y&&o*6+6<p.y))
                        //g.setColor(Color.GREEN);
                    //else
                        if (world.matrix[i][o].alive)
                            g.setColor(new Color(world.matrix[i][o].energy, 20, world.matrix[i][o].rebornEnergy));
                        else
                            g.setColor(Color.black);
                g.fillRect(i*6, o*6, 6, 6);
            }
        }
    }
}
