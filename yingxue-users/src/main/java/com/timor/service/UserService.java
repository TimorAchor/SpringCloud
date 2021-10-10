package com.timor.service;

import com.timor.entity.User;

import java.util.List;

/**
 * 用户(User)表服务接口
 *
 * @author makejava
 * @since 2021-10-06 16:56:36
 */
public interface UserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    User queryById(Integer id);


    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User insert(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User update(User user);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 查询所有
     * @return
     */
    User queryAll();

    List<User> findAllByKeywords(int offset, int limit, String id, String name, String phone);

    Long findTotalCountsByKeywords(String id, String name, String phone);
}
