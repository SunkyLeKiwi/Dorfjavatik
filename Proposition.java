import javax.swing.*;
import java.awt.*;
import java.io.File;
import javax.sound.sampled.*;

public class Proposition extends JPanel {
    protected int score = 0;
    private int combo = 0;
    private Tuile tap;
    private JLabel scoreLabel;
    private Clip[] clips;
    private File[] audioFiles;

    public Proposition() {
        super();
        this.tap = new Tuile();
        this.setPreferredSize(new Dimension(150, 150));
        this.repaint();
        loadAudioClips();
    }

    // Chargement des sons
    private void loadAudioClips() {
        try {
            audioFiles = new File[4];
            audioFiles[0] = new File("res/1c.wav");
            audioFiles[1] = new File("res/2c.wav");
            audioFiles[2] = new File("res/3c.wav");
            audioFiles[3] = new File("res/4c.wav");
            clips = new Clip[4];
            for (int i = 0; i < 4; i++) {
                clips[i] = AudioSystem.getClip();
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFiles[i]);
                clips[i].open(audioStream);
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la lecture des fichiers audio : " + e.getMessage());
        }
    }

    public Tuile getTuile() {
        return this.tap; 
    }

    public void nouvelleTuile() {
        this.tap = new Tuile();
        this.repaint();
    }

    public int getScore() {
        return this.score;
    }

    public void addScore(Case[] voisins) {
        for (int i = 0; i < 4; i++) {
            if (voisins[i] != null && voisins[i].getTuile() != null) {
                if (i == 0 && tap.correspond(voisins[i].getTuile().getColor(2), 0)) {
                    this.score += 1;
                    this.combo += 1;
                } else if (i == 1 && tap.correspond(voisins[i].getTuile().getColor(3), 1)) {
                    this.score += 1;
                    this.combo += 1;
                } else if (i == 2 && tap.correspond(voisins[i].getTuile().getColor(0), 2)) {
                    this.score += 1;
                    this.combo += 1;
                } else if (i == 3 && tap.correspond(voisins[i].getTuile().getColor(1), 3)) {
                    this.score += 1;
                    this.combo += 1;
                }
            }
        }
        updateScoreLabel();
        playSound();
        combo = 0;
    }

    // Lecture des sons en fonction du combo
    private void playSound() {
        if (combo > 0 && combo <= 4) {
            try {
                Clip clip = clips[combo - 1];
                clip.stop();
                clip.setFramePosition(0); // Réinitialisation du clip pour que le son puisse être rejoué
                clip.start();
            } catch (Exception e) {
                System.out.println("Erreur lors de la lecture du son : " + e.getMessage());
            }
        }
    }

    public void setScoreLabel(JLabel scoreLabel) {
        this.scoreLabel = scoreLabel;
    }

    public void updateScoreLabel() {
        if (this.scoreLabel != null) {
            this.scoreLabel.setText("Score : " + getScore());
            this.scoreLabel.repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension currentSize = this.getSize();
        this.tap.setSize(currentSize);
        this.tap.paintComponent(g);
    }
}