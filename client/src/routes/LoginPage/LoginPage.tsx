import './LoginPage.css';
import React, { useRef, useState } from 'react';
import { API_ORIGIN } from '../../util/Constants';
import { APIResponse, Session } from '../../interfaces/APIResponse';
import { useNavigate } from 'react-router';
import LoginError from '../../components/ErrorMsg/LoginError';



export default function LoginPage() {
  const [ErrorMsg, setErrorMsg] = useState<string>("");
  const emailRef = useRef<HTMLInputElement>(null);
  const passwordRef = useRef<HTMLInputElement>(null);
  const navigate = useNavigate();

  const handleLogin = async (e: React.FormEvent | React.MouseEvent) : Promise<void> => {
    e.preventDefault();
    const email:string = emailRef.current?.value ?? "";
    const password:string = passwordRef.current?.value ?? "";

    if (email === "" || password === ""){
      setErrorMsg("Please Fill Out Fields");
      return;
    }

    const formData = new FormData();
    formData.append("email", email);
    formData.append("password", password);

    const response:Response = await fetch(`${API_ORIGIN}/auth/login`, {
      method: "POST",
      body: formData,
      credentials: "include",
    });

    const apiResponse:APIResponse<Session> = await response.json();

    if (!response.ok){
      if (response.status === 401){
        setErrorMsg(apiResponse.message);
        passwordRef.current!.value = "";
        passwordRef.current!.focus();
        return;
      }
    }

    setErrorMsg("");
    console.log(apiResponse);
    navigate("/home");
  }
  return (
    <div className="login-page">
      <div className="lp-header">Cool Server</div>
      <div className="lp-login-box">
        <div className="lp-lb-marg">
          <div className="lp-lb-1">Sign in</div>
          <form className="lp-lb-form" onSubmit={handleLogin}>
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
          </form>
        </div>
      </div>
    </div>
  )
}
