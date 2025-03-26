import { API_ORIGIN } from '../../util/Constants';
import { useNavigate } from 'react-router';

function RootRedirect() {
    const navigate = useNavigate();

    fetch(`${API_ORIGIN}/api/auth/authenticate`, {
        credentials: "include",
    }) 
    .then((response) => {
        if (!response.ok){
            console.error("not authenticated!");
            navigate("/login");
        }

        navigate("/home");
    });

    return (
    <></>
  )
}

export default RootRedirect;