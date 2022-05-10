# Minibank-backend

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

Person<br>
GET - api/persons<br>
GET - api/persons/{id}<br>
POST - api/persons<br>
PUT - api/persons/{id}<br>
DELETE - api/persons/{id}<br>

Account<br>
Create Account - POST - api/accounts/create/{personId}<br>
Block Account - POST - api/accounts/create/{accountId}<br>
Check Balance - GET - api/accounts/balance/{accountId}<br>

Transaction<br>
All Transactions - GET - api/transactions/{accountId}<br>
History Transactions - GET - api/transactions/{accountId}/history<br>
Debit - POST - api/transactions/{accountId}/debit/{amountToDebit}<br>
Credit - POST - api/transactions/{accountId}/credit//{amountToCredit}<br>
