
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Semaphore;

import javax.swing.JButton;
import javax.swing.JLabel;

public class ControleCarro implements ActionListener {
	private JLabel carro1, carro2;
	private JLabel semaforoVermelho1, semaforoVermelho2;
	private JLabel semaforoVerde1, semaforoVerde2;
	private JButton btnInicio;
	private Semaphore semaforo = new Semaphore(1);

	public ControleCarro(JLabel carro1, JLabel carro2, JLabel semaforoVermelho1, JLabel semaforoVermelho2,
			JLabel semaforoVerde1, JLabel semaforoVerde2, JButton btnInicio) {

		this.carro1 = carro1;
		this.carro2 = carro2;

		this.semaforoVermelho1 = semaforoVermelho1;
		this.semaforoVermelho2 = semaforoVermelho2;

		this.semaforoVerde1 = semaforoVerde1;
		this.semaforoVerde2 = semaforoVerde2;

		this.btnInicio = btnInicio;
	}

	public void actionPerformed(ActionEvent e) {

		btnInicio.setVisible(false);

		ThreadSemaforo ts1 = new ThreadSemaforo(carro1, semaforoVermelho1, semaforoVerde1, semaforo, btnInicio, 1);

		ThreadSemaforo ts2 = new ThreadSemaforo(carro2, semaforoVermelho2, semaforoVerde2, semaforo, btnInicio, 2);

		ts1.start();
		ts2.start();

	}

}
