# Revizeedilmiş halindeki kodda,
# 1. Kelimelerin uzunlukları eşit olmadığında anagram olamayacağına dair bir kontrol ekledim.
# 2. Kelimelerin her bir karakterinin ascii değerini alıp, bu değerleri bir associative array'e atadım.
# 3. İki associative array'deki her bir key için, value'lerin eşit olup olmadığını kontrol ettim.
# 4. Eğer value'ler eşit değilse, anagram olmadığını belirttim.
# 5. Eğer value'ler eşitse, anagram olduğunu belirttim.


# https://stackoverflow.com/questions/20988848/how-to-use-the-single-dimensional-array-in-shell-script-and-display-the-array
# Stackoverflow'da öğrendiğimle shell array 2 türe sahip bir array.
# 1. numerical indexli array
# 2. associative array
# Bir associative array oluşturup bunu çözebilirim.

# https://linuxsimply.com/bash-scripting-tutorial/array/declare-create-initialize-array/#:~:text=To%20declare%20an%20empty%20array%20in%20Bash%2C%20you%20can%20use,you%20can%20assign%20values%20later.
# boş iki adet associative array oluşturuyorum.

declare -A result1
declare -A result2

# kelimeleri bash argümandan alıyorum.
word1=$1
word2=$2

check_anagram() 
{
    # kelimelerin uzunluklarını alıyorum.
    # https://stackoverflow.com/questions/17368067/length-of-string-in-bash
    len1=${#1}
    len2=${#2}

    # https://www.digitalocean.com/community/tutorials/if-else-in-shell-scripts
    # kelimelerin uzunlukları eşit değilse anagram olamaz.
    if [ $len1 != $len2 ]
    then
        echo "Not an anagram"
        exit 1
    fi

    # https://stackoverflow.com/questions/10551981/how-to-perform-a-for-loop-on-each-character-in-a-string-in-bash
    # kelimenin her bir karakterini alıyorum.
    for (( i=0; i<${len1}; i++ ))
    do

        # https://stackoverflow.com/questions/10551981/how-to-perform-a-for-loop-on-each-character-in-a-string-in-bash
        # kelimenin her bir karakterini alıyorum.

        char1=${word1:$i:1}
        char2=${word2:$i:1}


        # Verilen ödevdeki gibi her bir karakterin ascii değerini alıyorum.
        ascii1=$(printf "%d" "'$char1")
        ascii2=$(printf "%d" "'$char2")

        # https://linuxsimply.com/bash-scripting-tutorial/array/associative-array/#:~:text=There%20are%20many%20ways%20to,%5D%7D%E2%80%9D%20is%20non%2Dzero.
        # kelimenin her bir karakterini associative array'e atıyorum.
        if [[ -n "${result1[$ascii1]}" ]]
        then
            current=${result1[$ascii1]}
            result1[$ascii1]=$((current+1))
        else
            result1[$ascii1]=1
        fi

        if [[ -n "${result2[$ascii2]}" ]]
        then
            current=${result2[$ascii2]}
            result2[$ascii2]=$((current+1))
        else
            result2[$ascii2]=1
        fi
    done
    
    # https://stackoverflow.com/questions/3112687/how-to-iterate-over-associative-arrays-in-bash
    # associative array'lerde döngü yapabilmek için bu şekilde döngü yapılır.
    for key in "${!result1[@]}"
    do 
        # check if the key exists in the second array
        if [[ -n "${result2[$key]}" ]]
        then
            # check if the values are equal
            if [[ ${result1[$key]} != ${result2[$key]} ]]
            then
                echo "Not an anagram"
                exit 0
            fi
        else
            echo "Not an anagram"
            exit 0
        fi
    done

    echo "Anagram"
    exit 0

}

check_anagram $1 $2
