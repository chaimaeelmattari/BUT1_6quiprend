/**
 * 
 */
package appli;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author 
 *
 */
public class Joueur {
	private int id;
	private String nom;
	private ArrayList<Carte> listecarte = new ArrayList<>();
	private ArrayList<Carte> listecartet2b = new ArrayList<>();
	private int score;
	
	public Joueur(int id, String nom ) {
		this.id=id;
		this.nom=nom;
	}
	
	int getid() {
		return this.id;
	}
	
	String getnom() {
		return this.nom;
	}
	public void addcarte(Carte carte) {
		listecarte.add(carte);
		Collections.sort(listecarte);
	}
	public void tricartes(){
		listecarte.sort(Comparator.comparing(Carte::getvaleur));
	}
	
	public void removecarte(Carte carte) {
		listecarte.remove(carte);
		}
	
	public void removecarte(int valcarte) {
		for (int i=0; i< this.listecarte.size()-1;i++)
			if (this.listecarte.get(i).getvaleur() == valcarte)
				listecarte.remove(i);
	}
	public  ArrayList<Carte> getlistecarte() {
		return listecarte;
	}
	
	public void addcartet2b(Carte carte) {
		listecartet2b.add(carte);
		score+=carte.getvaleurt2b();
	}
	
	public int addseriet2b(ArrayList<Carte> seriecarte) {
		int tete2b = 0;
		listecartet2b.addAll(seriecarte);
		for (Carte c :  seriecarte)
			tete2b+=c.getvaleurt2b();
		return tete2b;
	}
	
	public void removecartet2b(Carte carte) {
		listecartet2b.remove(carte);
	}

	void affichecartes() {
		System.out.print(" - Vos cartes : " + listecarte.get(0));
		for(int j = 1; j<listecarte.size();j++) 
			System.out.print(", " + listecarte.get(j));
		System.out.println();
	}
	
	boolean detientcarte(int valcarte) {
		for(Carte c : listecarte)
			if (c.getvaleur() == valcarte)
				return true;
		return false;
	}
	
	@Override
    public String toString()
    {
		return this.nom;	
    }
}
