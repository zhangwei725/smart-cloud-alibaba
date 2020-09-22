package com.smart.auth.mapper;

import com.smart.auth.entity.Admin;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

@SpringBootTest
public class AdminMapperTest {
    @Resource
    AdminMapper adminMapper;
    @Resource
    BCryptPasswordEncoder encoder;

    @Test
    public void testInsert() {
        Admin admin = new Admin();
        admin.setUsername("admin");
        admin.setPassword(encoder.encode("123456"));
        admin.setEmail("123456@qq.com");
        adminMapper.insert(admin);
    }

}