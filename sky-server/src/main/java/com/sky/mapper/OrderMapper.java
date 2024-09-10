package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.GoodsSalesDTO;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper {
    void insert(Orders order);
    /**
     * 根据订单号查询订单
     * @param orderNumber
     */
    @Select("select * from orders where number = #{orderNumber}")
    Orders getByNumber(String orderNumber);

    /**
     * 修改订单信息
     * @param orders
     */
    void update(Orders orders);

    Page<Orders> list(OrdersPageQueryDTO ordersPageQueryDTO);

    @Select("select * from orders where id =#{id}")
    Orders getById(Long id);

    @Select("select count(id) from orders where status=#{status}")
    Integer countStatus(Integer status);

    @Select("select * from orders where status=#{status} and order_time<#{now}")
    List<Orders> getBystatusAndOrderTime(Integer status, LocalDateTime now);

    @Select("select sum(amount) from orders where status=#{status} and DATE(order_time)=#{date} " +
            "group by DATE(order_time)")
    Double sumByMap(Map map);

    @Select("select count(id) from orders where DATE(order_time)=#{date} group by DATE(order_time)")
    Integer getorder(Map map);

    @Select("select count(id) from orders where DATE(order_time)=#{date} and status=#{status} group by DATE(order_time)")
    Integer getvalidorder(Map map);

    List<GoodsSalesDTO> getSalesTop10(LocalDateTime begin, LocalDateTime end);

    Integer countByMap(Map map);
}
