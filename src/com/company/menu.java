package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Klasa odpowiadająca za okienko menu
 * @author Maksymilian Gontarek
 */
public class menu extends JFrame implements ActionListener {

    private final JButton difficulty, exit, StartGame;

    /**zmienne odpowiadające za wygląd*/
    private final int gameWidth=800;
    private final int gameHeight=600;
    private final int screenWidth=Toolkit.getDefaultToolkit().getScreenSize().width;
    private final int screenHeight=Toolkit.getDefaultToolkit().getScreenSize().height;
    private final int xCenter=(screenWidth-gameWidth)/2;
    private final int yCenter=(screenHeight-gameHeight)/2;
    /**zmienna określająca czy użytkownik wybrał poziom*/
    public static boolean isLevelChosen;

    public menu()
    {
        /**ustawienia menu*/
        setResizable(false);
        setSize(800,600);
        setTitle("Labirynt");
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(530,200);
        setVisible(true);
        /**dodawanie tła*/
        setContentPane(new JLabel(new ImageIcon("res/tlo2.jpg")));
        JLabel background = new JLabel();
        add(background);

        /**inicjalizacja JButtonów*/
        difficulty = new JButton("Wybierz poziom");
        difficulty.setFont(new Font("Monospace",Font.ITALIC + Font.BOLD,24));
        difficulty.setBounds(250,100,300,100);
        add(difficulty);
        difficulty.addActionListener(this);

        exit = new JButton("Wyjście");
        exit.setFont(new Font("Monospace",Font.ITALIC + Font.BOLD,24));
        exit.setBounds(250,400,300,100);
        add(exit);
        exit.addActionListener(this);

        StartGame = new JButton("Rozpocznij grę");
        StartGame.setFont(new Font("Monospace",Font.ITALIC + Font.BOLD,24));
        StartGame.setBounds(250,250,300,100);
        add(StartGame);
        StartGame.addActionListener(this);
        /**dodanie napisu na szczycie*/
        JLabel title = new JLabel("Gra Labirynt");
        title.setBounds(250, 20, 300, 50);
        title.setFont(new Font("SansSerif", Font.BOLD + Font.ITALIC, 50));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setForeground(Color.white);
        add(title);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == difficulty)
        {
            LevelWindow levelWindow = new LevelWindow();
            levelWindow.setVisible(true);
            dispose();
        }
        else if (source == StartGame)
        {
            /**startuje tylko jeśli level został wybrany, jeśli nie to musi wybrać*/
           if(isLevelChosen)
           {
               GameWindow gameWindow = new GameWindow(800, 600, xCenter, yCenter);
               GameWindow.lives = 3;
               GameWindow.win = 0;
               GameWindow.isGameOn = true;
               gameWindow.setVisible(true);
               dispose();
               isLevelChosen = false;
           }
           else
           {
               JOptionPane.showConfirmDialog(null,"Wybierz poziom trudności","Ostrzeżenie", JOptionPane.DEFAULT_OPTION);
               LevelWindow levelWindow = new LevelWindow();
               levelWindow.setVisible(true);
               dispose();
           }
        }
        /**wyjście*/
        else if (source == exit)
        {
            int odp = JOptionPane.showConfirmDialog(null, "Czy na pewno chcesz wyjść?", "Pytanie", JOptionPane.YES_NO_OPTION);
            if(odp == JOptionPane.YES_OPTION)
            {
                dispose();
            }
        }
    }
}
