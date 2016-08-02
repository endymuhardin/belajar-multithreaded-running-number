package com.muhardin.endy.belajar.database.isolation.dao;

import com.muhardin.endy.belajar.database.isolation.entity.RunningNumber;
import java.util.Date;
import javax.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RunningNumberDao extends PagingAndSortingRepository<RunningNumber, Long>{
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public RunningNumber findByKegunaanAndTanggalKerja(String pemakaian, Date tanggalKerja);
}
