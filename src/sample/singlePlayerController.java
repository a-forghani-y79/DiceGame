package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class singlePlayerController implements Initializable {


    public ImageView imgPreviewP1;
    
    public JFXTextField nameP1;
    public JFXTextField goal;
    public JFXTextField nameP2;
    public JFXButton btnLetsGo;
    String[] data = new String[5];
    int imageNumberP1 = 1;
    int imageNumberP2 = 7;
    double x = 0, y = 0;
    private Object ciscoController;


    public void selectImageP1(ActionEvent event) {

        MenuItem menuItem = (MenuItem) event.getSource();
        switch (menuItem.getId().charAt(8)) {
            case '1':
                File file1 = new File("src/sample/PNG/AV1.png");
                imgPreviewP1.setImage(new Image(file1.toURI().toString()));
                imageNumberP1 = 1;
                break;
            case '2':
                File file2 = new File("src/sample/PNG/AV2.png");
                imgPreviewP1.setImage(new Image(file2.toURI().toString()));
                imageNumberP1 = 2;

                break;
            case '3':
                File file3 = new File("src/sample/PNG/AV3.png");
                imgPreviewP1.setImage(new Image(file3.toURI().toString()));
                imageNumberP1 = 3;

                break;
            case '4':
                File file4 = new File("src/sample/PNG/AV4.png");
                imgPreviewP1.setImage(new Image(file4.toURI().toString()));
                imageNumberP1 = 4;

                break;
            case '5':
                File file5 = new File("src/sample/PNG/AV5.png");
                imgPreviewP1.setImage(new Image(file5.toURI().toString()));
                imageNumberP1 = 5;

                break;
            case '6':
                File file6 = new File("src/sample/PNG/AV6.png");
                imgPreviewP1.setImage(new Image(file6.toURI().toString()));
                imageNumberP1 = 6;

                break;


            default:
                break;
        }


    }


    public void onClickBtnLetsGo() {
        data[0] = goal.getText();
        data[1] = nameP1.getText();
        data[2] = String.valueOf(imageNumberP1);
        data[3] = nameP2.getText();
        data[4] = String.valueOf(imageNumberP2);

        Parent root;

        try {
            sample.ciscoController.main(data);
            Stage stage = (Stage) btnLetsGo.getScene().getWindow();
            stage.close();


            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/Cisco.fxml"));

            root = loader.load();
            loader.setController(ciscoController);
            stage = new Stage();
            root.setOnMousePressed(event -> {
                x = event.getSceneX();
                y = event.getSceneY();
            });
            Stage finalStage = stage;
            root.setOnMouseDragged(event -> {
                finalStage.setX(event.getScreenX() - x);
                finalStage.setY(event.getScreenY() - y);
            });
            finalStage.setResizable(false);
            finalStage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    
    
    
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
}
