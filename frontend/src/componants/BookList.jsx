import React from "react";
import BookCard from './BookCard'

const BookList = ({ books }) => {
  if (books.length === 0)
    return <p className="no-results">No books found. Try searching!</p>;

  return (
    <div className="book-list">
      {books.map((book) => (
        <BookCard key={book.id} book={book} />
      
      
      ))}
    </div>
  );
};

export default BookList;
