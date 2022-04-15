package com.company;
import org.w3c.dom.css.RGBColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.Random;

/**
 * Klasa odpowiedzialna za okienko gry
 * @author Maksymilian Gontarek
 */

public class GameWindow extends JFrame implements ActionListener {

    private final Image img = Toolkit.getDefaultToolkit().getImage("res/tlo2.jpg");

    /**
     * zmienne statyczne
     * jeżeli mazeRandom mniejszy/równy od 25 labirynt maze, jeśli większy labirynt maze2, można poszerzać o dodatkowe labirynty
     */
    public static int mazeRandom, win = 0, lives = 3;
    public static boolean isGameOn = true;

    /**implementowanie innych klas*/
    public static Words wordsITPL = new Words();
    public static Player player = new Player(1,14);
    private paintingGame paintingGame;

    /**implementowanie JComponentów*/
    private final JButton bmenu;
    public JLabel time, word, winLabel;

    /**zmienne boolowskie odpowiedzialne za ruch wrogów*/
    private boolean lewo, prawo, gora, dol;

    /**tworzenie timerów*/
    private Timer timer;
    private final Timer timer2;

    /**zmienne potrzebne do odmierzania czasu oraz jego licznika*/
    private int second = 30, minute = 1;
    private String ddSecond, ddMinute;

    private Random rand;

    /**czcionki oraz format*/
    Font font1 = new Font("Dialog", Font.BOLD + Font.ITALIC, 26);
    Font font2 = new Font("Calibri", Font.BOLD + Font.ITALIC, 15);
    Font font3 = new Font("Dialog", Font.BOLD + Font.ITALIC, 30);
    DecimalFormat dFormat = new DecimalFormat("00");

    /**
     * Zdefiniowanie okienka gry - główny konstruktor
     * @param width
     * @param height
     * @param x
     * @param y
     */
    public GameWindow(int width, int height, int x, int y) {


        /**ustawienia GameWindow*/
        super();
        this.setContentPane(new JPanel(){
            @Override
            public void paintComponent(Graphics graphics) {
                super.paintComponent(graphics);
                graphics.drawImage(img, 0, 0, null);
            }
        });
        rand = new Random();
        paintingGame = new paintingGame(width,height);
        setResizable(false);
        setSize(width,height);
        setTitle("Labirynt");
        setLayout(null);
        setFocusable(true);
        setLocation(x,y);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(paintingGame);
        paintingGame.setBounds(-3,-7,800,600);
        paintingGame.setOpaque(false);
        addKeyListener(new Action());
        setVisible(true);

        /**wywoływanie niezbędnych metod*/
        mazeRandom = rand.nextInt(1,50);
        words();
        setWinLabel();
        labelTime();
        startTime();
        timer.start();

        /**tworzenie obiektu JButton*/
        bmenu = new JButton("Powrót do menu");
        bmenu.setBounds(32,510,140,30);
        add(bmenu, BorderLayout.PAGE_END);
        bmenu.addActionListener(this);

        /**inicjalizacja Timeru odpowiedzialnego za odświeżanie okna*/
        timer2 = new Timer(17,this);
        //Timer timer2 = new Timer(40, e -> {randMove();});
        timer2.setRepeats(true);
        timer2.start();
        addWindowListener(new WindowAdapter() {
            /**
             * Działania związane z zamknięciem okna
             * @param e
             */
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });
    }

    /**metoda odpowiedzialna za generowanie słów*/
    public void words() {
        if(Words.easyWords) {
            wordsITPL.randomEasyWord();
            Words.usedWords.add(Words.easyWordITNumber);
            word = new JLabel(wordsITPL.easyWordPL);
            word.setBounds(357, 512, 150, 30);
            word.setHorizontalAlignment(JLabel.CENTER);
            word.setFont(font1);
            word.setForeground(Color.WHITE);
            add(word, BorderLayout.PAGE_END);
        }
        else if (Words.mediumWords)
        {
            wordsITPL.randomMediumWord();
            Words.usedWords.add(Words.mediumWordITNumber);
            word = new JLabel(wordsITPL.mediumWordPL);
            word.setBounds(350, 512, 150, 30);
            word.setHorizontalAlignment(JLabel.CENTER);
            word.setFont(font1);
            word.setForeground(Color.white);
            add(word, BorderLayout.PAGE_END);
        }
        else if(Words.hardWords)
        {
            wordsITPL.randomHardWord();
            Words.usedWords.add(Words.hardWordITNumber);
            word = new JLabel(wordsITPL.hardWordPL);
            word.setBounds(350, 512, 150, 30);
            word.setHorizontalAlignment(JLabel.CENTER);
            word.setFont(font1);
            word.setForeground(Color.white);
            add(word, BorderLayout.PAGE_END);
        }
    }

