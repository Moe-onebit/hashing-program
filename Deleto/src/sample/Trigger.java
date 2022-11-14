package sample;

import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;

import java.io.File;

public class Trigger
{   public Boolean is;
    public Pane pane;
    public javafx.scene.control.TableView tableView;
    String hash;
    String path;

    public Trigger(String path, Pane pane, TableView tableView,Boolean is,String hash){this.path=path;this.pane=pane;this.tableView=tableView;this.is=is;this.hash=hash;}
    public void main() throws Exception
    {
        Finder finder=new Finder(pane,tableView);
        File file =new File (path);
        FolderExplorer folderExplorer=new FolderExplorer(pane,tableView,finder);
        folderExplorer.explore(file,hash,is);
    }
}
