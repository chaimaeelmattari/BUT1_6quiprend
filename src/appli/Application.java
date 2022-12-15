/**
 * 
 */
//package appli;

/**
 * @author 
 *
 */
//public class Application {

	/**
	 * @param args
	 */
//	public static void main(String[] args) {
		// TODO Auto-generated method stub

//	}

//} 

package appli;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static appli.Console.clearScreen;
import static appli.Console.pause;

public class Application {
	 // Démonstration
	//List<Joueur> listejoueur = new List<Joueur> ();
	 
	private static Random rd = new Random();
	private static final int NBRCARTE = 104;
	private static ArrayList<Joueur> listejoueur = new ArrayList<>();
	private static ArrayList<Carte> listecarte = new ArrayList<>();
	private static ArrayList<ArrayList<Carte> > serie2carte = new ArrayList<ArrayList<Carte>>(4);
	private static boolean gameover=false;
	
	public static void main(String[] args) throws IOException {
	Scanner sccarte = new Scanner(System.in);	
		
		init();
		
		int valcarte;
		ArrayList<Integer> keyj = new ArrayList<>();
		HashMap<Integer, Joueur> coupjoue = new HashMap<>();
		
		
		
		System.out.print("Les " + listejoueur.size() + " joueurs sont " + listejoueur.get(0).getnom());
		for(int j = 1; j<listejoueur.size()-1;j++) {
			System.out.print(", " + listejoueur.get(j).getnom());
		}
		System.out.println(" et " + listejoueur.get(listejoueur.size()-1).getnom() + ". Merci de jouer à 6 qui prend !");
	
		
		while(!gameover){
			keyj.clear();
			coupjoue.clear();
					
			for(int j = 0; j<listejoueur.size();j++) {
				System.out.println("A " + listejoueur.get(j).getnom() + " de jouer.");
				afficheseries();		
				listejoueur.get(j).affichecartes();
				System.out.print("Saisissez votre choix : " );
				valcarte = sccarte.nextInt();
				//int valcarte=getrep("Saisissez votre choix : ");
				while (!listejoueur.get(j).detientcarte(valcarte)){  
					System.out.println("Vous n'avez pas cette carte, saisissez votre choix : " );  
					valcarte = sccarte.nextInt();  
					//valcarte=getrep("\"Vous n'avez pas cette carte, saisissez votre choix : ");
				}
			
				//coupjoue.put(valcarte,listejoueur.get(j).getnom());
				coupjoue.put(valcarte,listejoueur.get(j));
				keyj.add(j,valcarte);
				clearScreen();
			}
		
			Collections.sort(keyj);
		
			if (keyj.get(0) < pluspetitcarte2series()) { // Règle n°4:La carte la plus faible
				//int pluspetite=keyj.get(0);
				System.out.print("Les cartes ");
				System.out.print(keyj.get(0) + " (" + coupjoue.get(keyj.get(0)) + ")");
				
				for(int k=1;k<keyj.size()-1;k++) { 
					System.out.print(", " + keyj.get(k) + " (" + coupjoue.get(keyj.get(k)) + ")");
				}
				if (listejoueur.size() >1)
					System.out.print(" et " + keyj.get(listejoueur.size()-1) + " (" + coupjoue.get(keyj.get(listejoueur.size()-1)) + ")");
				
				System.out.println(" vont être posées.");
				System.out.println("Pour poser la carte " + keyj.get(0) +", "+ coupjoue.get(keyj.get(0)) + " doit choisir la série qu'il va ramasser.");
				afficheseries();
				
				int serieramasse = getrep("Saisissez votre choix : ");
				while (serieramasse < 0 || serieramasse > 4 )
					serieramasse = getrep("Saisissez votre choix : ");
				
				int tete2boeufs=coupjoue.get(keyj.get(0)).addseriet2b(serie2carte.get(serieramasse-1));
				System.out.println(coupjoue.get(keyj.get(0)) + " a ramassé " + tete2boeufs +" têtes de boeufs");
				serie2carte.get(serieramasse-1).clear();
				serie2carte.get(serieramasse-1).add(new Carte(keyj.get(0)));
				
				
				
				coupjoue.get(keyj.get(0)).removecarte(keyj.get(0));
				keyj.remove(0);
				
			
				for (int k=0;k<keyj.size();k++) { 
					addcarte2serie(keyj.get(k));
					coupjoue.get(keyj.get(k)).removecarte(keyj.get(k));
					
				}
				//afficheseries();	
				
			} else {
				int tete2boeufs=0;
				int idjoueur=0;
				for (int k=0;k<keyj.size();k++) { 
					int idserie=getnextidx(keyj.get(k)) / 100 - 1;
					int idcarte=getnextidx(keyj.get(k)) %100 ;
					if ( idcarte == 5 ) {    // Règle n°3: Série terminé
						tete2boeufs=coupjoue.get(keyj.get(k)).addseriet2b(serie2carte.get(idserie));
						
						serie2carte.get(idserie).clear();
						serie2carte.get(idserie).add(new Carte(keyj.get(k)));
						idjoueur=k;
						
					}
					
					addcarte2serie(keyj.get(k));
					coupjoue.get(keyj.get(k)).removecarte(keyj.get(k));
				}
				
				
				System.out.print("Les cartes ");
				System.out.print(keyj.get(0) + " (" + coupjoue.get(keyj.get(0)) + ")");
				
				for(int n=1;n<keyj.size()-1;n++) { 
					System.out.print(", " + keyj.get(n) + " (" + coupjoue.get(keyj.get(n)) + ")");
				}
				if (listejoueur.size() >1)
					System.out.print(" et " + keyj.get(listejoueur.size()-1) + " (" + coupjoue.get(keyj.get(listejoueur.size()-1)) + ")");
				
				System.out.println(" Ont été posées.");
				afficheseries();
				System.out.println(coupjoue.get(keyj.get(idjoueur)) + " a ramassé " + tete2boeufs +" têtes de boeufs");
				//gameover=true;
			}
			
			
		}
		
		//sccarte.close();	
	}

