package com.mycompany.cdiary.logic;

import com.mycompany.cdiary.dao.EntryFacade;
import com.mycompany.cdiary.entity.Entry;
import com.mycompany.cdiary.entity.EntryPK;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class EntryLogic {
    @EJB
    private EntryFacade entryDao;
    
    /**
     * エントリーを登録
     * @param entry エントリー
     * @return エントリー
     */
    public Entry register(Entry entry) {
        this.entryDao.create(entry);
        return entry;
    }
    
    /**
     * エントリーを登録
     * @param userId ユーザーID
     * @param entryId エントリーID
     * @param c1 選択肢1
     * @param c2 選択肢2
     * @param c3 選択肢3
     * @param rating 評価
     * @param note ノート
     * @return エントリーを登録
     */
    public Entry register(String userId, long entryId, String c1, String c2, String c3, int rating, String note) {
        Entry entry = new Entry(entryId, userId);
        entry.setC1(c1);
        entry.setC2(c2);
        entry.setC3(c3);
        entry.setRating(rating);
        if (note != null) {
            entry.setNote(note);
        }
        this.entryDao.create(entry);
        return entry;
    }
    
    /**
     * エントリーを検索
     * @param userId ユーザーID
     * @param entryId エントリーID
     * @return エントリー
     */
    public Entry find(String userId, long entryId) {
        EntryPK pk = new EntryPK(entryId, userId);
        return this.entryDao.find(pk);
    }
}
