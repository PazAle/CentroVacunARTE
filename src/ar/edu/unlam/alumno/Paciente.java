package ar.edu.unlam.alumno;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Paciente implements Comparable<Paciente>{

	private Integer dni;
	private String nombre;
	private Boolean tuvoHepatitis;
	private Boolean tuvoRubeola;
	private Boolean seAplicoPrimerDosisCovid;
	private List<Vacuna> vacunasAplicadas;
	
	public Paciente(Integer dni, String nombre) {
		this.dni = dni;
		this.nombre = nombre;
		this.vacunasAplicadas = new ArrayList<>();
		this.tuvoHepatitis = false;
		this.tuvoRubeola = false;
		this.seAplicoPrimerDosisCovid = false;
	}

	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Vacuna> getVacunasAplicadas() {
		return vacunasAplicadas;
	}

	public void setVacunasAplicadas(List<Vacuna> vacunasAplicadas) {
		this.vacunasAplicadas = vacunasAplicadas;
	}

	public Boolean getTuvoHepatitis() {
		return tuvoHepatitis;
	}

	public void setTuvoHepatitis(Boolean tuvoHepatitis) {
		this.tuvoHepatitis = tuvoHepatitis;
	}

	public Boolean getTuvoRubeola() {
		return tuvoRubeola;
	}

	public void setTuvoRubeola(Boolean tuvoRubeola) {
		this.tuvoRubeola = tuvoRubeola;
	}
	

	public Boolean getSeAplicoPrimerDosisCovid() {
		return seAplicoPrimerDosisCovid;
	}

	public void setSeAplicoPrimerDosisCovid(Boolean seAplicoPrimerDosisCovid) {
		this.seAplicoPrimerDosisCovid = seAplicoPrimerDosisCovid;
	}

	public void aplicarVacuna(Vacuna vacuna) throws VacunaYaAplicadaException {
		if(this.getVacunasAplicadas().contains(vacuna)) {
			throw new VacunaYaAplicadaException("El paciente ya tiene aplicada la vacuna");
		} else {
			this.vacunasAplicadas.add(vacuna);
		}
		
	}

	@Override
	public int hashCode() {
		return Objects.hash(dni, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Paciente other = (Paciente) obj;
		return Objects.equals(dni, other.dni) && Objects.equals(nombre, other.nombre);
	}

	@Override
	public int compareTo(Paciente o) {
		Integer comparacionPorDni = this.getDni().compareTo(o.getDni());
	    if (comparacionPorDni != 0) {
	        return comparacionPorDni; // 
	    }
	    return this.getNombre().compareTo(o.getNombre());
	}

	@Override
	public String toString() {
		return "Paciente [dni=" + dni + ", nombre=" + nombre + ", tuvoHepatitis=" + tuvoHepatitis + ", tuvoRubeola="
				+ tuvoRubeola + "]";
	}
	
	
	
	
}
