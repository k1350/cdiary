package com.mycompany.cdiary.web;

import com.mycompany.cdiary.constants.Constants;
import com.mycompany.cdiary.constants.SelectOneMenuItems;
import com.mycompany.cdiary.logic.EntryLogic;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@RequestScoped
public class EntryInputBean implements Serializable {
    @Inject
    transient Logger log;
    
    @EJB
    private EntryLogic entryLogic;
    
    private String userId;
    private int c1;
    private int c2;
    private int c3;
    private int rating;
    private String image;
    private String note;
    private HttpSession session;
    
    @PostConstruct
    public void init() {
        this.session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        this.userId = this.session.getAttribute(Constants.USER_KEY).toString();
        this.c1 = 1;
        this.c2 = 1;
        this.c3 = 1;
        this.rating = 3;
        this.image = "";
        this.note = "";
    }
    
    public String register() {
        log.info("rating: " + this.rating);
        return "/user/home?faces-redirect=true";
    }
    
    public List<SelectItem> getC1items() {
        return SelectOneMenuItems.c1items;
    }
    
    public List<SelectItem> getC2items() {
        return SelectOneMenuItems.c2items;
    }
    
    public List<SelectItem> getC3items() {
        return SelectOneMenuItems.c3items;
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
    
    
}
