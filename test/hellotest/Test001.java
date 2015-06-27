package hellotest;

/**
 * @author Bryan-zhou
 * @date 2015年6月27日下午5:04:46
 **/
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import edu.scut.mybatis.inter.IUserOperation;
import edu.scut.mybatis.model.User;


public class Test001 {
    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader; 

    static{
        try{
            reader    = Resources.getResourceAsReader("Configuration.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getSession(){
        return sqlSessionFactory;
    }
    
    public static void main(String[] args) {
    	new Test001().test2();
    }
    
    private void test2(){

        SqlSession session = sqlSessionFactory.openSession();
        try {
            IUserOperation userOperation=session.getMapper(IUserOperation.class);
            User user = userOperation.selectUserByID(1);
            System.out.println(user.getUserAddress());
            System.out.println(user.getUserName());
        } finally {
            session.close();
        }
    }
    
    
    private  void test1(){

        SqlSession session = sqlSessionFactory.openSession();
        try {
        User user = (User) session.selectOne("edu.scut.mybatis.model.selectUserByID", 1);
        System.out.println(user.getUserAddress());
        System.out.println(user.getUserName());
        System.out.println("OVer");
        } finally {
        session.close();
        }
    
    }
}
