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
import com.pokerface.model.Baccarat;
import com.pokerface.repos.BaccaratRepository;
import com.pokerface.util.HttpUtil;

@Component
public class BaccaratService {
    @PersistenceContext
    private EntityManager em;
    
    @Autowired
    //private RouletteRepository repo;
    private BaccaratRepository repo;

    public List<Baccarat> baccarats(){
    	Query query = em.createQuery("SELECT sub FROM Baccarat sub WHERE sub.deleted = false order by sub.createTime desc");
		@SuppressWarnings("unchecked")
		List<Baccarat> list = query.getResultList();
    	return list;
    }
    
    public Baccarat baccaratDetail(Long id){
    	return repo.findOne(id);
    }
    
    public void removeBaccaratRoom(Long id) {
    	EntityManagerFactory entityManagerFactory = em.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
    	StringBuffer sb = new StringBuffer("Update Baccarat bacc set bacc.deleted = true, bacc.deletedTime = :now where bacc.id = :id");
    	Query query = entityManager.createQuery(sb.toString());
    	query.setParameter("now", new Date());
    	query.setParameter("id", id);
    	EntityTransaction tx = entityManager.getTransaction();
    	tx.begin();
    	query.executeUpdate();
    	tx.commit();
    	HttpUtil.sendGet(HttpUtil.getServerPrefix()+"/refresh?type=baccarat");
    }
    
    public void copyRoom(Long id){
//    	Roulette from =  repo.findOne(id);
//    	Roulette copy = from.clone();
//    	copy.setId(null);
//    	Long roomId = (long) (10000 + new Random().nextInt(90000));
//    	while(roomIdExists(roomId)){
//    		roomId = (long) (10000 + new Random().nextInt(90000));
//    	}
//    	copy.setRoomId(roomId);
//    	saveRoulette(copy);
    }
    
    public void saveBaccarat(Baccarat baccarat) {
    	repo.save(baccarat);
    	HttpUtil.sendGet(HttpUtil.getServerPrefix()+"/refresh?type=baccarat");
    }
    
    public boolean roomIdExists(String roomId, Long id){
    	Query query = em
                .createQuery("From Baccarat WHERE roomId = :roomId and id != :id");
    	query.setParameter("roomId", roomId);
    	query.setParameter("id", id);
    	@SuppressWarnings("unchecked")
		List<Agent> list = query.getResultList();
    	return list!=null && list.size()>0;
    }
    
    public boolean channelExists(String channel, Long id){
    	Query query = em
                .createQuery("From Baccarat WHERE channel = :channel and id != :id");
    	query.setParameter("channel", channel);
    	query.setParameter("id", id);
    	@SuppressWarnings("unchecked")
		List<Agent> list = query.getResultList();
    	return list!=null && list.size()>0;
    }
}
