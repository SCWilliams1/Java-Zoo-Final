//Solomon Williams
//02/21/19
public class Food {
	int id = -1;
	String name = "";
	
	public Food(String n){
		id = ZooManager.id_return();
		name = n;		
	}
	
	//Name
	public String get_name() {
		return name;
	}
	public void set_name(String n) {
		name = n;		
	}
}
