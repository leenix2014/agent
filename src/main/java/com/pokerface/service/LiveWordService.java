package com.pokerface.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.pokerface.model.LiveWord;
import com.pokerface.repos.LiveWordRepository;
import com.pokerface.util.HttpUtil;

@Component
public class LiveWordService {
    @PersistenceContext
    private EntityManager em;
    
    @Autowired
    private LiveWordRepository repo;
    
    public List<LiveWord> livewords(){
    	return repo.findAll(new Sort(Sort.Direction.DESC, "weight"));
    }
    
    public LiveWord liveWordsDetail(Long id){
    	return repo.findOne(id);
    }
    
    public void removeLiveWord(Long id) {
    	EntityManagerFactory entityManagerFactory = em.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
    	StringBuffer sb = new StringBuffer("delete from LiveWord where id = :id");
    	Query query = entityManager.createQuery(sb.toString());
    	query.setParameter("id", id);
    	EntityTransaction tx = entityManager.getTransaction();
    	tx.begin();
    	query.executeUpdate();
    	tx.commit();
    	HttpUtil.sendGet(HttpUtil.getServerPrefix()+"/refresh?type=liveword");
    }
   
    public void saveLiveWord(LiveWord liveword) {
    	repo.save(liveword);
    	HttpUtil.sendGet(HttpUtil.getServerPrefix()+"/refresh?type=liveword");
    }
}
