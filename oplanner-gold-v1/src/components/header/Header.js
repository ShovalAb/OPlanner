import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faVideoSlash } from "@fortawesome/free-solid-svg-icons";
import Button from "react-bootstrap/Button";
import Container from "react-bootstrap/Container"
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import {NavLink} from "react-router-dom";
import NavbarBrand from "react-bootstrap/esm/NavbarBrand";
import NavbarToggle from "react-bootstrap/esm/NavbarToggle";
import NavbarCollapse from "react-bootstrap/esm/NavbarCollapse";

const Header = () => {
 
    return (
        <Navbar>
            <Container fluid>
                <NavbarBrand herf="/" style={{"color": 'gold'}}>
                    <FontAwesomeIcon icon={faVideoSlash}/> gold
                </NavbarBrand>
                <NavbarToggle aria-controls="navbarScroll"/>
                <NavbarCollapse id="navbarScroll">
                <Nav
                        className="me-auto my-2 my-lg-0"
                        style={{maxHeight: '100px'}}
                        navbarScroll
                    >
                    <NavLink className ="nav-link" to="/">Home</NavLink>
                    <NavLink className ="nav-link" to="/watchList">Watch List</NavLink>      
                </Nav>
                <Button variant="outline-info" className="me-2">Login</Button>
                <Button variant="outline-info">Register</Button>
                </NavbarCollapse>
            </Container>
        </Navbar>
      )
    }
    
    export default Header