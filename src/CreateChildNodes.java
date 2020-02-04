
import java.io.File;
import javax.swing.tree.DefaultMutableTreeNode;

 public class CreateChildNodes implements Runnable {

        public DefaultMutableTreeNode root;

        public File fileRoot;

        public CreateChildNodes(File fileRoot, 
                DefaultMutableTreeNode root) {
            this.fileRoot = fileRoot;
            this.root = root;
        }

   

       
        public void run() {
            createChildren(fileRoot, root);
        }

        public void createChildren(File fileRoot, 
                DefaultMutableTreeNode node) {
            File[] files = fileRoot.listFiles();
            if (files == null) return;

            for (File file : files) {
                DefaultMutableTreeNode childNode = 
                        new DefaultMutableTreeNode(new FileNode(file));
                node.add(childNode);
                if (file.isDirectory()) {
                    createChildren(file, childNode);
                    
                }
            }
        }

    }

   