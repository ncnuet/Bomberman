package uet.oop.bomberman;



import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application ;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.util.Duration;


public class TheTimer  {
    final  Integer startTime=280 ;
    Integer seconds=startTime;
    Label label ;

    public void start(Stage stage,Scene scene) throws Exception{
        Group root = new Group();
        label = new Label();
        label.setTextFill(Color.BLACK);
        label.setFont(Font.font(20));
        HBox layout= new HBox(5);
        layout.getChildren().add(label);
        root.getChildren().add(layout);
        doTime();
        stage.show();
    }

    public void doTime(){
        Timeline time = new Timeline();
        time.setCycleCount(Timeline.INDEFINITE);
        if(time==null){
            time.stop();
        }


        KeyFrame frame=new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                label.setText("Countdown:"+ seconds.toString());
                seconds--;
                if(seconds<=0){
                    time.stop();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Count down reset to 0");
                    alert.show();
                }
            }
        });
        time.getKeyFrames().add(frame);
        time.playFromStart();
    }
}

