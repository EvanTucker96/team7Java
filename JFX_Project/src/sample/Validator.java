package sample;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.regex.Pattern;
/*
VALIDATOR MADE AND DONE BY BRANDON

 */


public class Validator {
    public static boolean nullTextField(TextField tb) //makes sure the data is not empty
    {
        boolean isNull = false;


        if (tb.getText().isEmpty()){
            isNull = true;

        }

        return isNull;
    }
  public static boolean dataLength(TextField tb, String length)
    {
        boolean isDataLength = true;

        if (!tb.getText().matches("\\b\\w" + "{" + length + "}")){
            isDataLength = false;
        }
        return isDataLength;
    }
    public static boolean alphabetOnly(TextField tb)
    {   boolean isAlphabet = true;
            if (!tb.getText().matches("[a-z A-Z]+"))
            {
                isAlphabet = false;
            }
            return isAlphabet;

    }
    public static boolean numbersOnly(TextField tb)
    {   //canadian postal codes only
        boolean isNumber = true;
        if (!tb.getText().matches("[0-9]*"))
        {
            isNumber = false;
        }
        return isNumber;
    }
    public static boolean alphaNumeric(TextField tb) // also symbols
    {   //canadian postal codes only
        boolean isAlphaNumeric = true;
        //
        if (!tb.getText().matches("[a-zA-Z0-9 \\s!@#$&()`.+,/\"-]*"))
        {
            isAlphaNumeric = false;
        }
        return isAlphaNumeric;
    }
    public static boolean isPostal(TextField tb)//couldnt get postal to work
    {   //canadian postal codes only
        boolean isPostal = true;
        if (!tb.getText().matches("[A-Z][0-9][0-9A-Z]?\\s?[0-9][A-Z][A-Z]"))
        {
            isPostal = false;
        }
        return isPostal;
    }
    public static boolean isEmail(TextField tb)
    {   //canadian postal codes only
        boolean isEmail = true;
        if (!tb.getText().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.com"))
        {
            isEmail = false;
        }
        return isEmail;
    }
    public static boolean isPhone(TextField tb)
    {   //canadian postal codes only
        boolean isPhone = true;
        if (!tb.getText().matches("[0-9()-]+"))
        {
            isPhone = false;
        }
        return isPhone;
    }


}
/*
WHY ARE YOU DOWN HERE AND WHY AM I YELLING
 */