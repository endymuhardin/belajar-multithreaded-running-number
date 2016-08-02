/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muhardin.endy.belajar.database.isolation.service;

import com.muhardin.endy.belajar.database.isolation.entity.RunningNumber;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.exception.LockAcquisitionException;
import org.junit.Assert;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.CannotAcquireLockException;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RunningNumberServiceTest {
    
    @Autowired private RunningNumberService service;

    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    @Test
    public void testGenerateSimple() {
        RunningNumber num = service.generate(new Date());
        assertNotNull(num);
        Long angka = num.getNomerTerbaru();
        logger.info("Angka terakhir = {}",angka);
    }
    
    @Test
    public void testMultithreaded() throws Exception {
        int numThread = 20;
        final int numLoop = 100;

        final List<Long> generatedNumbers = 
                Collections.synchronizedList(new ArrayList<Long>());

        final List<GeneratorThread> generators = 
                Collections.synchronizedList(new ArrayList<GeneratorThread>());

        // create threads
        for (int i = 0; i < numThread; i++) {
           generators.add(new GeneratorThread(service, generatedNumbers, numLoop, i));
        }

        
        logger.info("Start {} thread, masing-masing {} iterasi", numThread, numLoop);
        // run threads
        for (GeneratorThread generatorThread : generators) {
            new Thread(generatorThread).start();
        }

        // check thread completion
        boolean completed = false;
        while(!completed){
            for (GeneratorThread generatorThread : generators) {
                completed = generatorThread.getCompleted();
                System.out.println("Thread : "+generatorThread.getThreadNo() +" "+(completed?"completed":"still running"));
            }

            Thread.sleep(10);
        }

        logger.info("Selesai menjalankan test multithreading");
        logger.info("Jumlah angka yang dihasilkan : "+generatedNumbers.size());
        
        logger.info("Memeriksa duplikat .... ");
        // all completed, now check result
        Set<Long> checker = new HashSet<Long>();
        Long duplicate = 0L;
        for (Long result : generatedNumbers) {
            if(!checker.add(result)){
                duplicate++;  
            }
        }
        
        logger.info("Jumlah duplikat : {}", duplicate);
        
        if(duplicate > 0){
            Assert.fail("Ada "+duplicate+" yang duplikat");
        }

        // all OK, check number of successful number generation:
        System.out.println("# of number generated : "+checker.size());
    }

    private static class GeneratorThread implements Runnable {

        private List<Long> generatedNumbers;
        private Integer numLoops;
        private Integer threadNo;
        private Boolean completed = false;
        
        private RunningNumberService rns;

        public Boolean getCompleted() {
            return completed;
        }

        public Integer getThreadNo() {
            return threadNo;
        }

        public GeneratorThread(RunningNumberService rns, List<Long> generatedNumbers, Integer numLoops, Integer threadNo) {
            this.generatedNumbers = generatedNumbers;
            this.numLoops = numLoops;
            this.threadNo = threadNo;
            this.rns = rns;
        }

        public void run() {
            for (int j = 0; j < numLoops; j++) {
                try {
                    RunningNumber num = rns.generate(new Date());
                    System.out.println("Thread " + threadNo + " : Current Number : " + num.getNomerTerbaru());
                    generatedNumbers.add(num.getNomerTerbaru());
                } catch(CannotAcquireLockException err) {
                    System.out.println("Thread " + threadNo +" berebut lock, no problem, lanjut saja");
                } catch (LockAcquisitionException err){
                    System.out.println("Thread " + threadNo +" berebut lock, no problem, lanjut saja");
                }
            }
            completed = true;
        }
    }
}
