package cn.mybatis.util;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

// 通过此工具类加载mybatis的配置文件，然后生成连接数据库的对象(SqlSession)
public class JdbcUtils {
	
	// 工厂模式主要用于创建对象，例如创建session（数据库连接）的工厂，就成为sessionFactory
	// 工厂通常是单例模式
	
	private static SqlSessionFactory sqlSessionFactory = null;
 
	
	// 所有的配置文件只需加载一次
	static{
		System.out.println("static......");
		// 加载配置文件后返回输入流，其中，“/”代表src目录，只写文件名代表当前路径
		InputStream input = JdbcUtils.class.getResourceAsStream("/mybatis-cfg.xml");
		System.out.println(input);
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(input);
	}
	
	// 创建方法，返回数据库连接对象
	public static SqlSession getSqlSession(){
		return sqlSessionFactory != null ? sqlSessionFactory.openSession() : null;
	}
	
	// 完成 SqlSession关闭功能
	public static void close(SqlSession sqlSession){
		if (sqlSession != null) {
			sqlSession.close();
		}
	}
	
	public static void main(String[] args) {
		System.out.println(JdbcUtils.getSqlSession());
		System.out.println(JdbcUtils.getSqlSession());
		// sqlSession中封装了Collection对象
		System.out.println(JdbcUtils.getSqlSession().getConnection());
	}
	
}
