/*
 * Copyright (c) 2020 Karl Oczadly (karl@oczadly.uk)
 * Licensed under the MIT License
 */

package uk.oczadly.karl.jnano.rpc.request.node;

import uk.oczadly.karl.jnano.rpc.request.RpcRequest;
import uk.oczadly.karl.jnano.rpc.response.ResponseNodeId;

/**
 * This request class is used to fetch the node's ID and private key.
 * <br>Calls the RPC command {@code node_id}, and returns a {@link ResponseNodeId} data object.
 *
 * @see <a href="https://docs.nano.org/commands/rpc-protocol/#node_id">Official RPC documentation</a>
 * @deprecated This request is for debugging purposes only and is subject to change with each node revision.
 */
@Deprecated
public class RequestNodeId extends RpcRequest<ResponseNodeId> {
    
    public RequestNodeId() {
        super("node_id", ResponseNodeId.class);
    }
    
}