    /**metoda odpowiedzialna za tworzenie licznika czasu*/
    public void labelTime() {
        time = new JLabel("01:30");
        time.setBounds(500,504,100,50);
        time.setHorizontalAlignment(JLabel.CENTER);
        time.setFont(font3);
        time.setOpaque(true);
        time.setBackground(new Color(112, 200, 229));
        time.setForeground(Color.BLACK);
        add(time, BorderLayout.PAGE_END);
    }

    /**metoda odpowiedzialna za tworzenie licznika poprawnych słów*/
    public void setWinLabel() {
        if(Words.easyWords)
        {
            winLabel = new JLabel("<html>Liczba poprawnych słów: 0/25<br/>Poziom: Łatwy</html>");
        }
        else if(Words.mediumWords)
        {
            winLabel = new JLabel("<html>Liczba poprawnych słów: 0/25<br/>Poziom: Średni</html>");
        }
        else if(Words.hardWords)
        {
            winLabel = new JLabel("<html>Liczba poprawnych słów: 0/25<br/>Poziom: Trudny<html>");
        }
        winLabel.setBounds(180,485,190,80);
        winLabel.setHorizontalAlignment(JLabel.CENTER);
        winLabel.setFont(font2);
        winLabel.setForeground(Color.WHITE);
        add(winLabel);
    }

    /**metoda aktualizująca licznik słów*/
    public void winCount() {
        if(Words.easyWords)
        {
            winLabel.setText("<html>Liczba poprawnych słów:"+ win + "/25<br/>Poziom: Łatwy</html>");
        }
        else if(Words.mediumWords)
        {
            winLabel.setText("<html>Liczba poprawnych słów:"+ win + "/25<br/>Poziom: Średni</html>");
        }
        else if(Words.hardWords)
        {
            winLabel.setText("<html>Liczba poprawnych słów:"+ win + "/25<br/>Poziom: Trudny</html>");
        }
    }

    /**metoda odpowiedzialna za timer licznika czasu*/
    public void startTime() {
        timer = new Timer(1000, e -> {
          //  randMove();
            second --;
            ddSecond = dFormat.format(second);
            ddMinute = dFormat.format(minute);
            time.setText(ddMinute + ":" + ddSecond);

            if(second==-1)
            {
                second = 59;
                minute--;
                time.setText(ddMinute + ":" + ddSecond);
            }

            if(minute==0 && second==0)
            {
                timer.stop();
            }
        });
        timer.start();
    }

    /**
     * pobieranie współrzędnych
     * @param x
     * @param y
     * @return
     */
    private int getMap(int x, int y){
        return Maze.maze[y][x];
    }

    private int getMap2(int x, int y){
        return Maze.maze2[y][x];
    }
    /** wgrane są tylko dwie mapy w klasie maze jako przykładowe, dla większej ich ilości
    należy wpisywać je w klasie Maze, a następnie tworzyć do nich odpowiednie gettery współrzędnych
     */

