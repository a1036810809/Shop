package com.lzfmy.mapper;

import com.lzfmy.model.Product;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer pid);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer pid);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
    
    @Select("select * from product p where is_hot=1 order by pdate desc")
    @ResultMap("com.lzfmy.mapper.ProductMapper.BaseResultMap")
    List<Product> findAllHot();
    
    @Select("select * from product p order by pdate desc")
    @ResultMap("com.lzfmy.mapper.ProductMapper.BaseResultMap")
    List<Product> findAllNew();
    
    @Select("select count(*) from product p,categorysecond cs,category c where p.csid=cs.csid and cs.cid=c.cid and c.cid=#{cid}")
    int findCountCid(Integer cid);
    
    @Select("select count(*) from product p where p.csid = #{csid}")
    int findCountCsid(Integer csid);
    
    @Select("select * from product p,categorysecond cs,category c where p.csid=cs.csid and cs.cid=c.cid and c.cid = #{cid} limit #{begin},#{limit}")
    @ResultMap("com.lzfmy.mapper.ProductMapper.BaseResultMap")
    List<Product> findByPageCid(@Param("cid") Integer cid,@Param("begin") Integer begin,@Param("limit") Integer limit);
    
    @Select("select * from product p where p.csid = #{csid} limit #{begin},#{limit}")
    @ResultMap("com.lzfmy.mapper.ProductMapper.BaseResultMap")
    List<Product> findByPageCsid(@Param("csid") Integer csid,@Param("begin") Integer begin,@Param("limit") Integer limit);
    
    @Select("select count(*) from product")
    int findCount();
    
    @Select("select * from product order by pdate desc limit #{begin},#{limit}")
    @ResultMap("com.lzfmy.mapper.ProductMapper.BaseResultMap")
    List<Product> findByPage(@Param("begin") Integer begin,@Param("limit") Integer limit);
}