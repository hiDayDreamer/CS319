import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.geometry.Pos;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

class LevelsPane extends GridPane{
   //properties
   private final String BACK_ICON = "/img/backIcon.png";
   private final String SOUND_ICON = "/img/soundIcon.png";
   private final String SETTINGS_ICON = "/img/Settings-icon.png";
   private final String LOCkED_IMAGE = "/img/lockNew.png";
   private final double WIDTH  = 1080;
   private final double HEIGHT = 720;
   private final int ICON_SIZE = 64;
   private final double COPYRIGHT_PANEL_SIZE = HEIGHT / 15;
   private final double BUTTON_SIZE = (WIDTH - 2 * ICON_SIZE - HEIGHT)/8;

   //Labels
   private Pane copyRightPanel;
   private Label title;
   private String dimension;
   private Label starNo;
   private Button[] buttons;
   private int[] starArr;
   private Button soundButton;
   private Button settingsButton;
   //Buttons
   private Button backButton;
   private ImageView backImage;
   private boolean[] openMapArray;

   //constructors
   public LevelsPane(String dimension, int[] starArr,boolean[] mapArray){
      super();
      this.dimension = dimension;
      buttons = new Button[15];
      this.starArr = starArr;
      this.openMapArray = mapArray;
      initialize();
   }

   public void setOpenMaps(boolean[] mapArray){
      this.openMapArray = mapArray;
   }
   //methods
   public void initialize(){
      setCurrentColor(null);
      //set dimensions
      this.setMinHeight(HEIGHT - COPYRIGHT_PANEL_SIZE-12);
      this.setMinWidth(WIDTH);

      //Creating copyright panel
      copyRightPanel = new Pane();

      //backButton
      backImage = new ImageView(BACK_ICON);
      backImage.setFitWidth(ICON_SIZE);
      backImage.setFitHeight(ICON_SIZE);
      backButton = new Button();
      backButton.setGraphic(backImage);
      backButton.setStyle("-fx-background-color: transparent");
      backButton.setMinSize(ICON_SIZE, ICON_SIZE);
      backButton.setLayoutX(25);
      backButton.setLayoutY(25);

      ImageView soundImage = new ImageView(new Image(SOUND_ICON));
      soundImage.setFitWidth(ICON_SIZE);
      soundImage.setFitHeight(ICON_SIZE);
      soundButton = new Button();
      soundButton.setStyle("-fx-background-color: transparent");
      soundButton.setMinSize(ICON_SIZE,ICON_SIZE);
      soundButton.setLayoutX(WIDTH - 100);
      soundButton.setLayoutY(25);

      soundButton.setGraphic(soundImage);

      ImageView settingsImage = new ImageView(new Image(SETTINGS_ICON));
      settingsImage.setFitWidth(ICON_SIZE);
      settingsImage.setFitHeight(ICON_SIZE);
      settingsButton = new Button();
      settingsButton.setStyle("-fx-background-color: transparent");
      settingsButton.setMinSize(ICON_SIZE, ICON_SIZE);
      settingsButton.setLayoutX(WIDTH - 200);
      settingsButton.setLayoutY(25);
      settingsButton.setGraphic(settingsImage);
      // create the label
      title = new Label("");
      title.setStyle("-fx-font-weight: bold;");
      if ( dimension.equals("6X6")){
         ImageView labelImage = new ImageView("/img/6x6.png");
         labelImage.setFitWidth(400);
         labelImage.setFitHeight(60);
         title.setGraphic(labelImage);
      }else if (dimension.equals("6X6")){
         ImageView labelImage = new ImageView("/img/8x8.png");
         labelImage.setFitWidth(400);
         labelImage.setFitHeight(60);
         title.setGraphic(labelImage);
      }else{
         ImageView labelImage = new ImageView("/img/10x10.png");
         labelImage.setFitWidth(400);
         labelImage.setFitHeight(60);
         title.setGraphic(labelImage);
      }
      title.setFont(new Font("Verdana", ICON_SIZE * 6/5));
      title.setMinWidth( WIDTH);
      title.setAlignment(Pos.TOP_CENTER);
      title.setLayoutY(35);
      Pane top = new Pane();
      top.getChildren().addAll(title, backButton, soundButton, settingsButton);
      this.addRow(0, top);

      // create the stars pane
      HBox starBox = new HBox();
      ImageView bottomStar = new ImageView(new Image("/img/fullStar.png"));
      bottomStar.setFitWidth(HEIGHT / 5 / 3);
      bottomStar.setFitHeight(HEIGHT / 5 / 3);
      starNo = new Label("45");
      starNo.setFont(new Font("Verdana", HEIGHT / 5 / 5));
      starBox.getChildren().addAll(bottomStar, starNo);//, backButton);
      starBox.setAlignment(Pos.CENTER);

      // create the level boxes
      //double boxSize = HEIGHT / 5;
      int iterationIndex = 0;
      VBox middle = new VBox( HEIGHT / 5 / 6);
      int levelNumber = 1;
      int levelIndex = 0;
      String location = "/img/level.png";

      for ( int k = 0; k < 3; k++ ) {
         // One of the rows of the buttons
         HBox box = new HBox(BUTTON_SIZE);
         for ( int i = 0; i < 5; i++) {
            // the pane to put the view in
            Pane a = new Pane();
            // The view for each level's button
            VBox level = new VBox();
            if (openMapArray[levelIndex]){
               location = "/img/level.png";
            } else {
               location = LOCkED_IMAGE;
            }
            levelIndex++;
            Image buttonImage = new Image(getClass().getResourceAsStream(location),HEIGHT / 5,HEIGHT/5,false,false);
            // The image for the buttons
            ImageView s = new ImageView(buttonImage);
            s.setFitWidth(HEIGHT/5);
            s.setFitHeight(HEIGHT/5);
            level.setMinWidth(HEIGHT/5);
            level.setMinHeight(HEIGHT/5);
            a.getChildren().add(s);
            // create the number in the box
            Label number = new Label( levelNumber + "");
            number.setStyle("-fx-font-weight: bold;");
            number.setFont(new Font("Verdana", HEIGHT / 5 * 3 / 5));
            level.getChildren().addAll(number);

            // create the stars in the boxes
            HBox stars = new HBox( ( HEIGHT / 5 - 3 * HEIGHT / 5 / 4 ) / 4);
            int temp = starArr[i+k];
            for(int j = 0; j < temp; j++) {
               ImageView star = new ImageView(new Image("/img/fullStar.png"));
               star.setFitHeight(HEIGHT / 5 / 4);
               star.setFitWidth(HEIGHT / 5 / 4);
               stars.getChildren().add(star);
            }
            stars.setAlignment(Pos.CENTER);
            level.getChildren().add(stars);

            //align the elements inside each of the small boxes
            level.setAlignment(Pos.CENTER);
            Button button = new Button();
            //button.setGraphic(level);
            button.setOnMouseEntered(new EventHandler<MouseEvent>(){
                  public void handle(MouseEvent e){
                     //System.out.println("asdfads");
                     number.setFont(new Font("Verdana", HEIGHT / 5 * 3 / 5));
                     //button.setGraphic(new ImageView(new Image("fullStar.png")));
                  }
            });
            button.setOnMousePressed( new EventHandler<MouseEvent>() {
                  public void handle(MouseEvent e){
                     //System.out.println("asdfads");
                     number.setFont(new Font("Verdana", HEIGHT / 5 * 3 / 5));
                     //button.setGraphic(new ImageView(new Image("fullStar.png")));
               }
            });

            button.setMinSize(HEIGHT / 5, HEIGHT/ 5);
            button.setStyle("-fx-background-color: transparent");
            a.getChildren().add(level);
            button.setGraphic(a);
            buttons[levelNumber - 1] = button;
            box.getChildren().add(button);
            levelNumber++;
         }
         box.setAlignment(Pos.CENTER);
         middle.getChildren().add(box);
      };
      addAnimation(10);
      this.addRow(1, middle);
      this.addRow(2, starBox);
      this.addRow(3, copyRightPanel);
   }


