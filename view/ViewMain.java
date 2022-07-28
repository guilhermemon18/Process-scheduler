package view;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.ControllerMain;
import utility.EnumAlgoritmo;

public class ViewMain extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private ControllerMain ctrl;


	public void exec() {
		setVisible(true);
	}
	

	public ViewMain(ControllerMain ctrl) {
		setResizable(false);
		this.ctrl = ctrl;
		setTitle("Janela Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 790, 501);
		setLocationRelativeTo ( null );
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{200, 189, 189, 194, 0};
		gbl_contentPane.rowHeights = new int[]{61, 61, 61, 61, 61, 61, 66, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblProcessos = new JLabel("n\u00BA de processos:");
		lblProcessos.setHorizontalAlignment(SwingConstants.CENTER);
		lblProcessos.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblProcessos = new GridBagConstraints();
		gbc_lblProcessos.fill = GridBagConstraints.BOTH;
		gbc_lblProcessos.insets = new Insets(0, 0, 5, 5);
		gbc_lblProcessos.gridx = 0;
		gbc_lblProcessos.gridy = 0;
		getContentPane().add(lblProcessos, gbc_lblProcessos);
		
		JSpinner spinnerProcessos = new JSpinner();
		spinnerProcessos.setModel(new SpinnerNumberModel(new Integer(5), new Integer(1), null, new Integer(1)));
		GridBagConstraints gbc_spinnerProcessos = new GridBagConstraints();
		gbc_spinnerProcessos.fill = GridBagConstraints.BOTH;
		gbc_spinnerProcessos.insets = new Insets(0, 0, 5, 5);
		gbc_spinnerProcessos.gridx = 1;
		gbc_spinnerProcessos.gridy = 0;
		contentPane.add(spinnerProcessos, gbc_spinnerProcessos);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		GridBagConstraints gbc_horizontalGlue = new GridBagConstraints();
		gbc_horizontalGlue.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalGlue.gridx = 2;
		gbc_horizontalGlue.gridy = 0;
		contentPane.add(horizontalGlue, gbc_horizontalGlue);
		
		JButton btnVisualizarProcessos = new JButton("Visualizar Processos");
		
		GridBagConstraints gbc_btnVisualizarProcessos = new GridBagConstraints();
		gbc_btnVisualizarProcessos.insets = new Insets(0, 0, 5, 0);
		gbc_btnVisualizarProcessos.fill = GridBagConstraints.BOTH;
		gbc_btnVisualizarProcessos.gridx = 3;
		gbc_btnVisualizarProcessos.gridy = 0;
		getContentPane().add(btnVisualizarProcessos, gbc_btnVisualizarProcessos);
		
		JLabel lblAlgoritmo = new JLabel("Algoritmo:");
		lblAlgoritmo.setHorizontalAlignment(SwingConstants.LEFT);
		lblAlgoritmo.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblAlgoritmo = new GridBagConstraints();
		gbc_lblAlgoritmo.fill = GridBagConstraints.BOTH;
		gbc_lblAlgoritmo.insets = new Insets(0, 0, 5, 5);
		gbc_lblAlgoritmo.gridx = 0;
		gbc_lblAlgoritmo.gridy = 1;
		getContentPane().add(lblAlgoritmo, gbc_lblAlgoritmo);
		
		JRadioButton rdbtnSJF = new JRadioButton("Shortest Job First (SJF)");
		rdbtnSJF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setAlgoritmo(EnumAlgoritmo.SJF);
			}
		});
		buttonGroup.add(rdbtnSJF);
		GridBagConstraints gbc_rdbtnSJF = new GridBagConstraints();
		gbc_rdbtnSJF.fill = GridBagConstraints.BOTH;
		gbc_rdbtnSJF.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnSJF.gridx = 0;
		gbc_rdbtnSJF.gridy = 2;
		getContentPane().add(rdbtnSJF, gbc_rdbtnSJF);
		
		JRadioButton rdbtnSRTN = new JRadioButton("Shortest Remaining Time Next (SRTN)");
		rdbtnSRTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setAlgoritmo(EnumAlgoritmo.SRTN);
			}
		});
		
		JLabel lblQuantum = new JLabel("Quantum:");
		lblQuantum.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblQuantum = new GridBagConstraints();
		gbc_lblQuantum.insets = new Insets(0, 0, 5, 5);
		gbc_lblQuantum.gridx = 2;
		gbc_lblQuantum.gridy = 2;
		contentPane.add(lblQuantum, gbc_lblQuantum);
		
		JSpinner spinnerQuantum = new JSpinner();
		btnVisualizarProcessos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ctrl.setQuantum((Integer)spinnerQuantum.getValue());
				ctrl.gerarProcessos((Integer) spinnerProcessos.getValue());
			}
		});
		spinnerQuantum.setModel(new SpinnerNumberModel(30, 0, 50, 1));
		GridBagConstraints gbc_spinnerQuantum = new GridBagConstraints();
		gbc_spinnerQuantum.fill = GridBagConstraints.BOTH;
		gbc_spinnerQuantum.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerQuantum.gridx = 3;
		gbc_spinnerQuantum.gridy = 2;
		contentPane.add(spinnerQuantum, gbc_spinnerQuantum);
		rdbtnSRTN.setHorizontalAlignment(SwingConstants.LEFT);
		buttonGroup.add(rdbtnSRTN);
		GridBagConstraints gbc_rdbtnSRTN = new GridBagConstraints();
		gbc_rdbtnSRTN.gridwidth = 2;
		gbc_rdbtnSRTN.fill = GridBagConstraints.BOTH;
		gbc_rdbtnSRTN.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnSRTN.gridx = 0;
		gbc_rdbtnSRTN.gridy = 3;
		getContentPane().add(rdbtnSRTN, gbc_rdbtnSRTN);
		
		JRadioButton rdbtnRoundRobin = new JRadioButton("Round-Robin");
		rdbtnRoundRobin.setSelected(true);
		setAlgoritmo(EnumAlgoritmo.RR);//padrao
		rdbtnRoundRobin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setAlgoritmo(EnumAlgoritmo.RR);
			}
		});
		buttonGroup.add(rdbtnRoundRobin);
		GridBagConstraints gbc_rdbtnRoundRobin = new GridBagConstraints();
		gbc_rdbtnRoundRobin.fill = GridBagConstraints.BOTH;
		gbc_rdbtnRoundRobin.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnRoundRobin.gridx = 0;
		gbc_rdbtnRoundRobin.gridy = 4;
		getContentPane().add(rdbtnRoundRobin, gbc_rdbtnRoundRobin);
		
		JRadioButton rdbtnPrioridade = new JRadioButton("Prioridade");
		rdbtnPrioridade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setAlgoritmo(EnumAlgoritmo.PRIORIDADE);
			}
		});
		buttonGroup.add(rdbtnPrioridade);
		GridBagConstraints gbc_rdbtnPrioridade = new GridBagConstraints();
		gbc_rdbtnPrioridade.fill = GridBagConstraints.BOTH;
		gbc_rdbtnPrioridade.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnPrioridade.gridx = 0;
		gbc_rdbtnPrioridade.gridy = 5;
		getContentPane().add(rdbtnPrioridade, gbc_rdbtnPrioridade);
		
		JRadioButton rdbtnMF = new JRadioButton("M\u00FAltiplas Filas");
		rdbtnMF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setAlgoritmo(EnumAlgoritmo.MF);
			}
		});
		buttonGroup.add(rdbtnMF);
		GridBagConstraints gbc_rdbtnMF = new GridBagConstraints();
		gbc_rdbtnMF.fill = GridBagConstraints.BOTH;
		gbc_rdbtnMF.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnMF.gridx = 0;
		gbc_rdbtnMF.gridy = 6;
		getContentPane().add(rdbtnMF, gbc_rdbtnMF);
	}
	private void  setAlgoritmo(EnumAlgoritmo algoritmo) {
		ctrl.setAlgoritmo(algoritmo);
	}
	


}
