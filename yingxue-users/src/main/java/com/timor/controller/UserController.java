package com.timor.controller;

import com.timor.entity.User;
import com.timor.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户(User)表控制层
 *
 * @author makejava
 * @since 2021-10-06 16:56:35
 */
@Slf4j
@RestController
@RequestMapping("user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;


    //用户列表
    @GetMapping
    public Map<String, Object> users(@RequestParam(value = "page", defaultValue = "1") Integer pageNow,
                                     @RequestParam(value = "per_page", defaultValue = "5") Integer rows,
                                     @RequestParam(required = false) String id,
                                     String name,
                                     String phone) {
        Map<String, Object> result = new HashMap<>();
        log.info("分页信息 当前页:{} ,每页展示记录数: {}", pageNow, rows);
        log.info("搜索的值 id:{}, name:{}, phone:{}", id, name, phone);
        //查询用户 分页查询用户信息  指定条件分页查询用户信息
        List<User> items = userService.findAllByKeywords(pageNow, rows, id, name, phone);
        //查询总条数
        Long totalCounts = userService.findTotalCountsByKeywords(id, name, phone);
        log.info("当前list中的总数: {}", items.size());
        log.info("当前符合条件的总数: {}", totalCounts);
        result.put("total_count", totalCounts);
        result.put("items", items);
        return result;
    }



//    /**
//     * 查询所有
//     * @return
//     */
//    @GetMapping
//    public ResponseEntity<User> queryAll() {
//        return ResponseEntity.ok(this.userService.queryAll());
//    }


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<User> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.userService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param user 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<User> add(User user) {
        return ResponseEntity.ok(this.userService.insert(user));
    }

    /**
     * 编辑数据
     *
     * @param user 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<User> edit(User user) {
        return ResponseEntity.ok(this.userService.update(user));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.userService.deleteById(id));
    }

}

