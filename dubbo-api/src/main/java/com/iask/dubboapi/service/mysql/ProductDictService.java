package com.iask.dubboapi.service.mysql;

import com.iask.dubbocommon.entity.mysql.ProductDict;
import java.util.List;

/**
 * 产品清单表 产品清单表(ProductDict)表服务接口
 *
 * @author makejava
 * @since 2020-06-18 23:32:00
 */
public interface ProductDictService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ProductDict queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<ProductDict> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param productDict 实例对象
     * @return 实例对象
     */
    //ProductDict insert(ProductDict productDict);

    /**
     * 修改数据
     *
     * @param productDict 实例对象
     * @return 实例对象
     */
    //ProductDict update(ProductDict productDict);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    //boolean deleteById(Integer id);

}