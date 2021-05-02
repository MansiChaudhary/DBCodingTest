
package com.db.tradestorage.schedulingtasks;
/**
 * Trade transmission Sceduler class
 * Date Create: 02/05/2021
 * @author Mansi Deshmukh
 *
 */
import com.db.tradestorage.service.TradeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class TradeScheduledTasks {

	private static final Logger log = LoggerFactory.getLogger(TradeScheduledTasks.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Autowired
	TradeService tradeService;
/*
 * Scheduler for updating the flag after date check automatically
 */
	@Scheduled(cron = "${trade.expiry.schedule}")//currentlly setup for the day as we would need to update the flag automatically 
	public void reportCurrentDate() {
		log.info("The Date is now {}", dateFormat.format(new Date()));
		tradeService.updateExpiryFlagOfTrade();
	}
}