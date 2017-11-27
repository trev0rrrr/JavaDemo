package trev0r.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void getOptions() {
        System.out.println("功能说明:  数据库连接测试和执行查询SQL");
        System.out.println("    选项1:  Oracle  (格式:jdbc:oracle:thin:@machine_name:port:dbname)");
        System.out.println("    选项2:  MySQL   (格式:jdbc:mysql://machine_name:port/dbname)");
        System.out.println("    选项9:  执行预设");
    }
    public static void main(String[] args) {
        Main main = new Main();
        try{
            main.exec();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void exec() throws InterruptedException, IOException, SQLException, ClassNotFoundException {
        getOptions();
        String OracleDriverName = "oracle.jdbc.OracleDriver";
        String OracleTestSql = "select 'x' from dual";
        String OraclePrefix = "dbc:oracle:thin:@";
        String MySQLDriverName = "com.mysql.jdbc.Driver";
        String MySQLTestSql = "SELECT 'x' FROM BASIC";
        String MySQLPrefix = "jdbc:mysql://";
        Scanner sc = new Scanner(System.in);
        String url = "", username = "", password = "", sql = "", prefix = "", driverName = "";
        int type;
        System.out.print("请选择 : ");
        type = sc.nextInt();
        if (type == 1) {
            sql = OracleTestSql;
            prefix = OraclePrefix;
            driverName = OracleDriverName;
            Class.forName(driverName);
        } else if (type == 2) {
            sql = MySQLTestSql;
            prefix = MySQLPrefix;
            driverName = MySQLDriverName;
            Class.forName(driverName);
        }else if(type==9) {
            Connection con = null;
            try {
                con = DriverManager.getConnection("jdbc:mysql://192.168.81.1/HGQ","root","root");
                if(con==null) throw new Exception("获取不到连接");
            } catch (SQLException e1) {
                e1.printStackTrace();
                return ;
            } catch (Exception e) {
                e.printStackTrace();
                return ;
            }
            SQLTest.run(con, "jdbc:mysql://192.168.81.1:3306/HGQ", "root", "root","select * from users;" );
            return ;
        }

        System.out.print("输入数据库url : " + prefix);
        url = prefix+sc.next();
        System.out.print("输入用户名 : ");
        username = sc.next();
        System.out.print("输入密码 : ");
        password = sc.next();
        System.out.println("输入sql(输入1使用默认sql语句) : ");
        if (sc.hasNext()) {
            String tmp = sc.next();
            if (!"1".equals(tmp))
                sql = tmp;
        }
        sc.close();
        Connection con = DriverManager.getConnection(url,username,password);
        SQLTest.run(con, url, username, password, sql);
    }
}
