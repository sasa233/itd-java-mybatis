package cn.yd.shop.dao.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		product.setDate(new Date());
		product.setPrice(new BigDecimal(66.9));
		daoImpl.save(product);
//		product.setId(8);
//		daoImpl.update(product);
//		daoImpl.delete(4);
//		for (Product temp : daoImpl.queryByName1("", 3.0, 100.0)) {
//			System.out.println(temp);
//		}
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
		session.insert("cn.yd.shop.model.Product.delete", id);
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
	
	public List<Product> queryByName1(String keyword, Double minPrice, Double maxPrice) {
		// 1: 获取sqlSession --> sessionFactory --> mybatis-cfg.xml --->
		// Product.xml
		
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("keyword", "%" + keyword + "%");
		if (minPrice!=null) paraMap.put("minPrice", minPrice);
		if (maxPrice!=null) paraMap.put("maxPrice", maxPrice);
		SqlSession session = JdbcUtils.getSqlSession();
		List<Product> proList = session.selectList(Product.class.getName() + ".queryByName1", paraMap);
		JdbcUtils.close(session);
		return proList;
	}

}
