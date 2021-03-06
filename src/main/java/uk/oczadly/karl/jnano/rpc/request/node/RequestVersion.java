/*
 * Copyright (c) 2020 Karl Oczadly (karl@oczadly.uk)
 * Licensed under the MIT License
 */

package uk.oczadly.karl.jnano.rpc.request.node;

import uk.oczadly.karl.jnano.rpc.request.RpcRequest;
import uk.oczadly.karl.jnano.rpc.response.ResponseVersion;

/**
 * This request class is used to retrieve the node's version information.
 * <br>Calls the RPC command {@code version}, and returns a {@link ResponseVersion} data object.
 *
 * @see <a href="https://docs.nano.org/commands/rpc-protocol/#version">Official RPC documentation</a>
 */
public class RequestVersion extends RpcRequest<ResponseVersion> {
    
    public RequestVersion() {
        super("version", ResponseVersion.class);
    }
    
}
