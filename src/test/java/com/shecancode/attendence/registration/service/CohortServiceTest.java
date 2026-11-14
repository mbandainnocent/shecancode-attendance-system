package com.shecancode.attendence.registration.service;

import com.shecancode.attendence.registration.dao.CohortRequestDao;
import com.shecancode.attendence.registration.dao.CohortResponseDao;
import com.shecancode.attendence.registration.Exception.CohortAlreadyExistException;
import com.shecancode.attendence.registration.Exception.ProgramNotFoundException;
import com.shecancode.attendence.registration.Model.Cohort;
import com.shecancode.attendence.registration.Model.Program;
import com.shecancode.attendence.registration.Repository.CohortRepository;
import com.shecancode.attendence.registration.Repository.ProgramRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CohortServiceTest {

    @Mock
    private CohortRepository cohortRepository;

    @Mock
    private ProgramRepository programRepository;

    @InjectMocks
    private CohortService cohortService;

    private CohortRequestDao cohortRequestToTest;
    private final UUID programId = UUID.randomUUID();
    private Program program;

    @BeforeEach
    void SetUp(){

        program = Program.builder().id(programId).programName("Backend").build();

        cohortRequestToTest = CohortRequestDao.builder()
                .cohortNumber("C10")
                .programId(programId)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .stipendEligible(true)
                .stipendAmount(new BigDecimal("50000.00"))
                .build();

    }

    @Test
    void givenNewCohortNumber_whenCreateCohort_thenCohortIsSaved(){

        when(programRepository.findByIdAndDeletedAtIsNull(programId)).thenReturn(Optional.of(program));
        when(cohortRepository.existsByProgram_IdAndCohortNumber(programId, "C10")).thenReturn(false);
        when(cohortRepository.save(any(Cohort.class))).thenAnswer(invocation -> invocation.getArgument(0));

        CohortResponseDao savedCohort = cohortService.createCohort(cohortRequestToTest);

        assertNotNull(savedCohort);
        assertEquals("C10", savedCohort.getCohortNumber());
        assertEquals("Backend", savedCohort.getProgramName());
        assertTrue(savedCohort.isStipendEligible());
        assertEquals(new BigDecimal("50000.00"), savedCohort.getStipendAmount());
        verify(cohortRepository).existsByProgram_IdAndCohortNumber(programId, "C10");
        verify(cohortRepository, times(1)).save(any(Cohort.class));
    }

    @Test
    void test_createCohort_AlreadyExists_ThrowsException(){
        when(programRepository.findByIdAndDeletedAtIsNull(programId)).thenReturn(Optional.of(program));
        when(cohortRepository.existsByProgram_IdAndCohortNumber(programId, "C10")).thenReturn(true);

        assertThrows(CohortAlreadyExistException.class, () -> cohortService.createCohort(cohortRequestToTest));
    }

    @Test
    void test_createCohort_InvalidProgram_ThrowsNotFound(){
        when(programRepository.findByIdAndDeletedAtIsNull(programId)).thenReturn(Optional.empty());

        assertThrows(ProgramNotFoundException.class, () -> cohortService.createCohort(cohortRequestToTest));
        verify(cohortRepository, never()).save(any(Cohort.class));
    }

    @Test
    void givenNoStipendDecision_whenCreateCohort_thenRejected(){
        cohortRequestToTest.setStipendEligible(null);
        when(programRepository.findByIdAndDeletedAtIsNull(programId)).thenReturn(Optional.of(program));
        when(cohortRepository.existsByProgram_IdAndCohortNumber(programId, "C10")).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> cohortService.createCohort(cohortRequestToTest));
        verify(cohortRepository, never()).save(any(Cohort.class));
    }



    @org.junit.jupiter.params.ParameterizedTest
    @org.junit.jupiter.params.provider.NullSource
    @org.junit.jupiter.params.provider.ValueSource(strings = {"0", "-10", "1.001", "100000000000000000"})
    void eligibleCohortRejectsInvalidAmount(String amount) {
        cohortRequestToTest.setStipendAmount(amount == null ? null : new BigDecimal(amount));
        when(programRepository.findByIdAndDeletedAtIsNull(programId)).thenReturn(Optional.of(program));
        assertThrows(IllegalArgumentException.class, () -> cohortService.createCohort(cohortRequestToTest));
        verify(cohortRepository, never()).save(any());
    }

    @Test
    void ineligibleCohortNeedsNoAmount() {
        cohortRequestToTest.setStipendEligible(false);
        cohortRequestToTest.setStipendAmount(null);
        when(programRepository.findByIdAndDeletedAtIsNull(programId)).thenReturn(Optional.of(program));
        when(cohortRepository.save(any(Cohort.class))).thenAnswer(i -> i.getArgument(0));
        assertNull(cohortService.createCohort(cohortRequestToTest).getStipendAmount());
    }

    @Test
    void updatePreservesAmountAndDisablingEligibilityClearsIt() {
        UUID id = UUID.randomUUID();
        Cohort cohort = Cohort.builder().id(id).program(program).stipendEligible(true)
                .stipendAmount(new BigDecimal("50000.00")).build();
        when(cohortRepository.findByIdAndDeletedAtIsNull(id)).thenReturn(Optional.of(cohort));
        when(cohortRepository.save(any(Cohort.class))).thenAnswer(i -> i.getArgument(0));
        cohortRequestToTest.setStipendEligible(null);
        cohortRequestToTest.setStipendAmount(null);
        assertEquals(new BigDecimal("50000.00"), cohortService.updateCohort(id, cohortRequestToTest).getStipendAmount());
        cohortRequestToTest.setStipendAmount(new BigDecimal("60000.00"));
        assertEquals(new BigDecimal("60000.00"), cohortService.updateCohort(id, cohortRequestToTest).getStipendAmount());
        cohortRequestToTest.setStipendEligible(false);
        cohortRequestToTest.setStipendAmount(null);
        assertNull(cohortService.updateCohort(id, cohortRequestToTest).getStipendAmount());
        cohortRequestToTest.setStipendEligible(true);
        assertThrows(IllegalArgumentException.class, () -> cohortService.updateCohort(id, cohortRequestToTest));
    }
}
