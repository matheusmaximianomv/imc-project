package views;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import models.Person;
import controllers.ListPersons;
import javafx.scene.control.Alert;
/**
 *
 * @author Matheus Max
 */
public class NewPerson extends Application{
    
    private static Stage stage;
    private AnchorPane pane;
    private Label lbName, lbCpf, lbBirth, lbWeight, lbHeight;
    private TextField txName, txCpf, txBirth, txWeight, txHeight;
    private Button btVoltar, btAdicionar;
    private ListPersons listPersons;
    
    @Override
    public void start(Stage stage) throws Exception{
        
        initComponents();
        initListeners();
        
        Scene scene = new Scene(pane);
        
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("IMC - Nova Pessoa");
        stage.show();
        
        initLayout();
        
        NewPerson.stage = stage;
        
    }
   
    /* Initializers */
    private void initComponents() {
        
        pane = new AnchorPane();
        pane.setPrefSize(500, 322);
        
        lbName = new Label("Nome");
        txName = new TextField();
        txName.setPromptText("Digite um nome...");
        
        lbCpf = new Label("CPF");
        txCpf = new TextField();
        txCpf.setPromptText("000.000.000-00");
        
        lbBirth = new Label("Data de Nascimento");
        txBirth = new TextField();
        txBirth.setPromptText("01/01/2019");
        
        lbWeight = new Label("Peso");
        txWeight = new TextField();
        txWeight.setPromptText("Seu peso...");
        
        lbHeight = new Label("Altura");
        txHeight = new TextField();
        txHeight.setPromptText("Sua altura...");
        
        btVoltar = new Button("Voltar");
        btAdicionar = new Button("Salvar");
        
        pane.getChildren().addAll(lbName, txName, lbCpf, txCpf, lbBirth, txBirth, lbWeight, txWeight, lbHeight, txHeight, btVoltar, btAdicionar);
        
    }
    
    private void initLayout(){
        
        lbName.setLayoutX(25);
        lbName.setLayoutY(71);
        txName.setLayoutX(25);
        txName.setLayoutY(93);
        
        lbCpf.setLayoutX(txName.getLayoutX() + txName.getWidth() + 10);
        lbCpf.setLayoutY(71);
        txCpf.setLayoutX(txName.getLayoutX() + txName.getWidth() + 10);
        txCpf.setLayoutY(93);
        
        lbBirth.setLayoutX(txCpf.getLayoutX() + txCpf.getWidth() + 10);
        lbBirth.setLayoutY(71);
        txBirth.setLayoutX(txCpf.getLayoutX() + txCpf.getWidth() + 10);
        txBirth.setLayoutY(93);
        
        lbWeight.setLayoutX(86);
        lbWeight.setLayoutY(157);
        txWeight.setLayoutX(86);
        txWeight.setLayoutY(179);
        
        lbHeight.setLayoutX(txWeight.getLayoutX() + txWeight.getWidth() + 30);
        lbHeight.setLayoutY(157);
        txHeight.setLayoutX(txWeight.getLayoutX() + txWeight.getWidth() + 30);
        txHeight.setLayoutY(179);
        
        btVoltar.setLayoutX((txName.getLayoutX() + txName.getWidth()) - (btVoltar.getWidth() / 2));
        btVoltar.setLayoutY(txHeight.getLayoutY() + 60);
        
        btAdicionar.setLayoutX(btVoltar.getLayoutX() + 139);
        btAdicionar.setLayoutY(txHeight.getLayoutY() + 60);
    }
    
    private void initListeners() {
        btVoltar.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                backToHome();
            }
        });
        
        btAdicionar.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                savePerson();
            }
        });
    }
    
    /* Application Functions */
    private void backToHome() {
        Home home = new Home();
                
        try {
            home.start(new Stage());
            NewPerson.stage.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    private void savePerson() {
        if(fieldsValid()) {
            try {
                Home.getListPersons().addPessoa(registerPerson());
                Home home = new Home();

                home.start(new Stage());
                NewPerson.stage.close();
            } catch(Exception e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Cálculo do IMC");
                alert.setHeaderText(null);
                alert.setContentText("Alguns campos não foram preenchidos corretamente.");

                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Cálculo do IMC");
            alert.setHeaderText(null);
            alert.setContentText("Alguns campos não foram preenchidos corretamente.");

            alert.showAndWait();
        }
    }
    
    /* Auxiliary Functions */
    private boolean fieldsValid() {
        if(!txName.getText().trim().isEmpty() || !txCpf.getText().trim().isEmpty() || !txBirth.getText().trim().isEmpty() || !txWeight.getText().trim().isEmpty() || !txHeight.getText().trim().isEmpty()){
            return true;
        } else {
            return false;
        }
    }
    
    private Person registerPerson() {
        String name = txName.getText(), cpf = txCpf.getText(), dt_birth = txBirth.getText();
        double height = new Double(txHeight.getText()), weight = new Double(txWeight.getText());
        
        Person person = new Person(name,cpf,dt_birth, weight, height);
        
        return person;
    }

}
