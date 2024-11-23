import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Accueil extends JFrame implements ActionListener {
    private JComboBox<Integer> tailleComboBox;

    public Accueil() {
        this.setTitle("Accueil");
        this.setSize(500,100);
        this.setLocation(600,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(500, 100));

        this.setLayout(new BorderLayout());
        
        JLabel bienvenue = new JLabel("Bienvenue sur DorfJavatik, le nouveau LOL en 2D !");
        bienvenue.setHorizontalAlignment(JLabel.CENTER);
        this.add(bienvenue, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        Integer[] tailles = {3, 4, 5, 6, 7, 8, 9, 10};
        this.tailleComboBox = new JComboBox<>(tailles);
        centerPanel.add(tailleComboBox);
        
        centerPanel.add(tailleComboBox);

        JButton validerButton = new JButton("Valider");
        centerPanel.add(validerButton);
        validerButton.addActionListener(this);

        this.add(centerPanel, BorderLayout.CENTER);
        
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        // Récupérer la valeur entrée
        int t_select = (int) tailleComboBox.getSelectedItem();

        // Fermer la page
        this.dispose();
        
        new Jeu(t_select);
    }
}
