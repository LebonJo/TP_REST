package com.bankonet.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Employe {
	
	@Id
	@GeneratedValue
	private int id;
	private String nom;
	private String prenom;
	private String fonction;
	
	public Employe(){
		super();
	}
	
	public Employe(String nom, String prenom, String fonction){
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.fonction = fonction;
	}
	
	public int getId(){
		return id;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	
	public String getPrenom() {
		return prenom;
	}
	
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	
	public String getFonction() {
		return fonction;
	}
	
	public void setFonction(String fonction) {
		this.fonction = fonction;
	}
	
	public String toString(){
		return "ID : " + this.getId() + ", NOM : " + this.getNom() + ", PRENOM : " + this.getPrenom() + ", FONCTION : " + this.getFonction(); 
	}
	
}
