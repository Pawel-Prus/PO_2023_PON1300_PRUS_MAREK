package agh.ics.oop.presenter;
import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.model.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.*;


public class SimulationPresenter implements MapChangeListener {

    @FXML
    private TextField movementsListTextField;
    @FXML
    private Label movementsDescriptionLabel;
    @FXML
    private GridPane mapGrid;

    @FXML
    private Label infoLabel;
    private WorldMap worldMap;
    private boolean firstStartClicked = true;

    public void setWorldMap(WorldMap map) {
        this.worldMap = map;
    }

    public void drawMap(WorldMap worldMap) {
        infoLabel.setText("");
        clearGrid();
        Boundary mapBoundary = worldMap.getCurrentBounds();
        int lowerLeftLimitX = mapBoundary.lowerLeftLimit().getX();
        int lowerLeftLimitY = mapBoundary.lowerLeftLimit().getY();
        int upperRightLimitX = mapBoundary.upperRightLimit().getX();
        int upperRightLimitY = mapBoundary.upperRightLimit().getY();
        int cellSize = 30;
        GridPane.setHalignment(mapGrid, HPos.CENTER);


        for(int y=upperRightLimitY ; y >= lowerLeftLimitY;y--){
            mapGrid.getRowConstraints().add(new RowConstraints(cellSize));
            Label label = new Label();
            label.setText(String.valueOf(y));
            GridPane.setHalignment(label, HPos.CENTER);
            GridPane.setValignment(label, VPos.CENTER);
            mapGrid.add(label, 0, upperRightLimitY-y+1);

        }

        for(int x=lowerLeftLimitX ; x <= upperRightLimitX;x++){
            mapGrid.getColumnConstraints().add(new ColumnConstraints(cellSize));
            Label label = new Label();
            label.setText(String.valueOf(x));
            GridPane.setHalignment(label, HPos.CENTER);
            GridPane.setValignment(label, VPos.CENTER);
            mapGrid.add(label,  x-lowerLeftLimitX+1, 0);

        }
        mapGrid.getRowConstraints().add(new RowConstraints(cellSize));
        mapGrid.getColumnConstraints().add(new ColumnConstraints(cellSize));
        Label label = new Label();
        label.setText("y/x");
        GridPane.setHalignment(label, HPos.CENTER);
        GridPane.setValignment(label, VPos.CENTER);
        mapGrid.add(label,0,0);


        for(int x=lowerLeftLimitX ; x <= upperRightLimitX;x++){
            for(int y=upperRightLimitY ; y >= lowerLeftLimitY;y--){
                Object object  = worldMap.objectAt(new Vector2d(x, y));
                if (object != null){
                    Label Elemlabel = new Label();
                    Elemlabel.setText(object.toString());
                    GridPane.setHalignment(Elemlabel, HPos.CENTER);
                    GridPane.setValignment(Elemlabel, VPos.CENTER);
                    mapGrid.add(Elemlabel, x-lowerLeftLimitX+1, upperRightLimitY-y+1);
                }

                }
            }

    }

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        Platform.runLater(()-> {
            drawMap(worldMap);
            movementsDescriptionLabel.setText(message);
        });
    }


    private void clearGrid() {
        mapGrid.getChildren().retainAll(mapGrid.getChildren().get(0)); // hack to retain visible grid lines
        mapGrid.getColumnConstraints().clear();
        mapGrid.getRowConstraints().clear();
    }

//    public void onSimulationStartClicked() {
//        String moveList = movementsListTextField.getText();
//        String[] moves = moveList.split("");
//        List<MoveDirection> directions;
//        directions = OptionsParser.parseToEnum(moves);
//        GrassField worldMap2 = new GrassField(10, UUID.randomUUID());
//        worldMap2.addObserver(new ConsoleMapDisplay());
//        worldMap2.addObserver(this);
//        setWorldMap(worldMap2);
//        ArrayList<Vector2d> positions = new ArrayList<>(Arrays.asList(new Vector2d(2, 2), new Vector2d(3, 4)));
//        Simulation simulation1 = new Simulation(directions,positions, worldMap);
//        ArrayList<Simulation> simulations = new ArrayList<>(List.of(simulation1));
//        SimulationEngine multipleSimulations = new SimulationEngine(simulations);
//        multipleSimulations.runAsyncInThreadPool();
//
//    }
    public void onSimulationStartClicked() {
        if (firstStartClicked) {
            String moveList = movementsListTextField.getText();
            String[] moves = moveList.split("");
            //configureSimulation(moves);
            firstStartClicked = false;
        } else {
            openSimulationWindow();
        }
    }

    private void openSimulationWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("simulation.fxml"));
            BorderPane viewRoot = loader.load();


            Stage stage = new Stage();
            configureStage(stage, viewRoot);


            firstStartClicked = !firstStartClicked;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void configureStage(Stage primaryStage, BorderPane viewRoot) {
        var scene = new Scene(viewRoot);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Simulation app");
        primaryStage.minWidthProperty().bind(viewRoot.minWidthProperty());
        primaryStage.minHeightProperty().bind(viewRoot.minHeightProperty());
        primaryStage.showAndWait();
    }


    private void configureSimulation(Integer[] moves) {
        List<MoveDirection> directions = OptionsParser.parseToEnum(moves);
        List<Vector2d> positions =  List.of(new Vector2d(2, 3), new Vector2d(3, 4));
        RectangularMap worldMap1 = new RectangularMap(100, 100, UUID.randomUUID());
        setWorldMap(worldMap1);
        ConsoleMapDisplay consoleMapDisplay = new ConsoleMapDisplay();
        worldMap1.addObserver(consoleMapDisplay);
        worldMap1.addObserver(this);
        GrassField worldMap2 = new GrassField(10, UUID.randomUUID());
        worldMap2.addObserver(consoleMapDisplay);
        worldMap2.addObserver(this);
        Simulation simulation = new Simulation(directions, positions,worldMap2);
        ArrayList<Simulation> simulations = new ArrayList<>(List.of(simulation));
        SimulationEngine multipleSimulations = new SimulationEngine(simulations);
        multipleSimulations.runAsyncInThreadPool();
    }


    public TextField getMovementsListTextField() {
        return movementsListTextField;
    }
}

