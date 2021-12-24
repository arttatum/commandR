package commandR.interfaces;

public interface Sender {
    /**
     * Sends the request into the mediator, which determines how to proceed.
     * @param request The Request object to send.
     * @param <TRequest> The Type of the Request.
     * @param <TResponse> The response Type for this Request (can be NullType).
     * @return The response.
     */
    <TRequest extends Request<TResponse>, TResponse> TResponse send(TRequest request);
}
