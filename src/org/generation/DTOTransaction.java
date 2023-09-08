package org.generation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DTOTransaction {

	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
	private String fechaTransaccion;
	private String movimiento;
	private double monto;

	public DTOTransaction(String movimiento, double monto) {
		super();
		LocalDateTime now = LocalDateTime.now();
		this.fechaTransaccion = dtf.format(now);
		this.movimiento = movimiento;
		this.monto = monto;
		}

	@Override
	public String toString() {
		return this.fechaTransaccion + " - " + this.movimiento + " de $" + this.monto; 
		}
}
