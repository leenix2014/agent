package com.pokerface.service;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pokerface.dto.IEntity;
import com.pokerface.dto.TopupDto;
import com.pokerface.model.Agent;
import com.pokerface.model.RoomCardBill;
import com.pokerface.model.Topup;
import com.pokerface.model.User;
import com.pokerface.repos.RoomCardBillRepo;
import com.pokerface.repos.TopupRepository;

@Component
public class TopupService {
    @Autowired
    private TopupRepository repository;
    
    @Autowired
    private RoomCardBillRepo rmCdBillRepo;
    
    @PersistenceContext
    private EntityManager em;

    public void topupAgent(Agent fromAgent, Agent toAgent, Long amount){
    	fromAgent.setRoomCardCount(fromAgent.getRoomCardCount() - amount);
    	toAgent.setRoomCardCount(toAgent.getRoomCardCount() + amount);
    	Topup record = new Topup();
    	record.setAdmin(fromAgent.isAdmin());
    	record.setAmount(amount);
    	record.setFromAgent(true);
    	record.setFromBalance(fromAgent.getRoomCardCount());
    	record.setFromId(fromAgent.getLoginId());
    	record.setFromName(fromAgent.getName());
    	record.setToAgent(true);
    	record.setToBalance(toAgent.getRoomCardCount());
    	record.setToId(toAgent.getLoginId());
    	record.setToName(toAgent.getName());
    	record.setTopupTime(new Date());
    	EntityManagerFactory entityManagerFactory = em.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory
                .createEntityManager();
        StringBuffer sb = new StringBuffer("Update Agent agent set agent.roomCardCount = :fromCount");
    	sb.append(" where agent.id = :fromId");
    	Query fromQuery = entityManager.createQuery(sb.toString());
    	fromQuery.setParameter("fromCount", fromAgent.getRoomCardCount());
    	fromQuery.setParameter("fromId", fromAgent.getId());
    	
    	sb = new StringBuffer("Update Agent agent set agent.roomCardCount = :toCount");
    	sb.append(" where agent.id = :toId");
    	Query toQuery = entityManager.createQuery(sb.toString());
    	toQuery.setParameter("toCount", toAgent.getRoomCardCount());
    	toQuery.setParameter("toId", toAgent.getId());
        EntityTransaction tx = entityManager.getTransaction();
    	tx.begin();
    	repository.save(record);
    	fromQuery.executeUpdate();
    	toQuery.executeUpdate();
    	tx.commit();
    }
    
    public List<TopupDto> getTopup(String loginId, boolean isAgent){
    	StringBuffer sb = new StringBuffer();
    	sb.append("SELECT new com.pokerface.dto.TopupDto(CASE WHEN t.fromId = :loginId THEN 'OUT' ELSE 'IN' END as transferType, t)");
    	sb.append("From Topup t ");
    	sb.append("WHERE (t.fromId = :loginId AND t.isFromAgent = :isAgent) OR (t.toId = :loginId AND t.toAgent = :isAgent) ");
    	sb.append("ORDER BY t.topupTime DESC");
    	
    	Query query = em.createQuery(sb.toString());
    	query.setParameter("loginId", loginId);
    	query.setParameter("isAgent", isAgent);
    	@SuppressWarnings("unchecked")
		List<TopupDto> list = query.getResultList();
    	return list;
    }
    
    public void topupUser(IEntity from, User toUser, Long amount){
    	boolean isFromAgent = from instanceof Agent?true:false;
    	long fromBefore = from.getRoomCardCount();
    	long fromAfter = fromBefore - amount;
    	from.setRoomCardCount(fromAfter);
    	long beforeBal = toUser.getRoomCardCount();
    	long afterBal = beforeBal + amount;
    	Date now = new Date();
    	toUser.setRoomCardCount(afterBal);
    	Topup record = new Topup();
    	record.setAdmin(from.isAdmin());
    	record.setAmount(amount);
    	record.setFromAgent(isFromAgent);
    	record.setFromBalance(from.getRoomCardCount());
    	record.setFromId(from.getLoginId());
    	record.setFromName(from.getName());
    	record.setToAgent(false);
    	record.setToBalance(afterBal);
    	record.setToId(toUser.getLoginId());
    	record.setToName(toUser.getName());
    	record.setTopupTime(now);
    	
    	RoomCardBill toBill = new RoomCardBill();
    	toBill.setUserId(toUser.getLoginId());
    	toBill.setUserName(toUser.getName());
    	toBill.setSource("topup");
    	toBill.setSourceId(from.getLoginId());
    	toBill.setSourceName(from.getName());
    	toBill.setAmount(amount);
    	toBill.setBeforeBal(beforeBal);
    	toBill.setAfterBal(afterBal);
    	toBill.setCreateTime(now);
    	
    	EntityManagerFactory entityManagerFactory = em.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory
                .createEntityManager();
        StringBuffer sb = new StringBuffer();
        if(isFromAgent){
        	sb.append("Update Agent out set out.roomCardCount = :fromCount");
        } else {
        	sb.append("Update User out set out.roomCardCount = :fromCount");
        }
    	sb.append(" where out.id = :fromId");
    	Query fromQuery = entityManager.createQuery(sb.toString());
    	fromQuery.setParameter("fromCount", from.getRoomCardCount());
    	fromQuery.setParameter("fromId", from.getId());
    	
    	sb = new StringBuffer("Update User user set user.roomCardCount = :toCount");
    	sb.append(" where user.id = :toId");
    	Query toQuery = entityManager.createQuery(sb.toString());
    	toQuery.setParameter("toCount", afterBal);
    	toQuery.setParameter("toId", toUser.getId());
        EntityTransaction tx = entityManager.getTransaction();
    	tx.begin();
    	repository.save(record);
    	rmCdBillRepo.save(toBill);
    	if(!isFromAgent){
    		RoomCardBill fromBill = new RoomCardBill();
    		fromBill.setUserId(from.getLoginId());
    		fromBill.setUserName(from.getName());
    		fromBill.setSource("transfer");
    		fromBill.setSourceId(toUser.getLoginId());
    		fromBill.setSourceName(toUser.getName());
    		fromBill.setAmount(-amount);
    		fromBill.setBeforeBal(fromBefore);
    		fromBill.setAfterBal(fromAfter);
    		fromBill.setCreateTime(now);
    		rmCdBillRepo.save(fromBill);
    	}
    	fromQuery.executeUpdate();
    	toQuery.executeUpdate();
    	tx.commit();
    }
    
}
