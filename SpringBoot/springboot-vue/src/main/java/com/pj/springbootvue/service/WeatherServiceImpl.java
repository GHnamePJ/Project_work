package com.pj.springbootvue.service;

import com.pj.springbootvue.mapper.WeatherMapper;
import com.pj.springbootvue.pojo.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : Pj-Xk
 * @date : 2022-10-17 16:02
 **/
@Service
public class WeatherServiceImpl implements WeatherService {
    @Autowired
    WeatherMapper weatherMapper;
    public void setWeatherMapper(WeatherMapper weatherMapper) {
        this.weatherMapper = weatherMapper;
    }
    @Override
    public List<Weather> FindAllWeather() {
        List<Weather> weathers = weatherMapper.FindAllWeather();
        return weathers;
    }
}
