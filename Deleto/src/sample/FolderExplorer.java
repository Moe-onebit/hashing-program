package sample;

import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;

import java.io.File;

public class FolderExplorer
{   public Pane pane;
    public TableView tableView;
    public Finder finder;
    FolderExplorer(Pane pane,TableView tableView,Finder finder) throws Exception {this.pane=pane;this.tableView=tableView;this.finder=finder; }
    public void explore(File path,String hash ,Boolean includeSubDirectory) throws Exception
    {
        for(File file : path.listFiles())
        {
            if(file.isDirectory())
            {
                if(includeSubDirectory)
                    explore(file,hash,true);
            }
            else
                finder.process(file,hash);
        }
    }
}
