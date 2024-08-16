
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import HomePage from './routes/HomePage';
import LoginPage from './routes/LoginPage';
import './App.css'

function App() {

  return (
    <div className="App">
    <Router>
      <Routes>
        <Route path='/' element={<LoginPage />}/>
        <Route path='/home' element={<HomePage />}/>
      </Routes>

    </Router>
    
    
    </div>
  )
}

export default App
