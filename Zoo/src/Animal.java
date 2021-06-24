//Solomon Williams
//02/21/19
public class Animal {
	int id = -1;
	String name = "";
	String food = "";
	String species = "";
	String terrain = "";
	
	public Animal(String n, String s, String f, String t){
		//constructor
		id = ZooManager.id_return();
		name = n;
		species = s;
		food = f;
		terrain = t;
	}
	
	//Name
	public String get_name() {
		return name;
	}
	public void set_name(String n) {
		name = n;		
	}
	
	//Species
	public String get_species() {
		return species;
	}
	public void set_species(String s) {
		species = s;		
	}
	
	//food
	public String get_food() {
		return food;
	}	
	public void set_food(String f) {
		food = f;		
	}
	
	//Terrain
	public String get_terrain() {
		return terrain;
	}	
	public void set_terrain(String f) {
		terrain = f;		
	}


}
