package trev0r.jdbc;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
public class SQLTest {
    public static String conventer(String s, int length) {
        if(s==null){
            if(length<=4) return "null".substring(length);
            else{
                return repeatChar(' ',length-4)+"null";
            }
        }
        if(length<=s.length())
            return s;
        return repeatChar(' ', length-s.length())+s;
    }
    private static String repeatChar(char s ,int t){
        StringBuffer sb= new StringBuffer();
        for (int i=0;i<t;i++)
            sb.append(s);
        return sb.toString();
    }

    public static void run(Connection con, String url, String user, String password, String sql) {
        try {
            if (!con.isClosed())
                System.out.println("成功连接到数据库!");
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            System.out.println("执行结果如下所示:");
            ResultSetMetaData resultSetMetaData = rs.getMetaData();

            String colName[] = new String[resultSetMetaData.getColumnCount()];
            int maxLength = 4;
            for (int i = 0; i < resultSetMetaData.getColumnCount(); i++) {
                String t=resultSetMetaData.getColumnName(i + 1);
                colName[i] = t;
                maxLength=Math.max(maxLength, t.length());
            }
            System.out.print(repeatChar('—', 1+(maxLength+2)*resultSetMetaData.getColumnCount()));
            System.out.println("");
            System.out.print("|");
            for (String t :colName){
                System.out.print(conventer(t, maxLength)+" |");
            }
            System.out.println("\n"+repeatChar('—', 1+(maxLength+2)*resultSetMetaData.getColumnCount()));
            while (rs.next()) {
                System.out.print('|');
                for (String s : colName) {
                    System.out.print(conventer(rs.getString(s), maxLength)+" |");
                }
                System.out.println("\n"+repeatChar('—', 1+(maxLength+2)*resultSetMetaData.getColumnCount()));
            }
            rs.close();
            con.close();
        } catch (SQLException e) {
            // 数据库连接失败异常处理
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("程序执行完成！！！    即将自动退出~");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}