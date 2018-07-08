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

import com.pokerface.dto.LiveGiftDto;
import com.pokerface.model.LiveGift;
import com.pokerface.repos.LiveGiftRepository;
import com.pokerface.util.HttpUtil;

@Component
public class LiveGiftService {
    @PersistenceContext
    private EntityManager em;
    
    @Autowired
    private LiveGiftRepository repo;
    
    public List<LiveGift> livegift(){
    	return repo.findAll(new Sort(Sort.Direction.DESC, "giftId"));
    }
    
    public List<LiveGift> validGifts(){
    	Query query = em
                .createQuery("From LiveGift WHERE isValid = true");
    	@SuppressWarnings("unchecked")
		List<LiveGift> list = query.getResultList();
    	return list;
    }
    
    public List<LiveGift> validGifts(Long giftId){
    	Query query = em
                .createQuery("From LiveGift WHERE isValid = true and giftId = :giftId");
    	query.setParameter("giftId", giftId);
    	@SuppressWarnings("unchecked")
		List<LiveGift> list = query.getResultList();
    	return list;
    }
    
    public LiveGift liveGiftDetail(Long id){
    	return repo.findOne(id);
    }
    
    public void removeLiveGiftPoss(Long id) {
    	EntityManagerFactory entityManagerFactory = em.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
    	StringBuffer sb = new StringBuffer("update LiveGift set giftPoss = '' where id = :id");
    	Query query = entityManager.createQuery(sb.toString());
    	query.setParameter("id", id);
    	EntityTransaction tx = entityManager.getTransaction();
    	tx.begin();
    	query.executeUpdate();
    	tx.commit();
    	HttpUtil.sendGet(HttpUtil.getServerPrefix()+"/refresh?type=livegift");
    }
    
    public void saveLiveGift(LiveGift gift) {
    	LiveGift entity = repo.findOne(gift.getId());
    	entity.setCost(gift.getCost());
    	entity.setPlayEffect(gift.getPlayEffect());
    	repo.save(entity);
    	HttpUtil.sendGet(HttpUtil.getServerPrefix()+"/refresh?type=livegift");
    }
   
    public void saveLivePossGift(LiveGiftDto livegift) {
    	LiveGift entity = repo.findOne(livegift.getId());
    	entity.setPoss(livegift);
    	repo.save(entity);
    	HttpUtil.sendGet(HttpUtil.getServerPrefix()+"/refresh?type=livegift");
    }
}
