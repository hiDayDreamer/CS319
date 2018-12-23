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
   //Buttons
   private Button backButton;
   private Image backImage;

   //constructors
   public LevelsPane(String dimension, int[] starArr){
      super();
      this.dimension = dimension;
      buttons = new Button[15];
      this.starArr = starArr;
      initialize();
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
      backImage = new Image(getClass().getResourceAsStream(BACK_ICON));
      backButton = new Button("",new ImageView(backImage));
      //backButton.setGraphic();
      backButton.setStyle("-fx-background-color: transparent");
      backButton.setMinSize(ICON_SIZE, ICON_SIZE);
      backButton.setLayoutX(25);
      backButton.setLayoutY(25);

      // create the label
      title = new Label(dimension);
      title.setStyle("-fx-font-weight: bold;");
      title.setFont(new Font("Verdana", ICON_SIZE * 6/5));
      title.setMinWidth( WIDTH);
      title.setAlignment(Pos.TOP_CENTER);
      Pane top = new Pane();
      top.getChildren().addAll(title, backButton);
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
      VBox middle = new VBox( HEIGHT / 5 / 6);
      int levelNumber = 1;
      for ( int k = 0; k < 3; k++ ) {
         // One of the rows of the buttons
         HBox box = new HBox(BUTTON_SIZE);
         for ( int i = 0; i < 5; i++) {
            // the pane to put the view in
            Pane a = new Pane();
            // The view for each level's button
            VBox level = new VBox();
            Image buttonImage = new Image(getClass().getResourceAsStream("/img/level.png"),HEIGHT / 5,HEIGHT/5,false,false);
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
   }
}
