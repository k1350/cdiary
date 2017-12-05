package com.mycompany.cdiary.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class NavBean implements Serializable {
    public List<Page> getPages() {
        List<Page> pages = new ArrayList<Page>();
        pages.add(new Page("/user/home.xhtml", "Home", "/user/home"));
        pages.add(new Page("/user/search.xhtml", "Search", "/user/search"));
        pages.add(new Page("/user/summary.xhtml", "Summary", "/user/summary"));
        pages.add(new Page("/user/setting.xhtml", "Setting", "/user/setting"));
        return pages;
    }
}
