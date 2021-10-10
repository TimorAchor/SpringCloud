package com.timor.service.impl;

import com.timor.entity.User;
import com.timor.dao.UserDao;
import com.timor.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户(User)表服务实现类
 *
 * @author makejava
 * @since 2021-10-06 16:56:36
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS) //SUPPORTS 支持事务： 外层存在事务融入当前事务  外层不存在事务 不会开启新的事务
    public List<User> findAllByKeywords(int offset, int limit, String id, String name, String phone) {
        int start = (offset - 1) * limit;
        return userDao.findAllByKeywords(start, limit, id, name, phone);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Long findTotalCountsByKeywords(String id, String name, String phone) {
        return this.userDao.findTotalCountsByKeywords(id, name, phone);
    }


    /**
     * 查询所有
     *
     * @return 实例对象
     */
    @Override
    public User queryAll() {
        return this.userDao.queryAll();
    }


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public User queryById(Integer id) {
        return this.userDao.queryById(id);
    }



    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User insert(User user) {
        this.userDao.insert(user);
        return user;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User update(User user) {
        this.userDao.update(user);
        return this.queryById(user.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.userDao.deleteById(id) > 0;
    }



}
