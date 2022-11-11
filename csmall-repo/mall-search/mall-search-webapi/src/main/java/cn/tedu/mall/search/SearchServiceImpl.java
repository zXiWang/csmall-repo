package cn.tedu.mall.search;

import cn.tedu.mall.common.restful.JsonPage;
import cn.tedu.mall.pojo.product.model.Spu;
import cn.tedu.mall.pojo.search.entity.SpuForElastic;
import cn.tedu.mall.product.service.front.IForFrontSpuService;
import cn.tedu.mall.search.repository.SpuForElasticRepository;
import cn.tedu.mall.search.service.ISearchService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import springfox.documentation.spring.web.json.Json;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SearchServiceImpl implements ISearchService {
    @DubboReference
    private IForFrontSpuService dubboSpuService;

    @Autowired
    private SpuForElasticRepository spuRepository;


    @Override
    public JsonPage<SpuForElastic> search(String keyword, Integer page, Integer pageSize) {

        Page<SpuForElastic> spus = spuRepository.querySearch(keyword, PageRequest.of(page - 1, pageSize));
        JsonPage<SpuForElastic> jsonPage = new JsonPage<SpuForElastic>();
        jsonPage.setPage(page);
        jsonPage.setPageSize(pageSize);
        jsonPage.setTotal(spus.getTotalElements());
        jsonPage.setTotalPage(spus.getTotalPages());
        jsonPage.setList(spus.getContent());
        return jsonPage;
    }

    @Override
    public void loadSpuByPage() {
        // 循环完成分页查询所有数据,
        // 每循环一次,将查询到的当页数据新增到ES,直到最后一页
        // 因为是需要运行一次之后,才知道总页数,所以这里采用do-while循环结构
        int i = 1;     // 循环变量i,从1开始,因为可以同时用作页码值
        int pages;   // 总页数,在循环进行一次之后,才能被赋值,这里可以只声明或赋默认值

        do {
            // dubbo调用查询当前也的spu数据
            JsonPage<Spu> spus = dubboSpuService.getSpuByPage(i, 2);
            // 我们从数据查询出来的类型Spu不能直接向ES中执行新增
            // 需要转换为SpuForElastic类型,所以我们先声明这样类型的集合
            List<SpuForElastic> esSpus = new ArrayList<>();
            // 遍历数据库中查询出的当页数据
            for (Spu spu : spus.getList()) {
                // 下面开始转换,实例化新实体类,并将同名属性赋值给它
                SpuForElastic esSpu = new SpuForElastic();
                BeanUtils.copyProperties(spu, esSpu);
                // 赋值完成后,添加到上面的集合中!
                esSpus.add(esSpu);
            }
            // esSpus集合中已经包含了本次查询的所有数据,下面执行批量新增到ES的操作
            spuRepository.saveAll(esSpus);
            log.info("成功加载了第{}页数据", i);
            // 下次循环i值自增
            i++;
            // 给pages赋值总页数
            pages = spus.getTotalPage();
        } while (i <= pages);
    }
}
