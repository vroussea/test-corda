package com.talanCoin.state;

import com.talanCoin.model.TalanCoin;
import com.talanCoin.contract.TalanCoinContract;
import net.corda.core.contracts.ContractState;
import net.corda.core.contracts.UniqueIdentifier;
import net.corda.core.identity.AbstractParty;
import net.corda.core.identity.Party;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Define your state object here.
 */
public class TalanCoinState implements ContractState {
    private final TalanCoinContract contract;
    private final TalanCoin talancoin;
    private final Party sender;
    private final Party recipient;
    private final UniqueIdentifier linearId;

    /**
     * @param TalanCoindetails of the TalanCoin.
     * @param sender the party issuing the TalanCoin.
     * @param recipient the party receiving and approving the TalanCoin.
     * @param contract the contract which governs which transactions are valid for this state object.
     */
    public TalanCoinState(TalanCoin talancoin,
                    Party sender,
                    Party recipient,
                    TalanCoinContract contract)
    {
        this.talancoin = talancoin;
        this.sender = sender;
        this.recipient = recipient;
        this.contract = contract;
        this.linearId = new UniqueIdentifier();
    }

    public TalanCoingetIOU() { return iou; }
    public Party getSender() { return sender; }
    public Party getRecipient() { return recipient; }
    @Override public TalanCoinContract getContract() { return contract; }
    @Override public UniqueIdentifier getLinearId() { return linearId; }
    @Override public List<AbstractParty> getParticipants() {
        return Arrays.asList(sender, recipient);
    }

    public TalanCoinState(TalanCoinContract contract) { this.contract = contract; }

    @Override public TalanCoinContract getContract() { return contract; }

    /** The public keys of the involved parties. */
    @Override public List<AbstractParty> getParticipants() { return Collections.emptyList(); }
}