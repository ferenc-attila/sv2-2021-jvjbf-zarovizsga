package webshop;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class ProductRepository {

    private JdbcTemplate jdbcTemplate;

    public ProductRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public long insertProduct(String productName, int price, int stock) {
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement statement = con.prepareStatement("INSERT INTO products(product_name, price, stock) VALUES (?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, productName);
            statement.setInt(2, price);
            statement.setInt(3, stock);
            return statement;
        }, holder);
        return getKeyHolder(holder);
    }

    private long getKeyHolder(KeyHolder holder) {
        try {
            return holder.getKey().longValue();
        } catch (NullPointerException npe) {
            throw new IllegalArgumentException("Cannot insert!");
        }
    }

    public Product findProductById(long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM products WHERE id = ?",
                (rs, rowNum) -> new Product(
                        rs.getLong("id"),
                        rs.getString("product_name"),
                        rs.getInt("price"),
                        rs.getInt("stock")
                ), id);
    }

    public void updateProductStock(long id, int amount) {
        int productStock = getActualProductStock(id) - amount;
        jdbcTemplate.update("UPDATE products SET stock = ? WHERE id = ?",
                productStock, id);
    }

    private int getActualProductStock(long id) {
        try {
            return jdbcTemplate.queryForObject("SELECT stock FROM products WHERE id = ?",
                    (rs, rowNum) -> rs.getInt("stock"),
                    id);
        } catch (NullPointerException nfe) {
            throw new IllegalArgumentException("Cannot find product with id: " + id);
        }
    }
}
