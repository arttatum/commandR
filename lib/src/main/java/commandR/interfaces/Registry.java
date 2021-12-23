package commandR.interfaces;

public interface Registry {
    <TRequest extends Request<TResponse>, TResponse> RequestHandler<TRequest, TResponse> getHandler(TRequest request);
    Registry withHandlers(Iterable<? extends RequestHandler<?,?>> handlers);
}
