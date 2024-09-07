package com.sky.controller.user;

import com.sky.context.BaseContext;
import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;
import com.sky.result.Result;
import com.sky.service.ShoppingCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/user/shoppingCart")
@Api("C端-购物车接口")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;
    @PostMapping("/add")
    @ApiOperation("添加购物车")
    public Result addshopping(@RequestBody ShoppingCartDTO shoppingCartDTO){
        log.info("添加购物车：{}", shoppingCartDTO);
        shoppingCartService.addshopping(shoppingCartDTO);
        return Result.success();
    }

    @GetMapping("list")
    @ApiOperation("查看购物车")
    public Result <List<ShoppingCart>> list(){
        List<ShoppingCart> shoppingCartList = shoppingCartService.showshoppingCart();
        return Result.success(shoppingCartList);
    }
    @DeleteMapping("/clean")
    @ApiOperation("清空购物车")
    public Result shoppingclean(){
        shoppingCartService.delete();
        return Result.success();
    }

    @PostMapping("/sub")
    @ApiOperation("删除购物车中一个商品")
    public Result shoppingdelete(@RequestBody ShoppingCartDTO shoppingCartDTO){
        shoppingCartService.subshoppingclean(shoppingCartDTO);
        return Result.success();
    }
}
