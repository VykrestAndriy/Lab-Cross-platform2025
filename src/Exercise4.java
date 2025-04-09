import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Scanner;

public class Exercise4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Будь ласка, введіть шлях до вихідної директорії: ");
        String originPath = input.nextLine();

        System.out.print("Введіть шлях до цільової директорії для копіювання: ");
        String destinationPath = input.nextLine();

        try {
            transferFiles(originPath, destinationPath);
            System.out.println("Операція копіювання файлів завершена успішно.");
        } catch (IOException exception) {
            System.err.println("Виникла помилка під час копіювання: " + exception.getMessage());
        } finally {
            input.close();
        }
    }

    private static void transferFiles(String sourceDirectory, String targetDirectory) throws IOException {
        Path source = Paths.get(sourceDirectory);
        Path target = Paths.get(targetDirectory);

        if (!Files.exists(target)) {
            Files.createDirectories(target);
        }

        Files.walkFileTree(source, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) throws IOException {
                Path targetFile = target.resolve(source.relativize(file));
                Files.copy(file, targetFile, StandardCopyOption.REPLACE_EXISTING);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult preVisitDirectory(Path directory, BasicFileAttributes attributes) throws IOException {
                Path newTargetDirectory = target.resolve(source.relativize(directory));
                if (!Files.exists(newTargetDirectory)) {
                    Files.createDirectories(newTargetDirectory);
                }
                return FileVisitResult.CONTINUE;
            }
        });
    }
}