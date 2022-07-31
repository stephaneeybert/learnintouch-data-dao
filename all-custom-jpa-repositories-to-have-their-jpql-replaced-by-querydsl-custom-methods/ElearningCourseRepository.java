package com.thalasoft.learnintouch.data.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.jpa.domain.ElearningCourse;
import com.thalasoft.learnintouch.data.jpa.domain.ElearningMatter;
import com.thalasoft.learnintouch.data.jpa.domain.ElearningSession;
import com.thalasoft.learnintouch.data.jpa.domain.ElearningTeacher;
import com.thalasoft.learnintouch.data.jpa.domain.UserAccount;

public interface ElearningCourseRepository extends GenericRepository<ElearningCourse, Long> {

    @Query("SELECT ec FROM ElearningCourse ec ORDER BY ec.name")
    public Page<ElearningCourse> findThemAll(Pageable page);

    @Query("SELECT ec FROM ElearningCourse ec WHERE ec.name = :name")
    public ElearningCourse findByName(@Param("name") String name);

    @Query("SELECT ec FROM ElearningCourse ec WHERE ec.image = :image")
    public List<ElearningCourse> findByImage(@Param("image") String image);

    @Query("SELECT ec FROM ElearningCourse ec WHERE ec.importable = '1' ORDER BY ec.name")
    public List<ElearningCourse> findImportable();

    @Query("SELECT ec FROM ElearningCourse ec WHERE ec.autoSubscription = '1' ORDER BY ec.name")
    public List<ElearningCourse> findAutoSubscription();

    @Query("SELECT ec FROM ElearningCourse ec, ElearningSessionCourse esc WHERE ec.autoSubscription = '1' AND esc.elearningCourse.id = ec.id AND esc.elearningSession = :elearningSession ORDER BY ec.name")
    public List<ElearningCourse> findBySession(@Param("elearningSession") ElearningSession elearningSession);

    @Query("SELECT ec FROM ElearningCourse ec WHERE ec.elearningMatter = :elearningMatter ORDER BY ec.name")
    public Page<ElearningCourse> findByMatter(@Param("elearningMatter") ElearningMatter elearningMatter, Pageable page);

    @Query("SELECT ec FROM ElearningCourse ec WHERE ec.userAccount = :userAccount ORDER BY ec.name")
    public Page<ElearningCourse> findByUserAccount(@Param("userAccount") UserAccount userAccount, Pageable page);

    @Query("SELECT ec FROM ElearningCourse ec WHERE LOWER(ec.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(ec.description) LIKE LOWER(CONCAT('%', :searchTerm, '%')) ORDER BY ec.name")
    public Page<ElearningCourse> search(@Param("searchTerm") String searchTerm, Pageable page);

    @Query("SELECT ec FROM ElearningCourse ec, ElearningSessionCourse esc WHERE LOWER(ec.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(ec.description) LIKE LOWER(CONCAT('%', :searchTerm, '%')) AND esc.elearningCourse.id = ec.id AND esc.elearningSession = :elearningSession ORDER BY ec.name")
    public Page<ElearningCourse> searchBySession(@Param("searchTerm") String searchTerm, @Param("elearningSession") ElearningSession elearningSession, Pageable page);

    @Query("SELECT ec FROM ElearningCourse ec, ElearningSubscription es WHERE es.elearningCourse.id = ec.id AND es.elearningTeacher = :elearningTeacher ORDER BY ec.name")
    public List<ElearningCourse> findBySubscriptionTeacher(@Param("elearningTeacher") ElearningTeacher elearningTeacher);

}
