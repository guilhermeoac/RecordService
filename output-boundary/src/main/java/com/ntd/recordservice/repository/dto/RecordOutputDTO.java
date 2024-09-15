package com.ntd.recordservice.repository.dto;

import java.math.BigDecimal;

public record RecordOutputDTO(
        String operationType,
        BigDecimal amount,
        BigDecimal cost,
        String operationResult
) {

}
