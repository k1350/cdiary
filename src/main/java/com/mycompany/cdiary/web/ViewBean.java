package com.mycompany.cdiary.web;

import com.mycompany.cdiary.constants.Constants;
import com.mycompany.cdiary.entity.Entry;
import com.mycompany.cdiary.logic.EntryLogic;
import com.mycompany.cdiary.validator.NoBlank;
import java.io.Serializable;
import java.util.Calendar;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;

@Named
@RequestScoped
public class ViewBean implements Serializable {
    @Inject
    private transient Logger log;
    
    @EJB
    private EntryLogic entryLogic;
    
    private String userId;

    @NoBlank
    private String year;
    private String month;
    private String date;
    private int c1;
    private int c2;
    private int c3;
    private String c1Item;
    private String c2Item;
    private String c3Item;
    private int rating;
    private String shop;
    private String image;
    private String note;
    private HttpSession session;
    private boolean doEdit;
    
    @PostConstruct
    public void init() {
        this.session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        this.userId = this.session.getAttribute(Constants.USER_KEY).toString();
        long id = (long)this.session.getAttribute(Constants.VIEW_KEY);
        this.session.removeAttribute(Constants.VIEW_KEY);
        
        Entry entry = this.entryLogic.find(id);
        if (entry == null) {
            // 例外を投げる
        }
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(entry.getDate());
        this.year = String.valueOf(cal.get(Calendar.YEAR));
        this.month = String.valueOf(cal.get(Calendar.MONTH) + 1);
        this.date = String.valueOf(cal.get(Calendar.DATE));
        
        this.c1 = entry.getC1();
        this.c2 = entry.getC2();
        this.c3 = entry.getC3();
        this.rating = entry.getRating();
        this.shop = entry.getShop();
        this.image = entry.getImage();
        this.note = entry.getNote();
        
        this.doEdit = false;
    }

    public String getYear() {
        return year;
    }

    public String getMonth() {
        return month;
    }

    public String getDate() {
        return date;
    }

    public int getC1() {
        return c1;
    }

    public int getC2() {
        return c2;
    }

    public int getC3() {
        return c3;
    }

    public int getRating() {
        return rating;
    }

    public String getShop() {
        return shop;
    }

    public String getImage() {
        return image;
    }

    public String getNote() {
        return note;
    }
    
    public boolean getDoEdit() {
        return this.doEdit;
    }
}
