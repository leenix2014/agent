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

import com.pokerface.model.LiveName;
import com.pokerface.repos.LiveNameRepository;
import com.pokerface.util.HttpUtil;

@Component
public class LiveNameService {
	@PersistenceContext
    private EntityManager em;
    
    @Autowired
    private LiveNameRepository liveNameRepo;
    
    @Autowired
    private LiveNameRepository repo;
    
    public List<LiveName> livename(){
    	return liveNameRepo.findAll(new Sort(Sort.Direction.DESC, "name"));
    }
    
    public LiveName liveNameDetail(Long id){
    	return repo.findOne(id);
    }
    
    public void removeLiveName(Long id) {
    	EntityManagerFactory entityManagerFactory = em.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
    	StringBuffer sb = new StringBuffer("delete from LiveName where id = :id");
    	Query query = entityManager.createQuery(sb.toString());
    	query.setParameter("id", id);
    	EntityTransaction tx = entityManager.getTransaction();
    	tx.begin();
    	query.executeUpdate();
    	tx.commit();
    	HttpUtil.sendGet(HttpUtil.getServerPrefix()+"/refresh?type=livename");
    }
   
    public void saveLiveName(LiveName livename) {
    	liveNameRepo.save(livename);
    	HttpUtil.sendGet(HttpUtil.getServerPrefix()+"/refresh?type=livename");
    }
}
