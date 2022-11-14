package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

public class View {

    public AnchorPane root;
    public TableView tableView;
    public Boolean is;
    public String hash;
    public View(AnchorPane root, TableView tableView) throws IOException {
        this.root=root;
        this.tableView=tableView;
        Trigger main2 = new Trigger(null,root,tableView,is,hash);
        root.setStyle("-fx-background-color: #12141d;");
        Button button = new Button("Browse");
        Button scan = new Button("Scan");
        Button delete = new Button("Delete");

        TextField textField = new TextField();
        Label label = new Label("Choose the directory:");
        Label label1 = new Label("Deleto");
        RadioButton dark = new RadioButton("Dark Mode");
        RadioButton light = new RadioButton("Light Mode");
        RadioButton md5=new RadioButton("MD5");
        RadioButton sha=new RadioButton("SHA-256");
        ToggleGroup toggleGroup2=new ToggleGroup();
        ToggleGroup toggleGroup = new ToggleGroup();
        CheckBox selectAll=new CheckBox("Select All");
        CheckBox subDirectory=new CheckBox("SubDirectory");

        tableView.setEditable(false);

        TableColumn<Finder.Filee, File> column1 = new TableColumn<>("File Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("file"));

        TableColumn<Finder.Filee, String> column2 = new TableColumn<>("Path");
        column2.setCellValueFactory(new PropertyValueFactory<>("path"));

        TableColumn<Finder.Filee, String> column3 = new TableColumn<>("Size");
        column3.setCellValueFactory(new PropertyValueFactory<>("size"));

        TableColumn<Finder.Filee, String> column4 = new TableColumn<>("Format");
        column4.setCellValueFactory(new PropertyValueFactory<>("format"));

        TableColumn<Finder.Filee, CheckBox> column5 = new TableColumn<>("Select");
        column5.setCellValueFactory(new PropertyValueFactory<>("checkBox"));

        column1.setMinWidth(200);
        column2.setMinWidth(150);
        column3.setMinWidth(100);
        column4.setMinWidth(115);
        column5.setMinWidth(50);

        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);
        tableView.getColumns().add(column3);
        tableView.getColumns().add(column4);
        tableView.getColumns().add(column5);
        tableView.setLayoutX(65.0);
        tableView.setLayoutY(248.0);
        tableView.setPrefHeight(347.0);
        tableView.setPrefWidth(664.0);
        tableView.setEffect(new DropShadow());
        tableView.setEditable(false);

        delete.setStyle("-fx-background-color: #1DA1F2");
        delete.setTextFill(Color.web("#F5F8FA"));
        delete.setFont(new Font("System Bold", 15));
        delete.setCursor(Cursor.HAND);
        delete.setLayoutX(412);
        delete.setLayoutY(605);
        delete.setPrefHeight(31);
        delete.setPrefWidth(122);

        button.setLayoutX(412.0);
        button.setLayoutY(146.0);
        button.setPrefHeight(31);
        button.setPrefWidth(122);
        button.setStyle("-fx-background-color: #1DA1F2");
        button.setTextFill(Color.web("#F5F8FA"));
        button.setFont(new Font("System Bold", 15));
        button.setCursor(Cursor.HAND);

        scan.setLayoutX(412.0);
        scan.setLayoutY(188);
        scan.setPrefHeight(31);
        scan.setPrefWidth(122);
        scan.setStyle("-fx-background-color: #1DA1F2");
        scan.setTextFill(Color.web("#F5F8FA"));
        scan.setFont(new Font("System Bold", 15));
        scan.setCursor(Cursor.HAND);

        textField.setLayoutX(65.0);
        textField.setLayoutY(146.0);
        textField.setPrefHeight(31.0);
        textField.setPrefWidth(336.0);
        textField.setStyle("-fx-background-color: #FFFFFF;");
        subDirectory.setLayoutX(68.0);
        subDirectory.setLayoutY(185.0);
        subDirectory.setTextFill(Color.web("#f5f8fa"));
        subDirectory.setEffect(new Glow());
        subDirectory.setCursor(Cursor.HAND);
        label.setLayoutX(65.0);
        label.setLayoutY(117.0);
        label.setPrefHeight(21.0);
        label.setPrefWidth(336.0);
        label.setTextFill(Color.web("#F5F8FA"));
        label.setFont(new Font("System Bold", 15.0));
        label1.setAlignment(Pos.CENTER);
        label1.setLayoutX(200.0);
        label1.setLayoutY(23.0);
        label1.setPrefHeight(78.0);
        label1.setPrefWidth(423.0);
        label1.setTextFill(Color.web("#F5F8FA"));
        label1.setFont(new Font("System Bold Italic", 55.0));
        label1.setCursor(Cursor.V_RESIZE);
        label1.setCursor(Cursor.DEFAULT);

        md5.setToggleGroup(toggleGroup2);
        sha.setToggleGroup(toggleGroup2);
        dark.setToggleGroup(toggleGroup);
        light.setToggleGroup(toggleGroup);
        md5.setLayoutX(550.0);
        md5.setLayoutY(151.0);
        sha.setLayoutX(550.0);
        sha.setLayoutY(193.0);
        md5.setSelected(true);
        md5.setTextFill(Color.web("#f5f8fa"));
        md5.setEffect(new Glow());
        md5.setCursor(Cursor.HAND);
        sha.setTextFill(Color.web("#f5f8fa"));
        sha.setEffect(new Glow());
        sha.setCursor(Cursor.HAND);
        dark.setLayoutX(200.0);
        dark.setLayoutY(620.0);
        dark.onInputMethodTextChangedProperty();
        dark.setTextFill(Color.web("#f5f8fa"));
        dark.setEffect(new Glow());
        dark.setCursor(Cursor.HAND);
        dark.setSelected(true);
        light.setLayoutX(90.0);
        light.setLayoutY(620.0);
        light.onInputMethodTextChangedProperty();
        light.setTextFill(Color.web("#f5f8fa"));
        light.setEffect(new Glow());
        light.setCursor(Cursor.HAND);
        selectAll.setLayoutX(635);
        selectAll.setLayoutY(610);
        selectAll.setTextFill(Color.web("#f5f8fa"));
        selectAll.setCursor(Cursor.HAND);
        selectAll.setEffect(new Glow());
        selectAll.onInputMethodTextChangedProperty();
        selectAll.setDisable(true);

        button.setOnAction(ActionEvent -> {
            final DirectoryChooser directoryChooser = new DirectoryChooser();     //غرض من صف DirectoryChooser لاختيار المسار
            Stage stage = (Stage) root.getScene().getWindow();                // من أجل عرض النافذة لاختيار المسار
            File file = directoryChooser.showDialog(stage);                       // من أجل اختيار المسار
            if (file != null) {
                textField.setText(file.getAbsolutePath());
            }
            button.requestFocus();
        });


        scan.setOnAction(ActionEvent -> {
            tableView.getItems().clear();
            File file = new File(textField.getText());
            selectAll.setSelected(false);
            if(file.exists()){
            main2.path = textField.getText();
            if(md5.isSelected())main2.hash="MD5";else main2.hash="SHA-256";
            if(subDirectory.isSelected())main2.is=true;else main2.is=false;
            StopWatch stopWatch=new StopWatch();
            Alert alert = new Alert(Alert.AlertType.INFORMATION); // Popup Alert for processing
            alert.setTitle("Processing...");
            alert.show();
            stopWatch.start();
            try {
                main2.main();
            } catch (Exception e) {
                e.printStackTrace();
            }
            stopWatch.stop();
            alert.close();
            Alert alert1=new Alert(Alert.AlertType.NONE,"",ButtonType.CLOSE);
            alert1.setContentText("Time in Milliseconds: "+ Long.toString(stopWatch.getElapsedTime())+"\n"+"Time in Second: "+Long.toString(stopWatch.getElapsedTimeSecs()));
            alert1.show();}

            else if (textField.getText()==null){Alert alert=new Alert(Alert.AlertType.NONE,"Empty Field!",ButtonType.CLOSE);alert.setTitle("Error!");
                alert.show();}
            else{
                Alert alert=new Alert(Alert.AlertType.NONE,"Wrong Path!",ButtonType.CLOSE);alert.setTitle("Error!");
                alert.show();
            }
            ObservableList<Finder.Filee>item=tableView.getItems();
            if (!item.isEmpty()) {
                selectAll.setDisable(false);
                if (selectAll.isSelected()) {
                } else
                    selectAll.fire();
                ObservableList<Finder.Filee> filee = tableView.getItems();
                for (Finder.Filee p : filee) {
                    p.getCheckBox().setOnAction(Action -> {
                        Boolean f = false;
                        for (Finder.Filee pp : filee) {
                            if (pp.getCheckBox().isSelected()) {
                                f = true;
                            } else {
                                f = false;
                                break;
                            }
                        }
                        if (f == false) selectAll.setSelected(false);
                        else selectAll.setSelected(true);
                    });
                }
            }});

            dark.setOnAction(ActionEvent -> {
            label1.setTextFill(Color.web("#f5f8fa"));
            label.setTextFill(Color.web("#F5F8FA"));
            button.setStyle("-fx-background-color: #1DA1F2;");
            button.setTextFill(Color.web("#F5F8FA"));
            scan.setStyle("-fx-background-color: #1DA1F2;");
            scan.setTextFill(Color.web("#F5F8FA"));
            textField.setStyle("-fx-background-color: #FFFFFF;");
            root.setStyle("-fx-background-color: #12141d;");
            dark.setTextFill(Color.web("#f5f8fa"));
            light.setTextFill(Color.web("#f5f8fa"));
            selectAll.setTextFill(Color.web("#f5f8fa"));
            md5.setTextFill(Color.web("#f5f8fa"));
            sha.setTextFill(Color.web("#f5f8fa"));
            subDirectory.setTextFill(Color.web("#f5f8fa"));
        });
            light.setOnAction(ActionEvent -> {
            label1.setTextFill(Color.web("#14171A"));
            label.setTextFill(Color.web("#14171A"));
            button.setStyle("-fx-background-color: #1DA1F2;");
            button.setTextFill(Color.web("#FFFFFF"));
            scan.setStyle("-fx-background-color: #1DA1F2;");
            scan.setTextFill(Color.web("#FFFFFF"));
            textField.setStyle("-fx-background-color: #AAB8C2;");
            root.setStyle("-fx-background-color: #FFFFFF;");
            dark.setTextFill(Color.web("#14171A"));
            light.setTextFill(Color.web("#14171A"));
            selectAll.setTextFill(Color.web("#14171A"));
            md5.setTextFill(Color.web("#14171A"));
            sha.setTextFill(Color.web("#14171A"));
            subDirectory.setTextFill(Color.web("#14171A"));
        });

        selectAll.setOnAction(ActionEvent ->{
            ObservableList<Finder.Filee> item;
            item=tableView.getItems();
            for(Finder.Filee items : item){
                if(selectAll.isSelected())
                    items.getCheckBox().setSelected(true);
                else
                    items.getCheckBox().setSelected(false);
            }
        selectAll.requestFocus();
        });

        delete.setOnAction(ActionEvent ->{
            ObservableList<Finder.Filee> dataListRemove = FXCollections.observableArrayList();
            ObservableList<Finder.Filee> item;
            item=tableView.getItems();
            for(Finder.Filee items : item){
                if(items.getCheckBox().isSelected()){
                    try
                    {
                        Files.deleteIfExists(Paths.get(items.getFile().getAbsolutePath()));
                        dataListRemove.add(items);
                    }
                    catch(NoSuchFileException e)
                    {
                        System.out.println("No such file/directory exists");
                    }
                    catch(DirectoryNotEmptyException e)
                    {
                        System.out.println("Directory is not empty.");
                    }
                    catch(IOException e)
                    {
                        System.out.println("Invalid permissions.");
                    }

                }

            }
            selectAll.setSelected(false);
            item.removeAll(dataListRemove);
        });

        root.getChildren().addAll(button, scan, textField, label, label1, tableView, dark, light,delete,selectAll,subDirectory,md5,sha);
        root.requestFocus();


    }


    public Pane getView() {
        return root;
    }

}
