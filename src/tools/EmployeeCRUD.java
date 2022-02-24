package tools;

import daos.EmployeeDAO;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import models.Employee;

/**
 *
 * @author deanchristt
 */
public class EmployeeCRUD implements CRUD {

    DbConnection connection = new DbConnection();
    EmployeeDAO edao = new EmployeeDAO(connection.getConncetion());
    Scanner scan = new Scanner(System.in);
    List<String> employeeListsStr = new ArrayList<>();
    List<Integer> employeeListsInt = new ArrayList<>();

    public void ListOfEmployees() {
        edao.getAll().forEach((i) -> employeeListsStr.add(i.getEmail() + i.getPhoneNumber()));
        edao.getAll().forEach((i) -> employeeListsInt.add(i.getEmployeeId()));
    }

    @Override
    public void show() {
        TablePrinter table = new TablePrinter("Employee ID", "First Name", "Last Name", "Email", "Phone Number", "Hire Date",
                "Job", "Salary", "Comission PCT", "Manager", "Department");
        for (Employee employee : edao.getAll()) {
            table.addRow(String.valueOf(employee.getEmployeeId()), employee.getFirstName(), employee.getLastName(), employee.getEmail(),
                    String.valueOf(employee.getPhoneNumber()), String.valueOf(employee.getHireDate()), String.valueOf(employee.getJobTitle()),
                    String.valueOf(employee.getSalary()), String.valueOf(employee.getCommissionPCT()), String.valueOf(employee.getManagerId()),
                    String.valueOf(employee.getDepartmentId()));
        }
        table.print();
    }

    @Override
    public void insert() {
        System.out.print("Input Employee ID: ");
        int employeeId_Insert = scan.nextInt();
        scan.nextLine();
        System.out.print("Input first name: ");
        String firstName_Insert = scan.nextLine();
        System.out.print("Input last name: ");
        String lastName_Insert = scan.nextLine();
        System.out.print("Input emai: ");
        String email_Insert = scan.nextLine();
        System.out.print("Input phone number: ");
        String phoneNumber_Insert = scan.nextLine();
        System.out.print("Input hire date(yyyy-MM-dd): ");
        String hireDate_Insert = scan.nextLine();
        Date hireDateSQLFormat = Date.valueOf(hireDate_Insert);
        System.out.print("Input Job ID: ");
        String jobTitle_Insert = scan.nextLine();
        System.out.print("Input salary: ");
        double salary_Insert = scan.nextDouble();
        scan.nextLine();
        System.out.print("Input commissionPCT: ");
        double commissionPCT_Insert = scan.nextDouble();
        scan.nextLine();
        System.out.print("Input manager ID: ");
        int managerId_Insert = scan.nextInt();
        scan.nextLine();
        System.out.print("Insert Department ID: ");
        int departmentId_Insert = scan.nextInt();
        scan.nextLine();

        ListOfEmployees();
        boolean validasiStrEmail = employeeListsStr.stream().anyMatch(email_Insert::contains);
        boolean validasiStrPhone = employeeListsStr.stream().anyMatch(phoneNumber_Insert::contains);
        boolean validasiIntEmpId = employeeListsInt.stream().anyMatch(n -> (n == employeeId_Insert));

        if (validasiStrEmail == false && validasiStrPhone == false && validasiIntEmpId == false) {
            Employee employeeInsert = new Employee(employeeId_Insert, firstName_Insert, lastName_Insert,
                    email_Insert, phoneNumber_Insert, hireDateSQLFormat, jobTitle_Insert, salary_Insert,
                    commissionPCT_Insert, managerId_Insert, departmentId_Insert);
            edao.insert(employeeInsert);
            System.out.println("Data berhasil ditambhakan");
        } else {
            System.out.println("Employee ID, Email, atau Phone Number sudah ada dalam database");
        }
    }

    @Override
    public void update() {
        System.out.print("Job ID: ");
        int employeeId_Update = scan.nextInt();
        scan.nextLine();

        ListOfEmployees();
        boolean validasiEmployeeID = employeeListsInt.stream().anyMatch(n -> ((int) n == employeeId_Update));

        if (validasiEmployeeID == true) {
            System.out.println("Data:\n" + edao.getById(employeeId_Update));
            System.out.print("Input Email: ");
            String email_Update = scan.nextLine();
            System.out.print("Input Phone Number: ");
            String phoneNumber_Update = scan.nextLine();
            System.out.print("Input Salary: ");
            double salary_Update = scan.nextDouble();
            scan.nextLine();
            System.out.print("Input Comission PCT: ");
            double comissionPCT_Update = scan.nextDouble();
            scan.nextLine();

            Employee updateEmployee = new Employee(employeeId_Update,
                    edao.getById(employeeId_Update).getFirstName(), edao.getById(employeeId_Update).getLastName(),
                    email_Update, phoneNumber_Update, edao.getById(employeeId_Update).getHireDate(),
                    edao.getById(employeeId_Update).getJobTitle(), salary_Update, comissionPCT_Update,
                    edao.getById(employeeId_Update).getManagerId(), edao.getById(employeeId_Update).getDepartmentId());
            edao.update(employeeId_Update, updateEmployee);
            System.out.println("Data berhasil diperbaharui");
        } else {
            System.out.println("Data tidak terdapat dalam database");
        }
    }

    @Override
    public void delete() {
        System.out.print("Input Employee ID: ");
        int employeeId_Delete = scan.nextInt();
        scan.nextLine();

        ListOfEmployees();
        boolean validasiDelete = employeeListsInt.stream().anyMatch(n -> (n == employeeId_Delete));

        if (validasiDelete == true) {
            System.out.println("Data :\n" + edao.getById(employeeId_Delete));
            System.out.print("Input job_id : ");
            int confirmDelete = scan.nextInt();
            scan.nextLine();
            edao.delete(confirmDelete);
            System.out.println("Data berhasil dihapus");
        } else {
            System.out.println("Data tidak terdapat dalam database");
        }
    }

}
