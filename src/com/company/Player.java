package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
/**
 * Klasa odpowiadająca za tworzenie gracza
 * @author Maksymilian Gontarek
 */
public class Player extends JPanel {
    /**zmienne określające położenie gracza*/
    public int x, y;
    /**zmienna przechowująca wygląd gracza*/
    private final Image player;
    /**
     * Zdefiniowanie położenia gracza - główny konstruktor
     * @param x
     * @param y
     */
    public Player (int x, int y) {
        this.x = x;
        this.y = y;

        ImageIcon img = new ImageIcon("res/pingwina.png");
        player = img.getImage();
    }
    /**getter wyglądu wroga*/
    public Image getPlayer() {
        return player;
    }
    /**getter zmiennej x*/
    public int getX() {
        return x;
    }
    /**getter zmiennej y*/
    public int getY() {
        return y;
    }
    /**metoda odpowiadająca za rysowanie gracza*/
    public void draw(Graphics2D g){
        g.drawImage(getPlayer(),x * 30,y * 30, null);
    }
    /**metoda odpowiadająca za kolizje z wrogami*/
    public void checkColl() {
        ArrayList<Enemy> enemies = paintingGame.getEnemyList();

        for (int i = 0; i < enemies.size(); i++)
        {
            Enemy temp = enemies.get(i);
            /**sprawdzamy czy rectangle gracza i wroga się pokrywają*/
            if (getBounds().intersects(temp.getBounds()))
            {
                /**odejmowanie jednego życia*/
                GameWindow.lives--;
            }
        }
    }
    /**metoda rysująca kwadrat wokół gracza na potrzeby kolizji*/
    public Rectangle getBounds() {
        return new Rectangle(x * 30,y * 30,30, 30);
    }

    /**
     * Metoda odpowiadająca za ruch
     * @param dx
     * @param dy
     */
    @Override
    public void move(int dx, int dy) {
        x += dx;
        y += dy;

        checkColl();
    }
}
