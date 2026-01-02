package week7;
import java.io.*;
import java.util.*;
import java.util.zip.*;
import java.text.SimpleDateFormat;

// ================= BACKUP DATA =================

class BackupData implements Serializable {
    private static final long serialVersionUID = 1L;

    private String backupId;
    private Date backupTime;
    private String description;
    private Map<String, Object> data;

    public BackupData(String description, Map<String, Object> data) {
        this.backupId = UUID.randomUUID().toString();
        this.backupTime = new Date();
        this.description = description;
        this.data = new HashMap<>(data);
    }

    public String getBackupId() { return backupId; }
    public Date getBackupTime() { return backupTime; }
    public String getDescription() { return description; }
    public Map<String, Object> getData() { return data; }

    public void saveBinary(String path) throws IOException {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(this);
        }
    }

    public void saveCompressed(String path) throws IOException {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream(path)))) {
            oos.writeObject(this);
        }
    }

    public void exportCSV(String path) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            bw.write("Key,Value,Type\n");
            for (var e : data.entrySet()) {
                bw.write(e.getKey() + "," + e.getValue() + "," +
                        e.getValue().getClass().getSimpleName() + "\n");
            }
        }
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return """
               Backup ID: %s
               Time: %s
               Description: %s
               Data Items: %d
               """.formatted(backupId, sdf.format(backupTime), description, data.size());
    }
}

// ================= BACKUP MANAGER =================

class BackupManager {

    private final String BACKUP_DIR = "backups/";
    private final String VERSION_DIR = "versions/";

    public BackupManager() {
        new File(BACKUP_DIR).mkdirs();
        new File(VERSION_DIR).mkdirs();
    }

    public BackupData restoreBinary(String path)
            throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(path))) {
            return (BackupData) ois.readObject();
        }
    }

    public BackupData restoreCompressed(String path)
            throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois =
                     new ObjectInputStream(new GZIPInputStream(new FileInputStream(path)))) {
            return (BackupData) ois.readObject();
        }
    }

    public void createBackup(String description, Map<String, Object> data)
            throws IOException {

        BackupData backup = new BackupData(description, data);
        String ts = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String base = BACKUP_DIR + "backup_" + ts;

        backup.saveBinary(base + ".dat");
        backup.saveCompressed(base + ".dat.gz");
        backup.exportCSV(base + ".csv");

        backup.saveBinary(VERSION_DIR + "version_" + backup.getBackupId() + ".dat");

        System.out.println("\n✅ Backup created successfully!");
        System.out.println("Files created:");
        System.out.println(" • " + base + ".dat");
        System.out.println(" • " + base + ".dat.gz");
        System.out.println(" • " + base + ".csv");
        System.out.println("\nBackup ID: " + backup.getBackupId());
    }

    public List<BackupData> listVersions() throws Exception {
        List<BackupData> list = new ArrayList<>();
        File[] files = new File(VERSION_DIR).listFiles(f -> f.getName().endsWith(".dat"));
        if (files != null) {
            for (File f : files) list.add(restoreBinary(f.getPath()));
        }
        list.sort((a, b) -> b.getBackupTime().compareTo(a.getBackupTime()));
        return list;
    }
}

// ================= MAIN SYSTEM =================

public class DataBackupSystem {

    private static Scanner sc = new Scanner(System.in);
    private static BackupManager manager = new BackupManager();

    private static Map<String, Object> inputData() {
        Map<String, Object> data = new HashMap<>();
        while (true) {
            System.out.print("Enter key: ");
            String key = sc.next();

            System.out.print("Enter value: ");
            String value = sc.next();

            System.out.print("Type (String/Integer/Double/Boolean): ");
            String type = sc.next();

            Object val = switch (type) {
                case "Integer" -> Integer.parseInt(value);
                case "Double" -> Double.parseDouble(value);
                case "Boolean" -> Boolean.parseBoolean(value);
                default -> value;
            };

            data.put(key, val);

            System.out.print("Add more items? (y/n): ");
            if (!sc.next().equalsIgnoreCase("y")) break;
        }
        return data;
    }

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n=== DATA BACKUP SYSTEM ===");
            System.out.println("1. Create New Backup");
            System.out.println("2. Restore from Binary");
            System.out.println("3. Restore from Compressed");
            System.out.println("4. List All Versions");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int ch = sc.nextInt();

            try {
                switch (ch) {

                    case 1 -> {
                        System.out.print("\nEnter backup description: ");
                        sc.nextLine();
                        String desc = sc.nextLine();
                        Map<String, Object> data = inputData();
                        manager.createBackup(desc, data);
                    }

                    case 2 -> {
                        System.out.print("Enter binary file path: ");
                        BackupData b = manager.restoreBinary(sc.next());
                        System.out.println("\n✅ RESTORED BACKUP:\n" + b);
                        b.getData().forEach((k, v) ->
                                System.out.println("• " + k + " = " + v));
                    }

                    case 3 -> {
                        System.out.print("Enter compressed file path: ");
                        BackupData b = manager.restoreCompressed(sc.next());
                        System.out.println("\n✅ RESTORED BACKUP:\n" + b);
                    }

                    case 4 -> {
                        System.out.println("\n=== ALL BACKUP VERSIONS ===");
                        List<BackupData> versions = manager.listVersions();
                        int i = 1;
                        for (BackupData b : versions) {
                            System.out.println("\nVersion " + i++ + ":\n" + b);
                        }
                        System.out.println("\nTotal Versions: " + versions.size());
                    }

                    case 5 -> {
                        System.out.println("Backup system closed.");
                        return;
                    }

                    default -> System.out.println("Invalid choice!");
                }
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
            }
        }
    }
}
