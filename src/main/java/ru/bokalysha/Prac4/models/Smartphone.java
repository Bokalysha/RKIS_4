package ru.bokalysha.Prac4.models;

import java.util.Objects;

/**
 * Класс Smartphone представляет смартфон.
 */
public class Smartphone {

    /**
     * Идентификатор смартфона.
     */
    private int id;

    /**
     * Бренд смартфона.
     */
    private String brand;

    /**
     * Модель смартфона.
     */
    private String model;

    /**
     * Операционная система смартфона.
     */
    private String operating_system;

    /**
     * Объем памяти в смартфоне (Gb).
     */
    private int storage_capacity_gb;

    /**
     * Цена смартфона (USD).
     */
    private float price_usd;

    /**
     * Конструктор без параметров.
     */
    public Smartphone() {
        this.brand = null;
        this.model = null;
        this.operating_system = null;
        this.storage_capacity_gb = 0;
        this.price_usd = 0;
    }

    /**
     * Конструктор с параметрами
     *
     * @param id идентификатор смартфона.
     * @param brand бренд смартфона.
     * @param model модель смартфона.
     * @param operating_system операционная система смартфона.
     * @param storage_capacity_gb объем памяти смартфона.
     * @param price_usd цена смартфона.
     */
    public Smartphone(int id, String brand, String model, String operating_system, int storage_capacity_gb, 
                  float price_usd) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.operating_system = operating_system;

        if (storage_capacity_gb >= 0) {
            this.storage_capacity_gb = storage_capacity_gb;
        } else {
            throw new IllegalArgumentException("Память не может быть отрицательной!");
        }

        if (price_usd > 0) {
            this.price_usd = price_usd;
        } else {
            throw new IllegalArgumentException("Цена не может быть отрицательной!");
        }
    }

    /**
     * Возвращает идентификатор смартфона.
     *
     * @return идентификатор смартфона
     */
    public int getId() {
        return id;
    }

    /**
     * Устанавливает идентификатор смартфона.
     *
     * @param id идентификатор для установки
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Возвращает бренд смартфона.
     *
     * @return бренд смартфона
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Устанавливает бренд смартфона.
     *
     * @param brand бренд для установки
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Возвращает модель смартфона.
     *
     * @return модель смартфона
     */
    public String getModel() {
        return model;
    }

    /**
     * Устанавливает модель смартфона.
     *
     * @param model модель для установки
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Возвращает операционную систему смартфона.
     *
     * @return операционная система смартфона
     */
    public String getOperating_system() {
        return operating_system;
    }

    /**
     * Устанавливает операционную систему смартфона.
     *
     * @param operating_system операционная система для установки
     */
    public void setOperating_system(String operating_system) {
        this.operating_system = operating_system;
    }

    /**
     * Возвращает объем памяти смартфона в гигабайтах.
     *
     * @return объем памяти смартфона
     */
    public int getStorage_capacity_gb() {
        return storage_capacity_gb;
    }

    /**
     * Устанавливает объем памяти смартфона в гигабайтах.
     *
     * @param storage_capacity_gb объем памяти для установки.
     * @throws IllegalArgumentException если переданный объем памяти отрицателен.
     */
    public void setStorage_capacity_gb(int storage_capacity_gb) {
        if (storage_capacity_gb >= 0) {
            this.storage_capacity_gb = storage_capacity_gb;
        } else {
            throw new IllegalArgumentException("Память не может быть отрицательной!");
        }
    }

    /**
     * Возвращает цену смартфона в долларах США.
     *
     * @return цена смартфона.
     */
    public float getPrice_usd() {
        return price_usd;
    }

    /**
     * Устанавливает цену смартфона в долларах США.
     *
     * @param price_usd цена для установки.
     * @throws IllegalArgumentException если переданная цена отрицательна.
     */
    public void setPrice_usd(float price_usd) {
        if (price_usd > 0) {
            this.price_usd = price_usd;
        } else {
            throw new IllegalArgumentException("Цена не может быть отрицательной!");
        }
    }

    /**
     * Возвращает строковое представление смартфона.
     *
     * @return строковое представление смартфона.
     */
    @Override
    public String toString() {
        return "Smartphone{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", operating_system='" + operating_system + '\'' +
                ", storage_capacity_gb=" + storage_capacity_gb +
                ", price_usd=" + price_usd +
                '}';
    }

    /**
     * Проверяет, равен ли текущий объект Smartphone предоставленному объекту.
     * Переопределяет метод equals() по умолчанию, унаследованный от класса Object.
     *
     * @param o объект для сравнения с текущим объектом Smartphone.
     * @return true, если объекты равны, в противном случае - false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Smartphone that = (Smartphone) o;
        return getStorage_capacity_gb() == that.getStorage_capacity_gb()
            && Float.compare(that.getPrice_usd(), getPrice_usd()) == 0
            && Objects.equals(getBrand(), that.getBrand()) && Objects.equals(
            getModel(), that.getModel()) && Objects.equals(getOperating_system(),
            that.getOperating_system());
    }

    /**
     * Возвращает хэш-код для текущего объекта.
     * Переопределяет метод hashCode() по умолчанию, унаследованный от класса Object.
     *
     * @return хэш-код объекта.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getBrand(), getModel(), getOperating_system(), getStorage_capacity_gb(),
            getPrice_usd());
    }
}

