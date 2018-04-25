package Logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

public final class ConnectionDB {

    public ConnectionDB() {
    }

    /*Method that makes the connection to the DB*/
    public Connection connectDB() {
        Connection c = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost/Jump",
                            "postgres", "1234");
        } catch (Exception e) {}

        return c;
    }

    public void executeUpdateSQL(String sqlStatement){
        Connection c;
        Statement stmt = null;
        try {
            c = connectDB();
            stmt = c.createStatement();
            stmt.executeUpdate(sqlStatement);
            stmt.close();
            c.setAutoCommit(false);
            c.commit();
            c.close();
        } catch (Exception e) {}
    }

    //Method to insert data in a table
    public void insertData(String tableName, String values) {
        String sql = "INSERT INTO " + tableName + " VALUES (" + values + ");";
        executeUpdateSQL(sql);
    }
    //
    public void updateData(String tableName, int id, String columValue, String columName) {
        String sql = "UPDATE INTO " + tableName + " SET " + columName+" = "+ columValue + " WHERE id = "+id+";";
        executeUpdateSQL(sql);
    }

    public void deleteData(String tableName, int id) {
        String sql = "DELETE FROM " + tableName + " WHERE id = " + id + ");";
        executeUpdateSQL(sql);
    }

    public ArrayList<String> executeSelectSQL(String sqlStatement, ArrayList<String> fields){
        Connection c;

        Statement stmt = null;
        ArrayList<String> datos = new ArrayList<String>();

        try {
            c = connectDB();
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(sqlStatement);
            ResultSetMetaData rsm = rs.getMetaData();
            int numColumns = rsm.getColumnCount();
            while (rs.next()) {
                String registro = "";
                for (int i=0;i<numColumns;i++){
                    if(i!=numColumns-1) {
                        registro += rs.getString(i) + ",";
                    }
                    else{
                        registro += rs.getString(i);
                    }
                }

                datos.add(registro);
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            String error = (e.getMessage());
        }
        return datos;
    }



/*    public void updateData(String values) {
        //values debe estar en el formato correspondiente SQL
        String[] datosSplit = values.split(",");
        Connection c;
        Statement stmt = null;
        try {
            c = connectDB();
            stmt = c.createStatement();
            String sql = "UPDATE patient set firstName = " + datosSplit[1] + ",lastName = " + datosSplit[2] + ",genre = " + datosSplit[3] + ",birthdate = " + datosSplit[4] + "," + datosSplit[5] + ",heigth  = " + datosSplit[6] + ",weight = " + datosSplit[7] + ",phone = " + datosSplit[8] + ",email = " + datosSplit[9] + " where id = " + datosSplit[0];
            stmt.executeUpdate(sql);
            stmt.close();
            c.setAutoCommit(false);
            c.commit();
            c.close();
        } catch (Exception e) {
            String error = (e.getMessage());
        }
        String success = "Patient information updated successfully";
    }




    *//*Funcion que se encarga de recuperar los registros de la tabla paciente*//*
    public ArrayList<String> recoverData() {
        Connection c;

        Statement stmt = null;
        ArrayList<String> datos = new ArrayList<String>();
        String datoPaciente;
        try {
            c = connectDB();
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM patient;");

            while (rs.next()) {
                String cedula = rs.getString("id");
                String nombre = rs.getString("firstName");
                String apellido = rs.getString("lastName");
                String genero = rs.getString("genre");
                String fechNac = rs.getString("birthdate");
                String altura = rs.getString("heigth");
                String peso = rs.getString("weight");

                datoPaciente = cedula + "," + "," + nombre + "," + apellido + "," + genero + "," + fechNac + "," + altura + "," + peso;

                datos.add(datoPaciente);
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            String error = (e.getMessage());
        }
        return datos;
    }


    public String recoverPatient(String id) {
        Connection c;

        Statement stmt = null;
        String datoPaciente = "";
        try {
            c = connectDB();
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM patient WHERE id =" + "'" + id + "';");
            rs.next();
            String cedula = rs.getString("id");
            String nombre = rs.getString("firstName");
            String apellido = rs.getString("lastName");
            String genero = rs.getString("genre");
            String fechNac = rs.getString("birthdate");
            String altura = rs.getString("heigth");
            String peso = rs.getString("weight");

            datoPaciente = cedula + "," + nombre + "," + apellido + "," + genero + "," + fechNac + "," + altura + "," + peso;

            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            String error = (e.getMessage());
        }
        return datoPaciente;
    }



    *//*Funcion que se encarga de eliminar un registro de la tabla paciente,
    para borrar se debe pasar como parametro la cedula del paciente y la fecha de analisis*//*
    public void deleteRegister(String cedula) {

        Connection c;
        Statement stmt = null;
        try {
            c = connectDB();
            stmt = c.createStatement();
            String sql = "DELETE from patient where id = '" + cedula + "';";
            stmt.executeUpdate(sql);
            c.setAutoCommit(false);
            c.commit();
            stmt.close();
            c.close();
        } catch (Exception e) {
            String error = (e.getMessage());
        }
        String success = "Patient deleted successfully";

    }




// METODOS NUEVOS   ---------------------------


    public ArrayList<String> getPatient(String cedula, String lastAnalisis) {

        Connection c;
        Statement stmt = null;
        Statement stmt2 = null;
        ArrayList<String> list = new ArrayList<String>();
        try {
            c = connectDB();
            stmt = c.createStatement();
            stmt2 = c.createStatement();
            String sql = "SELECT id,firstName,lastName,genre,birthDate,heigth,weight from patient where id = '" + cedula + "';";
            String sql2 = "SELECT dateAnalysis from PatientRecord where idpatient = '" + cedula + "' order by dateAnalysis limit 1;";
            ResultSet rs = stmt.executeQuery(sql);
            ResultSet rs2 = stmt2.executeQuery(sql2);

            if(rs.next()){
                list.add(rs.getString("id"));
                list.add(rs.getString("firstName").toUpperCase());
                list.add(rs.getString("lastName").toUpperCase());
                list.add(rs.getString("birthDate"));
                list.add(rs.getString("heigth"));
                list.add(rs.getString("weight"));
                list.add(rs.getString("genre").toUpperCase());
            }



            if(rs2.next()){
                list.add(rs2.getString("dateAnalysis"));
            }

            rs.close();
            rs2.close();
            //rs2.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
        return list;
    }


    public String dataFromDB(String id) {
        String diagnostics = "";
        try {
            Connection connection = connectDB();
            Statement st = connection.createStatement();
            String sql = "SELECT id, diagnosis, medication FROM PatientRecord WHERE idPatient = '" + id + "'";
            ResultSet result = st.executeQuery(sql);
            while(result.next()) {
                diagnostics += result.getString("id")+ ": "+result.getString("diagnosis")+"\n"
                        +result.getString("medication")+"\n";
            }
            result.close();
            st.close();
            connection.close();
        }catch(Exception e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
        return diagnostics;
    }

    public void updateDB(String idRecordTextField, String idPatientTextField, String idImageTextField,
                         String diagnosticTextArea, String medicationTextArea) {
        String diagnostics = ""; //este sí va vacio

        try {
            Connection connection = connectDB();
            Statement st = connection.createStatement();
            String sql =
                    "INSERT INTO PatientRecord VALUES ('"+
                            idRecordTextField +"', '"+idPatientTextField+"', '"+idImageTextField+"', '"+
                            diagnosticTextArea +"', '"+medicationTextArea+"', current_date)";
            st.executeUpdate(sql);
            st.close();
            connection.close();
        } catch(Exception e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }*/
}
