package com.talanCoin.contract;

import com.talanCoin.state.TalanCoinState;
import net.corda.core.contracts.AuthenticatedObject;
import net.corda.core.contracts.CommandData;
import net.corda.core.contracts.Contract;
import net.corda.core.contracts.TransactionForContract;
import net.corda.core.crypto.SecureHash;
import net.corda.core.identity.AbstractParty;

import java.util.stream.Collectors;

import static net.corda.core.contracts.ContractsDSL.requireSingleCommand;
import static net.corda.core.contracts.ContractsDSL.requireThat;

/**
 * Define your contract here.
 */
public class TalanCoinContract implements Contract {
    /**
     * The verify() function of the contract of each of the transaction's input and output states must not throw an
     * exception for a transaction to be considered valid.
     */
    @Override
    public void verify(TransactionForContract tx) {
        final AuthenticatedObject<Commands.Create> command = requireSingleCommand(tx.getCommands(), Commands.Create.class);
        requireThat(require -> {
            // Generic constraints around the TalanCointransaction.
            require.using("No inputs should be consumed when issuing an IOU.",
                    tx.getInputs().isEmpty());
            require.using("Only one output state should be created.",
                    tx.getOutputs().size() == 1);
            final TalanCoinState out = (TalanCoinState) tx.getOutputs().get(0);
            require.using("The sender and the recipient cannot be the same entity.",
                    out.getSender() != out.getRecipient());
            require.using("All of the participants must be signers.",
                    command.getSigners().containsAll(out.getParticipants().stream().map(AbstractParty::getOwningKey).collect(Collectors.toList())));

            // IOU-specific constraints.
            require.using("The IOU's value must be non-negative.",
                    out.getTalanCoin().getValue() > 0);

            return null;
        });
    }

    public interface Commands extends CommandData {
        class Create implements Commands {}
    }

    /** A reference to the underlying legal contract template and associated parameters. */
    private final SecureHash legalContractReference = SecureHash.sha256("Prose contract.");
    @Override public final SecureHash getLegalContractReference() { return legalContractReference; }
}