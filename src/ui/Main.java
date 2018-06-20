/*
 * Copyright 2012 Richard Beech rp.beech@gmail.com
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package ui;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;




import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import simulation.GroundItem;
import simulation.Location;
import simulation.NPC;
import simulation.levelDef;

public class Main {
	public static final String MYSQLURL = "jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&useSSL=false";
	public static final String serviceUser = "stardustagent";
	public static final String servicePass = "observe4024crisis";
	public static final boolean DEBUG = true;
	static GroundItem Land[] = new GroundItem[20];
	public static byte[][] routeMap = new byte[129][129];
public static List <NPC> dataSubjectList=new ArrayList<NPC>();
	static int landSize = 64;
	static Location[][] LandScape = new Location[64][64];
	static Connection con;
	static Timer aitimer;
	static UserInterface myWindow;
	static String myLog; 
	
	public static void start(final UserInterface window){
		myWindow=window;
		System.out.println("start main");
		//UI.createAndShowGUI();
		// Register the JDBC driver for MySQL.
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						// Define URL of database server
										
						// Get a connection to the database
						con = DriverManager.getConnection(MYSQLURL, serviceUser,
								servicePass);
				
						// Display URL and connection information
						System.out.println("URL: " + MYSQLURL);
						System.out.println("Connection: " + con);
				
						// Get a Statement object
//					} catch (MySQLIntegrityConstraintViolationException e) {
//						System.out.println("fail");
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					for (int i=0; i<100;i++)
					{
						populateNames();
					}
						
					for (int i=0; i<100;i++)
					{
						System.out.println(Main.dataSubjectList.get(i).get_firstname()+
								" "+Main.dataSubjectList.get(i).get_surname());
					}
						
		
		// loop forever every .5 secs running NPC moves
//		Timer sectimer = new Timer();
//		sectimer.scheduleAtFixedRate(new TimerTask() {
//			public void run() {
//				
//				window.setLogString(myLog);
//				System.out.println("start NPC tasks");
//
//				NPCtasks.regenHiveShieldDisruptors();
//				PlayerTasks.checkDisconnects();
//				try {
//					VendorTasks.restock();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}, 0, 1000);
//
//		// loop forever every.5secs check for new routepath
//		 aitimer = new Timer();
//		aitask(aitimer);	
	}
	


	/**
	 * @param timer
	 */
	


	private static void drawmap(byte sx, byte sy, byte ex, byte ey) {
		// draw map with star and end points
		for (int i = 0; i < routeMap.length; i++) {
			for (int j = 0; j < routeMap.length; j++)
				if (i == sx * 2 && j == sy * 2) {
					System.out.print("S ");
				} else if (i == ex * 2 && j == ey * 2) {
					System.out.print("E ");

				} else {
					System.out.print(routeMap[i][j] + " ");
				}
			System.out.println();
		}
	}

	public static void populateNames() {
		myLog=myLog +(" populating level list /n");
		
		try {
			Statement stmt;
				
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from getrandfullfrfemname limit 1");
			
			while (rs.next()) {
				NPC dataSubject=new NPC();
				dataSubject.set_firstname(rs.getString("firstname"));
				dataSubject.set_surname(rs.getString("surname"));
					dataSubjectList.add(dataSubject);
			}
			for (int i=0; i<Main.dataSubjectList.size();i++)
			{
				System.out.println(Main.dataSubjectList.get(i).get_firstname()+
						" "+Main.dataSubjectList.get(i).get_surname());
			}
			
			//con.close();
//		} catch (MySQLIntegrityConstraintViolationException e) {
//			System.out.println("fail");
		} catch (Exception e) {
			e.printStackTrace();
		}// end catch
	}// end method totallyRandom



	public static void stop() {
		// TODO Auto-generated method stub
	//	aitimer.cancel();
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}