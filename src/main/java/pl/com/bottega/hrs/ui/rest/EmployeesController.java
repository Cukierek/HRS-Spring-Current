package pl.com.bottega.hrs.ui.rest;

import org.springframework.web.bind.annotation.*;
import pl.com.bottega.hrs.application.*;
import pl.com.bottega.hrs.model.commands.ChangeEmployeeTitleCommand;
import pl.com.bottega.hrs.model.commands.ChangeSalaryCommand;

@RestController
public class EmployeesController {

    private EmployeeFinder employeeFinder;
    private ChangeSalaryHandler changeSalaryHandler;
    private ChangeEmployeeTitleHandler changeEmployeeTitleHandler;

    public EmployeesController(EmployeeFinder employeeFinder, ChangeSalaryHandler changeSalaryHandler, ChangeEmployeeTitleHandler changeEmployeeTitleHandler) {
        this.employeeFinder = employeeFinder;
        this.changeSalaryHandler = changeSalaryHandler;
        this.changeEmployeeTitleHandler = changeEmployeeTitleHandler;
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
    public DetailedEmployeeDto changeSalary(@PathVariable Integer empNo,
                             @RequestBody ChangeSalaryCommand cmd) {
       cmd.setEmpNo(empNo);
       changeSalaryHandler.handle(cmd);
       return employeeFinder.getEmployeeDetails(empNo);
    }

    @PutMapping("/employees/{empNo}/title")
    public DetailedEmployeeDto changeTitle(@PathVariable Integer empNo,
                                            @RequestBody ChangeEmployeeTitleCommand cmd) {
        cmd.setEmpNo(empNo);
        changeEmployeeTitleHandler.handle(cmd);
        return employeeFinder.getEmployeeDetails(empNo);
    }

}
