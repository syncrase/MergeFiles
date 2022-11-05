package fr.syncrase.merger;

import java.io.IOException;

public class MergeException extends RuntimeException {
    public MergeException(String s, IOException e) {
        super(s, e);
    }
}
