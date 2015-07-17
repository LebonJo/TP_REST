import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.*;

public class bankonetClient {
	
	private static final String REST_URL = "http://localhost:8080/bankonet-rest/api";

	public static void main(String[] args){
		
		Logger.getLogger("").setLevel(Level.SEVERE);
		
		Scanner inputScan = new Scanner(System.in);
		
		Client client = ClientBuilder.newClient();
		
		WebTarget target = client.target(REST_URL);
		
		boolean boucle = true;
		while(boucle){
			System.out.println("***** BANKONET CLIENT *****");
			System.out.println("");
			System.out.println("0. Arrêter le programme");
			System.out.println("1. Lister les employés");
			System.out.println("2. Recherche un employé par id");
			System.out.println("");
			System.out.println("Choisir une option");
			String input = inputScan.nextLine();
			if(input.equals("0")){
				System.out.println("Arrêt du programme");
				boucle = false;
				break;
			} else if(input.equals("1")){
				System.out.println("Liste des employés");
				Response res = target.path("/employes").request().get();
				if(res.getStatus() == 200){
					JSONArray jsonRes = new JSONArray(res.readEntity(String.class));
					for(int i=0; i<jsonRes.length(); i++){
						JSONObject obj = new JSONObject(jsonRes.get(i).toString());
						System.out.println("ID : " + obj.get("id") + ", NOM : " + obj.get("nom") + ", PRENOM : " + obj.get("prenom") + ", FONCTION : " + obj.get("fonction"));
					}
				} else {
					System.out.println("Aucun employé trouvé");
				}
				System.out.println("");
			} else if(input.equals("2")){
				System.out.println("Saisir l'id de l'employé recherché :");
				int id = Integer.valueOf(inputScan.nextLine());
				String uri = REST_URL + "/employes/" + id;
				Response res = target.path("/employes/" + id).request().get();
				if(res.getStatus() == 200){
					JSONObject jsonRes = new JSONObject(res.readEntity(String.class));
					System.out.println("ID : " + jsonRes.get("id") + ", NOM : " + jsonRes.get("nom") + ", PRENOM : " + jsonRes.get("prenom") + ", FONCTION : " + jsonRes.get("fonction"));
				} else {
					System.out.println("Aucune correspondance trouvée");
				}
				System.out.println("");
			} else {
				System.out.println("Option non valide !");
			}
		}
	}
	
}
