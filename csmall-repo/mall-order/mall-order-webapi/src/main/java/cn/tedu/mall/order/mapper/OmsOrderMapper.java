package cn.tedu.mall.order.mapper;


import cn.tedu.mall.pojo.order.dto.OrderListTimeDTO;
import cn.tedu.mall.pojo.order.model.OmsOrder;
import cn.tedu.mall.pojo.order.vo.OrderListVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OmsOrderMapper {

    // 新增订单的方法
    int insertOrder(OmsOrder omsOrder);

    // 查询当前用户指定时间范围内的所有订单信息
    List<OrderListVO> selectOrdersBetweenTimes(OrderListTimeDTO orderListTimeDTO);

    // 利用动态sql,实现对订单内容的修改
    // 包含id值,且不可修改,其他属性不为空就修改为当前值
    int updateOrderById(OmsOrder omsOrder);
}
