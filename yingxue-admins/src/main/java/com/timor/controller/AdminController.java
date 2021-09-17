package com.timor.controller;

import com.timor.dto.AdminDTO;
import com.timor.entity.Admin;
import com.timor.service.AdminService;
import com.timor.utils.JSONUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * (Admin)表控制层
 *
 * @author makejava
 * @since 2021-09-17 10:52:22
 */
@Slf4j
@RestController
public class AdminController {

    private AdminService adminService;

    private RedisTemplate redisTemplate;

    @Autowired
    public AdminController(AdminService adminService, RedisTemplate redisTemplate) {
        this.adminService = adminService;
        this.redisTemplate = redisTemplate;
    }

    @PostMapping("/tokens")
    public Map<String,String> token(@RequestBody Admin admin, HttpSession session) {
        Map<String,String> result = new HashMap<>();
        log.info("接收到的Admin对象为:{}", JSONUtils.writeJSON(admin));
        //进行登录
        Admin adminDB  = adminService.login(admin);
        //登录成功
        String token = session.getId();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.opsForValue().set(token,adminDB,30, TimeUnit.MINUTES);
        result.put("token",token);
        return result;
    }

    @GetMapping("/admin-user")
    public AdminDTO getAdmin(String token){
        log.info("当前的token信息:{}"+token);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        Admin admin = (Admin) redisTemplate.opsForValue().get(token);
        AdminDTO adminDTO = new AdminDTO();
        //属性复制
        BeanUtils.copyProperties(admin,adminDTO);
        return adminDTO;
    }


    @DeleteMapping("/tokens/{token}")
    public void logout(@PathVariable("token") String token ){
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.delete(token);
    }















}

