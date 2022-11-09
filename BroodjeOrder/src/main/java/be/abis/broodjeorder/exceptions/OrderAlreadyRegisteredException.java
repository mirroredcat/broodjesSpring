package be.abis.broodjeorder.exceptions;

public class OrderAlreadyRegisteredException extends Exception {
    public OrderAlreadyRegisteredException(String message) {
        super(message);
    }
}
