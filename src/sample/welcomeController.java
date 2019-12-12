package sample;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSpinner;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;


public class welcomeController implements Initializable {
    private double x = 0;
    private double y = 0;
    mainMenuController mainMenuController;

    @FXML
    Label lblDice;
    @FXML
    JFXButton btnStart;
    @FXML
    Label lblWelcom;
    @FXML
    Label lblGame;
    @FXML
    JFXSpinner spinner;


    FadeTransition fadeTransitionWelcom = new FadeTransition();
    FadeTransition fadeTransitionDice = new FadeTransition();
    FadeTransition fadeTransitionGame = new FadeTransition();
    FadeTransition fadeTransitionSpinner = new FadeTransition();
    FadeTransition fadeTransitionButton = new FadeTransition();

    TranslateTransition translateTransitionWelcom = new TranslateTransition();
    TranslateTransition translateTransitionDice = new TranslateTransition();
    TranslateTransition translateTransitionGame = new TranslateTransition();


    public void setTranslateTransition(TranslateTransition translateTransition, Node node, double duration, double fromX, double toX, double fromY, double toY) {

        translateTransition.setNode(node);
        translateTransition.setFromX(fromX);
        translateTransition.setFromY(fromY);
        translateTransition.setToX(toX);
        translateTransition.setToY(toY);
        translateTransition.setDuration(Duration.millis(duration));
        // translateTransition.setDelay(Duration.millis(750));

    }

    public void setFadeTransition(FadeTransition fadeTransition, Node node, double duration, double fromValue, double toValue) {
        fadeTransition.setByValue(fromValue);
        fadeTransition.setToValue(toValue);
        fadeTransition.setNode(node);
        fadeTransition.setDuration(Duration.millis(duration));
        //  fadeTransition.setDelay(Duration.millis(750));
    }


    public void onClick(ActionEvent event) {

        fadeTransitionButton.play();
        fadeTransitionWelcom.play();
        fadeTransitionDice.play();
        fadeTransitionGame.play();
        fadeTransitionSpinner.play();
        translateTransitionDice.play();
        translateTransitionGame.play();
        translateTransitionWelcom.play();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() ->{switchOnMainMenu();} );
            }
        },3000);

    }

    Stage stage;
    public void switchOnMainMenu() {

        Parent root;

        try {
            stage = (Stage) btnStart.getScene().getWindow();
            stage.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/mainMenu.fxml"));
            loader.setController(mainMenuController);
            root = loader.load();
            stage = new Stage();
            root.setOnMousePressed(event -> {
                x = event.getSceneX();
                y = event.getSceneY();
            });
            //Stage finalStage = stage;
            root.setOnMouseDragged(event -> {
               stage.setX(event.getScreenX() - x);
               stage.setY(event.getScreenY() - y);
            });
            stage.setResizable(false);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(new Scene(root));
            stage.show();




        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Animation welcome
        setFadeTransition(fadeTransitionWelcom, lblWelcom, 900, 0, 1);
        setTranslateTransition(translateTransitionWelcom, lblWelcom, 900, 0, 0, 0, -30);

        //Animation Dice
        setFadeTransition(fadeTransitionDice, lblDice, 900, 0, 1);
        setTranslateTransition(translateTransitionDice, lblDice, 900, 0, 56, 0, 0);

        //Animation Game
        setFadeTransition(fadeTransitionGame, lblGame, 900, 0, 1);
        setTranslateTransition(translateTransitionGame, lblGame, 900, 0, -55, 0, 0);

        //Animation Spinner
        setFadeTransition(fadeTransitionSpinner, spinner, 950, 0, 1);

        //Animation Button
        setFadeTransition(fadeTransitionButton, btnStart, 300, 1, 0);


    }


}
