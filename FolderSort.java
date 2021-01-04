import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;

public class FolderSort {
    public static final int MAX_SORT_ATTRIBUTES = 100;
    private static Scanner sc = new Scanner(System.in);
    private static ArrayList<File> toSort = new ArrayList<>(MAX_SORT_ATTRIBUTES);
    public static void main(String[] args) {
        System.out.println("\nHello, this is my little program to sort files (espcially pictures) whose original Location is in diffrent folders.\n");
        System.out.println("Please hand me the main folder to sort");
        File path = getPath();
        System.out.print("\"" + path + "\" : is a " + path.isDirectory() + " directory.\n");
        System.out.println("Now the keywords to sort by/to. Divided by ONLY A ';' (e.g.: ahri;eve;...;).");
        System.out.println("If you choose \"all\" as your first keyword, it will take every file out of every folder and copy it to \"all\".");
        getToSort(path);
        if (toSort.get(0).getName().equals("all")) {
            All all = new All(path);
        } else {
            Sort sorter = new Sort(path, toSort);
            if (sorter.done) {
                System.out.println("\nDONE SORTING. Thanks for using my program :). I hope it worked to your desire.");
            } else {
                System.err.println("\nSorry, but something went wrong.");
            }
        }
    }
    private static File getPath() {
        String in = sc.nextLine();
        File inFile = new File(in);
        return inFile;
    }
    private static void getToSort(File path) {
        String[] nextDirs = sc.nextLine().split("\\p{Punct}");
        for (String nextDir : nextDirs) {
            toSort.add(new File(path.getPath() + "/" + nextDir));
        }
    }
}