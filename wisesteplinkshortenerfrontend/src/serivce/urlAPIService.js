// services/urlService.js
import axios from 'axios';

export const shortenUrl = async (originalUrl) => {
  const response = await axios.post('http://localhost:7071/shorten', originalUrl, {
    headers: { 'Content-Type': 'text/plain' }
  });
  return response.data;
};
