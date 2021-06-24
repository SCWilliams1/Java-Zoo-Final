//Solomon Williams
//
//03/8/19
//
public class DeepOcean extends Ocean
{
	public DeepOcean()
	{
		super();
	}

	public void createTerrain(double t, double h, int c)
	{
		set_temp(t);
		set_humidity(h);
		set_capacity(c);
		set_terraintype("Deep Ocean");
	}
	
	public void capacity_reduce(int r) {
		set_capacity(Math.max(0,(get_capacity() - r)));
	}
}
