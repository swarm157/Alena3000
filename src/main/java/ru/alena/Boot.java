package ru.alena;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Boot {

    static Random r = new Random(System.currentTimeMillis());
    static Draw draw = new Draw();
    static JFrame window = new JFrame("Аленина игрушка") {{
       setSize(612, 636);
       setResizable(false);
       setLocation(400, 400);
       setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
       add(draw);
       setVisible(true);
    }};
    static Bot[][] matrix;
    public static void main(String[] args) {
        matrix = draw.world.matrix;
        //draw.repaint();
        //window.repaint();
        Timer timer = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.repaint();
            }
        });
        timer.start();
        Timer stepper = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean deadWorld = true;
                for (int i = 0; i < 100; i++) {
                    for(int o = 0; o < 100; o++) {
                        if (matrix[i][o].alive) {
                            deadWorld = false;
                            Bot bot = matrix[i][o];
                            bot.energy -=1;
                            if(bot.energy <1) {
                                bot.energy = bot.rebornEnergy;
                                bot.rebornEnergy/=2;
                            }
                            if (bot.rebornEnergy==0) {
                                bot.alive = false;
                            }
                            Code code = bot.gen.get(bot.position);
                                //bot.alive=false;
                            switch (code) {

                                case moveLeft:
                                    if (isNotOnBorder(i, o) &&!matrix[i-1][o].alive) {
                                        matrix[i][o] = matrix[i-1][o];
                                        matrix[i-1][o] = bot;
                                    }
                                    break;

                                case moveRight:
                                    if (isNotOnBorder(i, o) &&!matrix[i+1][o].alive) {
                                        matrix[i][o] = matrix[i+1][o];
                                        matrix[i+1][o] = bot;
                                    }
                                    break;
                                case moveUp:
                                    if (o-1!=50||(i>40&&i<60))
                                        if (o!=0&&!matrix[i][o-1].alive) {
                                            matrix[i][o] = matrix[i][o-1];
                                            matrix[i][o-1] = bot;

                                            int temp = (-1*(-100+o))/10;
                                            //System.out.println(temp);
                                            bot.energy+=temp;
                                        }
                                    break;
                                case moveDown:
                                    if (o+1!=50||(i>40&&i<60))
                                        if (isNotOnBorder(i, o) &&!getByEnumContainSide(code, i, o).alive) {
                                            matrix[i][o] = matrix[i][o+1];
                                            matrix[i][o+1] = bot;
                                        }
                                    break;
                                case eatLeft:
                                    if (isNotOnBorder(i, o) &&getByEnumContainSide(code, i, o).alive) {
                                        bot.energy+=matrix[i-1][o].energy;
                                        matrix[i-1][o].energy=0;
                                        matrix[i-1][o].alive=false;
                                    }
                                    break;
                                case eatRight:
                                    if (isNotOnBorder(i, o) &&getByEnumContainSide(code, i, o).alive) {
                                        bot.energy+=matrix[i+1][o].energy;
                                        matrix[i+1][o].energy=0;
                                        matrix[i+1][o].alive=false;
                                    }
                                    break;
                                case eatUp:
                                    if (isNotOnBorder(i, o) &&getByEnumContainSide(code, i, o).alive) {
                                        bot.energy+=matrix[i][o-1].energy;
                                        matrix[i][o-1].energy=0;
                                        matrix[i][o-1].alive=false;
                                    }
                                    break;
                                case eatDown:
                                    if (isNotOnBorder(i, o) &&getByEnumContainSide(code, i, o).alive) {
                                        bot.energy+=matrix[i][o+1].energy;
                                        matrix[i][o+1].energy=0;
                                        matrix[i][o+1].alive=false;
                                    }
                                    break;
                            }
                            if (bot.energy>250) {
                                bot.energy=250;
                            }
                            bot.position++;
                            if (bot.position==bot.gen.size()) {
                                bot.position=0;
                            }
                        }
                    }
                }
                if (deadWorld) {
                    draw.world = new World();
                    matrix = draw.world.matrix;
                }
            }
        });
        stepper.start();
        timer.start();
    }

    static boolean isNotOnBorder(int i, int o) {
        return i != 0 && o != 0 && i != 99 && o != 99;
    }

    static Bot getByEnumContainSide(Code code, int i, int o) {
        String t = code.toString();
        if (t.toLowerCase().contains("left")) {
            return  matrix[i-1][o];
        }
        if (t.toLowerCase().contains("right")) {
            return  matrix[i+1][o];
        }
        if (t.toLowerCase().contains("up")) {
            return  matrix[i][o-1];
        }
        if (t.toLowerCase().contains("down")) {
            return  matrix[i][o+1];
        }
        return null;
    }
}
