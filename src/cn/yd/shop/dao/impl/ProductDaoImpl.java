package cn.yd.shop.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import cn.mybatis.util.JdbcUtils;
import cn.yd.shop.model.Product;

//ProductDaoImpl可使用BaseDaoImpl提供的方法
//java是单一继承的，super指向唯一的父类
//继承是代码复用的手段
//public class ProductDaoImpl extends BaseDaoImpl<Product> { // 此处由子类确定了了父类BaseDapImpl的<T>的类型
public class ProductDaoImpl {

	public static void main(String[] args) {
		Product product = new Product();
		ProductDaoImpl daoImpl = new ProductDaoImpl();
		product.setName("test");
//		daoImpl.save(product);
//		product.setPrice(new BigDecimal(3.14));
//		product.setId(4);
//		daoImpl.update(product);
//		daoImpl.delete(4);
		for (Product temp : daoImpl.queryByName("")) {
			System.out.println(temp);
		}
	}

	public void save(Product product) {
		// 1:获取sqlSession --> sessionFactory --> mybatis-cfg,xml --> Product.xml
		SqlSession session = JdbcUtils.getSqlSession();
		System.out.println(Product.class.getName());
		session.insert(Product.class.getName() + ".save", product);
		session.commit();
		JdbcUtils.close(session);
		// String sql = "insert into product (name,price,remark,pic) values
		// (?,?,?,?)";
		// jdbcTemplate.update(
		// sql,
		// new Object[] { product.getName(), product.getPrice(),
		// product.getRemark(), product.getPic() });
	}

	public void update(Product product) {
		// 1: 获取sqlSession --> sessionFactory --> mybatis-cfg.xml --->
		// Product.xml
		SqlSession session = JdbcUtils.getSqlSession();
		session.insert(Product.class.getName() + ".update", product);
		session.commit();
		JdbcUtils.close(session);
	}

	public void delete(int id) {
		// 1: 获取sqlSession --> sessionFactory --> mybatis-cfg.xml --->
		// Product.xml
		SqlSession session = JdbcUtils.getSqlSession();
		session.insert(Product.class.getName() + ".delete", id);
		session.commit();
		JdbcUtils.close(session);
	}

	public List<Product> queryByName(String keyword) {
		// 1: 获取sqlSession --> sessionFactory --> mybatis-cfg.xml --->
		// Product.xml
		SqlSession session = JdbcUtils.getSqlSession();
		List<Product> proList = session.selectList(Product.class.getName() + ".queryByName", "%" + keyword + "%");
		JdbcUtils.close(session);
		return proList;
	}

}
