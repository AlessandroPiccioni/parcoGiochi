package parcogiochi;

import java.util.Scanner;

public class parcoDivertimenti1 {

	public static void main(String[] args) {
		// Dichiarazione array attrazione
		String nome[] = { "Montagne russe", "Ruota Panoramica", "Torre" };
		int maxCapacita[] = { 10, 5, 15 };
		int maxGiro[] = { 10, 5, 15 };
		double costoOperativo[] = { 50, 70, 40 };
		double biglietto[] = { 9.99, 12.99, 7.99 };
		int tempoPersona[] = { 3, 7, 2 };

		// Valori da random
		int attualeCapacita[] = new int[3];
		int attualeGiro[] = new int[3];
		int coda[] = new int[3];

		// Valori da calcolare durante l'esecuzione
		int totCoda[] = new int[3];
		int tempoAttesa = 0;
		int tempoTotaleAttesa[] = new int[3];
		int totVisite[] = new int[3];
		double guadagnoTotale[] = new double[3];

		// Variabili
		// VISITATORI
		int numeroAttrazione = 0;
		int codiceAttrazioneScelta = 0;
		// valore random
		double budget = 0;
		int dispAttesa = 0; // Tempo d'attesa della persona

		// PARCO
		int totaleVisitatori = 0;
		int visitatoreGiornaliero = 0;// Persone che visitano giornalmente il parco (random)
		int personeSoddisfatte = 0;
		int giorniLavorativi = 1;
		String attrazionePiuVisitata = "";
		double guadagnoNetto = 0; // (attrazione.guadagnoTotale - attrazione.costoOperativo) +

		// (attrazione2.guadagnoTotale - attrazione.costoOperativo) ecc...

		// Dichiarazione scanner
		Scanner scanner = new Scanner(System.in);

		System.out.println(
				"                        \\______   \\ ____   _______  __ ____   ____  __ ___/  |_|__| _____        |    |____ ___  _______  |    |   _____    ____    __| _/\r\n"
						+ "			 |    |  _// __ \\ /    \\  \\/ // __ \\ /    \\|  |  \\   __\\  | \\__  \\       |    \\__  \\\\  \\/ /\\__  \\ |    |   \\__  \\  /    \\  / __ | \r\n"
						+ "			 |    |   \\  ___/|   |  \\   /\\  ___/|   |  \\  |  /|  | |  |  / __ \\_ /\\__|    |/ __ \\\\   /  / __ \\|    |___ / __ \\|   |  \\/ /_/ | \r\n"
						+ "			 |______  /\\___  >___|  /\\_/  \\___  >___|  /____/ |__| |__| (____  / \\________(____  /\\_/  (____  /_______ (____  /___|  /\\____ | \r\n"
						+ "			        \\/     \\/     \\/          \\/     \\/                      \\/                \\/           \\/        \\/    \\/     \\/      \\/");

		// loop per giorni lavorativi
		for (int giorni = 0; giorni < giorniLavorativi; giorni++) {
			visitatoreGiornaliero = (int) Math.round(Math.random() * 10 + 5);     //era 151
			// loop per ogni visitatore
			for (int visitatore = 0; visitatore < visitatoreGiornaliero; visitatore++) {
				budget = Math.random() * 50;
				dispAttesa = (int) Math.round(Math.random() * 30);
				codiceAttrazioneScelta = 0;

				while (true) {
					// Interface interaction
					if (codiceAttrazioneScelta == 0) {
						// Assegnazione random per i valori delle attrazioni
						for (int i = 0; i < coda.length; i++) {
							attualeCapacita[i] = (int) Math.round(Math.random() * maxCapacita[i] * 2);
							attualeGiro[i] = (int) Math.round(Math.random() * maxGiro[i]);
							coda[i] = (int) Math.round(Math.random() * (maxCapacita[i]*2));
						}
						// ciclo per schermata inziale con le attrazioni
						System.out.println("Visitatore " + (visitatore + 1));
						System.out.println("LE NOSTRE ATTRAZIONI");
						for (int i = 0; i < nome.length; i++) {

							System.out.println((i + 1) + "." + nome[i]);

						}
						System.out.println("9. Esci"); // uscita

						codiceAttrazioneScelta = scanner.nextInt();
					} else if (codiceAttrazioneScelta == 9) {
						System.out.println("Arrivederci");
						break;
					}

					// CONTROLLO VALIDITA' INPUT CODICE
					if (codiceAttrazioneScelta >= 1 && codiceAttrazioneScelta <= 3) {
						codiceAttrazioneScelta--;
						// CONTROLLO BUDGET
						if (budget >= biglietto[codiceAttrazioneScelta]) {
							System.out.println("budget sufficiente");
							// CONTROLLO GIRO
							if (attualeGiro[codiceAttrazioneScelta] < maxGiro[codiceAttrazioneScelta]) {
								// CONTROLLO CAPACITA' ATTRAZIONE
								if (attualeCapacita[codiceAttrazioneScelta] < maxCapacita[codiceAttrazioneScelta]) {
									System.out.println("giro disponibile");
									// CONTROLLO PERSONE SODDISFATTE
									if (numeroAttrazione == 0) {
										personeSoddisfatte++;
									}
									totVisite[codiceAttrazioneScelta]++;
									tempoTotaleAttesa[codiceAttrazioneScelta] += tempoAttesa;
									totCoda[codiceAttrazioneScelta]++;
									numeroAttrazione++;
									budget -= biglietto[codiceAttrazioneScelta];
									guadagnoTotale[codiceAttrazioneScelta] += biglietto[codiceAttrazioneScelta];
									codiceAttrazioneScelta = 0;

								} else {
									// CONTROLLO DISPONIBILITA' AD ATTENDERE
									System.out.println("capacità massima raggiunta"); // aggiungere coda
									String sceltaCoda;
									// CONTROLLO INPUT SCELTA
									do {
										System.out.println("Vuoi restare in coda? si/no"); // far scegliere all'utente
																							// se
																							// restare in coda
										sceltaCoda = scanner.next();
									} while (!(sceltaCoda.equalsIgnoreCase("si") || sceltaCoda.equalsIgnoreCase("no")));
									if (sceltaCoda.equalsIgnoreCase("si")) { // calcolare tempo attesa
										tempoAttesa = coda[codiceAttrazioneScelta]
												* tempoPersona[codiceAttrazioneScelta];
										if (dispAttesa >= tempoAttesa) {
											System.out.println("BUON DIVERTIMENTO!");
											if (numeroAttrazione == 0) {
												personeSoddisfatte++;
											}
											totVisite[codiceAttrazioneScelta]++;
											tempoTotaleAttesa[codiceAttrazioneScelta] += tempoAttesa;
											totCoda[codiceAttrazioneScelta]++;
											numeroAttrazione++;
											budget -= biglietto[codiceAttrazioneScelta];
											guadagnoTotale[codiceAttrazioneScelta] += biglietto[codiceAttrazioneScelta];
											codiceAttrazioneScelta = 0;
										} else {
											System.out.println("Tempo di attesa troppo lungo");
											codiceAttrazioneScelta = 0;
										}

									}

									codiceAttrazioneScelta = 0;

								}
							} else {
								System.out.println("Giri massimi raggiunti.");
								codiceAttrazioneScelta = 0;
							}
						} else {
							System.out.println("budget non sufficiente");
							codiceAttrazioneScelta = 0;
						}

					}
					// DA FARE: COMPLETA TUTTO NON ABBIAMO SCELTA SIAMO SPACCIATI
					
				}

			}
			// aggiungere guadagnoTotale = totVisitatori * costoPersona
			totaleVisitatori += visitatoreGiornaliero;
			// aggiungere visitatoreGiornaliero a totaleVisitatori
		}
		for (int i = 0; i < 3; i++) {
			guadagnoNetto += guadagnoTotale[i] - costoOperativo[i] * giorniLavorativi;

			
		} 
		//array totVisite
		int num = 0; 
		
		for (int i=0; i<totVisite.length; i++) {
			if(totVisite[i]>num) {
				attrazionePiuVisitata = nome[i];
		System.out.println("Il tempo medio di attesa dell'attrazione " + nome[i] + (tempoTotaleAttesa[i] / totCoda[i]));
		
			
			}
		}
		System.out.println("L'attrazione più visitata è: " + attrazionePiuVisitata);
        System.out.println("La percentuale di visitatori soddisfatti è " + ((personeSoddisfatte/totaleVisitatori)*100));
	}
}
