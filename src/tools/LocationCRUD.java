package tools;

import daos.LocationDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import models.Location;

/**
 *
 * @author deanchristt
 */
public class LocationCRUD implements CRUD {

    DbConnection connection = new DbConnection();
    LocationDAO ldao = new LocationDAO(connection.getConncetion());
    Scanner scan = new Scanner(System.in);
    List<String> locationListsStr = new ArrayList<>();
    List<Integer> locationListsInt = new ArrayList<>();

    public void ListOfLocations() {
        ldao.getAll().forEach((i) -> locationListsStr.add(i.getCountryId() + i.getStreetAddress()));
        ldao.getAll().forEach((i) -> locationListsInt.add(i.getLocationId()));
    }

    @Override
    public void show() {
        TablePrinter table = new TablePrinter("Location ID", "Street Address", "Postal Code", "City",
                "State Province", "Country ID");
        for (Location location : ldao.getAll()) {
            table.addRow(String.valueOf(location.getLocationId()), String.valueOf(location.getStreetAddress()),
                    String.valueOf(location.getPostalCode()), String.valueOf(location.getCity()),
                    String.valueOf(location.getStateProvince()), String.valueOf(location.getCountryId()));
        }
        table.print();
    }

    @Override
    public void insert() {
        System.out.print("Input ID: ");
        int locationId_Insert = scan.nextInt();
        scan.nextLine();
        System.out.print("Input Street Address: ");
        String streetAddress_Insert = scan.nextLine();
        System.out.print("Input Postal Code: ");
        String postalCode_Insert = scan.nextLine();
        System.out.print("Input City: ");
        String city_Insert = scan.nextLine();
        System.out.print("Input State Province: ");
        String stateProvince_Insert = scan.nextLine();
        System.out.print("Input Country ID: ");
        String countryId_Insert = scan.nextLine();
        ListOfLocations();

        boolean validasiInsert_Cid = locationListsStr.stream().anyMatch(countryId_Insert::contains);
        boolean validasiInsert_SA = locationListsStr.stream().anyMatch(streetAddress_Insert::contains);
        boolean validasiInsert_ID = locationListsInt.stream().anyMatch(n -> (n == locationId_Insert));

        if (validasiInsert_Cid == false) {
            System.out.println("country dengan country ID dari " + countryId_Insert + " tidak terdapat dalam database");
        } else {
            if (validasiInsert_SA == false && validasiInsert_ID == false) {
                Location locationInsert = new Location(locationId_Insert, streetAddress_Insert, postalCode_Insert,
                        city_Insert, stateProvince_Insert, countryId_Insert);
                ldao.insert(locationInsert);
                System.out.println("Berhasil diinput");
            } else if (validasiInsert_SA == true && validasiInsert_ID == false) {
                System.out.println("street address sudah ada dalam database. Input gagal");
            } else if (validasiInsert_SA == false && validasiInsert_ID == true) {
                System.out.println("location ID sudah ada dalam database. Input gagal");
            } else if (validasiInsert_SA == true && validasiInsert_ID == true) {
                System.out.println("Street address and location ID sudah ada dalam database. Input gagal");
            }
        }
    }

    @Override
    public void update() {
        System.out.print("Input ID: ");
        int locationId_Update = scan.nextInt();
        scan.nextLine();
        System.out.println("Data :\n" + ldao.getById(locationId_Update));
        System.out.print("Input Street Address: ");
        String streetAddress_Update = scan.nextLine();
        System.out.print("Input Postal Code: ");
        String postalCode_Update = scan.nextLine();
        System.out.print("Input City: ");
        String city_Update = scan.nextLine();
        System.out.print("Input State Province: ");
        String stateProvince_Update = scan.nextLine();
        System.out.print("Input Country ID: ");
        String countryId_Update = scan.nextLine();

        ListOfLocations();
        boolean validasiUpdate_Cid = locationListsStr.stream().anyMatch(countryId_Update::contains);
        boolean validasiUpdate_SA = locationListsStr.stream().anyMatch(streetAddress_Update::contains);

        if (validasiUpdate_Cid == false) {
            System.out.println("Country ID tidak terdapat dalam database");
        } else {
            if (validasiUpdate_SA == false || streetAddress_Update.equals(ldao.getById(locationId_Update).getStreetAddress())) {
                Location updateLocation = new Location(locationId_Update, streetAddress_Update,
                        postalCode_Update, city_Update, stateProvince_Update, countryId_Update);
                ldao.update(locationId_Update, updateLocation);
                System.out.println("Data berhasil diperbaharui");
            } else {
                System.out.println("Street address sudah terdapat dalam database");
            }
        }
    }

    @Override
    public void delete() {
        System.out.print("Inpus location ID: ");
        int locationId_Delete = scan.nextInt();
        scan.nextLine();
        System.out.println("Data :\n" + ldao.getById(locationId_Delete));
        System.out.print("Input location ID: ");
        int confirmDeletion = scan.nextInt();
        scan.nextLine();

        ListOfLocations();
        boolean validasiDelete_ID = locationListsInt.stream().anyMatch(n -> (n == locationId_Delete));

        if (validasiDelete_ID == true) {
            ldao.delete(confirmDeletion);
            System.out.println("Data berhasil dihapus");
        } else {
            System.out.println("Data tidak terdapat dalam database");
        }
    }
}
