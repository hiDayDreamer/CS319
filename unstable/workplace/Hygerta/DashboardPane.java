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

public class DashboardPane extends Pane {
    //Constants
    private final String BACK_ICON = "./img/backIcon.png";
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

        Label dashboard = new Label("Dashboard");
        dashboard.setStyle("-fx-font-weight: bold;");
        dashboard.setMaxSize(450,75);
        dashboard.setFont(new Font("Verdana", 45));
        dashboard.setLayoutX(400);
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
        chart.setLayoutY(325);
        chart.setMaxSize(350,350);

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
            n.setStyle("-fx-bar-fill: blue;");
        }
        for (Node n : barChart.lookupAll(".default-color1.chart-bar")) {
            n.setStyle("-fx-bar-fill: #ff1f79;");
        }
        for (Node n : barChart.lookupAll(".default-color2.chart-bar")) {
            n.setStyle("-fx-bar-fill: #88ffd4;");
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
        /*
        barChart.setLayoutX(50);
        barChart.setLayoutY(115);
        barChart.setMaxSize(250,250);
        barChart.setLegendVisible(false);
        barChart.setStyle("-fx-background-color: transparent");

        ProgressIndicator gameStatusIndicator = new ProgressIndicator();
        gameStatusIndicator.setProgress(gameStatus);
        gameStatusIndicator.setMinSize(200,200);
        gameStatusIndicator.setStyle("-fx-progress-color: #ff2c47;");
        gameStatusIndicator.setLayoutX(725);
        gameStatusIndicator.setLayoutY(85);

        StackPane starsCollectedStack = new StackPane();
        ImageView starsCollected = new ImageView("./img/star-icon.png");
        starsCollected.setFitHeight(200);
        starsCollected.setFitWidth(200);

        Label stars = new Label(String.valueOf(noOfStars) + "\nStars Collected");
        stars.setStyle("-fx-font-weight: bold; -fx-text-alignment: center; -fx-text-fill: #1b0611;");
        stars.setFont(new Font("Verdana", 13));

        starsCollectedStack.getChildren().addAll(starsCollected, stars);

        starsCollectedStack.setLayoutX(750);
        starsCollectedStack.setLayoutY(350);


        Pane skinsPane = new Pane();
        pagePassButtons = new Button[2];
        pagePassImages = new Image[2];
        pagePassImages[1] = new Image(getClass().getResourceAsStream("./img/play-symbol.png"), 16, 48, false, false);
        pagePassImages[0] = new Image(getClass().getResourceAsStream("./img/left-angle-bracket.png"), 16, 48, false, false);
        for ( int i = 0; i < 2; i++){
            pagePassButtons[i] = new Button();
            pagePassButtons[i].setMaxSize(16, 48);
            pagePassButtons[i].setGraphic(new ImageView(pagePassImages[i]));
            //backButton[i].setStyle("-fx-background-color: transparent");
        }
        pagePassButtons[0].setLayoutX(0);
        pagePassButtons[0].setLayoutY(75);
        pagePassButtons[1].setLayoutX(123);
        pagePassButtons[1].setLayoutY(75);

        skinsPane.setStyle("-fx-border-color: black");
        ImageView skin1 = new ImageView("./img/5-1.png");
        skin1.setFitWidth(100);
        skin1.setFitHeight(175);
        skin1.setLayoutX(28);
        skinsPane.setLayoutX(515);
        skinsPane.setLayoutY(425);
        skinsPane.getChildren().addAll(pagePassButtons[0], skin1, pagePassButtons[1]);

        ImageView profile = new ImageView("./img/userInfoIcon.png");
        profile.setFitWidth(300);
        profile.setFitHeight(200);
        profile.setLayoutX(360);
        profile.setLayoutY(175);
        Line line = new Line(450, 375, 325, 425);*/

        this.getChildren().addAll(dashboard);//,backButton, chart, caption, barChart);//, gameStatusIndicator, starsCollectedStack, skinsPane, profile, line);

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
    }
}
