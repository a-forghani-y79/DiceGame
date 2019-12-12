package sample;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class mainMenuController implements Initializable {

    @FXML
    JFXButton btnMulty;
    double x = 0;
    double y = 0;
    private multyPlayerController multyPlayerController;


    public static void main(String[] args) {
    }


    @FXML
    public void onClickMultiPlayer() {
        switchOnNew(btnMulty, "FXML/MultiPlayer.fxml");

    }


    public void switchOnNew(JFXButton btn, String FXML) {

        Parent root;

        try {
            Stage stage = (Stage) btn.getScene().getWindow();
            stage.close();


            FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML));

            root = loader.load();
            loader.setController(multyPlayerController);
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


    @FXML
    public void onClickExit() {

        System.exit(0);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
