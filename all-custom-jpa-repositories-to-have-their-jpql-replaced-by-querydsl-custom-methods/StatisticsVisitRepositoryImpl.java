package com.thalasoft.learnintouch.data.jpa.repository;

import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

public class StatisticsVisitRepositoryImpl implements StatisticsVisitRepositoryCustom {

    @Autowired
    private StatisticsVisitRepository statisticsVisitRepository;

    public Long countOldVisits(String year) {
        String sqlStatement = "SELECT COUNT(sv.id) AS count FROM StatisticsVisit sv WHERE YEAR(sv.visitDatetime) < :year";
        
        TypedQuery<Long> query = statisticsVisitRepository.getEntityManager().createQuery(sqlStatement, Long.class);

        query.setParameter("year", year);

        Long count = query.getSingleResult();
        
        return count;
    }

    public Long countByReferer(String year, String month) {
        String sqlStatement = "SELECT COUNT(sv.id) AS count, sv.visitorReferer FROM StatisticsVisit sv WHERE YEAR(sv.visitDatetime) = :year AND MONTH(sv.visitDatetime) = :month GROUP BY sv.visitorReferer";
        
        TypedQuery<Long> query = statisticsVisitRepository.getEntityManager().createQuery(sqlStatement, Long.class);

        query.setParameter("year", year);
        query.setParameter("month", month);

        Long count = query.getSingleResult();
        
        return count;
    }

    public Long countByBrowser() {
        String sqlStatement = "SELECT COUNT(sv.id) AS count, sv.visitorBrowser FROM StatisticsVisit sv GROUP BY sv.visitorBrowser";
        
        TypedQuery<Long> query = statisticsVisitRepository.getEntityManager().createQuery(sqlStatement, Long.class);

        Long count = query.getSingleResult();
        
        return count;
    }

    public Long countMonthVisitors(String year, String month) {
        String sqlStatement = "SELECT COUNT(DISTINCT sv.visitorHostAddress) AS count FROM StatisticsVisit sv WHERE YEAR(sv.visitDatetime) = :year AND MONTH(sv.visitDatetime) = :month";
        
        TypedQuery<Long> query = statisticsVisitRepository.getEntityManager().createQuery(sqlStatement, Long.class);

        query.setParameter("year", year);
        query.setParameter("month", month);

        Long count = query.getSingleResult();
        
        return count;
    }

    public Long countMonthVisits(String year, String month) {
        String sqlStatement = "SELECT COUNT(sv.id) AS count FROM StatisticsVisit sv WHERE YEAR(sv.visitDatetime) = :year AND MONTH(sv.visitDatetime) = :month";
        
        TypedQuery<Long> query = statisticsVisitRepository.getEntityManager().createQuery(sqlStatement, Long.class);

        query.setParameter("year", year);
        query.setParameter("month", month);

        Long count = query.getSingleResult();
        
        return count;
    }

    public Long countDayVisitors(String year, String month, String day) {
        String sqlStatement = "SELECT COUNT(DISTINCT sv.visitorHostAddress) AS count FROM StatisticsVisit sv WHERE YEAR(sv.visitDatetime) = :year AND MONTH(sv.visitDatetime) = :month AND DAYOFMONTH(sv.visitDatetime) = :day";
        
        TypedQuery<Long> query = statisticsVisitRepository.getEntityManager().createQuery(sqlStatement, Long.class);

        query.setParameter("year", year);
        query.setParameter("month", month);
        query.setParameter("day", day);

        Long count = query.getSingleResult();
        
        return count;
    }

    public Long countWeekDayVisits(String year, String day) {
        // MySQL is following the ODBC standard
        // which takes Sunday as the first day of the week
        int intDay = Integer.valueOf(day).intValue();
        intDay++;
        if (intDay > 7) {
            intDay = 1;
        }
        day = Integer.toString(intDay);
                
        String sqlStatement = "SELECT COUNT(sv.id) AS count FROM StatisticsVisit sv WHERE YEAR(sv.visitDatetime) = :year AND DAYOFWEEK(sv.visitDatetime) = :day";
        
        TypedQuery<Long> query = statisticsVisitRepository.getEntityManager().createQuery(sqlStatement, Long.class);

        query.setParameter("year", year);
        query.setParameter("day", day);

        Long count = query.getSingleResult();
        
        return count;
    }

    public Long countHourVisits(String year, String hour) {
        String sqlStatement = "SELECT COUNT(sv.id) AS count FROM StatisticsVisit sv WHERE YEAR(sv.visitDatetime) = :year AND HOUR(sv.visitDatetime) = :hour";
        
        TypedQuery<Long> query = statisticsVisitRepository.getEntityManager().createQuery(sqlStatement, Long.class);

        query.setParameter("year", year);
        query.setParameter("hour", hour);

        Long count = query.getSingleResult();
        
        return count;
    }

    public Long countVisitors(String visitDatetime) {
        String sqlStatement = "SELECT COUNT(DISTINCT sv.visitorHostAddress) AS count FROM StatisticsVisit sv WHERE sv.visitDatetime >= :visitDatetime";
        
        TypedQuery<Long> query = statisticsVisitRepository.getEntityManager().createQuery(sqlStatement, Long.class);

        query.setParameter("visitDatetime", visitDatetime);

        Long count = query.getSingleResult();
        
        return count;
    }

    public Long countVisits(String visitDatetime) {
        String sqlStatement = "SELECT COUNT(sv.id) AS count FROM StatisticsVisit sv WHERE sv.visitDatetime >= :visitDatetime";
        
        TypedQuery<Long> query = statisticsVisitRepository.getEntityManager().createQuery(sqlStatement, Long.class);

        query.setParameter("visitDatetime", visitDatetime);

        Long count = query.getSingleResult();
        
        return count;
    }

}
