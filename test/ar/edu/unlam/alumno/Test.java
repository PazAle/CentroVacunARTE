package ar.edu.unlam.alumno;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class Test {

	@org.junit.Test
	public void queSePuedaVacunarAUnPaciente() {
		String nombrePaciente = "Cecilita", nombreCentro = "VacunARTE", nombreVacuna = "Vacuna contra el Covid, primer dosis";
		Integer dniPaciente = 7366634;
		
		Centro centro = new Centro(nombreCentro);
		Vacuna covid = new Covid1erDosis(nombreVacuna);
		Paciente paciente = new Paciente(dniPaciente,nombrePaciente);
		
		Boolean sePudoVacunar = false;
		try {
			sePudoVacunar = centro.vacunar(covid, paciente);
		} catch (NoCovidVaccineException | YaTuvoLaEnfermedadException | NoMoreVaccineException | VacunaYaAplicadaException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		assertTrue(sePudoVacunar);
	}
	
	@org.junit.Test(expected = NoCovidVaccineException.class)
	public void queNoSePuedaVacunarAUnPacienteConLaSegundaDosisDeCovidSiNoTieneLaPrimerDosisAplicada() throws NoCovidVaccineException {
		String nombrePaciente = "Cecilita", nombreCentro = "VacunARTE", nombreVacuna = "Vacuna contra el Covid, segunda dosis";
		Integer dniPaciente = 7366634;
		
		Centro centro = new Centro(nombreCentro);
		Vacuna covid = new Covid2daDosis(nombreVacuna);
		Paciente paciente = new Paciente(dniPaciente,nombrePaciente);
		
		Boolean sePudoVacunar = false;
		try {
			sePudoVacunar = centro.vacunar(covid, paciente);
		} catch (NoCovidVaccineException | YaTuvoLaEnfermedadException | NoMoreVaccineException | VacunaYaAplicadaException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			throw new NoCovidVaccineException();
		}
		
		assertTrue(sePudoVacunar);
	}
	
	@org.junit.Test(expected = YaTuvoLaEnfermedadException.class)
	public void queNoSePuedaVacunarAUnPacienteConLaVacunaDeRubeolaSiTuvoLaEnfermedadPreviamente() throws YaTuvoLaEnfermedadException {
		String nombrePaciente = "Cecilita", nombreCentro = "VacunARTE", nombreVacuna = "Vacuna contra la Rubeola";
		Integer dniPaciente = 7366634;
		
		Centro centro = new Centro(nombreCentro);
		Vacuna rubeola = new Rubeola(nombreVacuna);
		Paciente paciente = new Paciente(dniPaciente,nombrePaciente);
		paciente.setTuvoRubeola(true);
		
		Boolean sePudoVacunar = false;
		try {
			sePudoVacunar = centro.vacunar(rubeola, paciente);
		} catch (NoCovidVaccineException | YaTuvoLaEnfermedadException | NoMoreVaccineException | VacunaYaAplicadaException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			throw new YaTuvoLaEnfermedadException();
		}
		
		assertTrue(sePudoVacunar);
	}
	
	@org.junit.Test(expected = YaTuvoLaEnfermedadException.class)
	public void queNoSePuedaVacunarAUnPacienteConLaVacunaDeHepatitisASiTuvoLaEnfermedadPreviamente() throws YaTuvoLaEnfermedadException {
		String nombrePaciente = "Cecilita", nombreCentro = "VacunARTE", nombreVacuna = "Vacuna contra la Hepatitis A";
		Integer dniPaciente = 7366634;
		
		Centro centro = new Centro(nombreCentro);
		Vacuna hepatitisA = new HepatitisA(nombreVacuna);
		Paciente paciente = new Paciente(dniPaciente,nombrePaciente);
		paciente.setTuvoHepatitis(true);
		
		Boolean sePudoVacunar = false;
		try {
			sePudoVacunar = centro.vacunar(hepatitisA, paciente);
		} catch (NoCovidVaccineException | YaTuvoLaEnfermedadException | NoMoreVaccineException | VacunaYaAplicadaException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			throw new YaTuvoLaEnfermedadException();
		}
		
		assertTrue(sePudoVacunar);
	}

	@org.junit.Test
	public void queSePuedaAplicarLaSegundaDosisDelCovid(){
		String nombrePaciente = "Cecilita", nombreCentro = "VacunARTE", nombreVacuna = "Vacuna contra el COVID 19, primer dosis",
				nombreVacuna2 = "Vacuna conra el COVID 19, segunda dosis";
		Integer dniPaciente = 7366634;
		
		Centro centro = new Centro(nombreCentro);
		Vacuna covid = new Covid1erDosis(nombreVacuna);
		Vacuna covid2 = new Covid2daDosis(nombreVacuna2);
		Paciente paciente = new Paciente(dniPaciente,nombrePaciente);
		paciente.setTuvoHepatitis(true);
		
		Boolean sePudoVacunar = false;
		try {
			sePudoVacunar = centro.vacunar(covid, paciente);
			sePudoVacunar = centro.vacunar(covid2, paciente);
		} catch (NoCovidVaccineException | YaTuvoLaEnfermedadException | NoMoreVaccineException | VacunaYaAplicadaException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			
		}
		Integer valorEsperado = 1;
		Integer valorObtenido = paciente.getVacunasAplicadas().size();
		assertEquals(valorEsperado, valorObtenido);
		//assertTrue(sePudoVacunar);
	}
	
	@org.junit.Test(expected= NoMoreVaccineException.class)
	public void queNoSePuedaAplicarMasDeDosVacunasAUnPaciente() throws NoMoreVaccineException{
		String nombrePaciente = "Cecilita", nombreCentro = "VacunARTE", nombreVacuna = "Vacuna contra la Hepatitis B",
				nombreVacuna2 = "Vacuna conra la Rubeola", nombreVacuna3 = "Vacuna contra la Hepatitis A";
		Integer dniPaciente = 7366634;
		
		Centro centro = new Centro(nombreCentro);
		Vacuna hepatitisB = new HepatitisB(nombreVacuna);
		Vacuna rubeola = new Rubeola(nombreVacuna2);
		Vacuna hepatitisA = new HepatitisA(nombreVacuna3);
		Paciente paciente = new Paciente(dniPaciente,nombrePaciente);
		
		Boolean sePudoVacunar = false;
		try {
			sePudoVacunar = centro.vacunar(hepatitisB, paciente);
			sePudoVacunar = centro.vacunar(rubeola, paciente);
			sePudoVacunar = centro.vacunar(hepatitisA, paciente);
		} catch (NoCovidVaccineException | YaTuvoLaEnfermedadException | NoMoreVaccineException | VacunaYaAplicadaException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			throw new NoMoreVaccineException();
			
		}
		
		assertTrue(sePudoVacunar);
	}
	
	@org.junit.Test(expected= VacunaYaAplicadaException.class)
	public void queNoSePuedaAplicarDosVecesLaMismaVacuna() throws VacunaYaAplicadaException{
		String nombrePaciente = "Cecilita", nombreCentro = "VacunARTE", nombreVacuna = "Vacuna contra la Hepatitis B";
		Integer dniPaciente = 7366634;
		
		Centro centro = new Centro(nombreCentro);
		Vacuna hepatitisB = new HepatitisB(nombreVacuna);
		Paciente paciente = new Paciente(dniPaciente,nombrePaciente);
		
		Boolean sePudoVacunar = false;
		try {
			sePudoVacunar = centro.vacunar(hepatitisB, paciente);
			sePudoVacunar = centro.vacunar(hepatitisB, paciente);
		} catch (NoCovidVaccineException | YaTuvoLaEnfermedadException | NoMoreVaccineException | VacunaYaAplicadaException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			throw new VacunaYaAplicadaException();
			
		}
		
		assertTrue(sePudoVacunar);
	}
	
	@org.junit.Test
	public void queSePuedaObtenerUnaListaDePacientesOrdenadaPorDniYNombre() {
		String nombrePaciente = "Cecilita", nombreCentro = "VacunARTE", nombreVacuna = "Vacuna contra la Hepatitis B",
				nombreAle = "Ale", nombreNico ="Nico", nombreChino = "Chino";
		Integer dniCecilita = 111, dniAle = 222, dniNico = 333, dniChino = 444;
		
		Centro centro = new Centro(nombreCentro);
		Vacuna hepatitisB = new HepatitisB(nombreVacuna);
		Paciente cecilita = new Paciente(dniCecilita, nombrePaciente);
		Paciente ale = new Paciente(dniAle, nombreAle);
		Paciente chino = new Paciente(dniChino, nombreChino);
		Paciente nico = new Paciente(dniNico, nombreNico);
		
		Boolean sePudoVacunar = false;
		try {
			sePudoVacunar = centro.vacunar(hepatitisB, ale);
			sePudoVacunar = centro.vacunar(hepatitisB, cecilita);
			sePudoVacunar = centro.vacunar(hepatitisB, chino);
			sePudoVacunar = centro.vacunar(hepatitisB, nico);
		} catch (NoCovidVaccineException | YaTuvoLaEnfermedadException | NoMoreVaccineException | VacunaYaAplicadaException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			
		}
		
		List<Paciente> pacientesOrdenados = new ArrayList<>(centro.getPacientesVacunados());
		
		assertEquals(pacientesOrdenados.get(0), cecilita);
		assertEquals(pacientesOrdenados.get(1), ale);
		assertEquals(pacientesOrdenados.get(2), nico);
		assertEquals(pacientesOrdenados.get(3), chino);
	}
	
	
}
