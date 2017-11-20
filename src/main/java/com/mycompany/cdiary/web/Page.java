package com.mycompany.cdiary.web;

public class Page {
    /**
     * ViewId
     */
    private String viewId;
    /**
     * ページタイトル
     */
    private String title;
    /**
     * URL
     */
    private String url;
    
    public Page() {
        
    }
    
    /**
     * 初期化
     * @param viewId ViewId
     * @param title ページタイトル
     * @param url URL
     */
    public Page(String viewId, String title, String url) {
        this.viewId = viewId;
        this.title = title;
        this.url = url;
    }

    public String getViewId() {
        return viewId;
    }

    public void setViewId(String viewId) {
        this.viewId = viewId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
}
