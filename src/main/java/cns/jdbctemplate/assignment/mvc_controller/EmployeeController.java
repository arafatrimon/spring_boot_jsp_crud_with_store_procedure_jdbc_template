package cns.jdbctemplate.assignment.mvc_controller;

import cns.jdbctemplate.assignment.model.Employee;
import cns.jdbctemplate.assignment.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/form")
    public String employeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("pageTitle", "Register Employee");
        model.addAttribute("formAction", "/save");
        return "create-employee";
    }

    @PostMapping("/save")
    public String saveEmployee(@Valid @ModelAttribute("employee") Employee employee, BindingResult result, Model model) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getAllErrors().forEach((error) -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);


            });
            model.addAttribute("errors", errors);
            model.addAttribute("pageTitle", "Register Employee");
            model.addAttribute("formAction", "/save");
            return "create-employee";
        }

        employeeService.create(employee);
        return "redirect:form";
    }

    @GetMapping("/list")
    public String getAllEmployees(Model model, @RequestParam(required = false) String title) {

        List<Employee> employees = new ArrayList<>();

        if (title == null)
            employeeService.list().forEach(employees::add);
        else
            employeeService.findByTitleContaining(title).forEach(employees::add);


        model.addAttribute("employees", employees);
        return "list-employee";
    }

    @GetMapping("/edit/{id}")
    public String getEditPage(ModelMap modelMap, @PathVariable("id") long id, Model model) {
        model.addAttribute("pageTitle", "Update Employee");
        model.addAttribute("formAction", "/update");
        String page = null;
        try {
            Employee employee = employeeService.get(id).get();
            modelMap.put("employee", employee);

            String[] strArray = null;
            strArray = employee.getHobby().split(",");


            Map<String, Object> map = new HashMap<>();
            for (String value : strArray) {
                map.put(value, value);
            }
            model.addAttribute("hobbyMap", map);


            page = "create-employee";

        } catch (Exception e) {
            e.printStackTrace();
            page = "redirect:list";
        }
        return page;
    }

    @PostMapping("/update")
    public String updateEmployee(@ModelAttribute Employee employee, Model model) {
        employeeService.update(employee);
        List<Employee> employees = employeeService.list();
        model.addAttribute("employees", employees);
        return "list-employee";
    }

    @GetMapping("/delete/{id}")

    public String deleteEmployee(@PathVariable("id") long id, Model model) {
        try {
            employeeService.delete(id);
            List<Employee> employees = employeeService.list();
            model.addAttribute("employees", employees);
            return "list-employee";
        } catch (Exception e) {
            e.printStackTrace();
            return "list-employee";
        }
    }
}
