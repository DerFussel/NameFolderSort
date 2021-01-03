import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;

public class FolderSort {
    public static final int MAX_SORT_ATTRIBUTES = 10;
    private static Scanner sc = new Scanner(System.in);
    private static ArrayList<File> toSort = new ArrayList<>(MAX_SORT_ATTRIBUTES);
    public static void main(String[] args) {
        System.out.println("\nHello, this is my little program to sort files (espcially pictures) whose original Location is in diffrent folders.\n");
        System.out.println("Please hand me the folder to sort");
        File path = getPath();
        path = new File("/Users/justuswolschner/Desktop/sevenbees/"); //debug
        System.out.println(path + " : " + path.isDirectory());
        System.out.println("Now the folders to sort by/to. Divided by ONLY A ';'.");
        getToSort();
        toSort.clear();
        toSort.add(new File("/Users/justuswolschner/Desktop/sevenbees/ahri"));
        toSort.add(new File("/Users/justuswolschner/Desktop/sevenbees/eve"));
        for (File i : toSort) { //debug
            System.out.println("-" + i + "-");
        }
        Sort sorter = new Sort(path, toSort);
    }
    private static File getPath() {
        String in = sc.nextLine();
        System.out.println(in); //debug
        File inFile = new File(in);
        return inFile;
    }
    private static void getToSort() {
        String[] nextDirs = sc.nextLine().split("\\p{Punct}");
        for (String nextDir : nextDirs) {
            toSort.add(new File(nextDir));
        }
    }
}