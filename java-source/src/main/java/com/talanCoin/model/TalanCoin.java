package com.talanCoin.model;

import net.corda.core.serialization.CordaSerializable;

/**
 * A simple class representing a TalanCoin amount.
 *
 * This is the data structure that the parties will reach agreement over. These data structures can be arbitrarily
 * complex. See https://github.com/corda/corda/blob/master/samples/irs-demo/src/main/kotlin/net/corda/irs/contract/IRS.kt
 * for a more complicated example.
 *
 * @param value the TalanCoin's value.
 */
@CordaSerializable
public class TalanCoin {
    private int value;

    public int getValue() { return value; }

    public TalanCoin(int value) {
        this.value = value;
    }

    // Dummy constructor used by the create-TalanCoinAPI endpoint.
    public TalanCoin() {}

    @Override public String toString() {
        return String.format("TalanCoin(value=%d)", value);
    }
}