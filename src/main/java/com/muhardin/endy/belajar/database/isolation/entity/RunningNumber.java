/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.muhardin.endy.belajar.database.isolation.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author endy
 */
@Entity 
@Table(
        name="running_number", 
        uniqueConstraints = 
                @UniqueConstraint(columnNames = {"reset_keyword", "kegunaan"})
)
public class RunningNumber implements Serializable {
    @Id @GeneratedValue
    private Long id;

    @NotNull
    @Column(name="reset_keyword", nullable = false)
    private String resetKeyword;

    @NotNull @Min(1)
    @Column(name="nomer_terbaru", nullable = false)
    private Long nomerTerbaru = 0L;
    
    @NotNull @NotEmpty
    @Column(nullable = false)
    private String kegunaan;

    public String getResetKeyword() {
        return resetKeyword;
    }

    public void setResetKeyword(String resetKeyword) {
        this.resetKeyword = resetKeyword;
    }

    public String getKegunaan() {
        return kegunaan;
    }

    public void setKegunaan(String kegunaan) {
        this.kegunaan = kegunaan;
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
