/*
 * Copyright (c) 2020 Karl Oczadly (karl@oczadly.uk)
 * Licensed under the MIT License
 */

package uk.oczadly.karl.jnano.rpc;

import java.io.IOException;
import java.net.URL;

/**
 * Classes which implement this interface are responsible for submitting requests and retrieving the responses from an
 * external Nano node.
 */
public interface RpcRequestExecutor {
    
    /**
     * Submits a raw RPC request to the specified external node address.
     * @param address   the network address of the local or external node
     * @param request   the raw request data
     * @param timeout   the timeout value in milliseconds, or 0 for infinite
     * @return the raw string response
     * @throws IOException if an exception occurs with the remote connection
     */
    String submit(URL address, String request, int timeout) throws IOException;
    
}
