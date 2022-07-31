package com.thalasoft.learnintouch.data.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.jpa.domain.StatisticsPage;

public interface StatisticsPageRepository extends GenericRepository<StatisticsPage, Long> {

    @Modifying
    @Query("UPDATE StatisticsPage sp SET sp.hits = sp.hits + 1 WHERE sp.id = :id")
    public void addHit(@Param("id") Long id);

    @Modifying
    @Query("DELETE StatisticsPage sp WHERE sp.year = :year")
    public void deleteOldStat(@Param("year") String year);

    @Query("SELECT sp FROM StatisticsPage sp ORDER BY sp.hits DESC")
    public Page<StatisticsPage> findThemAll(Pageable page);

    @Query("SELECT sp FROM StatisticsPage sp WHERE sp.year = :year AND sp.month = :month ORDER BY sp.hits DESC")
    public Page<StatisticsPage> findByYearAndMonth(@Param("year") String year, @Param("month") String month, Pageable page);

    @Query("SELECT sp FROM StatisticsPage sp WHERE sp.year = :year AND sp.month = :month AND sp.page = :statisticsPage ORDER BY sp.hits DESC")
    public Page<StatisticsPage> findByYearAndMonthAndPage(@Param("year") String year, @Param("month") String month, @Param("statisticsPage") String statisticsPage, Pageable page);

}
