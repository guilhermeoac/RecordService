package com.ntd.recordservice.repository;

import com.ntd.recordservice.repository.dto.RecordFilterOutputDTO;
import com.ntd.recordservice.repository.dto.RecordOutputDTO;
import com.ntd.recordservice.repository.dto.RecordResponseOutputDTO;
import org.springframework.data.domain.Page;

public interface RecordRepository {

    Page<RecordResponseOutputDTO> findRecordsPageable(Long userId, RecordFilterOutputDTO dto);

    void save(Long userId, RecordOutputDTO dto);
    void delete(Long id) throws Exception;

}

