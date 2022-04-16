import React  from 'react';
import { Button } from 'react-bootstrap';
import { Link } from "react-router-dom";

export const NavButton = (props) => (
    <div className="button-container">
        <Link to={props.route}>
            <Button vairant="primary" className="edit-button">
                { props.children }
            </Button>
        </Link>
    </div>
);

export default NavButton;