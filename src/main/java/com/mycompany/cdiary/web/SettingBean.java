package com.mycompany.cdiary.web;

import com.mycompany.cdiary.constants.Constants;
import com.mycompany.cdiary.entity.User;
import com.mycompany.cdiary.logic.UserLogic;
import com.mycompany.cdiary.validator.CustomEmail;
import com.mycompany.cdiary.validator.NoBlank;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Size;

@Named
@SessionScoped
public class SettingBean implements Serializable {
    
    @Inject
    transient Logger log;
    
    @EJB
    private UserLogic userLogic;
    
    private String userId;
    
    @NoBlank
    @Size(min=1, max=100)
    private String name;
    
    @NoBlank
    @Size(min = 1, max = 300)
    @CustomEmail
    private String mail;
    
    private User user;
    
    private HttpSession session;
    
    @PostConstruct
    public void init() {
        this.session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        this.userId = this.session.getAttribute(Constants.USER_KEY).toString();
        this.user = this.userLogic.getUser(this.userId);
        this.name = this.user.getName();
        this.mail = this.user.getMail();
    }
    
    /**
     * ログアウト
     * @return 
     */
    public String logout() {
        try {
            // セッションを無効にする
            if (this.session != null) {
                FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            }
            return "/index.xhtml?faces-redirect=true";
        } catch (Exception e) {
            log.info(e.getMessage());
            return null;
        }
    }
    
    /**
     * アカウントを削除する
     * @return 
     */
    public String invalidAccount() {
        try {
            this.userLogic.delete(this.user);
            if (this.session != null) {
                FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            }
            return "/index.xhtml?faces-redirect=true";
        } catch (Exception e) {
            log.info(e.getMessage());
            return null;
        }
    }
    
    public String update() {
        this.userLogic.update(this.userId, this.name, this.mail);
        return "/user/setting?faces-redirect=true";
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        if (this.name != name) {
            this.name = name;
        }
        
    }

    public String getMail() {
        return this.mail;
    }

    public void setMail(String mail) {
        if (this.mail != mail) {
            // すべての全角スペースを削除
            mail = mail.replaceAll("[　]*", "");
            // すべての空白文字を削除
            mail = mail.replaceAll("[\\s]*", "");
            this.mail = mail;
        }
    }
    
    
}
