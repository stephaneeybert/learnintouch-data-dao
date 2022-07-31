package com.thalasoft.learnintouch.data.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.jpa.domain.ContactStatus;

public interface ContactStatusRepository extends GenericRepository<ContactStatus, Long> {

    @Query("SELECT c FROM ContactStatus c ORDER BY c.listOrder")
    public List<ContactStatus> findThemAll();

    @Query("SELECT c FROM ContactStatus c ORDER BY c.id")
    public List<ContactStatus> findAllOrderById();

    @Query("SELECT c FROM ContactStatus c ORDER BY c.listOrder")
    public ContactStatus findFirst();

    @Query("SELECT c FROM ContactStatus c WHERE c.listOrder > :listOrder ORDER BY c.listOrder ASC LIMIT 1")
    public ContactStatus findByNextListOrder(@Param("listOrder") int listOrder);

    @Query("SELECT c FROM ContactStatus c WHERE c.listOrder < :listOrder ORDER BY c.listOrder DESC LIMIT 1")
    public ContactStatus findByPreviousListOrder(@Param("listOrder") int listOrder);

    @Query("SELECT c FROM ContactStatus c WHERE c.listOrder = :listOrder ORDER BY c.listOrder DESC")
    public List<ContactStatus> findByListOrder(@Param("listOrder") int listOrder);

    @Query("SELECT COUNT(DISTINCT c1.id) as count FROM ContactStatus c1, ContactStatus c2 WHERE c1.id != c2.id AND c1.listOrder = c2.listOrder")
    public Long countDuplicateListOrders();

}
