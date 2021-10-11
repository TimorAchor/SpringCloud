package com.timor.dao;

import com.timor.entity.Video;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * 视频(Video)表数据库访问层
 *
 * @author makejava
 * @since 2021-10-11 23:32:42
 */
public interface VideoDao {

    /**
     * 条件分页查询
     * @param offset 起始位置
     * @param limit 每页显示记录数
     * @param id  视频id
     * @param name 视频名称
     * @param categoryId 类别id
     * @param username   用户名
     */
    List<Video> findAllByKeywords(@Param("offset") int offset, @Param("limit") int limit, @Param("id") String id, @Param("title") String name, @Param("categoryId") String categoryId, @Param("username") String username);

    /**
     *
     * @param id  视频id
     * @param name 视频名称
     * @param categoryId 类别id
     * @param username   用户名
     * @return 条件符合条数
     */
    Long findTotalCountsByKeywords(@Param("id") String id, @Param("title") String name, @Param("categoryId") String categoryId, @Param("username") String username);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Video queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param video 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Video> queryAllByLimit(Video video, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param video 查询条件
     * @return 总行数
     */
    long count(Video video);

    /**
     * 新增数据
     *
     * @param video 实例对象
     * @return 影响行数
     */
    int insert(Video video);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Video> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Video> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Video> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Video> entities);

    /**
     * 修改数据
     *
     * @param video 实例对象
     * @return 影响行数
     */
    int update(Video video);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

