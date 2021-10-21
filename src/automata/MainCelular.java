package automata;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.JOptionPane;

public class MainCelular {
	int binario[] = new int[8];

	public static void main(String[] args) {

		String leer;
		Regla matriz = new Regla();
		MainCelular main = new MainCelular();

		leer = main.lectura();
		leer = main.quitarSaltos(leer);
		matriz.setReglas(main.aArreglo(leer));
		int opc = 0, cont = 0;

		int regla;
		String numRegla = "";
		do {
			
			do {
				numRegla = JOptionPane.showInputDialog(
						"Inserta el numero de regla a mostrar:", "");
			} while (numRegla.equals(""));
			
			regla = Integer.parseInt(numRegla);
		
			main.enteroBinario(regla);
		
			matriz.getRegla(main.binario[0], main.binario[1], main.binario[2],
					main.binario[3], main.binario[4], main.binario[5],
					main.binario[6], main.binario[7]);
			matriz.Reglas();
			matriz.automata();
			matriz.mostrar();

			int pregunta = JOptionPane.showConfirmDialog(null,
					"Desea ingresar otra regla?", "NUEVA REGLA",
					JOptionPane.YES_NO_OPTION);
			if (pregunta == 1) {
				opc = 1;
			} else {
				cont++;
			}

		} while (opc != 1);

	}

	public void enteroBinario(int aux) {
		

		for (int i = 7; i >= 0; i--) {
			binario[i] = aux % 2;
			aux /= 2;
		}

		System.out.print("En binario es: ");
		for (int i = 0; i < binario.length; i++) {
			System.out.print(binario[i]);
		}
		System.out.println();
	}

	String lectura() {
		
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;

		try {
			archivo = new File("automata.txt");
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			
			String linea, aux = null;

			
			while ((linea = br.readLine()) != null) {
				if (aux == null)
					aux = linea;
				else
					aux = aux + "\n" + linea;
			}
			
			return aux;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}

	public String quitarSaltos(String cadena) {
		
		return cadena.replaceAll("\n", "");
	}

	public int[][] aArreglo(String leer) {
		// traspasando las reglas del archivo a un arreglo, para trabajar de
		// forma mas facil
		int z = 0;
		int regla[][] = new int[8][3];
		for (int i = 0; i < regla.length; i++) {
			for (int j = 0; j < regla[i].length; j++) {
				regla[i][j] = Integer.parseInt("" + leer.charAt(z));
				z++;
			}

		}
		return regla;
	}

	
	public void mostrarArreglo(int regla[][]) {
		for (int i = 0; i < regla.length; i++) {
			for (int j = 0; j < regla[i].length; j++) {
				System.out.print(regla[i][j]);
			}
			System.out.println();
		}
	}

}
