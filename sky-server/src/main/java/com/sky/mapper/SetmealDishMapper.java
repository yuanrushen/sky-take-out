package com.sky.mapper;


import com.sky.entity.SetmealDish;
import com.sky.vo.DishItemVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


import java.util.List;

@Mapper
public interface SetmealDishMapper {
    /**
     * 根据菜品id查询对应的套餐id
     *
     * @param dishIds
     * @return
     */
    //select setmeal_id from setmeal_dish where dish_id in (1,2,3,4)
    List<Long> getSetmealIdsByDishIds(List<Long> dishIds);


    void insertBatch(List<SetmealDish> setmealDishes);

    @Delete("delete from setmeal_dish where setmeal_id=#{setmealId}")
    void deleteBySetmealId(Long id);

    void deleteBySetmealIds(List<Long> setmealIds);

    @Select("select * from setmeal_dish where setmeal_id=#{setmealId}")
    List<SetmealDish> getBySetmealId(Long id);

    /**
     * 根据套餐id查询菜品选项
     *
     * @param setmealId
     * @return
     */
    @Select("select sd.name, sd.copies, d.image, d.description " +
            "from setmeal_dish sd left join dish d on sd.dish_id = d.id " +
            "where sd.setmeal_id = #{setmealId}")
    List<DishItemVO> getSetmealIdsByDishId(Long setmealId);
}
