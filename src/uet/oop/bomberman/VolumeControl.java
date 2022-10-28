package uet.oop.bomberman;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Popup;
import javafx.stage.Stage;
import uet.oop.bomberman.sound.Sound;

import java.awt.*;

public class VolumeControl {
    private final Popup popup;
    private final Stage stage;

    public VolumeControl(Stage stage) {
        this.popup = new Popup();
        this.stage = stage;

        Label label = new Label("Volume: ");

        Slider slider = new Slider();
        slider.setMin(0);
        slider.setMax(100);
        slider.setValue(Sound.VOLUME);

        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(50);
        slider.setMinorTickCount(5);
        slider.setBlockIncrement(10);

        Button button = new Button("Apply");

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(label);
        borderPane.setCenter(slider);
        borderPane.setBottom(button);
        borderPane.setPadding(new Insets(20, 20, 20, 20));

        Rectangle rectangle = new Rectangle(0, 0, 180, 120);
        rectangle.setFill(Color.WHITE);

        popup.getContent().addAll(rectangle, borderPane);

        EventHandler<ActionEvent> eventOnApplyButtonClick =
                new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e) {
                        popup.hide();
                    }
                };

        // when button is pressed
        button.setOnAction(eventOnApplyButtonClick);
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                label.setText("Volume: " + String.format("%.0f", (double) newValue));
                Sound.VOLUME = (int) ((double) newValue);
                try {
                    Sound.bg_sound.setVolume(Sound.VOLUME);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void show() {
        popup.show(stage);
    }
}
