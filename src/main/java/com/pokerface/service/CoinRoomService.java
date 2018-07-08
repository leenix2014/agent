package com.pokerface.service;

import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.pokerface.model.Agent;
import com.pokerface.model.CoinRoom;
import com.pokerface.repos.CoinRoomRepository;
import com.pokerface.util.HttpUtil;

@Component
public class CoinRoomService {
    @PersistenceContext
    private EntityManager em;
    
    @Autowired
    private CoinRoomRepository repo;

    public List<CoinRoom> coinrooms(){
    	return repo.findAll(new Sort(Sort.Direction.DESC, "createTime"));
    }
    
    public CoinRoom coinroomDetail(Long id){
    	return repo.findOne(id);
    }
    
    public void copyRoom(Long id){
    	CoinRoom from =  repo.findOne(id);
    	CoinRoom copy = from.clone();
    	copy.setId(null);
    	Long roomId = (long) (10000 + new Random().nextInt(90000));
    	while(roomIdExists(roomId)){
    		roomId = (long) (10000 + new Random().nextInt(90000));
    	}
    	copy.setRoomId(roomId);
    	saveCoinRoom(copy);
    }
    
    public void saveCoinRoom(CoinRoom coinroom) {
    	repo.save(coinroom);
    	HttpUtil.sendGet(HttpUtil.getServerPrefix()+"/refresh?type=coinroom");
    }
    
    public boolean roomIdExists(Long roomId){
    	return roomIdExists(roomId, 0l);
    }
    public boolean roomIdExists(Long roomId, Long id){
    	Query query = em
                .createQuery("From CoinRoom cr WHERE cr.roomId = :roomId and cr.id != :id");
    	query.setParameter("roomId", roomId);
    	query.setParameter("id", id);
    	@SuppressWarnings("unchecked")
		List<Agent> list = query.getResultList();
    	return list!=null && list.size()>0;
    }
}
