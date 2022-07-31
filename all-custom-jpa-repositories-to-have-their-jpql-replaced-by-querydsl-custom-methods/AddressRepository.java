package com.thalasoft.learnintouch.data.jpa.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.jpa.domain.Address;

public interface AddressRepository extends GenericRepository<Address, Long> {

	@Query("UPDATE Address SET address1 = :address1, address2 = :address2, zipCode = :zipCode, city = :city, state = :state, country =  :country, postalBox = :postalBox WHERE id = :id")
	public Address update(@Param("address1") String address1, @Param("address2") String address2, @Param("zipCode") String zipCode, @Param("city") String city, @Param("state") String state, @Param("country") String country, @Param("postalBox") String postalBox, @Param("id") Long id);

}
