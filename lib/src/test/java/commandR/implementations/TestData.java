package commandR.implementations;

import commandR.interfaces.Request;
import commandR.interfaces.RequestHandler;

import javax.lang.model.type.NullType;
import java.util.Locale;

public class TestData {
    protected static class Capitalize implements Request<String>{
        private final String message;

        public Capitalize(String message) {
            this.message = message;
        }
    }

    protected static class CapitalizeHandler implements RequestHandler<Capitalize, String> {
        @Override
        public String handle(Capitalize request) {
            return request.message.toUpperCase(Locale.ROOT);
        }
    }

    protected static class RemoveVowels implements Request<String> {
        private final String message;

        public RemoveVowels(String message) {
            this.message = message;
        }
    }

    protected static class RemoveVowelsHandler implements RequestHandler<RemoveVowels, String> {
        @Override
        public String handle(RemoveVowels request) {
            return request.message.replaceAll("[aeiouAEIOU]", "");
        }
    }

    protected class IncrementCounter implements Request<NullType> {
        private final MyCounter counter;
        private final int lengthOfPauseInMs;

        public IncrementCounter(MyCounter counter, int lengthOfPauseInMs) {
            this.counter = counter;
            this.lengthOfPauseInMs = lengthOfPauseInMs;
        }
    }

    protected class IncrementCounterHandler implements RequestHandler<IncrementCounter, NullType> {
        @Override
        public NullType handle(IncrementCounter request) {
            try {
                request.counter.increment();
                Thread.sleep(request.lengthOfPauseInMs);
            } catch (InterruptedException ex) {
                System.err.println(ex);
            }
            return null;
        }
    }

    protected static class MyCounter {
        private int count;
        protected synchronized void increment() throws InterruptedException {
            int temp = count;
            count = temp + 1;
        }

        protected int getCount() {
            return count;
        }
    }
}
