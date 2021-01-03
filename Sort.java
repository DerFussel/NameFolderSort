import java.io.File;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

class Sort {
    private File path;
    private ArrayList<File> sortDirs;
    private Set<File> listedFolders;

    public Sort(File path, ArrayList<File> sortDirs) {
        this.path = path;
        this.sortDirs = sortDirs;
        listedFolders = new HashSet<>();
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
        String[] compareStrings = getCompareStrings();
        listFolders();
        //USE FILENAMEFILTER FOR COMPARING FILES TO compareStrings later on.

        System.out.println("ITS SORTED BOYYYYYY");
    }

    private void listFolders() {
        File[] list = path.listFiles(new MyFileFilter());
        for (File i : list) {
            listedFolders.add(i);
            System.out.println(i); //debug
        }
    }
    private String[] getCompareStrings() {
        String[] compareStrings = new String[FolderSort.MAX_SORT_ATTRIBUTES];
        int j = 0;
        for (File i : sortDirs) {
            String dir = i.getName();
            compareStrings[j] = dir;
            j++;
        }
        return compareStrings;
    }

    private boolean checkSortDirs() {
        for (File i : sortDirs) {
            if (!i.isDirectory()) {
                return false;
            }
        }
        return true;
    }
}