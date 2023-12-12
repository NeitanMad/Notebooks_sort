import java.util.*;
// Описываем класс Ноутбук
class Notebook {
    private String model;
    private int ram;
    private int storage;
    private String os;
    private String color;
    
    public Notebook(String model, int ram, int storage, String os, String color) {
        this.model = model;
        this.ram = ram;
        this.storage = storage;
        this.os = os;
        this.color = color;
    }

    @Override
        public String toString() {
            return "Модель: " + model +
                   ", ОЗУ: " + ram +
                   " ГБ, Жесткий диск: " + storage +
                   " ГБ, Операционная система: " + os +
                   ", Цвет: " + color;
        }
    
    public String getModel() {
        return model;
    }
    
    public int getRam() {
        return ram;
    }
    
    public int getStorage() {
        return storage;
    }
    
    public String getOs() {
        return os;
    }
    
    public String getColor() {
        return color;
    }
}

public class program {
    private static Random random = new Random();
    public static void main(String[] args) {
        Set<Notebook> notebooks = generateNotebooks(30); // Генерируем 30 ноутбуков      
        filterNotebooks(notebooks);
    }

    public static Set<Notebook> generateNotebooks(int size) {
        Set<Notebook> notebooks = new HashSet<>();

        String[] models = {"MacBook Pro", "Dell XPS 13", "HP Spectre x360", "Lenovo ThinkPad X1 Carbon", "DNS HighTech 1s", "Azerty A580"};
        int[] ramOptions = {4, 8, 16, 32};
        int[] storageOptions = {256, 512, 1024};
        String[] osOptions = {"Windows 7", "Windows 8", "Windows 10", "macOS", "Linux"};
        String[] colorOptions = {"Silver", "Black", "Gold"};

        for (int i = 0; i < size; i++) {
            String model = models[random.nextInt(models.length)];
            int ram = ramOptions[random.nextInt(ramOptions.length)];
            int storage = storageOptions[random.nextInt(storageOptions.length)];
            String os = osOptions[random.nextInt(osOptions.length)];
            String color = colorOptions[random.nextInt(colorOptions.length)];

            notebooks.add(new Notebook(model, ram, storage, os, color));
        }

        return notebooks;
    }
    
    public static void filterNotebooks(Set<Notebook> notebooks) {
        Map<Integer, Object> criteria = new LinkedHashMap<>();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Введите цифру, соответствующую необходимому критерию:\n"
                           + "1 - ОЗУ\n"
                           + "2 - Объем ЖД\n"
                           + "3 - Операционная система\n"
                           + "4 - Цвет");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера
        
        switch (choice) {
            case 1:
                System.out.println("Введите минимальное значение ОЗУ:");
                int ram = scanner.nextInt();
                criteria.put(choice, ram);
                break;
            case 2:
                System.out.println("Введите минимальный объем ЖД:");
                int storage = scanner.nextInt();
                criteria.put(choice, storage);
                break;
            case 3:
                System.out.println("Введите название операционной системы:");
                String os = scanner.nextLine();
                criteria.put(choice, os);
                break;
            case 4:
                System.out.println("Введите цвет:");
                String color = scanner.nextLine();
                criteria.put(choice, color);
                break;
            default:
                System.out.println("Неверный выбор");
                break;
        }
        
        Set<Notebook> filteredNotebooks = new HashSet<>();
        
        for (Notebook notebook : notebooks) {
            boolean passesCriteria = true;
            
            for (int key : criteria.keySet()) {
                if (!matchesCriteria(notebook, key, criteria.get(key))) {
                    passesCriteria = false;
                    break;
                }
            }
            
            if (passesCriteria) {
                filteredNotebooks.add(notebook);
            }
        }
        
        System.out.println("Отфильтрованные ноутбуки:");
        
        for (Notebook notebook : filteredNotebooks) {
            System.out.println(notebook.toString());
        }
    }
    
    public static boolean matchesCriteria(Notebook notebook, int key, Object value) {
        switch (key) {
            case 1:
                return notebook.getRam() >= (int) value;
            case 2:
                return notebook.getStorage() >= (int) value;
            case 3:
                return notebook.getOs().equalsIgnoreCase((String) value);
            case 4:
                return notebook.getColor().equalsIgnoreCase((String) value);
            default:
                return false;
        }
    }
}