package com.timor.controller;

import com.timor.service.CategoryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 分类(Category)表控制层
 *
 * @author hlh
 * @since 2021-09-17 17:24:20
 */
@RestController
@RequestMapping("categories")
public class CategoryController {
    /**
     * 服务对象
     */
    @Resource
    private CategoryService categoryService;











}

