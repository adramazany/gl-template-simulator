package dp.gl.gltemplatesimulator;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class TestMysql {

    @Test
    public void testConnection() throws ClassNotFoundException, SQLException {
        System.out.println("TestMysql.testConnection");
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection cn= DriverManager.getConnection("jdbc:mysql://digipay@10.198.111.61:3306/general_ledger_db"
                ,"digipay","D1giP@y_Us3r_MNG");
        try{
            Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from account");
            while(rs.next()){
                System.out.println("rs.getString(\"account_no\") = " + rs.getString("account_no"));
            }
            rs.close();
            System.out.println("TestMysql.testConnection:succeed!");
        }catch(Exception ex) {
            ex.printStackTrace();
        }finally {
            cn.close();
        }


    }
}
