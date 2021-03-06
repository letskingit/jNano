/*
 * Copyright (c) 2020 Karl Oczadly (karl@oczadly.uk)
 * Licensed under the MIT License
 */

package uk.oczadly.karl.jnano.rpc.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import uk.oczadly.karl.jnano.model.NanoAmount;


/**
 * This response class contains summary information about a single wallet.
 */
public class ResponseWalletInfo extends RpcResponse {
    
    @Expose @SerializedName("balance")
    private NanoAmount balance;
    
    @Expose @SerializedName("pending")
    private NanoAmount pendingBalance;
    
    @Expose @SerializedName("accounts_count")
    private int accountsCount;
    
    @Expose @SerializedName("adhoc_count")
    private int adhocCount;
    
    @Expose @SerializedName("deterministic_count")
    private int deterministicAccountCount;
    
    @Expose @SerializedName("deterministic_index")
    private int currentDeterministicIndex;
    
    
    /**
     * @return the total balance of all accounts
     */
    public NanoAmount getBalance() {
        return balance;
    }
    
    /**
     * @return the total pending balance of all accounts
     */
    public NanoAmount getPendingBalance() {
        return pendingBalance;
    }
    
    /**
     * @return the number of accounts created
     */
    public int getAccountsCount() {
        return accountsCount;
    }
    
    /**
     * @return the number of accounts created ad-hoc (from a private key)
     */
    public int getAdhocCount() {
        return adhocCount;
    }
    
    /**
     * @return the number of accounts created deterministically (from the wallet's seed)
     */
    public int getDeterministicAccountCount() {
        return deterministicAccountCount;
    }
    
    /**
     * @return the current index for deterministically creating accounts
     */
    public int getCurrentDeterministicIndex() {
        return currentDeterministicIndex;
    }
    
}
