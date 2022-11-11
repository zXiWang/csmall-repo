package cn.tedu.mall.seckill.mapper;

import cn.tedu.mall.pojo.seckill.model.SeckillSku;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeckillSkuMapper {

    // 根据spuId查询sku列表
    List<SeckillSku> findSeckillSkusBySpuId(Long spuId);

    // 根据skuId减少库存的方法
    int updateReduceStockBySkuId(@Param("skuId") Long  skuId,
                                 @Param("quantity") Integer quantity);


}
