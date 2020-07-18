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
import Gestores.Gestor_Camion;

import java.awt.CardLayout;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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

public class Zaisser extends JFrame {
	private final static String MENUCAMION="name_5541826395100";
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
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 544, 386);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		JPanel menu_principal = new JPanel();
		contentPane.add(menu_principal, "name_5500757628000");
		menu_principal.setLayout(null);
		
		JButton btnNewButton = new JButton("Men\u00FA Cami\u00F3n");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				((CardLayout)getContentPane().getLayout()).show(getContentPane(), "MENUCAMION");
				//menu_principal.hide();
			}
		});
		btnNewButton.setBounds(10, 25, 126, 23);
		menu_principal.add(btnNewButton);
		
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
		btnNewButton_3.setBounds(410, 264, 89, 23);
		modificar_eliminar_camion.add(btnNewButton_3);
		
		JButton eliminar_camion_btn = new JButton("Eliminar");
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
		eliminar_camion_btn.setBounds(311, 264, 89, 23);
		modificar_eliminar_camion.add(eliminar_camion_btn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 137, 489, 123);
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
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, "name_5544200076900");
	}
}
