package in.bigdolph.jnano.rpc.query.request.wallet;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import in.bigdolph.jnano.rpc.query.request.RPCRequest;
import in.bigdolph.jnano.rpc.query.response.generic.ExistsResponse;
import in.bigdolph.jnano.rpc.query.response.specific.NodeVersionResponse;

public class WalletContainsAccountRequest extends RPCRequest<ExistsResponse> {
    
    @Expose
    @SerializedName("wallet")
    private String walletId;
    
    @Expose
    @SerializedName("account")
    private String account;
    
    
    public WalletContainsAccountRequest(String walletId, String account) {
        super("wallet_contains", ExistsResponse.class);
        this.walletId = walletId;
        this.account = account;
    }
    
    
    
    public String getWalletId() {
        return walletId;
    }
    
    public String getAccount() {
        return account;
    }
    
}