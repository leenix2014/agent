package com.pokerface.config;

import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;

public class MyNamingStrategy extends SpringPhysicalNamingStrategy {

	@Override
	protected boolean isCaseInsensitive(JdbcEnvironment jdbcEnvironment) {
		return false;
	}
}
