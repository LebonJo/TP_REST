package com.bankonet.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.bankonet.model.Employe;

@Stateless
public class EmployeService {
	
	@PersistenceContext(unitName="Banque") private EntityManager em;
	
	public EmployeService(){
		super();
	}
	
	public List<Employe> findAll(){		
		String texteQuery = "select e from Employe as e";
		Query query = em.createQuery(texteQuery);
		List<Employe> employes = (List<Employe>)query.getResultList();
		return employes;
	}
	
	public Employe findById(int id){
		Employe employe = em.find(Employe.class, id);
		return employe;
	}
	
	public Employe createEmploye(String nom, String prenom, String fonction){
		Employe employe = new Employe(nom, prenom, fonction);
		em.persist(employe);
		return employe;
	}
	
	public void updateEmploye(Employe employe){
		em.merge(employe);
	}
	
	public void deleteEmploye(int id){
		Employe employe = em.find(Employe.class, id);
		em.remove(employe);
	}
}
