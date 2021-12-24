package commandR.interfaces;

/**
 * A RequestHandler object. Provides the logic for handling a Request.
 * This is where the necessary services (repositories etc.) are injected.
 * @param <TRequest> The Type of the Request.
 * @param <TResponse> The response Type for this Request (can be NullType).
 */
public interface RequestHandler<TRequest extends Request<TResponse>, TResponse> {
    /**
     * The method that defines how to handle a given Request
     * @param request The Request to handle.
     * @return The response to the Request.
     */
    TResponse handle(TRequest request);
}
