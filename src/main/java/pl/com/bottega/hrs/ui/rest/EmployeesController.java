package pl.com.bottega.hrs.ui.rest;

import org.springframework.web.bind.annotation.*;
import pl.com.bottega.hrs.application.*;
import pl.com.bottega.hrs.model.commands.ChangeSalaryCommand;

@RestController
public class EmployeesController {

	private EmployeeFinder employeeFinder;
	private ChangeSalaryHandler changeSalaryHandler;

	public EmployeesController(EmployeeFinder employeeFinder, ChangeSalaryHandler changeSalaryHandler) {
		this.employeeFinder = employeeFinder;
		this.changeSalaryHandler = changeSalaryHandler;
	}

	@GetMapping("/employees/{empNo}")
	public DetailedEmployeeDto get(@PathVariable Integer empNo) {
		return employeeFinder.getEmployeeDetails(empNo);
	}

	@GetMapping("/employees")
	public EmployeeSearchResults get(EmployeeSearchCriteria criteria) {
		return employeeFinder.search(criteria);
	}

	@PutMapping("/employees/{empNo}/salary")
	public void changeSalary(@PathVariable Integer empNo, @RequestBody ChangeSalaryCommand cmd) {
		cmd.setEmpNo(empNo);
		changeSalaryHandler.handle(cmd);
	}
}
