package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.Rectangle;

/**
 * Klasa odpowiadająca za tworzenie wroga
 * @author Maksymilian Gontarek
 */
public class Enemy extends JPanel{
    /**zmienne określające położenie wroga*/
    public int x, y;
    /**zmienna przechowująca wygląd wroga*/
    private final Image enemy;
    /**
     * Zdefiniowanie położenia wroga - główny konstruktor
     * @param x
     * @param y
     */
    public Enemy (int x, int y) {
        this.x = x;
        this.y = y;

        ImageIcon img = new ImageIcon("res/enemy.png");
        enemy = img.getImage();
    }
    /**getter wyglądu wroga*/
    public Image getEnemy() {
        return enemy;
    }
    /**getter zmiennej x*/
    public int getX() {
        return x;
    }
    /**getter zmiennej y*/
    public int getY() {
        return y;
    }

    /**
     *Metoda rysująca wroga w labiryncie
     * @param g
     */
    public void draw(Graphics2D g){
        g.drawImage(getEnemy(),x * 30,y * 30, null);
    }
    /**metoda rysująca kwadrat wokół wroga na potrzeby kolizji*/
    public Rectangle getBounds() {
        return new Rectangle(x*30,y*30,30, 30);
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
    }
}
