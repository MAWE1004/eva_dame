package modelle;

import java.io.*;
import java.util.Locale;
import java.util.Scanner;

public class Registrierung {

    public Registrierung(){
    }

    public static boolean checkIfUsernameVorhanden(String username) throws FileNotFoundException {
        String line;
        Scanner read = new Scanner(new File("anmeldung.txt"));
        while (read.hasNextLine()){
            line = read.nextLine();
            if(line.charAt(0) == 'u') {
                line = line.substring(3);
                System.out.println(line);
                if (line.toLowerCase().equals(username.toLowerCase())) {
                    System.out.println("Username schon vorhanden!");
                    return true;
                }
            }
        }
        return false;
    }

    public static void speichereRegistrierung(String username, String password){
        if(username.isEmpty() && password.isEmpty()){
            System.out.println("Username und Passwort dürfen nicht leer sein!");
        }
        try {
            if (checkIfUsernameVorhanden(username) == false && !username.isEmpty() && !password.isEmpty()){
                FileWriter myWriter = new FileWriter("anmeldung.txt", true);
                myWriter.write("u: " + username + '\n');
                myWriter.write("p: " + password + '\n');
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static void main(String[]args) throws FileNotFoundException {
//        speichereRegistrierung("King", "King");
//        speichereRegistrierung("King", "King");
        speichereRegistrierung("karl", "King");
        speichereRegistrierung("Hensel", "King");
        speichereRegistrierung("Hensel1", "King");
        speichereRegistrierung("Hensel2", "King");
        speichereRegistrierung("Hensel3", "King");
        speichereRegistrierung("Hensel4", "King");

    }
}
