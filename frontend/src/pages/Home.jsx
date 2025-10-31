import React, { useState, useEffect } from "react";
import Searchbar from "../componants/Searchbar";
import BookList from "../componants/BookList";

const Home = () => {
  const [books, setBooks] = useState([]);
 
  const API_URL = import.meta.env.VITE_API_URL;

  const fetchBooks = async () => {
    try {
      const response = await fetch(`${API_URL}/books/favorites`);
      if (!response.ok) {
        throw new Error("Failed to fetch books");
      }
      const data = await response.json();
      setBooks(data);
    } catch (error) {
      console.error("Error:", error);
    }
  };

  useEffect(() => {
    fetchBooks();
  }, []);

  const handleSearch = (query) => {
    const filtered = books.filter((b) =>
      b.title.toLowerCase().includes(query.toLowerCase())
    );
    setBooks(filtered);
  };

  return (
    <div className="home">
      <Searchbar onSearch={handleSearch} />
      <BookList books={books} />
    </div>
  );
};

export default Home;
