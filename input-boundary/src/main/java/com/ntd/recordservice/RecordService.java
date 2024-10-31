package com.ntd.recordservice;

import com.ntd.recordservice.dto.RecordFilterInputDTO;
import com.ntd.recordservice.dto.RecordInputDTO;
import org.springframework.data.domain.Page;

public interface RecordService {

    Page<RecordInputDTO> findRecordsPageable(Long userId, RecordFilterInputDTO dto);

    void save(Long userId, RecordInputDTO dto);
    void delete(Long id) throws Exception;

}

