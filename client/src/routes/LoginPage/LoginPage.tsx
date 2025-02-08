import './LoginPage.css';
import { useRef, useState } from 'react';
import { API_ORIGIN } from '../../util/Constants';
import LoginError from '../../components/ErrorMsg/LoginError';
import { AuthResponse } from '../../interfaces/AuthResponse';



export default function LoginPage() {
  const [ErrorMsg, setErrorMsg] = useState<string>("");
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
    const authResponse:AuthResponse = await response.json();
    if (!response.ok){
      if (response.status === 401){
        setErrorMsg(authResponse.message);
        return;
      }
    }

    setErrorMsg("");
    console.log(authResponse);
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

            {(ErrorMsg !== "") ? (
                <LoginError msg={ErrorMsg}/>
              ) : (
                <></>
              )}

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
