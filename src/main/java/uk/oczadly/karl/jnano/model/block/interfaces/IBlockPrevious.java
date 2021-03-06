/*
 * Copyright (c) 2020 Karl Oczadly (karl@oczadly.uk)
 * Licensed under the MIT License
 */

package uk.oczadly.karl.jnano.model.block.interfaces;

/**
 * This interface is to be implemented by blocks which contain a previous block hash.
 */
public interface IBlockPrevious extends IBlock {
    
    /**
     * Returns the hash of the previous block in the account's chain. For the first block in an account, this value
     * may return null or an empty string of zeroes.
     * @return the previous block hash
     */
    String getPreviousBlockHash();

}
