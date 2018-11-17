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
import javafx.geometry.Insets;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

class LevelsPane extends GridPane{
   //properties
   private final String BACK_ICON = "backIcon.png";
   private final String COPYRIGHT_LABEL = "Developed by Royal Flush";
   private final double WIDTH  = 1080;
   private final double HEIGHT = 720;
   private final int ICON_SIZE = 64;
   private final double COPYRIGHT_PANEL_SIZE = HEIGHT / 15;
   private final double BUTTON_SIZE = (WIDTH - 2 * ICON_SIZE - HEIGHT)/8;

   //Labels
   private Label copyRightLabel;
   private HBox starBox;
   private Pane copyRightPanel;
   private Label version;
   private Label title;
   private String dimension;
   private Label starNo;
   private Button[] buttons;

   //Buttons
   private Button backButton;
   private Image backImage;

   //constructors
   public LevelsPane(String dimension){
      super();
      this.dimension = dimension;
      buttons = new Button[15];
      initialize();
   }

   //methods
   public void initialize(){
      setCurrentColor(null);
      //set dimensions
      this.setMinHeight(HEIGHT - COPYRIGHT_PANEL_SIZE);
      this.setMinWidth(WIDTH);
      this.setMaxHeight(HEIGHT - COPYRIGHT_PANEL_SIZE);

      //Creating copyright panel
      copyRightPanel = new Pane();

      //Copyright label
      copyRightLabel = new Label(COPYRIGHT_LABEL);

      //Version label
      version = new Label("Version b0.1");

      //backButton
      backImage = new Image(getClass().getResourceAsStream(BACK_ICON));
      backButton = new Button("",new ImageView(backImage));
      //backButton.setGraphic();
      //backButton.setStyle("-fx-background-color: transparent");
      backButton.setMinSize(ICON_SIZE, ICON_SIZE);
      backButton.setLayoutX(25);
      backButton.setLayoutY(35);

      // create the label
      title = new Label(dimension);
      title.setStyle("-fx-font-weight: bold;");
      title.setFont(new Font("Times New Roman", ICON_SIZE * 3 / 2));
      title.setMinWidth( WIDTH);
      title.setAlignment(Pos.TOP_CENTER);
      Pane top = new Pane();
      top.getChildren().addAll(title, backButton);
      this.addRow(0, top);

      // create the stars pane
      HBox starBox = new HBox();
      ImageView bottomStar = new ImageView(new Image("emptyStar.png"));
      bottomStar.setFitWidth(HEIGHT / 5 / 3);
      bottomStar.setFitHeight(HEIGHT / 5 / 3);
      starNo = new Label("12355");
      starNo.setFont(new Font("Times New Roman", HEIGHT / 5 / 10));
      starBox.getChildren().addAll(bottomStar, starNo);//, backButton);
      starBox.setAlignment(Pos.CENTER);

      // create the level boxes
      //double boxSize = HEIGHT / 5;
      VBox middle = new VBox( HEIGHT / 5 / 6);
      int levelNumber = 1;
      for ( int k = 0; k < 3; k++ ) {
         HBox box = new HBox(BUTTON_SIZE);
         for ( int i = 0; i < 5; i++) {
            VBox level = new VBox();
            // create the number in the box
            Label number = new Label( levelNumber + "");
            number.setStyle("-fx-font-weight: bold;");
            number.setFont(new Font("Times New Roman", HEIGHT / 5 * 3 / 5));
            level.getChildren().addAll(number);

            // create the stars in the boxes
            HBox stars = new HBox( ( HEIGHT / 5 - 3 * HEIGHT / 5 / 4 ) / 3);
            for(int j = 0; j < 3; j++) {
               ImageView star = new ImageView(new Image("fullStar.png"));
               star.setFitHeight(HEIGHT / 5 / 4);
               star.setFitWidth(HEIGHT / 5 / 4);
               stars.getChildren().add(star);
            }
            level.getChildren().add(stars);

            //align the elements inside each of the small boxes
            level.setAlignment(Pos.CENTER);
            Button button = new Button();
            button.setGraphic(level);
            button.setOnMouseEntered(new EventHandler<MouseEvent>(){
                  public void handle(MouseEvent e){
                     //System.out.println("asdfads");
                     number.setFont(new Font("Times New Roman", HEIGHT / 5 * 3 / 5));
                     //button.setGraphic(new ImageView(new Image("fullStar.png")));
                  }
            });
            button.setOnMousePressed( new EventHandler<MouseEvent>() {
                  public void handle(MouseEvent e){
                     //System.out.println("asdfads");
                     number.setFont(new Font("Times New Roman", HEIGHT / 5 * 3 / 5));
                     //button.setGraphic(new ImageView(new Image("fullStar.png")));
               }
            });
            // button.setOnMouseExited(new EventHandler<MouseEvent>(){
            //       public void handle(MouseEvent e){
            //          System.out.println("asdfads");
            //          button.setGraphic(level);
            //       }
            // });
            button.setMinSize(HEIGHT / 5, HEIGHT/ 5);
            //button.setStyle("-fx-background-color: transparent");
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
            this.setStyle("-fx-background-color: lightblue;");
        }else{
            this.setStyle(colorCSS);
        }
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
