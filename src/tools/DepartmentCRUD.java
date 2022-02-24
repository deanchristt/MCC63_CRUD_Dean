package tools;

import daos.DepartmentDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import models.Department;

/**
 *
 * @author deanchristt
 */
public class DepartmentCRUD implements CRUD {

    DbConnection connection = new DbConnection();
    DepartmentDAO ddao = new DepartmentDAO(connection.getConncetion());
    Scanner scan = new Scanner(System.in);
    List<String> departmentListsStr = new ArrayList<>();
    List<Integer> departmentListsInt = new ArrayList<>();

    public void ListOfDepartments() {
        ddao.getAll().forEach((i) -> departmentListsStr.add(i.getDepartmentName()));
        ddao.getAll().forEach((i) -> departmentListsInt.add(i.getDepartmenId()));
    }

    @Override
    public void show() {
        TablePrinter table = new TablePrinter("Department ID", "Department Name", "Manager ID", "Location ID");
        for (Department department : ddao.getAll()) {
            table.addRow(String.valueOf(department.getDepartmenId()), department.getDepartmentName(),
                    String.valueOf(department.getManagerId()), String.valueOf(department.getLocationId()));
        }
        table.print();
    }

    @Override
    public void insert() {
        System.out.print("Input new ID: ");
        int departmentId_Insert = scan.nextInt();
        scan.nextLine();
        System.out.print("Input Department Name: ");
        String departmentname_Insert = scan.nextLine();
        System.out.print("Input Manager ID: ");
        int managerId_Insert = scan.nextInt();
        scan.nextLine();
        System.out.print("Input Location ID: ");
        int locationId_Insert = scan.nextInt();
        scan.nextLine();

        ListOfDepartments();
        boolean validasiInsert_DN = departmentListsStr.stream().anyMatch(departmentname_Insert::contains);
        boolean validasiInsert_Did = departmentListsInt.stream().anyMatch(n -> (n == departmentId_Insert));

        if (validasiInsert_Did == false) {
            if (validasiInsert_DN == false && validasiInsert_Did == false) {
                Department departmentInsert = new Department(departmentId_Insert, departmentname_Insert,
                        managerId_Insert, locationId_Insert);
                ddao.insert(departmentInsert);
                System.out.println("Berhasil diinput");
            } else if (validasiInsert_DN == true && validasiInsert_Did == false) {
                System.out.println("Department Name sudah ada dalam database. Input gagal");
            } else if (validasiInsert_DN == false && validasiInsert_Did == true) {
                System.out.println("Department ID sudah ada dalam database. Input gagal");
            } else if (validasiInsert_DN == true && validasiInsert_Did == true) {
                System.out.println("Department Name dan Department ID sudah ada dalam database. Input gagal");
            }
        } else {
            System.out.println("Department dengan ID dari " + departmentId_Insert + " sudah ada dalam database. Input gagal");
        }
    }

    @Override
    public void update() {
        System.out.print("Department ID: ");
        int departmentId_Update = scan.nextInt();
        scan.nextLine();
        System.out.println("Data:\n" + ddao.getById(departmentId_Update));
        System.out.print("Input Department Name: ");
        String departmentName_Update = scan.nextLine();
        System.out.print("Input Manager ID: ");
        int managerId_Update = scan.nextInt();
        scan.nextLine();
        System.out.print("Input Location ID: ");
        int locationId_Update = scan.nextInt();
        scan.nextLine();

        ListOfDepartments();
        boolean validasiUpdate_DN = departmentListsStr.stream().anyMatch(departmentName_Update::contains);

        if (validasiUpdate_DN == false || departmentName_Update.equals(ddao.getById(departmentId_Update).getDepartmentName())) {
            Department updateDepartment = new Department(departmentId_Update, departmentName_Update,
                    managerId_Update, locationId_Update);
            ddao.update(departmentId_Update, updateDepartment);
            System.out.println("Data berhasil diperbaharui");
        } else {
            System.out.println("Department sudah terdapat dalam database");
        }
    }

    @Override
    public void delete() {
        System.out.print("Input department ID: ");
        int departmentId_Delete = scan.nextInt();
        scan.nextLine();
        System.out.println("Data :\n" + ddao.getById(departmentId_Delete));
        System.out.print("input department name untuk dihapus: ");
        String departmenName_Delete = scan.nextLine();

        ListOfDepartments();
        boolean validasiDelete_N = departmentListsStr.stream().anyMatch(departmenName_Delete::contains);

        if (validasiDelete_N == true) {
            ddao.delete(departmentId_Delete);
            System.out.println("Data berhasil dihapus");
        } else {
            System.out.println("Data tidak terdapat dalam database");
        }
    }
}
