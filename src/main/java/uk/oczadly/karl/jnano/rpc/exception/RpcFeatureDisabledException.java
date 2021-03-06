/*
 * Copyright (c) 2020 Karl Oczadly (karl@oczadly.uk)
 * Licensed under the MIT License
 */

package uk.oczadly.karl.jnano.rpc.exception;

/**
 * Thrown if a requested command requires a feature which is disabled on the node.
 */
public class RpcFeatureDisabledException extends RpcConfigForbiddenException {
    
    public RpcFeatureDisabledException(String nodeMessage) {
        super(nodeMessage);
    }
    
    public RpcFeatureDisabledException(String message, String nodeMessage) {
        super(message, nodeMessage);
    }
    
}
