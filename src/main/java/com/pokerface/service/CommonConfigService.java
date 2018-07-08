package com.pokerface.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pokerface.model.Agent;
import com.pokerface.model.CommonConfig;
import com.pokerface.repos.CommonConfigRepository;
import com.pokerface.util.HttpUtil;

@Component
public class CommonConfigService {
    @PersistenceContext
    private EntityManager em;
    
    @Autowired
    private CommonConfigRepository repo;

    public List<CommonConfig> commonConfigs(){
    	return repo.findAll();
    }
    
    public CommonConfig commonConfigDetail(Long id){
    	return repo.findOne(id);
    }
    
    public void saveCommonConfig(CommonConfig commonConfig) {
    	repo.save(commonConfig);
    	HttpUtil.sendGet(HttpUtil.getServerPrefix()+"/refresh?type=common");
    }
    
    public boolean roomIdExists(String configKey){
    	Query query = em
                .createQuery("From CommonConfig cr WHERE cr.configKey = :configKey");
    	query.setParameter("configKey", configKey);
    	@SuppressWarnings("unchecked")
		List<Agent> list = query.getResultList();
    	return list!=null && list.size()>0;
    }
}
