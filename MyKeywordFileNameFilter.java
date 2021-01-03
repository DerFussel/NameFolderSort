import java.io.File;
import java.io.FilenameFilter;

public class MyKeywordFileNameFilter implements FilenameFilter {
    private String keyword;
    private Sort sorter;

    public MyKeywordFileNameFilter(String keyword, Sort sorter) {
        this.keyword = keyword;
        this.sorter = sorter;
    }
    @Override
    public boolean accept(File dir, String name) {
        if (name.toLowerCase().contains(keyword)) {
            //System.out.println("name: " + name + "\nKeyword: " + keyword); //debug s
            sorter.addToCopyArrayList(keyword, new File(dir.getPath() + "/" + name));
            return true;
        }
        return false;
    }
    
}
