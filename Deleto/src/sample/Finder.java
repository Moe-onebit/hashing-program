package sample;


import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;

public class Finder
{
    static ArrayList<Duplicate> duplicates;
    static TreeMap <String,String> hashToFile;
    public Pane pane;
    public TableView tableView;

    Finder(Pane pane,TableView tableView) throws Exception
    {
         this.tableView=tableView;
         this.pane = pane;
         duplicates = new ArrayList<Duplicate>();
         hashToFile = new TreeMap<String,String>();
    }
    public void process(File file, String hash) throws IOException,NoSuchAlgorithmException {
        String id = HashGiver.get(file,hash);
        if(hashToFile.containsKey(id))
        {
            // there is a duplicate file
            //process duplicate
            Duplicate d = new Duplicate(file);
            //duplicates.add(d);
//            System.out.println(file);
//            System.out.println(hashToFile.get(id));
//            System.out.println("**********************");
            try {
                BasicFileAttributes attr1 = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
                FileTime fileTime1 = attr1.creationTime();
                Date d1 = new Date( fileTime1.toMillis() );
                File f = new File(hashToFile.get(id));
                BasicFileAttributes attr2 = Files.readAttributes(f.toPath(), BasicFileAttributes.class);
                FileTime fileTime2 = attr2.creationTime();
                Date d2 = new Date( fileTime2.toMillis() );
                if(d1.before(d2))
                {
                    duplicates.add(d);
                    if (((Files.size(Paths.get(String.valueOf(file)))/(1024*1024))==0))
                        tableView.getItems().add(new Filee(file,file.getParent(), (Files.size(Paths.get(String.valueOf(file))) / (1024))+" KB",((file.getName()).substring(file.getName().length()-4)).toUpperCase()));
                    else if ((Files.size(Paths.get(String.valueOf(file)))/(1024*1024))<1000)
                        tableView.getItems().add(new Filee(file,file.getParent(), (Files.size(Paths.get(String.valueOf(file))) / (1024 * 1024))+" MB",((file.getName()).substring(file.getName().length()-4)).toUpperCase()));
                    else if ((Files.size(Paths.get(String.valueOf(file)))/(1024*1024))>1000)
                        tableView.getItems().add(new Filee(file,file.getParent(), (Files.size(Paths.get(String.valueOf(file))) / (1024 * 1024 * 1024))+ " GB",((file.getName()).substring(file.getName().length()-4)).toUpperCase()));
                }
                else
                {
                    File oldFile = new File(hashToFile.get(id));
                    hashToFile.remove(id);
                    hashToFile.put(id,file.toString());
                    Duplicate oldDuplicate = new Duplicate(oldFile);
                    duplicates.add(oldDuplicate);
                    if (((Files.size(Paths.get(String.valueOf(oldFile)))/(1024*1024))==0))
                        tableView.getItems().add(new Filee(oldFile,oldFile.getParent(), (Files.size(Paths.get(String.valueOf(oldFile))) / (1024))+" KB",((oldFile.getName()).substring(oldFile.getName().length()-4)).toUpperCase()));
                    else if ((Files.size(Paths.get(String.valueOf(file)))/(1024*1024))<1000)
                        tableView.getItems().add(new Filee(oldFile,oldFile.getParent(), (Files.size(Paths.get(String.valueOf(oldFile))) / (1024 * 1024))+" MB",((oldFile.getName()).substring(oldFile.getName().length()-4)).toUpperCase()));
                    else if ((Files.size(Paths.get(String.valueOf(file)))/(1024*1024))>1000)
                        tableView.getItems().add(new Filee(oldFile,oldFile.getParent(), (Files.size(Paths.get(String.valueOf(oldFile))) / (1024 * 1024 * 1024))+ " GB",((oldFile.getName()).substring(oldFile.getName().length()-4)).toUpperCase()));
                }
            } catch (Exception ex) {
                // handle exception
            }

        }
        else
        {
            // put the hash in the map
            hashToFile.put(id,file.toString());
        }
    }

    public void selectAll()
    {
        for(int i = 0;i < duplicates.size();++i)
        {
            duplicates.get(i).toDelete = true;
        }
    }

    public void delete(File path)
    {
        for(int i = 0;i < duplicates.size();++i)
        {
            if(duplicates.get(i).toDelete)
            {
                path.delete();
            }
        }
    }

    public  class Filee{
        File file;
        String path;
        String size;
        String format;
        CheckBox checkBox;
        private BooleanProperty selected = new SimpleBooleanProperty(false);
        Filee(File file, String path,String size,String format){
            this.file=file;
            this.path=path;
            this.size=size;
            this.format=format;
            this.checkBox=new CheckBox();
        }

        public BooleanProperty selectedProperty() {
            return selected;
        }

        public void setSelected(boolean selected) {
            this.selected.set(selected);
        }

        public boolean isSelected() {
            return selected.get();
        }

        public CheckBox getCheckBox() { return checkBox; }

        public File getFile() {
            return file;
        }

        public String getPath() {
            return path;
        }

        public String getSize() { return size; }

        public String getFormat() {
            return format;
        }

        public void setFile(File file) { this.file = file; }

        public void setPath(String path) {
            this.path = path;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public void setFormat(String format) {
            this.format = format;
        }

        public void setCheckBox(CheckBox checkBox) { this.checkBox = checkBox; }
    }

}
