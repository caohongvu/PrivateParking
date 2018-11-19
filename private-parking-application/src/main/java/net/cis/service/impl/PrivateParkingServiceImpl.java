package net.cis.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.cis.dto.PrivateParkingDto;
import net.cis.jpa.entity.PrivateParkingEntity;
import net.cis.repository.inhouse.parking.center.ParkingRepository;
import net.cis.service.ParkingService;

/**
 * Created by Vincent on 02/10/2018
 */

@Service
public class PrivateParkingServiceImpl implements ParkingService {

	@Autowired
	private ParkingRepository parkingRepository;

	ModelMapper mapper;

	private List<PrivateParkingDto> map(List<PrivateParkingEntity> source) {

		ArrayList<PrivateParkingDto> rtn = new ArrayList<>();
		source.stream().map((entity) -> {
			PrivateParkingDto dto = new PrivateParkingDto();
			mapper.map(entity, dto);
			return dto;
		}).forEachOrdered((dto) -> {
			rtn.add(dto);
		});
		return rtn;
	}

	@PostConstruct
	public void initialize() {
		mapper = new ModelMapper();
	}

	@Override
	public PrivateParkingDto save(PrivateParkingDto parkingDto) {
		PrivateParkingEntity entity = new PrivateParkingEntity();
		mapper.map(parkingDto, entity);
		entity = parkingRepository.save(entity);
		mapper.map(entity, parkingDto);
		return parkingDto;
	}

	@Override
	public void delete(PrivateParkingDto parkingDto) {
		PrivateParkingEntity entity = new PrivateParkingEntity();
		mapper.map(parkingDto, entity);
		parkingRepository.delete(entity);

	}

	@Override
	public PrivateParkingDto findByNumberPlate(String numberPlate) {
		PrivateParkingEntity entities = parkingRepository.findByNumberPlate(numberPlate);
		if (entities == null) {
			return null;
		}
		PrivateParkingDto dto = new PrivateParkingDto();
		mapper.map(entities, dto);
		return dto;
	}

	@Override
	public List<PrivateParkingDto> findAll() {
		List<PrivateParkingEntity> entities = parkingRepository.findAll();

		return this.map(entities);
	}

}
