package com.mycompany.cdiary.web;

import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.mycompany.cdiary.constants.Constants;
import com.mycompany.cdiary.entity.User;
import com.mycompany.cdiary.logic.UserLogic;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Named
@RequestScoped
public class LoginBean {

    private String id_token;

    @Inject
    transient Logger log;

    /**
     * Global instance of the HTTP transport.
     */
    private static HttpTransport httpTransport;

    /**
     * Global instance of the JSON factory.
     */
    private static final com.google.api.client.json.JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static GoogleClientSecrets clientSecrets;
    
    @EJB
    private UserLogic userLogic;

    /**
     * ログイン
     * @return 
     */
    public String login() {
        try {
            // load client secrets
            clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(LoginBean.class.getResourceAsStream("/client_secrets.json")));
            httpTransport = GoogleNetHttpTransport.newTrustedTransport();
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(httpTransport, JSON_FACTORY).
                    setAudience(Collections.singletonList(clientSecrets.getDetails().getClientId())).
                    build();
            GoogleIdToken idToken = verifier.verify(id_token);
            if (idToken != null) {
                GoogleIdToken.Payload payload = idToken.getPayload();
                
                String userId = payload.getSubject();
                String name = (String)payload.get("name");
                String email = payload.getEmail();
                
                User user = this.userLogic.getUser(userId);
                if (user == null) {
                    this.userLogic.register(userId, name, email);
                }
                
                // セッションIdを変更
                ExternalContext externalContext = getExternalContext();
                HttpServletRequest request = (HttpServletRequest)externalContext.getRequest();
                request.changeSessionId();
                
                externalContext.getSessionMap().put(Constants.USER_KEY, userId);
            } else {
                getExternalContext().getFlash().put(Constants.ErrTitle_Key, "ログインエラー");
                getExternalContext().getFlash().put(Constants.ErrMsg_Key, "ログインエラーが発生しました。");
                return "/error.xhtml?faces-redirect=true";
            }
            return "/user/home.xhtml?faces-redirect=true";
        } catch (Exception e) {
            return "/error.xhtml?faces-redirect=true";
        }
    }
    
    /**
     * ログアウト
     * @return 
     */
    public String logout() {
        try {
            // セッションを無効にする
            ExternalContext externalContext = getExternalContext();
            HttpSession session = (HttpSession)externalContext.getSession(false);
            if (session != null) {
                log.info(session.getAttribute(Constants.USER_KEY).toString());
                externalContext.invalidateSession();
            }
            return "/index.xhtml?faces-redirect=true";
        } catch (Exception e) {
            log.info(e.getMessage());
            return null;
        }
    }
    
     /**
     * ExternalContextを取得
     * @return ExternalContext
     */
    private ExternalContext getExternalContext() {
        return FacesContext.getCurrentInstance().getExternalContext();
    }

    public String getId_token() {
        return id_token;
    }

    public void setId_token(String id_token) {
        this.id_token = id_token;
    }
    
    public String getName() {
        String name = null;
        try {
            ExternalContext externalContext = getExternalContext();
            HttpSession session = (HttpSession)externalContext.getSession(false);
            if (session != null) {
               name = this.userLogic.getUserName(session.getAttribute(Constants.USER_KEY).toString());
            }
            return name;
        } catch (Exception e) {
            return null;
        }
    }
}