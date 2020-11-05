package pl.coderion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;

@Service("userDetailsService")
public class CustomUserDetailsService extends JdbcDaoImpl {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UtilService utilService;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    @Override
    @Value("select login, password, pin, status from sys_user where login = ?")
    public void setUsersByUsernameQuery(String usersByUsernameQueryString) {
        super.setUsersByUsernameQuery(usersByUsernameQueryString);
    }

    @Override
    @Value("select su.login, ssr.role from sys_user su, sys_user_sys_security_role su_ssr, sys_security_role ssr" +
            " where su_ssr.sys_user_id = su.id and su_ssr.roles_id = ssr.id and su.login = ?")
    public void setAuthoritiesByUsernameQuery(String queryString) {
        super.setAuthoritiesByUsernameQuery(queryString);
    }

    // override to get accountNonLocked
    @Override
    public List<UserDetails> loadUsersByUsername(String username) {
        return getJdbcTemplate().query(super.getUsersByUsernameQuery(), new String[] { username },
                (rs, rowNum) -> {
                    String login = rs.getString("login");
                    String password = rs.getString("password");
                    String status = rs.getString("status");
                    String pin = rs.getString("pin");

                    return new User(login, utilService.pinRequest() ? pin : password, true, true, true,
                            "ACTIVE".equals(status), AuthorityUtils.NO_AUTHORITIES);
                });
    }

    // override to pass accountNonLocked
    @Override
    public UserDetails createUserDetails(String username, UserDetails userFromUserQuery,
                                         List<GrantedAuthority> combinedAuthorities) {
        String returnUsername = userFromUserQuery.getUsername();

        if (!super.isUsernameBasedPrimaryKey()) {
            returnUsername = username;
        }

        return new User(returnUsername, userFromUserQuery.getPassword(), userFromUserQuery.isEnabled(),
                userFromUserQuery.isAccountNonExpired(), userFromUserQuery.isCredentialsNonExpired(),
                userFromUserQuery.isAccountNonLocked(), combinedAuthorities);
    }
}