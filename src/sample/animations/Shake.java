package sample.animations;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

/**
 * @author Vitalii
 * Animacja SHAKE
 */
public class Shake {
    private TranslateTransition tt;

    /**
     * Przenieś pole, które musisz potrząsnąć
     * @param node pole
     */
    public Shake(Node node) {
        tt = new TranslateTransition(Duration.millis(70), node);
        tt.setFromX(0f);
        tt.setByX(10f);
        tt.setCycleCount(3);
        tt.setAutoReverse(true);
    }
    /**
     * Wezwanie
     */
    public void playAnim() {
        tt.playFromStart();
    }
}
