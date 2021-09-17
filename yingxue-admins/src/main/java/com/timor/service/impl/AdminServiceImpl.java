package com.timor.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.timor.dao.AdminDao;
import com.timor.entity.Admin;
import com.timor.service.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

/**
 * (Admin)表服务实现类
 *
 * @author makejava
 * @since 2021-09-17 10:52:23
 */
@Service("adminService")
@Transactional
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminDao adminDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Admin queryById(Integer id) {
        return this.adminDao.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param admin 实例对象
     * @return 实例对象
     */
    @Override
    public Admin insert(Admin admin) {
        this.adminDao.insert(admin);
        return admin;
    }

    /**
     * 修改数据
     *
     * @param admin 实例对象
     * @return 实例对象
     */
    @Override
    public Admin update(Admin admin) {
        this.adminDao.update(admin);
        return this.queryById(admin.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.adminDao.deleteById(id) > 0;
    }

    /**
     * 登录
     * @param admin
     * @return
     */
    @Override
    public Admin login(Admin admin) {

        Admin adminDB = adminDao.findByUserName(admin.getUsername());
        if(ObjectUtils.isEmpty(adminDB)){
            throw new RuntimeException("用户名错误!");
        }
        String password = DigestUtils.md5DigestAsHex(admin.getPassword().getBytes(StandardCharsets.UTF_8));
        if(!StringUtils.equals(password,adminDB.getPassword())){
            throw new RuntimeException("密码输入错误!");
        }
        return adminDB;
    }
}
