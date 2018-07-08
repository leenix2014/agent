package com.pokerface.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pokerface.model.AppVersion;

public interface AppVersionRepository extends JpaRepository<AppVersion, Long> {

}
