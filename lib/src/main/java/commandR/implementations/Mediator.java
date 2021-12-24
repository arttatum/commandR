package commandR.implementations;

import commandR.interfaces.Registry;
import commandR.interfaces.Request;
import commandR.interfaces.RequestHandler;

public class Mediator implements commandR.interfaces.Mediator {
    private final Registry registry;

    public Mediator(Registry registry) {
        Guard.AgainstNull(registry);
        this.registry = registry;
    }

    @Override
    public <TRequest extends Request<TResponse>, TResponse> TResponse send(TRequest request) {
        Guard.AgainstNull(request);
        RequestHandler<TRequest, TResponse> handler = registry.getHandler(request);
        Guard.AgainstNull(handler);
        return handler.handle(request);
    }
}


