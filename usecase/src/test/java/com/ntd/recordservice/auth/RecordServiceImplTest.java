package com.ntd.recordservice.auth;

import com.ntd.recordservice.dto.RecordFilterInputDTO;
import com.ntd.recordservice.dto.RecordInputDTO;
import com.ntd.recordservice.repository.RecordRepository;
import com.ntd.recordservice.repository.dto.RecordFilterOutputDTO;
import com.ntd.recordservice.repository.dto.RecordOutputDTO;
import com.ntd.recordservice.repository.dto.RecordResponseOutputDTO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RecordServiceImplTest {

    @Mock
    private RecordRepository recordRepository;

    @InjectMocks
    private RecordServiceImpl recordService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindRecordsPageable_Success() {
        Long userId = 1L;
        RecordFilterInputDTO filterDTO = new RecordFilterInputDTO("operationType", BigDecimal.TEN, BigDecimal.ONE, "result", LocalDateTime.now(), LocalDateTime.now(), 0, 10, "id", "ASC");
        RecordResponseOutputDTO recordResponseOutputDTO = new RecordResponseOutputDTO(1L, "operationType", BigDecimal.TEN, BigDecimal.ONE, "result", LocalDateTime.now(), true);
        Page<RecordResponseOutputDTO> outputPage = new PageImpl<>(Collections.singletonList(recordResponseOutputDTO), PageRequest.of(0, 10), 1);
        when(recordRepository.findRecordsPageable(anyLong(), any(RecordFilterOutputDTO.class))).thenReturn(outputPage);

        Page<RecordInputDTO> result = recordService.findRecordsPageable(userId, filterDTO);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals("operationType", result.getContent().get(0).operationType());
        verify(recordRepository, times(1)).findRecordsPageable(anyLong(), any(RecordFilterOutputDTO.class));
    }

    @Test
    void testFindRecordsPageable_Exception() {
        Long userId = 1L;
        RecordFilterInputDTO filterDTO = new RecordFilterInputDTO("operationType", BigDecimal.TEN, BigDecimal.ONE, "result", LocalDateTime.now(), LocalDateTime.now(), 0, 10, "id", "ASC");
        when(recordRepository.findRecordsPageable(anyLong(), any(RecordFilterOutputDTO.class))).thenThrow(new RuntimeException("Database error"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> recordService.findRecordsPageable(userId, filterDTO));

        assertEquals("Database error", exception.getMessage());
        verify(recordRepository, times(1)).findRecordsPageable(anyLong(), any(RecordFilterOutputDTO.class));
    }

    @Test
    void testSave_Success() {
        Long userId = 1L;
        RecordInputDTO inputDTO = new RecordInputDTO(null, "operationType", BigDecimal.TEN, BigDecimal.ONE, "result", LocalDateTime.now(), true);

        recordService.save(userId, inputDTO);

        ArgumentCaptor<RecordOutputDTO> captor = ArgumentCaptor.forClass(RecordOutputDTO.class);
        verify(recordRepository, times(1)).save(eq(userId), captor.capture());
        assertEquals("operationType", captor.getValue().operationType());
        assertEquals(BigDecimal.TEN, captor.getValue().amount());
    }

    @Test
    void testSave_Exception() {
        Long userId = 1L;
        RecordInputDTO inputDTO = new RecordInputDTO(null, "operationType", BigDecimal.TEN, BigDecimal.ONE, "result", LocalDateTime.now(), true);
        doThrow(new RuntimeException("Database error")).when(recordRepository).save(anyLong(), any(RecordOutputDTO.class));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> recordService.save(userId, inputDTO));

        assertEquals("Database error", exception.getMessage());
        verify(recordRepository, times(1)).save(anyLong(), any(RecordOutputDTO.class));
    }
}
