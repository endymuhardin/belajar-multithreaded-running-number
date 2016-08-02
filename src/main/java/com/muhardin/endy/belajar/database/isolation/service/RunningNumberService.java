package com.muhardin.endy.belajar.database.isolation.service;

import com.muhardin.endy.belajar.database.isolation.dao.RunningNumberDao;
import com.muhardin.endy.belajar.database.isolation.entity.RunningNumber;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RunningNumberService {
    
    @Autowired private RunningNumberDao dao;
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Transactional
    public RunningNumber generate(Date tanggalKerja){
        RunningNumber terbaru = dao.findByCurrentDate(tanggalKerja);
        if(terbaru == null){
            terbaru = new RunningNumber();
            terbaru.setCurrentDate(tanggalKerja);
            terbaru.setCurrentNumber(0L);
        }
        
        logger.info("Angka lama : {}", terbaru.getCurrentNumber());
        terbaru.setCurrentNumber(terbaru.getCurrentNumber() + 1);
        dao.save(terbaru);
        logger.info("Angka baru : {}",terbaru.getCurrentNumber());
        
        return terbaru;
    }
}
