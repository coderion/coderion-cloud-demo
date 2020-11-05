package pl.coderion.config;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Sha256HexPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        return DigestUtils.sha256Hex(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        String digest = DigestUtils.sha256Hex(rawPassword.toString());

        if (digest.equals(encodedPassword)) {
            return true;
        }

        return false;
    }
}