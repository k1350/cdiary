/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cdiary.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author K
 */
@Entity
@Table(name = "c1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "C1.findAll", query = "SELECT c FROM C1 c"),
    @NamedQuery(name = "C1.findByC1key", query = "SELECT c FROM C1 c WHERE c.c1key = :c1key"),
    @NamedQuery(name = "C1.findByC1value", query = "SELECT c FROM C1 c WHERE c.c1value = :c1value")})
public class C1 implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "c1key")
    private Integer c1key;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "c1value")
    private String c1value;

    public C1() {
    }

    public C1(Integer c1key) {
        this.c1key = c1key;
    }

    public C1(Integer c1key, String c1value) {
        this.c1key = c1key;
        this.c1value = c1value;
    }

    public Integer getC1key() {
        return c1key;
    }

    public void setC1key(Integer c1key) {
        this.c1key = c1key;
    }

    public String getC1value() {
        return c1value;
    }

    public void setC1value(String c1value) {
        this.c1value = c1value;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (c1key != null ? c1key.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof C1)) {
            return false;
        }
        C1 other = (C1) object;
        if ((this.c1key == null && other.c1key != null) || (this.c1key != null && !this.c1key.equals(other.c1key))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.cdiary.entity.C1[ c1key=" + c1key + " ]";
    }
    
}
