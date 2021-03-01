package view;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.ControleCarro;

public class Tela extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel painel;

	public Tela() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 5, 700, 720);
		setResizable(false);

		painel = new JPanel();

		setContentPane(painel);

		painel.setLayout(null);

		JButton btnInicio = new JButton("INICIAR");
		btnInicio.setBounds(460, 20, 200, 67);
		btnInicio.setBackground(Color.cyan);

		painel.add(btnInicio);

		JLabel semaforoVermelho1 = new JLabel();
		semaforoVermelho1.setIcon(new ImageIcon("img/semaforos/vermelho/semaforo1.png"));
		semaforoVermelho1.setBounds(210, 125, 43, 128);

		painel.add(semaforoVermelho1);

		JLabel semaforoVermelho2 = new JLabel();
		semaforoVermelho2.setIcon(new ImageIcon("img/semaforos/vermelho/semaforo2.png"));
		semaforoVermelho2.setBounds(449, 210, 128, 43);

		painel.add(semaforoVermelho2);

		JLabel semaforoVerde1 = new JLabel();
		semaforoVerde1.setIcon(new ImageIcon("img/semaforos/verde/semaforo1.png"));
		semaforoVerde1.setBounds(210, 125, 43, 128);

		semaforoVerde1.setVisible(false);
		painel.add(semaforoVerde1);

		JLabel semaforoVerde2 = new JLabel();
		semaforoVerde2.setIcon(new ImageIcon("img/semaforos/verde/semaforo2.png"));
		semaforoVerde2.setBounds(449, 210, 128, 43);

		semaforoVerde2.setVisible(false);
		painel.add(semaforoVerde2);

		JLabel fundo = new JLabel();
		fundo.setBounds(0, 0, 700, 700);
		fundo.setIcon(new ImageIcon("img/planoDeFundo.png"));

		JLabel carro1 = new JLabel();
		carro1.setIcon(new ImageIcon("img/carros/carro1.png"));
		carro1.setBounds(283, 51, 64, 115);

		painel.add(carro1);

		JLabel carro2 = new JLabel();
		carro2.setIcon(new ImageIcon("img/carros/carro2.png"));
		carro2.setBounds(561, 272, 115, 64);

		painel.add(carro2);

		painel.add(fundo);

		ControleCarro cc = new ControleCarro(carro1, carro2, semaforoVermelho1, semaforoVermelho2, semaforoVerde1,
				semaforoVerde2, btnInicio);

		btnInicio.addActionListener(cc);
		setVisible(true);
	}
}
