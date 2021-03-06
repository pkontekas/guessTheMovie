package guessthemovie;
/**
  * @author pkontekas
 */
public class Validator {
    
        public static boolean validateAbc(char cha)
        //validate if character is alphabetic or not
        {
            return !(cha >= 'A' && cha <= 'Z');
        }
        
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
}