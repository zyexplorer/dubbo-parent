package com.iask.dubboprovider.dao.mongo.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.iask.dubboapi.dao.mongo.BaseMongoDao;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.client.result.UpdateResult;

public abstract class BaseMongoDaoImpl<T> implements BaseMongoDao<T> {

    @Autowired
    protected MongoTemplate mongoTemplate;

    private Class<T> entityClass;

    public static final String ID_FIELD = "_id";

    public BaseMongoDaoImpl() {
        Class<?> c = getClass();
        Type type = c.getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            Type[] parameterizedType = ((ParameterizedType) type).getActualTypeArguments();
            this.entityClass = (Class<T>) parameterizedType[0];
        }
    }

    @Override
    public T findById(String id) {
        return mongoTemplate.findById(new ObjectId(id), entityClass);
    }

    @Override
    public T findOne(String key, String value) {
        Query query = new Query(Criteria.where(key).is(value));
        return mongoTemplate.findOne(query, entityClass);
    }

    @Override
    public T findOneByMap(Map<String, Object> map) {
        Query query = new Query();
        for (String key : map.keySet()) {
            query.addCriteria(Criteria.where(key).is(map.get(key)));
        }
        return mongoTemplate.findOne(query, entityClass);
    }

    @Override
    public List<T> findListByKeyValue(String key, Object value) {
        Query query = new Query(Criteria.where(key).is(value));
        return mongoTemplate.find(query, entityClass);
    }

    @Override
    public List<T> findListByKeyValues(String key, Collection<Object> values) {
        Query query = new Query(Criteria.where(key).in(values));
        return mongoTemplate.find(query, entityClass);
    }

    @Override
    public List<T> findListByKeyValue(String key, Object value, int limit) {
        Query query = new Query(Criteria.where(key).is(value));
        query.limit(limit);
        return mongoTemplate.find(query, entityClass);
    }

    @Override
    public List<T> findListByKeyValues(String key, Collection<Object> values, int limit) {
        Query query = new Query(Criteria.where(key).in(values));
        query.limit(limit);
        return mongoTemplate.find(query, entityClass);
    }

    @Override
    public List<T> findListByIds(Collection<String> ids) {
        Query query = new Query(Criteria.where(ID_FIELD).in(ids));
        return mongoTemplate.find(query, entityClass);
    }

    @Override
    public List<T> findAll() {
        return mongoTemplate.findAll(entityClass);
    }

    @Override
    public long getCount() {
        return mongoTemplate.count(null, entityClass);
    }

    @Override
    public long getCount(String key, String value) {
        Query query = new Query(Criteria.where(key).is(value));
        return mongoTemplate.count(query, entityClass);
    }

    @Override
    public T save(T t) {
        mongoTemplate.save(t);
        return t;
    }

    @Override
    public void save(Collection<T> collection) {
        mongoTemplate.insertAll(collection);
    }

    @Override
    public int updateByKeyValue(String key, String value, T t) {
        try {
            Query query = new Query(Criteria.where(key).is(value));
            Update update = new Update();
            Field[] fields = t.getClass().getDeclaredFields();
            for (Field f : fields) {
                f.setAccessible(true);
                if ("serialVersionUID".equals(f.getName())) {
                    continue;
                }
                if (f.get(t) != null) {
                    update.set(f.getName(), f.get(t));
                }
            }
            return (int) mongoTemplate.updateFirst(query, update, entityClass).getModifiedCount();
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int updateById(String id, Update update) {
        Query query = new Query(Criteria.where(ID_FIELD).is(id));
        return (int) mongoTemplate.updateFirst(query, update, entityClass).getModifiedCount();
    }

    @Override
    public int updateByIds(Collection<String> ids, Update update) {
        Query query = new Query(Criteria.where(ID_FIELD).in(ids));
        return (int) mongoTemplate.updateMulti(query, update, entityClass).getModifiedCount();
    }

    @Override
    public int updateByMap(Query query, Map<String, Object> map) {
        Update update = new Update();
        for (String key : map.keySet()) {
            if (map.get(key) != null) {
                update.set(key, map.get(key));
            }
        }
        return (int) mongoTemplate.updateMulti(query, update, entityClass).getModifiedCount();
    }

    @Override
    public void remove(Query query) {
        mongoTemplate.remove(query, entityClass);
    }

    @Override
    public void remove(String id) {
        mongoTemplate.remove(new Query(Criteria.where(ID_FIELD).is(id)), entityClass);
    }

    @Override
    public void remove(T t) {
        mongoTemplate.remove(t);
    }

    @Override
    public void remove(Collection<String> ids) {
        mongoTemplate.remove(new Query(Criteria.where(ID_FIELD).in(ids)), entityClass);
    }

    @Override
    public <O> MapReduceResults<O> mapReduce(Query query, String inputCollectionName, String mapFunction,
                                             String reduceFunction, Class<O> clazz) {
        return mongoTemplate.mapReduce(query, inputCollectionName, mapFunction, reduceFunction, clazz);
    }

    @Override
    public String getCollectionName() {
        return mongoTemplate.getCollectionName(entityClass);
    }

    @Override
    public String getCollectionName(Class<?> subClazz) {
        return mongoTemplate.getCollectionName(subClazz);
    }

    @Override
    public T findAndRemove(Query query) {
        return mongoTemplate.findAndRemove(query, entityClass);
    }

    @Override
    public void findAndUpdate(Query query, Update update) {
        mongoTemplate.findAndModify(query, update, entityClass);
    }

    @Override
    public MongoTemplate getMongoTemplate() {
        return this.mongoTemplate;
    }

    @Override
    public <O> O findSub(String id, Class<O> subClazz) {
        String subName = getCollectionName(subClazz);
        Query query = Query.query(Criteria.where(ID_FIELD).is(id));
        T entity = mongoTemplate.findOne(query, entityClass);
        JSONObject object = JSONArray.parseObject(JSON.toJSONString(entity));
        O subObj = (O) object.getObject(subName, subClazz.getClass());
        return subObj;
    }

    @Override
    public <O> O findSub(String id, Class<O> subClazz, String subName) {
        Query query = Query.query(Criteria.where(ID_FIELD).is(id));
        T entity = mongoTemplate.findOne(query, entityClass);
        JSONObject object = JSONArray.parseObject(JSON.toJSONString(entity));
        O subObj = (O) object.getObject(subName, subClazz.getClass());
        return subObj;
    }

    @Override
    public List<T> findList(Query query) {
        return mongoTemplate.find(query, entityClass);
    }

    @Override
    public List<T> findList(Query query, Sort... sort) {
        if (sort != null) {
            for (Sort s : sort) {
                if (s != null) {
                    query.with(s);
                }
            }
        }
        return mongoTemplate.find(query, entityClass);
    }

    @Override
    public List<T> findList(Query query, int limit) {
        query.limit(limit);
        return mongoTemplate.find(query, entityClass);
    }

    @Override
    public List<T> findListByMap(Map<String, Object> queryMap) {
        if (queryMap == null || queryMap.isEmpty()) {
            return null;
        }
        Query query = new Query();
        for (String key : queryMap.keySet()) {
            query.addCriteria(Criteria.where(key).is(queryMap.get(key)));
        }
        return mongoTemplate.find(query, entityClass);
    }

    @Override
    public List<T> findListByIds(Collection<String> ids, Sort... sort) {
        Query query = Query.query(Criteria.where(ID_FIELD).in(ids));
        if (sort != null) {
            for (Sort s : sort) {
                if (s != null) {
                    query.with(s);
                }
            }
        }
        return mongoTemplate.find(query, entityClass);
    }

    @Override
    public T findOne(Query query) {
        return mongoTemplate.findOne(query, entityClass);
    }

    @Override
    public Long getCount(Query query) {
        return mongoTemplate.count(query, entityClass);
    }

    @Override
    public List<String> findField(Query query, String fieldName) {
        List<T> list = mongoTemplate.find(query, entityClass);
        List<String> fieldList = new ArrayList<>();
        if (list.size() <= 0) {
            return fieldList;
        }
        JSONArray array = JSONArray.parseArray(JSON.toJSONString(list));
        for (int i = 0; i < array.size(); i++) {
            fieldList.add(array.getJSONObject(i).getString(fieldName));
        }
        return fieldList;
    }

    @Override
    public List<String> findId(Query query) {
        return findField(query, ID_FIELD);
    }

    @Override
    public Long getCount(Map<String, Object> queryMap) {
        if (queryMap == null || queryMap.isEmpty()) {
            return mongoTemplate.count(null, entityClass);
        }
        Query query = new Query();
        for (String key : queryMap.keySet()) {
            query.addCriteria(Criteria.where(key).is(queryMap.get(key)));
        }
        return mongoTemplate.count(query, entityClass);
    }

    @Override
    public T insert(T entity) {
        mongoTemplate.insert(entity);
        return entity;
    }

    /**
     * 这里要注意实体必须要无参的构造函数
     * @param map
     * @return
     */
    @Override
    public T insertByMap(Map<String,Object> map) {
        try {
            T t=entityClass.newInstance();
            List<Field> fieldList=new ArrayList<>();
            Field[] fieldArr = entityClass.getSuperclass().getDeclaredFields();//获取父类的字段
            Field[] ownrFieldArr = entityClass.getDeclaredFields();//获取父类的字段
            fieldList.addAll(Arrays.asList(fieldArr));
            fieldList.addAll(Arrays.asList(ownrFieldArr));
            for(Field field:fieldList) {
                if(map.containsKey(field.getName())) {
                    field.setAccessible(true);
                    field.set(t, map.get(field.getName()));
                }
            }
            mongoTemplate.insert(t);
            return t;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int upsert(Query query, Update update) {
        UpdateResult updateResult = mongoTemplate.upsert(query, update, entityClass);
        return (int) updateResult.getModifiedCount();
    }

    @Override
    public int update(Query query, Update update, boolean isFirst) {
        UpdateResult updateResult = null;
        if (isFirst) {
            updateResult = mongoTemplate.updateFirst(query, update, entityClass);
        } else {
            updateResult = mongoTemplate.updateMulti(query, update, entityClass);
        }
        return (int) updateResult.getModifiedCount();
    }

    /**
     * 实体必须有id字段，id不能为空，更新非空字段
     *
     * @param entity
     * @return
     */
    @Override
    public int updateById(T entity) {
        try {
            if (entity == null) {
                return 0;
            }
            Method mothod = entity.getClass().getMethod("getId");
            String id = (String) mothod.invoke(entity);
            if (id == null) {
                return 0;
            }
            Query query = new Query(Criteria.where(ID_FIELD).is(id));
            Update update = new Update();
            Field[] fields = entity.getClass().getDeclaredFields();
            for (Field f : fields) {
                f.setAccessible(true);
                if ("serialVersionUID".equals(f.getName())) {
                    continue;
                }
                if (f.get(entity) != null) {
                    update.set(f.getName(), f.get(entity));
                }
            }
            return (int) mongoTemplate.updateFirst(query, update, entityClass).getModifiedCount();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int updateByMap(Map<String, Object> map, T entity) {
        try {
            Query query = new Query();
            for (String key : map.keySet()) {
                query.addCriteria(Criteria.where(key).is(map.get(key)));
            }
            Update update = new Update();
            Field[] fields = entity.getClass().getDeclaredFields();
            for (Field f : fields) {
                f.setAccessible(true);
                if ("serialVersionUID".equals(f.getName())) {
                    continue;
                }
                if (f.get(entity) != null) {
                    update.set(f.getName(), f.get(entity));
                }
            }
            return (int) mongoTemplate.updateFirst(query, update, entityClass).getModifiedCount();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public void update(T entity) {
        Class<?> cls = entity.getClass();
        Field[] fields = cls.getDeclaredFields();
        String id = null;
        Update update = new Update();
        try {
            for (int i = 0; i < fields.length; i++) {
                Field f = fields[i];
                f.setAccessible(true);
                if ("id".equals(f.getName())) {
                    id =  f.get(entity).toString();
                }else if ("serialVersionUID".equals(f.getName())){
                    continue;
                }else if (f.get(entity) != null) {
                    update.set(f.getName(), f.get(entity));
                }
            }
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        if (id == null) {
            return;
        }
        System.out.println("id:"+id+" update:"+update.toString());
        updateById(id, update);
    }
}
