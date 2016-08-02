/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.muhardin.endy.belajar.database.isolation.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author endy
 */
@Entity @Table(name="running_number")
public class RunningNumber implements Serializable {
    @Id @GeneratedValue
    private Long id;

    @Column(name="tanggal_kerja", unique = true) @Temporal(TemporalType.DATE)
    private Date tanggalKerja;

    @Column(name="nomer_terbaru")
    private Long nomerTerbaru = 0L;

    public Date getTanggalKerja() {
        return tanggalKerja;
    }

    public void setTanggalKerja(Date tanggalKerja) {
        this.tanggalKerja = tanggalKerja;
    }

    public Long getNomerTerbaru() {
        return nomerTerbaru;
    }

    public void setNomerTerbaru(Long nomerTerbaru) {
        this.nomerTerbaru = nomerTerbaru;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
}
