package src;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * SaltzekoMakina
 * 
 * Autobus-terminal bateko makina saltzaile bat kudeatzea ahalbidetuko duen
 * aplikazioa. Erabiltzaileak zenbakizko teklatu baten eta pantaila baten bidez
 * jarduten du makinarekin. Makinak aukeren menuak eskaintzen ditu eta
 * erabiltzaileak egiten duen aukeraketa jasotzen du, betiere erantzun egokia
 * sortuz. Dirua sartzea, aldaketak itzultzea eta produktua ematea ere
 * teklatuaren edo pantaila bidezko mezuen bidez simulatuko dira.
 */
public class SaltzekoMakina {

	/**
	 * Saltzen diren produktuen izenak
	 */
	private static final String[] PRODUKTUAK = { "Irten", "Ur botilatxoa", "Kola botilatxoa", "Laranja botilatxoa",
			"Limoi botilatxoa", "Nestea", "Kit-Kat", "Toblerone", "Fruitu lehorrak" };
	/**
	 * Saltzen diren produktuen prezioak
	 */
	private static final double[] PREZIOAK = { 0, 1.5, 2, 2, 2, 1.8, 1.5, 2, 1 };
	/**
	 * Autatutako produktuen kantitateak gordetzeko erabiliko den array-a
	 */
	private static int[] produktuKantitateak = new int[PRODUKTUAK.length];
	/**
	 * Makina eman ahal dituen bilete eta txanponen balioak
	 */
	private static final double[] DIRUMOTAK = { 200, 100, 50, 20, 10, 5, 2, 1, 0.5, 0.2, 0.1, 0.05, 0.02, 0.01 };
	/**
	 * Makina eman ahal dituen bilete eta txanponen izenak
	 */
	private static final String[] DIRUMOTAIZENAK = { "200€ bilete", "100€ bilete", "50€ bilete", "20€ bilete",
			"10€ bilete", "5€ bilete", "2€ txanpon", "1€ txanpon", "50 zentimo txanpon", "20 zentimo txanpon",
			"10 zentimo txanpon", "5 zentimo txanpon", "2 zentimo txanpon", "1 zentimo txanpon" };
	/**
	 * Erabiltzailea informazioa sartzeko erabiliko den Scanner
	 */
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
	 * Scanner-en bidez informazioa eskatuko du e edo b sartu arte, boolean bat
	 * itzuliko du satutako karakterearen arabera
	 * 
	 * @return true b sartzen badu eta false e sartzen badu
	 */
	private static boolean baiEzIrakurri() {
		String irakurri;
		char be;
		do {
			irakurri = sc.nextLine();
			if (irakurri.length() == 1) {
				be = irakurri.charAt(0);
				if (be == 'b' || be == 'B') {
					return true;
				} else if (be == 'e' || be == 'E') {
					return false;
				} else {
					System.out.println("B edo E autagaiak izan behar dira.");
				}
			} else {
				System.out.println("B edo E autagaiak izan behar dira.");
			}
		} while (true);
	}

	/**
	 * Double bat bi dezimaletara biribilduko du
	 * 
	 * @param d biribildu nahi dugun double
	 * @return bi dezimaleko double
	 */
	private static double round(double d) {
		return Math.round(d * 100) / 100.0;
		// TODO poner la historia del .0 en lo del debugger el .0
	}

	/**
	 * Autatutako produktu guztiak pantailatik inprimatuko ditu
	 */
	private static void autatutaErakutzi() {
		for (int i = 1; i < PRODUKTUAK.length; i++) {
			if (produktuKantitateak[i] > 0) {
				System.out.println(produktuKantitateak[i] + " " + PRODUKTUAK[i]);
			}
		}
	}

	/**
	 * ProduktuKantitateak hasieratuko du 0 esleitzen posizio guztietan
	 */
	private static void produktuKantitateakHasieratu() {
		for (int i = 0; i < PRODUKTUAK.length; i++) {
			produktuKantitateak[i] = 0;
		}
	}

	/**
	 * Produktuen menua pantailatik inprimatuko du
	 */
	private static void menuaInprimatu() {
		System.out.println("╔═══════╦═══════════════════════════╦═════════╗\n"
				+ "║ Kodea ║        Produktua          ║ Prezioa ║\n"
				+ "╠═══════╬═══════════════════════════╬═════════╣");
		for (int i = 0; i < PRODUKTUAK.length; i++) {
			System.out.printf("║     %d ║ %-25s ║ %.2f€   ║\n", i, PRODUKTUAK[i], PREZIOAK[i]);
		}
		System.out.println("╚═══════╩═══════════════════════════╩═════════╝");
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
			menuaInprimatu();
			op = intIrakurri();
			if (op == 0) {
				gPrezio = 0;
				produktuKantitateakHasieratu();
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
	 * Ordaindu nahi den ala ez galedtuko du eta dirua eskatuko du guztiz ordaindu
	 * arte edo erabiltzailea gehiago ordaindu nahi ez duela esan arte.
	 * 
	 * @param ordainduta   AtomicBoolean true bihurtuko da guztiz ordaindu badu,
	 *                     bestela false izango da
	 * @param prezioTotala ordaindu behar den dirua double bezala
	 * @return eman behar diren kanbioak double bezala
	 */
	private static double eskatuDirua(double prezioTotala, AtomicBoolean ordainduta) {
		// TODO documentar AtomicBoolean, sirve para poder modificarlo dentro de la
		// funcion y que la informacion llegue al main.
		double resto = prezioTotala;
		do {
			System.out.println(prezioTotala + "€ ordaindu behar duzu.\n" + resto + "€ falta da\n\n"
					+ "Dirua sartu nahi duzu (B)ai/(E)z:");
			if (baiEzIrakurri()) {
				System.out.println("Sartu dirua.");
				double sartutakoDirua = doubleIrakurri();
				resto -= sartutakoDirua;
				resto = round(resto);
			} else {
				ordainduta.set(false);
				return prezioTotala - resto;
			}
		} while (resto > 0);
		ordainduta.set(true);
		return Math.abs(resto);
		// TODO documentar debugger return -resto
	}

	/**
	 * Pantailatik inprimatuko ditu eman behar diren kanbioak bilete eta txanponetan
	 * 
	 * @param kanbioak double kanbio totalak
	 */
	private static void itzuli(double kanbioak) {
		int kantitatea = 0;
		for (int i = 0; i < DIRUMOTAK.length; i++) {
			// -0.001 double-en zehaztasun-arazo bat zuzentzeko da.
			while (kanbioak >= DIRUMOTAK[i] - 0.001) {
				kanbioak -= DIRUMOTAK[i];
				kantitatea++;
			}
			if (kantitatea > 0) {
				System.out.println(kantitatea + "x" + DIRUMOTAIZENAK[i]);
				kantitatea = 0;
			}
		}
	}

	/**
	 * Main metodoa, makinaren funtzio guztiak bukle baten egingo ditu
	 * 
	 * @param args Terminalean sartutako argumentuak
	 */
	public static void main(String[] args) {
		while (true) {
			double prezio = menu();
			AtomicBoolean ordainduta = new AtomicBoolean();
			double kanbioak = eskatuDirua(prezio, ordainduta);
			if (ordainduta.get()) {
				System.out.println("Bildu zure produktuak:");
				autatutaErakutzi();
			} else {
				produktuKantitateakHasieratu();
			}
			itzuli(kanbioak);
		}
	}
}