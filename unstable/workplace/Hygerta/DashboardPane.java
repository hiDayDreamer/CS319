import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.scene.text.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;

public class DashboardPane extends Pane {
    //Constants
    private final String BACK_ICON = "/img/backIcon.png";
    private final String SOUND_ICON = "/img/soundIcon.png";
    private final String SETTINGS_ICON = "/img/Settings-icon.png";
    private final String COPYRIGHT_LABEL = "Developed by Royal Flush";
    private final double WIDTH  = 1080;
    private final double HEIGHT = 720;
    private final double COPYRIGHT_PANEL_SIZE = 60;
    private final int ICON_SIZE = 64;

    //DataVariables
    private int winPercentage;
    private int losePercentage;
    private int sixToSixData;
    private int eightToEightData;
    private int tenToTenData;
    private int noOfStars;
    private double gameStatus;

    //Labels
    private Label copyRightLabel;
    private Pane copyRightPanel;
    private Label version;

    //Buttons
    private Button backButton;
    private Button[] pagePassButtons;
    private Button soundButton;
    private Button settingsButton;

    //Images
    private Image backImage;
    private Image[] pagePassImages;


    public DashboardPane(DashboardData data) {
        super();
        winPercentage = data.getWinPercentage();
        losePercentage = data.getLosePercentage();
        sixToSixData = data.getSixToSixData();
        eightToEightData = data.getEightToEightData();
        tenToTenData = data.getTenToTenData();
        noOfStars = data.getNoOfStars();
        gameStatus = data.getGameStatus();
        initialize();
    }

