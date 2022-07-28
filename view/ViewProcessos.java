package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.ControllerProcessos;
import model.Model;
import view.tableModel.TableModelProcesso;

public class ViewProcessos extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private TableModelProcesso tableModel;
	private JTable tableProcessos;
	
	
	public void exec(Component parent) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(parent);
		setVisible(true);
	}
	
	

	
	public ViewProcessos(ControllerProcessos ctrl, ArrayList<Model> models) {
		setModal(true);
		setResizable(false);
		setTitle("Visualizar Processos");
		setBounds(100, 100, 767, 512);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.NORTH);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JLabel lblProcessos = new JLabel("Processos");
			lblProcessos.setHorizontalAlignment(SwingConstants.CENTER);
			lblProcessos.setFont(new Font("Tahoma", Font.BOLD, 14));
			contentPanel.add(lblProcessos, BorderLayout.NORTH);
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new BorderLayout(0, 0));
			{
				{
					JPanel panel = new JPanel();
					buttonPane.add(panel, BorderLayout.EAST);
					JButton okButton = new JButton("OK");
					panel.add(okButton);
					okButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							setVisible(false);
							dispose();
							ctrl.executarProcessos();
						}
					});
					okButton.setActionCommand("OK");
					getRootPane().setDefaultButton(okButton);
					{
						JButton cancelButton = new JButton("Cancelar");
						panel.add(cancelButton);
						cancelButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								setVisible(false);
								dispose();
							}
						});
						cancelButton.setActionCommand("Cancel");
					}
				}
			}
			{
				JLabel lblAviso = new JLabel("Prioridade s\u00F3 tem efeito no algoritmo de prioridade!");
				lblAviso.setForeground(new Color(255, 51, 0));
				lblAviso.setFont(new Font("Tahoma", Font.BOLD, 10));
				lblAviso.setHorizontalAlignment(SwingConstants.RIGHT);
				buttonPane.add(lblAviso, BorderLayout.WEST);
			}
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			getContentPane().add(scrollPane, BorderLayout.CENTER);
			{
				tableModel = new TableModelProcesso();
				tableModel.setArray(models);
				tableProcessos = new JTable(tableModel);
				tableProcessos.setCellSelectionEnabled(true);
				/*tableProcessos.addMouseListener(new MouseAdapter(){
					
					@Override
					public void mouseClicked(MouseEvent e) {
						if(e.getClickCount() == 2) {
							ModelProcesso model = (ModelProcesso) tableModel.getModel(tableProcessos.getSelectedRow());
							ctrl.manter(model);
						}
					}
				});*/
				scrollPane.setViewportView(tableProcessos);
			}
		}
	}
	

}
