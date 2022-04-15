package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Klasa odpowiadająca za okienko wyboru poziomu
 * @author Maksymilian Gontarek
 */
public class LevelWindow extends JFrame implements  ActionListener{

    /**przyciski do wyboru poziomu*/
    private final JButton bmenu, beasy, bmid, bhard;
    menu m = new menu();

    /**
     * Zdefiniowanie okienka wyboru poziomów
     */
    public LevelWindow()
    {
        setLayout(null);
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(530,200);
        setResizable(false);
        setTitle("Labirynt");
        setVisible(true);

        setContentPane(new JLabel(new ImageIcon("res/tlo2.jpg")));
        JLabel background = new JLabel();
        add(background);
        bmenu = new JButton("Powrót do menu");
        bmenu.setBounds(20,520,140,30);
        bmenu.setOpaque(false);
        add(bmenu);
        bmenu.addActionListener(this);

        beasy = new JButton("Łatwy");
        beasy.setBounds(80,200,200,100);
        add(beasy);
        beasy.addActionListener(this);

        bmid = new JButton("Średni");
        bmid.setBounds(300,200,200,100);
        add(bmid);
        bmid.addActionListener(this);

        bhard = new JButton("Trudny");
        bhard.setBounds(520,200,200,100);
        add(bhard);
        bhard.addActionListener(this);

        JLabel title = new JLabel("Wybierz poziom trudności słownictwa");
        title.setBounds(145, 50, 600, 50);
        title.setFont(new Font("SansSerif", Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        add(title);


        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });
    }

    /**
     * Akcja w wyniku naciśnięcia JButtona
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == bmenu)
        {
            menu m = new menu();
            m.setVisible(true);
            dispose();
        }
        else if (source == beasy)
        {
            /**wybieranie poziomu trudności*/
            Words.easyWords = true;
            Words.mediumWords = false;
            Words.hardWords = false;
            menu.isLevelChosen = true;
            m.setVisible(true);
            dispose();
        }
        else if (source == bmid)
        {
            Words.easyWords = false;
            Words.mediumWords = true;
            Words.hardWords = false;
            menu.isLevelChosen = true;
            m.setVisible(true);
            dispose();
        }
        else if (source == bhard)
        {
            Words.easyWords = false;
            Words.mediumWords = false;
            Words.hardWords = true;
            menu.isLevelChosen = true;
            m.setVisible(true);
            dispose();
        }
    }
}
