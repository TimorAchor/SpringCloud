package com.timor.service.impl;

import com.timor.dao.CategoryDao;
import com.timor.entity.Category;
import com.timor.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 分类(Category)表服务实现类
 *
 * @author hlh
 * @since 2021-09-17 17:24:20
 */
@Service("categoryService")
@Transactional
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryDao categoryDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Category queryById(Integer id) {
        return this.categoryDao.queryById(id);
    }



    /**
     * 新增数据
     *
     * @param category 实例对象
     * @return 实例对象
     */
    @Override
    public Category insert(Category category) {
        this.categoryDao.insert(category);
        return category;
    }

    /**
     * 修改数据
     *
     * @param category 实例对象
     * @return 实例对象
     */
    @Override
    public Category update(Category category) {
        this.categoryDao.update(category);
        return this.queryById(category.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.categoryDao.deleteById(id) > 0;
    }


    /**
     * 查询一级目录
     * @return
     */
    @Override
    public List<Category> queryByFirstLevel() {

        return categoryDao.queryByFirstLevel();
    }
}
