package tools;

import daos.JobDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import models.Job;

/**
 *
 * @author deanchristt
 */
public class JobCRUD implements CRUD {

    DbConnection connection = new DbConnection();
    JobDAO jdao = new JobDAO(connection.getConncetion());
    Scanner scan = new Scanner(System.in);
    List<String> jobLists = new ArrayList<>();

    public void ListOfJobs() {
        jdao.getAll().forEach((i) -> jobLists.add(i.getJobId() + i.getJobTitle()));
    }

    @Override
    public void show() {
        TablePrinter table = new TablePrinter("Job ID", "Job Title", "Min Salary", "Max Salary");
        for (Job job : jdao.getAll()) {
            table.addRow(String.valueOf(job.getJobId()), job.getJobTitle(),
                    String.valueOf(job.getMinSalary()), String.valueOf(job.getMaxSalary()));
        }
        table.print();
    }

    @Override
    public void insert() {
        System.out.print("Input Job ID: ");
        String jobId_Insert = scan.nextLine();
        System.out.print("Input Job Title: ");
        String jobTitle_Insert = scan.nextLine();
        System.out.print("Input Min Salary: ");
        double minSalary_Insert = scan.nextDouble();
        scan.nextLine();
        System.out.print("Input Max Salary: ");
        double maxSalary_Insert = scan.nextDouble();
        scan.nextLine();

        ListOfJobs();
        boolean validasiInsert_JID = jobLists.stream().anyMatch(jobId_Insert::contains);
        boolean validasiInsert_JT = jobLists.stream().anyMatch(jobTitle_Insert::contains);

        if (validasiInsert_JID == false && validasiInsert_JT == false) {
            Job jobInsert = new Job(jobId_Insert, jobTitle_Insert, minSalary_Insert, maxSalary_Insert);
            jdao.insert(jobInsert);
            System.out.println("Data berhasil diinput");
        } else if (validasiInsert_JID == true && validasiInsert_JT == false) {
            System.out.println("Department Name sudah terdapat dalam database. Input gagal");
        } else if (validasiInsert_JID == false && validasiInsert_JT == true) {
            System.out.println("Department ID sudah terdapat dalam database. Input gagal");
        } else if (validasiInsert_JID == true && validasiInsert_JT == true) {
            System.out.println("Department Name and Department ID sudah terdapat dalam database. Input gagal");
        }
    }

    @Override
    public void update() {

        System.out.print("Job ID: ");
        String jobId_beforeUpdate = scan.nextLine();

        ListOfJobs();
        boolean validasiAwalJobID = jobLists.stream().anyMatch(jobId_beforeUpdate::contains);

        if (validasiAwalJobID == true) {
            System.out.println("Data:\n" + jdao.getById(jobId_beforeUpdate));
            System.out.print("Input Min Salary: ");
            double minSalary_Update = scan.nextDouble();
            scan.nextLine();
            System.out.print("Input Max Salary: ");
            double maxSalary_Update = scan.nextDouble();
            scan.nextLine();

            Job updateJob = new Job(jobId_beforeUpdate, jdao.getById(jobId_beforeUpdate).getJobTitle(),
                    minSalary_Update, maxSalary_Update);
            jdao.update(jobId_beforeUpdate, updateJob);

        } else {
            System.out.println("Data tidak terdapat didatabase");
        }
    }

    @Override
    public void delete() {
        System.out.print("Select Job ID: ");
        String jobId_Delete = scan.nextLine();
        System.out.println("Data:\n" + jdao.getById(jobId_Delete));
        System.out.print("Input Job Title: ");
        String jobTitle_Delete = scan.nextLine();

        ListOfJobs();
        boolean validasiDelete = jobLists.stream().anyMatch(jobTitle_Delete::contains);

        if (validasiDelete == true) {
            jdao.delete(jobId_Delete);
            System.out.println("Data berhasil dihapus");
        } else {
            System.out.println("Data tidak terdapat dalam database");
        }
    }
}
