BookFinder API Documentation
 Base URL
http://localhost:8081/api/books

SEARCH Books (Google Books API)

Method: GET
Endpoint:

/search?q={keyword}&limit={number}

 Example Request
GET http://localhost:8081/api/books/search?q=java&limit=3

 Example Response
[
  {
    "id": "x8BvqSRbR3cC",
    "volumeInfo": {
      "title": "Java For Dummies",
      "authors": ["Barry A. Burd"],
      "publisher": "John Wiley & Sons",
      "publishedDate": "2011-03-03",
      "description": "Start building powerful programs with Java 6—fast! ...",
      "imageLinks": {
        "smallThumbnail": "http://books.google.com/books/content?id=x8BvqSRbR3cC&printsec=frontcover&img=1&zoom=5",
        "thumbnail": "http://books.google.com/books/content?id=x8BvqSRbR3cC&printsec=frontcover&img=1&zoom=1"
      }
    }
  },
  {
    "id": "Sv36Gm741L0C",
    "volumeInfo": {
      "title": "Programming with Java",
      "authors": null,
      "publisher": "Pearson Education India",
      "publishedDate": "2008-09",
      "description": "Programming with Java is designed to help the reader understand ...",
      "imageLinks": {
        "smallThumbnail": "http://books.google.com/books/content?id=Sv36Gm741L0C&printsec=frontcover&img=1&zoom=5",
        "thumbnail": "http://books.google.com/books/content?id=Sv36Gm741L0C&printsec=frontcover&img=1&zoom=1"
      }
    }
  },
  {
    "id": "TRUdyfwdaSoC",
    "volumeInfo": {
      "title": "Java for Students",
      "authors": ["Doug Bell", "Mike Parr"],
      "publisher": "Pearson Educación",
      "publishedDate": "2001",
      "description": "A visual approach to learning Java with clear examples.",
      "imageLinks": {
        "smallThumbnail": "http://books.google.com/books/content?id=TRUdyfwdaSoC&printsec=frontcover&img=1&zoom=5",
        "thumbnail": "http://books.google.com/books/content?id=TRUdyfwdaSoC&printsec=frontcover&img=1&zoom=1"
      }
    }
  }
]

 ADD Favorite Book

Method: POST
Endpoint:

/favorites

 Request
POST http://localhost:8081/api/books/favorites

 Request Body
{
  "id": "zyTCAlFPjgYC",
  "volumeInfo": {
    "title": "Effective Java",
    "authors": ["Joshua Bloch"],
    "publisher": "Addison-Wesley",
    "publishedDate": "2018-01-06",
    "description": "A comprehensive guide to best practices in Java programming.",
    "imageLinks": {
      "thumbnail": "https://books.google.com/large1.jpg"
    }
  }
}

 Response
{
  "id": 1,
  "googleId": "zyTCAlFPjgYC",
  "title": "Effective Java",
  "authors": "Joshua Bloch",
  "publisher": "Addison-Wesley",
  "publishedDate": "2018-01-06",
  "description": "A comprehensive guide to best practices in Java programming.",
  "thumbnail": "https://books.google.com/large1.jpg"
}

 GET All Favorites

Method: GET
Endpoint:

/favorites

 Request
GET http://localhost:8081/api/books/favorites

 Response
[
  {
    "id": 1,
    "googleId": "def456",
    "title": "Head First Java",
    "authors": "Kathy Sierra, Bert Bates",
    "publisher": "O'Reilly Media",
    "publishedDate": "2005-02-09",
    "description": "A brain-friendly guide to learning Java programming.",
    "thumbnail": "https://books.google.com/large3.jpg"
  },
  {
    "id": 2,
    "googleId": "abc123",
    "title": "Clean Code",
    "authors": "Robert C. Martin",
    "publisher": "Prentice Hall",
    "publishedDate": "2008-08-11",
    "description": "A handbook of agile software craftsmanship.",
    "thumbnail": "https://books.google.com/large2.jpg"
  },
  {
    "id": 3,
    "googleId": "zyTCAlFPjgYC",
    "title": "Effective Java",
    "authors": "Joshua Bloch",
    "publisher": "Addison-Wesley",
    "publishedDate": "2018-01-06",
    "description": "A comprehensive guide to best practices in Java programming.",
    "thumbnail": "https://books.google.com/large1.jpg"
  }
]

 4️) DELETE Favorite Book

Method: DELETE
Endpoint:

/favorites/{googleId}

  Request
DELETE http://localhost:8081/api/books/favorites/zyTCAlFPjgYC
 Response
204 No Content

Record is deleted successfully 

 Summary Table
#	Operation	Method	Endpoint	Description
1️	Search Books	GET	/api/books/search?q=java&limit=3	Fetch books from Google Books API
2️	Add Favorite	POST	/api/books/favorites	Save book to DB
3️	View Favorites	GET	/api/books/favorites	Fetch all saved favorites
4️	Delete Favorite	DELETE	/api/books/favorites/{googleId}	Delete book by Google ID
