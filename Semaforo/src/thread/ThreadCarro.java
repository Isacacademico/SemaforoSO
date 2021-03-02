
package thread;

import java.awt.Rectangle;
import java.util.Random;
import java.util.concurrent.Semaphore;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ThreadCarro extends Thread {
	
	private JLabel carro, semaforoVermelho, semaforoVerde;
	private JButton botaoIniciar;
	private int opcao;
	private static int quantidadeDeCarrosParaOSistemaParar = 0;
	private Semaphore semaforo;

	public ThreadCarro(JLabel carro, JLabel semaforoVermelho, JLabel semaforoVerde, Semaphore semaforo,
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
					"Amarelo usou DOWN e viu que o semaforo esta igual a 1, isso significa que a região critica esta vazio e disponivel para o amarelo entrar.\nSemaforo agora é 0 para indicar que o amarelo esta na região critica",
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
					"Amarelo saiu da região critica e usou UP para atualizar o semaforo para 1, liberando a região critica para que outro possa acessar",
					"", 1);
			semaforoVermelho.setVisible(true);
			semaforoVerde.setVisible(false);

			break;
		case 2:
			semaforoVermelho.setVisible(false);
			semaforoVerde.setVisible(true);
			JOptionPane.showMessageDialog(null,
					"Azul usou DOWN e viu que o semaforo esta igual a 1, isso significa que a região critica esta vazio e disponivel para o azul entrar\nSemaforo agora é 0 para indicar que o azul esta na região critica",
					"", 1);
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
					"Azul saiu da zona critica e usou UP para atualizar o semaforo para 1, liberando a zona critica para que outro possa acessar",
					"", 1);

			break;

		}
	}
}
