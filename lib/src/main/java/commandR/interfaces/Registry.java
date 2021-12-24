package commandR.interfaces;

public interface Registry {
    /**
     * Gets the RequestHandler associated with the given Request.
     * @param request The Request for which a RequestHandler will be provided.
     * @param <TRequest>> The Type of the Request.
     * @param <TResponse> The Type of the response.
     * @return The appropriate RequestHandler
     */
    <TRequest extends Request<TResponse>, TResponse> RequestHandler<TRequest, TResponse> getHandler(TRequest request);

    /**
     * Populates the register with mappings from Requests to RequestHandlers, using
     * the Request object's Type name as a key.
     * This allows the mediator to quickly select the appropriate handler for each request
     * at run time.
     * Note: Request Types (and names) are inferred from the Generic argument for each RequestHandler.
     * @param handlers The RequestHandlers to register in the system.
     * @return The registry class
     */
    Registry withHandlers(Iterable<? extends RequestHandler<?,?>> handlers);
}
