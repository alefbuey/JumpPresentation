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
    public Job (int id){

    }


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
