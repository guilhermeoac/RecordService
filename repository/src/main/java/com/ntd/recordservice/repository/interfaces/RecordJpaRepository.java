package com.ntd.recordservice.repository.interfaces;

import com.ntd.recordservice.repository.model.RecordEntity;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordJpaRepository extends JpaRepository<RecordEntity, Long> {

    @Query("SELECT r FROM RecordEntity r " +
            "WHERE r.userId = :userId " +
            "AND (:operationType IS NULL OR r.operationType = :operationType) " +
            "AND (:amount IS NULL OR r.amount = :amount) " +
            "AND (:cost IS NULL OR r.cost = :cost) " +
            "AND (:operationResult IS NULL OR r.operationResult = :operationResult) " +
            "AND (TRUE = :#{#beginDate == null} OR r.date > :beginDate) " +
            "AND (TRUE = :#{#endDate == null} OR r.date < :endDate)")
    Page<RecordEntity> findRecordsPageable(
            @Param("userId") Long userId,
            @Param("operationType") String operationType,
            @Param("amount") BigDecimal amount,
            @Param("cost") BigDecimal cost,
            @Param("operationResult") String operationResult,
            @Param("beginDate") LocalDateTime beginDate,
            @Param("endDate") LocalDateTime endDate,
            Pageable pageable);

}

