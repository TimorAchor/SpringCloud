package com.timor.controller;

import com.timor.entity.Video;
import com.timor.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 视频(Video)表控制层
 *
 * @author makejava
 * @since 2021-10-11 23:32:42
 */
@Slf4j
@RestController
@RequestMapping("video")
public class VideoController {
    /**
     * 服务对象
     */
    @Resource
    private VideoService videoService;

    @GetMapping("/videos")
    public Map<String,Object> videos(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                     @RequestParam(value = "per_page",defaultValue = "5")Integer rows,
                                     String id,
                                     String name,
                                     @RequestParam(value = "category_id",required = false) String categoryId,
                                     @RequestParam(value = "uploader_name",required = false) String username){

        Map<String, Object> result = new HashMap<>();
        log.info("当前页为: {}", page);
        log.info("每页显示记录数: {}", rows);
        log.info("搜索条件id是否存在:{}, id为: {}", !ObjectUtils.isEmpty(id), id);
        log.info("搜索条件name是否存在:{}, name为: {}", !ObjectUtils.isEmpty(name), name);
        log.info("搜索条件category_id是否存在:{}, category_id为: {}", !ObjectUtils.isEmpty(categoryId), categoryId);
        log.info("搜索条件uploader_name是否存在:{}, uploader_name为: {}", !ObjectUtils.isEmpty(username), username);
        //根据条件搜索的服务条件的记录
        Long totalCounts = videoService.findTotalCountsByKeywords(id, name, categoryId, username);
        //根据条件搜索的结果集合
        List<Video> items = videoService.findAllByKeywords(page, rows, id, name, categoryId, username);
        log.info("符合条件的总数: {}", totalCounts);
        result.put("total_count", totalCounts);
        result.put("items", items);
        return result;
    }

    /**
     * 分页查询
     *
     * @param video 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<Video>> queryByPage(Video video, PageRequest pageRequest) {
        return ResponseEntity.ok(this.videoService.queryByPage(video, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Video> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.videoService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param video 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Video> add(Video video) {
        return ResponseEntity.ok(this.videoService.insert(video));
    }

    /**
     * 编辑数据
     *
     * @param video 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Video> edit(Video video) {
        return ResponseEntity.ok(this.videoService.update(video));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.videoService.deleteById(id));
    }

}

