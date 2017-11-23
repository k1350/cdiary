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
@Table(name = "c2")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "C2.findAll", query = "SELECT c FROM C2 c"),
    @NamedQuery(name = "C2.findByC2key", query = "SELECT c FROM C2 c WHERE c.c2key = :c2key"),
    @NamedQuery(name = "C2.findByC2value", query = "SELECT c FROM C2 c WHERE c.c2value = :c2value")})
public class C2 implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "c2key")
    private Integer c2key;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "c2value")
    private String c2value;

    public C2() {
    }

    public C2(Integer c2key) {
        this.c2key = c2key;
    }

    public C2(Integer c2key, String c2value) {
        this.c2key = c2key;
        this.c2value = c2value;
    }

    public Integer getC2key() {
        return c2key;
    }

    public void setC2key(Integer c2key) {
        this.c2key = c2key;
    }

    public String getC2value() {
        return c2value;
    }

    public void setC2value(String c2value) {
        this.c2value = c2value;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (c2key != null ? c2key.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof C2)) {
            return false;
        }
        C2 other = (C2) object;
        if ((this.c2key == null && other.c2key != null) || (this.c2key != null && !this.c2key.equals(other.c2key))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.cdiary.entity.C2[ c2key=" + c2key + " ]";
    }
    
}
