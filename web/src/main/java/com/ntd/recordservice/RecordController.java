package com.ntd.recordservice;

import com.ntd.recordservice.dto.RecordFilterInputDTO;
import com.ntd.recordservice.dto.RecordInputDTO;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/record")
public class RecordController {

    private final RecordService recordService;

    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }


    @PostMapping
    public ResponseEntity<Void> saveRecord(
            @RequestHeader("userId") Long userId,
            @RequestBody RecordInputDTO body
            ) {
        recordService.save(userId, body);
        return ResponseEntity.ok().build();
    }
    @GetMapping
    public ResponseEntity<Page<RecordInputDTO>> getaAllPage(
            @RequestHeader("userId") Long userId,
            @RequestParam(value = "operationType", required = false) String operationType,
            @RequestParam(value = "amount", required = false) BigDecimal amount,
            @RequestParam(value = "cost", required = false) BigDecimal cost,
            @RequestParam(value = "operationResult", required = false) String operationResult,
            @RequestParam(value = "beginDate", required = false) LocalDateTime beginDate,
            @RequestParam(value = "endDate", required = false) LocalDateTime endDate,
            @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "sortField", required = false) String sortField,
            @RequestParam(value = "sortDirection", required = false) String sortDirection
    ) throws Exception {
        return ResponseEntity.ok(recordService.findRecordsPageable(userId, new RecordFilterInputDTO(
                operationType,
                amount,
                cost,
                operationResult,
                beginDate,
                endDate,
                pageNumber != null ? pageNumber : 0,
                pageSize != null ? pageSize : 10,
                sortField,
                sortDirection)));
    }
}


