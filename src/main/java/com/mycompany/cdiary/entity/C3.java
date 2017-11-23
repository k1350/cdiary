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
@Table(name = "c3")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "C3.findAll", query = "SELECT c FROM C3 c"),
    @NamedQuery(name = "C3.findByC3key", query = "SELECT c FROM C3 c WHERE c.c3key = :c3key"),
    @NamedQuery(name = "C3.findByC3value", query = "SELECT c FROM C3 c WHERE c.c3value = :c3value")})
public class C3 implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "c3key")
    private Integer c3key;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "c3value")
    private String c3value;

    public C3() {
    }

    public C3(Integer c3key) {
        this.c3key = c3key;
    }

    public C3(Integer c3key, String c3value) {
        this.c3key = c3key;
        this.c3value = c3value;
    }

    public Integer getC3key() {
        return c3key;
    }

    public void setC3key(Integer c3key) {
        this.c3key = c3key;
    }

    public String getC3value() {
        return c3value;
    }

    public void setC3value(String c3value) {
        this.c3value = c3value;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (c3key != null ? c3key.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof C3)) {
            return false;
        }
        C3 other = (C3) object;
        if ((this.c3key == null && other.c3key != null) || (this.c3key != null && !this.c3key.equals(other.c3key))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.cdiary.entity.C3[ c3key=" + c3key + " ]";
    }
    
}
