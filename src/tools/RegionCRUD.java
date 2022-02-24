package tools;

import daos.RegionDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import models.Region;

/**
 *
 * @author deanchristt
 */
public class RegionCRUD implements CRUD {

    Scanner scan = new Scanner(System.in);
    DbConnection connection = new DbConnection();
    RegionDAO rdao = new RegionDAO(connection.getConncetion());
    List<String> regionLists = new ArrayList<>();

    public void ListOfRegions() {
        rdao.getAll().forEach((i) -> regionLists.add(i.getRegionName()));
    }

    @Override
    public void show() {
        TablePrinter table = new TablePrinter("Region ID", "Region Name");
        for (Region region : rdao.getAll()) {
            table.addRow(String.valueOf(region.getRegionId()), region.getRegionName());
        }
        table.print();
    }

    @Override
    public void insert() {
        System.out.print("Input Region ID: ");
        int id_Insert = scan.nextInt();
        scan.nextLine();
        System.out.print("Input region name: ");
        String regionName_Insert = scan.nextLine();

        ListOfRegions();
        boolean validasi = regionLists.stream().anyMatch(regionName_Insert::contains);

        if (validasi == false) {
            Region regionInsert = new Region(id_Insert, regionName_Insert);
            rdao.insert(regionInsert);
            System.out.println(regionName_Insert + " berhasil diinput");
        } else {
            System.out.println(regionName_Insert + " sudah ada data");
        }
    }

    @Override
    public void update() {
        System.out.print("Input Region ID: ");
        int id_Update = scan.nextInt();
        System.out.println("Nama sebelumnya: " + rdao.getById(id_Update).getRegionName());
        System.out.print("Update region name: ");
        String regionName_Update = scan.nextLine();

        ListOfRegions();
        boolean validasi = regionLists.stream().anyMatch(regionName_Update::contains);

        if (validasi == false || regionName_Update.equals(rdao.getById(id_Update).getRegionName())) {
            Region updateRegion = new Region(id_Update, regionName_Update);
            rdao.update(id_Update, updateRegion);
            System.out.println(regionName_Update + " berhasil diperbaharui");
        } else {
            System.out.println(regionName_Update + " sudah ada data");
        }
    }

    @Override
    public void delete() {
        System.out.print("Input Region ID: ");
        int id_Delete = scan.nextInt();
        scan.nextLine();
        System.out.print("Hapus region name: ");
        String regionName_Delete = scan.nextLine();

        ListOfRegions();
        boolean validasi = regionLists.stream().anyMatch(regionName_Delete::contains);

        if ((validasi == true)) {
            if (regionName_Delete.equals(regionLists.get(id_Delete - 1))) {
                rdao.delete(id_Delete);
                System.out.println(regionName_Delete + " berhasil dihapus");
            } else {
                System.out.println("Region Name dan ID tidak sesuai");
            }
        } else if ((validasi == false)) {
            System.out.println(regionName_Delete + " tidak terdapat didatabase");
        }
    }

}
