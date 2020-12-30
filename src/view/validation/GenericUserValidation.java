package view.validation;

public class GenericUserValidation {

    protected static boolean checkString(String email) {
        int count = 0;
        for(int i = 0; i < email.length(); i++) {
            if(email.charAt(i) == '@') {
                count++;
            }
        }
        return count > 1;
    }

    protected static boolean checkDots(String email) {
        int theAt = email.indexOf('@');
        int count = 0;
        for(int i = theAt; i < email.length(); i++) {
            if(email.charAt(i) == '.') {
                count++;
            }
        }
        return count > 1;
    }

    protected static boolean checkDigits(String text) {
        int count = 0;
        for(int i = 0; i < text.length(); i++) {
            if(!Character.isDigit(text.charAt(i))) {
                count++;
            }
        }
        return count != 0;
    }

    protected static boolean checkPassword(String password) {
        int countDigit = 0, countAlphabet = 0;
        for(int i = 0; i < password.length(); i++) {
            char pass = password.charAt(i);
            if(Character.isAlphabetic(pass)) {
                countAlphabet++;
            }
            if(Character.isDigit(pass)) {
                countDigit++;
            }
        }
        return countDigit == 0 || countAlphabet == 0;
    }

    protected static boolean validateEmail(String email) {
        if(email.isEmpty()) {
            return true;
        }

        if(!email.contains("@")) {
            return true;
        }

        if((email.startsWith("@") || email.endsWith("@")) ||
                (email.startsWith(".") || email.endsWith("."))) {
            return true;
        }

        if(email.charAt(email.indexOf("@") + 1) == '.' ||
                email.charAt(email.indexOf("@") - 1) == '.') {
            return true;
        }

        if(checkString(email)) {
            return true;
        }

        return checkDots(email);
    }
    
}
