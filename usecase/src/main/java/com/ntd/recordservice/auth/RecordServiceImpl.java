package com.ntd.recordservice.auth;

import com.ntd.recordservice.RecordService;
import com.ntd.recordservice.dto.RecordFilterInputDTO;
import com.ntd.recordservice.dto.RecordInputDTO;
import com.ntd.recordservice.repository.RecordRepository;
import com.ntd.recordservice.repository.dto.RecordFilterOutputDTO;
import com.ntd.recordservice.repository.dto.RecordOutputDTO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class RecordServiceImpl implements RecordService {
    protected final Log logger = LogFactory.getLog(this.getClass());

    private final RecordRepository recordRepository;

    public RecordServiceImpl(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @Override
    public Page<RecordInputDTO> findRecordsPageable(Long userId, RecordFilterInputDTO dto) {
        try {
            return recordRepository.findRecordsPageable(
                    userId,
                    new RecordFilterOutputDTO(
                            dto.operationType(),
                            dto.amount(),
                            dto.cost(),
                            dto.operationResult(),
                            dto.beginDate(),
                            dto.endDate(),
                            PageRequest.of(dto.pageNumber(), dto.pageSize(), Sort.by(
                                    new Sort.Order(dto.sortDirection() != null ? Sort.Direction.fromString( dto.sortDirection()) : Sort.Direction.ASC,
                                            dto.sortField() != null ? dto.sortField() : "id"))
                            ))).map(it -> new RecordInputDTO(it.id(), it.operationType(), it.amount(), it.cost(), it.operationResult(), it.date()));
        } catch (Exception e) {
            logger.error("RecordServiceImpl.findRecordsPageable, message:" + e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void save(Long userId, RecordInputDTO dto) {
        try {
            recordRepository.save(userId, new RecordOutputDTO(dto.operationType(), dto.amount(), dto.cost(), dto.operationResult()));
        } catch (Exception e) {
            logger.error("RecordServiceImpl.save, message:" + e.getMessage(), e);
            throw e;
        }

    }

    @Override
    public void delete(Long id) throws Exception {
        try {
            recordRepository.delete(id);
        } catch (Exception e) {
            logger.error("RecordServiceImpl.save, message:" + e.getMessage(), e);
            throw e;
        }
    }
}
