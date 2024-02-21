# Script cagirilirken girilen parametreleri kullanma ornegi:
echo "GIRILEN KELIMELER $1 VE $2"
word1=$1
word2=$2
returnStr=

 # fonksiyon tanimlama ornegi
fillArray()
{
    echo "Fonksiyon icinde main variable: "$word1
    echo "Fonk parametresi: $1"
    returnStr="Success"
    return 0
}

# Ornek fonksiyon cagirma sekli ve fonksiyona parametre gecme ve donen return degerlerini kullanma ornegi
fillArray "FONK_PARAM"
echo "Fonk dan donen result: $? VE returnStr degeri: $returnStr"

# Aritmetik islemler ornegi
 int1=33
int2=44
toplam=$(( int1 + int2 ))
echo “Sayılar toplamı: $toplam”

# bir komutun çıktısını değişkende saklama ornegi:
upperChar="A"

# Asagidaki komut ASCII degerini yani 65 rakamini ekrana basar:
printf '%d' "'$upperChar'"

# Bu 65 degerini bir degiskende saklamak icin italic tirnak isareti olan `` simgesini kullan
asciiValue=`printf '%d' "'$upperChar"`
echo ""
echo "$upperChar karakterinin ASCII degeri sudur: $asciiValue"
