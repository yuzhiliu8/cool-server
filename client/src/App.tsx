import './App.css'
import { BrowserRouter, Routes, Route } from 'react-router'
import LoginPage from './routes/LoginPage/LoginPage';
import HomePage from './routes/HomePage/HomePage';

const basepath = import.meta.env.VITE_BASEPATH;
function App() {

  return (
    <div className="app">
    <BrowserRouter basename={basepath}>
      <Routes>
        <Route path="/" element={<LoginPage />} />
        <Route path="/home" element={<HomePage />} />
      </Routes> 
    </BrowserRouter>

    </div>
  )
}

export default App
