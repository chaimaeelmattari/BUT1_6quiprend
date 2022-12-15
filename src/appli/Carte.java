/**
 * 
 */
package appli;

/**
 * @author 
 *
 */
public class Carte implements Comparable<Carte>{
	
	private int valeur;
	private int valeurt2b;
	
	/**
	 * Crée une Carte, et calcule valeur Tete de boeuf on fonction de sa valeur
	 * @param val
	 */
	public Carte(int val) {
		
		this.valeur = val;
		
	/**   Les cartes :
			en 5 (5, 15, 25, etc.) ont 2 têtes de boeuf,
			en 0 (10, 20, 30, etc.) 3 tetes,
			avec un doublet (11, 22, 33, etc.) 5 têtes. 
			Le nombre 55 est à la fois un doublet et un nombre en 5, 
			c est pourquoi cette carte compte 7 têtes de boeuf.
	*/
			
				
		if(val==55){
			valeurt2b=7;
		} else if(val%10==5){ // en 5 (5, 15, 25, etc.) 
			valeurt2b=2;     // ont 2 têtes de boeuf
		} else if(val%10==0){ // en 0 (10, 20, 30, etc.)
			valeurt2b=3;	  // ont 3 têtes de boeuf   	
		} else if(val%11==0){ // avec un doublet (11, 22, 33, etc.)		
			valeurt2b=5;	  // ont 5 têtes de boeuf
		} else {			  // Cartes normales (23,84,..)
			valeurt2b = 1;
		} 
		//System.out.println("valeur = " + valeur + "  <------> valeurt2b = " + valeurt2b);
	}
	
	public int getvaleur() {
		return valeur;
	}

	public int getvaleurt2b() {
		return valeurt2b;
	}

	@Override
	public int compareTo(Carte c) {
		   return Integer.compare(getvaleur(),c.getvaleur());
		  }
		
	
	@Override
    public String toString()
    {
		if ( valeurt2b >1 ) return this.valeur + "(" + this.valeurt2b + ")";
			else return Integer.toString(this.valeur);	
    }
	
	
}
