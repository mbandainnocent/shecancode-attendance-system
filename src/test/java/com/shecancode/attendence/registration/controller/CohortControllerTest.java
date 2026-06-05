//package com.shecancode.attendence.registration.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.shecancode.attendence.registration.dao.CohortRequestDao;
//import com.shecancode.attendence.registration.dao.CohortResponseDao;
//import com.shecancode.attendence.registration.service.CohortService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.time.LocalDate;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@ExtendWith(MockitoExtension.class)
//@WebMvcTest(CohortController.class)
//class CohortControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @MockBean
//    private CohortService cohortService;
//
//    private CohortRequestDao cohortRequestDao;
//    private CohortResponseDao cohortResponseDao;
//
//    @BeforeEach
//    void setUp() {
//        cohortRequestDao = CohortRequestDao.builder()
//                .cohortNumber("C10")
//                .startDate(LocalDate.now())
//                .endDate(LocalDate.now().plusMonths(6))
//                .build();
//
//        cohortResponseDao = CohortResponseDao.builder()
//                .cohortNumber("C10")
//                .startDate(LocalDate.now())
//                .endDate(LocalDate.now().plusMonths(6))
//                .build();
//    }
//
//    @Test
//    @WithMockUser(roles = "ADMIN")
//    void createCohort_whenValidRequest_thenReturnsCreated() throws Exception {
//        when(cohortService.createCohort(any(CohortRequestDao.class))).thenReturn(cohortResponseDao);
//
//        mockMvc.perform(post("/api/v1/cohorts")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(cohortRequestDao)))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.cohortNumber").value("C10"));
//    }
//
//    @Test
//    @WithMockUser(roles = "ADMIN")
//    void createCohort_whenBlankCohortNumber_thenReturnsBadRequest() throws Exception {
//        CohortRequestDao invalidRequest = CohortRequestDao.builder()
//                .cohortNumber("")
//                .startDate(LocalDate.now())
//                .endDate(LocalDate.now().plusMonths(6))
//                .build();
//
//        mockMvc.perform(post("/api/v1/cohorts")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(invalidRequest)))
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    @WithMockUser(roles = "USER")
//    void createCohort_whenNotAdmin_thenReturnsForbidden() throws Exception {
//        mockMvc.perform(post("/api/v1/cohorts")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(cohortRequestDao)))
//                .andExpect(status().isForbidden());
//    }
//}
