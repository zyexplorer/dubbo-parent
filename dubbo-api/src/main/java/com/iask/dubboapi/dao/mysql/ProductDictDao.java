package com.iask.dubboapi.dao.mysql;

import com.iask.dubbocommon.entity.mysql.ProductDict;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * 产品清单表 产品清单表(ProductDict)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-18 23:31:59
 */
@Repository
public interface ProductDictDao extends Mapper<ProductDict> {

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<ProductDict> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 通过ID查询实体类
     * @param id
     * @return
     */
    ProductDict queryById(Integer id);
}