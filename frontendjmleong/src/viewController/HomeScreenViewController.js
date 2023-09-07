import React, { useContext, useState} from 'react' 
import { AuthContext } from '../context/AuthContext'

export default HomeScreenViewController = () => {

    const { userInfo } = useContext(AuthContext);

    const [ automatePressed, setAutomateButtonPressed ] = useState(false);

    return {
        userInfo
    };

}

