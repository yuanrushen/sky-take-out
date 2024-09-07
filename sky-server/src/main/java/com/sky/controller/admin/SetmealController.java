package com.sky.controller.admin;

import com.github.pagehelper.Page;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import com.sky.vo.SetmealVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/setmeal")
@Slf4j
@Api(value = "套餐管理",tags = "套餐管理")
public class SetmealController {
    @Autowired
    private SetmealService setmealService;

    /*
    新增套餐
     */
    @PostMapping
    @ApiOperation(value = "新增套餐")
    @CacheEvict(cacheNames = "setmealCache",key = "#setmealDTO.categoryId")
    public Result save(@RequestBody SetmealDTO setmealDTO){
        log.info("新增套餐：{}",setmealDTO);
        setmealService.save(setmealDTO);
        return Result.success();
    }

    /*
    分页查询
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询")
    public Result<PageResult> page(SetmealPageQueryDTO setmealPageQueryDTO){
        log.info("分页查询：{}",setmealPageQueryDTO);
        PageResult pageResult = setmealService.page(setmealPageQueryDTO);
        return Result.success(pageResult);
    }

    /*
    批量删除
     */
    @DeleteMapping
    @ApiOperation(value = "批量删除")
    @CacheEvict(cacheNames = "setmealCache",allEntries = true)
    public Result delete(@RequestParam List<Long> ids){
        log.info("批量删除：{}",ids);
        setmealService.delete(ids);
        return Result.success();
    }

    /*
    查询
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询")
    public Result<SetmealVO> getById(@PathVariable Long id){
        log.info("根据id查询：{}",id);
        SetmealVO setmealVO = setmealService.getByIdWithDish(id);
        return Result.success(setmealVO);
    }
    /*
    修改
     */
    @PutMapping
    @ApiOperation(value = "修改套餐")
    @CacheEvict(cacheNames = "setmealCache",allEntries = true)
    public Result update(@RequestBody SetmealDTO setmealDTO){
        log.info("修改套餐：{}",setmealDTO);
        setmealService.update(setmealDTO);
        return Result.success();
    }
    @PostMapping("status/{status}")
    @ApiOperation(value = "修改套餐状态")
    @CacheEvict(cacheNames = "setmealCache",allEntries = true)
    public Result updateStatus(@PathVariable Integer status, Long id){
        log.info("修改套餐状态：{}",status);
        setmealService.updateStatus(status,id);
        return Result.success();
    }
}
