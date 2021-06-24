//Solomon Williams
//Main File
//02/21/19
//import java.io.*; 
import java.io.File;
import java.util.*;
import java.util.Scanner;
import java.io.PrintWriter;

public class ZooAppManager {
	//Zoo universals
	static int object_id = 0;
	static int object_total = 1;
	public ArrayList<Animal> ani_list = new ArrayList<Animal>(0);
	public ArrayList<Integer> ani_id_list = new ArrayList<Integer>(0);
	
	public ArrayList<Food> food_list = new ArrayList<Food>(0);
	public ArrayList<Integer> food_id_list = new ArrayList<Integer>(0);
	
	public ArrayList<Terrain> terrain_list = new ArrayList<Terrain>(0);
	public ArrayList<Integer> terrain_id_list = new ArrayList<Integer>(0);
	
	public String file_txt = "";
	
	
	//Mngr privates
	private int id = 0;
	Scanner input = new Scanner(System.in);
	
	//Constructor
	public ZooAppManager(ZooTester Test){
		id = 0;// for all other objects run id_return
		
		//Add terrains
		ZooTester ZT = Test;
	}
	
    //=========================================================================================================================================
	public void ani_add(ZooTester ZT) {
		String nm = ZT.create_dialouge("","Name").toLowerCase();
		String sp = ZT.create_dialouge("","Species: ").toLowerCase();
		String fd = ZT.create_dialouge("","Food: ").toLowerCase();
		String te = ZT.create_dialouge("","Terrain: ").toLowerCase();
		
		//terrain validation
		boolean terrain_flag = false; 
		do {
			for(int i = 0; i < terrain_id_list.size(); i++) {
				Terrain t = terrain_list.get(i);
				if(t.get_terraintype().equals(te)) {
					terrain_flag = true;
				}
			}
			if(!terrain_flag) {
				ZT.yell("ERROR", "There are no terrains with that name. Please input an existing Terrain");
				String str = "";
				for(int i = 0; i < terrain_id_list.size(); i++) {
					Terrain t = terrain_list.get(i);
					str += t.get_terraintype() +  " | ";
				}
				ZT.yell("",str);
				te = ZT.create_dialouge("","Terrain: ").toLowerCase();
			}
		}while(!terrain_flag);		
		
		ani_list.add(new Animal(nm,sp,fd,te));
		ani_id_list.add(object_id);
		
		int v = ani_list.size() - 1;
		ZT.yell("Animal List","Animal -  \"" + ani_list.get(v).get_name() + "\" added.\n");
	}
	public void ani_kill(ZooTester ZT) {
		//List animal
		String str = "";
		for(int i = 0; i < ani_id_list.size(); i++) {
			int n = i+1;
			Animal a = ani_list.get(i);
			str += (n +".  "+ a.get_name()+"\n");
		}
		
		//Kill choice
		if(ani_id_list.size() > 0) {
			int c = ZT.create_dialouge_int(str,"Which Animal would you like to remove?(number):  ");
			
			/*
			//validation
			while((c < 1) || (c > ani_id_list.size())) {
				ZT.yell("Invalid input","Invalid input");
				c = ZT.create_dialouge_int(str,"Which Animal would you like to remove?(number):  ");
			}*/
			
			//Kill
			int v = (c-1);
			ZT.yell("Animal Removed","Animal: " + ani_list.get(v).get_name() + " removed");
			ani_id_list.remove(v);
			ani_list.remove(v);
			object_total--;
		}else {
			ZT.yell("No animals","There are no animals to remove, please create an animal first.\n\n");
		}
	}
	public void ani_list(ZooTester ZT) {
		String str = "";
		for(int i = 0; i < ani_id_list.size(); i++) {
			Animal a = ani_list.get(i);
			str += a.get_name() +" | "+a.id + " | " + a.get_species() + " | "+ a.get_food() + " | "+ a.get_terrain();
			str += "\n";
		}
		ZT.yell("Animal List",str);
	}
	public int ani_search(ZooTester ZT) {
		if(ani_id_list.size() > 0) {
			//List animal
			String str_3 = "";
			for(int i = 0; i < ani_id_list.size(); i++) {
				int n = i+1;
				Animal a = ani_list.get(i);
				str_3 += (n +". " + a.get_name() +" | "+a.id + " | " + a.get_species() + " | "+ a.get_food() + " | "+ a.get_terrain() + "\n");
			}
			
			//search name
			String c = ZT.create_dialouge(str_3,"Animal Name: ").toLowerCase();
			boolean flag = true;
			int num = 0;
			String str = "";
			for(int i = 0; i < ani_id_list.size(); i++) {
				Animal a = ani_list.get(i);
				if(a.get_name().equals(c)) {
					str += ("Name: " + a.get_name() +" | ID: "+a.id + " | Species: " + a.get_species() + " | Food: "+ a.get_food() + " | Terrain: "+ a.get_terrain() +"\n");
					flag = false;
					num = i;
				}
			}
			
			if(flag) {ZT.yell("","There are no animals with that name"); return -1;}
			ZT.yell("Animals", str);
			return num;
		}else {
			ZT.yell("No animals","There are no animals to search, please create an animal first.\n\n");
		}
		return -1;
	}
	public int ani_change(ZooTester ZT) {
		int num = -1;
		//Which
		if(ani_list.size() > 0){
			//List animal
			String str = "";
			for(int i = 0; i < ani_id_list.size(); i++) {
				int n = i+1;
				Animal a = ani_list.get(i);
				str += (n +". " + a.get_name() +" | "+a.id + " | " + a.get_species() + " | "+ a.get_food() + " | "+ a.get_terrain() + "\n");
			}
			
			//Which animal
			int c = ZT.create_dialouge_int(str,"Please select an animal: ");
			/*
			//Validation
			while((c < 1) || (c > ani_id_list.size())) {
				c = ZT.create_dialouge_int("Invalid input!\n" + str,"Please select a valid animal: ");
			}
			*/
			Animal v = ani_list.get(c-1);
			
			//What to change
			String str_2 = "1. Name\n2. Species\n3. Food\n4. Terrain\n";
			int g = ZT.create_dialouge_int(str_2,"Option: ") - 1;
			/*
			while((g < 0) || (g > 3)) {
				g = ZT.create_dialouge_int("Invalid input!\n"+str_2,"Option: ") - 1;
			}*/

			//Change
			switch(g){
				case 0://name
					String nm = ZT.create_dialouge("","Enter a new Name: ").toLowerCase();
					v.set_name(nm);
				break;
				
				case 1://species
					String sp = ZT.create_dialouge("","Enter a new species: ").toLowerCase();
					v.set_species(sp);
				break;
				
				case 2://food
					String fo = ZT.create_dialouge("","Enter a new food: ").toLowerCase();
					v.set_food(fo);
				break;
				
				case 3://terrain
					String te = ZT.create_dialouge("","Enter a new terrain: ").toLowerCase();
					boolean terrain_flag = false; 
					do {
						for(int i = 0; i < terrain_id_list.size(); i++) {
							Terrain t = terrain_list.get(i);
							if(t.get_terraintype().equals(te)) {
								terrain_flag = true;
							}
						}
						if(!terrain_flag) {
							ZT.yell("ERROR", "There are no terrains with that name. Please input an existing Terrain");
							String str_3 = "";
							for(int i = 0; i < terrain_id_list.size(); i++) {
								Terrain t = terrain_list.get(i);
								str_3 += t.get_terraintype() +  " | ";
							}
							ZT.yell("",str_3);
							te = ZT.create_dialouge("","Terrain: ").toLowerCase();
						}
					}while(!terrain_flag);
				break;
			}
			num = c-1;
		}else {
			ZT.yell("","There are no animals to remove, please create an animal first.\n\n");
		}
		return num;
	}
	
