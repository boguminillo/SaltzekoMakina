package src;

import java.util.Scanner;

/**
 * SaltzekoMakina
 */
public class SaltzekoMakina {

	private static final String[] PRODUKTUAK = { "Irten", "Ur botilatxoa", "Kola botilatxoa", "Laranja botilatxoa",
			"Limoi botilatxoa", "Nestea", "Kit-Kat", "Toblerone", "Fruitu lehorrak" };
	private static final double[] PREZIOAK = { 0, 1.5, 2, 2, 2, 1.8, 1.5, 2, 1 };
	private static int[] kantitateak = new int[9];
	private static Scanner sc = new Scanner(System.in);

	/**
	 * Scanner-en bidez informazioa eskatuko du zenbaki oso bat sartu arte,
	 * sartutako zebnaki osoa itzuliko du
	 * 
	 * @return sartutako zenbaki osoa
	 */
	private static int intIrakurri() {
		int znb = -1;
		boolean errepikatu;
		do {
			try {
				znb = Integer.parseInt(sc.nextLine());
				errepikatu = false;
			} catch (Exception e) {
				System.out.println("Zenbaki oso bat izan behar da");
				errepikatu = true;
			}
		} while (errepikatu);
		return znb;
	}

	/**
	 * Scanner-en bidez informazioa eskatuko du zenbaki erreal bat sartu arte,
	 * sartutako zebnaki erreala itzuliko du
	 * 
	 * @return sartutako zenbaki erreala
	 */
	private static double doubleIrakurri() {
		double znb = -1;
		boolean errepikatu;
		do {
			try {
				znb = Double.parseDouble(sc.nextLine());
				errepikatu = false;
			} catch (Exception e) {
				System.out.println("Zenbaki erreal bat izan behar da");
				errepikatu = true;
			}
		} while (errepikatu);
		return znb;
	}

	/**
	 * 
	 * 
	 * @return
	 */
	private static boolean baiEzIrakurri() {
		char bz;
		do {
			bz = sc.nextLine().charAt(0);
			if (bz == 'b' || bz == 'B') {
				return true;
			} else if (bz == 'e' || bz == 'E') {
				return false;
			} else {
				System.out.println("B edo E autagaiak izan behar dira.");
			}
		} while (true);
	}

	private static double round(double d) {
		return Math.round(d * 100) / 100.0; // poner la historia de esto en lo del debugger
	}

	/**
	 * 
	 */
	private static void autatutaErakutzi() {
		for (int i = 1; i < PREZIOAK.length; i++) {
			if (kantitateak[i] > 0) {
				System.out.println(kantitateak[i] + " " + PRODUKTUAK[i]);
			}
		}
	}

	/**
	 * Produktuen eskaera egingo du. Produktu bakoitza eskatu ondoren eskatutako
	 * guztia eta prezio osoa BEZ-ekin azalduko du eta galdetuko du produktu gehiago
	 * eskatu nahi diren ala ez.
	 * 
	 * @return double ezkatutako produktuen prezioa.
	 */
	private static double menu() {
		double gPrezio = 0;
		int op;
		boolean jarraitu = true;
		do {
			System.out.println("╔═══════╦════════════════════╦═════════╗\n"
					+ "║ Kodea ║     Produktua      ║ Prezioa ║\n" + "╠═══════╬════════════════════╬═════════╣\n"
					+ "║     0 ║ Irten              ║         ║\n" + "║     1 ║ Ur botilatxoa      ║ 1,5€    ║\n"
					+ "║     2 ║ Kola botilatxoa    ║ 2€      ║\n" + "║     3 ║ Laranja botilatxoa ║ 2€      ║\n"
					+ "║     4 ║ Limoi botilatxoa   ║ 2€      ║\n" + "║     5 ║ Nestea             ║ 1,80€   ║\n"
					+ "║     6 ║ Kit-Kat            ║ 1,50€   ║\n" + "║     7 ║ Toblerone          ║ 2€      ║\n"
					+ "║     8 ║ Fruitu lehorrak    ║ 1€      ║\n" + "╚═══════╩════════════════════╩═════════╝");
			op = intIrakurri();
			if (op == 0) {
				jarraitu = false;
			} else if (op > 0 && op < 9) {
				gPrezio += PREZIOAK[op] * 1.21;
				gPrezio = round(gPrezio);
				kantitateak[op]++;
				System.out.println(PRODUKTUAK[op] + " autatu duzu.\nGuztira:");
				autatutaErakutzi();
				System.out.println(gPrezio + "€");
				System.out.println("1 jarraitzeko, edozein zenbaki ordaintzeko.");
				jarraitu = intIrakurri() == 1;
			} else {
				System.out.println("Ez dago produkturik sartutako kodearekin.");
			}
		} while (jarraitu);
		return gPrezio;
	}

	/**
	 * 
	 * @param gPrezio
	 * @return
	 */
	private static double eskatuDirua(double prezioTotala) {
		double resto = prezioTotala;
		do {
			System.out.println(prezioTotala + "€ ordaindu behar duzu.\n" + resto + "€ falta da\n\n"
					+ "Dirua sartu nahi duzu (B)ai/(E)z:");
			if (baiEzIrakurri()) {
				System.out.println("Sartu dirua.");
				double sartutakoDirua = doubleIrakurri();
				resto -= sartutakoDirua;
			} else {
				return prezioTotala - resto;
			}
		} while (resto > 0);
		resto = round(resto);
		return resto; // documentar debugger
	}

	public static void main(String[] args) {
		double prezio = menu();
		double resto = eskatuDirua(prezio);
		if (resto <= 0) {
			autatutaErakutzi();
			resto = -resto;
		}
		sc.close();
	}
}