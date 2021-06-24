//Solomon Williams
//Main File
//02/21/19
//import java.io.*; 
import java.io.File;
import java.util.*;
import java.util.Scanner;
import java.io.PrintWriter;

public class ZooManager {
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
	public ZooManager(ZooTester Test){
		id = 0;// for all other objects run id_return
		
		//Add terrains
		ZooTester ZT = Test;
	}
	
	///methods=============================================================================================
	//State Machine =======================================================================================
	public String method_execute(String a){
		String b;//Next script on the next loop
		
		//zs stands fot Zoo State
		switch(a){
			case "Main Menu"://Main Menu
				b = zs_mainmenu();
				break;
				
			//ANIMAL
			case "Animal Menu"://Animal Menu
				b = zs_animalmenu();
				break;
			case "Animal Add"://ani+
				b = zs_animaladd();
				break;
			case "Animal Change"://ani%
				b = zs_animalchange();
				break;
			case "Animal Kill"://ani-
				b = zs_animalkill();
				break;
			case "Animal List"://ani_list
				b = zs_animallist();
				break;
			case "Animal Search"://ani_list
				b = zs_animalsearch();
				break;
			
			//TERRAIN
			case "Terrain Menu"://ani_list
				b = zs_terrainmenu();
				break;
			case "Terrain List"://ani_list
				b = zs_terrainlist();
				break;
			case "Terrain Search"://ani_list
				b = zs_terrainsearch();
				break;
			case "Food Construction"://food_uc
				b = zs_fooduc();
				break;
			case "Exit"://Exit
				b = "Exit Zoo";
				break;
			default:
				System.out.println("Error");
				b = "Exit Zoo";
				break;
		}
		//b = "Exit Zoo";
		return b;
	}
	
