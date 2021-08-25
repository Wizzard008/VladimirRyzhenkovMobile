package util;

import java.util.ResourceBundle;

public class TokenReader {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("token");

    public static String getSecretToken() {

        return resourceBundle.getString("token");
    }
}
