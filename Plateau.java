import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Plateau extends JPanel implements MouseListener {
    private int taille;
    private Proposition p;
    private int compte = 0;
    private Case[][] tableau_valeurs;

    public Plateau(int size, Proposition pro) {
        super();

        // Définition du panneau + Ajout du listener
        this.taille = size;
        this.p = pro;
        this.setPreferredSize(new Dimension(500, 500));

        GridLayout gestionnaire = new GridLayout(this.taille, this.taille);
        this.setLayout(gestionnaire);

        tableau_valeurs = new Case[this.taille][this.taille];

        // Ajout des cases
        for(int i = 0; i < this.taille; i++) {
            for(int j = 0; j < this.taille; j++) {
                Case c = new Case(i, j);
                tableau_valeurs[i][j] = c;
                c.addMouseListener(this);
                this.add(c);
            }
        }
        this.repaint();
    }

    public void mouseClicked(MouseEvent e) {
        Case choix = (Case) e.getComponent();
        if (choix.getTuile() == null) {
            choix.setTuile(this.p.getTuile());
            this.repaint();

            // Récupération des tuiles autour de la case
            Case tNord = new Case(0,0);
            Case tEst = new Case(0,0);
            Case tSud = new Case(0,0);
            Case tOuest = new Case(0,0);

            if (choix.geti()-1 >= 0){
                tNord = this.tableau_valeurs[choix.geti()-1][choix.getj()];
            }
            if (choix.getj()+1 < this.taille){
                tEst = this.tableau_valeurs[choix.geti()][choix.getj()+1];
            }
            if (choix.geti()+1 < this.taille){
                tSud = this.tableau_valeurs[choix.geti()+1][choix.getj()];
            }
            if (choix.getj()-1 >= 0){
                tOuest = this.tableau_valeurs[choix.geti()][choix.getj()-1];
            }

            Case[] voisins = {tNord,tEst,tSud,tOuest};

            this.p.addScore(voisins);
            this.p.nouvelleTuile();

            // Si toute la grille est remplie
            this.compte++;
            if (this.compte == (this.taille * this.taille)) {
                new Resultat(this.p.getScore());
            }
        }
    }
       
    public void mouseEntered(MouseEvent e){};          
    public void mouseExited(MouseEvent e){};           
    public void mousePressed(MouseEvent e){};          
    public void mouseReleased(MouseEvent e){};         

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension currentSize = this.getSize();
        g.drawRect(0, 0, currentSize.width - 1, currentSize.height - 1);
    }
}