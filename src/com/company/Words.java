package com.company;
import java.util.ArrayList;
import java.util.Random;

/**
 * Klasa odpowiedzialna za przechowywanie tablic zawierających słowa oraz metod losujących je
 * @author Maksymilian Gontarek
 */
public class Words {
    /**tablice zawierające słowa, [i] po włosku odpowiada swojemu tłumaczeniu [i] po polsku*/
    public static String [] easyItalianWords = {"la Mucca", "il Cuoco", "l'Uovo", "il Latte", "l'Aglio", "il Gelato", "il Lemone", "la Tazza", "il Cavallo", "il Delfino", "il Piatto", "il Formaggio", "la Crema",
    "il Succo", "la Gonna", "la Borsa", "il Pepe", "la Cipolla", "il Cibo", "il Leone", "l'Ape", "la Zuppa", "la Farfalla", "il Vestito", "l'Uccello"};

    public static String [] easyPolishWords = {"Krowa", "Kucharz", "Jajko", "Mleko", "Czosnek", "Lody", "Cytryna", "Kubek", "Koń", "Delfin", "Talerz", "Ser", "Krem", "Sok", "Spódnica", "Torebka", "Pieprz", "Cebula",
    "Jedzenie", "Lew", "Pszczoła", "Zupa", "Motyl", "Sukienka", "Ptak"};

    public static String [] mediumItalianWords = {"la Finestra", "la Carota", "la Birra", "la Frutta", "l'Olio", "l'Orso", "la Pasta", "la Salsicia", "la Patata", "il Pesce", "il Cane", "il Gatto", "la Carne", "la Camicia",
    "la Domanda", "i Vestiti", "il Panino", "il Riso", "la Ricetta", "il Pomodoro", "la Bottiglia", "il Sandale", "la Cena", "il Portafoglio", "il Burro"};

    public static String [] mediumPolishWords = {"Okno", "Marchewka", "Piwo", "Owoc", "Olej", "Niedźwiedź", "Makaron", "Parówka", "Ziemniak", "Ryba", "Pies", "Kot", "Mięso", "Koszula", "Pytanie", "Ubrania", "Kanapka",
    "Ryż", "Przepis", "Pomidor", "Butelka", "Sandały", "Obiad", "Portfel", "Masło"};

    public static String [] hardItalianWords = {"l'Arancia", "l'Orologio", "l'Ombrello", "la Giacca", "il Pollo", "la Colazione", "il Biscotto", "il Cioccolato", "la Maglia", "i Pantaloni", "l'Abito", "la Cucina",
    "il Ponte", "l'Ingrediente", "la Fragola", "il Lupo", "il Ragno", "l'Anatra", "il Topo", "il Cameriere", "il Cappello", "la Formica", "l'Uva", "il Manzo", "la Stamattina"};

    public static String [] hardPolishWords = {"Pomarańcza", "Zegar", "Parasol", "Kurtka", "Kurczak", "Śniadanie", "Ciastko", "Czekolada", "Sweter", "Spodnie", "Garnitur", "Kuchnia", "Most",  "Składniki", "Truskawka",
    "Wilk", "Pająk", "Kaczka", "Mysz", "Kelner", "Czapka", "Mrówka", "Winogrono", "Wołowina", "Poranek"};

    public Random random = new Random();

    /**zmienne do określania słów*/
    public static int easyWordITNumber;
    public String easyWordIT;
    public int easyWord2ITNumber;
    public String easyWord2IT;
    public static int mediumWordITNumber;
    public String mediumWordIT;
    public int mediumWord2ITNumber;
    public String mediumWord2IT;
    public static int hardWordITNumber;
    public String hardWordIT;
    public int hardWord2ITNumber;
    public String hardWord2IT;

    /**zmienne określające jaki poziom trudności wybrał użytkownik*/
    public String easyWordPL;
    public String mediumWordPL;
    public String hardWordPL;
    /**lista w celu uniknięcia powtarzania się słów*/
    public static ArrayList<Integer> usedWords = new ArrayList<>();

    public static boolean easyWords, mediumWords, hardWords;

    /**metody losujące słowa z odpowiednich poziomów*/
    public void randomEasyWord() {
        if(easyWords)
        {
            easyWordITNumber = random.nextInt(easyItalianWords.length);
            easyWord2ITNumber = random.nextInt(easyItalianWords.length);
            easyWordPL = easyPolishWords[easyWordITNumber];
            easyWordIT = easyItalianWords[easyWordITNumber];
            easyWord2IT = easyItalianWords[easyWord2ITNumber];
            /**unikanie powtarzania się słów oraz tych samych słów w obu prostokątach*/
            while (easyWordITNumber == easyWord2ITNumber || usedWords.contains(easyWordITNumber))
            {
                randomEasyWord();
            }
        }
    }

    public void randomMediumWord() {
        if(mediumWords)
        {
            mediumWordITNumber = random.nextInt(mediumItalianWords.length);
            mediumWord2ITNumber = random.nextInt(mediumItalianWords.length);
            mediumWordPL = mediumPolishWords[mediumWordITNumber];
            mediumWordIT = mediumItalianWords[mediumWordITNumber];
            mediumWord2IT = mediumItalianWords[mediumWord2ITNumber];
            while (mediumWordITNumber == mediumWord2ITNumber || usedWords.contains(mediumWordITNumber))
            {
                randomMediumWord();
            }
        }
    }

    public void randomHardWord() {
        if (hardWords)
        {
            hardWordITNumber = random.nextInt(hardItalianWords.length);
            hardWord2ITNumber = random.nextInt(hardItalianWords.length);
            hardWordPL = hardPolishWords[hardWordITNumber];
            hardWordIT = hardItalianWords[hardWordITNumber];
            hardWord2IT = hardItalianWords[hardWord2ITNumber];
            while (hardWordITNumber == hardWord2ITNumber || usedWords.contains(hardWordITNumber))
            {
                randomHardWord();
            }
        }
    }
}
