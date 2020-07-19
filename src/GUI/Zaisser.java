package GUI;
//JOptionPane.showMessageDialog(null, "Seleccionar un cliente", "Error", JOptionPane.INFORMATION_MESSAGE); 
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import com.sun.xml.internal.ws.util.StringUtils;

import Dominio.Camion;
import Dominio.Grafo;
import Dominio.Planta;
import Dominio.Ruta;
import Gestores.Gestor_Camion;
import Gestores.Gestor_Planta;
import Gestores.Gestor_Ruta;
import database.RunHSQLDB;

import java.awt.CardLayout;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import java.awt.Canvas;
import java.awt.Toolkit;
import java.awt.Button;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import java.awt.Color;

public class Zaisser extends JFrame {
	private final static String MENUCAMION="name_5541826395100";
	private final static String MENUPLANTA="name_5544200076900";
	private JPanel contentPane;
	private JFormattedTextField patente_crear;
	private JTextField km_recorridos_crear;
	private JTextField marca_modelo_crear;
	private JFormattedTextField fecha_compra_crear;
	private JTextField costo_por_km_crear;
	private JTextField costo_por_hora_crear;
	private JTable table_buscar_camion;
	private JTextField costo_por_km_mod;
	private JTextField costo_por_hora_mod;
	private JTextField marca_modelo_mod;
	private JTextField km_recorridos_mod;
	private RunHSQLDB Database;
	private JTextField gen_ruta_distancia;
	private JTextField gen_ruta_duracion;
	private JTextField gen_ruta_pesomax;
	private JTextField nombre_nueva_planta;
	private Grafo grafo;

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
					Zaisser frame = new Zaisser();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Zaisser() {
		
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\DIED\\Zaisser\\Zaisser.ico"));
		setTitle("Zaisser - Log\u00EDstica de Transporte");
		setResizable(false);
		
		PrintWriter pw = new PrintWriter(System.out);
		Database = new RunHSQLDB();
		Database.HSQLDBRunner(pw);
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 603, 386);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		JButton seleccionar_camion_btn = new JButton("Seleccionar");
		JButton modificar_camion_btn = new JButton("Modificar");
		JButton eliminar_camion_btn = new JButton("Eliminar");
		JPanel menu_principal = new JPanel();
		contentPane.add(menu_principal, "name_5500757628000");
		menu_principal.setLayout(null);
		
