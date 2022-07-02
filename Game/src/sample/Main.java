
package sample;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    public int rand;
    public Label randResult;

    public int cerPos[][] = new int[10][10];
    public int specialTile[][] = new int[6][3];

    public static final int Tile_Size = 80;
    public static final int width = 10;
    public static final int height = 10;

    public Circle player1;
    public Circle player2;
    public Circle player3;
    public Circle player4;


    public int playerPos1 = 1;
    public int playerPos2 = 1;
    public int playerPos3 = 1;
    public int playerPos4 = 1;

    public boolean player1turn = true;
    public boolean player2turn = true;
    public boolean player3turn = true;
    public boolean player4turn = true;

    public static int player_1_XPos = 40;
    public static int player_1_YPos = 760;

    public static int player_2_XPos = 40;
    public static int player_2_YPos = 760;

    public static int player_3_XPos = 40;
    public static int player_3_YPos = 760;

    public static int player_4_XPos = 40;
    public static int player_4_YPos = 760;

    public boolean gameStart = false;
    public Button gameButton;
    public Button button;
    public Button button2;
    public Button button3;
    public Button button4;

    public int posCir1 = 1;
    public int posCir2 = 1;
    public int posCir3 = 1;
    public int posCir4 = 1;

    private Group tileGroup = new Group();

    private Parent createContent() {
        StackPane root = new StackPane();
        root.setPrefSize(width * Tile_Size, (height * Tile_Size) + 80);
        root.getChildren().addAll(tileGroup);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                TilePane tile = new TilePane(Tile_Size, Tile_Size);
                tile.setTranslateX(j * Tile_Size);
                tile.setTranslateY(i * Tile_Size);

                tileGroup.getChildren().add(tile);
            }
        }
        player1 = new Circle(40);
        player1.setId("Player1");
        //player1.getStyleClass().add("style.css");
        player1.setTranslateX(player_1_XPos);
        player1.setTranslateY(player_1_YPos);

        player2 = new Circle(40);
        player2.setId("Player2");
        //player2.getStyleClass().add("style.css");
        player2.setTranslateX(player_2_XPos);
        player2.setTranslateY(player_2_YPos);

        player3 = new Circle(40);
        player3.setId("Player3");
        //player1.getStyleClass().add("style.css");
        player3.setTranslateX(player_3_XPos);
        player3.setTranslateY(player_3_YPos);

        player4 = new Circle(40);
        player4.setId("Player4");
        //player2.getStyleClass().add("style.css");
        player4.setTranslateX(player_4_XPos);
        player4.setTranslateY(player_4_YPos);

        button = new Button("Player1");
        button.setTranslateX(0);
        button.setTranslateY(825);
        button.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                if (gameStart) {
                    if (player1turn) {
                        DiceRoller();
                        randResult.setText(String.valueOf(rand));
                        movePlayer1();
                        movePlayer(player_1_XPos, player_1_YPos, player1);
                        player1turn = false;
                        player2turn = true;
                    }
                }
            }
        });

        button2 = new Button("Player2");
        button2.setTranslateX(0);
        button2.setTranslateY(850);
        button2.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                if (gameStart) {
                    if (player2turn) {

                    }
                }
            }
        });
        button3 = new Button("Player3");
        button3.setTranslateX(0);
        button3.setTranslateY(875);
        button3.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                if (gameStart) {
                    if (player3turn) {

                    }
                }
            }
        });

        button4 = new Button("Player4");
        button4.setTranslateX(0);
        button4.setTranslateY(900);
        button4.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                if (gameStart) {
                    if (player4turn) {

                    }
                }
            }
        });

        gameButton = new Button("Start Game");
        gameButton.setTranslateX(380);
        gameButton.setTranslateY(820);
        gameButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                gameButton.setText("Game Started");
                gameStart = true;
                player_1_XPos = 40;
                player_1_YPos = 760;

                player_2_XPos = 40;
                player_2_YPos = 760;

                player_3_XPos = 40;
                player_3_YPos = 760;

                player_4_XPos = 40;
                player_4_YPos = 760;

                player1.setTranslateX(player_1_XPos);
                player1.setTranslateY(player_1_YPos);
                player2.setTranslateX(player_2_XPos);
                player2.setTranslateY(player_2_YPos);
                player3.setTranslateX(player_3_XPos);
                player3.setTranslateY(player_3_YPos);
                player4.setTranslateX(player_4_XPos);
                player4.setTranslateY(player_4_YPos);
            }
        });

        randResult = new Label("0");
        randResult.setTranslateX(300);
        randResult.setTranslateY(820);

        tileGroup.getChildren().addAll(player1, button, button2, button3, button4, gameButton, randResult);
        return root;
    }

    private void DiceRoller() {
        rand = (int) (Math.random() * 6 + 1);
    }

    private void movePlayer1() {
        for (int i = 0; i < rand; i++) {
            if (posCir1 % 2 == 1) {
                player_1_XPos += 80;
            }
        }
        if (posCir1 % 2 == 0) {
            player_1_XPos -= 80;
        }
        if (player_1_XPos > 760) {
            player_1_YPos -= 80;
            player_1_XPos -= 80;
            posCir1++;
        }
        if (player_1_XPos < 40) {
            player_1_YPos -= 80;
            player_1_XPos += 80;
            posCir1++;
        }

        if (player_1_XPos < 40 || player_1_YPos < 40) {
            player_1_XPos = 40;
            player_1_YPos = 40;
            randResult.setText("Player 1 Score: ");
        }
    }

    private void movePlayer(int x, int y, Circle b) {
        TranslateTransition animate = new TranslateTransition(Duration.millis(1000), b);
        animate.setToX(x);
        animate.setToY(y);

        animate.setAutoReverse(false);
        animate.play();
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createContent());
        primaryStage.setTitle("Asteroid Seeker");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
