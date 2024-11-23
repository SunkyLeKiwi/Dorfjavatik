import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Tuile extends JComponent {
    private Color[] cotes = new Color[4];
    // Color[0] = Couleur au Nord
    // Color[1] = Couleur à l'Est
    // Color[2] = Couleur au Sud
    // Color[3] = Couleur à l'Ouest

    public static Color rouge = new Color(176, 0, 0);   // Villages
    public static Color bleu = new Color(0, 176, 176);  // Rivières et lacs
    public static Color jaune = new Color(176, 176, 0); // Champs
    public static Color vert = new Color(0, 176, 0);    // Forêts
    public static Color gris = new Color(44, 44, 44);   // Montagnes


    public Tuile() {
        Color[] tabC = {rouge, bleu, jaune, vert, gris};
        Random random = new Random();

        // Assignation de quatre couleurs choisies aléatoirement
        for (int i = 0; i < 4; i++) {
            this.cotes[i] = tabC[random.nextInt(5)];
        }
    }

    public Color getColor(int dir) {
        return this.cotes[dir];
    }
    
    // Renvoie un boolean en fonction de la correspondance entre une tuile et une couleur
    public boolean correspond(Color c, int dir) {
        if (this.cotes[dir] == c) {
            return true;
        }
        return false;
    }

    @Override
    protected void paintComponent(Graphics pinceau) {
        super.paintComponent(pinceau);
        Dimension currentSize = this.getSize();
        int l = currentSize.width;
        int h = currentSize.height;

        // Dessiner le triangle pour chaque côté
        int[][] xPoints = {
            {0,         l / 2,      l},  // Nord
            {l / 2,     l,          l},  // Est
            {0,         l / 2,      l},  // Sud
            {l / 2,     0,          0}   // Ouest
        };

        int[][] yPoints = {
            {0,         h / 2,      0},  // Nord
            {h / 2,     0,          h},  // Est
            {h,         h / 2,      h},  // Sud
            {h / 2,     0,          h}   // Ouest
        };

        // Dessiner chaque côté avec la couleur appropriée
        for (int i = 0; i < 4; i++) {
            pinceau.setColor(cotes[i]);
            pinceau.fillPolygon(xPoints[i], yPoints[i], 3);
        }
        this.repaint();
    }
}