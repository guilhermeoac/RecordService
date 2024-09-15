package com.ntd.recordservice.repository.impl;

import com.ntd.recordservice.repository.dto.RecordFilterOutputDTO;
import com.ntd.recordservice.repository.dto.RecordOutputDTO;
import com.ntd.recordservice.repository.dto.RecordResponseOutputDTO;
import com.ntd.recordservice.repository.interfaces.RecordJpaRepository;
import com.ntd.recordservice.repository.model.RecordEntity;
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
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RecordRepositoryImplTest {

    @Mock
    private RecordJpaRepository recordJpaRepository;

    @InjectMocks
    private RecordRepositoryImpl recordRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindRecordsPageable_Success() {
        Long userId = 1L;
        RecordFilterOutputDTO filterDTO = new RecordFilterOutputDTO("operationType", BigDecimal.TEN, BigDecimal.ONE, "result", LocalDateTime.now(), LocalDateTime.now(), PageRequest.of(0, 10));
        RecordEntity recordEntity = new RecordEntity(1L, userId, "operationType", BigDecimal.TEN, BigDecimal.ONE, "result");
        Page<RecordEntity> entityPage = new PageImpl<>(Collections.singletonList(recordEntity), filterDTO.pageable(), 1);
        when(recordJpaRepository.findRecordsPageable(anyLong(), anyString(), any(BigDecimal.class), any(BigDecimal.class), anyString(), any(LocalDateTime.class), any(LocalDateTime.class), any(Pageable.class)))
                .thenReturn(entityPage);

        Page<RecordResponseOutputDTO> result = recordRepository.findRecordsPageable(userId, filterDTO);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals("operationType", result.getContent().get(0).operationType());
        verify(recordJpaRepository, times(1)).findRecordsPageable(anyLong(), anyString(), any(BigDecimal.class), any(BigDecimal.class), anyString(), any(LocalDateTime.class), any(LocalDateTime.class), any(Pageable.class));
    }

    @Test
    void testFindRecordsPageable_Exception() {
        Long userId = 1L;
        RecordFilterOutputDTO filterDTO = new RecordFilterOutputDTO("operationType", BigDecimal.TEN, BigDecimal.ONE, "result", LocalDateTime.now(), LocalDateTime.now(), PageRequest.of(0, 10));
        when(recordJpaRepository.findRecordsPageable(anyLong(), anyString(), any(BigDecimal.class), any(BigDecimal.class), anyString(), any(LocalDateTime.class), any(LocalDateTime.class), any(Pageable.class)))
                .thenThrow(new RuntimeException("Database error"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> recordRepository.findRecordsPageable(userId, filterDTO));

        assertEquals("Database error", exception.getMessage());
        verify(recordJpaRepository, times(1)).findRecordsPageable(anyLong(), anyString(), any(BigDecimal.class), any(BigDecimal.class), anyString(), any(LocalDateTime.class), any(LocalDateTime.class), any(Pageable.class));
    }

    @Test
    void testSave_Success() {
        Long userId = 1L;
        RecordOutputDTO outputDTO = new RecordOutputDTO("operationType", BigDecimal.TEN, BigDecimal.ONE, "result");

        recordRepository.save(userId, outputDTO);

        ArgumentCaptor<RecordEntity> captor = ArgumentCaptor.forClass(RecordEntity.class);
        verify(recordJpaRepository, times(1)).save(captor.capture());
        assertEquals("operationType", captor.getValue().getOperationType());
        assertEquals(BigDecimal.TEN, captor.getValue().getAmount());
        assertEquals(userId, captor.getValue().getUserId());
    }

    @Test
    void testSave_Exception() {
        Long userId = 1L;
        RecordOutputDTO outputDTO = new RecordOutputDTO("operationType", BigDecimal.TEN, BigDecimal.ONE, "result");
        doThrow(new RuntimeException("Database error")).when(recordJpaRepository).save(any(RecordEntity.class));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> recordRepository.save(userId, outputDTO));

        assertEquals("Database error", exception.getMessage());
        verify(recordJpaRepository, times(1)).save(any(RecordEntity.class));
    }
}
