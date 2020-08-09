package GUI;
 
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
import Dominio.Insumo;
import Dominio.Insumo_general;
import Dominio.Insumo_liquido;
import Dominio.Planta;
import Dominio.Ruta;
import Dominio.Stock;
import Gestores.Gestor_Camion;
import Gestores.Gestor_Insumo;
import Gestores.Gestor_Planta;
import Gestores.Gestor_Ruta;
import Gestores.Gestor_Stock;
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
import javax.swing.JRadioButton;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Zaisser extends JFrame {
	private final static String MENUCAMION="name_5541826395100";
	private final static String MENUPLANTA="name_5544200076900";
	private final static String MENUINSUMO="name_165698070879700";
	private final static String MENUPRINCIPAL="name_5500757628000";
	private final static String MENUSTOCK="name_74420176734100";
	
	
	
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
	private JTextField descripcion_mod;
	private JTextField um_mod;
	private JTextField costo_mod;
	private JTextField peso_mod;
	private JTextField densidad_mod;
	private JTextField id_mod;
	private JTable table_buscar_insumo;
	private JTextField costo_crear;
	private JTextField descripcion_crear;
	private JTextField um_crear;
	private JTextField id_crear;
	private JTextField densidad_crear;
	private JTextField peso_crear;
	private JTextField textField;
	private JTextField cant_stock;
	private JTextField punto_pedido_stock;
	private JTable tabla_stock_pp;
	private JTextField planta_pp;
	private JTextField insumo_pp;

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
		contentPane.add(menu_principal, MENUPRINCIPAL);
		menu_principal.setLayout(null);
		
		JButton btnNewButton = new JButton("Administrar Camiones");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				((CardLayout)getContentPane().getLayout()).show(getContentPane(), MENUCAMION);
				//menu_principal.hide();
			}
		});
		btnNewButton.setBounds(10, 25, 142, 46);
		menu_principal.add(btnNewButton);
		
		JButton btnNewButton_4 = new JButton("Administrar Plantas");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout)getContentPane().getLayout()).show(getContentPane(), MENUPLANTA);
			}
		});
		btnNewButton_4.setBounds(162, 25, 142, 46);
		menu_principal.add(btnNewButton_4);
		
		JButton btnNewButton_7 = new JButton("Administrar Insumos");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				((CardLayout)getContentPane().getLayout()).show(getContentPane(), MENUINSUMO);
			}
		});
		btnNewButton_7.setBounds(314, 25, 142, 46);
		menu_principal.add(btnNewButton_7);
		
		JButton btnNewButton_16 = new JButton("Administrar Stock");
		btnNewButton_16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout)getContentPane().getLayout()).show(getContentPane(), MENUSTOCK);
			}
		});
		btnNewButton_16.setBounds(10, 82, 142, 46);
		menu_principal.add(btnNewButton_16);
		
		JPanel menu_camion = new JPanel();
		contentPane.add(menu_camion, MENUCAMION);
		menu_camion.setLayout(null);
		
		JFormattedTextField patente_mod = new JFormattedTextField();
		patente_mod.setColumns(10);
		patente_mod.setBounds(110, 14, 120, 20);
				
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 633, 326);
		menu_camion.add(tabbedPane);
		
		JPanel modificar_eliminar_camion = new JPanel();
		tabbedPane.addTab("Modificar/Eliminar", null, modificar_eliminar_camion, null);
		modificar_eliminar_camion.setLayout(null);
		
		JPanel menu_insumo = new JPanel();
		contentPane.add(menu_insumo, MENUINSUMO);
		menu_insumo.setLayout(null);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(0, 0, 587, 347);
		menu_insumo.add(tabbedPane_1);
		
		JPanel modificar_eliminar = new JPanel();
		tabbedPane_1.addTab("Modificar/Eliminar", null, modificar_eliminar, null);
		modificar_eliminar.setLayout(null);
		
		descripcion_mod = new JTextField();
		descripcion_mod.setBounds(102, 21, 120, 20);
		modificar_eliminar.add(descripcion_mod);
		descripcion_mod.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Descripci\u00F3n");
		lblNewLabel_8.setBounds(10, 24, 54, 14);
		modificar_eliminar.add(lblNewLabel_8);
		
		um_mod = new JTextField();
		um_mod.setBounds(102, 52, 86, 20);
		um_mod.setColumns(10);
		modificar_eliminar.add(um_mod);
		
		costo_mod = new JTextField();
		costo_mod.setBounds(102, 83, 86, 20);
		costo_mod.setColumns(10);
		modificar_eliminar.add(costo_mod);
		
		peso_mod = new JTextField();
		peso_mod.setBounds(348, 21, 86, 20);
		peso_mod.setColumns(10);
		modificar_eliminar.add(peso_mod);
		
		densidad_mod = new JTextField();
		densidad_mod.setBounds(348, 52, 86, 20);
		densidad_mod.setColumns(10);
		modificar_eliminar.add(densidad_mod);
		
		JLabel lblNewLabel_9 = new JLabel("Unidad de medida");
		lblNewLabel_9.setBounds(10, 55, 100, 14);
		modificar_eliminar.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("Costo");
		lblNewLabel_10.setBounds(10, 86, 46, 14);
		modificar_eliminar.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("Peso");
		lblNewLabel_11.setBounds(256, 24, 46, 14);
		modificar_eliminar.add(lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel("Densidad");
		lblNewLabel_12.setBounds(256, 55, 54, 14);
		modificar_eliminar.add(lblNewLabel_12);
		
		id_mod = new JTextField();
		id_mod.setBounds(348, 83, 86, 20);
		modificar_eliminar.add(id_mod);
		id_mod.setColumns(10);
		
		JLabel lblNewLabel_13 = new JLabel("Identificador");
		lblNewLabel_13.setBounds(256, 86, 82, 14);
		modificar_eliminar.add(lblNewLabel_13);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 114, 562, 163);
		modificar_eliminar.add(scrollPane_1);
		
		table_buscar_insumo = new JTable();
		table_buscar_insumo.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Descripción", "Costo", "Unidad de medida", "Peso (IG)", "Densidad (IL)", "Stock total"
			}
		));
		scrollPane_1.setViewportView(table_buscar_insumo);
		
		JButton btnNewButton_8 = new JButton("Buscar");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Insumo buscar_insumo = new Insumo();
				if(!costo_mod.getText().isEmpty()) buscar_insumo.setCosto(Double.valueOf(costo_mod.getText()));
				if(!descripcion_mod.getText().isEmpty()) buscar_insumo.setDescripcion(descripcion_mod.getText());
				if(!id_mod.getText().isEmpty()) buscar_insumo.setId(Integer.valueOf(id_mod.getText()));
				if(!um_mod.getText().isEmpty()) buscar_insumo.setUnidad_medida(um_mod.getText());

				ArrayList<Insumo> Auxinsumo = Gestor_Insumo.getInsumo(buscar_insumo);
				if(Auxinsumo.isEmpty())	JOptionPane.showMessageDialog(null, "No se encontraron insumos", "Sin resultados", JOptionPane.INFORMATION_MESSAGE);
				else {	
					((DefaultTableModel)table_buscar_insumo.getModel()).setRowCount(0);
				for (Insumo i : Auxinsumo) {
						Object[] auxRow = new Object[7];
						auxRow[0] = i.getId();
						auxRow[1] = i.getDescripcion();
						auxRow[2] = i.getCosto();
						auxRow[3] = i.getUnidad_medida();
						if(i instanceof Insumo_general) {
							auxRow[4]=((Insumo_general) i).getPeso();
						}
						if(i instanceof Insumo_liquido) {
							auxRow[5]=((Insumo_liquido) i).getDensidad();
						}
						auxRow[6] = Gestor_Stock.stock_total(i.getId()); 
						((DefaultTableModel)table_buscar_insumo.getModel()).addRow(auxRow);
					}
				}
			}
		});
		btnNewButton_8.setBounds(483, 51, 89, 23);
		modificar_eliminar.add(btnNewButton_8);
		
		JButton btnNewButton_9 = new JButton("Eliminar");
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Insumo insumo = new Insumo();
				if(table_buscar_insumo.getSelectedRow()>=0) {
				insumo.setId((Integer) table_buscar_insumo.getModel().getValueAt(table_buscar_insumo.getSelectedRow(), 0));
				Gestor_Insumo.eliminar_insumo(insumo);
				((DefaultTableModel)table_buscar_insumo.getModel()).setRowCount(0);
				}
			}
		});
		btnNewButton_9.setBounds(483, 288, 89, 23);
		modificar_eliminar.add(btnNewButton_9);
		
		JButton btnNewButton_11 = new JButton("Atr\u00E1s");
		btnNewButton_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout)getContentPane().getLayout()).show(getContentPane(), MENUPRINCIPAL);
			}
		});
		btnNewButton_11.setBounds(10, 288, 89, 23);
		modificar_eliminar.add(btnNewButton_11);
		
		JButton btnNewButton_14 = new JButton("Modificar");
		btnNewButton_14.setBounds(483, 82, 89, 23);
		modificar_eliminar.add(btnNewButton_14);
		
		JButton btnNewButton_15 = new JButton("Seleccionar");
		btnNewButton_15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Insumo insumo = new Insumo();
				if(table_buscar_insumo.getSelectedRow()>=0) {
				insumo.setId((Integer) table_buscar_insumo.getModel().getValueAt(table_buscar_insumo.getSelectedRow(), 0));
				Insumo aux = Gestor_Insumo.getInsumo(insumo).get(1);
				um_mod.setText(aux.getUnidad_medida());
				id_mod.setText(String.valueOf(aux.getId()));
				descripcion_mod.setText(aux.getDescripcion());
				costo_mod.setText(aux.getCosto().toString());
				if(aux instanceof Insumo_liquido) densidad_mod.setText(((Insumo_liquido) aux).getDensidad().toString());
				if(aux instanceof Insumo_general) peso_mod.setText(((Insumo_general) aux).getPeso().toString());
				//peso_mod.setText(t);
				}
			}
		});
		btnNewButton_15.setBounds(384, 288, 89, 23);
		modificar_eliminar.add(btnNewButton_15);
		
		textField = new JTextField();
		textField.setBounds(485, 21, 87, 20);
		modificar_eliminar.add(textField);
		textField.setColumns(10);
		
		JPanel crear = new JPanel();
		tabbedPane_1.addTab("Crear", null, crear, null);
		crear.setLayout(null);
		
		costo_crear = new JTextField();
		costo_crear.setColumns(10);
		costo_crear.setBounds(102, 84, 86, 20);
		crear.add(costo_crear);
		
		JLabel lblNewLabel_10_1 = new JLabel("Costo");
		lblNewLabel_10_1.setBounds(10, 87, 46, 14);
		crear.add(lblNewLabel_10_1);
		
		JLabel lblNewLabel_9_1 = new JLabel("Unidad de medida");
		lblNewLabel_9_1.setBounds(10, 56, 100, 14);
		crear.add(lblNewLabel_9_1);
		
		JLabel lblNewLabel_8_1 = new JLabel("Descripci\u00F3n");
		lblNewLabel_8_1.setBounds(10, 25, 54, 14);
		crear.add(lblNewLabel_8_1);
		
		descripcion_crear = new JTextField();
		descripcion_crear.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				id_crear.setText(Gestor_Insumo.next_id().toString());
			}
		});
		descripcion_crear.setColumns(10);
		descripcion_crear.setBounds(102, 22, 120, 20);
		crear.add(descripcion_crear);
		
		um_crear = new JTextField();
		um_crear.setColumns(10);
		um_crear.setBounds(102, 53, 86, 20);
		crear.add(um_crear);
		
		JLabel lblNewLabel_11_1 = new JLabel("Peso");
		lblNewLabel_11_1.setBounds(256, 25, 46, 14);
		crear.add(lblNewLabel_11_1);
		
		JLabel lblNewLabel_12_1 = new JLabel("Densidad");
		lblNewLabel_12_1.setBounds(256, 56, 54, 14);
		crear.add(lblNewLabel_12_1);
		
		JLabel lblNewLabel_13_1 = new JLabel("Identificador");
		lblNewLabel_13_1.setBounds(256, 87, 82, 14);
		crear.add(lblNewLabel_13_1);
		
		id_crear = new JTextField();
		id_crear.setEnabled(false);
		id_crear.setEditable(false);
		id_crear.setColumns(10);
		id_crear.setBounds(348, 84, 86, 20);
		crear.add(id_crear);
		
		densidad_crear = new JTextField();
		densidad_crear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				densidad_crear.setEnabled(true);
				peso_crear.setEnabled(false);
				peso_crear.setText("");
			}
		});
		densidad_crear.setColumns(10);
		densidad_crear.setBounds(348, 53, 86, 20);
		crear.add(densidad_crear);
		
		peso_crear = new JTextField();
		peso_crear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				peso_crear.setEnabled(true);
				densidad_crear.setEnabled(false);
				densidad_crear.setText("");
			}
		});
		peso_crear.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				densidad_crear.setEnabled(false);
			}
		});
		peso_crear.setColumns(10);
		peso_crear.setBounds(348, 22, 86, 20);
		crear.add(peso_crear);
		
		JButton btnNewButton_10 = new JButton("New button");
		btnNewButton_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!peso_crear.getText().isEmpty()) {
					Insumo_general insumo=new Insumo_general();
					insumo.setCosto(Double.valueOf(costo_crear.getText()));
					insumo.setDescripcion(descripcion_crear.getText());
					insumo.setId(Integer.valueOf(id_crear.getText()));
					insumo.setPeso(Double.valueOf(peso_crear.getText()));
					insumo.setUnidad_medida(um_crear.getText());
					Gestor_Insumo.alta_insumo(insumo);
				}
				else {
					Insumo_liquido insumo=new Insumo_liquido();
					insumo.setCosto(Double.valueOf(costo_crear.getText()));
					insumo.setDescripcion(descripcion_crear.getText());
					insumo.setId(Integer.valueOf(id_crear.getText()));
					insumo.setDensidad(Double.valueOf(densidad_crear.getText()));
					insumo.setUnidad_medida(um_crear.getText());
					Gestor_Insumo.alta_insumo(insumo);
				}
				

			}
		});
		btnNewButton_10.setBounds(467, 52, 89, 23);
		crear.add(btnNewButton_10);
		
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
			
			JLabel lblNewLabel_1_1_1 = new JLabel("Costo por km");
			lblNewLabel_1_1_1.setBounds(295, 45, 90, 14);
			modificar_eliminar_camion.add(lblNewLabel_1_1_1);
			
			JLabel lblNewLabel_2_1_1 = new JLabel("Fecha de compra");
			lblNewLabel_2_1_1.setBounds(295, 76, 90, 14);
			modificar_eliminar_camion.add(lblNewLabel_2_1_1);
			
			marca_modelo_mod = new JTextField();
			marca_modelo_mod.setColumns(10);
			marca_modelo_mod.setBounds(110, 76, 120, 20);
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
			
			JButton btnNewButton_2 = new JButton("Limpiar");
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					patente_mod.setText("");
					patente_mod.setEnabled(true);
					costo_por_hora_mod.setText("");
					costo_por_km_mod.setText("");
					marca_modelo_mod.setText("");
					km_recorridos_mod.setText("");
					fecha_compra_mod.setText("");
				}
			});
			btnNewButton_2.setBounds(494, 42, 77, 20);
			modificar_eliminar_camion.add(btnNewButton_2);
			
			JButton btnNewButton_12 = new JButton("Atr\u00E1s");
			btnNewButton_12.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					((CardLayout)getContentPane().getLayout()).show(getContentPane(), MENUPRINCIPAL);
				}
			});
			btnNewButton_12.setBounds(10, 264, 89, 23);
			modificar_eliminar_camion.add(btnNewButton_12);
			
		
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
		btnNewButton_1.setBounds(395, 104, 120, 23);
		crear_camion.add(btnNewButton_1);
		
		patente_crear = new JFormattedTextField();
		patente_crear.setBounds(110, 14, 120, 20);
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
		lblNewLabel.setBounds(10, 17, 58, 14);
		crear_camion.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Km recorridos");
		lblNewLabel_1.setBounds(10, 48, 90, 14);
		crear_camion.add(lblNewLabel_1);
		
		km_recorridos_crear = new JTextField();
		km_recorridos_crear.setColumns(10);
		km_recorridos_crear.setBounds(110, 45, 120, 20);
		crear_camion.add(km_recorridos_crear);
		
		JLabel lblNewLabel_2 = new JLabel("Marca y modelo");
		lblNewLabel_2.setBounds(10, 79, 90, 14);
		crear_camion.add(lblNewLabel_2);
		
		marca_modelo_crear = new JTextField();
		marca_modelo_crear.setColumns(10);
		marca_modelo_crear.setBounds(110, 76, 120, 20);
		crear_camion.add(marca_modelo_crear);
		
		JLabel lblCostoPorHora = new JLabel("Costo por hora");
		lblCostoPorHora.setBounds(295, 14, 90, 14);
		crear_camion.add(lblCostoPorHora);
		
		JLabel lblNewLabel_1_1 = new JLabel("Costo por km.");
		lblNewLabel_1_1.setBounds(295, 45, 90, 14);
		crear_camion.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("Fecha de compra");
		lblNewLabel_2_1.setBounds(295, 76, 90, 14);
		crear_camion.add(lblNewLabel_2_1);
		
		fecha_compra_crear = new JFormattedTextField();
		fecha_compra_crear.setColumns(10);
		fecha_compra_crear.setBounds(395, 73, 89, 20);
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
		costo_por_km_crear.setBounds(395, 42, 89, 20);
		crear_camion.add(costo_por_km_crear);
		
		costo_por_hora_crear = new JTextField();
		costo_por_hora_crear.setColumns(10);
		costo_por_hora_crear.setBounds(395, 11, 89, 20);
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
				
				JButton btnNewButton_13 = new JButton("Atr\u00E1s");
				btnNewButton_13.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						((CardLayout)getContentPane().getLayout()).show(getContentPane(), MENUPRINCIPAL);
					}
				});
				btnNewButton_13.setBounds(10, 275, 89, 23);
				menu_planta_ruta.add(btnNewButton_13);
				
				JPanel menu_stock = new JPanel();
				contentPane.add(menu_stock, MENUSTOCK);
				menu_stock.setLayout(null);

				JTabbedPane tabbedPane_2 = new JTabbedPane(JTabbedPane.TOP);
				tabbedPane_2.setBounds(0, 0, 577, 310);
				menu_stock.add(tabbedPane_2);
				
				JPanel agregar_stock = new JPanel();
				tabbedPane_2.addTab("Agregar stock", null, agregar_stock, null);
				agregar_stock.setLayout(null);
				
				JLabel Planta = new JLabel("Planta");
				Planta.setBounds(25, 14, 46, 14);
				agregar_stock.add(Planta);
				
				JLabel lblNewLabel_15 = new JLabel("Cantidad");
				lblNewLabel_15.setBounds(25, 58, 46, 14);
				agregar_stock.add(lblNewLabel_15);
				
				cant_stock = new JTextField();
				cant_stock.setBounds(87, 55, 121, 20);
				agregar_stock.add(cant_stock);
				cant_stock.setColumns(10);
				
				JComboBox<String> planta_stock = new JComboBox<String>();
				planta_stock.setBounds(87, 11, 121, 20);
				agregar_stock.add(planta_stock);
				
				JLabel lblNewLabel_14 = new JLabel("Insumo");
				lblNewLabel_14.setBounds(226, 14, 46, 14);
				agregar_stock.add(lblNewLabel_14);
				
				JLabel lblNewLabel_16 = new JLabel("Punto de pedido");
				lblNewLabel_16.setBounds(226, 58, 78, 14);
				agregar_stock.add(lblNewLabel_16);
				
				punto_pedido_stock = new JTextField();
				punto_pedido_stock.setBounds(314, 55, 121, 20);
				agregar_stock.add(punto_pedido_stock);
				punto_pedido_stock.setColumns(10);
				
				JComboBox<String> insumo_stock = new JComboBox<String>();
				for(int i = 0 ; i < plantas.size() ; i++) {
					planta_stock.addItem(plantas.get(i).getNombre());
				}
				
				
				ArrayList<Insumo> insumos = Gestor_Insumo.getInsumo();
				for (int i = 0; i < insumos.size(); i++) {
					insumo_stock.addItem(insumos.get(i).getDescripcion());
				}
				
				insumo_stock.setBounds(314, 11, 121, 20);
				agregar_stock.add(insumo_stock);
				
				JButton btnNewButton_18 = new JButton("Atr\u00E1s");
				btnNewButton_18.setBounds(25, 150, 89, 23);
				agregar_stock.add(btnNewButton_18);
				
				JButton btnNewButton_17 = new JButton("Guardar");
				btnNewButton_17.setBounds(443, 137, 89, 23);
				agregar_stock.add(btnNewButton_17);
				btnNewButton_17.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Stock s = new Stock();
						s.setPlanta(Gestor_Planta.getID(planta_stock.getSelectedItem().toString()));
						s.setInsumo(Gestor_Insumo.getID(insumo_stock.getSelectedItem().toString()));
						s.setCantidad(Integer.valueOf(cant_stock.getText()));
						s.setPunto_pedido(Integer.valueOf(punto_pedido_stock.getText()));
						Gestor_Stock.guardarStock(s);
					}
				});
				btnNewButton_18.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						((CardLayout)getContentPane().getLayout()).show(getContentPane(), MENUPRINCIPAL);
					}
				});
				
				JPanel punto_pedido = new JPanel();
				tabbedPane_2.addTab("Stock en punto de pedido", null, punto_pedido, null);
				punto_pedido.setLayout(null);
				
				JScrollPane scrollPane_2 = new JScrollPane();
				scrollPane_2.setBounds(10, 41, 552, 230);
				punto_pedido.add(scrollPane_2);
				
				tabla_stock_pp = new JTable();
				tabla_stock_pp.setModel(new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
						"Planta", "Insumo", "Cantidad", "Punto pedido", "Stock total"
					}
				));
				scrollPane_2.setViewportView(tabla_stock_pp);
				
				JButton btnNewButton_19 = new JButton("Buscar");
				btnNewButton_19.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						ArrayList<Stock> stock_aux = Gestor_Stock.getStocks(planta_pp.getText(), insumo_pp.getText());
						if(stock_aux.isEmpty())	JOptionPane.showMessageDialog(null, "No se encontraron stocks", "Sin resultados", JOptionPane.INFORMATION_MESSAGE);
						else {	
							((DefaultTableModel)tabla_stock_pp.getModel()).setRowCount(0);
						for (Stock i : stock_aux) {
								Object[] auxRow = new Object[5];
								auxRow[0] = Gestor_Planta.getNombre(i.getPlanta());
								auxRow[1] = Gestor_Insumo.getNombre(i.getInsumo());
								auxRow[2] = i.getCantidad();
								auxRow[3] = i.getPunto_pedido();
								auxRow[4] = Gestor_Stock.stock_total(i.getInsumo()); 
								((DefaultTableModel)tabla_stock_pp.getModel()).addRow(auxRow);
							}
						}
					}
					}
				);
				btnNewButton_19.setBounds(473, 7, 89, 23);
				punto_pedido.add(btnNewButton_19);
				
				planta_pp = new JTextField();
				planta_pp.setBounds(83, 8, 111, 20);
				punto_pedido.add(planta_pp);
				planta_pp.setColumns(10);
				
				insumo_pp = new JTextField();
				insumo_pp.setBounds(280, 8, 111, 20);
				punto_pedido.add(insumo_pp);
				insumo_pp.setColumns(10);
				
				JLabel lblNewLabel_17 = new JLabel("Planta");
				lblNewLabel_17.setBounds(10, 11, 63, 14);
				punto_pedido.add(lblNewLabel_17);
				
				JLabel lblNewLabel_18 = new JLabel("Insumo");
				lblNewLabel_18.setBounds(204, 11, 46, 14);
				punto_pedido.add(lblNewLabel_18);
				


	}
}
