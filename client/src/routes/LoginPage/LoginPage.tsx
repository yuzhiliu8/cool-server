import './LoginPage.css';
import { useRef } from 'react';
import { API_ORIGIN } from '../../util/Constants';


export default function LoginPage() {
  const emailRef = useRef<HTMLInputElement>(null);
  const passwordRef = useRef<HTMLInputElement>(null);

  const handleLogin = async () : Promise<void> => {
    const email:string = (emailRef.current as HTMLInputElement).value;
    const password:string = (passwordRef.current as HTMLInputElement).value;

    const formData = new FormData();
    formData.append("email", email);
    formData.append("password", password);

    const response:Response = await fetch(`${API_ORIGIN}/auth/login`, {
      method: "POST",
      body: formData,
      credentials: "include",
    });

    const data = await response.json();
    console.log(data);
  }
  return (
    <div className="login-page">
      <div className="lp-header">Cool Server</div>
      <div className="lp-login-box">
        <div className="lp-lb-marg">
          <div className="lp-lb-1">Sign in</div>
          <div className="lp-lb-form">
            <input ref={emailRef} className="lp-form-input" type="email" placeholder='Enter Email'/>
            <input ref={passwordRef} className="lp-form-input" type="password" placeholder='Enter Password'/>
              <div className="lp-remember-me">
                <input className="lp-checkbox" type= "checkbox" />
                <div className="lp-checkbox-label" >Remember Me</div>
              </div>
            <button className="lp-login-btn" onClick={handleLogin}>Login</button>
          </div>
        </div>
      </div>
    </div>
  )
}
