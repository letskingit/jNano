module uk.oczadly.karl.jnano {
    
    // Models
    exports uk.oczadly.karl.jnano.model;
    exports uk.oczadly.karl.jnano.model.block;
    exports uk.oczadly.karl.jnano.model.block.interfaces;
    exports uk.oczadly.karl.jnano.model.work;
    // Callback
    exports uk.oczadly.karl.jnano.callback;
    // RPC
    exports uk.oczadly.karl.jnano.rpc;
    exports uk.oczadly.karl.jnano.rpc.exception;
    exports uk.oczadly.karl.jnano.rpc.request;
    exports uk.oczadly.karl.jnano.rpc.request.conversion;
    exports uk.oczadly.karl.jnano.rpc.request.node;
    exports uk.oczadly.karl.jnano.rpc.request.wallet;
    exports uk.oczadly.karl.jnano.rpc.response;
    // Utils
    exports uk.oczadly.karl.jnano.util;
    // WebSocket
    exports uk.oczadly.karl.jnano.websocket;
    exports uk.oczadly.karl.jnano.websocket.topic;
    exports uk.oczadly.karl.jnano.websocket.topic.message;
    
    // Dependencies
    requires transitive com.google.gson;
    requires blake2b;
    requires Java.WebSocket;

}