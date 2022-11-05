package fr.syncrase.merger;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public enum FileExtension {
    PDF("pdf"), TXT(".txt");

    public final String extension;

    FileExtension(String extension) {
        this.extension = extension;
    }

    @Contract(pure = true)
    public @NotNull String getExtension() {
        return extension;
    }
}
