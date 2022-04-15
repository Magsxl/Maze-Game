package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Klasa odpowiadająca za rysowanie labiryntu
 * @author Maksymilian Gontarek
 */
public class paintingGame extends JPanel {
    /**inicjalizacja klas*/
    Maze mazeIMG = new Maze();

    public int sWidth, sHeight;
    static ArrayList<Enemy> enemies = new ArrayList<>();

    Font font1 = new Font("Monospace", Font.BOLD + Font.ITALIC, 18);

    /**
     * Zdefiniowanie okienka labiryntu - Główny konstruktor
     * @param width
     * @param height
     */
    public paintingGame(int width, int height){
        this.sWidth=width;
        this.sHeight=height;
      /**określanie losowego położenia wrogów i dodawanie ich do listy(działa), wykomentowane na potrzeby prezentacji*/
      //  int enemyCount = 3;
      //  for (int i = 0; i < enemyCount; i++){
      //      addEnemy(new Enemy(random.nextInt(15), random.nextInt(15)));
      //  }

    }

    /**
     * Metoda odpowiadająca za rysowanie labiryntu
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.translate(35,25);
        /**włączenie antyaliasingu*/
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Toolkit t = Toolkit.getDefaultToolkit();
        /**pobranie obrazku serca*/
        Image heart = t.getImage("res/heart.png");

        if (GameWindow.lives != 0 && GameWindow.isGameOn)
        {
            /**rysowanie serc*/
            for (int i = 0; i < GameWindow.lives; i++)
            {
                g2.drawImage(heart, 600 + (25*i), 500, null);
            }
            /**malowanie labiryntu maze*/
            if(GameWindow.mazeRandom <= 25)
            {
                for (int row = 0; row < Maze.maze.length; row++ )
                {
                    for (int col = 0; col < Maze.maze[0].length; col++)
                    {
                        /**jeśli maze[][] = 1 maluje ścianę, inaczej maluje podłogę*/
                        Image img = switch (Maze.maze[row][col])
                                {
                                    case 1 -> mazeIMG.wall;
                                    default -> mazeIMG.surface;
                                };
                        g2.drawImage(img,30*col, 30*row, 30,30,null);
                    }
                }
                /**wypisywanie słów zależnie od poziomu*/
                if(Words.easyWords)
                {
                    g2.setFont(font1);
                    g2.drawString(GameWindow.wordsITPL.easyWordIT, 163, 125);
                    g2.drawString(GameWindow.wordsITPL.easyWord2IT, 582, 125);
                }
                else if(Words.mediumWords)
                {
                    g2.setFont(font1);
                    g2.drawString(GameWindow.wordsITPL.mediumWordIT, 163, 125);
                    g2.drawString(GameWindow.wordsITPL.mediumWord2IT, 582, 125);
                }
                else if(Words.hardWords)
                {
                    g2.setFont(font1);
                    g2.drawString(GameWindow.wordsITPL.hardWordIT, 163, 125);
                    g2.drawString(GameWindow.wordsITPL.hardWord2IT, 582, 125);
                }
            }
            else
            {
                /**malowanie labiryntu maze2*/
                for (int row = 0; row < Maze.maze2.length; row++ )
                {
                    for (int col = 0; col < Maze.maze2[0].length; col++)
                    {
                        Image img = switch (Maze.maze[row][col])
                                {
                                    case 1 -> mazeIMG.wall;
                                    default -> mazeIMG.surface;
                                };
                        g2.drawImage(img,30*col, 30*row,30,30, null);
                    }
                }
                if(Words.easyWords)
                {
                    g2.setFont(font1);
                    g2.drawString(GameWindow.wordsITPL.easyWord2IT, 163, 125);
                    g2.drawString(GameWindow.wordsITPL.easyWordIT, 590, 125);
                }
                else if(Words.mediumWords)
                {
                    g2.setFont(font1);
                    g2.drawString(GameWindow.wordsITPL.mediumWord2IT, 163, 125);
                    g2.drawString(GameWindow.wordsITPL.mediumWordIT, 590, 125);
                }
                else if(Words.hardWords)
                {
                    g2.setFont(font1);
                    g2.drawString(GameWindow.wordsITPL.hardWord2IT, 163, 125);
                    g2.drawString(GameWindow.wordsITPL.hardWordIT, 590, 125);
                }
            }
            /**malowanie gracza*/
            GameWindow.player.draw(g2);

        /** rysowanie wrogów - działa, wykomentowane na potrzeby prezentacji, nie działa natomiast ruszanie się ich w labiryncie*/
        /*for (int i = 0; i < enemies.size(); i++){
            Enemy temp = enemies.get(i);
        while ((getMap(temp.getX(), temp.getY()) == 1))
        {
            int en = random.nextInt(1,2);
            int eny = random.nextInt(1,2);
            temp.move(en,eny);
        }
            temp.draw(g2);
        }
        if(Words.easyWords) {
            g2.setFont(font1);
            g2.drawString(GameWindow.wordsITPL.easyWordIT, 172, 125);
            g2.drawString(GameWindow.wordsITPL.easyWord2IT, 590, 125);
        } else if(Words.mediumWords) {
            g2.setFont(font1);
            g2.drawString(GameWindow.wordsITPL.mediumWordIT, 172, 125);
            g2.drawString(GameWindow.wordsITPL.mediumWord2IT, 590, 125);
        } else if(Words.hardWords) {
            g2.setFont(font1);
            g2.drawString(GameWindow.wordsITPL.hardWordIT, 172, 125);
            g2.drawString(GameWindow.wordsITPL.hardWord2IT, 590, 125);
        } */


        }
        /**porażka gracza*/
        else
        {
            g2.setFont(new Font("SansSerif",Font.BOLD,50));
            g2.drawString("Przegrałeś", 230,150);
            GameWindow.win = 0;
            GameWindow.isGameOn = false;
            enemies.removeAll(enemies);
        }
    }

    /**
     * Metoda dodająca wrogów do listy
     * @param e
     */
    public void addEnemy(Enemy e){
        enemies.add(e);
    }
    /**getter wrogów w liście*/
    public static ArrayList<Enemy> getEnemyList() {
        return enemies;
    }
}


