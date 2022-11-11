package cn.tedu.mall.search.service;

import cn.tedu.mall.common.restful.JsonPage;
import cn.tedu.mall.pojo.search.entity.SpuEntity;
import cn.tedu.mall.pojo.search.entity.SpuForElastic;


public interface ISearchService {

    // ES分页查询spu的方法
    JsonPage<SpuForElastic> search(String keyword, Integer page, Integer pageSize);

    // 向ES中加载数据的方法
    void loadSpuByPage();
}








