package Work;

import android.media.Image;

import java.util.ArrayList;
import java.util.Date;

import Logic.ConnectionWebService;
import People.Employee;
import People.Employer;

public class Job{
    private int id;
    private Employer employer;
    private ArrayList <Employee> employees;
    private ArrayList <Employee> applicants;
    private String mode;
    private String state;
    private String location;
    private String title;
    private String description;
    private float jobCost;
    private Image photo;
    private Date datePosted;
    private Date dateStart;
    private Date dateEnd;
    private Date datePostEnd;
    private byte availablePercentage;
    private double[] address;
    private String[] tags;
    private String[] categories;
    private ArrayList<ParentComment> comments;


    //Constructor


    public Job(int id, Employer employer, ArrayList<Employee> employees, ArrayList<Employee> applicants, String mode, String state, String location, String title, String description, float jobCost, Image photo, Date datePosted, Date dateStart, Date dateEnd, Date datePostEnd, byte availablePercentage, double[] address, String[] tags, String[] categories, ArrayList<ParentComment> comments) {
        this.id = id;
        this.employer = employer;
        this.employees = employees;
        this.applicants = applicants;
        this.mode = mode;
        this.state = state;
        this.location = location;
        this.title = title;
        this.description = description;
        this.jobCost = jobCost;
        this.photo = photo;
        this.datePosted = datePosted;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.datePostEnd = datePostEnd;
        this.availablePercentage = availablePercentage;
        this.address = address;
        this.tags = tags;
        this.categories = categories;
        this.comments = comments;
    }

    public Job(Employer employer, String mode, String state, String location, String title, String description, float jobCost, Image photo, Date datePosted, Date dateStart, Date dateEnd, Date datePostEnd, byte availablePercentage, double[] address, String[] tags) {
        this.employer = employer;
        this.mode = mode;
        this.state = state;
        this.location = location;
        this.title = title;
        this.description = description;
        this.jobCost = jobCost;
        this.photo = photo;
        this.datePosted = datePosted;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.datePostEnd = datePostEnd;
        this.availablePercentage = availablePercentage;
        this.address = address;
        this.tags = tags;
    }

//Getters and setters






    //Methods

    public void createJob(){

        ConnectionWebService c = new ConnectionWebService();

        String values =
                        "default" + "," +
                        employer.getId() + "," +
                       // c.getId("jobmode", "mode",mode) + "," +
                       // c.getId("jobstate", "state",state) + "," +
                       // c.getId("location", "city",location) + "," +
                        title + "," +
                        description + "," +
                        jobCost + "," +
                        photo + "," +
                        datePosted + "," +
                        dateStart + "," +
                        dateEnd + "," +
                        datePostEnd + "," +
                        30 + "," +
                        0;

        //c.insertData("Job",values);
    }

    public void deleteJob(){

    }

    public void updateJob(){

    }

    public void displayJob(){

    }

}
