package pl.coderion.util;

import com.google.common.hash.Hashing;
import org.apache.commons.lang3.RandomStringUtils;

import java.nio.charset.StandardCharsets;

public class StringUtil {

    /**
     * Hashes string
     * @param s
     * @return
     */
    public static String hashSha512(String s) {
        return Hashing.sha512().hashString(s, StandardCharsets.UTF_8).toString();
    }

    public static String generateNewPassword() {
        return RandomStringUtils.random(10, true, true);
    }
}