    /**nieudana metoda losowego poruszania się wrogów w labiryncie*/
   /* public void randMove() {
        if (!(getMap(enemy.getTileX(), enemy.getTileY() + 1) == 1) && !(getMap(enemy.getTileX(), enemy.getTileY() - 1) == 1) && !(getMap(enemy.getTileX() + 1, enemy.getTileY()) == 1) && !(getMap(enemy.getTileX() - 1, enemy.getTileY()) == 1)){
            int v = rand.nextInt(1,4);
            switch (v){
                case 1:
                    if (!gora) {
                        enemy.move(0, -1);
                        dol = false;
                        lewo = false;
                        prawo = false;
                        gora = true;
                    }
                    break;
                case 2:
                    if (!dol) {
                        enemy.move(0, 1);
                        dol = true;
                        lewo = false;
                        prawo = false;
                        gora = false;
                    }
                    break;
                case 3:
                    if (!lewo) {
                        enemy.move(-1, 0);
                        dol = false;
                        lewo = true;
                        prawo = false;
                        gora = false;
                    }
                    break;
                case 4:
                    if (!prawo) {
                        enemy.move(1, 0);
                        dol = false;
                        lewo = false;
                        prawo = true;
                        gora = false;
                    }
                    break;
                default:
                    break;
            }
        }else if(!(getMap(enemy.getTileX(), enemy.getTileY() + 1) == 1) && !(getMap(enemy.getTileX(), enemy.getTileY() - 1) == 1) && !(getMap(enemy.getTileX() + 1, enemy.getTileY()) == 1)){
            int v = rand.nextInt(1,3);
            switch (v){
                case 1:
                    if (!gora) {
                        enemy.move(0, -1);
                        dol = false;
                        lewo = false;
                        prawo = false;
                        gora = true;
                    }
                    break;
                case 2:
                    if (!dol) {
                        enemy.move(0, 1);
                        dol = true;
                        lewo = false;
                        prawo = false;
                        gora = false;
                    }
                    break;
                case 3:
                    if (!prawo) {
                        enemy.move(1, 0);
                        dol = false;
                        lewo = false;
                        prawo = true;
                        gora = false;
                    }
                    break;
                default:
                    break;
            }
        } else if (!(getMap(enemy.getTileX(), enemy.getTileY() + 1) == 1) && !(getMap(enemy.getTileX(), enemy.getTileY() - 1) == 1) && !(getMap(enemy.getTileX() - 1, enemy.getTileY()) == 1)){
            int v = rand.nextInt(1,3);
            switch (v){
                case 1:
                    if (!gora) {
                        enemy.move(0, -1);
                        dol = false;
                        lewo = false;
                        prawo = false;
                        gora = true;
                    }
                    break;
                case 2:
                    if (!dol) {
                        enemy.move(0, 1);
                        dol = true;
                        lewo = false;
                        prawo = false;
                        gora = false;
                    }
                    break;
                case 3:
                    if (!lewo) {
                        enemy.move(-1, 0);
                        dol = false;
                        lewo = true;
                        prawo = false;
                        gora = false;
                    }
                    break;
                default:
                    break;
            }
        } else if (!(getMap(enemy.getTileX(), enemy.getTileY() + 1) == 1) && !(getMap(enemy.getTileX() + 1, enemy.getTileY()) == 1) && !(getMap(enemy.getTileX() - 1, enemy.getTileY()) == 1)){
            int v = rand.nextInt(1,3);
            switch (v){
                case 1:
                    if (!dol) {
                        enemy.move(0, 1);
                        dol = true;
                        lewo = false;
                        prawo = false;
                        gora = false;
                    }
                    break;
                case 2:
                    if (!prawo) {
                        enemy.move(1, 0);
                        dol = false;
                        lewo = false;
                        prawo = true;
                        gora = false;
                    }
                    break;
                case 3:
                    if (!lewo) {
                        enemy.move(-1, 0);
                        dol = false;
                        lewo = true;
                        prawo = false;
                        gora = false;
                    }
                    break;
                default:
                    break;
            }
        } else if (!(getMap(enemy.getTileX(), enemy.getTileY() - 1) == 1) && !(getMap(enemy.getTileX() + 1, enemy.getTileY()) == 1) && !(getMap(enemy.getTileX() - 1, enemy.getTileY()) == 1)){
            int v = rand.nextInt(1,3);
            switch (v){
                case 1:
                    if (!gora) {
                        enemy.move(0, -1);
                        dol = false;
                        lewo = false;
                        prawo = false;
                        gora = true;
                    }
                    break;
                case 2:
                    if (!prawo) {
                        enemy.move(1, 0);
                        dol = false;
                        lewo = false;
                        prawo = true;
                        gora = false;
                    }
                    break;
                case 3:
                    if (!lewo) {
                        enemy.move(-1, 0);
                        dol = false;
                        lewo = true;
                        prawo = false;
                        gora = false;
                    }
                    break;
                default:
                    break;
            }
        } else if (!(getMap(enemy.getTileX(), enemy.getTileY() + 1) == 1) && !(getMap(enemy.getTileX(), enemy.getTileY() - 1) == 1)) {
          //  dol = false;
          //  lewo = false;
          //  prawo = false;
          //  gora = false;
            int q = rand.nextInt(1,2);
            switch (q) {
                case 1 -> {
                    // if (dol == false) {
                    // enemy.move(0, 1);
                    dol = true;
                    lewo = false;
                    prawo = false;
                    gora = false;
                }
                // }
                case 2 -> {
                    //  if (gora == false) {
                    enemy.move(0, -1);
                    dol = false;
                    lewo = false;
                    prawo = false;
                    gora = true;
                }
                //  }
                default -> {
                }
            }
        }else if (!(getMap(enemy.getTileX(), enemy.getTileY() + 1) == 1) && !(getMap(enemy.getTileX()- 1, enemy.getTileY()) == 1)) {
            dol = false;
            lewo = false;
            prawo = false;
            gora = false;
            int n = rand.nextInt(1,2);
            switch (n) {
                case 1 -> enemy.move(0, 1);
                case 2 -> enemy.move(-1, 0);
                default -> enemy.move(0, 0);
            }
        }else if (!(getMap(enemy.getTileX(), enemy.getTileY() + 1) == 1) && !(getMap(enemy.getTileX() + 1, enemy.getTileY()) == 1)) {
            dol = false;
            lewo = false;
            prawo = false;
            gora = false;
            int n = rand.nextInt(1,2);
            switch (n) {
                case 1 -> enemy.move(0, 1);
                case 2 -> enemy.move(1, 0);
                default -> {
                }
            }
        }else if (!(getMap(enemy.getTileX(), enemy.getTileY() - 1) == 1) && !(getMap(enemy.getTileX() - 1, enemy.getTileY()) == 1)) {
            dol = false;
            lewo = false;
            prawo = false;
            gora = false;
            int n = rand.nextInt(1,2);
            switch (n) {
                case 1 -> enemy.move(0, -1);
                case 2 -> enemy.move(-1, 0);
                default -> {
                }
            }
        }else if (!(getMap(enemy.getTileX(), enemy.getTileY() - 1) == 1) && !(getMap(enemy.getTileX() + 1, enemy.getTileY()) == 1)) {
            dol = false;
            lewo = false;
            prawo = false;
            gora = false;
            int n = rand.nextInt(1,2);
            switch (n) {
                case 1 -> enemy.move(0, -1);
                case 2 -> enemy.move(1, 0);
                default -> {
                }
            }
        }else if (!(getMap(enemy.getTileX() - 1, enemy.getTileY()) == 1) && !(getMap(enemy.getTileX() + 1, enemy.getTileY()) == 1)) {
            dol = false;
            lewo = false;
            prawo = false;
            gora = false;
            int m = rand.nextInt(3,4);
            switch (m) {
                case 3 -> enemy.move(-1, 0);
                case 4 -> enemy.move(1, 0);
                default -> {
                }
            }
        }else if (!(getMap(enemy.getTileX() - 1, enemy.getTileY()) == 1)){
            dol = false;
            lewo = false;
            prawo = false;
            gora = false;
            enemy.move(-1, 0);
        }else if (!(getMap(enemy.getTileX() + 1, enemy.getTileY()) == 1)){
            dol = false;
            lewo = false;
            prawo = false;
            gora = false;
            enemy.move(1 , 0);
        }else if (!(getMap(enemy.getTileX(), enemy.getTileY() - 1) == 1)){
            dol = false;
            lewo = false;
            prawo = false;
            gora = false;
            enemy.move(0,-1);
        }else if (!(getMap(enemy.getTileX(),enemy.getTileY() + 1) == 1)){
            dol = false;
            lewo = false;
            prawo = false;
            gora = false;
            enemy.move(0, 1);
        }
    } */

