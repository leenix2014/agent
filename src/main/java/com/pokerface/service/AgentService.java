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
import com.pokerface.repos.AgentRepository;

@Component
public class AgentService {
    @Autowired
    private AgentRepository repository;
    @PersistenceContext
    private EntityManager em;
    
    public List<Agent> findSubAgents(Agent agent) {
		Query query = em.createQuery("SELECT sub FROM Agent sub WHERE sub.parentAgentId = :agentId and sub.deleted = false");
    	query.setParameter("agentId", agent.getLoginId());
    	@SuppressWarnings("unchecked")
		List<Agent> list = query.getResultList();
    	return  list;
    }

    public void saveAgent(Agent agent) {
        repository.save(agent);
    }

    public Agent findSingleAgent(Long id) {
        return repository.findOne(id);
    }
    
    public boolean loginIdExists(String loginId){
    	Query query = em
                .createQuery("From Agent agent WHERE agent.loginId = :loginId and agent.deleted = false");
    	query.setParameter("loginId", loginId);
    	@SuppressWarnings("unchecked")
		List<Agent> list = query.getResultList();
    	return list!=null && list.size()>0;
    }
    
    public boolean nameExists(String name){
    	Query query = em
                .createQuery("From Agent agent WHERE agent.name = :name and agent.deleted = false");
    	query.setParameter("name", name);
    	@SuppressWarnings("unchecked")
		List<Agent> list = query.getResultList();
    	return list!=null && list.size()>0;
    }
    
    public boolean phoneExists(String phone){
    	Query query = em
                .createQuery("From Agent agent WHERE agent.phone = :phone and agent.deleted = false");
    	query.setParameter("phone", phone);
    	@SuppressWarnings("unchecked")
		List<Agent> list = query.getResultList();
    	return list!=null && list.size()>0;
    }
    
    public Agent findAgent(String loginId){
    	Query query = em
                .createQuery("From Agent agent WHERE (agent.loginId = :loginId or agent.name = :loginId or agent.phone = :loginId) and agent.deleted = false");
    	query.setParameter("loginId", loginId);
    	@SuppressWarnings("unchecked")
		List<Agent> list = query.getResultList();
    	if(list == null || list.size() == 0){
    		return null;
    	} else {
    		return list.get(0);
    	}
    }

    public void removeAgent(Long id) {
    	EntityManagerFactory entityManagerFactory = em.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
    	StringBuffer sb = new StringBuffer("Update Agent agent set agent.deleted = true, agent.deleteTime = :now where agent.id = :id");
    	Query query = entityManager.createQuery(sb.toString());
    	query.setParameter("now", new Date());
    	query.setParameter("id", id);
    	EntityTransaction tx = entityManager.getTransaction();
    	tx.begin();
    	query.executeUpdate();
    	tx.commit();
    }
    
    public void changePwd(Long id, String newPwd){
    	EntityManagerFactory entityManagerFactory = em.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
    	StringBuffer sb = new StringBuffer("Update Agent agent set agent.pwd = :newPwd where agent.id = :id");
    	Query query = entityManager.createQuery(sb.toString());
    	query.setParameter("newPwd", newPwd);
    	query.setParameter("id", id);
    	EntityTransaction tx = entityManager.getTransaction();
    	tx.begin();
    	query.executeUpdate();
    	tx.commit();
    }

    public void updateAgent(Agent agent) {
    	//repository.saveAndFlush(agent);
    	EntityManagerFactory entityManagerFactory = em.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory
                .createEntityManager();
    	StringBuffer sb = new StringBuffer("Update Agent agent set agent.loginId = :loginId, agent.name = :name");
    	sb.append(", agent.phone = :phone");
    	sb.append(", agent.discount = :discount");
    	if(agent.getPwd()!=null){
    		sb.append(", agent.pwd = :pwd");
    	}
    	sb.append(" where agent.id = :id");
    	Query query = entityManager
                .createQuery(sb.toString());
    	query.setParameter("loginId", agent.getLoginId());
    	query.setParameter("name", agent.getName());
    	query.setParameter("phone", agent.getPhone());
    	query.setParameter("discount", agent.getDiscount());
    	if(agent.getPwd()!=null){
    		query.setParameter("pwd", agent.getPwd());
    	}
    	query.setParameter("id", agent.getId());
    	EntityTransaction tx = entityManager.getTransaction();
    	tx.begin();
    	int updateCount = query.executeUpdate();
    	if(updateCount != 1){
    		System.out.println("update count error! Update Count:" + updateCount + ", Hql:" + sb.toString());
    		tx.rollback();
    	} else {
    		tx.commit();
    	}
    }
}
