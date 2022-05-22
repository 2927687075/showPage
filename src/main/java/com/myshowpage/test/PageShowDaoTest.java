package com.myshowpage.test;

import com.myshowpage.dao.ShowPageDao;
import com.myshowpage.pojo.ShowPage;
import com.myshowpage.service.ShowPageService;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.boot.test.context.SpringBootTest;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class PageShowDaoTest {

    @Resource
    private ShowPageDao showPageDao;
    @Resource
    private ShowPageService showPageService;

    @Test
    public void test01(){
        String name = "高";
        Example example = new Example(ShowPage.class);

        if(StringUtils.isNotBlank(name)){
            name = "%" + name + "%";
            example.createCriteria().andLike("name", name);
        }
        List<ShowPage> showPages = showPageDao.selectByExample(example);
        for (ShowPage showPage : showPages) {
            System.out.println("===="+showPage);
        }


    }

    @Test
    public void test02(){
        System.out.println(System.currentTimeMillis());
    }

    @Test
    public void test03(){
        List<ShowPage> list = showPageDao.findByPicName("高");
        for (ShowPage showPage : list) {
            System.out.println(showPage);
        }

    }

}
