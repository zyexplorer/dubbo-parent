package com.iask.dubbocommon.entity.mysql;

import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.Builder;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 产品清单表 产品清单表(ProductDict)实体类
 *
 * @author makejava
 * @since 2020-06-18 23:31:58
 */
@Data
@Builder
@Table(name = "t_product_dict")
public class ProductDict implements Serializable {

    private static final long serialVersionUID = 939913883508835609L;

    /**
    * 表主键ID 产品ID
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    /**
    * 应用名称
    */
    private String productName;

    /**
    * 应用编码
    */
    private String productCode;

    /**
    * 归属端 1.企业端  2.医生端 3.用户端 4.商业端
    */
    private Integer belongEnd;

    /**
    * 平台 1.Web  2.App  3.小程序
    */
    private Integer platform;

    /**
    * 系统 1.IOS  2.Android
    */
    private Integer operateSystem;

    /**
    * 域名(多个域名以,分割)
    */
    private String domains;

    /**
    * 页面类型 1.PC  2.H5
    */
    private Integer pageType;

    /**
    * h5类型： 1.H5网站  2.H5单社区
    */
    private Integer h5Type;

    /**
    * 是否删除 0.否 1.是
    */
    private Integer delFlag;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
    * 更新时间
    */
    private Date updateTime;

    /**
    * 备注
    */
    private String remark;

}