package com.db.tradestorage.exception;
/**
 * Trade transmission Custome Exception class
 * Date Create: 02/05/2021
 * @author Mansi Deshmukh
 *
 */
public class InvalidTradeException extends RuntimeException {

    private final String id;

    public InvalidTradeException(final String id) {
        super("Invalid Trade: " + id);
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
