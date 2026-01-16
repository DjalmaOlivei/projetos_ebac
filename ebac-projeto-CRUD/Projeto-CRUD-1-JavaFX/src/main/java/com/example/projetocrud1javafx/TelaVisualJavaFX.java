package com.example.projetocrud1javafx;

import com.example.projetocrud1javafx.dao.ClienteMapDAO;
import com.example.projetocrud1javafx.dao.IClienteDAO;
import com.example.projetocrud1javafx.domain.Cliente;
import com.example.projetocrud1javafx.exceptions.InvalidOptionException;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import com.example.projetocrud1javafx.*;

import javax.swing.*;
import java.util.Map;
import java.util.function.Function;

public class TelaVisualJavaFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    ClienteMapDAO iClienteDAO = new ClienteMapDAO();

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Cadastro de Pessoas - Visual");



        // Criando os campos do formulário
        TextField nomeField = new TextField();
        TextField cpfField = new TextField();
        TextField telefoneField = new TextField();
        TextField enderecoField = new TextField();
        TextField numeroField = new TextField();
        TextField cidadeField = new TextField();
        TextField estadoField = new TextField();

        Runnable limparCampos = () -> {
            nomeField.clear();
            cpfField.clear();
            telefoneField.clear();
            enderecoField.clear();
            numeroField.clear();
            cidadeField.clear();
            estadoField.clear();
        };


        // Criando a tabela com duas colunas
        TableView<Map.Entry<Long, Cliente>> tabela = new TableView<>();
        
        TableColumn<Map.Entry<Long, Cliente>, String> nomeCol = new TableColumn<>("Nome");
        nomeCol.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getValue().getNome()));
        
        TableColumn<Map.Entry<Long, Cliente>, String> cpfCol = new TableColumn<>("CPF");
        cpfCol.setCellValueFactory(cellData ->
               new SimpleStringProperty( String.valueOf(cellData.getValue().getValue().getCpf())));
        
        tabela.getColumns().addAll(nomeCol, cpfCol);
        tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Adicionando dados de exemplo (apenas para visualização)
        ObservableList<Map.Entry<Long, Cliente>> items =
         FXCollections.observableArrayList(iClienteDAO.buscarTodos().entrySet());
        tabela.setItems(items);

        // Seleção na tabela
        tabela.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                nomeField.setText(newSelection.getValue().getNome());
                cpfField.setText(String.valueOf(newSelection.getValue().getCpf()));
                telefoneField.setText(String.valueOf(newSelection.getValue().getTel()));
                enderecoField.setText(newSelection.getValue().getEnd());
                numeroField.setText(String.valueOf(newSelection.getValue().getNumero()));
                cidadeField.setText(newSelection.getValue().getCidade());
                estadoField.setText(newSelection.getValue().getEstado());
            }
        });

        // Criando os botões
        Button cadastrarBtn = new Button("Cadastrar");
        Button alterarBtn = new Button("Alterar");
        Button excluirBtn = new Button("Excluir");

        cadastrarBtn.setOnAction( e -> {
            try {
                iClienteDAO.cadastrar(new Cliente(nomeField.getText(), Long.valueOf(cpfField.getText()), Long.valueOf(telefoneField.getText()), enderecoField.getText(), Integer.valueOf(numeroField.getText()), cidadeField.getText(), estadoField.getText()));
            }catch (Exception e1){
                JOptionPane.showMessageDialog(null,"Input invalido \n Tente novamente","Erro",JOptionPane.ERROR_MESSAGE);
                return;
            }

            ObservableList<Map.Entry<Long, Cliente>> newItems =
                    FXCollections.observableArrayList(iClienteDAO.buscarTodos().entrySet());
            tabela.getItems().clear();
            tabela.getItems().addAll(newItems);
            limparCampos.run();
            return;
        } );

        alterarBtn.setOnAction(e -> {
            try {
                iClienteDAO.alterar(new Cliente(nomeField.getText(), Long.valueOf(cpfField.getText()), Long.valueOf(telefoneField.getText()), enderecoField.getText(), Integer.valueOf(numeroField.getText()), cidadeField.getText(), estadoField.getText()));

            }catch (Exception e1){
                JOptionPane.showMessageDialog(null,"Input invalido \n Tente novamente","Erro",JOptionPane.ERROR_MESSAGE);
                return;
            }
            ObservableList<Map.Entry<Long, Cliente>> newItems =
                    FXCollections.observableArrayList(iClienteDAO.buscarTodos().entrySet());

            tabela.getItems().clear();
            tabela.getItems().addAll(newItems);
            limparCampos.run();
            return;
        });

        excluirBtn.setOnAction(e -> {
            try {
                iClienteDAO.excluir(Long.valueOf(cpfField.getText()));
            }catch (Exception ee){
                JOptionPane.showMessageDialog(null,"Nenhum cliente selecionado","Erro",JOptionPane.ERROR_MESSAGE);
            }
            ObservableList<Map.Entry<Long, Cliente>> newItems =
                    FXCollections.observableArrayList(iClienteDAO.buscarTodos().entrySet());
            tabela.getItems().clear();
            tabela.getItems().addAll(newItems);
            limparCampos.run();
            return;
        });

        
        HBox botoesBox = new HBox(10, cadastrarBtn, alterarBtn, excluirBtn);

        // Organizando o layout
        VBox formBox = new VBox(10,
            criarLinhaFormulario("Nome:", nomeField),
            criarLinhaFormulario("CPF:", cpfField),
            criarLinhaFormulario("Telefone:", telefoneField),
            criarLinhaFormulario("Endereço:", enderecoField),
            criarLinhaFormulario("Número:", numeroField),
            criarLinhaFormulario("Cidade:", cidadeField),
            criarLinhaFormulario("Estado:", estadoField)
        );
        formBox.setPadding(new Insets(20));

        VBox layoutPrincipal = new VBox(20, formBox, tabela, botoesBox);
        layoutPrincipal.setPadding(new Insets(20));

        // Configurando a cena
        Scene scene = new Scene(layoutPrincipal, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private HBox criarLinhaFormulario(String label, TextField field) {
        return new HBox(10, new Label(label), field);
    }


}