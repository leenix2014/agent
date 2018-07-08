package com.pokerface.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.pokerface.model.RoomConfig;
import com.pokerface.model.RoomInning;

@Component
public class RoomService {
	@PersistenceContext
    private EntityManager em;
    
    public List<RoomConfig> findAllRoom(){
    	Query query = em
                .createQuery("From RoomConfig room order by room.status");
    	@SuppressWarnings("unchecked")
		List<RoomConfig> list = query.getResultList();
    	return list;
    }
    
    public List<RoomConfig> findRooms(long status){
    	Query query = em
                .createQuery("From RoomConfig room WHERE room.status = :status;");
    	query.setParameter("status", status);
    	@SuppressWarnings("unchecked")
		List<RoomConfig> list = query.getResultList();
    	return list;
    }
    
    public List<RoomInning> findRoomInnings(long roomId){
    	Query query = em
                .createQuery("From RoomInning t WHERE t.roomId = :roomId and t.inning != 0");
    	query.setParameter("roomId", roomId);
    	@SuppressWarnings("unchecked")
		List<RoomInning> list = query.getResultList();
    	return list;
    }
    
    public List<RoomInning> findRoomBill(long roomId){
    	Query query = em
                .createQuery("From RoomInning t WHERE t.roomId = :roomId and t.inning = 0 and t.status != 0");
    	query.setParameter("roomId", roomId);
    	@SuppressWarnings("unchecked")
		List<RoomInning> list = query.getResultList();
    	return list;
    }
}
