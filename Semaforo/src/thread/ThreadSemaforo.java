
package thread;

import java.awt.Rectangle;
import java.util.Random;
import java.util.concurrent.Semaphore;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ThreadSemaforo extends Thread {
	private JLabel carro, semaforoVermelho, semaforoVerde;
	private JButton botaoIniciar;
	private int opcao;
	private static int quantidadeDeCarrosParaOSistemaParar = 0;
	private Semaphore semaforo;

	public ThreadSemaforo(JLabel carro, JLabel semaforoVermelho, JLabel semaforoVerde, Semaphore semaforo,
			JButton botaoIniciar, int op) {

		this.carro = carro;
		this.semaforoVermelho = semaforoVermelho;
		this.semaforoVerde = semaforoVerde;
		this.semaforo = semaforo;
		this.botaoIniciar = botaoIniciar;
		this.opcao = op;

	}

	public void run() {
		try {

			semaforo.acquire();

			acionaCarro();

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			semaforo.release();

		}

		quantidadeDeCarrosParaOSistemaParar++;

		if (quantidadeDeCarrosParaOSistemaParar == 2) {

			botaoIniciar.setVisible(true);

			quantidadeDeCarrosParaOSistemaParar = 0;
		}
	}

	public void acionaCarro() {
		Rectangle posicao = carro.getBounds();
		int aux = 0;
		Random random = new Random();

		switch (opcao) {
		case 1:
			semaforoVermelho.setVisible(false);
			semaforoVerde.setVisible(true);

			JOptionPane.showMessageDialog(null,
					"Amarelo usou down e viu que a variavel do semaforo estava igual a 1 e que a zona ta vazia então \n ele entra na zona critica deixando a variavel igual a 0",
					"", 1);
			while (aux < 2) {
				try {
					Thread.sleep(10);
				} catch (Exception e) {
					// TODO: handle exception
				}
				if (aux == 1) {
					posicao.y = (posicao.y) + (random.nextInt(2));
					if (posicao.y >= 46) {
						aux = 2;
					}
				}
				posicao.y = (posicao.y) + (random.nextInt(5));
				carro.setBounds(posicao);
				if (posicao.y >= 800) {
					posicao.y = -100;
					aux = 1;
				}

			}

			JOptionPane.showMessageDialog(null,
					"Amarelo saiu da zona critica e usou UP para atualizar a variavel para 1, liberando a zona critica para que outro possa acessar",
					"", 1);
			semaforoVermelho.setVisible(true);
			semaforoVerde.setVisible(false);

			break;
		case 2:
			semaforoVermelho.setVisible(false);
			semaforoVerde.setVisible(true);
			JOptionPane.showMessageDialog(null,
					"Agora Azul usou down e viu que a zona ta vazia então ele entra na zona critica", "", 1);
			while (aux < 2) {
				try {
					Thread.sleep(10);
				} catch (Exception e) {
					// TODO: handle exception
				}
				if (aux == 1) {
					posicao.x = (posicao.x) - (random.nextInt(2));
					if (posicao.x <= 560) {
						aux = 2;
					}
				}
				posicao.x = (posicao.x) - (random.nextInt(5));
				carro.setBounds(posicao);
				if (posicao.x <= -100) {
					posicao.x = 800;
					aux = 1;
				}

			}
			semaforoVermelho.setVisible(true);
			semaforoVerde.setVisible(false);
			JOptionPane.showMessageDialog(null,
					"Azul saiu da zona critica e usou UP para atualizar a variavel para 1, liberando a zona critica para que outro possa acessar",
					"", 1);

			break;

		}
	}
}
