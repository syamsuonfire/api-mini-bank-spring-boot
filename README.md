# Hijrabank-test

1. Struktur table di PostgreSQL untuk menyimpan data-data tsb (Done)
2. Menyimpan data di table PostgreSQL (Done)
3. REST API dengan Spring Boot untuk <br>
   a Melihat daftar anggota di PostgreSQL (Done) <br>
   b. Menambah anggota di PostgreSQL (Done) <br>
   c. Melakukan transaksi dengan menyimpan ke PostgreSQL (Done) <br>
   d. Melihat history transaksi antara satu tanggal ke tanggal lain (Done) <br>
   e. Melihat transaksi satu anggota (Done) <br>
4. Lakukan test dengan PostMan (Terlampir postman collection)

# List API

Person
GET - api/persons
GET - api/persons/{id}
POST - api/persons
PUT - api/persons/{id}
DELETE - api/persons/{id}

Account
Create Account - POST - api/accounts/create/{personId}
Block Account - POST - api/accounts/create/{accountId}
Check Balance - GET - api/accounts/balance/{accountId}

Transaction
All Transactions - GET - api/transactions/{accountId}
History Transactions - GET - api/transactions/{accountId}/history
Debit - POST - api/transactions/{accountId}/debit/{amountToDebit}
Credit - POST - api/transactions/{accountId}/credit//{amountToCredit}
