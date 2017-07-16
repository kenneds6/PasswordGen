package sample;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.Random;


public class Controller {
    public TextField lengthField;
    public TextField specialCharField;
    public TextField numberField;
    public TextField resultPW;
    private static final String symbols =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789$&@?<>~!%#";



    public static String randString(Random r) {
        while(true) {
            char[] password = new char[r.nextBoolean()?12:13];
            boolean hasUpper = false, hasLower = false, hasDigit = false, hasSpecial = false;
            for(int i=0; i<password.length; i++) {
                char ch = symbols.charAt(r.nextInt(symbols.length()));
                if(Character.isUpperCase(ch))
                    hasUpper = true;
                else if(Character.isLowerCase(ch))
                    hasLower = true;
                else if(Character.isDigit(ch))
                    hasDigit = true;
                else
                    hasSpecial = true;
                password[i] = ch;
            }
            if(hasUpper && hasLower && hasDigit && hasSpecial) {
                return new String(password);
            }
        }
    }
    public static String genPassword(int x, int y, int z){
        boolean goodPass = false;
        String password = null;
        while (!goodPass) {
            Random rand = new Random();
            int numCount = 0;
            int alphCount = 0;


            password = (randString(rand).substring(0, x));

            for (int i = 0; i < x; i++) {

                Character c = password.charAt(i);
                if (Character.isDigit(c)) {
                    numCount++;
                }
                if (Character.isAlphabetic(c)) {
                    alphCount++;
                }
            }
            if (numCount == z && alphCount == x - y - z) {
                goodPass = true;
                //System.out.print(password);
            } else {
                goodPass = false;
            }

        }

        return  password;


    }



    @FXML protected void handleCreateButtonAction (ActionEvent actionEvent){

        String lengthStr = lengthField.getText();
        String specStr = specialCharField.getText();
        String numStr = numberField.getText();

        int length = Integer.parseInt(lengthStr);
        int specChar = Integer.parseInt(specStr);
        int nums = Integer.parseInt(numStr);





        resultPW.setText(genPassword(length,specChar,nums));
    }
}
