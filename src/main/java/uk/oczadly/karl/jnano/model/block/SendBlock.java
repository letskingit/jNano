/*
 * Copyright (c) 2020 Karl Oczadly (karl@oczadly.uk)
 * Licensed under the MIT License
 */

package uk.oczadly.karl.jnano.model.block;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import uk.oczadly.karl.jnano.internal.JNH;
import uk.oczadly.karl.jnano.model.NanoAccount;
import uk.oczadly.karl.jnano.model.NanoAmount;
import uk.oczadly.karl.jnano.model.block.interfaces.IBlockBalance;
import uk.oczadly.karl.jnano.model.block.interfaces.IBlockLink;
import uk.oczadly.karl.jnano.model.block.interfaces.IBlockPrevious;
import uk.oczadly.karl.jnano.model.work.WorkSolution;

import java.math.BigInteger;
import java.util.function.Function;

/**
 * Represents a {@code send} block, and the associated data.
 */
public class SendBlock extends Block implements IBlockPrevious, IBlockLink, IBlockBalance {
    
    /** A function which converts a {@link JsonObject} into a {@link SendBlock} instance. */
    public static final Function<JsonObject, SendBlock> DESERIALIZER = json -> new SendBlock(
            JNH.nullable(json.get("hash"), JsonElement::getAsString),
            json.get("signature").getAsString(),
            new WorkSolution(json.get("work").getAsString()),
            json.get("previous").getAsString(),
            NanoAccount.parseAddress(json.get("destination").getAsString()),
            new NanoAmount(json.get("balance").getAsString()));
    
    private static final BlockIntent INTENT = new BlockIntent(true, false, false, false, false, false);
    
    
    @Expose @SerializedName("previous")
    private String previousBlockHash;
    
    @Expose @SerializedName("destination")
    private NanoAccount destinationAccount;
    
    @Expose @SerializedName("balance")
    private NanoAmount balance;
    
    
    SendBlock() {
        super(BlockType.SEND);
    }
    
    @Deprecated
    public SendBlock(String signature, WorkSolution work, String previousBlockHash, NanoAccount destinationAccount,
                     BigInteger balance) {
        this(null, signature, work, previousBlockHash, destinationAccount, new NanoAmount(balance));
    }
    
    public SendBlock(String signature, WorkSolution work, String previousBlockHash, NanoAccount destinationAccount,
                     NanoAmount balance) {
        this(null, signature, work, previousBlockHash, destinationAccount, balance);
    }
    
    protected SendBlock(String hash, String signature, WorkSolution work,
                     String previousBlockHash, NanoAccount destinationAccount, NanoAmount balance) {
        super(BlockType.SEND, hash, signature, work);
    
        if (previousBlockHash == null) throw new IllegalArgumentException("Previous block hash cannot be null.");
        if (!JNH.isValidHex(previousBlockHash, HASH_LENGTH))
            throw new IllegalArgumentException("Previous block hash is invalid.");
        if (destinationAccount == null) throw new IllegalArgumentException("Block destination account cannot be null.");
        if (balance == null) throw new IllegalArgumentException("Account balance cannot be null.");
        
        this.previousBlockHash = previousBlockHash;
        this.destinationAccount = destinationAccount;
        this.balance = balance;
    }
    
    
    @Override
    public final String getPreviousBlockHash() {
        return previousBlockHash;
    }
    
    /**
     * @return the destination account which the funds will be sent to
     */
    public final NanoAccount getDestinationAccount() {
        return destinationAccount;
    }
    
    @Override
    public final NanoAmount getBalance() {
        return balance;
    }
    
    
    /**
     * {@inheritDoc}
     * @deprecated Implemented for compatibility with the link interface.
     * @see #getDestinationAccount()
     */
    @Deprecated
    @Override
    public final String getLinkData() {
        return getDestinationAccount().toPublicKey();
    }
    
    /**
     * {@inheritDoc}
     * @deprecated Implemented for compatibility with the link interface.
     * @see #getDestinationAccount()
     */
    @Deprecated
    @Override
    public final NanoAccount getLinkAsAccount() {
        return getDestinationAccount();
    }
    
    @Override
    public BlockIntent getIntent() {
        return INTENT;
    }
    
    
    @Override
    protected byte[][] generateHashables() {
        return new byte[][] {
                JNH.ENC_16.decode(getPreviousBlockHash()),
                getDestinationAccount().getPublicKeyBytes(),
                JNH.leftPadByteArray(getBalance().getAsRaw().toByteArray(), 16, false)
        };
    }
    
}
