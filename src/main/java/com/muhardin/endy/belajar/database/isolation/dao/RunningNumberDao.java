package com.muhardin.endy.belajar.database.isolation.dao;

import com.muhardin.endy.belajar.database.isolation.entity.RunningNumber;
import java.util.Date;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RunningNumberDao extends PagingAndSortingRepository<RunningNumber, Long>{
    public RunningNumber findByCurrentDate(Date tanggalKerja);
}
