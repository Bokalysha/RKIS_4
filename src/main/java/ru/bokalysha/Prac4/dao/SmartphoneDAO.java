package ru.bokalysha.Prac4.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.bokalysha.Prac4.models.Smartphone;

/**
 * Класс SmartphoneDAO представляет собой объект доступа к данным для работы с сущностями Smartphone.
 * Он обеспечивает доступ к базе данных для выполнения CRUD операций, таких как создание, чтение, обновление и удаление,
 * а также выполнение дополнительных запросов для фильтрации данных.
 */
@Component
public class SmartphoneDAO {

    /**
     * Объект для доступа к базе данных.
     */
    private final JdbcTemplate jdbcTemplate;

    /**
     * Конструктор класса SmartphoneDAO
     *
     * @param jdbcTemplate объект JdbcTemplate, который предоставляет доступ к базе данных.
     */
    @Autowired
    public SmartphoneDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Получает список всех смартфонов из базы данных.
     *
     * @return список смартфонов.
     */
    public List<Smartphone> findAll() {
        return jdbcTemplate.query("SELECT * FROM Phones",
                new BeanPropertyRowMapper<>(Smartphone.class));
    }

    /**
     * Находит смартфоны по её идентификатору.
     *
     * @param id идентификатор смартфона.
     * @return смартфон с указанным идентификатором или null, если смартфон не найдена.
     */
    public Smartphone find(int id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM Phones WHERE id = ?",
                    new BeanPropertyRowMapper<>(Smartphone.class), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * Вставляет новый смартфон в базу данных.
     *
     * @param smartphone смарфтон для вставки.
     */
    public void insert(Smartphone smartphone) {
        String sql = "INSERT INTO Phones(brand, model, operating_system, storage_capacity_gb, price_usd) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(
                sql,
                smartphone.getBrand(),
                smartphone.getModel(),
                smartphone.getOperating_system(),
                smartphone.getStorage_capacity_gb(),
                smartphone.getPrice_usd()
        );
    }

    /**
     * Обновляет информацию о смартфоне в базе данных.
     *
     * @param smartphone смартфон с обновленными данными.
     */
    public void update(Smartphone smartphone) {
        String sql = "UPDATE Phones SET "
                + "brand = ?, "
                + "model = ?, "
                + "operating_system = ?, "
                + "storage_capacity_gb = ?, "
                + "price_usd = ? WHERE id = ?";

        jdbcTemplate.update(
                sql,
                smartphone.getBrand(),
                smartphone.getModel(),
                smartphone.getOperating_system(),
                smartphone.getStorage_capacity_gb(),
                smartphone.getPrice_usd(),
                smartphone.getId()
        );
    }

    /**
     * Удаляет смартфон из базы данных по её идентификатору.
     *
     * @param id идентификатор смартфона для удаления.
     * @return количество удаленных записей (1, если успешно, 0, если смартфон с указанным идентификатором не найден).
     */
    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM Phones WHERE id = ?", id);
    }

    /**
     * Фильтрует смартфоны по максимальной памяти.
     *
     * @param maxStorageCapacityGb максимальная память смартфона для фильтрации.
     * @return список смартфонов, у которых память меньше или равна указанной максимальной памяти.
     */
    public List<Smartphone> filterStorageCapacityGb(float maxStorageCapacityGb) {
        return jdbcTemplate.query("SELECT * FROM Phones WHERE storage_capacity_gb <= ?",
                new BeanPropertyRowMapper<>(Smartphone.class), maxStorageCapacityGb);
    }
}

