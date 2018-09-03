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


public class TTS-Voipi extends JFrame {
	
	private static final long serialVersionUID = 1L;
	protected static final Window frame = null;
	public Boolean Flag;
	public Boolean SqlEscFlag;
	static ArrayList<DBCommonPropertyItem> CommonPropertyItem = new ArrayList<>();
	static ArrayList<DBCommonProperty> CommonProperty = new ArrayList<>();
	static ArrayList<DBResourceAssortment> ResourceAssortment = new ArrayList<>();
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
				
				while (data.next()) {
					DBCommonPropertyItem temp = new DBCommonPropertyItem(data.getString(1), data.getString(2),
							data.getString(3), data.getString(4), data.getString(5), data.getString(6));
					CommonPropertyItem.add(temp);
				}
				
				data = statement.executeQuery("SELECT * FROM HatechTTS.dbo.CommonProperty");
				while (data.next()) {
					DBCommonProperty temp = new DBCommonProperty(data.getString(1), data.getString(2), data.getString(3),
							data.getString(4));
					CommonProperty.add(temp);
				}
				
				data = statement.executeQuery("SELECT * FROM HatechTTS.dbo.ResourceAssortment");
				while (data.next()) {
					DBResourceAssortment temp = new DBResourceAssortment(data.getString(1), data.getString(2),
							data.getString(3));
					ResourceAssortment.add(temp);
				}
				statement.close();
				connection.close();
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						TTS-Voipi frame = new TTS-Voipi();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			
		
		}
	
	public TTS-Voipi() {
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			dx =  (int) (screenSize.getWidth()/2 - 400);
			dy =  (int) (screenSize.getHeight()/2 - 400);
			setTitle("VOIPI");
			setFont(new Font("Dialog", Font.BOLD, 12));
			setForeground(Color.BLACK);
			setVisible(true);
			setLocation(new Point(10, 0));
			setResizable(false);
			FirstPage("");
		}
		
	/////////////////////////////////////// page to add voice to database //////////////////////////////////
	public void AddVoice() {
			Flag = false;
			SqlEscFlag = true;
			
			////////////////////////////// read company image to show
			ImageIcon icon = new ImageIcon(getClass().getResource("/hatech-blue.png"));
			Image scaleImage = icon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
			
			////////////////////////////// set some features of frame
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(dx, dy, 800, 800);
			JPanel contentPane1 = new JPanel();
			contentPane1.setBackground(Color.GRAY);
			contentPane1.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane1);
			contentPane1.setLayout(null);
			
			////////////////////////////// add label to display company logo
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setBounds(300, 19, 250, 49);
			lblNewLabel.setIcon(new ImageIcon(scaleImage));
			contentPane1.add(lblNewLabel);
			
			////////////////////////////// add field to show Pronounce Type text
			JTextField textField = new JTextField();
			textField.setFocusable(false);
			textField.setFocusTraversalKeysEnabled(false);
			textField.setFont(new Font("Tahoma", Font.BOLD, 22));
			textField.setSelectedTextColor(Color.DARK_GRAY);
			textField.setEditable(false);
			textField.setBorder(null);
			textField.setBackground(Color.GRAY);
			textField.setText("\u0646\u0648\u0639 \u06AF\u0648\u06CC\u0634");
			textField.setBounds(465, 80, 120, 35);
			contentPane1.add(textField);
			textField.setColumns(10);
			
			//////////////////////////////// add combobox to display Pronounce Type
			JComboBox<String> comboBox = new JComboBox<String>();
			comboBox.setFont(new Font("Tahoma", Font.BOLD, 22));
			comboBox.setBounds(200, 80, 250, 35);
			contentPane1.add(comboBox);
			for (int i = 0; i < CommonPropertyItem.size(); i++)
				if (CommonPropertyItem.get(i).CP.equals(CommonProperty.get(0).ID))
					comboBox.addItem(CommonPropertyItem.get(i).Name);
			
			/////////////////////////////// add field to show Word Type text
			JTextField textField_1 = new JTextField();
			textField_1.setFocusable(false);
			textField_1.setFocusTraversalKeysEnabled(false);
			textField_1.setEditable(false);
			textField_1.setBorder(null);
			textField_1.setBackground(Color.GRAY);
			textField_1.setFont(new Font("Tahoma", Font.BOLD, 22));
			textField_1.setToolTipText("");
			textField_1.setText("\u0646\u0648\u0639 \u06A9\u0644\u0645\u0647");
			textField_1.setBounds(465, 130, 131, 35);
			contentPane1.add(textField_1);
			textField_1.setColumns(10);
			
			//////////////////////////////// add combobox to display Word Type
			JComboBox<String> comboBox_1 = new JComboBox<String>();
			comboBox_1.setFont(new Font("Tahoma", Font.BOLD, 22));
			comboBox_1.setBounds(200, 130, 250, 35);
			contentPane1.add(comboBox_1);
			for (int i = 0; i < CommonPropertyItem.size(); i++)
				if (CommonPropertyItem.get(i).CP.equals(CommonProperty.get(1).ID))
					comboBox_1.addItem(CommonPropertyItem.get(i).Name);
			
			//////////////////////////////// add field to show WordPosition Type text 
			JTextField textField_2 = new JTextField();
			textField_2.setFocusable(false);
			textField_2.setFocusTraversalKeysEnabled(false);
			textField_2.setText("\u062C\u0627\u06CC\u06AF\u0627\u0647 \u06A9\u0644\u0645\u0647");
			textField_2.setFont(new Font("Tahoma", Font.BOLD, 22));
			textField_2.setBorder(null);
			textField_2.setBackground(Color.GRAY);
			textField_2.setEditable(false);
			textField_2.setBounds(465, 180, 166, 35);
			contentPane1.add(textField_2);
			textField_2.setColumns(10);
			
			///////////////////////////////// add combobox to display WordPosition Type
			JComboBox<String> comboBox_2 = new JComboBox<String>();
			comboBox_2.setBounds(200, 180, 250, 35);
			comboBox_2.setFont(new Font("Tahoma", Font.BOLD, 22));
			contentPane1.add(comboBox_2);
			for (int i = 0; i < CommonPropertyItem.size(); i++)
				if (CommonPropertyItem.get(i).CP.equals(CommonProperty.get(2).ID))
					comboBox_2.addItem(CommonPropertyItem.get(i).Name);
			
			//////////////////////////////// add field to show Assortment text 			
			JTextField textField_3 = new JTextField();
			textField_3.setFocusable(false);
			textField_3.setFocusTraversalKeysEnabled(false);
			textField_3.setBorder(null);
			textField_3.setBackground(Color.GRAY);
			textField_3.setBackground(Color.GRAY);
			textField_3.setText("\u062F\u0633\u062A\u0647 \u0628\u0646\u062F\u06CC");
			textField_3.setFont(new Font("Tahoma", Font.BOLD, 22));
			textField_3.setEditable(false);
			textField_3.setBounds(465, 234, 166, 35);
			contentPane1.add(textField_3);
			textField_3.setColumns(10);
			
			///////////////////////////////// add combobox to display Assortment Types
			JComboBox<String> comboBox_3 = new JComboBox<String>();
			comboBox_3.setBounds(200, 230, 250, 35);
			comboBox_3.setFont(new Font("Tahoma", Font.BOLD, 22));
			contentPane1.add(comboBox_3);
			for (int i = 0; i < ResourceAssortment.size(); i++)
				comboBox_3.addItem(ResourceAssortment.get(i).Name);
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