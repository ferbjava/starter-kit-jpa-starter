package com.capgemini.Utils;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import com.capgemini.types.CarTO;
import com.capgemini.types.CarTO.CarTOBuilder;
import com.capgemini.types.ClientTO;
import com.capgemini.types.ClientTO.ClientTOBuilder;
import com.capgemini.types.DepartmentTO;
import com.capgemini.types.DepartmentTO.DepartmentTOBuilder;
import com.capgemini.types.EmployeeTO;
import com.capgemini.types.EmployeeTO.EmployeeTOBuilder;
import com.capgemini.types.PositionTO;
import com.capgemini.types.PositionTO.PositionTOBuilder;

/**
 * Support class with helpful data to test application 'starter-kit-jpa-starter'
 * <p>Provides following data:<p>
 * <p>- 3 DepartmentTO,
 * <p>- 3 PositionTO,
 * <p>- 12 EMployeeTO, 
 * <p>- 10 ClientTO,
 * <p>- 10 CarTO,
 * <p>and getters to each TO's by 'id'.
 * @author MKOTECKI
 *
 */
public class InsertData {

	private List<DepartmentTO> departments = new ArrayList<>();
	private List<PositionTO> positions = new ArrayList<>();
	private List<EmployeeTO> employee = new ArrayList<>();
	private List<ClientTO> clients = new ArrayList<>();
	private List<CarTO> cars = new ArrayList<>();

	public InsertData() {
		fillDepartmentList();
		fillPositionList();
		fillEmployeeList();
		fillClientList();
		fillCarList();
	}

	public DepartmentTO getDepById(int id) {
		return departments.get(id);
	}

	public PositionTO getPosById(int id) {
		return positions.get(id);
	}

	public EmployeeTO getEmplById(int id) {
		return employee.get(id);
	}

	public ClientTO getClientById(int id) {
		return clients.get(id);
	}

	public CarTO getCarById(int id) {
		return cars.get(id);
	}

	private void fillDepartmentList() {
		this.departments.add(new DepartmentTOBuilder().withAdress("Poznan, ul. Glowna")
				.withEmail("poznan@car_rental.pl").withPhoneNumber(700700701).build());
		this.departments.add(new DepartmentTOBuilder().withAdress("Warszawa, ul. Marszalkowska")
				.withEmail("warszawa@car_rental.pl").withPhoneNumber(700700702).build());
		this.departments.add(new DepartmentTOBuilder().withAdress("Krakow, ul. Bracka")
				.withEmail("krakow@car_rental.pl").withPhoneNumber(700700703).build());
	}

	private void fillPositionList() {
		this.positions.add(new PositionTOBuilder().withPosition("manager").build());
		this.positions.add(new PositionTOBuilder().withPosition("seller").build());
		this.positions.add(new PositionTOBuilder().withPosition("accountant").build());
	}

