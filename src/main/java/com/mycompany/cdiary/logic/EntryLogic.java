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
