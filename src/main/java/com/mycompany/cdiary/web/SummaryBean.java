package com.mycompany.cdiary.web;

import com.mycompany.cdiary.constants.Constants;
import com.mycompany.cdiary.logic.SummaryLogic;
import com.mycompany.cdiary.util.CalendarUtil;
import com.mycompany.cdiary.validator.NoBlank;
import com.mycompany.cdiary.validator.ValidDate;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.servlet.http.HttpSession;

@Named
@RequestScoped
public class SummaryBean {
    private JsonObject data1;
    private JsonObject data2;
    private JsonObject data3;
    
    @Inject
    transient Logger log;
    
    @EJB
    private SummaryLogic summaryLogic;
    
    private String userId;
    private HttpSession session;
    
    @NoBlank
    @ValidDate
    private String startDate;

    @NoBlank
    @ValidDate
    private String endDate;
    
    private JsonArray colsValue;
    
    @PostConstruct
    public void init() {
        this.session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        this.userId = this.session.getAttribute(Constants.USER_KEY).toString();
        Calendar cal = Calendar.getInstance();
        Calendar one = Calendar.getInstance();
        one.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1);
        this.startDate = CalendarUtil.getString(one);
        this.endDate = CalendarUtil.getString(cal);
        
        JsonArrayBuilder cols = Json.createArrayBuilder();
        cols.add(Json.createObjectBuilder()
                        .add("id", "")
                        .add("label", "Topping")
                        .add("pattern", "")
                        .add("type", "string").build());
        cols.add(Json.createObjectBuilder()
                        .add("id", "")
                        .add("label", "Slices")
                        .add("pattern", "")
                        .add("type", "number").build());
        this.colsValue = cols.build();
        
        setData1();
        setData2();
        setData3();
    }
    
    public String update() {
        setData1();
        setData2();
        setData3();
        return "";
    }
    
    public void setData1() {
        JsonArray row = this.summaryLogic.getSelect1(userId, CalendarUtil.parseDate(startDate), CalendarUtil.parseDate(endDate));

        this.data1 = Json.createObjectBuilder()
                .add("cols", colsValue)
                .add("rows", row)
                .build();
    }
    
    public JsonObject getData1() {
        return this.data1;
    }

    public JsonObject getData2() {
        return data2;
    }

    public void setData2() {
        JsonArray row = this.summaryLogic.getSelect2(userId, CalendarUtil.parseDate(startDate), CalendarUtil.parseDate(endDate));
        
        this.data2 = Json.createObjectBuilder()
                .add("cols", colsValue)
                .add("rows", row)
                .build();
    }

    public JsonObject getData3() {
        return data3;
    }

    public void setData3() {
         JsonArray row = this.summaryLogic.getSelect3(userId, CalendarUtil.parseDate(startDate), CalendarUtil.parseDate(endDate));
        
        this.data3 = Json.createObjectBuilder()
                .add("cols", colsValue)
                .add("rows", row)
                .build();
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
    
    
}