	private void fillEmployeeList() {
		this.employee.add(new EmployeeTOBuilder().withFirstName("Jan").withLastName("Kowalski")
				.withDateBirth(new GregorianCalendar(1986, 0, 1)).build());
		this.employee.add(new EmployeeTOBuilder().withFirstName("Andrzej").withLastName("Nowak")
				.withDateBirth(new GregorianCalendar(1997, 1, 2)).build());
		this.employee.add(new EmployeeTOBuilder().withFirstName("Jakub").withLastName("Wisniewski")
				.withDateBirth(new GregorianCalendar(1970, 2, 3)).build());
		this.employee.add(new EmployeeTOBuilder().withFirstName("John").withLastName("oFarrell")
				.withDateBirth(new GregorianCalendar(1995, 3, 4)).build());
		this.employee.add(new EmployeeTOBuilder().withFirstName("Anna").withLastName("Nijaka")
				.withDateBirth(new GregorianCalendar(1990, 4, 5)).build());
		this.employee.add(new EmployeeTOBuilder().withFirstName("Jacek").withLastName("Krzynowek")
				.withDateBirth(new GregorianCalendar(1970, 5, 6)).build());
		this.employee.add(new EmployeeTOBuilder().withFirstName("Jarogniew").withLastName("Koniecpolski")
				.withDateBirth(new GregorianCalendar(1991, 6, 7)).build());
		this.employee.add(new EmployeeTOBuilder().withFirstName("Grzymislaw").withLastName("Kloc")
				.withDateBirth(new GregorianCalendar(1988, 7, 8)).build());
		this.employee.add(new EmployeeTOBuilder().withFirstName("Swiatowid").withLastName("Zolkiewski")
				.withDateBirth(new GregorianCalendar(1963, 0, 11)).build());
		this.employee.add(new EmployeeTOBuilder().withFirstName("Hildegarda").withLastName("Pedziwiatr")
				.withDateBirth(new GregorianCalendar(1987, 1, 12)).build());
		this.employee.add(new EmployeeTOBuilder().withFirstName("Jaxa").withLastName("Kopaniecki")
				.withDateBirth(new GregorianCalendar(1983, 2, 13)).build());
		this.employee.add(new EmployeeTOBuilder().withFirstName("Wilkomir").withLastName("Jomsborski")
				.withDateBirth(new GregorianCalendar(1994, 3, 14)).build());
	}

	private void fillClientList() {
		this.clients.add(new ClientTOBuilder().withFirstName("Joanny").withLastName("Kuphal")
				.withAdress("1983 Keeling Estate\nAlland, AK 59190").withEmail("pwatsica@gmail.com")
				.withBirthDate(new GregorianCalendar(1980, 3, 14)).withPhoneNumber(589819894)
				.withCreditCard("7115232476757633").build());
	}

	private void fillCarList() {
		this.cars.add(new CarTOBuilder().withType("combii").withBrand("Skoda").withModel("Octavia").
				withProductionYear(2016).withColor("red").withEngineCapacity(1968).withEnginePower(150).withMileage(215000).build());
		this.cars.add(new CarTOBuilder().withType("compact").withBrand("VW").withModel("Golf").
				withProductionYear(2010).withColor("blue").withEngineCapacity(1999).withEnginePower(310).withMileage(154021).build());
		this.cars.add(new CarTOBuilder().withType("combii").withBrand("Audi").withModel("A6").
				withProductionYear(2013).withColor("black").withEngineCapacity(2976).withEnginePower(245).withMileage(93000).build());
		this.cars.add(new CarTOBuilder().withType("combii").withBrand("Ford").withModel("Mondeo").
				withProductionYear(2009).withColor("grey").withEngineCapacity(2000).withEnginePower(140).withMileage(220000).build());
		this.cars.add(new CarTOBuilder().withType("minivan").withBrand("Opel").withModel("Meriva").
				withProductionYear(2005).withColor("silver").withEngineCapacity(1686).withEnginePower(101).withMileage(172000).build());
		this.cars.add(new CarTOBuilder().withType("compact").withBrand("Ford").withModel("Focus").
				withProductionYear(2016).withColor("black").withEngineCapacity(2000).withEnginePower(300).withMileage(26000).build());
		this.cars.add(new CarTOBuilder().withType("suv").withBrand("Lexus").withModel("NX").
				withProductionYear(2016).withColor("brown").withEngineCapacity(1998).withEnginePower(1238).withMileage(29000).build());
		this.cars.add(new CarTOBuilder().withType("compact").withBrand("Skoda").withModel("Fabia").
				withProductionYear(2013).withColor("blue").withEngineCapacity(1600).withEnginePower(75).withMileage(100000).build());
		this.cars.add(new CarTOBuilder().withType("suv").withBrand("Audi").withModel("Q5").
				withProductionYear(2015).withColor("black").withEngineCapacity(1968).withEnginePower(190).withMileage(96000).build());
		this.cars.add(new CarTOBuilder().withType("suv").withBrand("Jeep").withModel("Grand Cherokee").
				withProductionYear(2003).withColor("black").withEngineCapacity(2700).withEnginePower(170).withMileage(270000).build());
	}

}
