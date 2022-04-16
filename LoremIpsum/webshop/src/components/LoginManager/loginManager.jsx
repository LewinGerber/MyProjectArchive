import { React, useState, useEffect } from 'react';
import axios from 'axios';

import './loginManagerStyle.css';
import { Button, TextField } from '@material-ui/core';

export const Login = (props) => {
    //email of the user
    const [email, setEmail] = useState('');
    //password of the user
    const [password, setPassword] = useState('');
    //boolean to see if user is logged in or not
    const [isLoggedIn, setIsLoggedIn] = useState(false);

    /*
        ////////////////////////////////
        verify user login data
    */
    async function verifyLoginData(e) {
        e.preventDefault();
        try {
            //email and password from state
            const result = await axios.post("/api/authenticateUser", {
                email,
                password,
            });
            // save the token into the local storage
            localStorage.setItem("lit", result.data);
            //set token in header
            axios.defaults.headers.common[
                "Authorization"
            ] = localStorage.getItem("lit");
            props.loadCartCode();
            tryLogin();
        } catch (err) { console.error(err); }
    }

    /*
        ////////////////////////////////
        automatically login user if there is already a token in the local storage
    */
    async function tryLogin() {
        try {
            // call to backend to see if user is authenticated (true/ false)
            if(localStorage.getItem("lit") !== undefined) {
                axios.defaults.headers.common[
                    "Authorization" ] = localStorage.getItem("lit");
                const result = await axios.get('/api/isAuthenticated');
                //set the status of the user
                if(result.data) {
                    props.loadCartCode();
                    setIsLoggedIn(true);
                } 
            } else {
                logout();
            }
        } catch (e) { }
    }

    function logout() {
        setIsLoggedIn(false);
        localStorage.removeItem("lit");
        //localStorage.removeItem("lit");
        tryLogin();
    }

    /*
        ////////////////////////////////
        on load
    */
    useEffect(() => {
        props.loadCartCode();
        tryLogin();
    }, []);

    return (
        <div>
            {
                !isLoggedIn ?
                <div id="login">
                <div id="tf">
                    <TextField  variant="outlined" value={email} color="default" onChange={(e) => setEmail(e.target.value)} label="Email" />
                </div>
                <div id="tf">
                    <TextField variant="outlined" type="password" value={password} color="default" onChange={(e) => setPassword(e.target.value)} label="Password" />
                </div>
                <div id="login-btn-container">
                    <Button  variant="contained" onClick={(e) => verifyLoginData(e)}>LOGIN</Button>
                </div>
                </div> : <div id="logged-in"><p>Sie sind angemeldet</p><Button variant="contained" onClick={(e) => logout()}> ABMELDEN </Button></div>
            }
        </div>
    );
}

export default Login;