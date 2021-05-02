package com.db.tradestorage.controller;
/**
 * TRade transmission controller
 * Date Create: 02/05/2021
 * @author Mansi Deshmukh
 *
 */
import com.db.tradestorage.exception.InvalidTradeException;
import com.db.tradestorage.model.Trade;
import com.db.tradestorage.service.TradeService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


 @RestController
public class TradeController {
	Logger logger = LoggerFactory.getLogger(TradeController.class);
    @Autowired
    TradeService tradeService;
    
   
    /**
     * @param trade
     * @return response as custom message
     */
    @PostMapping("/trade/save")
    public ResponseEntity<String> tradeValidateStore(@RequestBody Trade trade){
    	logger.info("IN tradeValidateStore :TradeController");
    	System.out.println(trade);
       if(tradeService.isValid(trade)) {
    	   
           tradeService.persist(trade);
     }else{
          // return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
           throw new InvalidTradeException(trade.getTradeId()+"  Trade Id is not found");
       }
        return ResponseEntity.status(HttpStatus.OK).body("Trade saved successfully ");
    
    }

    /**
     * @return all rows 
     */
    @GetMapping("/trade")
    public List<Trade> findAllTrades(){
    	logger.info("IN findAllTrades :TradeController");
    	List<Trade> tradeList = new ArrayList<Trade>();
    	 tradeList=tradeService.findAll();
    	 if(tradeList.isEmpty())
    	 {
    		 throw new RuntimeException( "NO records Found!!! Please insert the records First");
    	 }
    	 
    	 return tradeList;
    }
}
