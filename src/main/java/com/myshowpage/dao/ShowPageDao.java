package com.myshowpage.dao;

import com.myshowpage.pojo.ShowPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ShowPageDao extends tk.mybatis.mapper.common.Mapper<ShowPage> {

    @Select("select * from showpage where `name` like concat('%', #{name}, '%')")
    List<ShowPage> findByPicName(@Param("name") String name);

}
