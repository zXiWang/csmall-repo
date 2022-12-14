package cn.tedu.mall.ams.jwt;


import cn.tedu.mall.common.utils.JwtTokenUtils;
import cn.tedu.mall.sso.security.service.admin.AdminSSOUserDetailsService;
import cn.tedu.mall.sso.security.service.user.UserSSOUserDetailsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JWTTest {

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @Autowired
    private AdminSSOUserDetailsService adminSSOService;
    @Autowired
    private UserSSOUserDetailsService userSSOservice;

    @Test
    public void test01() {

    }
}