	//File Management===============================================================================
    public boolean file_read(String txt) {
    	try {
	        File fileName = new File(txt);
	        Scanner inFile = new Scanner(fileName);
	        file_txt = txt;
	        
	        //read from file(inFile);
        	int ani_num = inFile.nextInt();
        	inFile.nextLine();
        	inFile.nextLine();
        	///*
        	for(int i = 0; i < ani_num; i++) {
    			String ani  = inFile.nextLine().toLowerCase();
    			
    			String[] data = ani.split("\\|\\|");
    			//System.out.println(Arrays.toString(data));
    			///*
        		String n = data[0];
        		String s = data[1];
        		String f = data[2];
        		String t = data[3];
        		
        		ani_list.add(new Animal(n,s,f,t));
        		ani_id_list.add(object_id);
        		//*/
        	}
        	inFile.nextLine();
        	
        	//Food Handle
        	int food_num = inFile.nextInt();
        	inFile.nextLine();
        	inFile.nextLine();
        	
        	for(int j = 0; j < food_num; j++) {
        		//No food
    			String food = inFile.nextLine().toLowerCase();    			
    			String[] data = food.split("\\|\\|");
    			
        		String f = data[0];
        		food_list.add(new Food(f));
        		food_id_list.add(object_id);
        	}
        	inFile.nextLine();
        	
        	//Terrain
        	int terrain_num = inFile.nextInt();
        	inFile.nextLine();
        	inFile.nextLine();
        	for(int u = 0; u < terrain_num; u++) {
    			String terr = inFile.nextLine().toLowerCase();    			
    			String[] data = terr.split("\\|\\|");
    			
        		double n = Double.parseDouble(data[1]);
        		double s = Double.parseDouble(data[2]);
        		int f = Integer.parseInt(data[3]);
        		String t = data[4];
        		
        		terrain_list.add(new SubTerrain());
                terrain_list.get(u).createSubTerrain(n,s,f,t);
        		
        		terrain_id_list.add(object_id);
        	}
        	//End
        	inFile.close();
    	}catch(Exception FilenotFound){
    		System.out.println("File Not Found. Terminating Application.");
    		return false;
    	}
    	System.out.println("File Found. Starting Application.");
    	return true;
    }
    
