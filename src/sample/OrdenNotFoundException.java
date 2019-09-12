package sample;


public class OrdenNotFoundException extends RuntimeException {

    OrdenNotFoundException(Long id) {
        super("Could not find a Pumakatari " + id);
    }
}