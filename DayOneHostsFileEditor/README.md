# Hosts File Editor

Hosts File Editor is a simple command-line utility written in Java for managing the hosts file on your system. It allows users to add, update, and remove entries in the hosts file, which maps domain names to IP addresses. Additionally, it provides functionality to back up and restore the hosts file.

## Features

- **Add or Update Hosts:** Easily add new entries to the hosts file or update existing ones by providing a domain name and its corresponding IP address.

- **Remove Hosts:** Remove entries from the hosts file by specifying the domain name to be removed.

- **Save Changes:** Save any modifications made to the hosts file.

- **Backup and Restore:** Create a backup of the hosts file before making changes, and restore it if needed.

- **Validation:** Validate domain names and IP addresses using regular expressions to ensure data integrity.

- **Simple Menu Interface:** Provides a straightforward text-based menu interface for easy interaction with the program.

## How to Use

1. **Compile the Program:**

    ```bash
    cd src
    javac Main.java
    ```

    Or use the provided release JAR file:

    ```bash
    cd releases
    java -jar DayOneHostsFileEditor.jar
    ```


2. **Follow the On-Screen Instructions:**
- Choose from options to add/update a host, remove a host, save changes, or exit.
- Follow the prompts to provide domain names and IP addresses.

3. **Customize Hosts File Path (Optional):**
- When prompted, you can specify a custom path for the hosts file. Press Enter to use the default path ("./hosts") or enter "x" to cancel.

4. **Make Changes and Save:**
- After making changes, choose the "Save Changes" option from the menu to write modifications to the hosts file.

5. **Backup and Restore (Optional): NOT YET IMPLEMENTED**
- Use the backup and restore options to create a backup of the hosts file before making changes and restore it if needed.

## Requirements

- Java Development Kit (JDK) installed on your system.

## Contributing

Contributions are welcome! If you find any issues or have suggestions for improvements, please open an issue or create a pull request.

## License

This project has no license and is open for public use and modification. You can use, modify, and distribute this project freely.


