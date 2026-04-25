package com.kosowski.cruddemo.controller;

import com.kosowski.cruddemo.entity.Employee;
import com.kosowski.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.json.JsonMapper;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeRestController {

    private final EmployeeService employeeService;
    private final JsonMapper jsonMapper;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService, JsonMapper jsonMapper){
        this.employeeService = employeeService;
        // Json mapper is managed by spring, so we don't need to create it manually
        this.jsonMapper = jsonMapper;
    }

    @GetMapping("/")
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id){
        Employee theEmployee = employeeService.findById(id);

        if(theEmployee == null){
            throw new RuntimeException("Employee not found for id - " + id);
        }

        return theEmployee;
    }

    @DeleteMapping("/{id}")
    public void deleteEmployeeById(@PathVariable Long id){
        Employee theEmployee = employeeService.findById(id);

        if(theEmployee == null){
            throw new RuntimeException("Employee not found for id - " + id);
        }

        employeeService.deleteById(id);
    }

    @PostMapping("/")
    public Employee addEmployee(@RequestBody Employee employee){
        return employeeService.save(employee);
    }

    @PutMapping("/")
    public Employee updateEmployee(@RequestBody Employee employee){
        return employeeService.save(employee);
    }

    @PatchMapping("/{id}")
    // There is Map<String, Object> as a parameter because we don't know what fields will be updated,
    // for exmaple the value may be an integer, and we don't want to send it as a string '1';
    public Employee patchEmployee(@PathVariable Long id, @RequestBody Map<String, Object> patchPayload){
        Employee employee = employeeService.findById(id);

        if(employee == null){
            throw new RuntimeException("Employee not found for id - " + id);
        }

        if(patchPayload.containsKey("id")){
            throw new RuntimeException("Id field cannot be updated");
        }
        // It takes an existing Java object, find key in payload which matches the field name, and update the value
        Employee updatedEmployee = jsonMapper.updateValue(employee, patchPayload);
        return employeeService.save(updatedEmployee);
    }

}
