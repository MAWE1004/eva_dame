package modelle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Anmeldung {

    public static boolean checkIfUsernameAndPasswordCompatible(String username, String password) throws FileNotFoundException {
        String line;
        String uname;
        String pword;
        Scanner read = new Scanner(new File("anmeldung.txt"));
        while (read.hasNextLine()){
            line = read.nextLine();
            if(line.charAt(0) == 'u') {
                uname = line.substring(3);
                //System.out.println(uname);
                if (uname.toLowerCase().equals(username.toLowerCase())) {
                    System.out.println(uname);
                    line = read.nextLine();
                    pword = line.substring(3);
                    System.out.println(pword);
                    if(pword.equals(password)){
                        System.out.println("Username und Passwort stimmen überein!");
                        return true;
                    }else{
                        System.out.println("Username oder Passwort falsch!");
                        return false;
                    }
                }
            }
        }
        System.out.println("Keinen Usernamen gefunden!");
        return false;
    }
    public static void main(String[]args) throws FileNotFoundException {
        checkIfUsernameAndPasswordCompatible("Haare8", "Katze7");
    }
}
