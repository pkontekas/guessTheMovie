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
}