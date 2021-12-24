package commandR.interfaces;

/**
 * A Request object. It must have a single RequestHandler associated with it,
 * otherwise the mediator will be unable to process it.
 * @param <TResponse> The response Type for this Request (can be NullType).
 */
public interface Request<TResponse> { }
