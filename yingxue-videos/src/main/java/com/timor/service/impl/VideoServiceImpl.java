package com.timor.service.impl;

import com.timor.entity.Video;
import com.timor.dao.VideoDao;
import com.timor.service.VideoService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 视频(Video)表服务实现类
 *
 * @author makejava
 * @since 2021-10-11 23:32:42
 */
@Service("videoService")
@Transactional
public class VideoServiceImpl implements VideoService {
    @Resource
    private VideoDao videoDao;


    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Video> findAllByKeywords(int offset, int limit, String id, String name, String categoryId, String username) {
        int start = (offset - 1) * limit;
        return videoDao.findAllByKeywords(start, limit, id, name, categoryId, username);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Long findTotalCountsByKeywords(String id, String name, String categoryId, String username) {
        return videoDao.findTotalCountsByKeywords(id, name, categoryId, username);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Video queryById(Integer id) {
        return this.videoDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param video 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Video> queryByPage(Video video, PageRequest pageRequest) {
        long total = this.videoDao.count(video);
        return new PageImpl<>(this.videoDao.queryAllByLimit(video, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param video 实例对象
     * @return 实例对象
     */
    @Override
    public Video insert(Video video) {
        this.videoDao.insert(video);
        return video;
    }

    /**
     * 修改数据
     *
     * @param video 实例对象
     * @return 实例对象
     */
    @Override
    public Video update(Video video) {
        this.videoDao.update(video);
        return this.queryById(video.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.videoDao.deleteById(id) > 0;
    }
}
