
import java.util.*;
import java.util.stream.Collectors;


public class LaptopMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//      Создание магазина ноутбуков
        LaptopStore store = new LaptopStore();
        store.addLaptop(new Laptop("Dell", 8, 256, "Windows", "Черный"));
        store.addLaptop(new Laptop("Apple", 16, 512, "MacOS", "Серый"));
        store.addLaptop(new Laptop("HP", 8, 1000, "Windows", "Белый"));
        store.addLaptop(new Laptop("Lenovo", 4, 256, "Linux", "Черный"));
        store.addLaptop(new Laptop("Asus", 16, 512, "Windows", "Белый"));


//      Создание множеств уникальных значений характеристик


        Set<Integer> uniqueRamValues = new TreeSet<>();
        for (Laptop el : store.getLaptops()) uniqueRamValues.add(el.getRam());

        Set<Integer> uniqueStorageValues = new TreeSet<>();
        for (Laptop el : store.getLaptops()) uniqueStorageValues.add(el.getStorage());

        Set<String> uniqueOsValues = new TreeSet<>();
        for (Laptop el : store.getLaptops()) uniqueOsValues.add(el.getOs());

        Set<String> uniqueColorValues = new TreeSet<>();
        for (Laptop el : store.getLaptops()) uniqueColorValues.add(el.getColor());



        Map<String, Integer> filters = new HashMap<>();

        while (true) {
            System.out.println("Введите цифру, соответствующую необходимому критерию:");
            System.out.println("1 - ОЗУ");
            System.out.println("2 - Объем ЖД");
            System.out.println("3 - Операционная система");
            System.out.println("4 - Цвет");
            System.out.println("0 - Завершить выбор критериев, для вывода имеющихся ноутбуков!");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) {
                break;
            }

            // Обработка выбора пользователя и запрос минимального значения для выбранного критерия
            switch (choice) {
                case 1:
                    System.out.print("В магазине имеются следующие объемы оперативной памяти: ");
                    for (int el : uniqueRamValues) System.out.print(el + " ");
                    System.out.println();
                    System.out.print("Введите минимальное значение ОЗУ (в ГБ) из указанных: ");
                    int ram = scanner.nextInt();
                    if (!uniqueRamValues.contains(ram)) {
                        System.out.println("Нет такого значения!!!");
                    } else {
                        filters.put("ram", ram);
                        System.out.println("Ваш выбор записан.");
                        scanner.nextLine();
                    }
                    System.out.println();
                    break;
                case 2:
                    System.out.print("В магазине имеются следующие объемы памяти: ");
                    for (int el : uniqueStorageValues) System.out.print(el + " ");
                    System.out.println();
                    System.out.print("Введите минимальное значение объема жесткого диска (в ГБ) из указанных:");
                    int storage = scanner.nextInt();
                    if (!uniqueStorageValues.contains(storage)) {
                        System.out.println("Нет такого значения!!!");
                    } else {
                        filters.put("storage", storage);
                        System.out.println("Ваш выбор записан.");
                        scanner.nextLine();
                    }
                    System.out.println();
                    break;
                case 3:
                    System.out.print("В магазине имеются следующие операционные системы: ");
                    for (String el : uniqueOsValues) System.out.print(el + " ");
                    System.out.println();
                    System.out.print("Введите необходимую операционную систему: ");
                    String os = scanner.nextLine();
                    if (!uniqueOsValues.contains(os)) {
                        System.out.println("Нет такого значения!!!");
                    } else {
                        filters.put("os", os.hashCode());
                        System.out.println("Ваш выбор записан.");
                    }
                    System.out.println();
                    break;
                case 4:
                    System.out.print("В магазине имеются оутбуки следующих цветов: ");
                    for (String el : uniqueColorValues) System.out.print(el + " ");
                    System.out.println();
                    System.out.print("Введите необходимый цвет: ");
                    String color = scanner.nextLine();
                    if (!uniqueColorValues.contains(color)) {
                        System.out.println("Нет такого значения!!!");
                    } else {
                        filters.put("color", color.hashCode());
                        System.out.println("Ваш выбор записан.");
                    }
                    System.out.println();
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
        System.out.println();

        // Фильтрация ноутбуков на основе заданных критериев
        Set<Laptop> filteredLaptops = filterLaptops(store.getLaptops(), filters);
        if (!filteredLaptops.isEmpty()){
            System.out.println("Подходящие ноутбуки:");
        } else {
            System.out.println("Нет подходящих ноутбуков!");
        }

        System.out.println();

        for (Laptop elem : filteredLaptops) {
            System.out.println(elem);
            System.out.println();
        }
        scanner.close();
    }

    // Метод для фильтрации ноутбуков на основе заданных критериев
    private static Set<Laptop> filterLaptops(Set<Laptop> laptops, Map<String, Integer> filters) {
        Set<Laptop> filteredLaptops = new HashSet<>();

        for (Laptop laptop : laptops) {
            boolean matches = true;

            for (Map.Entry<String, Integer> entry : filters.entrySet()) {
                switch (entry.getKey()) {
                    case "ram":
                        if (laptop.getRam() < entry.getValue()) {
                            matches = false;
                        }
                        break;
                    case "storage":
                        if (laptop.getStorage() < entry.getValue()) {
                            matches = false;
                        }
                        break;
                    case "os":
                        if (laptop.getOs().hashCode() != entry.getValue()) {
                            matches = false;
                        }
                        break;
                    case "color":
                        if (laptop.getColor().hashCode() != entry.getValue()) {
                            matches = false;
                        }
                        break;
                }
                if (!matches) break; // Если хотя бы одно условие не выполняется, прекращаем проверку
            }

            if (matches) {
                filteredLaptops.add(laptop);
            }
        }

        return filteredLaptops;
    }

}