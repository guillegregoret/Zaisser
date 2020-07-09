package database;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;

public class DatabaseMgr extends JFrame {

	private JPanel contentPane;
	private JTextField direccion;
	private JTextField usuario;
	private JTextField contrasenia;
	private JButton btnDefault;
	private JButton button;
	private JButton btnIniciarDb;
	private JButton btnStop;
	private RunHSQLDB Database;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try{
			  JFrame.setDefaultLookAndFeelDecorated(true);
			  UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}
		catch (Exception e)	{
			  e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DatabaseMgr frame = new DatabaseMgr();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DatabaseMgr() {
		setTitle("Database Manager");
		String[] config =ReadConfig.RFD();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 440, 209);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDireccinDelServidor = new JLabel("Direcci\u00F3n del servidor");
		lblDireccinDelServidor.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblDireccinDelServidor.setBounds(10, 11, 127, 20);
		contentPane.add(lblDireccinDelServidor);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblUsuario.setBounds(10, 42, 127, 20);
		contentPane.add(lblUsuario);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblContrasea.setBounds(10, 73, 127, 20);
		contentPane.add(lblContrasea);
		
		direccion = new JTextField();
		direccion.setBounds(147, 12, 267, 20);
		contentPane.add(direccion);
		direccion.setColumns(10);
		
		usuario = new JTextField();
		usuario.setColumns(10);
		usuario.setBounds(147, 43, 267, 20);
		contentPane.add(usuario);
		
		contrasenia = new JTextField();
		contrasenia.setColumns(10);
		contrasenia.setBounds(147, 74, 267, 20);
		contentPane.add(contrasenia);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if(((String)direccion.getText()).indexOf("jdbc:hsqldb:hsql:") ==0) {
				String dir = (String)direccion.getText()+"\n"+usuario.getText()+"\n"+contrasenia.getText();
				WriteConfig.CambiarDireccion(dir);
			};
			}
		});
		btnGuardar.setBounds(287, 108, 127, 23);
		contentPane.add(btnGuardar);
		
		direccion.setText(config[0]);
		usuario.setText(config[1]);
		contrasenia.setText(config[2]);
		
		btnDefault = new JButton("Default");
		btnDefault.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				direccion.setText("jdbc:hsqldb:hsql://127.0.0.1/arkana");
				usuario.setText("SA");
				contrasenia.setText("");
			}
		});
		btnDefault.setBounds(147, 108, 127, 23);
		contentPane.add(btnDefault);
		
		button = new JButton("?");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String msg = "";
				msg += "Formato dirección de servidor: jdbc:hsqldb:hsql://xxx.xxx.xxx.xxx/nombre_base_de_datos \n";
				msg += "Usuario no puede ser nulo, contraseña si, aunque no se recomienda. Por defecto: 'SA', ''";
				JOptionPane.showMessageDialog(null, msg, "Ayuda de conexión base de datos", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		button.setBounds(10, 108, 48, 23);
		contentPane.add(button);
		
		btnIniciarDb = new JButton("Start");
		btnIniciarDb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrintWriter pw = new PrintWriter(System.out);
				Database = new RunHSQLDB();
				Database.HSQLDBRunner(pw);
			}
		});
		btnIniciarDb.setBounds(10, 142, 89, 23);
		contentPane.add(btnIniciarDb);
		
		btnStop = new JButton("Stop");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RunHSQLDB.hsqlServer.stop();
			}
		});
		btnStop.setBounds(109, 142, 89, 23);
		contentPane.add(btnStop);
	}
}
