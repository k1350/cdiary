/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cdiary.dao;

import com.mycompany.cdiary.entity.C2;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author K
 */
@Stateless
public class C2Facade extends AbstractFacade<C2> {
    @PersistenceContext(unitName = "com.mycompany_cdiary_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public C2Facade() {
        super(C2.class);
    }
    
}
