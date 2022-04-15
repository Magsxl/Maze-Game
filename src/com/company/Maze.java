package com.company;

import javax.swing.*;
import java.awt.*;

/**
 * Klasa przechowująca zmienne dotyczące labiryntu
 * @author Maksymilian Gontarek
 */
public class Maze {
    /**
     *dwuwymiarowe tablice przechowujące układ labiryntu
     *"1"-ściana, "0"-podłoga, "2"-poprawne tłumaczenie, "3"-złe tłumaczenie
     */
    public static int [][] maze =
        {       {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {1,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,1,0,0,0,0,0,0,1},
                {1,0,1,0,1,1,1,0,1,1,0,0,0,0,1,0,0,0,1,1,1,1,1,1},
                {1,0,1,0,1,2,2,2,2,1,1,1,1,1,1,1,1,0,1,3,3,3,3,1},
                {1,0,0,0,1,2,2,2,2,1,0,1,0,0,0,0,0,0,0,3,3,3,3,1},
                {1,1,1,1,1,1,1,1,1,1,0,1,0,1,0,1,1,1,1,1,1,1,1,1},
                {1,0,0,0,1,0,0,0,0,0,0,1,0,1,0,1,0,0,0,1,0,0,0,1},
                {1,0,1,1,1,0,1,1,1,0,1,1,0,1,0,1,0,1,0,0,0,1,0,1},
                {1,0,0,0,1,0,1,0,0,0,0,0,0,1,0,1,1,1,1,0,1,1,0,1},
                {1,1,1,0,1,1,1,0,1,1,1,1,1,1,0,1,0,0,0,0,0,1,0,1},
                {1,0,1,0,0,0,1,0,0,0,0,0,0,0,0,1,0,1,1,1,1,1,0,1},
                {1,0,1,0,1,0,0,0,1,1,1,1,1,1,1,1,0,1,0,0,0,0,0,1},
                {1,0,1,1,1,0,1,0,0,1,0,0,0,0,0,1,0,1,1,1,1,1,0,1},
                {1,0,1,0,0,0,1,1,1,1,0,1,0,1,1,1,0,1,1,1,0,0,0,1},
                {1,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1,0,1},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
        };
    public static int [][] maze2 =
        {       {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {1,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,1,0,0,0,0,0,0,1},
                {1,0,1,0,1,1,1,0,1,1,0,0,0,0,1,0,0,0,1,1,1,1,1,1},
                {1,0,1,0,1,3,3,3,3,1,1,1,1,1,1,1,1,0,1,2,2,2,2,1},
                {1,0,0,0,1,3,3,3,3,1,0,1,0,0,0,0,0,0,0,2,2,2,2,1},
                {1,1,1,1,1,1,1,1,1,1,0,1,0,1,0,1,1,1,1,1,1,1,1,1},
                {1,0,0,0,1,0,0,0,0,0,0,1,0,1,0,1,0,0,0,1,0,0,0,1},
                {1,0,1,1,1,0,1,1,1,0,1,1,0,1,0,1,0,1,0,0,0,1,0,1},
                {1,0,0,0,1,0,1,0,0,0,0,0,0,1,0,1,1,1,1,0,1,1,0,1},
                {1,1,1,0,1,1,1,0,1,1,1,1,1,1,0,1,0,0,0,0,0,1,0,1},
                {1,0,1,0,0,0,1,0,0,0,0,0,0,0,0,1,0,1,1,1,1,1,0,1},
                {1,0,1,0,1,0,0,0,1,1,1,1,1,1,1,1,0,1,0,0,0,0,0,1},
                {1,0,1,1,1,0,1,0,0,1,0,0,0,0,0,1,0,1,1,1,1,1,0,1},
                {1,0,1,0,0,0,1,1,1,1,0,1,0,1,1,1,0,1,1,1,0,0,0,1},
                {1,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1,0,1},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
        };
    /**zmienne przechowujące wygląd ścian oraz podłogi*/
    public Image wall;
    public Image surface;

    public Maze() {
        /**pobieranie grafik*/
        ImageIcon img = new ImageIcon("res/lod.png");
        ImageIcon img2= new ImageIcon("res/podloga.png");

        surface = img2.getImage();
        wall = img.getImage();
    }
}

   

