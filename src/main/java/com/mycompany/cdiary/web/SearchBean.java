package com.mycompany.cdiary.web;

import com.mycompany.cdiary.constants.Constants;
import com.mycompany.cdiary.entity.Entry;
import com.mycompany.cdiary.logic.EntryLogic;
import java.util.List;
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
public class SearchBean {
    @Inject
    private transient Logger log;
    
    @EJB
    private EntryLogic entryLogic;
    
    private String userId;
    private HttpSession session;
    
    @PostConstruct
    public void init() {
        this.session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        this.userId = this.session.getAttribute(Constants.USER_KEY).toString();
    }
    
    public String mock() {
        List<Entry> entries = this.entryLogic.findAll();
        if (entries.size() == 0) {
            return "/user/home?faces-redirect=true";
        }
        this.session.setAttribute(Constants.VIEW_KEY, entries.get(0).getId());
        return "/user/view?faces-redirect=true";
    }
}
