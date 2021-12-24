package commandR.implementations;

import commandR.interfaces.Mediator;
import commandR.interfaces.Registry;
import commandR.interfaces.RequestHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

class MediatorTest extends TestData {
    private Mediator mediator;

    @BeforeEach
    void setUp() {
        ArrayList<RequestHandler<?,?>> list = new ArrayList<>(){};
        list.add(new CapitalizeHandler());
        list.add(new RemoveVowelsHandler());
        list.add(new IncrementCounterHandler());
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

    @Test
    void send_concurrent_requests_handled_in_parallel() throws InterruptedException {
        int numberOfThreads = 1000;
        int lengthOfPauseInMs = 100;

        ExecutorService pool = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);
        MyCounter counter = new MyCounter();
        long start = System.currentTimeMillis();
        for (int i = 0; i < numberOfThreads; i++) {
            pool.execute(() -> {
                mediator.send(new IncrementCounter(counter, lengthOfPauseInMs));
                latch.countDown();
            });
        }
        latch.await();
        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;

        assertEquals (numberOfThreads, counter.getCount());
        assert (lengthOfPauseInMs * numberOfThreads > timeElapsed);
    }
}
