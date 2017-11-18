package com.mycompany.cdiary.web;

import com.mycompany.cdiary.constants.Constants;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@RequestScoped
public class MessageBean {
    @Inject
    transient Logger log;
    
    private String errorTitle;
    private String errorMsg;

    @PostConstruct
    public void init() {
        HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        setErrorTitle(session.getAttribute(Constants.ErrTitle_Key).toString());
        setErrorMsg(session.getAttribute(Constants.ErrMsg_Key).toString());
        session.removeAttribute(Constants.ErrTitle_Key);
        session.removeAttribute(Constants.ErrMsg_Key);
    }

    public String getErrorTitle() {
        return errorTitle;
    }

    public void setErrorTitle(String errorTitle) {
        this.errorTitle = errorTitle;
    }
    
    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
    
    
}
