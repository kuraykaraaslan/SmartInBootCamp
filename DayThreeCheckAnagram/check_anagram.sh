#!/bin/bash

# Extracting the input words from command line arguments
word1=$1
word2=$2

# Predefined array mapping Turkish letters to prime numbers
declare -A letter_prime_map=(
    ["A"]=2 ["B"]=3 ["C"]=5 ["Ç"]=7 ["D"]=11 ["E"]=13 ["F"]=17 ["G"]=19 ["Ğ"]=23 ["H"]=29 ["I"]=31 ["İ"]=37 ["J"]=41 ["K"]=43 ["L"]=47 ["M"]=53 ["N"]=59 ["O"]=61 ["Ö"]=67 ["P"]=71 ["R"]=73 ["S"]=79 ["Ş"]=83 ["T"]=89 ["U"]=97 ["Ü"]=101 ["V"]=103 ["Y"]=107 ["Z"]=109
)

# Check if the lengths of the words are equal
len1=${#word1}
len2=${#word2}
if [ $len1 -ne $len2 ]; then
    echo "Word lengths are not equal, hence they cannot be anagrams."
    return
fi

# Calculate product of prime numbers for each word
product1=1
product2=1
for ((i=0; i<len1; i++)); do
    char1=${word1:$i:1}
    char2=${word2:$i:1}
    
    # Check if the characters are in the letter_prime_map
    if [[ -v "letter_prime_map[$char1]" && -v "letter_prime_map[$char2]" ]]; then
        product1=$((product1 * ${letter_prime_map[$char1]}))
        product2=$((product2 * ${letter_prime_map[$char2]}))
    else
        echo "Invalid characters found in the words."
        return
    fi
done

# Check if the products are equal
if [ $product1 -eq $product2 ]; then
    echo "The given words are anagrams."
else
    echo "The given words are not anagrams."
fi