    public boolean file_write(String txt) {
    	try {
    		PrintWriter printWriter = new PrintWriter(txt);
    		
    		//Create text
	        String br = "-------------------------------------------------------\n";
	        
	        //animal
	        String ani = "";
	        ani += (ani_list.size() + "\n" + br);
			for(int i = 0; i < ani_list.size(); i++) {
				Animal a = ani_list.get(i);
				ani += (a.get_name() +"||"+a.get_species()+"||"+ a.get_food()+"||"+ a.get_terrain()+"\n");
			}
			ani += "\n";
			
			//food
	        String food = "";
	        food += (food_list.size() + "\n" + br + "\n");
	        ///*
			for(int i = 0; i < food_id_list.size(); i++) {
				Food f = food_list.get(i);
				//ani += (f.get_name() +"||"+f.id +"||"+f.get_species()+"||"+ f.get_food()+"||"+ f.get_terrain()+"\n");
			}
			//*/
	        
	        String ter = "";
	        ter += (terrain_list.size() + "\n" + br);
			for(int i = 0; i < terrain_list.size(); i++) {
				Terrain t = terrain_list.get(i);
				ter += (t.get_terraintype() +"||"+t.get_temp()+"||"+ t.get_humidity()+"||"+ t.get_capacity()+"||"+ t.get_terraintype()+"\n");
			}
	        
	        
	        //write to file
	        //System.out.print(ani+food+ter);
	        printWriter.print(ani+food+ter);
	        printWriter.close();
    	}catch(Exception FilenotFound){
    		System.out.println("File Not Found. Terminating Application.");
    		return false;
    	}
    	System.out.print("\nFile Successfully saved!\n");
    	return true;
    }
    

	
 
}    
    
    
    
    
