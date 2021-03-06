/*
 * Copyright (c) 2020 Karl Oczadly (karl@oczadly.uk)
 * Licensed under the MIT License
 */

package uk.oczadly.karl.jnano.model.block;

/**
 * Represents a set of actions performed by a specific block.
 */
public class BlockIntent {
    
    /**
     * Represents a BlockIntent object with all unknown values. For use with blocks that have no intent implementation.
     */
    public static final BlockIntent ALL_UNKNOWN = new BlockIntent(UncertainBool.UNKNOWN, UncertainBool.UNKNOWN,
            UncertainBool.UNKNOWN, UncertainBool.UNKNOWN, UncertainBool.UNKNOWN, UncertainBool.UNKNOWN);
    
    
    private final UncertainBool isSend, isReceive, isChange, isOpen, isEpoch, isGenesis;
    
    /**
     * @param isSend    if the block sends funds
     * @param isReceive if the block receives funds
     * @param isChange  if the block changes the representative (true if isOpen is true)
     * @param isOpen    if this is the first block in the account
     * @param isEpoch   if this block is an epoch marker block
     * @param isGenesis if this block is the genesis block for the network
     */
    public BlockIntent(Boolean isSend, Boolean isReceive, Boolean isChange, Boolean isOpen, Boolean isEpoch,
                       Boolean isGenesis) {
        this(UncertainBool.valueOf(isSend), UncertainBool.valueOf(isReceive), UncertainBool.valueOf(isChange),
                UncertainBool.valueOf(isOpen), UncertainBool.valueOf(isEpoch), UncertainBool.valueOf(isGenesis));
    }
    
    /**
     * @param isSend    if the block sends funds
     * @param isReceive if the block receives funds
     * @param isChange  if the block changes the representative
     * @param isOpen    if this is the first block in the account
     * @param isEpoch   if this block is an epoch marker block
     * @param isGenesis if this block is the genesis block for the network
     */
    public BlockIntent(UncertainBool isSend, UncertainBool isReceive, UncertainBool isChange, UncertainBool isOpen,
                       UncertainBool isEpoch, UncertainBool isGenesis) {
        this.isSend = UncertainBool.notNull(isSend);
        this.isReceive = UncertainBool.notNull(isReceive);
        this.isOpen = UncertainBool.notNull(isOpen);
        this.isChange = UncertainBool.notNull(isChange);
        this.isEpoch = UncertainBool.notNull(isEpoch);
        this.isGenesis = UncertainBool.notNull(isGenesis);
        
    }
    
    
    /**
     * @return true if the block sent funds
     */
    public UncertainBool isSendFunds() {
        return isSend;
    }
    
    /**
     * @return true if the block received funds
     */
    public UncertainBool isReceiveFunds() {
        return isReceive;
    }
    
    /**
     * @return true if the block changed the representative, or if the block is an open block
     */
    public UncertainBool isChangeRep() {
        return isChange;
    }
    
    /**
     * @return true if this is the opening block for the account
     */
    public UncertainBool isFirstBlock() {
        return isOpen;
    }
    
    /**
     * @return true if this block is an account upgrade block
     */
    public UncertainBool isEpochUpgrade() {
        return isEpoch;
    }
    
    /**
     * @return true if this block is the genesis block for the network
     */
    public UncertainBool isGenesis() {
        return isGenesis;
    }
    
    /**
     * Returns whether this block has a transactional function. Note that this is not mututally exclusive to
     * {@link #isSpecial()} returning true.
     * @return true if this block has a transactional function
     */
    public UncertainBool isTransactional() {
        return UncertainBool.anyOf(isSend, isReceive);
    }
    
    /**
     * Returns whether this block has a special (non-transactional) function. Note that this is not mututally
     * exclusive to {@link #isTransactional()} returning true.
     * @return true if this block has a non-transactional function
     */
    public UncertainBool isSpecial() {
        return UncertainBool.anyOf(isEpoch, isChange, isGenesis);
    }
    
    /**
     * Returns whether this block has a functional purpose (eg. transacting funds, modifying an account parameter).
     * @return true if this block has a function
     */
    public UncertainBool hasPurpose() {
        return UncertainBool.anyOf(isTransactional(), isSpecial());
    }
    
    
    @Override
    public String toString() {
        return "BlockIntent{" +
                "isSendFunds=" + isSendFunds() +
                ", isReceiveFunds=" + isReceiveFunds() +
                ", isChangeRep=" + isChangeRep() +
                ", isFirstBlock=" + isFirstBlock() +
                ", isEpochUpgrade=" + isEpochUpgrade() +
                ", isGenesis=" + isGenesis() +
                ", isTransactional=" + isTransactional() +
                ", isSpecial=" + isSpecial() +
                ", hasPurpose=" + hasPurpose() + '}';
    }
    
    
    /**
     * Represents a boolean state value, with the addition of the {@link #UNKNOWN} value in cases of uncertainty. In
     * most cases, calling {@link #bool()} on the value will suffice.
     */
    public enum UncertainBool {
        /** Equivalent to {@code true}. */
        TRUE,
        /** Equivalent to {@code false}. */
        FALSE,
        /** If a value is unknown or uncertain from the provided context. Treat this value as "it's a possibility". */
        UNKNOWN;
        
    
        /**
         * @return true if this == {@link #TRUE}, or false if {@link #FALSE} or {@link #UNKNOWN}
         */
        public boolean bool() {
            return this == TRUE;
        }
    
        /**
         * @param bool the boolean to convert from
         * @return {@link #TRUE} if true, {@link #FALSE} if false, {@link #UNKNOWN} if null
         */
        public static UncertainBool valueOf(java.lang.Boolean bool) {
            if (bool == null) return UNKNOWN;
            return bool ? TRUE : FALSE;
        }
    
        /**
         * Returns {@link #TRUE} whenever {@code bool} is true, {@link #FALSE} when {@code bool} is false and
         * {@code isKnown} is true, and {@link #UNKNOWN} when {@code bool} is false and {@code isKnown} is false.
         * @param isKnown if the value is known
         * @param bool    the value to convert
         * @return the enum value
         */
        public static UncertainBool ifKnown(boolean isKnown, boolean bool) {
            return bool ? TRUE : (isKnown ? FALSE : UNKNOWN);
        }
    
        /**
         * Returns {@link #TRUE} if any of the values are {@code true}, {@link #FALSE} if all the values are
         * {@code false}, and {@link #UNKNOWN} if at least one value is {@code unknown} and all others are false.
         * @param bools an array of UncertainBool values
         * @return the calculated UncertainBool value
         */
        public static UncertainBool anyOf(UncertainBool...bools) {
            boolean anyUnknown = false;
            for (UncertainBool state : bools) {
                if (state == TRUE) {
                    return TRUE;
                } else if (state == UNKNOWN) {
                    anyUnknown = true;
                }
            }
            return anyUnknown ? UNKNOWN : FALSE;
        }
        
        static UncertainBool notNull(UncertainBool bool) {
            return bool == null ? UNKNOWN : bool;
        }
    }
    
}
