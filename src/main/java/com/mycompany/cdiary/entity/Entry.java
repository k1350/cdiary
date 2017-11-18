/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cdiary.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "entry")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Entry.findAll", query = "SELECT e FROM Entry e"),
    @NamedQuery(name = "Entry.findById", query = "SELECT e FROM Entry e WHERE e.entryPK.id = :id"),
    @NamedQuery(name = "Entry.findByUserID", query = "SELECT e FROM Entry e WHERE e.entryPK.userID = :userID"),
    @NamedQuery(name = "Entry.findByC1", query = "SELECT e FROM Entry e WHERE e.c1 = :c1"),
    @NamedQuery(name = "Entry.findByC2", query = "SELECT e FROM Entry e WHERE e.c2 = :c2"),
    @NamedQuery(name = "Entry.findByC3", query = "SELECT e FROM Entry e WHERE e.c3 = :c3"),
    @NamedQuery(name = "Entry.findByRating", query = "SELECT e FROM Entry e WHERE e.rating = :rating"),
    @NamedQuery(name = "Entry.findByNote", query = "SELECT e FROM Entry e WHERE e.note = :note")})
public class Entry implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EntryPK entryPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "C1")
    private String c1;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "C2")
    private String c2;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "C3")
    private String c3;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Rating")
    private int rating;
    @Size(max = 1000)
    @Column(name = "Note")
    private String note;
    @JoinColumn(name = "UserID", referencedColumnName = "UserID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;

    public Entry() {
    }

    public Entry(EntryPK entryPK) {
        this.entryPK = entryPK;
    }

    public Entry(EntryPK entryPK, String c1, String c2, String c3, int rating) {
        this.entryPK = entryPK;
        this.c1 = c1;
        this.c2 = c2;
        this.c3 = c3;
        this.rating = rating;
    }

    public Entry(long id, String userID) {
        this.entryPK = new EntryPK(id, userID);
    }

    public EntryPK getEntryPK() {
        return entryPK;
    }

    public void setEntryPK(EntryPK entryPK) {
        this.entryPK = entryPK;
    }

    public String getC1() {
        return c1;
    }

    public void setC1(String c1) {
        this.c1 = c1;
    }

    public String getC2() {
        return c2;
    }

    public void setC2(String c2) {
        this.c2 = c2;
    }

    public String getC3() {
        return c3;
    }

    public void setC3(String c3) {
        this.c3 = c3;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (entryPK != null ? entryPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Entry)) {
            return false;
        }
        Entry other = (Entry) object;
        if ((this.entryPK == null && other.entryPK != null) || (this.entryPK != null && !this.entryPK.equals(other.entryPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.cdiary.entiry.Entry[ entryPK=" + entryPK + " ]";
    }
    
}
