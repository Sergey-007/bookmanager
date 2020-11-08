package net.proselyte.bookmanager.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import net.proselyte.bookmanager.dao.BookDao;
import net.proselyte.bookmanager.dao.BookDaoImpl;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

@Configuration
@EnableWebMvc
public class BookConfiguration {

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/bookmanager?serverTimezone=UTC");
        config.setUsername("Sergey");
        config.setPassword("07081991Sergey");
        config.setDriverClassName("com.mysql.jdbc.Driver");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        return new HikariDataSource(config);
    }
    @Bean
    public BookDao bookDao(DataSource dataSource, SessionFactory sessionFactory) {
        return new BookDaoImpl(dataSource, sessionFactory);
    }
}