    public void initialize(){

        //Creating middle panel
        this.setMinHeight(HEIGHT - COPYRIGHT_PANEL_SIZE);
        this.setMinWidth(WIDTH);

        //Creating copyright panel
        copyRightPanel = new Pane();

        //Copyright label
        copyRightLabel = new Label(COPYRIGHT_LABEL);

        //Version label
        version = new Label("Version b0.1");

        //Back button
        backImage = new Image(getClass().getResourceAsStream(BACK_ICON));
        backButton = new Button();
        backButton.setGraphic(new ImageView(backImage));
        backButton.setStyle("-fx-background-color: transparent");
        backButton.setMinSize(ICON_SIZE, ICON_SIZE);
        backButton.setLayoutX(25);
        backButton.setLayoutY(35);


        ImageView soundImage = new ImageView(new Image(SOUND_ICON));
        soundImage.setFitWidth(ICON_SIZE);
        soundImage.setFitHeight(ICON_SIZE);
        soundButton = new Button();
        soundButton.setStyle("-fx-background-color: transparent");
        soundButton.setMinSize(ICON_SIZE,ICON_SIZE);
        soundButton.setLayoutX(WIDTH - 100);
        soundButton.setLayoutY(25);

        soundButton.setGraphic(soundImage);

        settingsButton = new Button();
        settingsButton.setStyle("-fx-background-color: transparent");
        settingsButton.setMinSize(ICON_SIZE, ICON_SIZE);
        settingsButton.setLayoutX(WIDTH - 200);
        settingsButton.setLayoutY(25);
        settingsButton.setGraphic(new ImageView(SETTINGS_ICON));

        Label dashboard = new Label("");
        ImageView labelImage = new ImageView("/img/dashboard_label.png");
        labelImage.setFitWidth(400);
        labelImage.setFitHeight(60);

        dashboard.setGraphic(labelImage);
        dashboard.setStyle("-fx-font-weight: bold;");
        dashboard.setMaxSize(450,75);
        dashboard.setFont(new Font("Verdana", 45));
        dashboard.setLayoutX(325);
        dashboard.setLayoutY(50);

/*
        //https://docs.oracle.com/javafx/2/charts/pie-chart.htm#BABFJECC
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Won", winPercentage),
                        new PieChart.Data("Lost", losePercentage));
        final PieChart chart = new PieChart(pieChartData);
        //chart.setTitle("Win/Lose");
        chart.setLegendVisible(false);
        chart.setLayoutX(50);
        chart.setLayoutY(350);
        chart.setMaxSize(320,320);

        final Label caption = new Label("");
        caption.setStyle("-fx-font-weight: bold;");
        caption.setStyle("-fx-font: 16 verdana;");

        for (final PieChart.Data data : chart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
                    e -> {
                        caption.setTranslateX(e.getSceneX());
                        caption.setTranslateY(e.getSceneY());
                        caption.setText(String.valueOf(data.getPieValue()) + "%");
                    });
        }

        final String sixToSix = "6X6";
        final String eightToEight = "8X8";
        final String tenToTen = "10X10";
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> barChart = new BarChart<String,Number>(xAxis,yAxis);
        XYChart.Series series1 = new XYChart.Series();
        series1.getData().add(new XYChart.Data(sixToSix, sixToSixData));
        XYChart.Series series2 = new XYChart.Series();
        series2.getData().add(new XYChart.Data(eightToEight, eightToEightData));
        XYChart.Series series3 = new XYChart.Series();
        series3.getData().add(new XYChart.Data(tenToTen, tenToTenData));
        barChart.getData().addAll(series1, series2, series3);
        barChart.setBarGap(-15);
        for (Node n : barChart.lookupAll(".default-color0.chart-bar")) {
            n.setStyle("-fx-bar-fill: #0a3957;");
        }
        for (Node n : barChart.lookupAll(".default-color1.chart-bar")) {
            n.setStyle("-fx-bar-fill: #65092b;");
        }
        for (Node n : barChart.lookupAll(".default-color2.chart-bar")) {
            n.setStyle("-fx-bar-fill: #366655;");
        }

        for (Node n : barChart.lookupAll(".chart-plot-background")) {
            n.setStyle("-fx-background-color: transparent");
        }
        for (Node n : barChart.lookupAll(".chart-vertical-grid-lines")) {
            //n.setStyle("-fx-stroke: #ffffff");
            n.setStyle("-fx-background-color: transparent");
        }
        for (Node n : barChart.lookupAll(".chart-horizontal-grid-lines")) {
            //n.setStyle("-fx-stroke: #ffffff");
            n.setStyle("-fx-background-color: transparent");
        }

        barChart.setLayoutX(75);
        barChart.setLayoutY(115);
        barChart.setMaxSize(250,250);
        barChart.setLegendVisible(false);
        barChart.setStyle("-fx-background-color: transparent");

        ProgressIndicator gameStatusIndicator = new ProgressIndicator();
        gameStatusIndicator.setProgress(gameStatus);
        gameStatusIndicator.setMinSize(200,200);
        gameStatusIndicator.setStyle("-fx-progress-color: rgba(255,51,56,0.81);");
        gameStatusIndicator.setLayoutX(750);
        gameStatusIndicator.setLayoutY(130);

        StackPane starsCollectedStack = new StackPane();
        ImageView starsCollected = new ImageView("./img/star-icon.png");
        starsCollected.setFitHeight(200);
        starsCollected.setFitWidth(200);

        Label stars = new Label(String.valueOf(noOfStars) + "\nStars Collected");
        stars.setStyle("-fx-font-weight: bold; -fx-text-alignment: center; -fx-text-fill: #1b0611;");
        stars.setFont(new Font("Verdana", 13));

        starsCollectedStack.getChildren().addAll(starsCollected, stars);

        starsCollectedStack.setLayoutX(750);
        starsCollectedStack.setLayoutY(400);


        Pane skinsPane = new Pane();
        pagePassButtons = new Button[2];
        pagePassImages = new Image[2];
        pagePassImages[1] = new Image(getClass().getResourceAsStream("/img/next.png"), 30, 48, true, false);
        pagePassImages[0] = new Image(getClass().getResourceAsStream("/img/prev.png"), 30, 48, true, false);
        for ( int i = 0; i < 2; i++){
            pagePassButtons[i] = new Button();
            pagePassButtons[i].setMaxSize(16, 48);
            pagePassButtons[i].setGraphic(new ImageView(pagePassImages[i]));
            pagePassButtons[i].setStyle("-fx-background-color: transparent");
        }
        pagePassButtons[0].setLayoutX(-10);
        pagePassButtons[0].setLayoutY(75);
        pagePassButtons[1].setLayoutX(125);
        pagePassButtons[1].setLayoutY(75);

        //skinsPane.setStyle("-fx-border-color: black");
        ImageView skin1 = new ImageView("/img/5-1.png");
        skin1.setFitWidth(100);
        skin1.setFitHeight(175);
        skin1.setLayoutX(28);
        skinsPane.setLayoutX(450);
        skinsPane.setLayoutY(425);
        skinsPane.getChildren().addAll(pagePassButtons[0], skin1, pagePassButtons[1]);
        Line line1 = new Line(450, 375, 325, 425);

        ImageView profile = new ImageView("./img/userInfoIcon.png");
        profile.setFitWidth(300);
        profile.setFitHeight(200);
        profile.setLayoutX(375);
        profile.setLayoutY(175);

        Line lineToBarChart = new Line(435, 240, 325, 200);
        lineToBarChart.setStroke(Color.BLACK);
        lineToBarChart.setStrokeWidth(2);
        lineToBarChart.setStrokeLineCap(StrokeLineCap.BUTT);
        lineToBarChart.getStrokeDashArray().addAll(15d, 5d, 15d, 15d, 20d);

        Line lineToPieOne = new Line(450, 340, 325, 440);
        lineToPieOne.setStroke(Color.BLACK);
        lineToPieOne.setStrokeWidth(2);
        lineToPieOne.setStrokeLineCap(StrokeLineCap.BUTT);
        lineToPieOne.getStrokeDashArray().addAll(15d, 5d, 15d, 15d, 20d);

        Line lineToSkin = new Line(530, 370, 530, 425);
        lineToSkin.setStroke(Color.BLACK);
        lineToSkin.setStrokeWidth(2);
        lineToSkin.setStrokeLineCap(StrokeLineCap.BUTT);
        lineToSkin.getStrokeDashArray().addAll(15d, 5d, 15d, 15d, 20d);

        Line lineToStars = new Line(612, 310, 800, 425);
        lineToStars.setStroke(Color.BLACK);
        lineToStars.setStrokeWidth(2);
        lineToStars.setStrokeLineCap(StrokeLineCap.BUTT);
        lineToStars.getStrokeDashArray().addAll(15d, 5d, 15d, 15d, 20d);

        Line lineToPieTwo = new Line(612, 240, 760, 190);
        lineToPieTwo.setStroke(Color.BLACK);
        lineToPieTwo.setStrokeWidth(2);
        lineToPieTwo.setStrokeLineCap(StrokeLineCap.BUTT);
        lineToPieTwo.getStrokeDashArray().addAll(15d, 5d, 15d, 15d, 20d);

        this.getChildren().addAll(dashboard,backButton, chart, caption, barChart, gameStatusIndicator,
                starsCollectedStack, skinsPane, profile, lineToBarChart, lineToPieOne, lineToSkin, lineToStars, lineToPieTwo, settingsButton, soundButton );

        //Adding labels to panel
        copyRightPanel.getChildren().add(copyRightLabel);
        copyRightPanel.getChildren().add(version);

        //Default theme
        setCurrentColor(null);
    }

    public void setCurrentColor(String colorCSS){

        if (colorCSS == null){
            this.setStyle("-fx-background-color: #81aae6;");
        }else{
            this.setStyle(colorCSS);
        }
    }

    public void addHandler( GameManager.ButtonListener e) {
        backButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e);

        GameManager.ButtonListener settings = e.clone();
        settings.setIndex(4);
        settingsButton.addEventHandler(MouseEvent.MOUSE_CLICKED, settings);
    }
}
