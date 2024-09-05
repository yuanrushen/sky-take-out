package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/dish")
@Api(tags = "菜品管理")
@Slf4j
public class DishController {
    @Autowired
    private DishService dishService;
    @PostMapping
    @ApiOperation(value = "新增菜品")
    public Result save(@RequestBody DishDTO dishDTO){
        log.info("新增菜品:{}",dishDTO);
        dishService.saveWithFlavor(dishDTO);
        return Result.success();
    }

    /*
    分页查询菜品
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询菜品")
    public Result<PageResult> page(DishPageQueryDTO dishPageQueryDTO){
        log.info("分页查询菜品:{}",dishPageQueryDTO);
        PageResult pageResult = dishService.pageQuery(dishPageQueryDTO);
        return Result.success(pageResult);
    }

    /*
    删除菜品
     */
    @DeleteMapping
    @ApiOperation(value = "删除菜品")
    public Result delete(@RequestParam List<Long> ids){
        log.info("菜品批量删除：{}", ids);
        dishService.deleteBatch(ids);
        return Result.success();
    }

    /*
    修改菜品
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询菜品")
    public Result<DishVO> getById(@PathVariable Long id){
        log.info("根据id查询菜品：{}", id);
        DishVO  dishVO = dishService.getByIdWithFlavor(id);
        return Result.success(dishVO);
    }

    /*
    修改菜品
     */
    @PutMapping
    @ApiOperation(value = "修改菜品")
    public Result update(@RequestBody DishDTO dishDTO){
        log.info("修改菜品:{}",dishDTO);
        dishService.updateWithFlavor(dishDTO);
        return Result.success();
    }

    /*
    修改菜品状态
     */
    @PostMapping("/status/{status}")
    @ApiOperation(value = "修改菜品状态")
    public Result updateStatus(@PathVariable Integer status,Long id){
        log.info("修改菜品状态：{}",status);
        dishService.updateStatus(status,id);
        return Result.success();
    }

    @GetMapping("/list")
    @ApiOperation(value = "根据分类id查询菜品列表")
    public Result<List<DishVO>> list(Long categoryId){
        log.info("根据分类id查询菜品列表：{}",categoryId);
        List<DishVO> dishVOList = dishService.list(categoryId);
        return Result.success(dishVOList);
    }
}
