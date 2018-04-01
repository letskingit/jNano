package in.bigdolph.jnano.rpc.query.exception;

/**
 * Thrown if one of the given request arguments are not valid.
 */
public class RpcInvalidArgumentException extends RpcQueryException {
    
    public RpcInvalidArgumentException(String message) {
        super(message);
    }
    
}