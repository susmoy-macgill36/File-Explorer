import java.awt.Color;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.PopupMenu;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;

//import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;





public class FileMain implements Runnable {

    public DefaultMutableTreeNode[] root;

    public DefaultTreeModel[] treeModel;

    public JTree tree[];
    ///private JTree tree1;
///String s;

    
   
    public void run() {
        //tree1=tree[];
        JButton button =new JButton();
        button.setText("open  files");
         button.setSize(100,20);
        
        button.setBackground(Color.pink);
         
              
        // JButton button1 =new JButton();
         // button.setSize(10,20);
        
        JFrame frame = new JFrame("File eXPLORER");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(2, 2));
        //PopupMenu button = null;
       //frame.add(button);
       // button.setSize(100,20);
       button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				frame.dispose();
				//new SecondFrame1();
                                  new DesignClass().setVisible(true);
                              //  TreePath s;
                            //s = tree.setPathForLocation(ae.getX(),ae.getY());
                                
                               			}
		});
        
        File[] drives = File.listRoots();
        int l=drives.length;
      
        root = new DefaultMutableTreeNode[l];
        treeModel = new DefaultTreeModel[l];
        tree = new JTree[l];
        File[] fileRoot= new File[l];
        JScrollPane[] scrollPane = new JScrollPane[l];
        CreateChildNodes[] createChildNodes = new CreateChildNodes[l];
        
        Container container = new Container();
        //container.setLayout(new GridLayout(1,l));
        container.setLayout(new GridLayout(2,l));
        
        for(int driveCount=0; driveCount< l;driveCount++){

            fileRoot[driveCount] = new File(drives[driveCount]+"");
            root[driveCount] = new DefaultMutableTreeNode(new FileNode(fileRoot[driveCount]));
            treeModel[driveCount] = new DefaultTreeModel(root[driveCount]);

            tree[driveCount] = new JTree(treeModel[driveCount]);
            tree[driveCount].setShowsRootHandles(true);
            scrollPane[driveCount] = new JScrollPane(tree[driveCount]);
            
            container.add(scrollPane[driveCount],driveCount);
            createChildNodes[driveCount] =    new CreateChildNodes(fileRoot[driveCount], root[driveCount]);
            new Thread(createChildNodes[driveCount]).start();
            
        }
        frame.add(container);
        frame.setLocationByPlatform(true);
        frame.add(button);
        frame.setSize(1280,720);
        frame.setVisible(true);
      // if (Desktop.isDesktopSupported()) {
// try {
     //Desktop desktop = Desktop.getDesktop();
    // File myFile = new File("C:/");
    // desktop.open(myFile);
    // } catch (IOException ex) {}
    
// } 

    }

 
    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(new FileMain());
        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    try {
                        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(FileMain.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InstantiationException ex) {
                        Logger.getLogger(FileMain.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(FileMain.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (UnsupportedLookAndFeelException ex) {
                        Logger.getLogger(FileMain.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                }
            }
        
   // }

    }
}

       