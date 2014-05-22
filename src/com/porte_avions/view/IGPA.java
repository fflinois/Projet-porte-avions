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

	private static final long serialVersionUID = 1L;

	int[][] jeu;
	int[] menu;
    Color blanc = Color.white;
    Color noir = new Color(150,120,120);
	private final HashMap<Integer, ImageIcon> images;
	private String[] textAAfficher;

	// protected HashMap<Integer, ImageIcon> imagesMenu;

	SpecialPanel(final int[][] je, final int[] men,
			final HashMap<Integer, ImageIcon> im, final String[] text) {
		jeu = je;
		menu = men;
		images = im;
		textAAfficher = text;
		this.setForeground(Color.YELLOW);
	}

	public void setTextAAfficher(final String[] textAAfficher) {
		this.textAAfficher = textAAfficher;
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
		for (int i = 0; i < menu.length; i++) {
			images.get(menu[i]).paintIcon(this, g, jeu.length * 48 + 48 + 62,
					i * 45 + 150);
		}
		for (int i = 0; i < textAAfficher.length; i++) {
			g.drawString(textAAfficher[i] + "", jeu.length * 48 + 48,
					30 + 20 * i);
		}

		g.drawString("Cliquez sur une icône :", jeu.length * 48 + 48,
				20 * textAAfficher.length + 30);
    }

}


public class IGPA extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int[][] jeu;
	private final int[] menu;
	private final HashMap<Integer, ImageIcon> images;
	private SpecialPanel jpane;
	private String[] textAAfficher = { "Initialisation", "Partie" };
	public IGPA(final int x, final int y, final Partie partie) {
		jeu = new int[x][y];
		images = new HashMap<Integer, ImageIcon>();
		menu = new int[5];
	}

	public void creerFenetre() {
		if (!this.isVisible()) {
			this.setTitle("Terrain");
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			jpane = new SpecialPanel(jeu, menu, images, textAAfficher);
			jpane.setLayout(null);
			this.setContentPane(jpane);
			jpane.setPreferredSize(new Dimension(jeu.length * 48 + 48 + 200,
						 jeu[0].length*48+48));
			jpane.setBackground(Color.black);
			this.pack();
			this.setVisible(true);
			this.setAlwaysOnTop(true);
		}
	}

	public void setTextAAfficher(final String[] textAAfficher) {
		this.textAAfficher = textAAfficher;
		jpane.setTextAAfficher(textAAfficher);
	}

	public void definirMenu(final int[] menu) {
		for (int i = 0; i < menu.length; i++) {
			this.menu[i] = menu[i];
		}
	}

	public void definirTerrain(final int[][] je) {
		if (jeu.length != je.length && jeu[0].length != je[0].length)
			throw new TailleErreur();
		for (int i = 0; i < je.length; i++) {
			for (int j = 0; j < je[0].length; j++) {
				jeu[i][j] = je[i][j];
			}
		}
	}

	public void declarerImage(final int c, final String s) {
		images.put(c, new ImageIcon("images/" + s));
    }

	public void modifierCase(final int x, final int y, final int val) {
		jeu[x][y] = val;
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

