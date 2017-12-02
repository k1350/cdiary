package com.mycompany.cdiary.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

@Named
@RequestScoped
public class SummaryBean {
    private JsonObject data1;
    
    @Inject
    transient Logger log;
    
    @PostConstruct
    public void init() {
        setData1();
    }
    
    public void setData1() {
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
   
        List<JsonArrayBuilder> r = new ArrayList<JsonArrayBuilder>();
        JsonArrayBuilder r1 = Json.createArrayBuilder();
        r1.add(Json.createObjectBuilder()
                .add("v", "Mushrooms")
                .add("f", "Mushrooms").build());
        r1.add(Json.createObjectBuilder()
                .add("v", 3)
                .add("f", "").build());
        r.add(r1);
        
        JsonArrayBuilder r2 = Json.createArrayBuilder();
        r2.add(Json.createObjectBuilder()
                .add("v", "Onions")
                .add("f", "Onions").build());
        r2.add(Json.createObjectBuilder()
                .add("v", 1)
                .add("f", "").build());
        r.add(r2);
        
        JsonArrayBuilder r3 = Json.createArrayBuilder();
        r3.add(Json.createObjectBuilder()
                .add("v", "Olives")
                .add("f", "Olives").build());
        r3.add(Json.createObjectBuilder()
                .add("v", 1)
                .add("f", "").build());
        r.add(r3);

        JsonArrayBuilder r4 = Json.createArrayBuilder();
        r4.add(Json.createObjectBuilder()
                .add("v", "Zucchini")
                .add("f", "Zucchini").build());
        r4.add(Json.createObjectBuilder()
                .add("v", 1)
                .add("f", "").build());
        r.add(r4);
        
        JsonArrayBuilder r5 = Json.createArrayBuilder();
        r5.add(Json.createObjectBuilder()
                .add("v", "Pepperoni")
                .add("f", "Pepperoni").build());
        r5.add(Json.createObjectBuilder()
                .add("v", 2)
                .add("f", "").build());
        r.add(r5);
        
        JsonArrayBuilder rows = Json.createArrayBuilder();
        for (JsonArrayBuilder ar : r) {
            rows.add(Json.createObjectBuilder().add("c", ar.build()));
        }

        this.data1 = Json.createObjectBuilder()
                .add("cols", cols.build())
                .add("rows", rows.build())
                .build();
    }
    
    public JsonObject getData1() {
        return this.data1;
    }
}
