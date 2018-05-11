package Logic;

public class Constants {

    public static final int CODIGO_DETALLE = 100;

    public static final int CODIGO_ACTUALIZACION = 101;

    //private static final String PUERTO_HOST = "80";



    //private static final String ip = "http://192.168.88.44"; //Oscar
   // private static final String ip = "http://172.23.207.50"; //Oscar

    private static final String ip = "http://192.168.10.102"; //Fernando repetidor
    //  private static final String ip = "http://192.168.0.108"; //Fernando emulador





    //URLs del Web Service


    //CRU de usuario
    public static final String selectUserProfile = ip + "/JumpWebService/Logic/User/selectUserProfile.php";
    public static final String selectUser = ip + "/JumpWebService/Logic/User/selectUser.php";
    public static final String updateUser = ip + "/JumpWebService/Logic/User/updateUser.php";
    public static final String insertUser = ip + "/JumpWebService/Logic/User/insertUser.php";
    public static final String removeUser = ip + "/JumpWebService/Logic/User/deleteUser.php";

    public static final String applicantInfo = ip + "/JumpWebService/Logic/User/selectApplicantInfo.php";
    public static final String applicants = ip + "/JumpWebService/Logic/User/selectApplicants.php";
    public static final String updateEmployeeState = ip + "/JumpWebService/Logic/User/updateEmployeeState.php";

    public static final String jobRead = ip + "/JumpWebService/Logic/Work/jobRead.php";
    public static final String jobApply = ip + "/JumpWebService/Logic/Work/jobApply.php";
    public static final String jobReadMultiple = ip + "/JumpWebService/Logic/Work/jobReadMultiple.php";
    public static final String jobCreate = ip + "/JumpWebService/Logic/Work/jobCreate.php";
    public static final String jobDelete = ip + "/JumpWebService/Logic/Work/jobDelete.php";
    public static final String jobUpdate = ip + "/JumpWebService/Logic/Work/jobUpdate.php";
    public static final String jobModeRead = ip + "/JumpWebService/Logic/Work/jobModeRead.php";
    public static final String jobMyJobs = ip + "/JumpWebService/Logic/Work/jobMyJobs.php";
    public static final String jobMyBusiness = ip + "/JumpWebService/Logic/Work/jobMyBusiness.php";
    public static final String jobMyFovoriteJobs = ip + "/JumpWebService/Logic/Work/jobGetFavorite.php";
    public static final String jobUpdateFavorite = ip + "/JumpWebService/Logic/Work/jobUpdateFavorite.php";
    public static final String jobCheckFavorite = ip + "/JumpWebService/Logic/Work/jobCheckFavorite.php";


    public static String getSelectUserProfile() {
        return selectUserProfile;
    }
    public static String getSelectUser() {
        return selectUser;
    }
    public static String getInsertUser() {
        return insertUser;
    }
    public static String getUpdateUser() { return updateUser; }
    public static String getRemoveUser() { return removeUser; }

    public static String getJobRead() {
        return jobRead;
    }
    public static String getJobApply() {
        return jobApply;
    }

    public static String getApplicantInfo() {return applicantInfo; }
    public static String getApplicants() {return applicants;}
    public static String getUpdateEmployeeState() {return updateEmployeeState;}

    public static String getJobReadMultiple() {return jobReadMultiple;}
    public static String getJobCreate() {
        return jobCreate;
    }
    public static String getJobDelete() {
        return jobDelete;
    }
    public static String getJobUpdate() {
        return jobUpdate;
    }
    public static String getJobModeRead() {
        return jobModeRead;
    }
    public static String getJobMyJobs() {
        return jobMyJobs;
    }
    public static String getJobMyBusiness() {
        return jobMyBusiness;
    }
    public static String getJobMyFovoriteJobs() {
        return jobMyFovoriteJobs;
    }
    public static String getJobUpdateFavorite() {
        return jobUpdateFavorite;
    }
    public static String getJobCheckFavorite() { return jobCheckFavorite;}

    public static String getIp() {
        return ip;
    }
}

