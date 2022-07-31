package com.thalasoft.learnintouch.data.jpa.repository;

public interface StatisticsVisitRepositoryCustom {

    public Long countOldVisits(String year);

    public Long countByReferer(String year, String month);

    public Long countByBrowser();

    public Long countMonthVisitors(String year, String month);

    public Long countMonthVisits(String year, String month);

    public Long countDayVisitors(String year, String month, String day);

    public Long countWeekDayVisits(String year, String day);

    public Long countHourVisits(String year, String hour);

    public Long countVisitors(String visitDatetime);

    public Long countVisits(String visitDatetime);

}
