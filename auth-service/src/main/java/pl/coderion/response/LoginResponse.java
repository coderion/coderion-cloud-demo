package pl.coderion.response;

import lombok.Builder;
import lombok.Data;
import pl.coderion.user.RoleEnum;

import java.util.Set;

@Data
@Builder
public class LoginResponse {

    private String token;

    private Set<RoleEnum> roles;
}
