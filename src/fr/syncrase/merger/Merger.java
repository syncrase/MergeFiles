package fr.syncrase.merger;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Merger {

    protected MergingStrategy strategy;

    protected List<Path> inputFiles;

    @Contract(value = " -> new", pure = true)
    public static @NotNull MergingStrategy defineStrategy() {
        return new MergingStrategy();
    }

    public void setStrategy(MergingStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Utilise les données stratégiques pour construire l'ensemble des fichiers à merger<br/>
     * Récupère la liste des fichiers :<br/>
     * <ul>
     *     <li>qui possède l'extension de fichier donnée au merger</li>
     *     <li>à partir de la racine donnée au merger</li>
     * </ul>
     */
    public void merge() throws IOException {
        recupereLaListeFichiers();
        inputFiles.sort(strategy.getPathComparator());
        apply();
    }

    /**
     * Méthode à implémenter afin d'utiliser les données de la stratégie
     *
     * @throws IOException lorsqu'un quelconque problème arrive lors de l'accès I/O des fichiers
     */
    abstract void apply() throws IOException;

    /**
     * Si une liste existe déjà, elle est écrasée
     */
    void recupereLaListeFichiers() {
        Path rootPath = Path.of(strategy.getRoot());
        try (Stream<Path> pdfsUnderRootPath = Files.find(rootPath, 5, strategy.getFileSelector())) {
            // TODO use tree structure backed by the file system file structure
            inputFiles = pdfsUnderRootPath.collect(Collectors.toList());
        } catch (IOException e) {
            throw new MergeException("Impossible de construire l'ensemble des fichiers à merger", e);
        }
    }

}
