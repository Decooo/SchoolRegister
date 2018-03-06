package dziennikelektroniczny.custom;

/**
 *
 * @author Jakub
 */
public class PoprawnyEmail {

    public static boolean czyEmailJestPoprawny(String email) {
        if (email.contains("@")) {
            return true;
        } else {
            return false;
        }
    }

}
