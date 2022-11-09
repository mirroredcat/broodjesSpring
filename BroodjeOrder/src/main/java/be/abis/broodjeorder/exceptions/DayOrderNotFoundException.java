package be.abis.broodjeorder.exceptions;

import java.util.function.Supplier;

public class DayOrderNotFoundException extends Exception {
    public DayOrderNotFoundException(String message) {
        super(message);
    }
}
