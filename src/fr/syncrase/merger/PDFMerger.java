package fr.syncrase.merger;

import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Path;

public class PDFMerger extends Merger {

    private static void addFileToMerger(@NotNull PDFMergerUtility merger, @NotNull Path path) {
        try {
            merger.addSource(path.toFile().getCanonicalFile());
        } catch (IOException e) {
            System.err.println(path.toFile().getName() + " not found");
        }
    }

    @Override
    void apply() throws IOException {
        PDFMergerUtility merger = new PDFMergerUtility();
        merger.setDestinationFileName(super.strategy.getDestinationFileName());

        super.inputFiles.forEach(path -> addFileToMerger(merger, path));
        merger.mergeDocuments(null);
    }
}
