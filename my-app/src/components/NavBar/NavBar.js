import React from 'react';
import { Link } from "react-router-dom";
import {NavLink} from "react-router-dom";
import  "./NavBar.css";
import logo from './log1.svg';
import { useLocation } from "react-router-dom";

const NavBar = () => {
    //assigning location variable
    const location = useLocation();

    //destructuring pathname from location
    const { pathname } = location;
    const splitLocation = pathname.split("/");

    return(
        <nav className="navbar">
            <div><Link to="/" className="home"><img src={logo} alt="Logo" /></Link></div>
            <ul className="nav-links">
                <li className={splitLocation[1] === "train" ? "active" : ""}>
                <NavLink exact to="/train" className="train" activeClassName="train-active">
                    Trenuj
                </NavLink></li>
                <li className={splitLocation[1] === "care" ? "active" : ""}>
                <NavLink exact to="/care" className="care" activeClassName="care-active">
                    Opieka
                </NavLink></li>
                <li className={splitLocation[1] === "login" ? "active" : ""}>
                <NavLink to="/login" className="login" activeClassName="login-active">
                   ZALOGUJ / ZAŁÓŻ KONTO
                </NavLink></li>
            </ul>
        </nav>
    )

}
export default NavBar