package com.pj.springbootvue.mapper;

import com.pj.springbootvue.pojo.Weather;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface WeatherMapper {
    //查询天气
    @Select("select * from t_weather")
    public List<Weather> FindAllWeather();
}
