package trev0r.mybatis;
import trev0r.mybatis.domain.Dto;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
public class Main {
    public static void main(String args[]){
        String resource = "conf.xml";
        InputStream is = Main.class.getClassLoader().getResourceAsStream(resource);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = sessionFactory.openSession();
        String statement = "trev0r.mybatis.dao.dtoMapper.getDto";//映射sql的标识字符串
        Dto dto = session.selectOne(statement, 1);
        System.out.println(dto);
        Dto dto2 = session.selectOne(statement, 9527);
        System.out.println(dto2);
        session.close();
    }
}