	private static void afficheseries() {
			
			for(int s=0;s<serie2carte.size(); s++) {
				ArrayList<Carte> lstcarte = new ArrayList<>();
				lstcarte = serie2carte.get(s);
				System.out.print(" - Série n° " + Integer.toString(s+1) + " : " + lstcarte.get(0));
				for (int e=1; e< lstcarte.size();e++) {
					System.out.print(", " + lstcarte.get(e));
				}

			System.out.println();
		}
		
	}
	
	private static int addcarte2serie(int valcarte) {
		ArrayList<Carte> allcartes = new ArrayList<>();
		for(int s=0;s<serie2carte.size(); s++) {
			allcartes.addAll(serie2carte.get(s));
		}
		
		Carte newcarte=new Carte(valcarte);
		Collections.sort(allcartes);
		allcartes.add(newcarte);
		Collections.sort(allcartes);
		Carte tmpcarte=allcartes.get(allcartes.indexOf(newcarte) - 1);
		int serieidx=-1;
		for(int s=0;s<serie2carte.size(); s++) {
			if(serie2carte.get(s).contains(tmpcarte)) {
				serie2carte.get(s).add(newcarte);
				serieidx=s;
			}
				
		}
		return serie2carte.get(serieidx).size();
	}
	
	private static int getnextidx(int valcarte) {
		ArrayList<Carte> allcartes = new ArrayList<>();
		for(int s=0;s<serie2carte.size(); s++) {
			allcartes.addAll(serie2carte.get(s));
		}
		
		Carte newcarte=new Carte(valcarte);
		Collections.sort(allcartes);
		allcartes.add(newcarte);
		Collections.sort(allcartes);
		
		Carte tmpcarte=allcartes.get(allcartes.indexOf(newcarte) - 1);
		int serieidx=0;
		for(int s=0;s<serie2carte.size(); s++) {
			if(serie2carte.get(s).contains(tmpcarte)) {
				serieidx=s;
			}
				
		}
		int idserieidcarte=(serieidx +1 ) * 100 + serie2carte.get(serieidx).size();
		//idserieidcarte=serie2carte.get(serieidx).size();
		
		return idserieidcarte;
	}
	
	private static void init() throws IOException {
		
		for (int i = 0; i < NBRCARTE; ++i)
			listecarte.add(new Carte(i+1));
				
		Collections.shuffle(listecarte);
			
		String fileName = "ressources/config.txt";
        Scanner scan = new Scanner(new File(fileName));
        int idjoueur=0;
        while(scan.hasNextLine())  {
        	
            String  nomjoueur= scan.nextLine();
            Joueur joueur = new Joueur(idjoueur++,nomjoueur);
            try {
				listejoueur.add(joueur);				
			} catch (Exception e) {
				// A faire
				e.printStackTrace();
			}
        }
        		
        if (listejoueur.size() < 2 || listejoueur.size() > 10) {
        	System.out.println(" Nombre de joueurs (" + listejoueur.size() + ") incorrect. Il doit être compris entre 2 et 10 ");
        	System.exit(1);
        }
        for( int i=0; i < listejoueur.size() * 10;i++) {
        	listejoueur.get(i/10).addcarte(listecarte.get(i));
        }
        
        for( int i=0; i < 4;i++) {
        	ArrayList<Carte> lstcarte = new ArrayList<>();
        	int idx= (listejoueur.size() * 10)+i;
        	lstcarte.add(listecarte.get(idx));
        	serie2carte.add(lstcarte);
        }
                
        for( int i=0; i < (listejoueur.size() * 10)+4;i++) {      	
        	listecarte.remove(i);
        }
		
	}
	
	public static int getrep(String s){
		Scanner sc = new Scanner(System.in);
		try{
			System.out.println(s);
			String srep = null;
			srep = sc.next();
			//sc.close();
			return Integer.parseInt(srep);
		} catch (NumberFormatException e){
			//System.err.println("Veuillez saisir un nombre");
			//sc.close();
		}
		return -1;
	}
	
	private static int pluspetitcarte2series() {
			int pluspetite=serie2carte.get(0).get(serie2carte.get(0).size()-1).getvaleur();
			for(int s=1;s<serie2carte.size(); s++) {
				if ( serie2carte.get(s).get(serie2carte.get(s).size()-1).getvaleur() < pluspetite)
				pluspetite=serie2carte.get(s).get(serie2carte.get(s).size()-1).getvaleur();
	       }
			return pluspetite;
	}
	// Utilitaire

}
