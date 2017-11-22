/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cdiary.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    @NamedQuery(name = "Entry.findById", query = "SELECT e FROM Entry e WHERE e.id = :id"),
    @NamedQuery(name = "Entry.findByDate", query = "SELECT e FROM Entry e WHERE e.date = :date"),
    @NamedQuery(name = "Entry.findByC1", query = "SELECT e FROM Entry e WHERE e.c1 = :c1"),
    @NamedQuery(name = "Entry.findByC2", query = "SELECT e FROM Entry e WHERE e.c2 = :c2"),
    @NamedQuery(name = "Entry.findByC3", query = "SELECT e FROM Entry e WHERE e.c3 = :c3"),
    @NamedQuery(name = "Entry.findByRating", query = "SELECT e FROM Entry e WHERE e.rating = :rating"),
    @NamedQuery(name = "Entry.findByShop", query = "SELECT e FROM Entry e WHERE e.shop = :shop"),
    @NamedQuery(name = "Entry.findByImage", query = "SELECT e FROM Entry e WHERE e.image = :image"),
    @NamedQuery(name = "Entry.findByNote", query = "SELECT e FROM Entry e WHERE e.note = :note")})
public class Entry implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @NotNull
    @Column(name = "C1")
    private int c1;
    @Basic(optional = false)
    @NotNull
    @Column(name = "C2")
    private int c2;
    @Basic(optional = false)
    @NotNull
    @Column(name = "C3")
    private int c3;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Rating")
    private int rating;
    @Size(max = 100)
    @Column(name = "Shop")
    private String shop;
    @Size(max = 300)
    @Column(name = "Image")
    private String image;
    @Size(max = 1000)
    @Column(name = "Note")
    private String note;
    @JoinColumn(name = "UserID", referencedColumnName = "UserID")
    @ManyToOne(optional = false)
    private User userID;

    public Entry() {
    }

    public Entry(Long id) {
        this.id = id;
    }

    public Entry(Long id, Date date, int c1, int c2, int c3, int rating) {
        this.id = id;
        this.date = date;
        this.c1 = c1;
        this.c2 = c2;
        this.c3 = c3;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getC1() {
        return c1;
    }

    public void setC1(int c1) {
        this.c1 = c1;
    }

    public int getC2() {
        return c2;
    }

    public void setC2(int c2) {
        this.c2 = c2;
    }

    public int getC3() {
        return c3;
    }

    public void setC3(int c3) {
        this.c3 = c3;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public User getUserID() {
        return userID;
    }

    public void setUserID(User userID) {
        this.userID = userID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Entry)) {
            return false;
        }
        Entry other = (Entry) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.cdiary.entity.Entry[ id=" + id + " ]";
    }
    
}
