package com.sunyu.redis.dao;

import com.sunyu.redis.model.Articles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yu 2018/11/9.
 */
@Repository
public class ArticleDaoCache {

    @Autowired
    private ArticlesDao articlesDao;

    /**
     * 保存数据
     *
     * @param article
     * @return
     */

    @Caching(
            put = {
                    @CachePut(value = "articleCache", key = "#article.articleId"),
                    @CachePut(value = "articleCache", key = "#article.category+'_'+#article.articleId")
            }
    )
    public int save(Articles article) {
        return articlesDao.save(article);
    }

    /**
     * 批量添加数据
     *
     * @param entityList
     * @return
     */
    public int batchSave(List<Articles> entityList) {
        return articlesDao.batchSave(entityList);
    }

    /**
     * 更新数据
     *
     * @param entity
     * @return
     */
    public int update(Articles entity) {
        return articlesDao.update(entity);
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    public int delete(int id) {
        return articlesDao.delete(id);
    }

    /**
     * 根据id查询数据
     *
     * @param id
     * @return
     */
    public Articles queryById(int id) {
        return articlesDao.queryById(id);
    }
}
