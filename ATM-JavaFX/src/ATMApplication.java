import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class ATMApplication extends Application {

    public void start(Stage window) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("atm.fxml"));


        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("atm.css").toExternalForm());
        window.setScene(scene);

        Image icon = new Image("atm.png");
        window.getIcons().add(icon);
        window.setTitle("ATM Machine");
        window.setResizable(false);

        window.show();
    }


    public static void main(String[] args){
        launch(args);
    }
}