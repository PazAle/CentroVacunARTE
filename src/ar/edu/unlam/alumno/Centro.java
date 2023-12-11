package ar.edu.unlam.alumno;

import java.util.Set;
import java.util.TreeSet;

public class Centro {

	private String nombre;
	private Set<Paciente> pacientesVacunados;
	
	public Centro(String nombre) {
		this.nombre = nombre;
		this.pacientesVacunados = new TreeSet<>();
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Paciente> getPacientesVacunados() {
		return pacientesVacunados;
	}

	public void setPacientesVacunados(Set<Paciente> pacientesVacunados) {
		this.pacientesVacunados = pacientesVacunados;
	}

	public Boolean vacunar(Vacuna vacuna, Paciente paciente) throws 
	NoCovidVaccineException, YaTuvoLaEnfermedadException, NoMoreVaccineException, VacunaYaAplicadaException {
		Boolean sePudoVacunar = false;
		Boolean estaVacunado = estaVacunado(paciente);
		Integer tipoDeVacuna = getTipoDeVacuna(vacuna);
		if(!estaVacunado) {
			aplicarVacuna(tipoDeVacuna, paciente, vacuna);
			sePudoVacunar = true;
		} else {
			tieneDosVacunas(paciente);
			aplicarVacuna(tipoDeVacuna, paciente, vacuna);
		}
		return sePudoVacunar;
	}

	private void tieneDosVacunas(Paciente paciente) throws NoMoreVaccineException {
		if(paciente.getVacunasAplicadas().size() == 2) {
			throw new NoMoreVaccineException("El paciente ya tiene dos vacunas aplicadas, no es posible aplicarse más vacunas");
		}
		
	}

	private void aplicarVacuna(Integer tipoDeVacuna, Paciente paciente, Vacuna vacuna) throws NoCovidVaccineException, YaTuvoLaEnfermedadException, VacunaYaAplicadaException {
		Boolean tienePrimerDosis, tuvoHepatitis, tuvoRubeola;
		final int covid1erDosis = 1, covid2daDosis = 2, hepatitisA = 3, hepatitisB = 4, hepatitisC = 5, rubeola = 6;
		switch (tipoDeVacuna) {
		case covid1erDosis:
			paciente.setSeAplicoPrimerDosisCovid(true);
			break;
		case covid2daDosis:
			tienePrimerDosis = tienePrimerDosis(paciente);
			if(tienePrimerDosis) {
				paciente.aplicarVacuna(vacuna);
				registrarPacienteVacunado(paciente);
			}
			break;
		case hepatitisA:
			tuvoHepatitis = tuvoHepatitis(paciente);
			if(tuvoHepatitis) {
				throw new YaTuvoLaEnfermedadException("No es posible vacunarse contra la Hepatits A, debido a que ya tuvo dicha enfermedad");
			}
			paciente.aplicarVacuna(vacuna);
			registrarPacienteVacunado(paciente);
			break;
		case hepatitisB:
			tuvoHepatitis = tuvoHepatitis(paciente);
			if(tuvoHepatitis) {
				throw new YaTuvoLaEnfermedadException("No es posible vacunarse contra la Hepatits B, debido a que ya tuvo dicha enfermedad");
			}
			paciente.aplicarVacuna(vacuna);
			registrarPacienteVacunado(paciente);
			break;
		case hepatitisC:
			tuvoHepatitis = tuvoHepatitis(paciente);
			if(tuvoHepatitis) {
				throw new YaTuvoLaEnfermedadException("No es posible vacunarse contra la Hepatits C, debido a que ya tuvo dicha enfermedad");
			}
			paciente.aplicarVacuna(vacuna);
			registrarPacienteVacunado(paciente);
			break;
		case rubeola:
			tuvoRubeola = tuvoRubeola(paciente);
			if(tuvoRubeola) {
				throw new YaTuvoLaEnfermedadException("No es posible vacunarse contra la Rubeola, debido a que ya tuvo dicha enfermedad");
			}
			paciente.aplicarVacuna(vacuna);
			registrarPacienteVacunado(paciente);
			break;
		}
		
		
	}

	private void registrarPacienteVacunado(Paciente paciente) {
		this.pacientesVacunados.add(paciente);		
	}

	private Boolean tuvoRubeola(Paciente paciente) {
		Boolean tuvoRubeola = false;
		if(paciente.getTuvoRubeola().equals(true)) {
			tuvoRubeola = true;
		}
		return tuvoRubeola;
	}

	private Boolean tuvoHepatitis(Paciente paciente) {
		Boolean tuvoHepatitis = false;
		if(paciente.getTuvoHepatitis().equals(true)) {
			tuvoHepatitis = true;
		}
		return tuvoHepatitis;
	}

	private Boolean tienePrimerDosis(Paciente paciente) throws NoCovidVaccineException {
		Boolean tienePrimerDosis = false;
		if(paciente.getSeAplicoPrimerDosisCovid().equals(true)) {
			tienePrimerDosis = true;
		}
		
		if(!tienePrimerDosis) {
			throw new NoCovidVaccineException("No es posible aplicarse la segunda dosis de covid ya que primero debe aplicarse la primer dosis");
		}
		return tienePrimerDosis;
	}

	private Integer getTipoDeVacuna(Vacuna vacuna) {
		Integer tipo = 0;
		
		if(vacuna instanceof Covid1erDosis) {
			tipo = 1;
		} else if(vacuna instanceof Covid2daDosis) {
			tipo = 2;
		} else if(vacuna instanceof HepatitisA) {
			tipo = 3;
		} else if(vacuna instanceof HepatitisB) {
			tipo = 4;
		} else if(vacuna instanceof HepatitisC) {
			tipo = 5;
		} else if(vacuna instanceof Rubeola) {
			tipo = 6;
		}
		
		return tipo;
	}

	private Boolean estaVacunado(Paciente paciente) {
		Boolean estaVacunado = this.getPacientesVacunados().contains(paciente);
		return estaVacunado;
	}

}
