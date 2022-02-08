package com.example.restapi.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.restapi.api.v1.mapper.VendorMapper;
import com.example.restapi.api.v1.model.VendorDTO;
import com.example.restapi.domain.Vendor;
import com.example.restapi.repositories.VendorRepository;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class VendorServiceTest {

	private VendorService vendorService;

	@Mock
	private VendorRepository vendorRepository;

	@BeforeAll
	public void setUp() throws Exception {

		MockitoAnnotations.openMocks(this);
		vendorService = new VendorServiceImpl(vendorRepository, VendorMapper.INSTANCE);
	}

	@Test
	void getVendors() {

		List<Vendor> vendorList = new ArrayList<>();

		// Given
		Vendor vendorOne = new Vendor();
		Vendor vendorTwo = new Vendor();

		vendorList.add(vendorOne);
		vendorList.add(vendorTwo);

		// When
		when(vendorRepository.findAll()).thenReturn(vendorList);

		List<VendorDTO> vendorDTOList = vendorService.getVendors();

		// Then
		assertEquals(vendorDTOList.size(), 2);
	}

	@Test
	void getVendorById() {

		// Given
		Vendor vendor = new Vendor();
		vendor.setVendorId(1L);
		vendor.setVendorName("Fruits");

		// When
		when(vendorRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(vendor));

		VendorDTO vendorDTO = vendorService.getVendorById(1L);

		// Then
		assertEquals(vendorDTO.getVendorName(), vendor.getVendorName());
	}

	@Test
	void createVendor() {

		// Given
		VendorDTO vendorDTO = new VendorDTO();

		vendorDTO.setVendorName("Shops");

		Vendor vendor = new Vendor();
		vendor.setVendorName(vendorDTO.getVendorName());
		vendor.setVendorId(1L);

		when(vendorRepository.save(any(Vendor.class))).thenReturn(vendor);

		// When
		VendorDTO saveVendor = vendorService.createVendor(vendorDTO);

		// Then
		assertEquals(saveVendor.getVendorName(), vendorDTO.getVendorName());
		assertEquals("/api/v1/vendors/1", saveVendor.getVendorUrl());

	}

	@Test
	void updateVendor() {

		// Given
		VendorDTO vendorDTO = new VendorDTO();
		vendorDTO.setVendorName("Fruits");

		Vendor vendor = new Vendor();
		vendor.setVendorName(vendorDTO.getVendorName());
		vendor.setVendorId(1L);

		when(vendorRepository.save(any(Vendor.class))).thenReturn(vendor);

		// When
		VendorDTO returnVendorDto = vendorService.updateVendor(vendor.getVendorId(), vendorDTO);

		// Then
		assertEquals(returnVendorDto.getVendorName(), vendorDTO.getVendorName());
		assertEquals(returnVendorDto.getVendorUrl(), "/api/v1/vendors/1");
	}

	@Test
	void deleteVendorById() {

		Long vendorId = 1L;

		vendorRepository.deleteById(vendorId);

		verify(vendorRepository, times(1)).deleteById(anyLong());
	}
}