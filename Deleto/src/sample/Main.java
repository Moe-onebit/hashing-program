package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Main extends Application
{
    
    public static void main (String[] args)throws Exception
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        AnchorPane root=new AnchorPane();
        View t=new View(root,new TableView());
        primaryStage.setScene(new Scene(t.getView(), 800, 699));
        primaryStage.setTitle("Deleto");
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("file:Deleto.png"));
        primaryStage.show();
    }
}