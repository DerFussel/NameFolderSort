import java.io.File;
import java.util.ArrayList;

class Sort {
    private File path;
    private ArrayList<File> sortDirs;

    public Sort(File path, ArrayList<File> sortDirs) {
        this.path = path;
        this.sortDirs = sortDirs;
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
        System.out.println("ITS SORTED BOYYYYYY");
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