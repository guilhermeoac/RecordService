package com.ntd.recordservice.repository.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.springframework.data.domain.Pageable;

public record RecordFilterOutputDTO(
        String operationType,
        BigDecimal amount,
        BigDecimal cost,
        String operationResult,
        LocalDateTime beginDate,
        LocalDateTime endDate,
        Pageable pageable
) {

}
