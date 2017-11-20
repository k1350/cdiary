package com.mycompany.cdiary.web;

import com.mycompany.cdiary.constants.Constants;
import com.mycompany.cdiary.logic.EntryLogic;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
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
    private String c1;
    private String c2;
    private String c3;
    private int rating;
    private String note;
    private HttpSession session;
    
    @PostConstruct
    public void init() {
        this.session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        this.userId = this.session.getAttribute(Constants.USER_KEY).toString();
        this.c1 = "";
        this.c2 = "";
        this.c3 = "";
        this.rating = 0;
        this.note = "";
    }
    
    public void register() {
        this.entryLogic.register(userId, 1, this.c1, this.c2, this.c3, this.rating, this.note);
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
    
    
}
