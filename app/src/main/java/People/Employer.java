package People;

public class Employer extends User{

    int id;

    public Employer(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    private float ranking;
    private short numJobsPosted;
}
