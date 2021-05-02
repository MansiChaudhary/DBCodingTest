package com.db.tradestorage.service;
/**
 * Trade transmission service class
 * Date Create: 02/05/2021
 * @author Mansi Deshmukh
 *
 */
import com.db.tradestorage.dao.TradeDao;
import com.db.tradestorage.dao.TradeRepository;
import com.db.tradestorage.exception.InvalidTradeException;
import com.db.tradestorage.model.Trade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class TradeService {

	Logger logger = LoggerFactory.getLogger(TradeService.class);

    @Autowired
    TradeDao tradeDao;

    @Autowired
    TradeRepository tradeRepository;

    /**
     * @param trade
     * @return true or false 
     */
    public boolean isValid(Trade trade){
    	logger.info("in isValid : TradeService");
    	
        if(validateMaturityDate(trade)) {
        	
            // Trade exsitingTrade = tradeDao.findTrade(trade.getTradeId());
            Optional<Trade> exsitingTrade = tradeRepository.findById(trade.getTradeId());
             if (exsitingTrade.isPresent()) {
                 return validateVersion(trade, exsitingTrade.get());
             }else{
                 return true;
             }
         }
         return false;
    }

    /**
     * @param trade
     * @param oldTrade
     * @return
     */
    private boolean validateVersion(Trade trade,Trade oldTrade) {
    	logger.info("in isValid : validateVersion :TradeService");
      
        // lower version is being received by the store it will reject the trade and throw an exception.
        if(trade.getVersion() >= oldTrade.getVersion()){
        	return true;
            
        }else
        {
        	throw new InvalidTradeException(trade.getTradeId()+"  Trade Version is lower than the received version");
        }
       
        
    }

    //2.	Store should not allow the trade which has less maturity date then today date
    /**
     * @param trade
     * @return boolean rue or false
     */
    private boolean validateMaturityDate(Trade trade){
    	logger.info("in isValid : validateMaturityDate : TradeService");
         if(trade.getMaturityDate().isBefore(LocalDate.now()) ) 
         {
        	 throw new InvalidTradeException(trade.getTradeId()+"  Trade MAturity Date is less than current Date");
         }
         else {
        	 
         return true;
         } 
    }

    public void  persist(Trade trade){
    	logger.info("in persist : TradeService");
        trade.setCreatedDate(LocalDate.now());
        tradeRepository.save(trade);
    }

    public List<Trade> findAll(){
    	logger.info("in findAll : TradeService");
       return  tradeRepository.findAll();
        //return tradeDao.findAll();
    }

    public void updateExpiryFlagOfTrade(){
    	logger.info("in updateExpiryFlagOfTrade : TradeService");
      
        tradeRepository.findAll().stream().forEach(t -> {
                if (!validateMaturityDate(t)) {
                    t.setExpiredFlag("Y");
                    logger.info("Trade which needs to updated {}", t);
                    tradeRepository.save(t);
                }
            });
        }


}