   public void setCurrentColor(String colorCSS){

        if (colorCSS == null){
            this.setStyle("-fx-background-color: #81aae6;");
        }else{
            this.setStyle(colorCSS);
        }
    }

    public void setStars(int[] stars) {
        this.starArr = stars;
    }

    public void addHandler( GameManager.ButtonListener e) {
      e.setIndex(1);
      backButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e);
      for ( int i = 0; i < 15; i++) {
         GameManager.ButtonListener level = e.clone();
         level.setIndex(i + 11);
         buttons[i].addEventHandler(MouseEvent.MOUSE_CLICKED, level);

      }
       GameManager.ButtonListener settings = e.clone();
       settings.setIndex(4);
       settingsButton.addEventHandler(MouseEvent.MOUSE_CLICKED, settings);
   }
   public void addAnimation(int factor) {
      soundButton.setOnMouseEntered(new GameManager.Animation(soundButton, factor, true));
      soundButton.setOnMouseExited(new GameManager.Animation(soundButton, factor, false));
      settingsButton.setOnMouseEntered(new GameManager.Animation(settingsButton, factor, true));
      settingsButton.setOnMouseExited(new GameManager.Animation(settingsButton, factor, false));
      backButton.setOnMouseEntered(new GameManager.Animation(backButton, factor, true));
      backButton.setOnMouseExited(new GameManager.Animation(backButton, factor, false));
   }
   public boolean[] getOpenMapArray(int levelNo){
      if ( levelNo < openMapArray.length && !openMapArray[levelNo]){
         openMapArray[levelNo] = true;
      }/*
      for (int i = 0; i < openMapArray.length; i++){
         if(!openMapArray[i]){
            openMapArray[i] = true;
            return openMapArray;
         }
      }*/
      return openMapArray;
   }
}
