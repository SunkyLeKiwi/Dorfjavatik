import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.sound.sampled.*;

public class Resultat extends JFrame {
    private JLabel score;

    public Resultat(int score_t){
        this.setTitle("Fin de la partie !");
        this.setSize(400,100);
        this.setLocation(500,500);
        this.setMinimumSize(new Dimension(400, 100));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        if (score_t <= 1) score = new JLabel("Votre score final est de " + score_t + " point !");
        else score = new JLabel("Votre score final est de " + score_t + " points !");
        score.setHorizontalAlignment(JLabel.CENTER);

        this.add(score, BorderLayout.CENTER);
        this.setVisible(true);

        // Jingle de fin
        try {
            File file = new File("res/end.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            Jeu.stopMusic();
            clip.open(audioStream);
            clip.start();
        } catch (Exception e) {
            System.out.println("Erreur lors de la lecture de la musique.");
        }
    }
}
