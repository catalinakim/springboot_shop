package com.sparta.springcore.repository;
/*
import com.sparta.springcore.model.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRepository {

    private final String dbUrl;
    private final String dbId;
    private final String dbPassword;

    public ProductRepository(String dbUrl, String dbId, String dbPassword) {
        this.dbUrl = dbUrl;
        this.dbId = dbId;
        this.dbPassword = dbPassword;
    }

    public Product insertDB(Product product) throws SQLException {
        // DB 연결
        //Connection connection = DriverManager.getConnection("jdbc:h2:mem:springcoredb", "sa", "");
        Connection connection = getSa();

        // DB Query 작성
        PreparedStatement ps = connection.prepareStatement("select max(id) as id from product");
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            // product id 설정 = product 테이블의 마지막 id + 1
            product.setId(rs.getLong("id") + 1);
        } else {
            throw new SQLException("product 테이블의 마지막 id 값을 찾아오지 못했습니다.");
        }
        ps = connection.prepareStatement("insert into product(id, title, image, link, lprice, myprice) values(?, ?, ?, ?, ?, ?)");
        ps.setLong(1, product.getId());
        ps.setString(2, product.getTitle());
        ps.setString(3, product.getImage());
        ps.setString(4, product.getLink());
        ps.setInt(5, product.getLprice());
        ps.setInt(6, product.getMyprice());

        // DB Query 실행
        ps.executeUpdate();

        // DB 연결 해제
        ps.close();
        connection.close();
        return product;
    }

    private Connection getSa() throws SQLException {
        //return DriverManager.getConnection("jdbc:h2:mem:springcoredb", "sa", "");
        return DriverManager.getConnection(dbUrl, dbId, dbPassword);
    }
}
*/

// JPA
import com.sparta.springcore.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> { }