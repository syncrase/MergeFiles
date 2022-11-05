import fr.syncrase.merger.FileExtension;
import fr.syncrase.merger.Merger;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Comparator;
import java.util.function.BiPredicate;

public class MergeLesMiseEnPlan {

    private static final String DOSSIER_PLANS = "C:\\Users\\syncrase\\Documents\\SolidWorks Projects\\Barrière haut escalier\\plans\\";
    private static final String FICHIER_SORTIE = "C:\\Users\\syncrase\\Documents\\toto.pdf";

    public static void main(String[] args) throws IOException {
        Comparator<Path> pathComparator = Comparator.comparing(path -> path.toFile().getName());

        // Si aucune extension n'a été renseignée, retourne toujours 'true'. Concrètement, on prend tous les fichiers.
        BiPredicate<Path, BasicFileAttributes> fileSelector = (path, basicFileAttributes) ->
            path.toFile().getName().endsWith(FileExtension.PDF.getExtension());

        Merger.defineStrategy()
            .fileSelector(fileSelector)
            .allFilesUnder(DOSSIER_PLANS)
            .sortedBy(pathComparator)
            .outputFile(FICHIER_SORTIE)
            .apply();
    }
}
