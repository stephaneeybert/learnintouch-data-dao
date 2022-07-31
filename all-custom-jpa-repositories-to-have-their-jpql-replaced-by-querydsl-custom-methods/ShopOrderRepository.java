package com.thalasoft.learnintouch.data.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.jpa.domain.ShopOrder;
import com.thalasoft.learnintouch.data.jpa.domain.UserAccount;

public interface ShopOrderRepository extends GenericRepository<ShopOrder, Long> {

    @Query("SELECT so FROM ShopOrder so ORDER BY so.orderDate DESC")
    public Page<ShopOrder> findThemAll(Pageable page);
    
    @Query("SELECT so FROM ShopOrder so WHERE so.email = :email ORDER BY so.orderDate DESC")
    public Page<ShopOrder> findByEmail(@Param("email") String email, Pageable page);

    @Query("SELECT so FROM ShopOrder so WHERE so.userAccount = :userAccount ORDER BY so.orderDate DESC")
    public Page<ShopOrder> findByUserAccount(@Param("userAccount") UserAccount userAccount, Pageable page);

    @Query("SELECT so FROM ShopOrder so WHERE so.status = :status ORDER BY so.orderDate DESC")
    public Page<ShopOrder> findByStatus(@Param("status") String status, Pageable page);
    
    @Query("SELECT so FROM ShopOrder so WHERE YEAR(so.orderDate) = :year AND MONTH(so.orderDate) = :month ORDER BY so.orderDate DESC")
    public Page<ShopOrder> findByYearAndMonth(@Param("year") String year, @Param("month") String month, Pageable page);
    
    @Query("SELECT so FROM ShopOrder so WHERE so.status = :status AND YEAR(so.orderDate) = :year AND MONTH(so.orderDate) = :month ORDER BY so.orderDate DESC")
    public Page<ShopOrder> findByStatusAndYearAndMonth(@Param("status") String status, @Param("year") String year, @Param("month") String month, Pageable page);
    
    @Query("SELECT so FROM ShopOrder so WHERE LOWER(so.firstname) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(so.lastname) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(so.email) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(so.organisation) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(so.telephone) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(so.mobilePhone) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(so.fax) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(so.message) LIKE LOWER(CONCAT('%', :searchTerm, '%')) ORDER BY so.orderDate DESC")
    public Page<ShopOrder> search(@Param("searchTerm") String searchTerm, Pageable page);

}
