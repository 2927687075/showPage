package com.myshowpage.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myshowpage.dao.ShowPageDao;
import com.myshowpage.pojo.ShowPage;
import com.myshowpage.service.ShowPageService;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ShowPageServiceImpl implements ShowPageService {
    @Resource
    private JedisPool jedisPool;

    @Resource
    private ShowPageDao showPageDao;

    @Override
    public List<ShowPage> selectAll() {
        List<ShowPage> pageShows = showPageDao.selectAll();

        return pageShows;
    }

    @Override
    public PageInfo<ShowPage> splicePics(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ShowPage> showPages = showPageDao.selectAll();
        return PageInfo.of(showPages);
    }

    @Override
    public PageInfo<ShowPage> selectByName(String name, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Example example = new Example(ShowPage.class);
        if(StringUtils.isNotBlank(name)){
            name = "%" + name + "%";
            example.createCriteria().andLike("name", name);
        }

        List<ShowPage> showPages = showPageDao.selectByExample(example);
        return PageInfo.of(showPages);
    }


    @Override
    public PageInfo<ShowPage> selectByNamePic(String name, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        List<ShowPage> list = showPageDao.findByPicName(name);
        return PageInfo.of(list);
    }

    @Override
    public int addInfo(ShowPage showPage) {
        int i = showPageDao.insertSelective(showPage);
        Jedis jedis=jedisPool.getResource();
        if (i > 0 ){
            jedis.set(showPage.getId()+"", JSON.toJSONString(showPage));
        }
        jedis.close();
        return i;
    }

    @Override
    public int deletePicById(Integer id) {

        int i = showPageDao.deleteByPrimaryKey(id);
        Jedis jedis=jedisPool.getResource();
        if (i > 0 ){
            jedis.del(id+"");
        }
        jedis.close();
        return i;
    }

    @Override
    public int updatePic(ShowPage showPage) {
        int i = showPageDao.updateByPrimaryKey(showPage);
        Jedis jedis=jedisPool.getResource();
        if (i > 0 ){
            jedis.set(showPage.getId()+"", JSON.toJSONString(showPage));
        }
        jedis.close();
        return i;
    }
}
