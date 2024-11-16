package com.ntd.recordservice.repository.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record RecordResponseOutputDTO(
        Long id,
        String operationType,
        BigDecimal amount,
        BigDecimal cost,
        String operationResult,
        LocalDateTime date,

        Boolean active
) {

}
