package com.mycompany.cdiary.constants;

import com.mycompany.cdiary.util.Messages;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javax.faces.model.SelectItem;

public class SelectOneMenuItems {
    public static final List<SelectItem> c1items = 
            Collections.unmodifiableList(new LinkedList<SelectItem>() {
                {
                    add(new SelectItem(1, Messages.getString(Constants.RES, "c1select1", null)));
                    add(new SelectItem(2, Messages.getString(Constants.RES, "c1select2", null)));
                    add(new SelectItem(3, Messages.getString(Constants.RES, "c1select3", null)));
                    add(new SelectItem(4, Messages.getString(Constants.RES, "c1select4", null)));
                }
            });
    
    public static final List<SelectItem> c2items = 
            Collections.unmodifiableList(new LinkedList<SelectItem>() {
                {
                    add(new SelectItem(1, Messages.getString(Constants.RES, "c2select1", null)));
                    add(new SelectItem(2, Messages.getString(Constants.RES, "c2select2", null)));
                    add(new SelectItem(3, Messages.getString(Constants.RES, "c2select3", null))); 
                }
            });
    
    public static final List<SelectItem> c3items = 
            Collections.unmodifiableList(new LinkedList<SelectItem>() {
                {
                    add(new SelectItem(1, Messages.getString(Constants.RES, "c3select1", null)));
                    add(new SelectItem(2, Messages.getString(Constants.RES, "c3select2", null)));
                    add(new SelectItem(3, Messages.getString(Constants.RES, "c3select3", null))); 
                    add(new SelectItem(3, Messages.getString(Constants.RES, "c3select4", null)));
                    add(new SelectItem(3, Messages.getString(Constants.RES, "c3select5", null))); 
                }
            });
}