		JButton btnNewButton = new JButton("Administrar Camiones");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				((CardLayout)getContentPane().getLayout()).show(getContentPane(), "MENUCAMION");
				//menu_principal.hide();
			}
		});
		btnNewButton.setBounds(10, 25, 142, 23);
		menu_principal.add(btnNewButton);
		
		JButton btnNewButton_4 = new JButton("Administrar Plantas");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout)getContentPane().getLayout()).show(getContentPane(), MENUPLANTA);
			}
		});
		btnNewButton_4.setBounds(10, 63, 142, 23);
		menu_principal.add(btnNewButton_4);
		
		JPanel menu_camion = new JPanel();
		contentPane.add(menu_camion, "MENUCAMION");
		menu_camion.setLayout(null);
		
		JFormattedTextField patente_mod = new JFormattedTextField();
		patente_mod.setColumns(10);
		patente_mod.setBounds(110, 14, 120, 20);
				
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 633, 326);
		menu_camion.add(tabbedPane);
		
		JPanel buscar_camion = new JPanel();
		tabbedPane.addTab("Buscar", null, buscar_camion, null);
		buscar_camion.setLayout(null);
		
		JButton btnNewButton_2 = new JButton("New button");
		btnNewButton_2.setBounds(370, 194, 89, 23);
		buscar_camion.add(btnNewButton_2);
		
		JPanel modificar_eliminar_camion = new JPanel();
		tabbedPane.addTab("Modificar/Eliminar", null, modificar_eliminar_camion, null);
		modificar_eliminar_camion.setLayout(null);
		
		JFormattedTextField fecha_compra_mod = new JFormattedTextField();
		fecha_compra_mod.setColumns(10);
		fecha_compra_mod.setBounds(395, 73, 89, 20);
		modificar_eliminar_camion.add(fecha_compra_mod);
		
		JButton btnNewButton_3 = new JButton("Buscar");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificar_camion_btn.setEnabled(false);
				eliminar_camion_btn.setEnabled(false);
				seleccionar_camion_btn.setEnabled(false);
				Camion buscar_camion = new Camion();
				if(!patente_mod.getText().isEmpty())buscar_camion.setPatente(patente_mod.getText());
				if(!costo_por_hora_mod.getText().isEmpty())buscar_camion.setCosto_por_hora(Double.valueOf(costo_por_hora_mod.getText()));
				if(!costo_por_km_mod.getText().isEmpty())buscar_camion.setCosto_por_km(Double.valueOf(costo_por_km_mod.getText()));
				if(!km_recorridos_mod.getText().isEmpty())buscar_camion.setKm_recorridos(Integer.valueOf(km_recorridos_mod.getText()));
				if(!marca_modelo_mod.getText().isEmpty())buscar_camion.setMarca_modelo(marca_modelo_mod.getText());
				if(!fecha_compra_mod.getText().isEmpty()) {
				try {
					SimpleDateFormat out = new SimpleDateFormat("yyyy-MM-dd");
					SimpleDateFormat in = new SimpleDateFormat("dd/MM/yyyy");
					Date date = in.parse(fecha_compra_mod.getText());
					buscar_camion.setFecha_compra(out.parse(out.format(date)));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				}

				ArrayList<Camion> Auxcamiones = Gestor_Camion.getCamiones(buscar_camion);
				if(Auxcamiones.isEmpty())	JOptionPane.showMessageDialog(null, "No se encontraron camiones", "Sin resultados", JOptionPane.INFORMATION_MESSAGE);
				else {	
					((DefaultTableModel)table_buscar_camion.getModel()).setRowCount(0);
				for (Camion c : Auxcamiones) {
						Object[] auxRow = new Object[6];
						auxRow[0] = c.getPatente();
						auxRow[1] = c.getCosto_por_hora();
						auxRow[2] = c.getCosto_por_km();
						auxRow[3] = c.getMarca_modelo();
						auxRow[4] = c.getKm_recorridos();
						auxRow[5] = c.getFecha_compra();
						((DefaultTableModel)table_buscar_camion.getModel()).addRow(auxRow);
					}
					
					//((CardLayout)getContentPane().getLayout()).show(getContentPane(), MENUCAMION);
				}
			}
		});
		btnNewButton_3.setBounds(468, 104, 89, 23);
		modificar_eliminar_camion.add(btnNewButton_3);
		
		
		eliminar_camion_btn.setEnabled(false);
		eliminar_camion_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Camion camion = new Camion();
				if(table_buscar_camion.getSelectedRow()>=0) {
				camion.setPatente((String) table_buscar_camion.getModel().getValueAt(table_buscar_camion.getSelectedRow(), 0));
				Gestor_Camion.eliminar_camion(camion);
				((DefaultTableModel)table_buscar_camion.getModel()).setRowCount(0);
				}
			}
		});
		eliminar_camion_btn.setBounds(371, 264, 89, 23);
		modificar_eliminar_camion.add(eliminar_camion_btn);
		
		
		modificar_camion_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					Camion nuevo_camion = new Camion();
					if(!patente_mod.getText().isEmpty())nuevo_camion.setPatente(patente_mod.getText());
					if(!costo_por_hora_mod.getText().isEmpty())nuevo_camion.setCosto_por_hora(Double.valueOf(costo_por_hora_mod.getText()));
					if(!costo_por_km_mod.getText().isEmpty())nuevo_camion.setCosto_por_km(Double.valueOf(costo_por_km_mod.getText()));
					if(!km_recorridos_mod.getText().isEmpty())nuevo_camion.setKm_recorridos(Integer.valueOf(km_recorridos_mod.getText()));
					if(!marca_modelo_mod.getText().isEmpty())nuevo_camion.setMarca_modelo(marca_modelo_mod.getText());
					if(!fecha_compra_mod.getText().isEmpty()) {
	
					try {
						SimpleDateFormat out = new SimpleDateFormat("yyyy-MM-dd");
						SimpleDateFormat in = new SimpleDateFormat("dd/MM/yyyy");
						Date date = in.parse(fecha_compra_mod.getText());
						nuevo_camion.setFecha_compra(out.parse(out.format(date)));
					} catch (ParseException e1) {
						e1.printStackTrace();
					}}

					
					System.out.println(nuevo_camion.getMarca_modelo());
					System.out.println(nuevo_camion.getPatente());
					System.out.println(nuevo_camion.getCosto_por_hora());
					System.out.println(nuevo_camion.getCosto_por_km());
					System.out.println(nuevo_camion.getFecha_compra());
					System.out.println(nuevo_camion.getKm_recorridos());
					Gestor_Camion.modificar_camion(nuevo_camion);
					
					patente_mod.setText("");
					costo_por_hora_mod.setText("");
					costo_por_km_mod.setText("");
					marca_modelo_mod.setText("");
					km_recorridos_mod.setText("");
					fecha_compra_mod.setText("");

					
					((DefaultTableModel)table_buscar_camion.getModel()).setRowCount(0);
				
			}
		});
		modificar_camion_btn.setEnabled(false);
		modificar_camion_btn.setBounds(371, 104, 89, 23);
		modificar_eliminar_camion.add(modificar_camion_btn);
		
		
		seleccionar_camion_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


				
				if(table_buscar_camion.getSelectedRow()>=0) {
				
				patente_mod.setEnabled(false);
				patente_mod.setText((String) table_buscar_camion.getModel().getValueAt(table_buscar_camion.getSelectedRow(), 0));
				costo_por_hora_mod.setText(String.valueOf(table_buscar_camion.getModel().getValueAt(table_buscar_camion.getSelectedRow(), 1)));
				costo_por_km_mod.setText(String.valueOf(table_buscar_camion.getModel().getValueAt(table_buscar_camion.getSelectedRow(), 2)));
				marca_modelo_mod.setText((String) table_buscar_camion.getModel().getValueAt(table_buscar_camion.getSelectedRow(), 3));
				km_recorridos_mod.setText(String.valueOf(table_buscar_camion.getModel().getValueAt(table_buscar_camion.getSelectedRow(), 4)));
				//fecha_compra_mod.setText(String.valueOf(table_buscar_camion.getModel().getValueAt(table_buscar_camion.getSelectedRow(), 5)));
				
				try {
					SimpleDateFormat in = new SimpleDateFormat("yyyy-MM-dd");
					Date date = in.parse(String.valueOf(table_buscar_camion.getModel().getValueAt(table_buscar_camion.getSelectedRow(), 5)));	
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					System.out.println(" -> "+sdf.format(date));
					

					fecha_compra_mod.setText(sdf.format(date));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				modificar_camion_btn.setEnabled(true);
				//Gestor_Camion.eliminar_camion(camion);
				((DefaultTableModel)table_buscar_camion.getModel()).setRowCount(0);
				}
				eliminar_camion_btn.setEnabled(false);
			}
		});
		seleccionar_camion_btn.setEnabled(false);
		seleccionar_camion_btn.setBounds(468, 264, 89, 23);
		modificar_eliminar_camion.add(seleccionar_camion_btn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 137, 549, 123);
		modificar_eliminar_camion.add(scrollPane);
		
			table_buscar_camion = new JTable();
			scrollPane.setViewportView(table_buscar_camion);
			
			table_buscar_camion.setToolTipText("");
			table_buscar_camion.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table_buscar_camion.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Patente", "Costo por hora", "Costo por Km", "Marca y modelo", "Km. realizados", "Fecha de compra"
				}
			));
			
			table_buscar_camion.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
		        public void valueChanged(ListSelectionEvent event) {
		            eliminar_camion_btn.setEnabled(true);
		            seleccionar_camion_btn.setEnabled(true);
		        }
		    });
			
			costo_por_km_mod = new JTextField();
			costo_por_km_mod.setColumns(10);
			costo_por_km_mod.setBounds(395, 42, 89, 20);
			modificar_eliminar_camion.add(costo_por_km_mod);
			
			costo_por_hora_mod = new JTextField();
			costo_por_hora_mod.setColumns(10);
			costo_por_hora_mod.setBounds(395, 11, 89, 20);
			modificar_eliminar_camion.add(costo_por_hora_mod);
			modificar_eliminar_camion.add(patente_mod);
			
			JLabel lblCostoPorHora_1 = new JLabel("Costo por hora");
			lblCostoPorHora_1.setBounds(295, 14, 90, 14);
			modificar_eliminar_camion.add(lblCostoPorHora_1);
			
			JLabel lblNewLabel_1_1_1 = new JLabel("Costo por km.");
			lblNewLabel_1_1_1.setBounds(295, 45, 90, 14);
			modificar_eliminar_camion.add(lblNewLabel_1_1_1);
			
			JLabel lblNewLabel_2_1_1 = new JLabel("Fecha de compra");
			lblNewLabel_2_1_1.setBounds(295, 76, 90, 14);
			modificar_eliminar_camion.add(lblNewLabel_2_1_1);
			
			marca_modelo_mod = new JTextField();
			marca_modelo_mod.setColumns(10);
			marca_modelo_mod.setBounds(110, 76, 150, 20);
			modificar_eliminar_camion.add(marca_modelo_mod);
			
			JLabel lblNewLabel_2_2 = new JLabel("Marca y modelo");
			lblNewLabel_2_2.setBounds(10, 79, 90, 14);
			modificar_eliminar_camion.add(lblNewLabel_2_2);
			
			JLabel lblNewLabel_1_2 = new JLabel("Km recorridos");
			lblNewLabel_1_2.setBounds(10, 48, 90, 14);
			modificar_eliminar_camion.add(lblNewLabel_1_2);
			
			km_recorridos_mod = new JTextField();
			km_recorridos_mod.setColumns(10);
			km_recorridos_mod.setBounds(110, 45, 120, 20);
			modificar_eliminar_camion.add(km_recorridos_mod);
			
			JLabel lblNewLabel_3 = new JLabel("Patente");
			lblNewLabel_3.setBounds(10, 17, 58, 14);
			modificar_eliminar_camion.add(lblNewLabel_3);
			
		
		JPanel crear_camion = new JPanel();
		tabbedPane.addTab("Crear", null, crear_camion, null);
		crear_camion.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("Guardar Cami\u00F3n");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Camion nuevo_camion = new Camion();
				nuevo_camion.setPatente(patente_crear.getText());
				nuevo_camion.setCosto_por_hora(Double.valueOf(costo_por_hora_crear.getText()));
				nuevo_camion.setCosto_por_km(Double.valueOf(costo_por_km_crear.getText()));
				nuevo_camion.setKm_recorridos(Integer.valueOf(km_recorridos_crear.getText()));
				nuevo_camion.setMarca_modelo(marca_modelo_crear.getText());
				
				try {
					SimpleDateFormat out = new SimpleDateFormat("yyyy-MM-dd");
					SimpleDateFormat in = new SimpleDateFormat("dd/MM/yyyy");
					Date date = in.parse(fecha_compra_crear.getText());
					nuevo_camion.setFecha_compra(out.parse(out.format(date)));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				Gestor_Camion.alta_camion(nuevo_camion);
				
				
			}
		});
		btnNewButton_1.setBounds(368, 242, 120, 23);
		crear_camion.add(btnNewButton_1);
		
		patente_crear = new JFormattedTextField();
		patente_crear.setBounds(113, 28, 120, 20);
		crear_camion.add(patente_crear);
		patente_crear.setColumns(10);
			MaskFormatter patente;
			try {
				patente = new MaskFormatter("*******");
				patente.install(patente_crear);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		JLabel lblNewLabel = new JLabel("Patente");
		lblNewLabel.setBounds(13, 31, 58, 14);
		crear_camion.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Km recorridos");
		lblNewLabel_1.setBounds(13, 62, 90, 14);
		crear_camion.add(lblNewLabel_1);
		
		km_recorridos_crear = new JTextField();
		km_recorridos_crear.setColumns(10);
		km_recorridos_crear.setBounds(113, 59, 120, 20);
		crear_camion.add(km_recorridos_crear);
		
		JLabel lblNewLabel_2 = new JLabel("Marca y modelo");
		lblNewLabel_2.setBounds(13, 93, 90, 14);
		crear_camion.add(lblNewLabel_2);
		
		marca_modelo_crear = new JTextField();
		marca_modelo_crear.setColumns(10);
		marca_modelo_crear.setBounds(113, 90, 150, 20);
		crear_camion.add(marca_modelo_crear);
		
		JLabel lblCostoPorHora = new JLabel("Costo por hora");
		lblCostoPorHora.setBounds(298, 28, 90, 14);
		crear_camion.add(lblCostoPorHora);
		
		JLabel lblNewLabel_1_1 = new JLabel("Costo por km.");
		lblNewLabel_1_1.setBounds(298, 59, 90, 14);
		crear_camion.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("Fecha de compra");
		lblNewLabel_2_1.setBounds(298, 90, 90, 14);
		crear_camion.add(lblNewLabel_2_1);
		
		fecha_compra_crear = new JFormattedTextField();
		fecha_compra_crear.setColumns(10);
		fecha_compra_crear.setBounds(398, 87, 89, 20);
		crear_camion.add(fecha_compra_crear);
		MaskFormatter dateMask;
		try {
			dateMask = new MaskFormatter("##/##/####");
			dateMask.install(fecha_compra_crear);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
        
		
		
		
		costo_por_km_crear = new JTextField();
		costo_por_km_crear.setColumns(10);
		costo_por_km_crear.setBounds(398, 56, 89, 20);
		crear_camion.add(costo_por_km_crear);
		
		costo_por_hora_crear = new JTextField();
		costo_por_hora_crear.setColumns(10);
		costo_por_hora_crear.setBounds(398, 25, 89, 20);
		crear_camion.add(costo_por_hora_crear);
		
		JPanel menu_planta_ruta = new JPanel();
		contentPane.add(menu_planta_ruta, MENUPLANTA);
		menu_planta_ruta.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), "Generar Plantas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 567, 77);
		menu_planta_ruta.add(panel);
		panel.setLayout(null);
		
		nombre_nueva_planta = new JTextField();
		nombre_nueva_planta.setBounds(25, 33, 152, 20);
		panel.add(nombre_nueva_planta);
		nombre_nueva_planta.setColumns(10);
		
		JButton btnNewButton_5 = new JButton("Guardar Planta");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Planta p = new Planta();
				p.setNombre(nombre_nueva_planta.getText());
				Gestor_Planta.altaPlanta(p);
			}
		});
		btnNewButton_5.setBounds(187, 32, 142, 23);
		panel.add(btnNewButton_5);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), "Generar Rutas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 99, 567, 165);
		menu_planta_ruta.add(panel_1);
		panel_1.setLayout(null);
		
		
		JComboBox<String> plantaorigen = new JComboBox<String>();
		plantaorigen.setBounds(10, 39, 149, 20);
		panel_1.add(plantaorigen);
		
		JComboBox<String> plantadestino = new JComboBox<String>();
		plantadestino.setBounds(408, 39, 149, 20);
		panel_1.add(plantadestino);
		//plantaorigen.setModel(new DefaultComboBoxModel<>());
		ArrayList<Planta> plantas = Gestor_Planta.getPlantas();
		for(int i = 0 ; i < plantas.size() ; i++) {
			plantaorigen.addItem(plantas.get(i).getNombre());
		}
		//plantaorigen.setModel(new DefaultComboBoxModel<>());
		for(int i = 0 ; i < plantas.size() ; i++) {
			plantadestino.addItem(plantas.get(i).getNombre());
		}
		
	
		gen_ruta_distancia = new JTextField();
		gen_ruta_distancia.setBounds(282, 39, 86, 20);
		panel_1.add(gen_ruta_distancia);
		gen_ruta_distancia.setColumns(10);
		
		gen_ruta_duracion = new JTextField();
		gen_ruta_duracion.setBounds(282, 70, 86, 20);
		panel_1.add(gen_ruta_duracion);
		gen_ruta_duracion.setColumns(10);
		
		gen_ruta_pesomax = new JTextField();
		gen_ruta_pesomax.setBounds(282, 101, 86, 20);
		panel_1.add(gen_ruta_pesomax);
		gen_ruta_pesomax.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Duraci\u00F3n [Hs]");
		lblNewLabel_5.setBounds(186, 73, 86, 14);
		panel_1.add(lblNewLabel_5);
		lblNewLabel_5.setEnabled(false);
		
		JLabel lblNewLabel_6 = new JLabel("Peso Max [Kg]");
		lblNewLabel_6.setBounds(186, 104, 86, 14);
		panel_1.add(lblNewLabel_6);
		lblNewLabel_6.setEnabled(false);
		
		JLabel lblNewLabel_4 = new JLabel("Distancia [Km]");
		lblNewLabel_4.setBounds(186, 42, 86, 14);
		panel_1.add(lblNewLabel_4);
		lblNewLabel_4.setEnabled(false);
		
				

				
				JLabel lblNewLabel_7 = new JLabel("Planta Origen");
				lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel_7.setBounds(10, 21, 149, 14);
				panel_1.add(lblNewLabel_7);
				
				JLabel lblNewLabel_7_1 = new JLabel("Planta Destino");
				lblNewLabel_7_1.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel_7_1.setBounds(408, 21, 149, 14);
				panel_1.add(lblNewLabel_7_1);
				
				JButton btnNewButton_6 = new JButton("Guardar Ruta");
				btnNewButton_6.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Ruta r = new Ruta();
						r.setDistancia_km(Integer.valueOf(gen_ruta_distancia.getText()));
						r.setDuracion_horas(Double.valueOf(gen_ruta_duracion.getText()));
						r.setPeso_max_kg(Double.valueOf(gen_ruta_pesomax.getText()));
						Planta plantao = new Planta();
						Planta plantad = new Planta();
						plantao.setId(Gestor_Planta.getID((String) plantaorigen.getSelectedItem()));
						plantad.setId(Gestor_Planta.getID((String) plantadestino.getSelectedItem()));
						r.setPlanta_origen(plantao);
						r.setPlanta_destino(plantad);
						Gestor_Ruta.alta_ruta(r);
					
					}
				});
				btnNewButton_6.setBounds(229, 132, 112, 23);
				panel_1.add(btnNewButton_6);

	}
}
