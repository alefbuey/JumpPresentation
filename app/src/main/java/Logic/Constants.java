package Logic;

public class Constants {

    public static final int CODIGO_DETALLE = 100;

    public static final int CODIGO_ACTUALIZACION = 101;

    //private static final String PUERTO_HOST = "80";

    private static final String ip = "http://192.168.88.44"; //Oscar


    //URLs del Web Service

    //CRU de usuario
    public static final String selectUser = ip  + "/JumpWebService/Logic/User/selectUser.php";
    public static final String updateUser = ip  + "/JumpWebService/Logic/User/updateUser.php";
    public static final String insertUser = ip  + "/JumpWebService/Logic/User/insertUser.php";
    public static final String removeUser = ip  + "/JumpWebService/Logic/User/deleteUser.php";

    public static String getSelectUser() { return selectUser; }
    public static String getInsertUser() { return insertUser; }
    public static String getUpdateUser() { return updateUser; }
    public static String getRemoveUser() { return removeUser; }


}
