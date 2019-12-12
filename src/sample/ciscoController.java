package sample;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ciscoController implements Initializable {


    public Text goalC;
    public ImageView imageCP1;
    public ImageView imageCP2;
    public Text nameCP1;
    public Text nameCP2;

    public Text txtStatus;


    public JFXButton btnCRoll;
    public Text totalCP1;

    public Text totalCP2;
    public JFXButton btnMainMenu;
    int total1 = 0;
    int total2 = 0;
    boolean flag = true;


    static int goal;
    static String p1Name;
    static String p1Image;
    static String p2Name;
    static String p2Image;
    double x = 0, y = 0;
    private Object mainMenuController;


    public void onClickRoll() {


        if (total2 < goal && total1 < goal) {
            if (flag) {
                int dice = (int) (Math.random() * 5 + 1);
                total1 += dice;
                totalCP1.setText("Total Score is: " + total1);


                flag = !flag;
            } else {
                int dice = (int) (Math.random() * 5 + 1);
                total2 += dice;
                totalCP2.setText("Total Score is: " + total2);
                flag = !flag;
            }
        }
        if (!(total2 < goal && total1 < goal)) {
            String winner = "";
            if (total1 >= goal)
                winner = p1Name;
            if (total2 >= goal)
                winner = p2Name;

            txtStatus.setText(winner + " is Win !");
            btnCRoll.setDisable(true);
            btnMainMenu.setDisable(false);
            btnMainMenu.setVisible(true);
        }

    }

    public void onClickMAinMenu() {

        Parent root;

        try {

            Stage stage = (Stage) btnMainMenu.getScene().getWindow();
            stage.close();


            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/MainMenu.fxml"));

            root = loader.load();
            loader.setController(mainMenuController);
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


    public static void main(String[] args) {
        goal = Integer.valueOf(args[0]);
        p1Name = args[1];
        p1Image = args[2];
        p2Name = args[3];
        p2Image = args[4];


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File file1 = new File("src/sample/PNG/AV" + p1Image + ".png");
        imageCP1.setImage(new Image(file1.toURI().toString()));

        File file2 = new File("src/sample/PNG/AV" + p2Image + ".png");
        imageCP2.setImage(new Image(file2.toURI().toString()));

        goalC.setText(String.valueOf(goal));

        nameCP1.setText(p1Name);
        nameCP2.setText(p2Name);
        totalCP1.setText("Total Score is: " + total1);
        totalCP2.setText("Total Score is: " + total2);
    }


}
