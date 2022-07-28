package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import model.Model;
import model.ModelProcesso;

public class ViewManterProcesso extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField fieldID;
	private JTextField fieldArrivalTime;
	private JTextField fieldBurstTime;
	private ModelProcesso model;

	
	public void exec(Component parent) {
		setarCampos();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(parent);
		setVisible(true);
		
	}

	/**
	 * Create the dialog.
	 */
	public ViewManterProcesso(Model model) {
		setResizable(false);
		this.model = (ModelProcesso)model;
		setTitle("Manter Processo");
		setBounds(100, 100, 369, 153);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblID = new JLabel("ID:");
			lblID.setBounds(71, 8, 91, 13);
			lblID.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(lblID);
		}
		
		fieldID = new JTextField();
		fieldID.setEnabled(false);
		fieldID.setBounds(167, 5, 96, 19);
		contentPanel.add(fieldID);
		fieldID.setColumns(10);
		
		JLabel lblArrivalTime = new JLabel("ArrivalTime:");
		lblArrivalTime.setBounds(71, 29, 91, 13);
		lblArrivalTime.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPanel.add(lblArrivalTime);
		{
			fieldArrivalTime = new JTextField();
			fieldArrivalTime.setBounds(167, 29, 96, 19);
			contentPanel.add(fieldArrivalTime);
			fieldArrivalTime.setColumns(10);
		}
		{
			JLabel lblBurstTime = new JLabel("Burst Time:");
			lblBurstTime.setBounds(71, 56, 91, 13);
			lblBurstTime.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(lblBurstTime);
		}
		{
			fieldBurstTime = new JTextField();
			fieldBurstTime.setBounds(167, 53, 96, 19);
			contentPanel.add(fieldBurstTime);
			fieldBurstTime.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setarModel();
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	private void setarCampos() {
		this.fieldID.setText(this.model.getId().toString());
		this.fieldArrivalTime.setText(this.model.getTimeArrival().toString());
		this.fieldBurstTime.setText(this.model.getBurstTime().toString());
		
	}
	private void setarModel(){
		this.model.setBurstTime(Integer.parseInt((this.fieldBurstTime.getText())));
		this.model.setBurstTimeOriginal(Integer.parseInt((this.fieldBurstTime.getText())));
		this.model.setTimeArrival(Integer.parseInt(this.fieldArrivalTime.getText()));
		this.model.setTimeArrivalOriginal(Integer.parseInt(this.fieldArrivalTime.getText()));
	}
}
