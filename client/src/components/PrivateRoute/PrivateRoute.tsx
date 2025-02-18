import React from 'react'
import { Route } from 'react-router'

const PrivateRoute:React.FC<PrivateRouteProps> = ({ path, element}) => {
    console.log(path, element); 
    return (
        <Route path={path} element={element}
        
        
        
        
        />

    )
}

export default PrivateRoute;


interface PrivateRouteProps {
    path: string;
    element: React.ReactNode;
}