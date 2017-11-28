/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cdiary.dao;

import com.mycompany.cdiary.entity.Entry;
import com.mycompany.cdiary.entity.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author K
 */
@Stateless
public class EntryFacade extends AbstractFacade<Entry> {
    @PersistenceContext(unitName = "com.mycompany_cdiary_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EntryFacade() {
        super(Entry.class);
    }
    
    /**
     * 検索
     * @param userID ユーザーID
     * @param startDate 開始日
     * @param endDate 終了日
     * @param c1 選択肢1
     * @param c2 選択肢2
     * @param c3 選択肢3
     * @param rating 評価
     * @param shop 店
     * @param note コメント
     * @return 検索結果
     */
    public List<Entry> search(User userID, Date startDate, Date endDate, int c1, int c2, int c3, int rating, String shop, String note) {
        StringBuilder sb = new StringBuilder("SELECT e FROM Entry e WHERE e.userID = :userID AND e.date BETWEEN :startDate and :endDate");
        if (c1 > 0) {
            sb.append(" AND e.c1 = :c1");
        }
        if (c2 > 0) {
            sb.append(" AND e.c2 = :c2");
        }
        if (c3 > 0) {
            sb.append(" AND e.c3 = :c3");
        }
        sb.append(" AND e.rating >= :rating");
        if (shop != null && !shop.isEmpty()) {
            sb.append(" AND e.shop LIKE :shop");
        }
        if (note != null && !note.isEmpty()) {
            sb.append(" AND e.note LIKE :note");
        }
        sb.append(" ORDER BY e.date");
        
        try {
            TypedQuery<Entry> query = this.em.createQuery(sb.toString(), Entry.class);
            query.setParameter("userID", userID);
            query.setParameter("startDate", startDate);
            query.setParameter("endDate", endDate);
            if (c1 > 0) {
                query.setParameter("c1", c1);
            }
            if (c2 > 0) {
                query.setParameter("c2", c2);
            }
            if (c3 > 0) {
                query.setParameter("c3", c3);
            }
            query.setParameter("rating", rating);
            if (shop != null && !shop.isEmpty()) {
                query.setParameter("shop", shop);
            }
            if (note != null && !note.isEmpty()) {
                query.setParameter("note", note);
            }
            List<Entry> result = query.getResultList();
            return result;
        } catch (Exception e) {
            
            return new ArrayList<Entry>();
        }
    }
}
