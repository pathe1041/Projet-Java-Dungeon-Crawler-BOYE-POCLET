import java.awt.Color;
import java.awt.Graphics;

public class BarreDeVie {
    private int vieMax;
    private int vieActuelle;
    private int x, y, largeur, hauteur;

    public BarreDeVie(int vieMax, int x, int y, int largeur, int hauteur) {
        this.vieMax = vieMax;
        this.vieActuelle = vieMax;
        this.x = x;
        this.y = y;
        this.largeur = largeur;
        this.hauteur = hauteur;
    }

    public void subirDegats(int degats) {
        vieActuelle -= degats;
        if (vieActuelle < 0) vieActuelle = 0;
    }

    public void soigner(int montant) {
        vieActuelle += montant;
        if (vieActuelle > vieMax) vieActuelle = vieMax;
    }
    public int getVieActuelle() {
        return vieActuelle;
    }


    public void dessiner(Graphics g) {
        double pourcentageVie = (double) vieActuelle / vieMax;


        g.setColor(Color.RED);
        g.fillRect(x, y, largeur, hauteur);


        g.setColor(Color.GREEN);
        g.fillRect(x, y, (int) (largeur * pourcentageVie), hauteur);


        g.setColor(Color.BLACK);
        g.drawRect(x, y, largeur, hauteur);
    }
}
