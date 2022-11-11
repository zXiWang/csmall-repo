package cn.tedu.mall.search;

import cn.tedu.mall.pojo.search.entity.SpuForElastic;
import cn.tedu.mall.search.repository.SpuForElasticRepository;
import cn.tedu.mall.search.service.ISearchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SearchServiceImplTest {

    @Autowired
    private ISearchService searchService;

    @Test
    void loadSpuByPage() {
        searchService.loadSpuByPage();
        System.out.println("ok");
    }

    @Autowired
    private SpuForElasticRepository spuRepository;
    @Test
    void showData(){
        Iterable<SpuForElastic> spus=spuRepository.findAll();
        spus.forEach(spu -> System.out.println(spu));
    }

    @Test
    void getSpuByTitle(){
        // 根据title指定的分词查询数据
        Iterable<SpuForElastic> spus=
                spuRepository.querySpuForElasticsByTitleMatches("华为手机");
        spus.forEach(spu -> System.out.println(spu));

    }


//    @Test
//    void getSpuByQuery() {
//        // 调用查询四个字段包含指定关键字数据的方法
//        Iterable<SpuForElastic> spus=
//                spuRepository.querySearch("华为手机");
//        spus.forEach(spu -> System.out.println(spu));
//    }
}