package src;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.nio.file.*;


public class All {
    private File path;
    private ArrayList<File> listedFolders;
    private ArrayList<File> filesToCopy;
    public All(File path) {
        this.path = path;
        listedFolders = new ArrayList<>();
        filesToCopy = new ArrayList<>();
        all();
    }

    private void all() {
        if (!path.isDirectory()) {
            System.err.println("The Path is not a Directory :c");
            return;
        }
        File all = new File(path + "/" + "all");
        if (!all.exists()) {
            try {
                Files.createDirectory(all.toPath());
            } catch(IOException e) {
                System.out.println(e.getMessage());
                return;
            }
        }
        listFolders();
        addEveryFile();
        copyEveryFileToAll();
    }

    private void listFolders() {
        File[] list = path.listFiles(new MyDirFileFilter());
        System.out.println("\nFound Directories: ");
        for (File i : list) {
            listedFolders.add(i);
            System.out.println(i); //debug
        }
    }

    private void addEveryFile() {
        for (File folder : listedFolders) {
            File[] files = folder.listFiles();
            for (File f : files) {
                filesToCopy.add(f);
            }
        }
    }

    private void copyEveryFileToAll() {
        for (File file : filesToCopy) {
            Path pathObj = file.toPath();
            File temp = new File(path + "/" + "all" + "/" + pathObj.getFileName());
            System.out.println("copying: " + pathObj.getFileName());
            try {
                Files.copy(pathObj, temp.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch(IOException e) {
                System.out.println("\nCopy failed. The given paths are:\n" + pathObj + "\n" + temp + "\n" + e.getMessage());
            }
        }
    }
}
