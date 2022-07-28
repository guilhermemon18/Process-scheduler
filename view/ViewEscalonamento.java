package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.ControllerEscalonamento;
import model.Model;
import view.tableModel.TableModelEscalonamento;

public class ViewEscalonamento extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField fieldTempoMedio;
	private JTable table;
	private TableModelEscalonamento tableModel;

	
	public void exec(Component parent) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(parent);
		setVisible(true);
		
	}
	/**
	 * Create the dialog.
	 */
	public ViewEscalonamento(ControllerEscalonamento ctrl,ArrayList<Model> models) {
		setModal(true);
		setTitle("Escalonamento: Resultados");
		//setBounds(100, 100, 652, 439);
		setBounds(100, 100, 767, 512);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.SOUTH);
			panel.setLayout(new GridLayout(0, 4, 0, 0));
			{
				JLabel lblTempoMedio = new JLabel("Tempo M\u00E9dio: ");
				lblTempoMedio.setHorizontalAlignment(SwingConstants.RIGHT);
				panel.add(lblTempoMedio);
			}
			{
				fieldTempoMedio = new JTextField();
				fieldTempoMedio.setEditable(false);
				panel.add(fieldTempoMedio);
				fieldTempoMedio.setColumns(10);
				fieldTempoMedio.setText(ctrl.getTempoMedio().toString());
			}
		}
		{
			JPanel panelTable = new JPanel();
			contentPanel.add(panelTable, BorderLayout.CENTER);
			panelTable.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				panelTable.add(scrollPane, BorderLayout.CENTER);
				{
					tableModel = new TableModelEscalonamento();
					tableModel.setArray(models);
					table = new JTable(tableModel);
					scrollPane.setViewportView(table);
				}
			}
			
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new BorderLayout(0, 0));
			{
				JPanel panel = new JPanel();
				FlowLayout flowLayout = (FlowLayout) panel.getLayout();
				flowLayout.setAlignment(FlowLayout.RIGHT);
				buttonPane.add(panel, BorderLayout.WEST);
				{
					JButton btnOutra = new JButton("Gr\u00E1fico WaitingTime");
					btnOutra.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							ctrl.graficoWaitingTime();
						}
					});
					panel.add(btnOutra);
				}
				{
					Component horizontalGlue = Box.createHorizontalGlue();
					panel.add(horizontalGlue);
				}
				{
					JButton bntGraficos = new JButton("Gr\u00E1fico FinishTime");
					bntGraficos.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							ctrl.graficoFinishTime();
						}
					});
					panel.add(bntGraficos);
				}
			}
			{
				{
					JPanel panel = new JPanel();
					FlowLayout flowLayout = (FlowLayout) panel.getLayout();
					flowLayout.setAlignment(FlowLayout.RIGHT);
					buttonPane.add(panel, BorderLayout.EAST);
					{
						Component horizontalGlue = Box.createHorizontalGlue();
						panel.add(horizontalGlue);
					}
					JButton cancelButton = new JButton("fechar");
					panel.add(cancelButton);
					cancelButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							dispose();
						}
					});
					cancelButton.setActionCommand("Cancel");
				}
			}
		}
	}

}
