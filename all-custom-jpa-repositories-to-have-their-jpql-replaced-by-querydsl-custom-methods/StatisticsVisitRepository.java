package com.thalasoft.learnintouch.data.jpa.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.jpa.domain.StatisticsVisit;

public interface StatisticsVisitRepository extends GenericRepository<StatisticsVisit, Long> {

    @Modifying
    @Query("DELETE StatisticsVisit sv WHERE YEAR(sv.visitDatetime) < :year")
    public void deleteOldStat(@Param("year") String year);

    @Query("SELECT sv.id, MAX(sv.visitDatetime) AS visitDatetime, sv.visitorHostAddress, sv.visitorBrowser, sv.visitorReferer FROM StatisticsVisit sv WHERE sv.visitorHostAddress = :visitorHostAddress GROUP BY sv.visitorHostAddress")
    public StatisticsVisit findLastVisit(@Param("visitorHostAddress") String visitorHostAddress);

}
