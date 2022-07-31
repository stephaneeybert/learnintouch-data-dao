package com.thalasoft.learnintouch.data.dialect;

import java.sql.Types;

import org.hibernate.dialect.MySQL5InnoDBDialect;

public class CustomMySQL5InnoDBDialect_NOT_USED extends MySQL5InnoDBDialect {

	public CustomMySQL5InnoDBDialect_NOT_USED() {
		super();
		
		// Upgrading from Hibernate 3 to Hibernate 4 made the Hibernate mapping of boolean type on the tinyint(1) type invalid.
		registerColumnType(Types.BOOLEAN, "bit");
	}

}
