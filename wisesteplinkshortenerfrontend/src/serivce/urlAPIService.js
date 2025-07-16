// services/urlService.js
import axios from 'axios';

export const shortenUrl = async (originalUrl) => {
  const response = await axios.post(`${process.env.REACT_APP_BASE_URL}shorten`, originalUrl, {
    headers: { 'Content-Type': 'text/plain' }
  });
  return response;
};
