/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cdiary.dao;

import com.mycompany.cdiary.entity.C1;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author K
 */
@Stateless
public class C1Facade extends AbstractFacade<C1> {
    @PersistenceContext(unitName = "com.mycompany_cdiary_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public C1Facade() {
        super(C1.class);
    }
    
}
