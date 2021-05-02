package com.db.tradestorage.dao;
/**
 * Trade transmission repository
 * Date Create: 02/05/2021
 * @author Mansi Deshmukh
 *
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.db.tradestorage.model.Trade;

@Repository
public interface TradeRepository extends JpaRepository<Trade,String> {
}
