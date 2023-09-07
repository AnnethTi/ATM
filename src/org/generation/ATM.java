package org.generation;


import java.util.InputMismatchException;
import java.util.Scanner;



public class ATM {

	//atributos
	private static double initialBalance = 10_000;
	private static double limitWithdraw = 6_000;
	private static double multiplesWithdraw = 50;
	private static double mountDonation = 200;

	private int serialNumber = 0;
	private double balance;
	private int attempts  = 0;
	private Scanner sc = new Scanner(System.in);
		
	//constructor
	public ATM() {
		this.serialNumber = serialNumber++;
		this.balance = initialBalance;
	}
	
	//setters
	//getters
	public double getBalance() {
		return this.balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}

	//metodos
	public void showBalance() {
		System.out.println("Tu balance es: $" + getBalance()); 
	}
	public void menu() {
		int option;
		System.out.println();
		System.out.println("==== MENU ====");
		System.out.println();
		System.out.println("1. Retirar dinero");
		System.out.println("2. Hacer depósitos");
		System.out.println("3. Consultar saldo");
		System.out.println("4. Quejas");
		System.out.println("5. Ver últimos movimientos");
		System.out.println("9. Salir del cajero");
		System.out.println();
		System.out.print("Escoge una opcion: ");
		
		option = sc.nextInt();	
		sc.nextLine();
		
		try {
			switch (option) {
			case 1: {	//listo
				attempts = 0;
				withdraw();
				break;
			}
			case 2: {	
				attempts = 0;
				deposit(); //listo
				break;
			}
			case 3: {	// listo
				attempts = 0;
				consultBalance();
				break;
			}
			case 4: {	//listo
				attempts = 0;
				claims();
				break;
			}
			case 5: {	
				attempts = 0;
				break;
			}
			case 9: {	//listo
				attempts = 0;
				exit();
				break;
			}
			default:
				attempts++;
				if(attempts == 3) exit();
				else {
					System.out.println("Opcion Invalida, vuelve a intentarlo");
					menu();				
				}
			}
		} catch (InputMismatchException e) {
			System.err.println("Error, introduciste un carácter");
			e.printStackTrace();
		} 
		
	}
	
	public void deposit() {
		System.out.println();
		System.out.println("===== Depósitos =====");
		System.out.println();
		double mount;
		int option;
		
		
		try {
			do {
				System.out.println("1. Cuenta de Cheques");
				System.out.println("2. Depósito a Tarjeta de Crédito");
				
				System.out.print("Escoge una opcion: ");
				
				option = sc.nextInt();	
				sc.nextLine();
			} while (option != 1 && option != 2);
			
			if (option == 1) {
				do {
					System.out.print("Introduce la cantidad a depositar: $");
					mount = sc.nextDouble();
					sc.nextLine();
					if (mount % 50 != 0)
					System.err.println("Solo depósitos múltiplos de $50");
				} while (mount % 50 != 0);
				setBalance(mount + getBalance());
				System.out.println("Deposito realizado con exito");
				showBalance();
				menu();
			}
			if (option == 2) {
				System.out.print("Introduce la cantidad a depositar: $");
				mount = sc.nextDouble();
				sc.nextLine();
				setBalance(getBalance() - mount);
				System.out.println("Deposito realizado con exito");
				showBalance();
				menu();
				
			}
			
		} catch (Exception e) {
			System.err.println("Introduciste un caracter incorrecto");
			e.printStackTrace();
		}
		
		
		
	}
	
	public void withdraw() {
		System.out.println();
		System.out.println("===== Retiros =====");
		System.out.println();
		double mount;
				
		do {
			System.out.println("Tu balance es: $" + getBalance());
			System.out.print("Introduce la cantidad a retirar: $");
			mount = sc.nextDouble();
			sc.nextLine();
			
			if (mount > limitWithdraw) System.err.println("Error: No se puede retirar más de $6,000.00");
			if (mount > getBalance()) System.err.println("Error: Lo máximo que puedes retirar es $" + getBalance());
			if ((mount % multiplesWithdraw) != 0) System.err.println("Error: Solo se puede retirar múltiplos de $50.00");
		
		} while (mount > limitWithdraw || mount > getBalance() || (mount % multiplesWithdraw) != 0 );
		setBalance(this.getBalance() - mount);
		System.out.println("Retiro realizado con exito");
		
		
		donations();
		
		
		showBalance(); 
		menu();
	}
	
	public void consultBalance() {
		System.out.println();
		System.out.println("===== Consultar saldo =====");
		System.out.println();
		showBalance();
		menu();
		
	}
	
	public void claims() {
		System.out.println();
		System.out.println("===== Quejas =====");
		System.out.println();
		System.out.println("No disponible por el momento, intente más tarde");
		menu();
	}
	
	public void movements() {}
	
	public void exit() {	
		System.out.println();
		System.out.println("Vuelve pronto!");
		sc.close();
		System.exit(0);
	}
	
	public void donations() {
		System.out.println("¿Desea donar $" +mountDonation +  " para la graduacón de ch30? s/n");
		String optionDonation;
		optionDonation = sc.nextLine().toLowerCase();
		
		if(optionDonation.charAt(0) == 's') {
			setBalance( getBalance() - 200);
			System.out.println("Gracias por tu donacion :D");
		}
		
		
	}
}
