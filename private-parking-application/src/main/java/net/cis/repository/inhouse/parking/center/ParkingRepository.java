package net.cis.repository.inhouse.parking.center;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.cis.jpa.entity.PrivateParkingEntity;

/**
 * Created by Vincent 02/10/2018
 */

public interface ParkingRepository  extends JpaRepository<PrivateParkingEntity, Long> {
	
	@Query(value="select * from sdb1.tickets where number_plate = :numberPlate order by id DESC limit 0,1", nativeQuery=true)
	PrivateParkingEntity findByNumberPlate(@Param(value="numberPlate") String numberPlate);

}
