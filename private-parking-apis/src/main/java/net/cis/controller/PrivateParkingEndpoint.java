package net.cis.controller;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.cis.common.util.DateTimeUtil;
import net.cis.common.util.Utils;
import net.cis.common.web.BaseEndpoint;
import net.cis.dto.PrivateParkingDto;
import net.cis.jpa.entity.CustomerBalanceEntity;
import net.cis.jpa.entity.CustomerBalanceLogEntity;
import net.cis.jpa.entity.enums.ParkingTypeEnum;
import net.cis.service.CustomerBalanceLogService;
import net.cis.service.CustomerBalanceService;
import net.cis.service.ParkingService;
import net.cis.utils.Constants;
import net.cis.utils.RestfulUtil;

/**
 * Created by Vincent on 02/10/2018
 */
@RestController
@RequestMapping("/parking")
@Api(value = "parking Endpoint", description = "The URL to handle parking endpoint")
public class PrivateParkingEndpoint extends BaseEndpoint {

	@Autowired
	private ParkingService parkingService;
	
	@Autowired
	private CustomerBalanceLogService balanceLogService;
	
	@Autowired
	private CustomerBalanceService balanceService;
	
	


	@RequestMapping(value = "/create_ticket", method = {RequestMethod.POST, RequestMethod.GET})
	@ApiOperation("Create ticket")
	public String fetchTokens(@RequestParam("numberPlate") String numberPlate, @RequestParam("type") ParkingTypeEnum type) throws Exception {
		PrivateParkingDto parkingDto = parkingService.findByNumberPlate(numberPlate);
		if(parkingDto == null || (parkingDto.getInTime()!= null && parkingDto.getOutTime() != null)) {
			parkingDto = new PrivateParkingDto();
		}
		parkingDto.setNumberPlate(numberPlate);
		parkingDto.setType(type);
		if(type == ParkingTypeEnum.IN) {
			parkingDto.setInTime(DateTimeUtil.formatDate(Calendar.getInstance().getTimeInMillis()));
			parkingDto = parkingService.save(parkingDto);
		}
		if(type == ParkingTypeEnum.OUT) {
			parkingDto.setOutTime(DateTimeUtil.formatDate(Calendar.getInstance().getTimeInMillis()));
			double fee = 25000;
			int cusId = 7425;
			parkingDto = parkingService.save(parkingDto);
			
			if(parkingDto.getNumberPlate().equalsIgnoreCase("29Y8358") 
					|| parkingDto.getNumberPlate().trim().equalsIgnoreCase("29Y-8358")) {
				CustomerBalanceEntity balanceEntity = balanceService.findById(cusId);
				balanceEntity.setBalance(balanceEntity.getBalance()-fee);
				balanceService.save(balanceEntity);
				
				CustomerBalanceLogEntity previousBalanceLogEntity = balanceLogService.findByCustomerId(cusId);
				CustomerBalanceLogEntity balancerLog = new CustomerBalanceLogEntity();
				balancerLog.setCusId(cusId);
				balancerLog.setBeforeBalance(previousBalanceLogEntity.getAfterBalance());
				balancerLog.setAmount(-fee);
				balancerLog.setAfterBalance(balancerLog.getBeforeBalance() - fee);
				balancerLog.setTtype(6);
				balancerLog.setRefDoc(parkingDto.getNumberPlate());
				balancerLog.setRefDesc("private parking ");
				balanceLogService.save(balancerLog);
			} else {
				String orderId = Utils.createRandomString(6);
				String result = RestfulUtil.postTest("https://admapi.iparkingtest.com/p/napas/paywithnon3DS?"
						+ "token=5111111869381118&orderId="+orderId+"&amount=25000&transId="+orderId+"_"+parkingDto.getNumberPlate());
				parkingDto.setPaymentResult(result);
				parkingService.save(parkingDto);
			}
		}
		
		return Constants.THANK_YOU_MESSAGE;
	}
	
	
	
}
