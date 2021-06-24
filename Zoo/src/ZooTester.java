//Solomon Williams
//Main File
//02/21/19

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.util.Optional;

import javax.swing.JOptionPane;

import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;


public class ZooTester extends Application{
	///*

	public static void main(String[] args)
	{
		launch(args);
	}
	//zoo start
	ZooAppManager Zoo = new ZooAppManager(this);
	boolean started = false;
    @Override
    public void start(Stage stage) throws Exception
    {
    	//======================================================================
    	stage.setTitle("Charli Horse Zoo");

    	//Top Menu(title)---------------------------------------------------------
    		Label lb_Title = new Label("Charli Horss Zoo");
    		lb_Title.setAlignment(Pos.CENTER);
    	//Left menu(option)---------------------------------------------------------

    		Label ani_current = new Label("Animal #: n/a");
    		ani_current.setId("ani_current");
    		//ani_current.setAlignment(Pos.CENTER);
    		
    		Button btn_start = new Button("START");
    		Button btn_exit = new Button("EXIT");
    		VBox pn_menu = new VBox(ani_current,btn_start,btn_exit);
    		pn_menu.setId("pn_menu");

    	//Center Menu(Animal Data)---------------------------------------------------------

    		//top
    		Label ani_file = new Label("File Status: No file Found");
    		ani_file.setAlignment(Pos.CENTER);

    		//mid
    				//Var
    				Label lb_nam = new Label("Name: ");
    				Label lb_spc = new Label("Species: ");
    				Label lb_fd = new Label("Food: ");
    				Label lb_ter = new Label("Terrain: ");
    				
    				Label nm_data = new Label("N/A");
    				Label sp_data = new Label("N/A");
    				Label fd_data = new Label("N/A");
    				Label tn_data = new Label("N/A");
    				
    	      //Grid Pane 
    	      GridPane ani_display = new GridPane();    
    	      
    	      //Setting size for the pane  
    	      ani_display.setMinSize(350, 100); 
    	       
    	      //Setting the padding  
    	      ani_display.setPadding(new Insets(10, 10, 10, 10)); 
    	      ani_display.setId("app_pane");
    	      
    	      //Setting the vertical and horizontal gaps between the columns 
    	      ani_display.setVgap(5); 
    	      ani_display.setHgap(5);       
    	      
    	      //Setting the Grid alignment 
    	      ani_display.setAlignment(Pos.CENTER);
    	      
    	      ColumnConstraints c1 = new ColumnConstraints();
    	      c1.setPercentWidth(25);
    	      ColumnConstraints c2 = new ColumnConstraints();
    	      c2.setPercentWidth(75);
    	      ani_display.getColumnConstraints().addAll(c1, c2);
    	           
    	      //Arranging all the nodes in the grid 
    	      ani_display.add(lb_nam, 0, 0); ani_display.add(nm_data, 1, 0);  
				ani_display.add(lb_spc, 0, 1); ani_display.add(sp_data, 1, 1); 
				ani_display.add(lb_fd, 0, 2); ani_display.add(fd_data, 1, 2); 
				ani_display.add(lb_ter, 0, 3); ani_display.add(tn_data, 1, 3);
				
				ani_display.setId("ani_display");
				ani_display.setAlignment(Pos.TOP_LEFT);

    		//bottom---------------------------------------------------------
    		Button anibtn_add = new Button("ADD");
    		Button anibtn_kill = new Button("REMOVE");
    		Button anibtn_search = new Button("SEARCH");
    		Button anibtn_list = new Button("LIST");
    		Button anibtn_change = new Button("CHANGE");
    		
    		anibtn_add.setId("ani_button");
    		anibtn_kill.setId("ani_button");
    		anibtn_search.setId("ani_button");
    		anibtn_list.setId("ani_button");
    		anibtn_change.setId("ani_button");
    		
    		anibtn_add.setDisable(true);
    		anibtn_kill.setDisable(true);
    		anibtn_search.setDisable(true);
    		anibtn_list.setDisable(true);
    		anibtn_change.setDisable(true);
    		
    		HBox ani_options = new HBox(anibtn_add,anibtn_kill,anibtn_search,anibtn_list,anibtn_change);
    		ani_options.setAlignment(Pos.CENTER);
    		
    		//full setup
    		VBox pn_animal = new VBox(ani_file,ani_display,ani_options);
    		pn_animal.setId("app_pane");
    		
    	//Right Menu(terrain)---------------------------------------------
    		Label ter_name = new Label("Terrain: n/a");
    		Label ter_temp = new Label("Temp: n/a");
    		Label ter_hum  = new Label("Humidity: n/a");
    		Label ter_cap  = new Label("Capacity: n/a");

    		VBox pn_terra = new VBox(ter_name,ter_temp,ter_hum,ter_cap);
    		pn_terra.setId("terra");
    	//==================================================================================
    	//Borderpane setup
    	Label lb_empty = new Label("");
    	BorderPane app_pane = new BorderPane(pn_animal,lb_Title,pn_terra,lb_empty,pn_menu); 
    	app_pane.setId("app_pane");
    	app_pane.setBottom(null);
    	
    	// set alignment 
    	app_pane.setAlignment(pn_animal, Pos.CENTER); 
    	app_pane.setAlignment(lb_Title, Pos.CENTER); 
    	app_pane.setAlignment(pn_terra, Pos.CENTER); 
    	app_pane.setAlignment(pn_menu, Pos.CENTER); 
    	app_pane.setPadding(new Insets(15, 20, 10, 10));  

    	//Scene finish
    	Scene scene = new Scene(app_pane, 700, 250);
    	scene.getStylesheets().add("Zoo.css");//getClass().getResource("Zoo.css").toExternalForm());
    	stage.setScene(scene);
    	stage.show();
    	//Zoo.file_write("Zoo.txt");
    	//System.out.println("GoodBye!");
    	
    	//ACTIONS

    	//Start button
        btn_start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(Zoo.file_read("Zoo.txt")) {
                	started = true;
            		anibtn_add.setDisable(false);
            		anibtn_kill.setDisable(false);
            		anibtn_search.setDisable(false);
            		anibtn_list.setDisable(false);
            		anibtn_change.setDisable(false);
                	
            		btn_start.setDisable(true);
            		
            		ani_file.setText("File Status: Found");
            		ani_current.setText("Animal #: " + Zoo.ani_list.size());
            		
            		
            		
                }else {
                	Alert alert = new Alert(AlertType.INFORMATION);
                	alert.setTitle("Information Dialog");
                	alert.setHeaderText(null);
                	alert.setContentText("No File Present. ");

                	alert.showAndWait();
                }
            }
        }); 
        btn_exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	Platform.exit();
            }
        });
    	//Ani Actions
        ZooTester zt = this;
        anibtn_add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	Zoo.ani_add(zt);
                Animal a = Zoo.ani_list.get(Zoo.ani_list.size() - 1);
                ani_current.setText("Animal #: " + Zoo.ani_list.size());
                
				nm_data.setText(a.get_name());
				sp_data.setText(a.get_species());
				fd_data.setText(a.get_food());
				tn_data.setText(a.get_terrain());
				
				int n = 0;
				for(int i = 0; i < Zoo.terrain_id_list.size(); i++) {
					Terrain t = Zoo.terrain_list.get(i);
					if(t.get_terraintype().equals(a.get_terrain())) {
						n = i;
					}
				}
				
				Terrain t = Zoo.terrain_list.get(n);
	    		ter_name.setText("Terrain: " + t.get_terraintype());
	    		ter_temp.setText("Temp : " + Double.toString(t.get_temp()) + "°C");
	    		ter_hum.setText("Humidity: " + Double.toString(t.get_humidity()) + "%");
	    		ter_cap.setText("Capacity: " + Integer.toString(t.get_capacity()));
            }
        });
        
        anibtn_kill.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	Zoo.ani_kill(zt);
            	ani_current.setText("Animal #: " + Zoo.ani_list.size());
            }
        });
        
        anibtn_search.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	int num = Zoo.ani_search(zt);
            	if(num != -1) {
	                Animal a = Zoo.ani_list.get(num);
	                ani_current.setText("Animal #: " + Zoo.ani_list.size());
	                
					nm_data.setText(a.get_name());
					sp_data.setText(a.get_species());
					fd_data.setText(a.get_food());
					tn_data.setText(a.get_terrain());
					
					int n = 0;
					for(int i = 0; i < Zoo.terrain_id_list.size(); i++) {
						Terrain t = Zoo.terrain_list.get(i);
						if(t.get_terraintype().equals(a.get_terrain())) {
							n = i;
						}
					}
					
					Terrain t = Zoo.terrain_list.get(n);
		    		ter_name.setText("Terrain: " + t.get_terraintype());
		    		ter_temp.setText("Temp : " + Double.toString(t.get_temp()) + "°C");
		    		ter_hum.setText("Humidity: " + Double.toString(t.get_humidity()) + "%");
		    		ter_cap.setText("Capacity: " + Integer.toString(t.get_capacity()));
            	}
            }
        });
       
        anibtn_list.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	Zoo.ani_list(zt);
            	ani_current.setText("Animal #: " + Zoo.ani_list.size());
            }
        });
        
        anibtn_change.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	int num = Zoo.ani_change(zt);
            	if(num != -1) {
	                Animal a = Zoo.ani_list.get(num);
	                ani_current.setText("Animal #: " + Zoo.ani_list.size());
	                
					nm_data.setText(a.get_name());
					sp_data.setText(a.get_species());
					fd_data.setText(a.get_food());
					tn_data.setText(a.get_terrain());
					
					int n = 0;
					for(int i = 0; i < Zoo.terrain_id_list.size(); i++) {
						Terrain t = Zoo.terrain_list.get(i);
						if(t.get_terraintype().equals(a.get_terrain())) {
							n = i;
						}
					}
					
					Terrain t = Zoo.terrain_list.get(n);
		    		ter_name.setText("Terrain: " + t.get_terraintype());
		    		ter_temp.setText("Temp : " + Double.toString(t.get_temp()) + "°C");
		    		ter_hum.setText("Humidity: " + Double.toString(t.get_humidity()) + "%");
		    		ter_cap.setText("Capacity: " + Integer.toString(t.get_capacity()));
            	}
            }
        });
    	 /**/
    }
    @Override
    public void stop() {
        System.out.println("Stop");
        if(started) {
        	Zoo.file_write("Zoo.txt");
        }
    }
    
    //
    public String create_dialouge(String s,String t) {
    	TextInputDialog dialog = new TextInputDialog("T");
    	 
    	dialog.setTitle("User Input Required");
    	dialog.setHeaderText(s);
    	dialog.setContentText(t);
    	 
    	Optional<String> result = dialog.showAndWait();
    	return result.get();
    }
    public int create_dialouge_int(String s,String t) {
    	TextInputDialog dialog = new TextInputDialog("T");
    	 
    	dialog.setTitle("User Input Required");
    	dialog.setHeaderText(s);
    	dialog.setContentText(t);
    	 
    	Optional<String> result = dialog.showAndWait();
    	
    	int nt = 0;
    	try {
    		nt = Integer.parseInt(result.get());
    		return nt;
    	}catch(Exception e){
    		
    		return -1;
    	}
    	
		
    } 
    public void yell(String t,String s) {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle(t);
    	alert.setHeaderText(null);
    	alert.setContentText(s);
    	alert.showAndWait();
    }
}

/*
//Zoo Controller Everything runs from here
public static void main(String [] args)
{
	ZooManager Zoo = new ZooManager();//handles everything
	if(Zoo.file_read("Zoo.txt")) {	
    	String zState = "Main Menu";//Initialize the Loop
    	while(zState != "Exit Zoo") {
    		zState = Zoo.method_execute(zState);//FSM
    	}
	}
	Zoo.file_write("Zoo.txt");
	System.out.println("GoodBye!");
}
//*/ 

