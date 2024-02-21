# Anagram Checker

This Bash script checks if two words provided as command line arguments are anagrams of each other. Anagrams are words that have the same letters in a different order.

## Usage

To use this script, follow these steps:

1. Open a terminal.
2. Navigate to the directory containing the script (`check_anagram.sh`).
3. Run the script by providing two words as command line arguments.

Example:
```
bash check_anagram.sh "listen" "silent"
```

## Requirements

This script requires Bash to be installed on your system.

## How it Works

1. The script extracts the two words from the command line arguments.

2. It initializes an associative array `letter_prime_map` where Turkish letters are mapped to prime numbers.

3. It checks if the lengths of the two words are equal. If not, it prints a message and exits.

4. It calculates the product of prime numbers for each word by iterating over each letter of the words and looking up the corresponding prime number in the `letter_prime_map`.

5. Finally, it compares the products of prime numbers for the two words. If they are equal, it prints a message indicating that the words are anagrams; otherwise, it prints a message indicating that they are not anagrams.

## Note

- This script is specifically designed to handle Turkish characters due to the mapping of Turkish letters to prime numbers in the `letter_prime_map`.

## License

You are free to use, modify, and distribute this script as you wish. No attribution is required.