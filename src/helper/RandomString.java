package helper;

import java.util.Random;

public class RandomString {

    public static String generateAlphanum() {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int length = 6;

        for(int i = 0; i < length; i++) {
            int index = random.nextInt(alphabet.length());
            char randChar = alphabet.charAt(index);
            sb.append(randChar);
        }
        return sb.toString();
    }

}
