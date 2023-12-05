package com.uni.labat23.repository;
import com.uni.labat23.entity.Calculations;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
@Repository
public interface CalculationsRepository extends JpaRepository<Calculations, Long> {
    @Transactional
    @Query(value = "select date_created from calculations where number_one=:numberOne", nativeQuery = true)
    Date findDate(String numberOne);

    @Query(value = "select *\n" +
            "from calculations\n" +
            " where (date_created > :startDate) AND (date_created < :endDate)", nativeQuery = true)
    List<Calculations> findByDate(Date startDate, Date endDate);

    @Query(value = "select *\n" +
            "from calculations\n" +
            "where id IN (select id from calculations where number_system_one= :numberSystemOne)\n" +
            "  AND (number_system_two = :numberSystemTwo)\n" +
            "  AND (operation_name = :operationName)\n" +
            "  AND (date_created > :startDate) AND (date_created < :endDate)", nativeQuery = true)
    List<Calculations> findByParameters(int numberSystemOne, int numberSystemTwo, String operationName, Date startDate, Date endDate);

    @Transactional
    @Modifying
    @Query(value = "delete from calculations", nativeQuery = true)
    void clear();

    List<Calculations> findByNumberSystemOneAndNumberSystemTwoAndDateCreatedGreaterThanAndDateCreatedLessThan(@NotNull Integer numberSystemOne, @NotNull Integer numberSystemTwo, @NotNull Date dateCreated, @NotNull Date dateCreated2);
    List<Calculations> findByDateCreatedGreaterThanAndDateCreatedLessThan(@NotNull Date dateCreated, @NotNull Date dateCreated2);
}
