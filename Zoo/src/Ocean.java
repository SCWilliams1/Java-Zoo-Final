//Solomon Williams
//
//03/8/19
//
public class Ocean extends Terrain
{
	public Ocean()
	{
		super();
	}
	public void createSubTerrain(double t, double h, int c, String tt) {/*Blank*/}
	public void createTerrain(double t, double h, int c)
	{
		set_temp(t);
		set_humidity(h);
		set_capacity(c);
		set_terraintype("Ocean");
	}
	
	public void capacity_reduce(int r) {
		set_capacity(Math.max(0,(get_capacity() - r)));
	}
}
