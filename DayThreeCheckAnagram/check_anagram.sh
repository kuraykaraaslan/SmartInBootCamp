#!/bin/bash

# Function to check whether two words are anagrams
checkWhetherAnagram() {
    # Extracting the input words from command line arguments
    word1=$1
    word2=$2

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

        # Calculate ASCII value of each character
        asciiValue1=`printf '%d' "'$char1"`
        asciiValue2=`printf '%d' "'$char2"`

        # Calculate the product of prime numbers for each word
        product1=$(( product1 * asciiValue1 ))
        product2=$(( product2 * asciiValue2 ))
    done

    # Check if the products are equal
    if [ $product1 -eq $product2 ]; then
        echo "The words are anagrams."
    else
        echo "The words are not anagrams."
    fi

    return 0

}

checkWhetherAnagram $word1 $word2

