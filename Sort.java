import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.nio.file.*;

public class Sort {
    private File path;
    private ArrayList<File> sortDirs;
    private ArrayList<File> listedFolders;
    private String[] compareStrings;
    private HashMap<String, ArrayList<File>> filesToCopy;
    public boolean done = false;

    public Sort(File path, ArrayList<File> sortDirs) {
        this.path = path;
        this.sortDirs = sortDirs;
        listedFolders = new ArrayList<>();
        filesToCopy = new HashMap<>();
        sort();
    }

    private void sort() {
        //check if the given paths are all really paths and not files.
        if (!path.isDirectory()) {
            System.err.println("The Path is not a Directory :c");
            return;
        }
        if (!checkSortDirs()) {
            System.err.println("The to/by paths are not Directories :c");
            return;
        }
        compareStrings = createCompareStrings();
        listFolders();
        for (String keyword : compareStrings) {
            if (keyword == null) {
                break;
            }
            filesToCopy.put(keyword, new ArrayList<>());
        }
        System.out.println("\nFound files to be copied: ");
        for (File folder : listedFolders) {
            for (String keyword : compareStrings) {
                if (keyword == null) {
                    break;
                }
                if (keyword.equals(folder.getName())) {
                    continue;
                }
                folder.listFiles(new MyKeywordFileNameFilter(keyword, this));
            }
        }

        System.out.println("\ncopying files....");
        for (String keyword : compareStrings) {
            if (keyword == null) {
                break;
            }
            ArrayList<File> list = filesToCopy.get(keyword);
            if (list != null) {
                for (File file : list) {
                    Path pathObj = file.toPath();
                    File temp = new File(path + "/" + keyword + "/" + pathObj.getFileName());
                    System.out.println("copying: " + pathObj.getFileName());
                    try {
                        Files.copy(pathObj, temp.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    } catch(IOException e) {
                        System.out.println("\nCopy failed. The given paths are:\n" + pathObj + "\n" + temp + "\n" + e.getMessage());
                    }
                }
            }
        }
        done = !done;
    }

    private void listFolders() {
        File[] list = path.listFiles(new MyDirFileFilter());
        System.out.println("\nFound Directories: ");
        for (File i : list) {
            listedFolders.add(i);
            System.out.println(i); //debug
        }
    }
    private String[] createCompareStrings() {
        String[] compareStrings = new String[FolderSort.MAX_SORT_ATTRIBUTES];
        int j = 0;
        System.out.println("\nGiven keywords: ");
        for (File i : sortDirs) {
            String dir = i.getName();
            compareStrings[j] = dir;
            System.out.println("-" + compareStrings[j] + "-"); //debug
            j++;
        }
        return compareStrings;
    }

    private boolean checkSortDirs() {
        for (File i : sortDirs) {
            if (!i.exists()) {
                try {
                    Files.createDirectory(i.toPath());
                } catch(IOException e) {
                    System.out.println(e.getMessage());
                    return false;
                }
            }
            if (!i.isDirectory()) {
                return false;
            }
        }
        return true;
    }
    
    public void addToCopyArrayList(String keyword, File file) {
        ArrayList<File> list = filesToCopy.get(keyword);
        //System.out.println("\nTO COPY FILES:");
        if (list != null) {
            list.add(file);
            System.out.println(keyword + " : " + file);
        }
    }
}
