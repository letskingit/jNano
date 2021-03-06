/*
 * Copyright (c) 2020 Karl Oczadly (karl@oczadly.uk)
 * Licensed under the MIT License
 */

package uk.oczadly.karl.jnano.rpc.request.node;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import uk.oczadly.karl.jnano.model.block.Block;
import uk.oczadly.karl.jnano.rpc.request.RpcRequest;
import uk.oczadly.karl.jnano.rpc.response.ResponseSignature;

/**
 * This request class is used to fetch sign a provided block using a specified private key or wallet address.
 * <br>Calls the RPC command {@code sign}, and returns a {@link ResponseSignature} data object.
 *
 * @see <a href="https://docs.nano.org/commands/rpc-protocol/#sign">Official RPC documentation</a>
 */
public class RequestSign extends RpcRequest<ResponseSignature> {
    
    @Expose @SerializedName("json_block")
    private final boolean json = false;
    
    
    @Expose @SerializedName("hash")
    private final String blockHash;
    
    @Expose @SerializedName("block")
    private final String blockJson;
    
    @Expose @SerializedName("key")
    private final String privateKey;
    
    @Expose @SerializedName("wallet")
    private final String walletId;
    
    @Expose @SerializedName("account")
    private final String account;
    
    
    /**
     * Constructs a sign request using a private key.
     * @param blockJson  the block's JSON contents
     * @param privateKey the private key of the account
     */
    public RequestSign(String blockJson, String privateKey) {
        this(null, blockJson, privateKey, null, null);
    }
    
    /**
     * Constructs a sign request using a private key.
     * @param block      the block to sign
     * @param privateKey the private key of the account
     */
    public RequestSign(Block block, String privateKey) {
        this(null, block.toJsonString(), privateKey, null, null);
    }
    
    /**
     * Constructs a sign request using a wallet account.
     * @param blockJson the block's JSON contents
     * @param walletId  the local wallet ID of the account
     * @param account   the local account's address
     */
    public RequestSign(String blockJson, String walletId, String account) {
        this(null, blockJson, null, walletId, account);
    }
    
    /**
     * Constructs a sign request using a wallet account.
     * @param block    the block to sign
     * @param walletId the local wallet ID of the account
     * @param account  the local account's address
     */
    public RequestSign(Block block, String walletId, String account) {
        this(null, block.toJsonString(), null, walletId, account);
    }
    
    /**
     * Signs a block from a given hash.
     * @param blockHash the block's hash
     */
    public RequestSign(String blockHash) { // TODO
        this(blockHash, null, null, null, null);
    }
    
    protected RequestSign(String blockHash, String blockJson, String privateKey, String walletId, String account) {
        super("sign", ResponseSignature.class);
        this.blockHash = blockHash;
        this.blockJson = blockJson;
        this.privateKey = privateKey;
        this.walletId = walletId;
        this.account = account;
    }
    
    
    /**
     * @return the requested block hash
     */
    public String getBlockHash() {
        return blockHash;
    }
    
    /**
     * @return the block's JSON contents
     */
    public String getBlockJson() {
        return blockJson;
    }
    
    /**
     * @return the private key
     */
    public String getPrivateKey() {
        return privateKey;
    }
    
    /**
     * @return the wallet ID
     */
    public String getWalletId() {
        return walletId;
    }
    
    /**
     * @return the account address
     */
    public String getAccount() {
        return account;
    }
    
}
