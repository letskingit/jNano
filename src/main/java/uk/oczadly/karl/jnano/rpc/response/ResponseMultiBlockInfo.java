/*
 * Copyright (c) 2020 Karl Oczadly (karl@oczadly.uk)
 * Licensed under the MIT License
 */

package uk.oczadly.karl.jnano.rpc.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import uk.oczadly.karl.jnano.model.NanoAccount;
import uk.oczadly.karl.jnano.model.NanoAmount;
import uk.oczadly.karl.jnano.model.block.Block;

import java.util.Map;

/**
 * This response class contains a set of block information.
 */
public class ResponseMultiBlockInfo extends RpcResponse {
    
    @Expose @SerializedName("blocks")
    private Map<NanoAccount, BlockInfo> blocks;
    
    
    /**
     * Map follows the structure {@code block hash -> block information}.
     *
     * @return a map of block hashes and information, or null if not present in the response
     */
    public Map<NanoAccount, BlockInfo> getBlocks() {
        return blocks;
    }
    
    
    public static class BlockInfo {
        @Expose @SerializedName("block_account")
        private NanoAccount account;
        
        @Expose @SerializedName("amount")
        private NanoAmount amount;
        
        @Expose @SerializedName("balance")
        private NanoAmount balance;
        
        @Expose @SerializedName("height")
        private long height;
        
        @Expose @SerializedName("local_timestamp")
        private long timestamp;
        
        @Expose @SerializedName("confirmed")
        private boolean confirmed;
        
        @Expose @SerializedName("contents")
        private Block blockContents;
        
        @Expose @SerializedName("subtype")
        private String subtype;
        
        
        /**
         * @return the account who created this block
         */
        public NanoAccount getAccount() {
            return account;
        }
        
        /**
         * @return the transactional amount associated with this block in RAW
         */
        public NanoAmount getAmount() {
            return amount;
        }
        
        /**
         * @return the final balance after executing this block
         */
        public NanoAmount getBalance() {
            return balance;
        }
        
        /**
         * @return the height of this block in the source account chain
         */
        public long getHeight() {
            return height;
        }
        
        /**
         * @return the local UNIX timestamp when this block was processed
         */
        public long getLocalTimestamp() {
            return timestamp;
        }
        
        /**
         * @return whether the block is confirmed
         */
        public boolean isConfirmed() {
            return confirmed;
        }
        
        /**
         * @return the contents of the block
         */
        public Block getBlockContents() {
            return blockContents;
        }
        
        /**
         * @return the subtype of the block
         */
        public String getSubtype() {
            return subtype;
        }
    }
    
}
