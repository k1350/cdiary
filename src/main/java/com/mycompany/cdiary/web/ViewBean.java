package com.mycompany.cdiary.web;

import com.mycompany.cdiary.constants.Constants;
import com.mycompany.cdiary.constants.SelectOneMenuItems;
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
    
    @EJB
    private SelectOneMenuItems somi;
    
    private String userId;

    @NoBlank
    private String year;
    private String month;
    private String date;
    private String c1Item;
    private String c2Item;
    private String c3Item;
    private int rating;
    private String shop;
    private String image;
    private String note;
    private HttpSession session;
    
    @PostConstruct
    public void init() {
        this.session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        this.userId = this.session.getAttribute(Constants.USER_KEY).toString();
        
        // デバッグ用
        Entry entry = this.entryLogic.find(2);
        /*
        long id = (long)this.session.getAttribute(Constants.VIEW_KEY);
        this.session.removeAttribute(Constants.VIEW_KEY);
        Entry entry = this.entryLogic.find(id);
        if (entry == null) {
            // 例外を投げる
             
        }
        */
        Calendar cal = Calendar.getInstance();
        cal.setTime(entry.getDate());
        this.year = String.valueOf(cal.get(Calendar.YEAR));
        this.month = String.valueOf(cal.get(Calendar.MONTH) + 1);
        this.date = String.valueOf(cal.get(Calendar.DATE));
        
        this.c1Item = this.somi.getC1item(entry.getC1());
        this.c2Item = this.somi.getC2item(entry.getC2());
        this.c3Item = this.somi.getC3item(entry.getC3());
        this.rating = entry.getRating();
        this.shop = entry.getShop();
        this.image = entry.getImage();
        this.note = entry.getNote();

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

    public String getC1Item() {
        return c1Item;
    }

    public String getC2Item() {
        return c2Item;
    }

    public String getC3Item() {
        return c3Item;
    }
    

}
