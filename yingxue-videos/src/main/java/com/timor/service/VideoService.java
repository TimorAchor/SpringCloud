package com.timor.service;

import com.timor.entity.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 视频(Video)表服务接口
 *
 * @author makejava
 * @since 2021-10-11 23:32:42
 */
public interface VideoService {

    /**
     * 查询分页
     * @param offset
     * @param limit
     * @param id
     * @param name
     * @param categoryId
     * @param username
     * @return
     */
    List<Video> findAllByKeywords(int offset, int limit, String id, String name, String categoryId, String username);

    /**
     * 查询总数
     * @param id
     * @param name
     * @param categoryId
     * @param username
     * @return
     */
    Long findTotalCountsByKeywords(String id, String name, String categoryId, String username);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Video queryById(Integer id);

    /**
     * 分页查询
     *
     * @param video 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Video> queryByPage(Video video, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param video 实例对象
     * @return 实例对象
     */
    Video insert(Video video);

    /**
     * 修改数据
     *
     * @param video 实例对象
     * @return 实例对象
     */
    Video update(Video video);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
