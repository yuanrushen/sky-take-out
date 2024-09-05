package com.sky.controller.admin;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController("adminShopController")
@RequestMapping("/admin/shop")
@Slf4j
@Api(tags = "店铺管理")
public class ShopController {
    public static final String SHOP_STATUS = "SHOP_STATUS";

    @Autowired
    private RedisTemplate redisTemplate;

    @PutMapping("/{status}")
    @ApiOperation(value = "修改店铺状态")
    public Result setstatus(@PathVariable Integer status){
        log.info("修改店铺状态：{}",status ==1 ? "营业" : "停业");
        redisTemplate.opsForValue().set(SHOP_STATUS,status);
        return Result.success();
    }

    @GetMapping("/status")
    @ApiOperation(value = "admin获取店铺状态")
    public Result<Integer> getstatus(){
        Integer shopStatus = (Integer) redisTemplate.opsForValue().get(SHOP_STATUS);
        log.info("获取店铺状态：{}",shopStatus ==1 ? "营业" : "停业");
        return Result.success(shopStatus);
    }
}
