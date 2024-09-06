package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.result.PageResult;
import com.sky.vo.DishItemVO;
import com.sky.vo.DishVO;
import com.sky.vo.SetmealVO;

import java.util.List;


public interface SetmealService {
    void save(SetmealDTO setmealDTO);

    PageResult page(SetmealPageQueryDTO setmealPageQueryDTO);

    void delete(List<Long> ids);

    SetmealVO getByIdWithDish(Long id);

    void update(SetmealDTO setmealDTO);

    void updateStatus(Integer status, Long id);

    List<Setmeal> list(Setmeal setmeal);

    List<DishItemVO> getDishItemById(Long id);
}
