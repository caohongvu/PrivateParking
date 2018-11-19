package net.cis.service;

import java.util.List;

import net.cis.dto.PrivateParkingDto;

/**
 * Created by Vincent 23/05/2018
 */
public interface ParkingService {

    PrivateParkingDto save(PrivateParkingDto parkingDto);

    void delete(PrivateParkingDto parkingDto);
    
    PrivateParkingDto findByNumberPlate(String numberPlate);
    
    List<PrivateParkingDto> findAll();

}
 