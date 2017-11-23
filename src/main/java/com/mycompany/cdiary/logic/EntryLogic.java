package com.mycompany.cdiary.logic;

import com.mycompany.cdiary.dao.EntryFacade;
import com.mycompany.cdiary.dao.UserFacade;
import com.mycompany.cdiary.entity.Entry;
import com.mycompany.cdiary.entity.User;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class EntryLogic {
    @EJB
    private EntryFacade entryDao;
    
    @EJB
    private UserFacade userDao;
    
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
     * @param id ID
     * @param userId ユーザーID
     * @param date 日付
     * @param c1 選択肢1
     * @param c2 選択肢2
     * @param c3 選択肢3
     * @param shop 店名
     * @param image 画像保存先パス
     * @param note コメント
     * @return 
     */
    public Entry register(long id, String userId, Date date, int c1, int c2, int c3, int rating, String shop, String image, String note) {
        Entry entry = new Entry();
        entry.setId(id);
        User user = this.userDao.find(userId);
        if (user == null) {
            return null;
        }
        entry.setUserID(user);
        entry.setDate(date);
        entry.setC1(c1);
        entry.setC2(c2);
        entry.setC3(c3);
        entry.setRating(rating);
        if (shop != null) {
            entry.setShop(shop);
        }
        if (image != null) {
            entry.setImage(image);
        }
        if (note != null) {
            entry.setNote(note);
        }
        
        return register(entry);
    }
    
    /**
     * エントリーを返す
     * @param id ID
     * @return エントリー
     */
    public Entry find(long id) {
        return this.entryDao.find(id);
    }

}
