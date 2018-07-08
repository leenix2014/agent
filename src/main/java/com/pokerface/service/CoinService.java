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

import com.pokerface.model.Agent;
import com.pokerface.model.CoinBill;
import com.pokerface.model.User;
import com.pokerface.model.Withdraw;
import com.pokerface.repos.CoinBillRepo;
import com.pokerface.repos.WithdrawRepository;

@Component
public class CoinService {
    @Autowired
    private WithdrawRepository repository;
    
    @Autowired
    private CoinBillRepo coinBillRepo;
    
    @PersistenceContext
    private EntityManager em;
    
    public List<Withdraw> getWithdraw(String loginId, String type){
    	StringBuffer sb = new StringBuffer();
    	sb.append("SELECT t ");
    	sb.append("From Withdraw t ");
    	sb.append("WHERE t.agentId = :loginId ");
    	if("agent".equals(type)){
    		sb.append("and t.isAgent = true ");
    	} else {
    		sb.append("and t.isAgent = false ");
    	}
    	sb.append("ORDER BY t.withdrawTime DESC");
    	
    	Query query = em.createQuery(sb.toString());
    	query.setParameter("loginId", loginId);
    	@SuppressWarnings("unchecked")
		List<Withdraw> list = query.getResultList();
    	return list;
    }
    
    public void withdraw(Agent agent, User withdrawer, Long amount){
    	long agentBefore = agent.getCoinCount();
    	long agentAfter = agentBefore + amount;
    	agent.setCoinCount(agentAfter);
    	long beforeBal = withdrawer.getCoinCount();
    	long afterBal = beforeBal - amount;
    	Date now = new Date();
    	withdrawer.setCoinCount(afterBal);
    	Withdraw record = new Withdraw();
    	record.setAdmin(agent.isAdmin());
    	record.setAmount(amount);
    	record.setAgentBalance(agentAfter);
    	record.setAgentId(agent.getLoginId());
    	record.setAgentName(agent.getName());
    	record.setUserBalance(afterBal);
    	record.setUserId(withdrawer.getLoginId());
    	record.setUserName(withdrawer.getName());
    	record.setWithdrawTime(now);
    	
    	CoinBill withdrawBill = new CoinBill();    	
    	withdrawBill.setUserId(withdrawer.getLoginId());
    	withdrawBill.setUserName(withdrawer.getName());
    	withdrawBill.setSource("withdraw");
    	withdrawBill.setSourceId(agent.getLoginId());
    	withdrawBill.setSourceName(agent.getName());
    	withdrawBill.setAmount(-amount);
    	withdrawBill.setBeforeBal(beforeBal);
    	withdrawBill.setAfterBal(afterBal);
    	withdrawBill.setCreateTime(now);
    	
    	EntityManagerFactory entityManagerFactory = em.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory
                .createEntityManager();
        StringBuffer sb = new StringBuffer();
        sb.append("Update Agent agent set agent.coinCount = :afterCount");
    	sb.append(" where agent.id = :agentId");
    	Query fromQuery = entityManager.createQuery(sb.toString());
    	fromQuery.setParameter("afterCount", agentAfter);
    	fromQuery.setParameter("agentId", agent.getId());
    	
    	sb = new StringBuffer("Update User user set user.coinCount = :toCount");
    	sb.append(" where user.id = :toId");
    	Query toQuery = entityManager.createQuery(sb.toString());
    	toQuery.setParameter("toCount", afterBal);
    	toQuery.setParameter("toId", withdrawer.getId());
        EntityTransaction tx = entityManager.getTransaction();
    	tx.begin();
    	repository.save(record);
    	coinBillRepo.save(withdrawBill);
    	CoinBill fromBill = new CoinBill();
    	fromBill.setUserId(agent.getLoginId());
    	fromBill.setUserName(agent.getName());
    	fromBill.setSource("withdraw");
    	fromBill.setSourceId(withdrawer.getLoginId());
    	fromBill.setSourceName(withdrawer.getName());
    	fromBill.setAmount(amount);
    	fromBill.setBeforeBal(agentBefore);
    	fromBill.setAfterBal(agentAfter);
    	fromBill.setCreateTime(now);
    	coinBillRepo.save(fromBill);
    	fromQuery.executeUpdate();
    	toQuery.executeUpdate();
    	tx.commit();
    }
    
    public void withdrawAgent(Agent agent, Agent withdrawer, Long amount){
    	long agentBefore = agent.getCoinCount();
    	long agentAfter = agentBefore + amount;
    	agent.setCoinCount(agentAfter);
    	long beforeBal = withdrawer.getCoinCount();
    	long afterBal = beforeBal - amount;
    	Date now = new Date();
    	withdrawer.setCoinCount(afterBal);
    	Withdraw record = new Withdraw();
    	record.setAdmin(agent.isAdmin());
    	record.setAmount(amount);
    	record.setAgentBalance(agentAfter);
    	record.setAgentId(agent.getLoginId());
    	record.setAgentName(agent.getName());
    	record.setUserBalance(afterBal);
    	record.setUserId(withdrawer.getLoginId());
    	record.setUserName(withdrawer.getName());
    	record.setAgent(true);
    	record.setWithdrawTime(now);
    	
    	CoinBill withdrawBill = new CoinBill();    	
    	withdrawBill.setUserId(withdrawer.getLoginId());
    	withdrawBill.setUserName(withdrawer.getName());
    	withdrawBill.setSource("withdraw");
    	withdrawBill.setSourceId(agent.getLoginId());
    	withdrawBill.setSourceName(agent.getName());
    	withdrawBill.setAmount(-amount);
    	withdrawBill.setBeforeBal(beforeBal);
    	withdrawBill.setAfterBal(afterBal);
    	withdrawBill.setCreateTime(now);
    	
    	EntityManagerFactory entityManagerFactory = em.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory
                .createEntityManager();
        StringBuffer sb = new StringBuffer();
        sb.append("Update Agent agent set agent.coinCount = :afterCount");
    	sb.append(" where agent.id = :agentId");
    	Query fromQuery = entityManager.createQuery(sb.toString());
    	fromQuery.setParameter("afterCount", agentAfter);
    	fromQuery.setParameter("agentId", agent.getId());
    	
    	sb = new StringBuffer("Update Agent user set user.coinCount = :toCount");
    	sb.append(" where user.id = :toId");
    	Query toQuery = entityManager.createQuery(sb.toString());
    	toQuery.setParameter("toCount", afterBal);
    	toQuery.setParameter("toId", withdrawer.getId());
        EntityTransaction tx = entityManager.getTransaction();
    	tx.begin();
    	repository.save(record);
    	coinBillRepo.save(withdrawBill);
    	CoinBill fromBill = new CoinBill();
    	fromBill.setUserId(agent.getLoginId());
    	fromBill.setUserName(agent.getName());
    	fromBill.setSource("withdraw");
    	fromBill.setSourceId(withdrawer.getLoginId());
    	fromBill.setSourceName(withdrawer.getName());
    	fromBill.setAmount(amount);
    	fromBill.setBeforeBal(agentBefore);
    	fromBill.setAfterBal(agentAfter);
    	fromBill.setCreateTime(now);
    	coinBillRepo.save(fromBill);
    	fromQuery.executeUpdate();
    	toQuery.executeUpdate();
    	tx.commit();
    }
    
}
