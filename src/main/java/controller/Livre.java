package controller;

public class Livre {
	public String isbn;
	public String auteur;
	public String titre;
	public int annee;
	public float prix;
	
	public Livre(String i,String a,String t,int an,float p) {
		isbn=i;
		auteur=a;
		titre=t;
		annee=an;
		prix=p;
	}
}
