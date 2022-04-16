import React, { useState } from 'react';
import '../assets/styles/home.css';
import { Form, Button } from 'react-bootstrap';
import { useHistory } from "react-router-dom";
import axios from 'axios';
import { FiEdit2 } from 'react-icons/fi';
import NavButton from '../components/navButton.jsx';

export const Home = () => {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    let history = useHistory();

    function handleLoginSuccess(token) {
        localStorage.setItem("ON_TOKEN", token);
        history.push("/note");
    }

    function handleSubmit(event) {
        event.preventDefault();
        axios.post('/api/login/', 
            {"id": "", "email": email, "password" : password}
        ).then((res) => {
            handleLoginSuccess(res.data);
        })
        .catch((err) => {
            console.log(err);
        });
    }

    return (
        <div className="main-container">
            <NavButton route="/users">
                <FiEdit2 className="icon" />
            </NavButton>
            <div className="login-container">
                <div className="onenote-logo-container"></div>
                <div>
                    <Form onSubmit={handleSubmit}>
                        <Form.Group className="mb-3" controlId="formBasicEmail">
                            <Form.Control
                                type="email"
                                placeholder="Email"
                                value={email}
                                onChange={(e) => setEmail(e.target.value)}
                            />
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="formBasicPassword">
                            <Form.Control
                                type="password"
                                placeholder="Password"
                                value={password}
                                onChange={(e) => setPassword(e.target.value)}
                            />
                        </Form.Group>
                        <Button variant="primary" type="submit" className="submit-button">
                            LOGIN
                        </Button>
                    </Form>
                </div>
            </div>
        </div>
    );
}

export default Home;