package fr.syncrase.merger;

import ch.codeblock.qrinvoice.NotYetImplementedException;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class MergerInstanceConstructor {

    @Contract(pure = true)
    public static @NotNull Merger getMergerFor(@NotNull FileExtension pdf) {
        switch (pdf) {
            case PDF -> {
                return new PDFMerger();
            }
            case TXT -> throw new NotYetImplementedException();
        }
        return new PDFMerger();
    }
}
