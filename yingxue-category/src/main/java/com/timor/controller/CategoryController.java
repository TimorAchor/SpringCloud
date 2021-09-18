package com.timor.controller;

import com.timor.entity.Category;
import com.timor.service.CategoryService;
import com.timor.utils.JSONUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 分类(Category)表控制层
 *
 * @author hlh
 * @since 2021-09-17 17:24:20
 */
@Slf4j
@RestController
@RequestMapping("categories")
public class CategoryController {
    /**
     * 服务对象
     */
    @Resource
    private CategoryService categoryService;

    /**
     * 修改类别列表
     * @return
     */
    @PatchMapping("/{id}")
    public Category update(@PathVariable("id")Integer id,@RequestBody Category category){
         log.info("更新的类别id:{}",id);
         log.info("更新的类别信息:{}", JSONUtils.writeJSON(category));
         category.setId(id);
        return categoryService.update(category);
    }



    /**
     * 类别列表
     * @return
     */
    @GetMapping
    public List<Category> categories(){
        return categoryService.queryByFirstLevel();
    }









}
