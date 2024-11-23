import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.sound.sampled.*;

public class Jeu {

    private static Clip clip;

    public Jeu(int taille) {
        // Création de la fenêtre
        JFrame fenetre = new JFrame("DorfJavatik");
        fenetre.setSize(1200, 750);
        fenetre.setLocation(100, 100);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setMinimumSize(new Dimension(1100,600));

        // Disposition GridLayout pour la fenêtre
        GridLayout g = new GridLayout(1,2);
        fenetre.setLayout(g);

        // Création des différents panneaux de la fenêtre
        JPanel infos = new JPanel();
        GridLayout gp = new GridLayout(2,1);
        infos.setLayout(gp);
        FlowLayout f = new FlowLayout(FlowLayout.CENTER);
        JPanel panneau2 = new JPanel();
        panneau2.setLayout(f);
        
        Proposition prop = new Proposition();
        panneau2.add(prop);

        // Initialisation du score
        JLabel txtscore = new JLabel("Score : " + prop.getScore());
        txtscore.setHorizontalAlignment(JLabel.CENTER);
        prop.setScoreLabel(txtscore);

        // Initialisation du plateau dans la fenêtre
        JPanel panneau = new JPanel();
        panneau.setLayout(f);
        panneau.setPreferredSize(new Dimension(500, 500));
        Plateau plateau = new Plateau(taille,prop);
        panneau.add(plateau);

        infos.setBackground(Color.LIGHT_GRAY);
        plateau.setBackground(Color.WHITE);

        // Ajout des différents éléments dans la fenêtre
        infos.add(txtscore);
        infos.add(panneau2);
        fenetre.add(infos);
        fenetre.add(panneau);

        fenetre.setVisible(true);

        // Musique de fond
        try {
            File file = new File("res/music.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            clip.setLoopPoints(249761, 2997073);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            System.out.println("Erreur lors de la lecture de la musique.");
        }
    }

    public static void stopMusic() {
        clip.stop();
    }
}