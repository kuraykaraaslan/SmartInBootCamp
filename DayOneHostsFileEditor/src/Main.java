import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.logging.Logger;
import java.lang.String;

public class Main {

    private Scanner scanner = new Scanner(System.in);
    private String hostsFilePath = "./hosts";

    private HashMap<String, String> hosts = new HashMap<>(); // key: domain, vaule: ip

    /**
     * This method clears the console screen by sending specific ANSI escape codes.
     */

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /*
     * This method validates a domain name using a regular expression.
     */
    public boolean isDomainValid(String domain) {
        //TODO: Implement this method
        return true;
        /*
        String domainRegex = "^((?!-))(xn--)?[a-z0-9][a-z0-9-_]{0,61}[a-z0-9]{0,1}\\.(xn--)?([a-z0-9\\-]{1,61}|[a-z0-9-]{1,30}\\.[a-z]{2,})$\r\n";
        Pattern pattern = Pattern.compile(domainRegex);
        Matcher matcher = pattern.matcher(domain);
        return matcher.matches();
        */
    }

    /*
     * This method validates an IP address using a regular expression.
     */
    public boolean isIpValid(String ip) {
        String ipRegex = "^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
        Pattern pattern = Pattern.compile(ipRegex);
        Matcher matcher = pattern.matcher(ip);
        return matcher.matches();
    }

    /*
     * This method reads from hosts file and populates the hosts HashMap.
     */
    public void readHostsFile() {
        File file = new File(hostsFilePath);
        if (!file.exists()) {
            System.out.println("File not found Create a new one? (y/n)");
            while (true) {
                String choice = scanner.nextLine();
                if (choice.equals("y")) {
                    try {
                        file.createNewFile();
                        System.out.println("File created");
                    } catch (Exception e) {
                        System.out.println("An error occurred.");
                    }
                    break;
                } else if (choice.equals("n")) {
                    System.out.println("Exiting...");
                    System.exit(0);
                    return;
                }
            }
        }


        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (line.startsWith("#")) {
                    continue;
                }

                String[] parts = line.split("\\s+");

                if (parts.length != 2) {
                    System.out.println("Invalid line: " + line);
                    continue;
                }

                String ip = parts[0];
                String domain = parts[1];

                if (!isIpValid(ip)) {
                    System.out.println("Invalid IP: " + ip);
                    continue;
                }

                if (!isDomainValid(domain)) {
                    System.out.println("Invalid domain: " + domain);
                    continue;
                }

                hosts.put(domain, ip);

            }

            System.out.println("File read successfully, total hosts: " + hosts.size());

            scanner.close();

        } catch (Exception e) {
            System.out.println("An error occurred.");
        }


    }

    /*
     * This method writes the hosts HashMap to the hosts file.
     */
    public void printHostsFile() {
        try
        {
            File file = new File(hostsFilePath);

            if (!file.exists()) {
                file.delete();
            }

            FileWriter fileWriter = new FileWriter(file);

            for (String domain : hosts.keySet()) {
                String ip = hosts.get(domain);
                fileWriter.write(ip + " " + domain + "\n");
            }

            fileWriter.close();

        } catch (Exception e) {

            System.out.println("An error occurred.");
        }

    }

    /*
     * This method adds or updates a host.
     */
    public void addOrUpdateHost() {
        String domain = "";
        String ip = "";

        while (true) {
            System.out.println("Enter the domain name: (x to cancel)");
            domain = scanner.nextLine();

            if (domain.equals("x")) {
                return;
            }

            if (!isDomainValid(domain)) {
                System.out.println("Invalid domain");
                continue;
            } else {
                break;
            }


        }

        while (true) {
            System.out.println("Enter the IP address: (x to cancel)");
            ip = scanner.nextLine();

            if (ip.equals("x")) {
                return;
            }

            if (!isIpValid(ip)) {
                System.out.println("Invalid IP");
                continue;
            } else {
                break;
            }
        }

        // confirm
        while (true) {
            System.out.println("Add " + ip + " " + domain + "? (y/n)");
            String confirm = scanner.nextLine();

            if (confirm.equals("y")) {
                hosts.put(domain, ip);
                System.out.println("Host added");
                return;
            } else if (confirm.equals("n")) {
                return;
            }
        }
    }

    /*
     * This method removes a host.
     */
    public void removeHost() {
        String domain = "";

        while (true) {
            System.out.println("Enter the domain name: (x to cancel)");
            domain = scanner.nextLine();

            if (domain.equals("x")) {
                return;
            }

            if (!isDomainValid(domain)) {
                System.out.println("Invalid domain");
                continue;
            } else {
                break;
            }
        }

        if (!hosts.containsKey(domain)) {
            System.out.println("Domain not found");
            return;
        }

        // confirm
        while (true) {
            System.out.println("Remove " + domain + "? (y/n)");
            String confirm = scanner.nextLine();

            if (confirm.equals("y")) {
                hosts.remove(domain);
                System.out.println("Host removed");
                return;
            } else if (confirm.equals("n")) {
                return;
            }
        }
    }

    /*
     * This method saves the changes to the hosts file.
     */
    public void saveChanges() {
        printHostsFile();
    }

    /*
     * This method backs up the hosts file.
     */
    public void backupHostsFile() {
        File file = new File(hostsFilePath);
        File backupFile = new File(hostsFilePath + ".bak");

        if (backupFile.exists()) {
            backupFile.delete();
        }

        file.renameTo(backupFile);
    }

    /*
     * This method restores the hosts file from the backup.
     */
    public void restoreHostsFile() {
        File file = new File(hostsFilePath);
        File backupFile = new File(hostsFilePath + ".bak");

        if (file.exists()) {
            file.delete();
        }

        backupFile.renameTo(file);
    }

    /*
     * Show the main menu.
     */
    public void showMenu() {
        while (true) {
            clearScreen();
            System.out.println("1. Add or update a host");
            System.out.println("2. Remove a host");
            System.out.println("3. Save Changes");
            System.out.println("4. Exit");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addOrUpdateHost();
                    break;
                case "2":
                    removeHost();
                    break;
                case "3":
                    saveChanges();
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }

    /*
     * This method is the entry point of the application.
     */
    public void Editor() {
        // Ask for hosts file path
        System.out.println("Enter the hosts file path: Default is ./hosts (ENTER to use default, x to cancel)");
        String input = scanner.nextLine();

        if (input.equals("x")) {
            System.out.println("Exiting...");
            System.exit(0);
            return;
        }

        if (!input.equals("")) {
            hostsFilePath = input;
        }

        // Read hosts file
        readHostsFile();

        // Show main menu
        showMenu();

    }

    public static void main(String[] args) {
        Main editor = new Main();
        editor.Editor();
    }
}
