package com.mycompany.cdiary.logic;

import com.mycompany.cdiary.dao.UserFacade;
import com.mycompany.cdiary.entity.User;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class UserLogic {
    @EJB
    private UserFacade userDao;
    
    /**
     * ユーザー登録
     * @param user ユーザー
     * @return ユーザー
     */
    public User register(User user) {
        this.userDao.create(user);
        return user;
    }
    
    /**
     * ユーザー登録
     * @param id ユーザーID
     * @param name 名前
     * @param mail メールアドレス
     * @return 登録したユーザー
     */
    public User register(String id, String name, String mail) {
        return register(new User(id, name, mail));
    }
    
    /**
     * ユーザーを更新
     * @param id ユーザーID
     * @param name 名前
     * @param mail メールアドレス
     * @return 登録したユーザー
     */
    public User update(String id, String name, String mail) {
        return this.update(new User(id, name, mail));
    }
    
    /**
     * ユーザーを更新
     * @param user ユーザー
     * @return 更新したユーザー
     */
    public User update(User user) {
        this.userDao.edit(user);
        return user;
    }
    
    /**
     * ユーザーを削除
     * @param user 削除するユーザー
     */
    public void delete(User user) {
        this.userDao.remove(user);
    }
    
    /**
     * ユーザーを取得
     * @param id ユーザーID
     * @return 取得したユーザー
     */
    public User getUser(String id) {
        return this.userDao.find(id);
    }
    
}
