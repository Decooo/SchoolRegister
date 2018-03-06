/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dziennikelektroniczny;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author Jakub
 */
public class DziennikElektroniczny extends Application {
    
    private static Stage primaryStage;
    private static BorderPane menuLayout;

    
    
    @Override
    public void start(Stage primaryStage) throws Exception {
      
        DziennikElektroniczny.primaryStage=primaryStage;
        DziennikElektroniczny.primaryStage.setTitle("Dziennik elektroniczny studentów");
        
        initMenu();
        showSceny();  
    }

    public static void initMenu() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(DziennikElektroniczny.class.getResource("view/MenuFXML.fxml"));
            menuLayout = (BorderPane) loader.load();

            Scene scene = new Scene(menuLayout);
            primaryStage.setScene(scene);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showSceny(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(DziennikElektroniczny.class.getResource("view/StronaGlownaFXML.fxml"));
            AnchorPane fxmlView = (AnchorPane) loader.load();
            menuLayout.setCenter(fxmlView);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public static BorderPane getMenuLayout() {
        return menuLayout;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
