import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

class Sort {
    private File path;
    private ArrayList<File> sortDirs;
    private ArrayList<File> listedFolders;
    private String[] compareStrings;
    private HashMap<String, ArrayList<File>> filesToCopy;

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
        //USE FILENAMEFILTER FOR COMPARING FILES TO compareStrings later on.
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

        //TO-DO: COPY THE DAMN FILES OF THE DAMN HASHMAP BOYYYOYOOOY

        System.out.println("ITS SORTED BOYYYYYY");
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
            if (!i.isDirectory()) {
                return false;
            }
        }
        return true;
    }
    
    public void addToCopyArrayList(String keyword, File path) {
        ArrayList<File> list = filesToCopy.get(keyword);
        //System.out.println("\nTO COPY FILES:");
        if (list != null) {
            list.add(path);
            System.out.println(keyword + " : " + path);
        }
    }
}
