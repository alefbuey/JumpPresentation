package Work;

import android.media.Image;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

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
    private SimpleDateFormat datePosted;
    private SimpleDateFormat dateStart;
    private SimpleDateFormat dateEnd;
    private SimpleDateFormat datePostEnd;
    private byte availablePercentage;
    private double[] address;
    private String[] tags;
    private String[] categories;
    private ArrayList<ParentComment> comments;


    //Constructor

    public Job(int id, Employer employer, ArrayList<Employee> employees, ArrayList<Employee> applicants, String mode, String state, String location, String title, String description, float jobCost, Image photo, SimpleDateFormat datePosted, SimpleDateFormat dateStart, SimpleDateFormat dateEnd, SimpleDateFormat datePostEnd, byte availablePercentage, double[] address, String[] tags, String[] categories, ArrayList<ParentComment> comments) {
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


    //Getters and setters






    //Methods

    public void createJob(){

    }

    public void deleteJob(){

    }

    public void updateJob(){

    }

    public void displayJob(){

    }

}
