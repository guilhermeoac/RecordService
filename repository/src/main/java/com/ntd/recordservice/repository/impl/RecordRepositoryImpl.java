package com.ntd.recordservice.repository.impl;

import com.ntd.recordservice.repository.RecordRepository;
import com.ntd.recordservice.repository.dto.RecordFilterOutputDTO;
import com.ntd.recordservice.repository.dto.RecordOutputDTO;
import com.ntd.recordservice.repository.dto.RecordResponseOutputDTO;
import com.ntd.recordservice.repository.interfaces.RecordJpaRepository;
import com.ntd.recordservice.repository.model.RecordEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class RecordRepositoryImpl implements RecordRepository {
    protected final Log logger = LogFactory.getLog(this.getClass());

    private final RecordJpaRepository recordJpaRepository;

    public RecordRepositoryImpl(RecordJpaRepository recordJpaRepository) {
        this.recordJpaRepository = recordJpaRepository;
    }

    @Override
    public Page<RecordResponseOutputDTO> findRecordsPageable(Long userId, RecordFilterOutputDTO dto) {
        try {
            return recordJpaRepository.findRecordsPageable(
                    userId,
                    dto.operationType(),
                    dto.amount(),
                    dto.cost(),
                    dto.operationResult(),
                    dto.beginDate(),
                    dto.endDate(),
                    dto.pageable()
            ).map( it -> new RecordResponseOutputDTO(it.getId(), it.getOperationType(), it.getAmount(), it.getCost(), it.getOperationResult(), it.getDate(), it.getActive()));
        } catch (Exception e) {
            logger.error("RecordRepositoryImpl.findRecordsPageable, message:" + e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void save(Long userId, RecordOutputDTO dto) {
        try {
            recordJpaRepository.save(new RecordEntity(null, userId, dto.operationType(), dto.amount(), dto.cost(), dto.operationResult()));
        } catch (Exception e) {
            logger.error("RecordRepositoryImpl.save, message:" + e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        try {
            var entity = recordJpaRepository.findById(id).orElseThrow(() -> new Exception("records.not.found"));
            entity.setActive(false);
            recordJpaRepository.save(entity);
        } catch (Exception e) {
            logger.error("RecordRepositoryImpl.execute, delete:" + e.getMessage(), e);
            throw e;
        }
    }
}
