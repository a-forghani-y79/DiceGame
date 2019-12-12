package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class welcome extends Application {

    private double x = 0;
    private double y = 0;

    private Stage secondaryStage;
    Parent root;


    @Override
    public void start(Stage primaryStage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("FXML/Welcome.fxml"));


        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - x);
            primaryStage.setY(event.getScreenY() - y);
        });

        primaryStage.setScene(new Scene(root));


        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.TRANSPARENT);


        primaryStage.show();

    }

    public void openCustomerPanel(String fxml, int delay) {
        TimerTask timerTask = new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {

                        secondaryStage = new Stage();
                        Parent root = null;
                        try {
                            root = FXMLLoader.load(getClass().getResource(fxml));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        root.setOnMousePressed(event -> {
                            x = event.getSceneX();
                            y = event.getSceneY();
                        });
                        root.setOnMouseDragged(event -> {
                            secondaryStage.setX(event.getScreenX() - x);
                            secondaryStage.setY(event.getScreenY() - y);
                        });
                        Scene scene = new Scene(root);
                        secondaryStage.setScene(scene);
                        secondaryStage.setResizable(false);
                        secondaryStage.initStyle(StageStyle.TRANSPARENT);
                        secondaryStage.show();
                    }
                });

            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, delay);


    }


    public static void main(String[] args) {
        launch(args);

    }
}
