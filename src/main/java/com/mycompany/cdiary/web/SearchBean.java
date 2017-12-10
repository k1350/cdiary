package com.mycompany.cdiary.web;

import com.mycompany.cdiary.constants.Constants;
import com.mycompany.cdiary.constants.SelectOneMenuItems;
import com.mycompany.cdiary.entity.Entry;
import com.mycompany.cdiary.logic.EntryLogic;
import com.mycompany.cdiary.util.CalendarUtil;
import com.mycompany.cdiary.util.Messages;
import com.mycompany.cdiary.validator.NoBlank;
import com.mycompany.cdiary.validator.ValidDate;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Size;

@Named
@ViewScoped
public class SearchBean implements Serializable {
    @Inject
    private transient Logger log;
    
    @EJB
    private EntryLogic entryLogic;
    @EJB
    private SelectOneMenuItems items;
    
    private String userId;
    private HttpSession session;
    
    @NoBlank
    @ValidDate
    private String startDate;

    @NoBlank
    @ValidDate
    private String endDate;
    private int c1;
    private int c2;
    private int c3;
    private int rating;
    @Size(max=100)
    private String shop;
    private String image;
    @Size(max=1000)
    private String note;
    
    private List<SelectItem> c1items;
    private List<SelectItem> c2items;
    private List<SelectItem> c3items;
    
    private List<DisplayEntry> displayEntries;
    
    private int rowIndex;
    
    @PostConstruct
    public void init() {
        this.session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        this.userId = this.session.getAttribute(Constants.USER_KEY).toString();
        Calendar cal = Calendar.getInstance();
        Calendar one = Calendar.getInstance();
        one.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1);
        this.startDate = CalendarUtil.getString(one);
        this.endDate = CalendarUtil.getString(cal);
        
        this.c1 = 0;
        this.c2 = 0;
        this.c3 = 0;
        this.rating = 1;
        this.shop = "";
        this.image = "";
        this.note = "";
        
        this.c1items = new ArrayList<SelectItem>();
        this.c1items.add(new SelectItem(0, ""));
        for (Object item : items.getC1items()) {
            this.c1items.add((SelectItem)item);
        }
        
        this.c2items = new ArrayList<SelectItem>();
        this.c2items.add(new SelectItem(0, ""));
        for (Object item : items.getC2items()) {
            this.c2items.add((SelectItem)item);
        } 
        
        this.c3items = new ArrayList<SelectItem>();
        this.c3items.add(new SelectItem(0, ""));
        for (Object item : items.getC3items()) {
            this.c3items.add((SelectItem)item);
        }        
        
        this.displayEntries = new ArrayList<>();
        innerSearch();
    }
    
    public String search() {
        innerSearch();
        return "";
    }
    
    public String view() {
        long viewId = this.displayEntries.get(rowIndex).getId();
        Entry entry = this.entryLogic.find(viewId);
        if (entry == null) {
            return "";
        }
        this.session.setAttribute(Constants.VIEW_KEY, viewId);
        return "/user/view?faces-redirect=true";
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
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

    public void setRating(int Rating) {
        this.rating = Rating;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
   
    
    public List<SelectItem> getC1items() {
        return this.c1items;
    }
    
    public List<SelectItem> getC2items() {
        return this.c2items;
    }
    
    public List<SelectItem> getC3items() {
        return this.c3items;
    }
    
    public List<DisplayEntry> getDisplayEntries() {
        return this.displayEntries;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }
    
    public String getSearchMessage() {
        if (this.displayEntries.size() == 0) {
            return Messages.getString("myRes", "searchMessage", null);
        }
        return "";
    }
    
    private void innerSearch() {
        this.displayEntries.clear();
        List<Entry> entries = this.entryLogic.search(userId, CalendarUtil.parseDate(startDate), CalendarUtil.parseDate(endDate), c1, c2, c3, rating, shop, note);
        for (Entry entry : entries) {
            DisplayEntry de = new DisplayEntry();
            de.setId(entry.getId());
            de.setDate(entry.getDate());
            de.setC1(this.items.getC1item(entry.getC1()));
            de.setC2(this.items.getC2item(entry.getC2()));
            de.setC3(this.items.getC3item(entry.getC3()));
            de.setRating(String.valueOf(entry.getRating()));
            de.setShop(entry.getShop());
            this.displayEntries.add(de);
        }
    }
}
