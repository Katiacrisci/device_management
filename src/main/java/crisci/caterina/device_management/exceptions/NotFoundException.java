package crisci.caterina.device_management.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Object notFound) {
        super("Can't find entity: " + notFound);
    }
}
