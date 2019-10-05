package views;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import models.Person;
import utils.IMC;
import controllers.ListPersons;
import java.text.DecimalFormat;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.cell.PropertyValueFactory;

public class Home extends Application {
    
    private AnchorPane pane;
    private TableView<PersonsProperty> tbPessoas;
    private TableColumn<PersonsProperty, String> columnName;
    private TableColumn<PersonsProperty, String> columnCpf;
    private TableColumn<PersonsProperty, String> columnBirth;
    private TableColumn<PersonsProperty, Double> columnWeight;
    private TableColumn<PersonsProperty, Double> columnHeight;
    private Button btCalcular, btAdicionar;
    private IMC imc;
    private static ListPersons listPersons;
    private static Stage stage;
    private ObservableList<PersonsProperty> listItens = FXCollections.observableArrayList();
    
    /* Inner Class */
    public class PersonsProperty {
        
        private SimpleStringProperty name, cpf, dt_birth;
        private SimpleDoubleProperty weight, height;

        PersonsProperty(String name, String cpf, String dt_birth, double weight, double height) {
            this.name = new SimpleStringProperty(name);
            this.cpf = new SimpleStringProperty(cpf);
            this.dt_birth = new SimpleStringProperty(dt_birth);
            this.weight = new SimpleDoubleProperty(weight);
            this.height = new SimpleDoubleProperty(height);
        }
        
        public String getName() {
            return this.name.get();
        }
        
        public void setName(String name) {
            this.name.set(name);
        }
        
        public String getCpf() {
            return this.cpf.get();
        }
        
        public void setCpf(String cpf) {
            this.cpf.set(cpf);
        }
        
        public String getDt_birth() {
            return this.dt_birth.get();
        }
        
        public void setDt_birth(String dt_birth) {
            this.dt_birth.set(dt_birth);
        }
        
        public double getWeight() {
            return this.weight.get();
        }
        
        public void setWeight(double weight) {
            this.weight.set(weight);
        }
        
        public double getHeight() {
            return this.height.get();
        }
        
        public void setHeight(double height) {
            this.height.set(height);
        }
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        
        initComponents();
        initListeners();
        
        Scene scene = new Scene(pane);
        
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("IMC");
        stage.show();
        
        initLayout();
        initPersons();
        
        Home.stage = stage;
    }
    
    /* Initializers */
    private void initComponents() {
        
        pane = new AnchorPane();
        pane.setPrefSize(700, 500);
        
        tbPessoas = new TableView<PersonsProperty>();
        tbPessoas.setPrefSize(580, 350);
        tbPessoas.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        columnName = new TableColumn<PersonsProperty, String>();
        columnName.setCellValueFactory(new PropertyValueFactory<PersonsProperty, String>("name"));
        columnName.setText("Nome");
        
        columnCpf = new TableColumn<PersonsProperty, String>();
        columnCpf.setCellValueFactory(new PropertyValueFactory<PersonsProperty, String>("cpf"));
        columnCpf.setText("CPF");
        
        columnBirth = new TableColumn<PersonsProperty, String>();
        columnBirth.setCellValueFactory(new PropertyValueFactory<PersonsProperty, String>("dt_birth"));
        columnBirth.setText("Data de Nascimento");
        
        columnWeight = new TableColumn<PersonsProperty, Double>();
        columnWeight.setCellValueFactory(new PropertyValueFactory<PersonsProperty, Double>("weight"));
        columnWeight.setText("Peso");
        
        columnHeight = new TableColumn<PersonsProperty, Double>();
        columnHeight.setCellValueFactory(new PropertyValueFactory<PersonsProperty, Double>("height"));
        columnHeight.setText("Altura");
        
        btAdicionar = new Button("Adicionar Pessoa");
        btCalcular = new Button("Calcular IMC");
        
        tbPessoas.getColumns().addAll(columnName, columnCpf, columnBirth, columnWeight, columnHeight);
        pane.getChildren().addAll(tbPessoas, btAdicionar, btCalcular);
        
    }
    
    private void initLayout() {
        
        tbPessoas.setLayoutX((pane.getWidth() - tbPessoas.getWidth()) / 2);
        tbPessoas.setLayoutY(10);
        
        btCalcular.setLayoutX(tbPessoas.getLayoutX());
        btCalcular.setLayoutY(tbPessoas.getLayoutY() + tbPessoas.getHeight() + 20);
        
        btAdicionar.setLayoutX(tbPessoas.getLayoutX() + tbPessoas.getWidth() - btAdicionar.getWidth());
        btAdicionar.setLayoutY(btCalcular.getLayoutY());
    }
    
    private void initListeners() {
        
        btCalcular.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                calculateIMC(tbPessoas.getSelectionModel().getSelectedItem());
            }            
        });
           
        btAdicionar.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                createPerson();
            }
        });
        
    }
    
    private void initPersons() {
        
        listPersons = new ListPersons();
        
        for (Person p : listPersons.getPessoas()) {
            listItens.add(new PersonsProperty(p.getName(), p.getCpf(), p.getDt_birth(), p.getWeight(), p.getHeight()));
        }
        
        tbPessoas.setItems(listItens);
    }
    
    /* Application Functions */
    private void calculateIMC(PersonsProperty person) {
        imc = new IMC(person.getHeight(), person.getWeight());
        DecimalFormat decimal = new DecimalFormat("0.00");

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cálculo do IMC");
        alert.setHeaderText(null);
        alert.setContentText("O IMC de "+person.getName()+" é de "+decimal.format(imc.number())+". Ele está "+imc.result());

        alert.showAndWait();
    }
    
    private void createPerson() {
        NewPerson newPerson = new NewPerson();
        try {
            newPerson.start(new Stage());
            Home.stage.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    /* Getter Function */
    public static ListPersons getListPersons() {
        return Home.listPersons;
    }
    
    /* Main */
    public static void main(String[] args) {
        launch(args);
    }
    
}
