import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import java.awt.Font;
import java.awt.Image;
import java.awt.Window;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.SequenceInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JLabel;


public class MainUIComboBOx extends JFrame {
	
	static String connectionUrl = "jdbc:sqlserver://192.***.*.*\\***************"; // add your own connection url
	static String username = "*********"; // add your own username
	static String password = "********";  // add your database password
	
	public static void main(String[] args) throws ClassNotFoundException {
		
		try {
				DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				Connection connection = DriverManager.getConnection(connectionUrl, username, password);
				if (connection != null) {
					System.out.println("Connected");
				}
				Statement statement = connection.createStatement();
				ResultSet data = statement.executeQuery("SELECT * FROM HatechTTS.dbo.CommonPropertyItem");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		}
	
	public MainUIComboBOx() {
		
		}
		
	/////////////////////////////////////// page to add voice to database //////////////////////////////////
	public void AddVoice() {
		
		}
		
	/////////////////////////////////////// page to get your text's related voice from database ///////////	
	public void GetVoice() {
		
		}
	
	/////////////////////////////////////// first page of program /////////////////////////////////////////
	public void FirstPage(String msg) {
		
		}
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////

class DBCommonPropertyItem {
	public String ID;
	public String Key;
	public String Name;
	public String CP;
	public String Ind;
	public String IndexOrd;
	
	public DBCommonPropertyItem(String ID, String CP, String Ind, String Name, String Key, String IndexOrd) {
		this.ID = ID;
		this.CP = CP;
		this.Ind = Ind;
		this.Name = Name;
		this.Key = Key;
		this.IndexOrd = IndexOrd;

	}
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////

class DBCommonProperty {
	public String ID;
	public String Key;
	public String Name;
	public String Editable;

	public DBCommonProperty(String ID, String Key, String Name, String Editable) {
		this.ID = ID;
		this.Name = Name;
		this.Key = Key;
		this.Editable = Editable;

	}
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////

class DBResourceAssortment {
	public String ID;
	public String Name;
	public String Member;
	
	public DBResourceAssortment(String ID, String Name, String Member) {
		this.ID = ID;
		this.Name = Name;
		this.Member = Member;

	}
}