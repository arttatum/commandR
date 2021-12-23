package commandR.implementations;

import commandR.interfaces.Request;
import commandR.interfaces.RequestHandler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;

public class Registry implements commandR.interfaces.Registry {

    HashMap<String, RequestHandler<?,?>> registry = new HashMap<>();

    @Override
    @SuppressWarnings("unchecked")
    public <TRequest extends Request<TResponse>, TResponse> RequestHandler<TRequest, TResponse> getHandler(TRequest request) {
        return (RequestHandler<TRequest, TResponse>) registry.get(request.getClass().getTypeName());
    }

    @Override
    public Registry withHandlers(Iterable<? extends RequestHandler<?,?>> handlers) {
        for (RequestHandler<?,?> handler : handlers){
            Type[] genericInterfaces = handler.getClass().getGenericInterfaces();
            for (Type genericInterface : genericInterfaces) {
                if (genericInterface instanceof ParameterizedType) {
                    Type[] genericTypes = ((ParameterizedType) genericInterface).getActualTypeArguments();
                    registry.putIfAbsent(genericTypes[0].getTypeName(), handler);
                }
            }
        }
        return this;
    }
}
