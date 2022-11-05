package fr.syncrase.merger;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Comparator;
import java.util.function.BiPredicate;

public class MergingStrategy {
    private String racineDesMisesEnPlan;
    private Comparator<Path> pathComparator;
    private String outputFile;
    private BiPredicate<Path, BasicFileAttributes> fileSelector;

    public MergingStrategy allFilesUnder(String racineDesMisesEnPlan) {
        this.racineDesMisesEnPlan = racineDesMisesEnPlan;
        return this;
    }

    public MergingStrategy sortedBy(Comparator<Path> pathComparator) {
        this.pathComparator = pathComparator;
        return this;
    }

    public MergingStrategy outputFile(String outputFile) {
        this.outputFile = outputFile;
        return this;
    }

    public void apply() throws IOException {
        Merger merger = MergerInstanceConstructor.getMergerFor(FileExtension.PDF);
        merger.setStrategy(this);
        merger.merge();
    }

    public String getRoot() {
        return this.racineDesMisesEnPlan;
    }

    public Comparator<Path> getPathComparator() {
        return pathComparator;
    }

    public String getDestinationFileName() {
        return outputFile;
    }

    public MergingStrategy fileSelector(BiPredicate<Path, BasicFileAttributes> fileSelector) {
        this.fileSelector = fileSelector;
        return this;
    }

    public BiPredicate<Path, BasicFileAttributes> getFileSelector() {
        return this.fileSelector;
    }
}
