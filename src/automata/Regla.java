package automata;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JFrame;

public class Regla extends JFrame {

	public Regla() {
		
		setTitle("automata Celular");
		setBackground(Color.BLACK);
		setSize(1000, 600);
		setLocationRelativeTo(null);
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	
	int matriz[][] = new int[1000][1000];
	int a, b, c, d, e, f, g, h;
	int reglas[][] = new int[8][3];

	public void Reglas() {
		
		for (int i = 0; i < 1000; i++) {
			matriz[0][i] = 0;
		}
		//punto inicial en la primera fila
		matriz[0][100] = 1;
	}

	public void automata() {
		// se utilizan 2 ciclos for para recorrer los puntos por filas y por
		// columnas
		for (int y = 0; y < 510; y++) {
			for (int x = 0; x < 255; x++) {
				// se limita que el eje x tiene que ser menor a 2
				if (x < 255) {
					
					int j = 0, k = 0, l = 0;
					// junto con la variable x, las variables j, k son sus
					// consecutivas.La variable L corresponde a la misma columna
					// de J pero de la siguiente fila
					j = x + 1;
					k = x + 2;
					l = y + 1;
					// se le asigna un nuevo valor a la posicion de la nueva
					// fila, que ser igual al resultado que arroje como
					// resultado de la regla de 3 numeros de la fila anterior
					matriz[l][j] = getCasilla(matriz[y][x], matriz[y][j],
							matriz[y][k]);
					//en caso excepcional para los ultimos terminos (cierre de anillo), se ocupa esta alternativa de llenado
				} else if (x >= 255) {
					int l = 0;
					l = y + 1;
					matriz[l][256] = getCasilla(matriz[y][256], matriz[y][256],
							matriz[y][0]);
					matriz[l][0] = getCasilla(matriz[y][256], matriz[y][0],
							matriz[y][1]);
				}
			}
		}
	}

	// llena la matriz con ceros (matriz vacia)
	public void llenarMatriz() {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				matriz[i][j] = 0;
			}
		}
	}

	// muestra la matriz de consola (modo de pruebas)
	public void mostrarMatriz() {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				System.out.print(matriz[i][j]);
			}
			System.out.println();
		}
	}

	public void encenderPixelCentral() {
		matriz[0][matriz[0].length / 2] = 1;
	}

	/*
	 * El metodo getCasilla es el encargado de ir siguiendo la regla y regresar
	 * el nuevo valor que la celula tendra en la siguiente etapa del tiempo
	 * (k+1)
	 */
	public int getCasilla(int A, int B, int C) {
		int j = 0;
		for (int i = 0; i < reglas.length; i++) {
			if (reglas[i][j] == A && reglas[i][j + 1] == B
					&& reglas[i][j + 2] == C) {
				//retorna el valor del resultado del siguiente metodo, enviandole previamente la posicion de la regla
				return consultaCasilla(i);
			}
		}
		return -1;
	}

	//recibida la posicion de la regla, se consulta por el resultado de la regla.
	public int consultaCasilla(int regla) {
		int r = 0;
		if (regla == 0) {
			r = a;
		} else if (regla == 1) {
			r = b;
		} else if (regla == 2) {
			r = c;
		} else if (regla == 3) {
			r = d;
		} else if (regla == 4) {
			r = e;
		} else if (regla == 5) {
			r = f;
		} else if (regla == 6) {
			r = g;
		} else if (regla == 7) {
			r = h;
		}
		return r;
	}

	
	/* Se reciben los valores del vector binario para crear la regla */
	public void getRegla(int H, int G, int F, int E, int D, int C, int B, int A) {
		a = A;
		b = B;
		c = C;
		d = D;
		e = E;
		f = F;
		g = G;
		h = H;
	}

	//getter y setters del arreglo bidimensional "reglas"
	public int[][] getReglas() {
		return reglas;
	}

	public void setReglas(int[][] reglas) {
		this.reglas = reglas;
	}

	
	//metodo de dibujo
	public void paint(Graphics g) {
		int constante = 5;// multiplicador del tamano del cuadro pintado
		super.paint(g);
		for (int y = 0; y < 500; y++) {
			try {
				//m�todo que se encarga de que se vaya pintando por fila o tupla, cada 20 milisegundos
				Thread.sleep(5);
				for (int x = 0; x < 250; x++) {
					if (matriz[y][x] == 1) {
						
						int l = 0, k = 0;
						l = x * constante; // multiplicando el tama�o del cuadro pintado
						k = y * constante;
						g.setColor(cambiarColor());
						g.fillRect(l, k, constante, constante);
					} else {
						
						int a = 0, b = 0;
						a = x * constante;
						b = y * constante;
						g.setColor(Color.BLACK);//color
						g.fillRect(a, b, constante, constante);//posici�n y tama�o de cada cuadro a pintar
					}
				}
				// se capturan las excepciones que puedan ocurrir
			} catch (InterruptedException e) {
				System.out.println("Excepcion: " + e.getMessage());
			}
		}
		/* Se termina el metodo paint */
	}

	public Color cambiarColor(){
		Color[] a=new Color[10];
		a[0]=Color.BLUE;
		a[1]=Color.CYAN;
		a[2]=Color.GREEN;
		a[3]=Color.ORANGE;
		a[4]=Color.PINK;
		a[5]=Color.YELLOW;
		a[6]=Color.WHITE;
		a[7]=Color.RED;
		a[8]=Color.MAGENTA;
		a[9]=Color.LIGHT_GRAY;
		
		Random rd=new Random();
		int aleatorio = rd.nextInt(10);
		return a[aleatorio];
	}
	//se pone en modo visible el JFrame
	public void mostrar() {
		
		setVisible(true);
		repaint();
	}
}
