package com.pokerface.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.pokerface.model.RoomCardBill;
import com.pokerface.model.User;

@Component
public class UserService {
    @PersistenceContext
    private EntityManager em;

    public List<RoomCardBill> findRoomCardBill(String loginId) {
    	Query query = em
                .createQuery("From RoomCardBill bill WHERE bill.userId = :loginId order by bill.createTime desc");
    	query.setParameter("loginId", loginId);
    	@SuppressWarnings("unchecked")
		List<RoomCardBill> list = query.getResultList();
    	return list;
    }
    
    public User findUser(String loginId){
    	Query query = em
                .createQuery("From User user WHERE user.loginId = :loginId");
    	query.setParameter("loginId", loginId);
    	@SuppressWarnings("unchecked")
		List<User> list = query.getResultList();
    	if(list == null || list.size() == 0){
    		return null;
    	} else {
    		return list.get(0);
    	}
    }
    
    public User findTopupUser(String loginId){
    	Query query = em
                .createQuery("From User user WHERE user.loginId = :loginId");
    	query.setParameter("loginId", loginId);
    	@SuppressWarnings("unchecked")
		List<User> list = query.getResultList();
    	if(list == null || list.size() == 0){
    		return null;
    	} else {
    		return list.get(0);
    	}
    }

    public void changePwd(Long id, String newPwd){
    	EntityManagerFactory entityManagerFactory = em.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
    	StringBuffer sb = new StringBuffer("Update User user set user.pwd = :newPwd where user.id = :id");
    	Query query = entityManager.createQuery(sb.toString());
    	query.setParameter("newPwd", newPwd);
    	query.setParameter("id", id);
    	EntityTransaction tx = entityManager.getTransaction();
    	tx.begin();
    	query.executeUpdate();
    	tx.commit();
    }
}
