package in.bigdolph.jnano.rpc.query.response;

import com.google.gson.JsonObject;

public class RPCResponse {
    
    private JsonObject rawJson;
    
    protected RPCResponse() {}
    
    
    public final JsonObject getRawJSON() {
        return this.rawJson;
    }
    
    /** This method should only be called after instantiation */
    public final void init(JsonObject rawJson) {
        if(this.rawJson != null) throw new IllegalStateException("Initial parameters have already been set");
        this.rawJson = rawJson;
    }
    
}
