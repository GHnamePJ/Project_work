package com.pj.springbootvue.controller;

import com.pj.springbootvue.mapper.WeatherMapper;
import com.pj.springbootvue.pojo.Weather;
import com.pj.springbootvue.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : Pj-Xk
 * @date : 2022-10-17 16:38
 **/
@RestController
public class WeatherController {
    @Autowired
    WeatherService weatherService;
    @RequestMapping("/weatherInfo")
    public List<Weather> WeatherInfo(){
        List<Weather> weathers = weatherService.FindAllWeather();
        System.out.println(weathers);
        return weathers;
    }
}
