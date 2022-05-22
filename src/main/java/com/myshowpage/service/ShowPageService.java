package com.myshowpage.service;

import com.github.pagehelper.PageInfo;
import com.myshowpage.pojo.ShowPage;

import java.util.List;


public interface ShowPageService {
    /**
     * 查找所有图片
     * @return
     */
    List<ShowPage> selectAll();

    /**
     * 图片分页
     * @param pageNum   当前页码
     * @param pageSize  每页显示的个数
     * @return
     */
    PageInfo<ShowPage> splicePics(int pageNum,int pageSize);

    /**
     * 根据姓名进行模糊查询
     * @param name  姓名
     * @param pageNum   当前页码
     * @param pageSize  每页显示的个数
     * @return
     */
    PageInfo<ShowPage> selectByName(String name,int pageNum,int pageSize);

    /*用自己写的sql进行模糊查询*/
    PageInfo<ShowPage> selectByNamePic(String name,int pageNum,int pageSize);

    /**
     * 添加照片信息
     * @param showPage
     * @return
     */
    int addInfo(ShowPage showPage);

    /**
     * 根据id删除图片信息
     * @param id
     * @return
     */
    int deletePicById(Integer id);

    /**
     * 修改图片信息
     * @param showPage
     * @return
     */
    int updatePic(ShowPage showPage);
}
