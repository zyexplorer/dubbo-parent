package com.iask.dubboapi.dao.mongo;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author ZY
 */
public interface BaseMongoDao<T> {

    T findById(String id);

    T findOne(String key, String value);

    T findOneByMap(Map<String, Object> map);

    List<T> findListByKeyValue(String key, Object value);

    List<T> findListByKeyValues(String key, Collection<Object> values);

    List<T> findListByKeyValue(String key, Object value, int limit);

    List<T> findListByKeyValues(String key, Collection<Object> values, int limit);

    List<T> findListByIds(Collection<String> ids);

    List<T> findAll();

    long getCount();

    long getCount(String key, String value);

    T save(T t);

    void save(Collection<T> collection);

    int updateByKeyValue(String key, String value, T t);

    int updateById(String id, Update update);

    int updateByIds(Collection<String> ids, Update update);

    int updateByMap(Query query, Map<String, Object> map);

    void remove(Query query);

    void remove(String id);

    void remove(T t);

    void remove(Collection<String> ids);

    <O> MapReduceResults<O> mapReduce(Query query, String inputCollectionName, String mapFunction,
                                             String reduceFunction, Class<O> clazz);

    String getCollectionName();

    String getCollectionName(Class<?> subClazz);

    T findAndRemove(Query query);

    void findAndUpdate(Query query, Update update);

    MongoTemplate getMongoTemplate();

    <O> O findSub(String id, Class<O> subClazz);

    <O> O findSub(String id, Class<O> subClazz, String subName);

    List<T> findList(Query query);

    List<T> findList(Query query, Sort... sort);

    List<T> findList(Query query, int limit);

    List<T> findListByMap(Map<String, Object> queryMap);

    List<T> findListByIds(Collection<String> ids, Sort... sort);

    T findOne(Query query);

    Long getCount(Query query);

    List<String> findField(Query query, String fieldName);

    List<String> findId(Query query);

    Long getCount(Map<String, Object> queryMap);

    T insert(T entity);

    /**
     * 这里要注意实体必须要无参的构造函数
     * @param map
     * @return
     */
    T insertByMap(Map<String,Object> map);

    int upsert(Query query, Update update);

    int update(Query query, Update update, boolean isFirst);

    /**
     * 实体必须有id字段，id不能为空，更新非空字段
     *
     * @param entity
     * @return
     */
    int updateById(T entity);

    int updateByMap(Map<String, Object> map, T entity);

    void update(T entity);

}
