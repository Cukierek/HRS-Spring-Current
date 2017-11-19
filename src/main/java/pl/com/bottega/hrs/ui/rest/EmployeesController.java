package pl.com.bottega.hrs.ui.rest;

import org.springframework.web.bind.annotation.*;
import pl.com.bottega.hrs.application.*;
import pl.com.bottega.hrs.model.commands.*;

@RestController
public class EmployeesController {

    private EmployeeFinder employeeFinder;
    private ChangeSalaryHandler changeSalaryHandler;
    private ChangeEmployeeTitleHandler changeEmployeeTitleHandler;
    private DepartmentAssignmentHandler departmentAssignmentHandler;
    private UnassignDepartmentHandler unassignDepartmentHandler;
    private ChangeEmployeeProfileHandler changeEmployeeProfileHandler;

    public EmployeesController(EmployeeFinder employeeFinder, ChangeSalaryHandler changeSalaryHandler,
                               ChangeEmployeeTitleHandler changeEmployeeTitleHandler, DepartmentAssignmentHandler departmentAssignmentHandler, UnassignDepartmentHandler unassignDepartmentHandler, ChangeEmployeeProfileHandler changeEmployeeProfileHandler){
        this.employeeFinder = employeeFinder;
        this.changeSalaryHandler = changeSalaryHandler;
        this.changeEmployeeTitleHandler = changeEmployeeTitleHandler;
        this.departmentAssignmentHandler = departmentAssignmentHandler;
        this.unassignDepartmentHandler = unassignDepartmentHandler;
        this.changeEmployeeProfileHandler = changeEmployeeProfileHandler;
    }

    @GetMapping("/employees/{empNo}")
    public DetailedEmployeeDto get(@PathVariable Integer empNo){

        return employeeFinder.getEmployeeDetails(empNo);
    }

    @GetMapping("/employees")
    public EmployeeSearchResults get(EmployeeSearchCriteria criteria){

        return employeeFinder.search(criteria);
    }

    @PutMapping("/employees/{empNo}/salary")
    public DetailedEmployeeDto changeSalary(@PathVariable Integer empNo, @RequestBody ChangeSalaryCommand cmd) {

        cmd.setEmpNo(empNo);
        changeSalaryHandler.handle(cmd);
        return employeeFinder.getEmployeeDetails(empNo);
    }

    @PutMapping("/employees/{empNo}/title")
    public DetailedEmployeeDto changeTitle(@PathVariable Integer empNo, @RequestBody ChangeEmployeeTitleCommand cmd) {

        cmd.setEmpNo(empNo);
        changeEmployeeTitleHandler.handle(cmd);
        return employeeFinder.getEmployeeDetails(empNo);
    }

    @PutMapping("/employees/{empNo}/department")
    public DetailedEmployeeDto assignToDepartment(@PathVariable Integer empNo,
                                                  @RequestBody AssignDepartmentToEmployeeCommand cmd) {

        cmd.setEmpNo(empNo);
        departmentAssignmentHandler.handle(cmd);
        return employeeFinder.getEmployeeDetails(empNo);
    }

    @DeleteMapping("/employees/{empNo}/department")
    public DetailedEmployeeDto unassignFromDepartment(@PathVariable Integer empNo,
                                                      @RequestBody UnassignDepartmentCommand cmd) {

        cmd.setEmpNo(empNo);
        unassignDepartmentHandler.handle(cmd);
        return employeeFinder.getEmployeeDetails(empNo);
    }

    @PutMapping("/employees/{empNo}")
    public DetailedEmployeeDto updateProfile(@PathVariable Integer empNo, @RequestBody ChangeEmployeeProfileCommand cmd) {
        cmd.setEmpNo(empNo);
        changeEmployeeProfileHandler.handle(cmd);
        return employeeFinder.getEmployeeDetails(empNo);
    }

}