package com.sunyu.redis.dao;

import com.github.pagehelper.PageHelper;
import com.sunyu.redis.model.Articles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
            },
            evict= { @CacheEvict(value= "allArticlesCache", allEntries= true) }
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
    @Caching(
            put= { @CachePut(value= "articleCache", key= "#article.articleId") },
            evict= { @CacheEvict(value= "allArticlesCache", allEntries= true) }
    )
    public int update(Articles article) {
        return articlesDao.update(article);
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    @Caching(
            evict= {
                    @CacheEvict(value= "articleCache", key= "#articleId"),
                    @CacheEvict(value= "allArticlesCache", allEntries= true)
            }
    )
    public int delete(int articleId) {
        return articlesDao.delete(articleId);
    }

    /**
     * 根据id查询数据
     *
     * @param id
     * @return
     */
    @Cacheable(value= "articleCache", key= "#articleId")
    public Articles queryById(int articleId) {
        return articlesDao.queryById(articleId);
    }

    /**
     * 分页查询
     * @param offset
     * @param limit
     * @return
     */
    @Cacheable(value= "allArticlesCache", unless= "#result.size() == 0")
    public List<Articles> queryPage(int offset, int limit){
        PageHelper.offsetPage(offset,limit);
        List<Articles> list = articlesDao.queryPage();
        return list;
    }
}
