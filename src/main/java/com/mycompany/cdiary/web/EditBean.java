package com.mycompany.cdiary.web;

import com.mycompany.cdiary.constants.Constants;
import com.mycompany.cdiary.constants.SelectOneMenuItems;
import com.mycompany.cdiary.entity.Entry;
import com.mycompany.cdiary.logic.EntryLogic;
import com.mycompany.cdiary.validator.NoBlank;
import java.io.Serializable;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Size;

@Named
@RequestScoped
public class EditBean implements Serializable {
    @Inject
    transient Logger log;
    
    @EJB
    private SelectOneMenuItems items;
    
    @EJB
    private EntryLogic entryLogic;
    
    private String userId;

    @NoBlank
    private String date;
    private int c1;
    private int c2;
    private int c3;
    private int rating;
    @Size(max=100)
    private String shop;
    private String image;
    @Size(max=1000)
    private String note;
    private HttpSession session;
    private Entry entry;
    
    private long id;
    
    @PostConstruct
    public void init() {
        this.session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        this.userId = this.session.getAttribute(Constants.USER_KEY).toString();
        
        // デバッグ用
        this.id = (long)this.session.getAttribute(Constants.VIEW_KEY);
        this.entry = this.entryLogic.find(this.id);
        if (this.entry == null) {
            // 例外を投げる
        }
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(this.entry.getDate());
        this.date = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DATE);
        this.c1 = this.entry.getC1();
        this.c2 = this.entry.getC2();
        this.c3 = this.entry.getC3();
        this.rating = this.entry.getRating();
        this.shop = this.entry.getShop();
        this.image = this.entry.getImage();
        this.note = this.entry.getNote();
    }
    
    public String update() {
        Calendar cl = Calendar.getInstance();
        String[] dl = this.date.split("-");
        cl.set(Integer.parseInt(dl[0]), Integer.parseInt(dl[1]) - 1, Integer.parseInt(dl[2]));
        this.entry.setDate(cl.getTime());
        this.entry.setC1(c1);
        this.entry.setC2(c2);
        this.entry.setC3(c3);
        this.entry.setRating(rating);
        this.entry.setShop(shop);
        this.entry.setImage(image);
        this.entry.setNote(note);
        this.entryLogic.update(this.entry);

        return "/user/view?faces-redirect=true";
    }
    
    public String cancel() {       
        return "/user/view?faces-redirect=true";
    }
    
    public List<SelectItem> getC1items() {
        return items.getC1items();
    }
    
    public List<SelectItem> getC2items() {
        return items.getC2items();
    }
    
    public List<SelectItem> getC3items() {
        return items.getC3items();
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }
}
