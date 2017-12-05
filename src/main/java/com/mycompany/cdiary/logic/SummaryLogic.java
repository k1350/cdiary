package com.mycompany.cdiary.logic;

import com.mycompany.cdiary.constants.SelectOneMenuItems;
import com.mycompany.cdiary.dao.EntryFacade;
import com.mycompany.cdiary.dao.UserFacade;
import com.mycompany.cdiary.entity.Entry;
import com.mycompany.cdiary.entity.User;
import com.mycompany.cdiary.util.Messages;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.model.SelectItem;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

@Stateless
public class SummaryLogic {
    @EJB
    private EntryFacade entryDao;
    
    @EJB
    private UserFacade userDao;
    
    @EJB
    private SelectOneMenuItems items;
    
    /**
     * 選択肢1のグラフデータ
     * @param userId ユーザーID
     * @param startDate 開始日
     * @param endDate 終了日
     * @return 選択肢1のグラフデータJson
     */
    public JsonArray getSelect1(String userId, Date startDate, Date endDate) {
        User user = this.userDao.find(userId);
        if (user == null) {
            return JsonZero();
        }
        
        JsonArrayBuilder rows = Json.createArrayBuilder();
        
        List<SelectItem> ls = this.items.getC1items();
        int totalCount = 0;
        for (SelectItem item : ls) {
            int c = (int)item.getValue();

            int count = this.entryDao.searchC1(user, startDate, endDate, c).size();
            totalCount += count;
            JsonArrayBuilder r1 = Json.createArrayBuilder();
            r1.add(Json.createObjectBuilder()
                .add("v", item.getLabel())
                .add("f", item.getLabel()).build());
            r1.add(Json.createObjectBuilder()
                .add("v", count)
                .add("f", count).build());
            rows.add(Json.createObjectBuilder().add("c", r1.build()));
        }

        if (totalCount != 0) {
            return rows.build();
        }
        
        // データがゼロ件のとき、そのままではグラフが出ない
        return JsonZero();
    }
   
     /**
     * 選択肢2のグラフデータ
     * @param userId ユーザーID
     * @param startDate 開始日
     * @param endDate 終了日
     * @return 選択肢2のグラフデータJson
     */
    public JsonArray getSelect2(String userId, Date startDate, Date endDate) {
        User user = this.userDao.find(userId);
        if (user == null) {
            return JsonZero();
        }
        
        JsonArrayBuilder rows = Json.createArrayBuilder();
        
        List<SelectItem> ls = this.items.getC2items();
        int totalCount = 0;
        for (SelectItem item : ls) {
            int c = (int)item.getValue();
            int count = this.entryDao.searchC2(user, startDate, endDate, c).size();
            totalCount += count;
            JsonArrayBuilder r1 = Json.createArrayBuilder();
            r1.add(Json.createObjectBuilder()
                .add("v", item.getLabel())
                .add("f", item.getLabel()).build());
            r1.add(Json.createObjectBuilder()
                .add("v", count)
                .add("f", count).build());
            rows.add(Json.createObjectBuilder().add("c", r1.build()));
        }

        if (totalCount != 0) {
            return rows.build();
        }
        
        // データがゼロ件のとき、そのままではグラフが出ない
        return JsonZero();
    }
    
     /**
     * 選択肢3のグラフデータ
     * @param userId ユーザーID
     * @param startDate 開始日
     * @param endDate 終了日
     * @return 選択肢3のグラフデータJson
     */
    public JsonArray getSelect3(String userId, Date startDate, Date endDate) {
        User user = this.userDao.find(userId);
        if (user == null) {
            return JsonZero();
        }
        
        JsonArrayBuilder rows = Json.createArrayBuilder();
        
        List<SelectItem> ls = this.items.getC3items();
        int totalCount = 0;
        for (SelectItem item : ls) {
            int c = (int)item.getValue();
            int count = this.entryDao.searchC3(user, startDate, endDate, c).size();
            totalCount += count;
            JsonArrayBuilder r1 = Json.createArrayBuilder();
            r1.add(Json.createObjectBuilder()
                .add("v", item.getLabel())
                .add("f", item.getLabel()).build());
            r1.add(Json.createObjectBuilder()
                .add("v", count)
                .add("f", count).build());
            rows.add(Json.createObjectBuilder().add("c", r1.build()));
        }

        if (totalCount != 0) {
            return rows.build();
        }
        
        // データがゼロ件のとき、そのままではグラフが出ない
        return JsonZero();
    }
    
    
    private JsonArray JsonZero() {
        JsonArrayBuilder r0 = Json.createArrayBuilder();
        r0.add(Json.createObjectBuilder()
                .add("v", Messages.getString("myRes", "searchMessage", null))
                .add("f", Messages.getString("myRes", "searchMessage", null)).build());
        r0.add(Json.createObjectBuilder()
                .add("v", 1)
                .add("f", "").build());
        JsonArrayBuilder rowZero = Json.createArrayBuilder();
        rowZero.add(Json.createObjectBuilder().add("c", r0.build()));
        return rowZero.build();
    }

}
