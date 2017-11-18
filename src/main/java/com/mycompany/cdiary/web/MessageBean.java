package com.mycompany.cdiary.web;

import com.mycompany.cdiary.constants.Constants;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class MessageBean {
    @Inject
    transient Logger log;
    
    private String errorTitle;
    private String errorMsg;

    @PostConstruct
    public void init() {
        try {
            setErrorTitle(FacesContext.getCurrentInstance().getExternalContext().getFlash().get(Constants.ErrTitle_Key).toString());
            setErrorMsg(FacesContext.getCurrentInstance().getExternalContext().getFlash().get(Constants.ErrMsg_Key).toString());
        } catch (Exception e) {
            setErrorTitle("");
            setErrorMsg("");
        }
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
