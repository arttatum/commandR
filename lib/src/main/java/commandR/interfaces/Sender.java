package commandR.interfaces;

public interface Sender {
    <TRequest extends Request<TResponse>, TResponse> TResponse send(TRequest request);
}
