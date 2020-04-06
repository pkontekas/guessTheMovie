package guessthemovie;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
/**
 * @author pkontekas
 */
public class GuessTheMovie {
    /**
     * @param args the command line arguments
     */ 
        static boolean checkContains(char guess, char[] origArr)
        //check if the character guessed belongs in the character array of the selected movie
    {
        for (char x : origArr)
        {
        if (x == guess)
            return true;
        }
        return false;
    }
        
        static void fillIn(char guessedChar, char[] newArr, char[] origArr)
        //fill the empty character array with the character guessed wherever it is required
        {
            for (int i=0; i<newArr.length; i++)
            {
                if (origArr[i] == guessedChar)
                    newArr[i] = guessedChar;
            }
        }
        
        static String getRandomMovie(ArrayList<String> movieList)
        //get a random movie as a string from a String arraylist with all the available movies
        {
            Random rand = new Random();
            return movieList.get(rand.nextInt(movieList.size()));
        }
        
        //original Arrays.toString was not good enough had to edit it to my needs
        public static String customCharArrayToString(char[] c)
        {
            if (c == null)
            {
                return "null";
            }
            int iMax = c.length - 1;
            if (iMax == -1)
                return "[]";
            StringBuilder b = new StringBuilder();
            for (int i = 0; ; i++)
            {
                b.append(c[i]);
                if (i == iMax)
                    return b.toString();
                if (c[i]==' ')
                    b.append("   ");
                else
                    b.append(" ");
            }
        }

    public static void main(String[] args) throws FileNotFoundException
    {
        // guess the movie game up to 8 tries total !!
        // how to read a text file of movies and then fill an Array List with them
        File file = new File("movie_list.txt");
        ArrayList<String> movieList;
            try (Scanner scanFile = new Scanner(file))
            {
                movieList = new ArrayList<>();
                while (scanFile.hasNext())
                    movieList.add(scanFile.nextLine());
            }
        //select a random movie from the array list with a random method
        String title = getRandomMovie(movieList);
        title = title.toUpperCase();
        char[] movieCharArray = title.toCharArray();
        char[] hiddenCharArray = title.toCharArray();
        int wordCount = 1;
        System.out.println("Welcome to the Guess the Movie game !!\nA Random Top Rated Movie from imdb was selected! Do you feel lucky???");
        for (int characters=0; characters<hiddenCharArray.length; characters++)
        {
            //to account for the spaces between words, letter position characters are filled with underscores
            if (movieCharArray[characters]==' ')
            {
                hiddenCharArray[characters]= ' ';
                wordCount++;
            }
            else
                hiddenCharArray[characters]= '_';
        }
        int mistakeCount=0;
        String mistakes = "";
        Scanner scan = new Scanner(System.in);
        while ((Arrays.equals(movieCharArray,hiddenCharArray)==false) && (mistakeCount<8))
        {
            System.out.println("You are now guessing a movie with " + wordCount + " words :\n" + customCharArrayToString(hiddenCharArray));
            System.out.print("You have guessed ( "+mistakeCount+" ) wrong letters: "+mistakes+"\n Guess a letter: ");
            char letter = Character.toUpperCase(scan.next().charAt(0));
            while (Validator.validateAbc(letter)==true)
            {
                System.out.println("Wrong input please try again with only Alphabetic values!");
                letter = Character.toUpperCase(scan.next().charAt(0));
            }
            boolean check = checkContains(letter, movieCharArray);
            if (check==true)
                fillIn(letter, hiddenCharArray, movieCharArray);
            else
            {
                //add mistakes in a String with a , separation
                if (mistakeCount==0)
                {
                    mistakes = mistakes+letter;
                    mistakeCount++;
                }
                else if (!mistakes.contains(Character.toString(letter)))
                    {
                            mistakes = mistakes + " , " + letter;
                            mistakeCount++;
                    }
            }
        }
        if (Arrays.equals(movieCharArray,hiddenCharArray))
            System.out.println("You Win!\n You have guessed "+title+" correctly.");
        else
            System.out.println("You lost all your guesses !! Better luck next time!\nThe Movie you were trying to guess is "+title+"!!");
    }
}