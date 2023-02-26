package com.pj.springbootvue.service;

import com.pj.springbootvue.pojo.Weather;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : Pj-Xk
 * @date : 2022-10-17 16:01
 **/
@Service
public interface WeatherService {
    public List<Weather> FindAllWeather();
}
