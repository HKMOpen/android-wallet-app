package org.iota.wallet.var.crypto;

public class DecryptionException extends Exception {
    //Parameterless Constructor
    public DecryptionException() {
    }

    //Constructor that accepts a message
    public DecryptionException(String message) {
        super(message);
    }
}