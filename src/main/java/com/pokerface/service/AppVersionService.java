package com.pokerface.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pokerface.model.AppVersion;
import com.pokerface.repos.AppVersionRepository;
import com.pokerface.util.StringUtil;

@Component
public class AppVersionService {
    @PersistenceContext
    private EntityManager em;
    
    @Autowired
    private AppVersionRepository repo;

    public List<AppVersion> appVersions(){
    	Query query = em.createQuery("SELECT ver FROM AppVersion ver WHERE ver.latest = true");
		@SuppressWarnings("unchecked")
		List<AppVersion> list = query.getResultList();
    	return list;
    }
    
    public AppVersion appVersionDetail(Long id){
    	return repo.findOne(id);
    }
    
    public void increaseAppVer(Long id){
    	AppVersion from =  repo.findOne(id);
    	AppVersion copy = from.clone();
    	copy.setId(null);
    	String appVer = StringUtil.versionIncrease(from.getAppVer());
    	copy.setAppVer(appVer);
    	saveAppVersion(copy);
    	
    	from.setLatest(false);
    	saveAppVersion(from);
    }
    
    public void saveAppVersion(AppVersion appVersion) {
    	repo.save(appVersion);
    }
}
