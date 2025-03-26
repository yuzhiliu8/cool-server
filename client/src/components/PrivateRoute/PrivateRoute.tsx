import React from 'react'
import { API_ORIGIN } from '../../util/Constants';
import { APIResponse, Session } from '../../interfaces/APIResponse';
import { useNavigate } from 'react-router';

const PrivateRoute:React.FC<PrivateRouteProps> = ({ element }) => {
    const navigate = useNavigate();
    try{
        fetch(`${API_ORIGIN}/auth/authenticate`,{ credentials: "include",
        })
        .then((response) => {
            if (!response.ok){
                console.error("bad response!");
                navigate('/login');
            }
            return response.json();
        })
        .then((data:APIResponse<Session>) => {
            console.log(data);
        });
    } catch {
        console.error("Promise rejected");
    }

    return (
        element
    )
}

export default PrivateRoute;


interface PrivateRouteProps {
    element: React.ReactNode;
}