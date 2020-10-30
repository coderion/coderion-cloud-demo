package pl.coderion.auth;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderion.exception.AuthenticationException;
import pl.coderion.exception.BaseException;
import pl.coderion.exception.ErrorCodeEnum;
import pl.coderion.response.LoginResponse;
import pl.coderion.user.User;
import pl.coderion.user.UserRepository;
import pl.coderion.util.StringUtil;

import java.util.Optional;

@Service
public class TokenAuthenticationService implements UserAuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @Override
    public Optional<LoginResponse> login(String email, String password) throws BaseException {
        Optional<User> user = userRepository.findByEmailAndPassword(email, StringUtil.hashSha512(password));
        Optional<User> activeUser = user.filter(u -> BooleanUtils.isTrue(u.getActive()));

        if (user.isPresent() && !activeUser.isPresent()) {
            throw new AuthenticationException(ErrorCodeEnum.USER_INACTIVE_ACCOUNT, email);
        }

        Optional<LoginResponse> loginResponse = activeUser
                .map(u -> LoginResponse.builder()
                        .token(tokenService.expiring(ImmutableMap.of("email", email)))
                        .roles(u.getRoles())
                        .build());

        return loginResponse;
    }

    @Override
    public Optional<User> findByToken(String token) {
        return Optional
                .of(tokenService.verify(token))
                .map(map -> map.get("email"))
                .flatMap(userRepository::findByEmail);
    }
}