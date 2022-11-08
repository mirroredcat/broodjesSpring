package be.abis.broodjeorder.exceptions;

public class OrderAlreadyExistsException extends Exception{
    public OrderAlreadyExistsException(String message) {
        super(message);
    }
}
