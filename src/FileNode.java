
import java.io.File;


public class FileNode {

        public File file;

        public FileNode(File file) {
            this.file = file;
        }

        
        public String toString() {
            String name = file.getName();
            if (name.equals("")) {
                return file.getAbsolutePath();
            } else {
                return name;
            }
        }
    }

//}

