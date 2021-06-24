//Solomon Williams
//Main File
//03/9/19

public abstract class Terrain implements Comparable<Terrain>
{
	private double avg_temp;
	private double avg_hum;
	private int capacity;
	private String terrainType;
	int id = -1;
	
	public Terrain() {
		//constructor
		avg_temp = 0;
		avg_hum = 0;
		capacity = 0;
		terrainType = "none";
		id = ZooManager.id_return();
	}
	public abstract void createTerrain(double t, double h, int c);
	public abstract void createSubTerrain(double t, double h, int c, String tt);
	
	//SET-----------------------
	public void set_temp(double t) {
		avg_temp = t;
	}
	public void set_humidity(double h) {
		avg_hum = h;
	}
	public void set_capacity(int c) {
		capacity = c;
	}
	public void set_terraintype(String v) {
		terrainType = v;
	}
	
	
	//GET-------------------------
	public double get_temp() {
		return avg_temp;
	}
	public double get_humidity() {
		return avg_hum;
	}
	public int get_capacity() {
		return capacity;
	}
	public String get_terraintype() {
		return terrainType;
	}	
	
	
	
	
	//GEN-------------------------
	public String toString()
	{
		return get_terraintype() + "must be at " + get_temp() + " degrees Fahrenheit.";
	}
	
	public int compareTo(Terrain obj)
	{
		if (avg_temp < obj.avg_temp)
		    return -1;
		else if (avg_temp == obj.avg_temp)
		    return 0;
		else 
		    return 1;
   }
	
}

