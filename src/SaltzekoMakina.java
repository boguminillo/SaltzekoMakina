package src;

import java.util.Scanner;

/**
 * SaltzekoMakina
 */
public class SaltzekoMakina {

	private static final String[] PRODUKTUAK = { "Irten", "Ur botilatxoa", "Kola botilatxoa", "Laranja botilatxoa",
			"Limoi botilatxoa", "Nestea", "Kit-Kat", "Toblerone", "Fruitu lehorrak" };
	private static final double[] PREZIOAK = { 0, 1.5, 2, 2, 2, 1.8, 1.5, 2, 1 };
	private static int[] produktuKantitateak = new int[PRODUKTUAK.length];
	private static final double[] DIRUMOTAK = { 200, 100, 50, 20, 10, 5, 2, 1, 0.5, 0.2, 0.1, 0.05, 0.02, 0.01 };
	private static int[] diruKantitateak = new int[DIRUMOTAK.length];
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

	/**
	 * 
	 * @param d
	 * @return
	 */
	private static double round(double d) {
		return Math.round(d * 100) / 100.0; // poner la historia de esto en lo del debugger el .0
	}

	/**
	 * 
	 */
	private static void autatutaErakutzi() {
		for (int i = 1; i < PREZIOAK.length; i++) {
			if (produktuKantitateak[i] > 0) {
				System.out.println(produktuKantitateak[i] + " " + PRODUKTUAK[i]);
			}
		}
	}

	/**
	 * 
	 */
	private static void produktuKantitateakHasieratu() {
		for (int i = 0; i < PRODUKTUAK.length; i++) {
			produktuKantitateak[i] = 0;
		}
	}

	/**
	 * 
	 */
	private static void kanbioakErakutzi() {
		for (int i = 0; i < DIRUMOTAK.length; i++) { // documentar debugger prezioak.length
			if (diruKantitateak[i] > 0) {
				System.out.println(diruKantitateak[i] + "x" + DIRUMOTAK[i] + "€");
				diruKantitateak[i] = 0;
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
				produktuKantitateak[op]++;
				System.out.println(PRODUKTUAK[op] + " autatu duzu.\nGuztira:");
				autatutaErakutzi();
				System.out.println(gPrezio + "€");
				System.out.println("Produktu gehiago erosi nahi dituzu? (B)ai/(E)z");
				jarraitu = baiEzIrakurri();
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

	/**
	 * 
	 * @param kanbioak
	 */
	private static void itzuli(double kanbioak) {
		for (int i = 0; i < DIRUMOTAK.length; i++) {
			while (kanbioak >= DIRUMOTAK[i] - 0.001) {
				kanbioak -= DIRUMOTAK[i];
				diruKantitateak[i]++;
			}
		}
		kanbioakErakutzi();
	}

	public static void main(String[] args) {
		boolean jarraitu = true;
		do {
			double prezio = menu();
			double kanbioak = eskatuDirua(prezio);
			if (kanbioak <= 0) {
				System.out.println("Bildu zure produktuak:");
				autatutaErakutzi();
				kanbioak = -kanbioak;
				jarraitu = false;
			} else {
				produktuKantitateakHasieratu();
			}
			itzuli(kanbioak);
		} while (jarraitu);
		System.out.println("Eskerrik asko. Hurrengora arte!");
		sc.close();
	}
}