package ru.bokalysha.Prac4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.bokalysha.Prac4.dao.SmartphoneDAO;
import ru.bokalysha.Prac4.models.Smartphone;

import java.util.List;

/**
 * Класс с базовым меню в консоле для взаимодействия с базой данных.
 */
@Component
public class TUI {

    /**
     * DAO для работы с объектами Smartphone в базе данных.
     */
    private final SmartphoneDAO smartphoneDAO;

    /**
     * Конструктор меню.
     *
     * @param smartphoneDAO DAO для работы с объектами Smartphone в базе данных.
     */
    @Autowired
    public TUI(SmartphoneDAO smartphoneDAO) {
        this.smartphoneDAO = smartphoneDAO;
    }

    /**
     * Метод запуска консольного меню.
     */
    public void start() {
        while (true) {
            printMainMenu();
            int choice = Input.intInput();
            switch (choice) {
                case 1:
                    addSmartphone();
                    break;
                case 2:
                    printAll();
                    break;
                case 3:
                    editSmartphone();
                    break;
                case 4:
                    deleteSmartphone();
                    break;
                case 5:
                    storageFilter();
                    break;
                case 6:
                    System.out.println("Ты заходи, если что!");
                    System.exit(0);
                default:
                    System.out.println("Неправильный ввод!");
            }
        }
    }

    /**
     * Метод для добавления нового смартфона в базу данных.
     */
    private void addSmartphone() {
        Smartphone vessel = new Smartphone();
        try {
            System.out.println("Введите бренд:");
            vessel.setBrand(Input.stringInput());
            System.out.println("Введите модель:");
            vessel.setModel(Input.stringInput());
            System.out.println("Введите операционную систему:");
            vessel.setOperating_system(Input.stringInput());
            System.out.println("Введите память смартфона:");
            vessel.setStorage_capacity_gb(Input.intInput());
            System.out.println("Введите цену (USD):");
            vessel.setPrice_usd(Input.floatInput());
            smartphoneDAO.insert(vessel);
            System.out.println("Товар добавлен в базу данных!");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Метод для вывода всех смартфонов в базе данных.
     */
    private void printAll() {
        List<Smartphone> Smartphones = smartphoneDAO.findAll();
        System.out.println("Список всех смартфонов:");
        for (Smartphone smartphone : Smartphones) {
            System.out.println(smartphone);
        }
    }

    /**
     * Метод для редактирования смартфона в базе данных.
     */
    private void editSmartphone() {
        System.out.println("Введите ID смарфтона:");
        Smartphone smartphone = smartphoneDAO.find(Input.intInput());
        if (smartphone == null) {
            System.err.println("Смартфон с указанным ID не был найден!");
            return;
        }
        int choose;
        do {
            System.out.println("""
                    Редактировать:
                    1. Бренд
                    2. Модель
                    3. Операционная система
                    4. Память
                    5. Цена
                    -----------------------
                    6. Выход
                    """);

            choose = Input.intInput();
            switch (choose) {
                case 1:
                    System.out.println("Введите бренд:");
                    smartphone.setBrand(Input.stringInput());
                    break;
                case 2:
                    System.out.println("Введите модель:");
                    smartphone.setModel(Input.stringInput());
                    break;
                case 3:
                    System.out.println("Введите операционную систему:");
                    smartphone.setOperating_system(Input.stringInput());
                    break;
                case 4:
                    System.out.println("Введите память:");
                    try {
                        smartphone.setStorage_capacity_gb(Input.intInput());
                    } catch (IllegalArgumentException e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 5:
                    System.out.println("Введите цену (USD):");
                    try {
                        smartphone.setPrice_usd(Input.floatInput());
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 6:
                    smartphoneDAO.update(smartphone);
                    break;
                default:
                    System.err.println("Нет такого пункта меню!");
            }
        } while (choose != 6);
    }

    /**
     * Метод для удаления смартфона из базы данных.
     */
    private void deleteSmartphone() {
        System.out.println("Введите ID смарфтона:");
        int result = smartphoneDAO.delete(Input.intInput());
        if (result == 0) {
            System.err.println("Смартфона с введённым ID не нашлось!");
        }
    }

    /**
     * Метод для фильтрации по памяти для смартфона в базе данных.
     */
    private void storageFilter() {
        System.out.println("Введите максимальную память:");
        float storage = Input.intInput();
        if (storage < 0) {
            System.err.println("Память не может быть отрицательной!");
            return;
        }

        List<Smartphone> smartphones = smartphoneDAO.filterStorageCapacityGb(storage);
        for (Smartphone smartphone : smartphones) {
            System.out.println(smartphone);
        }
    }

    /**
     * Выводит пункты меню в консоле.
     */
    private void printMainMenu() {
        System.out.println("""
                Меню:
                1. Добавить смартфон
                2. Вывести все смартфоны
                3. Редактировать смартфон
                4. Удалить смартфон
                5. Найти смартфон с памятью ниже определенной
                ---------------------------------------------
                6. Выход
                """);
    }
}
