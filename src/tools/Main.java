package tools;

import java.util.Scanner;

/**
 *
 * @author deanchristt
 */
public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        CRUD region = new RegionCRUD();
        CRUD country = new CountryCRUD();
        CRUD location = new LocationCRUD();
        CRUD department = new DepartmentCRUD();
        CRUD job = new JobCRUD();
        CRUD employee = new EmployeeCRUD();
        int pilih = -1;
        String string = "SELAMAT DATANG DI HR DATABASE CRUD APPLICATION\n"
                + "Tekan 1 untuk melihat daftar tabel dan 0 untuk keluar program.";
        System.out.println(string);
        while (pilih != 7) {
            pilih = scan.nextInt();
            scan.nextLine();
            switch (pilih) {
                case 0:
                    System.out.print("Daftar Tabel:\n1. Region\n2. Country\n3. Location\n4. Department\n5. Job\n6. Employee"
                            + "\n\"Input database yang ingin diakses ? \"\n");
                    break;
                case 1:
                    System.out.print("Tabel Region\n11. Show\n12. Insert\n13. Update\n14. Delete"
                            + "\n\"Input nomor untuk akses fitur dan input \"1\" untuk melihat menu dalam Tabel Region \"\n");
                    System.out.print("Input \"0\" untuk melihat daftar tabel\n");
                    break;
                case 2:
                    System.out.print("Tabel Country\n21. Show\n22. Insert\n23. Update\n24. Delete"
                            + "\n\"Input nomor untuk akses fitur dan input \"2\" untuk melihat menu dalam Tabel Country \"\n");
                    System.out.print("Input \"0\" untuk melihat daftar tabel\n");
                    break;
                case 3:
                    System.out.print("Tabel Location\n31. Show\n32. Insert\n33. Update\n34. Delete"
                            + "\n\"Input nomor untuk akses fitur dan input \"3\" untuk melihat menu dalam Tabel Location \"\n");
                    System.out.print("Input \"0\" untuk melihat daftar tabel\n");
                    break;
                case 4:
                    System.out.print("Tabel Department\n41. Show\n42. Insert\n43. Update\n44. Delete"
                            + "\n\"Input nomor untuk akses fitur dan input \"4\" untuk melihat menu dalam Tabel Department \"\n");
                    System.out.print("Input \"0\" untuk melihat daftar tabel\n");
                    break;
                case 5:
                    System.out.print("Tabel Job\n51. Show\n52. Insert\n53. Update\n54. Delete"
                            + "\n\"Input nomor untuk akses fitur dan input \"5\" untuk melihat menu dalam Tabel Job \"\n");
                    System.out.print("Input \"0\" untuk melihat daftar tabel\n");
                    break;
                case 6:
                    System.out.print("Tabel Employee\n61. Show\n62. Insert\n63. Update\n64. Delete"
                            + "\n\"Input nomor untuk akses fitur dan input \"6\" untuk melihat menu dalam Tabel Emmployee \"\n");
                    System.out.print("Input \"0\" untuk melihat daftar tabel\n");
                    break;
                case 07:
                    System.out.print("Terimakasih telah menggunakan program!\n");
                    break;

                // Tabel Region 
                case 11:
                    region.show();
                    break;
                case 12:
                    region.insert();
                    break;
                case 13:
                    region.update();
                    break;
                case 14:
                    region.delete();
                    break;

                // Tabel Country  
                case 21:
                    country.show();
                    break;
                case 22:
                    country.insert();
                    break;
                case 23:
                    country.update();
                    break;
                case 24:
                    country.delete();
                    break;

                // Tabel Location
                case 31:
                    location.show();
                    break;
                case 32:
                    location.insert();
                    break;
                case 33:
                    location.update();
                    break;
                case 34:
                    location.delete();
                    break;

                // department table
                case 41:
                    department.show();
                    break;
                case 42:
                    department.insert();
                    break;
                case 43:
                    department.update();
                    break;
                case 44:
                    department.delete();
                    break;

                // Table Job
                case 51:
                    job.show();
                    break;
                case 52:
                    job.insert();
                    break;
                case 53:
                    job.update();
                    break;
                case 54:
                    job.delete();
                    break;

                // Table Employee
                case 61:
                    employee.show();
                    break;
                case 62:
                    employee.insert();
                    break;
                case 63:
                    employee.update();
                    break;
                case 64:
                    employee.delete();
                    break;

                default:
                    System.out.print("INPUT TIDAK SESUAI.\n"
                            + string + "\n");
            }
        }
    }
}
