data class Book(val title: String, val author: String, val year: Int)

    class Library (private val books: List<Book> = emptyList()){
            fun addBook(book: Book): Library{
                return Library(books + book)
            }
        fun getBooks(): List<Book>{
            return books
        }
    }



fun main() {
    val initialLibrary = Library()
    var library = initialLibrary

    while (true) {
        println("1. Tambahkan buku")
        println("2. Tampilkan semua buku")
        println("3. Keluar")
        print("Pilih tindakan: ")

        when (readLine()?.toIntOrNull()) {
            1 -> {
                print("Judul buku: ")
                val title = readLine() ?: ""
                print("Penulis buku: ")
                val author = readLine() ?: ""
                print("Tahun terbit: ")
                val year = readLine()?.toIntOrNull() ?: 0

                val newBook = Book(title, author, year)
                library = library.addBook(newBook)
                println("Buku berhasil ditambahkan!")
            }
            2 -> {
                val books = library.getBooks()
                if (books.isEmpty()) {
                    println("Belum ada buku dalam perpustakaan.")
                } else {
                    println("Daftar buku:")
                    books.forEachIndexed { index, book ->
                        println("${index + 1}. ${book.title} by ${book.author}, ${book.year}")
                    }
                }
            }
            3 -> {
                println("Terima kasih!")
                return
            }
            else -> println("Pilihan tidak valid.")
        }
    }
}