	/* 
	 * So the way that this works is that each script will run and feed out a string into the main switch
	 * The switch returns a string to the tester
	 * this tells the tester whether to end the program or run another cycle of the state machine
	 * 
	 * The states themselves are private and can only be accessed by the Mngr
	 * This prevents crossover
	 * 
	 * To Do: look up enums??
	 */
	//Zoo states
	private String zs_mainmenu() {
		menu();
		return option_select("Animal Menu","Terrain Menu","Exit Zoo");//"Food Menu",
	}
	private String zs_animalmenu() {
		animal_menu();
		return option_select("Animal Add","Animal Change","Animal Kill","Animal List","Animal Search","Main Menu");
	}
	private String zs_animaladd() {		
		System.out.println("Name: ");
		String nm = input.nextLine().toLowerCase();

		System.out.println("Species: ");
		String sp = input.nextLine().toLowerCase();
		
		//Food
		System.out.println("Food: ");
		String fd = input.nextLine().toLowerCase();
		
		//Food validation
		boolean food_flag = false; 
		do {
			for(int i = 0; i < food_id_list.size(); i++) {
				Food f = food_list.get(i);
				if(f.get_name().equals(fd)) {
					food_flag = true;
				}
			}
			if(!food_flag) {
				System.out.println("There are no terrains with that name. Please input an existing Terrain");
				for(int i = 0; i < terrain_id_list.size(); i++) {
					Food f = food_list.get(i);
					System.out.print(f.get_name() +  " | ");
				}
				System.out.println();//space
				fd = input.nextLine().toLowerCase();
			}
		}while(!food_flag);
		
		//Terrain
		System.out.println("Terrain: ");
		String te = input.nextLine().toLowerCase();
		
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
				System.out.println("There are no terrains with that name. Please input an existing Terrain");
				for(int i = 0; i < terrain_id_list.size(); i++) {
					Terrain t = terrain_list.get(i);
					System.out.print(t.get_terraintype() +  " | ");
				}
				System.out.println();//space
				te = input.nextLine().toLowerCase();
			}
		}while(!terrain_flag);		
		
		ani_list.add(new Animal(nm,sp,fd,te));
		ani_id_list.add(object_id);
		
		int v = ani_list.size() - 1;
		System.out.println("Animal -  \"" + ani_list.get(v).get_name() + "\" added.\n");
		
		return "Animal Menu";
	}
	
	//-------------
	private String zs_animalchange() {//Change animal menu
		
		//Which
		if(ani_list.size() > 0){
			
			//List animal
			for(int i = 0; i < ani_id_list.size(); i++) {
				int n = i+1;
				Animal a = ani_list.get(i);
				System.out.println(n +". " + a.get_name() +" | "+a.id + " | " + a.get_species() + " | "+ a.get_food() + " | "+ a.get_terrain());
			}
			
			//Which animal
			System.out.println("Please select an animal: ");
			int c = input.nextInt();
			input.nextLine();
			
			//Validation
			while((c < 1) || (c > ani_id_list.size())) {
				System.out.println("Invalid input. \n\nPlease select an animal: ");
				c = input.nextInt();
				input.nextLine();
			}
			
			animal_change();
			
			//AC stands for animal change
			String y = option_select("ac_Name","ac_Species","ac_Food","Animal Menu");
			if(y == "ac_Name") {ac_Name(ani_list.get(c-1));}
			if(y == "ac_Species") {ac_Species(ani_list.get(c-1));}
			if(y == "ac_Food") {ac_Food(ani_list.get(c-1));}
			if(y == "ac_terrain") {ac_Terrain(ani_list.get(c-1));}	
		}else {
			System.out.println("There are no animals to remove, please create an animal first.\n\n");
		}
		return "Animal Menu";
	}
	private void ac_Name(Animal v){
		System.out.println("Enter a new Name: ");
		String n = input.nextLine().toLowerCase();
		
		v.set_name(n);
	}
	private void ac_Species(Animal v) {
		System.out.println("Enter a new Species: ");
		String n = input.nextLine().toLowerCase();
		
		v.set_species(n);
	}
	private void ac_Food(Animal v) {
		System.out.println("Enter a new Food: ");
		String n = input.nextLine().toLowerCase();
		
		v.set_food(n);
	}
	private void ac_Terrain(Animal v) {
		System.out.println("Enter a new Terrain: ");
		String te = input.nextLine().toLowerCase();
		
		//terrain validation
		boolean flag = false; 
		do {
			for(int i = 0; i < terrain_id_list.size(); i++) {
				Terrain t = terrain_list.get(i);
				if(t.get_terraintype().equals(te)) {
					flag = true;
				}
			}
			if(!flag) {
				System.out.println("There are no terrains with that name. Please input an existing Terrain");
				for(int i = 0; i < terrain_id_list.size(); i++) {
					Terrain t = terrain_list.get(i);
					System.out.print(t.get_terraintype() +  " | ");
				}
				System.out.println();//space
				te = input.nextLine().toLowerCase();
			}
		}while(!flag);
		v.set_terrain(te);
	}
	//-------------
	
	private String zs_animalkill() {
		//List animal
		for(int i = 0; i < ani_id_list.size(); i++) {
			int n = i+1;
			Animal a = ani_list.get(i);
			System.out.println(n +".  "+ a.get_name());
		}
		
		//Kill choice
		if(ani_id_list.size() > 0) {
			System.out.println("Which Animal would you like to remove?:  ");
			int c = input.nextInt();
			input.nextLine();
			
			//Validation
			while((c < 1) || (c > ani_id_list.size())) {
				System.out.println("Invalid input. \n\nWhich option would you like to select: ");
				c = input.nextInt();
				input.nextLine();
			}
			
			//Kill
			int v = (c-1);
			System.out.println("Animal: " + ani_list.get(v).get_name() + " removed");
			ani_id_list.remove(v);
			object_total--;
		}else {
			System.out.println("There are no animals to remove, please create an animal first.\n\n");
		}
		return "Animal Menu";
	}
	
	private String zs_animallist() {//list animals
		for(int i = 0; i < ani_id_list.size(); i++) {
			Animal a = ani_list.get(i);
			System.out.println(a.get_name() +" | "+a.id + " | " + a.get_species() + " | "+ a.get_food() + " | "+ a.get_terrain());
			System.out.println("-------------------------------------------");
		}
		return "Animal Menu";
	}
	private String zs_animalsearch() {//list animals
		if(ani_id_list.size() > 0) {
			//search name
			System.out.println("Animal Name: ");
			String c = input.nextLine().toLowerCase();
			boolean flag = true; 
			for(int i = 0; i < ani_id_list.size(); i++) {
				Animal a = ani_list.get(i);
				if(a.get_name().equals(c)) {
					System.out.println("Name: " + a.get_name() +" | ID: "+a.id + " | Species: " + a.get_species() + " | Food: "+ a.get_food() + " | Terrain: "+ a.get_terrain());
					flag = false;
				}
			}
			if(flag) {System.out.println("There are no animals with that name");}
		}else {
			System.out.println("There are no animals to search, please create an animal first.\n\n");
		}
		return "Animal Menu";
	}
	private String zs_fooduc() {
		food_menu();
		return "Main Menu";
	}

	//Terrain ========================================================================================
	private String zs_terrainmenu() {
		terrain_menu();
		return option_select("Terrain List","Terrain Search","Main Menu");
	}
	
	private String zs_terrainlist() {//list animals
		for(int i = 0; i < terrain_id_list.size(); i++) {
			Terrain t = terrain_list.get(i);
			System.out.println(t.get_terraintype() +" | "+t.id + " | " + t.get_temp() + "°C | "+ t.get_humidity() + "% | "+ t.get_capacity());
			System.out.println("-------------------------------------------");
		}
		return "Terrain Menu";
	}
	private String zs_terrainsearch() {//list animals
		if(terrain_id_list.size() > 0) {
			//search name
			System.out.println("Terrain Name: ");
			String c = input.nextLine().toLowerCase();
			boolean flag = true; 
			for(int i = 0; i < terrain_id_list.size(); i++) {
				Terrain t = terrain_list.get(i);
				if(t.get_terraintype().equals(c)) {
					System.out.println("Name: " + t.get_terraintype() +" | ID: " + t.id + " | Temp: " + t.get_temp() + "°C | Humidity: "+ t.get_humidity() + "% | Capacity:" + t.get_capacity());
					flag = false;
				}
			}
			if(flag) {System.out.println("There are no terrains with that name.");}
		}else {
			System.out.println("There are no Terrains to search.\n\n");
		}
		return "Terrain Menu";
	}
	
	//QOL=============================================================================================
	public int validate() {		
		int cu = 0;
		boolean error = false;
		do {
			try{
				cu = input.nextInt();
				error = false;
			}catch(Exception e){
				System.out.println("Invalid input. Please input an option to select: ");
				input.nextLine();
				cu = input.nextInt();
				error = true;
			}
		}
		while(error);
		return cu;
	}
	
	private String option_select(String ... a) {//Option select
		System.out.println("Which option would you like to select: ");
		int c = validate();
		input.nextLine();
		
		//Validation
		while((c < 1) || (c > a.length)) {
			System.out.println("Invalid input. Please input an option to select: ");
			c = validate();
			input.nextLine();
		}
		String b = a[c-1];
		return b;
	}
	
	public int[] get_aniarray() {
		int []arr = new int[ani_id_list.size()]; 
		return arr;
	}
	
	//ID handling methods ============================================================================
    public static int id_return() {
    	object_total++;//increments total object count
    	return ++object_id;//increments it by one, the returns the increment
    }
    
	public int get_id() {
		return id;
	}

    //Menu==============================================================================================
    private void menu() {
    	System.out.println("=======================================================================");
    	System.out.println("Zoo Menu");
    	System.out.println("1. Animal Options");
    	//System.out.println("2. Food Options");
    	System.out.println("2. Terrain Options");
    	System.out.println("3. Exit");
    	System.out.println("=======================================================================");
    }
    
    private void animal_menu() {
    	System.out.println("=======================================================================");
    	System.out.println("Animal Menu");
    	System.out.println("1. Add Animal");
    	System.out.println("2. Change Animal ");
    	System.out.println("3. Remove Animal");
    	System.out.println("4. List Animals");
    	System.out.println("5. Search Animals");
    	System.out.println("6. Exit");
    	System.out.println("=======================================================================");
    	
    }
    private void animal_change() {
    	System.out.println("=======================================================================");
    	System.out.println("Animal Change Menu");
    	System.out.println("1. Name");
    	System.out.println("2. Species");
    	System.out.println("3. Food");
    	System.out.println("4. Exit");
    	System.out.println("=======================================================================");
    }    
    private void food_menu() {
    	System.out.println("Food Menu");
    	System.out.println("1. Add food");
    	System.out.println("2. List Food");
    	System.out.println("3. Search Food");
    	System.out.println("4. Exit");
    }
    private void terrain_menu() {
    	System.out.println("=======================================================================");
    	System.out.println("Terrain Menu");
    	System.out.println("1. List Terrains");//Lists all the Terrains, number of animals, along with a list of animals
    	System.out.println("2. Search Terrains");//Searches terrains by name
    	System.out.println("3. Exit");
    	System.out.println("=======================================================================");
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
	        /*
			for(int i = 0; i < food_id_list.size(); i++) {
				Food f = food_list.get(i);
				ani += (f.get_name() +"||"+f.id +"||"+f.get_species()+"||"+ f.get_food()+"||"+ f.get_terrain()+"\n");
			}*/
	        
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
			
			//Validation
			while((c < 1) || (c > ani_id_list.size())) {
				ZT.yell("Invalid input","Invalid input");
				c = ZT.create_dialouge_int(str,"Which Animal would you like to remove?(number):  ");
			}
			
			//Kill
			int v = (c-1);
			ZT.yell("Animal Removed","Animal: " + ani_list.get(v).get_name() + " removed");
			ani_id_list.remove(v);
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
			//search name
			String c = ZT.create_dialouge("","Animal Name: ").toLowerCase();
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
	
    
}    
    
    
    
    
