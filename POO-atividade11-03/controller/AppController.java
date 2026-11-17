package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.collections.FXCollections;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.List;

import model.InimigoRepositorio;
import view.AppView;

public class AppController implements Initializable {
    @FXML
    private TableView<view.Inimigo> tabela;
    @FXML
    private TableColumn<view.Inimigo, Integer> idCol;
    @FXML
    private TableColumn<view.Inimigo, String> nomeCol;
    @FXML
    private TableColumn<view.Inimigo, Integer> vidaCol;
    @FXML
    private TableColumn<view.Inimigo, Integer> danoCol;
    @FXML
    private TextField idField;
    @FXML
    private TextField nomeField;
    @FXML
    private TextField vidaField;
    @FXML
    private TextField danoField;
    @FXML
    private Button adicionarButton;
    @FXML
    private Button atualizarButton;
    @FXML
    private Button deletarButton;    
    @FXML
    private Button cancelarButton;    
    @FXML
    private Button salvarButton;
    
    AppView appView;
    
    private static model.Database database = new model.Database("app.sqlite");
    private static model.InimigoRepositorio inimigoRepo = 
        new model.InimigoRepositorio(database);
        
    public AppController() {
        this.appView = new AppView();
    }
    
    public static void main(String[] args) throws Exception {
        AppController appController = new AppController();
        appController.appView.run(args);
    }
    
    private void desabilitarBotoes(boolean adicionar, boolean atualizar, boolean deletar, boolean cancelar, boolean salvar) {
        adicionarButton.setDisable(adicionar);
        atualizarButton.setDisable(atualizar);
        deletarButton.setDisable(deletar);
        cancelarButton.setDisable(cancelar);
        salvarButton.setDisable(salvar);        
    }
    
    private void desabilitarCampos(boolean desabilitado) {
        nomeField.setDisable(desabilitado);
        danoField.setDisable(desabilitado);
        vidaField.setDisable(desabilitado);
    }
    
    private void limparCampos() {
        idField.setText("");
        vidaField.setText("");
        nomeField.setText("");
        danoField.setText("");        
    }
    
    @FXML
    public void onDeletarButtonAction() {
        view.Inimigo selecionado = tabela.getSelectionModel().getSelectedItem();
        if (selecionado == null) {
            return;
        }

        try {
            model.Inimigo inimigo = new model.Inimigo();
            inimigo.setId(selecionado.getId());
            inimigo.setNome(selecionado.getNome());
            inimigo.setVida(selecionado.getVida());
            inimigo.setDano(selecionado.getDano());

            // Remove do banco (verifique se o método no seu InimigoRepositorio se chama delete)
            inimigoRepo.delete(inimigo);

            // Remove da tabela visual e reseta a tela
            tabela.getItems().remove(selecionado);
            tabela.getSelectionModel().select(-1);
            limparCampos();
            desabilitarCampos(true);
            desabilitarBotoes(false, true, true, true, true);
        } catch (Exception e) {
            new Alert(AlertType.ERROR, "Erro ao deletar: " + e.getMessage()).show();
        }
    }
    
    @FXML
    public void onCancelarButtonAction() {
        desabilitarCampos(true);
        desabilitarBotoes(false,true,true,true,true);
        limparCampos();
        tabela.getSelectionModel().select(-1);        
    }
    
    @FXML
    public void onAtualizarButtonAction() {
        desabilitarCampos(false);
        desabilitarBotoes(true, true, true, false, false);
    }

    @FXML
    public void onSalvarButtonAction() {
        try {
            model.Inimigo inimigo = new model.Inimigo();
            inimigo.setNome(nomeField.getText());
            inimigo.setVida(Integer.parseInt(vidaField.getText()));
            inimigo.setDano(Integer.parseInt(danoField.getText()));

            view.Inimigo selecionado = tabela.getSelectionModel().getSelectedItem();

            if (selecionado == null) {
                // FLUXO DE CRIAÇÃO (Adicionar)
                if (!idField.getText().isEmpty()) {
                    inimigo.setId(Integer.parseInt(idField.getText()));
                }
                model.Inimigo inimigoSalvo = inimigoRepo.create(inimigo);
                view.Inimigo inimigoView = modelToView(inimigoSalvo);
                tabela.getItems().add(inimigoView);
                tabela.getSelectionModel().select(inimigoView);
            } else {
                // FLUXO DE ATUALIZAÇÃO (Atualizar)
                inimigo.setId(Integer.parseInt(idField.getText()));
                inimigoRepo.update(inimigo); // certifique-se de que o método no repositório chama update

                // Atualiza os dados na tabela visual
                int index = tabela.getSelectionModel().getSelectedIndex();
                view.Inimigo inimigoAtualizado = modelToView(inimigo);
                tabela.getItems().set(index, inimigoAtualizado);
                tabela.getSelectionModel().select(index);
            }

            desabilitarCampos(true);
            desabilitarBotoes(false, false, false, true, true);
        } catch (Exception e) {
            new Alert(AlertType.ERROR, "Erro ao salvar: " + e.getMessage()).show();
        }
    }   
    
    @FXML
    public void onAdicionarButtonAction() {
        tabela.getSelectionModel().select(-1);
        desabilitarCampos(false);
        desabilitarBotoes(true,true,true,false,false);
        limparCampos();
    }

    private void handleInimigoSelected(view.Inimigo newSelection) {
    if (newSelection != null) {
        idField.setText(Integer.toString(newSelection.getId()));
        vidaField.setText(Integer.toString(newSelection.getVida()));
        nomeField.setText(newSelection.getNome());
        danoField.setText(Integer.toString(newSelection.getDano()));
        desabilitarBotoes(false, false, false, true, true);
    }
}
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idCol.setCellValueFactory(
                new PropertyValueFactory<>("id"));
        nomeCol.setCellValueFactory(
                new PropertyValueFactory<>("nome"));
        vidaCol.setCellValueFactory(
                new PropertyValueFactory<>("vida"));
        danoCol.setCellValueFactory(
                new PropertyValueFactory<>("dano"));
        tabela.setItems(loadAllInimigos());
        tabela.getSelectionModel().selectedItemProperty().addListener(
            (observableValue, oldSelection, newSelection) -> {
                handleInimigoSelected(newSelection);
            });
    }
    
    private view.Inimigo modelToView(model.Inimigo inimigo) {
        return new view.Inimigo(
            inimigo.getId(),
            inimigo.getNome(),
            inimigo.getVida(),
            inimigo.getDano()
        );
    }
    
    private ObservableList<view.Inimigo> loadAllInimigos() {
        ObservableList<view.Inimigo> lista = 
            FXCollections.observableArrayList();
        List<model.Inimigo> listaFromDatabase = inimigoRepo.loadAll();
        for(model.Inimigo inimigo: listaFromDatabase) {
            lista.add(modelToView(inimigo));
        }
        return lista;
    }
}