    /**metoda odpowiedzialna za poruszanie się gracza*/
    public class Action extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if (mazeRandom <= 25) {
                if (key == KeyEvent.VK_W) {
                    /**pierwszy labirynt ponieważ getMap oraz mazeRandom mniejszy/równy od 25*/
                    if (!(getMap(player.getX(), player.getY() - 1) == 1)) {
                        player.move(0, -1);
                    }
                }
                if (key == KeyEvent.VK_A) {
                    if (!(getMap(player.getX() - 1, player.getY()) == 1)) {
                        player.move(-1, 0);
                    }
                }
                if (key == KeyEvent.VK_D) {
                    if (!(getMap(player.getX() + 1, player.getY()) == 1)) {
                        player.move(1, 0);
                    }
                }
                if (key == KeyEvent.VK_S) {
                    if (!(getMap(player.getX(), player.getY() + 1) == 1)) {
                        player.move(0, 1);
                    }
                }
            }
            else
            {
                if (key == KeyEvent.VK_W) {
                    /**drugi labirynt ponieważ getMap2 oraz mazeRandom większy od 25*/
                    if (!(getMap2(player.getX(), player.getY() - 1) == 1)) {
                        player.move(0, -1);
                    }
                }
                if (key == KeyEvent.VK_A) {
                    if (!(getMap2(player.getX() - 1, player.getY()) == 1)) {
                        player.move(-1, 0);
                    }
                }
                if (key == KeyEvent.VK_D) {
                    if (!(getMap2(player.getX() + 1, player.getY()) == 1)) {
                        player.move(1, 0);
                    }
                }
                if (key == KeyEvent.VK_S) {
                    if (!(getMap2(player.getX(), player.getY() + 1) == 1)) {
                        player.move(0, 1);
                    }
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        /**przegrana w przypadku końca czasu*/
        if (second == 0 && minute == 0)
        {
            isGameOn = false;
            timer2.stop();
            time.setText("00:00");
            repaint();
            Words.usedWords.removeAll(Words.usedWords);
            int odp = JOptionPane.showConfirmDialog(null, "Czy chcesz zacząć od nowa?", "Pytanie", JOptionPane.YES_NO_OPTION);

            if (odp == JOptionPane.YES_OPTION)
            {
                lives = 3;
                isGameOn = true;
                win = 0;
                winCount();
                word.setText("");
                words();
                second = 30;
                minute = 1;
                time.setText("01:30");
                timer.start();
                timer2.start();
                player = new Player(1,14);
            }
            else
            {
                menu m = new menu();
                m.setVisible(true);
                dispose();
            }
        }
        /**przegranie w skutek utraty wszystkich żyć*/
        if (lives == 0)
        {
            timer2.stop();
            timer.stop();
            time.setText("00:00");
            Words.usedWords.removeAll(Words.usedWords);
            repaint();
            int odp = JOptionPane.showConfirmDialog(null, "Czy chcesz zacząć od nowa?", "Pytanie", JOptionPane.YES_NO_OPTION);

            if (odp == JOptionPane.YES_OPTION)
            {
                lives = 3;
                isGameOn = true;
                win = 0;
                winCount();
                word.setText("");
                words();
                second = 30;
                minute = 1;
                time.setText("01:30");
                timer.start();
                timer2.start();
                player = new Player(1,14);
            }
            else
            {
                menu m = new menu();
                m.setVisible(true);
                dispose();
            }
        }

        if(mazeRandom <= 25)
        {
            /**poprawne tłumaczenia są na polach oznaczonych "2"*/
            if ((getMap(player.getX(), player.getY()) == 2))
            {
                /**przy 25 wykonanych tłumaczeniach przechodzi poziom wyżej*/
                if (win < 24)
                {
                    rand = new Random();
                    timer.stop();
                    timer2.stop();
                    win++;
                }
                else if (Words.easyWords)
                {
                    rand = new Random();
                    timer.stop();
                    timer2.stop();
                    win = 0;
                    Words.easyWords = false;
                    Words.mediumWords = true;
                    Words.hardWords = false;
                    Words.usedWords.removeAll(Words.usedWords);
                }
                else if (Words.mediumWords)
                {
                    rand = new Random();
                    timer.stop();
                    timer2.stop();
                    win = 0;
                    Words.easyWords = false;
                    Words.mediumWords = false;
                    Words.hardWords = true;
                    Words.usedWords.removeAll(Words.usedWords);
                }
                /**po przejściu poziomu trudnego koniec gry*/
                else if(Words.hardWords)
                {
                    timer.stop();
                    timer2.stop();
                    win = 0;
                    Words.easyWords = false;
                    Words.mediumWords = false;
                    Words.hardWords = false;
                    Words.usedWords.removeAll(Words.usedWords);
                    JOptionPane.showMessageDialog(null,"Gratulacje! Przeszedłeś najtrudniejszy poziom, a zatem nic tu po tobie :)","Koniec", JOptionPane.OK_OPTION);
                    repaint();
                    isGameOn = true;
                    Words.usedWords.removeAll(Words.usedWords);
                    mazeRandom = rand.nextInt(1, 50);
                    second = 30;
                    minute = 1;
                    time.setText("01:30");
                    timer.start();
                    timer2.start();
                    winCount();
                    menu m = new menu();
                    m.setVisible(true);
                    dispose();
                }

                repaint();
                word.setText("");
                words();
                isGameOn = true;
                mazeRandom = rand.nextInt(1, 50);
                second = 30;
                minute = 1;
                time.setText("01:30");
                timer.start();
                timer2.start();
                winCount();
                player = new Player(1, 14);
            }
        }
        else
        {
            /**to co wyżej ale dla drugiego labiryntu*/
            if ((getMap2(player.getX(), player.getY()) == 2))
            {
                if (win < 24)
                {
                    rand = new Random();
                    timer.stop();
                    timer2.stop();
                    win++;
                }
                else if (Words.easyWords)
                {
                    rand = new Random();
                    timer.stop();
                    timer2.stop();
                    win = 0;
                    Words.easyWords = false;
                    Words.mediumWords = true;
                    Words.hardWords = false;
                    Words.usedWords.removeAll(Words.usedWords);
                }
                else if(Words.mediumWords)
                {
                    rand = new Random();
                    timer.stop();
                    timer2.stop();
                    win = 0;
                    Words.easyWords = false;
                    Words.mediumWords = false;
                    Words.hardWords = true;
                    Words.usedWords.removeAll(Words.usedWords);
                }
                else if(Words.hardWords)
                {
                    rand = new Random();
                    timer.stop();
                    timer2.stop();
                    win = 0;
                    Words.easyWords = false;
                    Words.mediumWords = false;
                    Words.hardWords = false;
                    Words.usedWords.removeAll(Words.usedWords);
                    JOptionPane.showMessageDialog(null,"Gratulacje! Przeszedłeś najtrudniejszy poziom, a zatem nic tu po tobie :)","Koniec", JOptionPane.OK_OPTION);
                    repaint();
                    word.setText("");
                    words();
                    isGameOn = true;
                    mazeRandom = rand.nextInt(1, 50);
                    second = 30;
                    minute = 1;
                    time.setText("01:30");
                    timer.start();
                    timer2.start();
                    winCount();
                    menu m = new menu();
                    m.setVisible(true);
                    dispose();
                }
                repaint();
                word.setText("");
                words();
                isGameOn = true;
                mazeRandom = rand.nextInt(1, 50);
                second = 30;
                minute = 1;
                time.setText("01:30");
                timer.start();
                timer2.start();
                winCount();
                player = new Player(1, 14);
            }
        }

        if(mazeRandom < 25)
        {
            /**strata życia w przypadku wejścia na pole "3", czyli błędne tłumaczenie*/
            if ((getMap(player.getX(), player.getY()) == 3))
            {
                lives--;
                isGameOn = true;
                player = new Player(1,14);
            }
        }
        else if(mazeRandom > 25)
        {
            /**to co wyżej dla drugiego labiryntu*/
            if ((getMap2(player.getX(), player.getY()) == 3))
            {
                lives--;
                isGameOn = true;
                player = new Player(1,14);
            }
        }

       Object source = e.getSource();
        /**wyjście do menu*/
        if (source == bmenu)
        {
            menu m = new menu();
            m.setVisible(true);
            dispose();
        }
    }
}
