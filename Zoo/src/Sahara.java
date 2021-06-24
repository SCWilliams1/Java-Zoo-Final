//Solomon Williams
//
//03/8/19
//
public class Sahara extends Terrain
{
	public Sahara()
	{
		super();
	}
	
	public void createSubTerrain(double t, double h, int c, String tt) {/*Blank*/}
	public void createTerrain(double t, double h, int c)
	{
		set_temp(t);
		set_humidity(h);
		set_capacity(c);
		set_terraintype("Sahara");
	}
	
	
	public void capacity_reduce(int r) {
		set_capacity(Math.max(0,(get_capacity() - r)));
	}
}
