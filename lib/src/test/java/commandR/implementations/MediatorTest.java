package commandR.implementations;

import commandR.interfaces.Mediator;
import commandR.interfaces.Registry;
import commandR.interfaces.RequestHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MediatorTest extends TestData {
    private Mediator mediator;

    @BeforeEach
    void setUp() {
        ArrayList<RequestHandler<?,?>> list = new ArrayList<>(){};
        list.add(new CapitalizeHandler());
        list.add(new RemoveVowelsHandler());
        Registry registry = new commandR.implementations.Registry().withHandlers(list);
        mediator = new commandR.implementations.Mediator(registry);
    }

    @Test
    void send() {
        String capitalizeResponse = mediator.send(new Capitalize("Hello"));
        assertEquals("HELLO", capitalizeResponse);

        String removeVowelsResponse = mediator.send(new RemoveVowels("Hello"));
        assertEquals(removeVowelsResponse, "Hll");
    }
}