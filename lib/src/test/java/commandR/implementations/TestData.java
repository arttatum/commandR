package commandR.implementations;

import commandR.interfaces.Request;
import commandR.interfaces.RequestHandler;

import java.util.Locale;

public class TestData {
    protected static class Capitalize implements Request<String> {
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
}
