package com.example.restapi.controllers.v1;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.restapi.api.v1.model.VendorDTO;
import com.example.restapi.services.VendorService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = {VendorController.class})
class VendorControllerTest {

    @MockBean
    VendorService vendorService;

    @Autowired
    MockMvc mockMvc;

    private VendorDTO vendorDTOOne;
    private VendorDTO vendorDTOTwo;

    @BeforeAll
    public void setUp() throws Exception {

        vendorDTOOne = new VendorDTO("VendorOne", VendorController.BASE_URL + "/1");
        vendorDTOTwo = new VendorDTO("VendorTwo", VendorController.BASE_URL + "/2");
    }

    @Test
    void getVendors() throws Exception {

        // Given
        List<VendorDTO> vendorDTOList = new ArrayList<VendorDTO>();
        vendorDTOList.add(vendorDTOOne);
        vendorDTOList.add(vendorDTOTwo);

        // When
        when(vendorService.getVendors()).thenReturn(vendorDTOList);

        // Then
        mockMvc.perform(get(VendorController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendors", hasSize(2)));

    }

    @Test
    void getVendorById() throws Exception {

        // Given
        vendorDTOOne.setVendorName("Fruits");

        // When
        when(vendorService.getVendorById(anyLong())).thenReturn(vendorDTOOne);

        // Then
        mockMvc.perform(get(VendorController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendorName", equalTo("Fruits")));
    }

    @Test
    void createVendor() throws Exception {

        given(vendorService.createVendor(vendorDTOOne)).willReturn(vendorDTOOne);

        mockMvc.perform(post(VendorController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(vendorDTOOne)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.vendorName", equalTo(vendorDTOOne.getVendorName())));


    }

    @Test
    void updateVendor() throws Exception {

        given(vendorService.updateVendor(anyLong(),any(VendorDTO.class))).willReturn(vendorDTOOne);

        mockMvc.perform(put(VendorController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(vendorDTOOne)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendorName", equalTo(vendorDTOOne.getVendorName())));

    }

    @Test
    public void patchVendor() throws  Exception{

        given(vendorService.patchVendor(anyLong(),any(VendorDTO.class))).willReturn(vendorDTOOne);

        mockMvc.perform(patch(VendorController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(vendorDTOOne)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendorName", equalTo(vendorDTOOne.getVendorName())));

    }

    @Test
    void deleteVendor() throws Exception {

        mockMvc.perform(delete(VendorController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        then(vendorService).should().deleteVendorById(anyLong());
    }
}