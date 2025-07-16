import { useState } from "react";
import { shortenUrl } from "../serivce/urlAPIService";
import '../assets/css/UrlShortener.css'

const UrlShortener = () => {

    const handleShorten = async () => {
        setShortUrl('');
        setMessage('');
        if(inputUrl){
            try {
                const result = await shortenUrl(inputUrl)
                console.log(result)
                if (result.status === 200) {
                    setShortUrl(`${process.env.REACT_APP_BASE_URL}${result.data}`);
                    setMessage('');
                } else {
                    setMessage(result.data);
                }  
            } catch (err) {
                setMessage(err.response?.data || "Something went wrong");
            }
        }
    };

    const [inputUrl, setInputUrl] = useState('');
    const [shortUrl, setShortUrl] = useState('');
    const [message, setMessage] = useState('');

    return (
        <div className='container'>
            <div className='titleParent'>
                <h1 className='title'>URL Shortener</h1>
                <div className='inputWrapper'>
                    <input
                        type="text"
                        value={inputUrl}
                        onChange={(e) => setInputUrl(e.target.value)}
                        placeholder="Enter a URL"
                        className='input'
                    />
                    <button onClick={handleShorten} className='button'>Shorten</button>
                </div>
                {shortUrl && <p className='result'><a href={shortUrl}>{shortUrl}</a></p>}
                {message && <p className='message'>{message}</p>}
            </div>
        </div>
    );
}

export default UrlShortener;