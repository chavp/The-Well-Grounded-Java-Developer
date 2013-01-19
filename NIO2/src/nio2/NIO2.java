/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nio2;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.nio.file.StandardCopyOption.*;
import static java.nio.file.StandardWatchEventKinds.*;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

/**
 *
 * @author MyParinya
 */
public class NIO2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        WatchService();
    }

    public static void FindingFilesInADirectory() {
        Path dir = Paths.get("B:\\GithubRepositories\\The-Well-Grounded-Java-Developer\\NIO2\\src\\nio2");
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.java")) {
            for (Path entry : stream) {
                System.out.println(entry.getFileName());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void WalkingTheDirectoryTree() {
        try {
            Path startingDir = Paths.get("B:\\GithubRepositories\\The-Well-Grounded-Java-Developer\\NIO2");
            Files.walkFileTree(startingDir, new FindJavaVisitor());
        } catch (IOException ex) {
            Logger.getLogger(NIO2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static class FindJavaVisitor extends SimpleFileVisitor<Path> {

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            if (file.toString().endsWith(".java")) {
                System.out.println(file.getFileName());
            }
            return FileVisitResult.CONTINUE;
        }
    }

    public static void FilesystemIOWithNIO2() {
        //Creating and deleting files
        Path source = Paths.get("B:\\harddisk\\Stuff.txt");
        Path target = Paths.get("B:\\Backup\\MyStuff.txt");
        try {
            Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rw-rw-rw-");
            FileAttribute<Set<PosixFilePermission>> attr = PosixFilePermissions.asFileAttribute(perms);
            //Files.createFile(target);
            //Files.delete(target);
            //Files.copy(source, target, REPLACE_EXISTING);
            Files.move(target, source, REPLACE_EXISTING, ATOMIC_MOVE);
        } catch (IOException ex) {
            Logger.getLogger(NIO2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void WatchService() {
        try {
            WatchService watcher = FileSystems.getDefault().newWatchService();
            Path dir = FileSystems.getDefault().getPath("B:\\Backup");
            WatchKey key = dir.register(watcher, ENTRY_MODIFY);
            while (!false) {
                key = watcher.take();
                for (WatchEvent<?> event : key.pollEvents()) {
                    if (event.kind() == ENTRY_MODIFY) {
                        System.out.println("Home dir changed!");
                    }
                }
                key.reset();
            }
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(NIO2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
