import java.io.File;
import java.io.FileFilter;

class MyDirFileFilter implements FileFilter {

	@Override
	public boolean accept(File pathname) {
		if (pathname.isDirectory()) {
            return true;
        }
		return false;
	}
}