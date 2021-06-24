//Solomon Williams
//
//03/8/19
//
public class SubTerrain extends Terrain
{
	public SubTerrain()
	{
		super();
	}

	public void createTerrain(double t, double h, int c) {/*Blank*/}
	public void createSubTerrain(double t, double h, int c, String tt)
	{
		set_temp(t);
		set_humidity(h);
		set_capacity(c);
		set_terraintype(tt);
	}
	
	public void capacity_reduce(int r) {
		set_capacity(Math.max(0,(get_capacity() - r)));
	}
}
