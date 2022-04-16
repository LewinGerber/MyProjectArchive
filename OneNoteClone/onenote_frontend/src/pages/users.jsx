import React, { useState, useEffect } from 'react';
import { Button, ButtonGroup, Table, Container, Form } from 'react-bootstrap';
import { HiOutlineHome } from 'react-icons/hi';

import NavButton from '../components/navButton.jsx';
import '../assets/styles/users.css';
import axios from 'axios';

export const Users = () => {
    const [users, setUsers] = useState([]);
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState([]);
    const [editEmail, setEditEmail] = useState("");
    const [editPassword, setEditPassword] = useState("");
    const [editId, setEditId] = useState(0);

    useEffect(() => {
        getUsers();
    }, []);

    const getUsers = () => {
        axios.get('/api/user/all')
            .then((res) => {
                setUsers([...res.data])
            })
            .catch((err) => {
                console.log(err)
            });
    }

    const editUser = (id) => {
        users.map((user) => {
            if (user.id === id) {
                setEditEmail(user.email);
                setEditPassword("");
                setEditId(id);
            }
        });
    }

    const deleteUser = (id) => {
        axios.delete('/api/user/' + id)
            .then((res) => {
                getUsers();
            })
            .catch((err) => {
                console.log(err)
            });
    }

    const handleCreateUser = (event) => {
        event.preventDefault();

        axios.post('/api/user/register/', 
            {"id": "", "email": email, "password" : password}
        ).then((res) => {
            getUsers();
        })
        .catch((err) => {
            console.log(err)
        });
    }

    const handleEditUser = (event) => {
        event.preventDefault();
        axios.post('/api/user/', 
            {"id": editId, "email": editEmail, "password" : editPassword}
        ).then((res) => {
            getUsers();
        })
        .catch((err) => {
            console.log(err)
        });
    }

    return (
        <Container>
            <NavButton route="/">
                <HiOutlineHome className="icon" />
            </NavButton>
            <div style={{marginTop: '5rem'}}>
                <h2 style={{fontWeight: 'bold'}}>Manage Users</h2>
            </div>
            <div className="user-form">
                <div>
                    <p>Create user</p>
                    <Form onSubmit={handleCreateUser}>
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
                        <Button variant="success" type="submit" style={{ float: 'right' }}>
                            CREATE SAVE
                        </Button>
                    </Form>
                </div>
                <div>
                <p>Edit user</p>
                    <Form onSubmit={handleEditUser}>
                        <Form.Group className="mb-3" controlId="formBasicEmail">
                            <Form.Control
                                type="email"
                                placeholder="Email"
                                value={editEmail}
                                onChange={(e) => setEditEmail(e.target.value)}
                            />
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="formBasicPassword">
                            <Form.Control
                                type="password"
                                placeholder="Password"
                                value={editPassword}
                                onChange={(e) => setEditPassword(e.target.value)}
                            />
                        </Form.Group>
                        <Button variant="info" type="submit" style={{ float: 'right' }}>
                            EDIT SAVE
                        </Button>
                    </Form>
                </div>
            </div>
            <div className="user-table-container">
                <Table striped bordered hover>
                    <thead>
                        <tr>
                            <th className="ut-first-col">ID</th>
                            <th>Email</th>
                            <th>Password</th>
                            <th>Role</th>
                            <th className="ut-last-col">Options</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            users.map((user) =>
                                <tr key={user.id} className="ut-row">
                                    <td>{ user.id }</td>
                                    <td>{ user.email }</td>
                                    <td>{ user.password }</td>
                                    <td>{ user.userRole.roleName }</td>
                                    <td>
                                        <ButtonGroup>
                                            <Button variant="success" onClick={() => editUser(user.id)}>edit</Button>
                                            <Button variant="danger" onClick={() => deleteUser(user.id)}>delete</Button>
                                        </ButtonGroup>
                                    </td>
                                </tr>
                            )
                        }
                    </tbody>
                </Table>
            </div>
        </Container>
    );
}

export default Users;