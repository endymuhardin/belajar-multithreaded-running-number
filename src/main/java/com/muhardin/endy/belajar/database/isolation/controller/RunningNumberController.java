package com.muhardin.endy.belajar.database.isolation.controller;

import com.muhardin.endy.belajar.database.isolation.entity.RunningNumber;
import com.muhardin.endy.belajar.database.isolation.service.RunningNumberService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RunningNumberController {
    
    @Autowired private RunningNumberService service;
    
    @RequestMapping("/generate")
    public RunningNumber generate(@RequestParam String kegunaan){
        return service.generate(kegunaan, new Date());
    }
}
