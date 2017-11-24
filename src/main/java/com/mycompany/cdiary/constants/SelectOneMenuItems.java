package com.mycompany.cdiary.constants;

import com.mycompany.cdiary.dao.C1Facade;
import com.mycompany.cdiary.dao.C2Facade;
import com.mycompany.cdiary.dao.C3Facade;
import com.mycompany.cdiary.entity.C1;
import com.mycompany.cdiary.entity.C2;
import com.mycompany.cdiary.entity.C3;
import com.mycompany.cdiary.util.Messages;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.DependsOn;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.faces.model.SelectItem;

@Singleton
public class SelectOneMenuItems implements Serializable {
    @EJB
    private C1Facade c1dao;
    
    @EJB
    private C2Facade c2dao;
    
    @EJB
    private C3Facade c3dao;
    
    /**
     * 選択肢1のselectItem
     */
    private List c1items;
    /**
     * 選択肢1の選択肢一覧
     */
    private Map<Integer, String> c1map;
    
    /**
     * 選択肢2のselectItem
     */
    private List c2items;
    
    /**
     * 選択肢2の選択肢一覧
     */
    private Map<Integer, String> c2map;
   
    /**
     * 選択肢3のselectItem
     */
    private List c3items;
    
    /**
     * 選択肢3の選択肢一覧
     */
    private Map<Integer, String> c3map;
    
    @PostConstruct
    public void init() {
        setC1items();
        setC2items();
        setC3items();
    }

    /**
     * c1itemsを設定
     */
    public void setC1items() {
        List<C1> c1s = c1dao.findAll();
        LinkedHashMap c1m = new LinkedHashMap();
        for (C1 each : c1s) {
            c1m.put(each.getC1key(), Messages.getString(Constants.RES, each.getC1value(), null));
        }
        c1map = Collections.unmodifiableMap(c1m);
        
        LinkedList c1l = new LinkedList();
        Set<Integer> c1keys = c1m.keySet();
        for (Integer key : c1keys) {
            c1l.add(new SelectItem(key, c1m.get(key).toString(), null));
        }
        c1items = Collections.unmodifiableList(c1l);
    }
    
    /**
     * c2itemsを設定
     */
    public void setC2items() {
        List<C2> c2s = c2dao.findAll();
        LinkedHashMap c2m = new LinkedHashMap();
        for (C2 each : c2s) {
            c2m.put(each.getC2key(), Messages.getString(Constants.RES, each.getC2value(), null));
        }
        c2map = Collections.unmodifiableMap(c2m);
        
        LinkedList c2l = new LinkedList();
        Set<Integer> c2keys = c2m.keySet();
        for (Integer key : c2keys) {
            c2l.add(new SelectItem(key, c2m.get(key).toString(), null));
        }
        c2items = Collections.unmodifiableList(c2l);
    }
    
    /**
     * c3itemsを設定
     */
    public void setC3items() {
        List<C3> c3s = c3dao.findAll();
        LinkedHashMap c3m = new LinkedHashMap();
        for (C3 each : c3s) {
            c3m.put(each.getC3key(), Messages.getString(Constants.RES, each.getC3value(), null));
        }
        c3map = Collections.unmodifiableMap(c3m);
        
        LinkedList c3l = new LinkedList();
        Set<Integer> c3keys = c3m.keySet();
        for (Integer key : c3keys) {
            c3l.add(new SelectItem(key, c3m.get(key).toString(), null));
        }
        c3items = Collections.unmodifiableList(c3l);
    }
    
    public List getC1items() {
        return c1items;
    }
    
    public List getC2items() {
        return c2items;
    }
    
    public List getC3items() {
        return c3items;
    }

    public String getC1item(int id) {
        if (this.c1map.containsKey(id)) {
            return this.c1map.get(id);
        }
        return "";
    }
    
    public String getC2item(int id) {
        if (this.c2map.containsKey(id)) {
            return this.c2map.get(id);
        }
        return "";
    }

    public String getC3item(int id) {
        if (this.c3map.containsKey(id)) {
            return this.c3map.get(id);
        }
        return "";
    }
    
    
}
