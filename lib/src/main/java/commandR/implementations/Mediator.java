package commandR.implementations;

import commandR.interfaces.Registry;
import commandR.interfaces.Request;
import commandR.interfaces.RequestHandler;

public class Mediator implements commandR.interfaces.Mediator {
    private final Registry registry;

    public Mediator(Registry registry) {
        this.registry = registry;
    }

    @Override
    public <TRequest extends Request<TResponse>, TResponse> TResponse send(TRequest TRequest) {
        RequestHandler<TRequest, TResponse> handler = registry.getHandler(TRequest);
        return handler.handle(TRequest);
    }
}


