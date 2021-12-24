package commandR.implementations;

import commandR.interfaces.Registry;
import commandR.interfaces.RequestHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class RegistryTest extends TestData {

    private Registry registry;

    @BeforeEach
    void setUp() {
        ArrayList<RequestHandler<?,?>> list = new ArrayList<>(){};
        list.add(new CapitalizeHandler());
        this.registry = new commandR.implementations.Registry().withHandlers(list);
    }

    @Test
    void getHandler() {
        RequestHandler<Capitalize, String> handler = registry.getHandler(new Capitalize("Hi"));
        assert (handler instanceof CapitalizeHandler);
    }
}