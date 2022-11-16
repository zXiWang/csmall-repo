package cn.tedu.mall.search.controller;

import cn.tedu.mall.common.restful.JsonPage;
import cn.tedu.mall.common.restful.JsonResult;
import cn.tedu.mall.pojo.search.entity.SpuEntity;
import cn.tedu.mall.search.service.ISearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
@Api(tags = "搜索模块")
public class SearchController {
    @Autowired
    private ISearchService searchService;

    // 搜索功能设计的路径为: localhost:10008/search
    // 因为搜索模块功能少,路径可以尽量简练
    // @GetMapping后面什么都不写,就表示采用类上声明的/search路径即可
    @GetMapping
    @ApiOperation("根据用户输入的关键字分页查询商品信息")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "搜索关键字", name = "keyword", example = "手机"),
            @ApiImplicitParam(value = "页码", name = "page", example = "1"),
            @ApiImplicitParam(value = "每页条数", name = "pageSize", example = "2"),
    })
    //                         ↓↓↓↓↓↓↓↓↓
    public JsonResult<JsonPage<SpuEntity>> searchByKeyword(
            String keyword, Integer page, Integer pageSize) {
//              ↓↓↓↓↓↓↓↓↓↓↓
        JsonPage<SpuEntity> jsonPage =
                searchService.search(keyword, page, pageSize);
        return JsonResult.ok(jsonPage);
    }

}
