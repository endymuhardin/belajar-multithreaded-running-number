package com.muhardin.endy.belajar.database.isolation.service;

import com.muhardin.endy.belajar.database.isolation.dao.RunningNumberDao;
import com.muhardin.endy.belajar.database.isolation.entity.RunningNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RunningNumberService {
    
    @Autowired private RunningNumberDao dao;
    
    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    @Transactional
    public RunningNumber generate(String kegunaan, String resetKeyword){
        RunningNumber rn = dao.findByKegunaanAndResetKeyword(kegunaan, resetKeyword);
        if(rn == null){
            rn = new RunningNumber();
            rn.setKegunaan(kegunaan);
            rn.setResetKeyword(resetKeyword);
            rn.setNomerTerbaru(0L);
        }
        
        logger.info("Angka lama : {}", rn.getNomerTerbaru());
        rn.setNomerTerbaru(rn.getNomerTerbaru() + 1);
        dao.save(rn);
        logger.info("Angka baru : {}",rn.getNomerTerbaru());
        
        return rn;
    }
}
