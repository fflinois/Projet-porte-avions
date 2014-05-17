package com.porte_avions.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.porte_avions.prog.Partie;

class SpecialPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int[][] jeu;
    Color blanc = Color.white;
    Color noir = new Color(150,120,120);
	private final HashMap<Integer, ImageIcon> images;

	SpecialPanel(final int[][] je, final HashMap<Integer, ImageIcon> im) {
		jeu = je;
		images = im;
		this.setForeground(Color.YELLOW);
    }

	@Override
	public void paintComponent(final Graphics g) {
	super.paintComponent(g);  
	for (int i=0; i<jeu.length; i++){
			g.drawString("" + i, i * 48 + 44, 15);
	    g.drawString(""+i,i*48+48,jeu[0].length*48+40);
	    for (int j=0; j<jeu[0].length; j++){
		g.drawString(""+j,7,j*48+48);
		g.drawString(""+j,jeu.length*48+30,j*48+48);
		images.get(jeu[i][j]).paintIcon(this,g,i*48+24,j*48+24);
	    }
	}
    }
}


public class IGPA extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int[][] jeu;
	private final HashMap<Integer, ImageIcon> images;
	private final Partie partie;
    private SpecialPanel jpane;

	public IGPA(final int x, final int y, final Partie partie) {
	jeu = new int[x][y];
	images = new  HashMap<Integer,ImageIcon>();
		this.partie = partie;
	}

	public void creerFenetre() {
		if (!this.isVisible()) {
			this.setTitle("Terrain");
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			jpane = new SpecialPanel(jeu, images);
			this.setContentPane(jpane);
			jpane.setPreferredSize(new Dimension(jeu.length * 48 + 48,
						 jeu[0].length*48+48));
			jpane.setBackground(Color.black);
			this.pack();
			this.setVisible(true);
			this.setAlwaysOnTop(true);
		}
	}

	public void definirTerrain(final int[][] je) {
	if (jeu.length != je.length && jeu[0].length != je[0].length)
	    throw new TailleErreur();
	for (int i = 0; i<je.length; i++){
	    for (int j=0; j<je[0].length; j++){
		jeu[i][j]=je[i][j];
	    }
	}
    }

	public void declarerImage(final int c, final String s) {
		images.put(c, new ImageIcon("images/" + s));
    }

	public void modifierCase(final int x, final int y, final int val) {
	jeu[x][y]=val;
	}

	public void reafficher() {
	jpane.repaint();
    }
    public void fermer(){
	this.dispose();
    }
}

class TailleErreur extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}

