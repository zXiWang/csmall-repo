package cn.tedu.mall.search.repository;

import cn.tedu.mall.pojo.search.entity.SpuForElastic;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * SpuForElastic实体类操作
 */
@Repository
public interface SpuForElasticRepository extends ElasticsearchRepository<SpuForElastic, Long> {

    // 查询title字段包含指定关键字(分词)的spu数据
    Iterable<SpuForElastic> querySpuForElasticsByTitleMatches(String title);

    @Query("{\n" +
            "    \"bool\": {\n" +
            "      \"should\": [\n" +
            "        { \"match\": { \"name\": \"?0\"}},\n" +
            "        { \"match\": { \"title\": \"?0\"}},\n" +
            "        { \"match\": { \"description\": \"?0\"}},\n" +
            "        { \"match\": { \"category_name\": \"?0\"}}\n" +
            "        ]\n" +
            "     }\n" +
            "}")
        // 上面指定查询语句的情况下,方法的方法名就可以随意起名了,参数对应查询语句中的"?0"
    Page<SpuForElastic> querySearch(String keyword, Pageable pageable);
}
