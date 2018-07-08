package com.pokerface.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.pokerface.dto.AnchorSumDto;
import com.pokerface.model.AnchorBill;
import com.pokerface.repos.AnchorBillRepository;

@Component
public class AnchorBillService {
    @PersistenceContext
    private EntityManager em;
    
//    @Autowired
//    private AnchorMacRepository macRepo;
    
    @Autowired
    private AnchorBillRepository anchorBillRepo;
    
//    @Autowired
//    private LiveRoomRepository liveRoomRepo;

    public boolean existsAnchorBill(Long anchorBillId){
    	return getAnchorBill(anchorBillId) != null;
    }
    
    public AnchorBill getAnchorBill(Long anchorBillId){
    	Query query = em
                .createQuery("From AnchorBill anc WHERE anc.anchorBillId = :anchorBillId");
    	query.setParameter("anchorBillId", anchorBillId);
    	@SuppressWarnings("unchecked")
		List<AnchorBill> list = query.getResultList();
    	if(list == null || list.isEmpty()){
    		return null;
    	} else {
    		return list.get(0);
    	}
    }
    
//    public void updateMachine(Long anchorId, String machine_MAC, String is_online, String supplies, String is_binding, String status){
//    	Query query = em
//                .createQuery("From AnchorMachine mac WHERE mac.anchorId = :anchorId and mac.machineMac = :machineMac");
//    	query.setParameter("anchorId", anchorId);
//    	query.setParameter("machineMac", machine_MAC);
//    	@SuppressWarnings("unchecked")
//		List<AnchorMachine> list = query.getResultList();
//    	Date now = new Date();
//    	if(list == null || list.isEmpty()){
//    		AnchorMachine mac = new AnchorMachine();
//    		mac.setAnchorId(anchorId);
//    		mac.setCreateTime(now);
//    		mac.setUpdateTime(now);
//    		mac.setIsBinding(is_binding);
//    		mac.setIsOnline(is_online);
//    		mac.setMachineMac(machine_MAC);
//    		mac.setStatus(status);
//    		mac.setSupplies(supplies);
//    		macRepo.save(mac);
//    	} else {
//    		AnchorMachine mac = list.get(0);
//    		EntityManagerFactory entityManagerFactory = em.getEntityManagerFactory();
//            EntityManager entityManager = entityManagerFactory.createEntityManager();
//        	StringBuffer sb = new StringBuffer("Update AnchorMachine mac set mac.isOnline = :isOnline,")
//        			.append(" mac.supplies = :supplies,");
//        	if(!StringUtil.isEmpty(is_binding)){
//        		sb.append(" mac.isBinding = :isBinding,");
//        	}
//        	sb.append(" mac.status = :status,");
//        	sb.append(" mac.updateTime = :updateTime");
//        	sb.append(" where mac.id = :id");
//        	Query updater = entityManager.createQuery(sb.toString());
//        	updater.setParameter("isOnline", is_online);
//        	updater.setParameter("supplies", supplies);
//        	if(!StringUtil.isEmpty(is_binding)){
//        		updater.setParameter("isBinding", is_binding);
//        	}
//        	updater.setParameter("status", status);
//        	updater.setParameter("updateTime", now);
//        	updater.setParameter("id", mac.getId());
//        	EntityTransaction tx = entityManager.getTransaction();
//        	tx.begin();
//        	updater.executeUpdate();
//        	tx.commit();
//    	}
//    }
    
    public List<AnchorBill> anchorBills(Long anchorId){
//    	return anchorBillRepo.findAll(new Sort(Sort.Direction.DESC, "recordTime"));
    	Query query = em.createQuery("from AnchorBill where anchorId = :anchorId");
    	query.setParameter("anchorId", anchorId);
    	List<AnchorBill> list = query.getResultList();
    	return list;
    }
    
    public List<AnchorSumDto> anchorSumBills(){
    	Query query = em.createQuery("select new com.pokerface.dto.AnchorSumDto(t.anchorId, SUM(t.amount), t.anchorName, "
    			+ "t.anchorType, t.roomId, t.channel) from AnchorBill t group by t.anchorId");
		List<AnchorSumDto> list = query.getResultList();
    	return list;
    }
    
    public AnchorBill anchorBillDetail(Long id){
    	return anchorBillRepo.findOne(id);
    }
    
    public void saveAnchorBill(AnchorBill anchorBill) {
    	anchorBillRepo.save(anchorBill);
    }
    
//    public boolean isLiving(Long anchorBillId){
//    	Query query = em
//                .createQuery("From LiveRoom live WHERE live.anchorBillId = :anchorBillId and live.status = 0");
//    	query.setParameter("anchorBillId", anchorBillId);
//    	@SuppressWarnings("unchecked")
//		List<LiveRoom> list = query.getResultList();
//    	boolean living = list!=null && list.size()>0;
//    	if(living){
//    		LiveRoom room = list.get(0);
//    		HttpUtil.sendGet(HttpUtil.getServerPrefix()+"/anchorBill?type=start&roomId="+room.getId());
//    	}
//    	return living;
//    }
    
//    public void anchorBillStart(Long anchorId){
//    	AnchorBill anchor = getAnchorBill(anchorId);
//    	LiveRoom live = new LiveRoom();
////    	live.setChannel(anchor.getChannel());
//    	live.setAnchorId(anchor.getAnchorId());
//    	live.setAnchorName(anchor.getAnchorName());
//    	live.setAnchorType(anchor.getAnchorType());
////    	live.setTitle(anchor.getTitle());
////    	live.setCost(anchor.getCost());
//    	live.setStatus(0);
//    	live.setOnlineCount(0);
//    	live.setCreateTime(new Date());
//    	live.setEndTime(new Date(0));
//    	LiveRoom saved = liveRoomRepo.save(live);
//    	
//    	HttpUtil.sendGet(HttpUtil.getServerPrefix()+"/anchor?type=start&roomId="+saved.getId());
//    }
    
//    public void anchorBillStop(Long anchorId){
//    	Query query = em
//                .createQuery("From LiveRoom live WHERE live.anchorId = :anchorId and live.status = 0");
//    	query.setParameter("anchorId", anchorId);
//    	@SuppressWarnings("unchecked")
//		List<LiveRoom> list = query.getResultList();
//    	if(list!=null && list.size()>0){
//    		Date now = new Date();
//    		for(LiveRoom room : list){
//    			room.setStatus(1);
//    			room.setEndTime(now);
//    			liveRoomRepo.save(room);
//    			HttpUtil.sendGet(HttpUtil.getServerPrefix()+"/anchor?type=stop&roomId="+room.getId());
//    		}
//    	}
//    }
}
