package tools;

import daos.CountryDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import models.Country;

/**
 *
 * @author deanchristt
 */
public class CountryCRUD implements CRUD {

    DbConnection connection = new DbConnection();
    CountryDAO cdao = new CountryDAO(connection.getConncetion());
    Scanner scan = new Scanner(System.in);
    List<String> countryLists = new ArrayList<>();

    public void ListOfCountries() {
        cdao.getAll().forEach((i) -> countryLists.add(i.getCountryId() + i.getCountryName()));
    }

    @Override
    public void show() {
        TablePrinter table = new TablePrinter("Country ID", "Country Name", "Region");
        for (Country country : cdao.getAll()) {
            table.addRow(String.valueOf(country.getCountryId()), country.getCountryName(), String.valueOf(country.getRegionId()));
        }
        table.print();
    }

    @Override
    public void insert() {
        System.out.print("Input Country ID: ");
        String CountryId_Insert = scan.nextLine();
        System.out.print("Input Country Name: ");
        String countryName_Insert = scan.nextLine();
        System.out.print("Input Region Name: ");
        int regionId_Insert = scan.nextInt();
        scan.nextLine();

        ListOfCountries();
        boolean validasiId = countryLists.stream().anyMatch(CountryId_Insert::contains);
        boolean validasiName = countryLists.stream().anyMatch(countryName_Insert::contains);

        if (validasiName == false && validasiId == false) {
            Country countryInsert = new Country(CountryId_Insert, countryName_Insert, regionId_Insert);
            cdao.insert(countryInsert);
            System.out.println("Data berhasil diinput");
        } else if (validasiName == true && validasiId == false) {
            System.out.println(countryName_Insert + " data sudah ada dalam database. Input data gagal");
        } else if (validasiName == false && validasiId == true) {
            System.out.println(CountryId_Insert + " data sudah ada dalam database. Input data gagal");
        } else if (validasiName == true && validasiId == true) {
            System.out.println("Data " + CountryId_Insert + " dan " + countryName_Insert + " sudah ada dalam database. Input data gagal");
        }
    }

    @Override
    public void update() {
        System.out.print("Input country id: ");
        String countryId_Update = scan.nextLine();
        System.out.println("country name sebelumnya: " + cdao.getById(countryId_Update).getCountryName());
        System.out.println("country id sebelumnya: " + cdao.getById(countryId_Update).getRegionId());
        System.out.print("Input country name: ");
        String countryName_Update = scan.nextLine();

        ListOfCountries();
        boolean validasi = countryLists.stream().anyMatch(countryName_Update::contains);

        if (validasi == false) {
            Country updateCountry = new Country(countryId_Update, countryName_Update, cdao.getById(countryId_Update).getRegionId());
            cdao.update(countryId_Update, updateCountry);
            System.out.println(countryName_Update + " berhasil diperbaharui");
        } else {
            System.out.println(countryName_Update + " sudah ada data");
        }
    }

    @Override
    public void delete() {
        System.out.print("Input country id: ");
        String countryId_Delete = scan.nextLine();
        System.out.print("Hapus country name: ");
        String countryName_Delete = scan.nextLine();

        ListOfCountries();
        boolean validasi = countryLists.stream().anyMatch(countryName_Delete::contains);

        if ((validasi == true)) {
            if (countryName_Delete.equals(cdao.getById(countryId_Delete).getCountryName())) {
                cdao.delete(countryId_Delete);
                System.out.println(countryName_Delete + " berhasil dihapus");
            } else {
                System.out.println("Region ID dan Name tidak sesuai");
            }
        } else if ((validasi == false)) {
            System.out.println(countryName_Delete + " tidak terdapat dalam database");
        }
    }
}
