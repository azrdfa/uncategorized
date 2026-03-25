HashMap

1. Tentukan Bucket Size dan Load Factor Treshold

    - Bucket Size: 16
    - Load Factor Treshold: 0.75

2. Tentukan Hash Function yang akan digunakan

    - index = key % capacity

3. Buat Node Class untuk Linked List
4. Buat HashMap Class dengan fungsi sebagai berikut

    - get
        - Jika bucket[index] tidak sama dengan key, traverse linked list sampai key sama
    - put
        - Jika bucket[index] tidak null, masukkan elemen pada tail linked list
        - Lakukan resize dan rehash jika Load Factor > Load Factor Treshold
    - remove

