import error from '../../assets/error.png';
import './LoginError.css';


const LoginError:React.FC<LoginErrorProps> = ({ msg }) => {
  return (
    <div className="login-error">
      <img className="le-error-icon" src={error}/>
      {msg}
    </div>
  )
}

export default LoginError;

interface LoginErrorProps{
  msg: string;
}
