package pkg.pma;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Teste {

	public static void main(String[] args) throws FileNotFoundException {

		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\eders\\Desktop\\Pos\\PETR4.SA.csv"));
		try {
			while (br.ready()) {
				String linha = br.readLine();
				System.out.println(linha);
				if (linha != null) {
					String[] arrayLinha = linha.split(",");

					// Ignora o cabeçalho
					if (!linha.contains("Date")) {
						String ano = arrayLinha[0].substring(0, 4);

						if (!arrayLinha[2].equals("null")) {
							double maximo = Double.parseDouble(arrayLinha[2]);
							System.out.println("Ano: " + ano + " Maximo: " + maximo);
						}
					} else {
						System.out.println("Contém Date");
					}
				}

			}
		} catch (Exception e) {
			System.out.println("Exception " + e.getMessage());
		}

	}

}
