import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Case extends JPanel {

    private Tuile t;

    // i et j permettent d'identifier les cases
    private int i;
    private int j;

    public Case(int val1, int val2) {
        super();
        i = val1;
        j = val2;
    }

    public int geti() {
        return this.i;
    }

    public int getj() {
        return this.j;
    }

    // Ajout d'une tuile dans la case
    public void setTuile(Tuile ti){
        this.t = ti;
    }

    // Récupération de la tuile contenue dans la case
    public Tuile getTuile() {
        return this.t;
    }

    @Override
    public void paintComponent(Graphics g) {
        Dimension currentSize = this.getSize();
        g.drawRect(0, 0, currentSize.width, currentSize.height);

        if (t != null) {
            t.setSize(currentSize); // Vérifier que la tuile a la bonne taille
            t.paintComponent(g); // Appel de la méthode paintComponent de la tuile
        }
    }
}