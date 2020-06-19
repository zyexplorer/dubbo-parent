package com.iask.dubboprovider.service.mysql.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.iask.dubboapi.dao.mysql.ProductDictDao;
import com.iask.dubboapi.service.mysql.ProductDictService;
import com.iask.dubbocommon.entity.mysql.ProductDict;

import javax.annotation.Resource;
import java.util.List;

/**
 * 产品清单表 产品清单表(ProductDict)表服务实现类
 *
 * @author makejava
 * @since 2020-06-18 23:32:00
 */
@Service
public class ProductDictServiceImpl implements ProductDictService {
    
    @Resource
    private ProductDictDao productDictDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ProductDict queryById(Integer id) {
        return this.productDictDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<ProductDict> queryAllByLimit(int offset, int limit) {
        return this.productDictDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param productDict 实例对象
     * @return 实例对象
     */
//    @Override
//    public ProductDict insert(ProductDict productDict) {
//        this.productDictDao.insert(productDict);
//        return productDict;
//    }

//    /**
//     * 修改数据
//     *
//     * @param productDict 实例对象
//     * @return 实例对象
//     */
//    @Override
//    public ProductDict update(ProductDict productDict) {
//        this.productDictDao.updateByPrimaryKey(productDict);
//        return this.queryById(productDict.getId());
//    }
//
//    /**
//     * 通过主键删除数据
//     *
//     * @param id 主键
//     * @return 是否成功
//     */
//    @Override
//    public boolean deleteById(Integer id) {
//        return this.productDictDao.deleteByPrimaryKey(id) > 0;
//    }
}