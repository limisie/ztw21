import * as React from "react"
import PropTypes from "prop-types"
import { Link } from "gatsby"
import { FaBars } from 'react-icons/fa';

const Header = ({ siteTitle }) => (
  <header>
    <nav className="navbar">
      <Link to="/" activeClassName="navbar__logo" className="navbar__logo">{ siteTitle }</Link>
      <div class="navbar__bars">
        <FaBars />
      </div>
      <div className="navbar-menu">
        <Link to="/" activeClassName="navbar-menu__link" className="navbar-menu__link">Home</Link>
        <Link to="/blog/" activeClassName="navbar-menu__link" className="navbar-menu__link">Blog</Link>
        <Link to="/about/" activeClassName="navbar-menu__link" className="navbar-menu__link">About us</Link>
        <Link to="/contact/" activeClassName="navbar-menu__button" className="navbar-menu__button">Contact</Link>
      </div>
    </nav>
  </header>
)

Header.propTypes = {
  siteTitle: PropTypes.string,
}

Header.defaultProps = {
  siteTitle: `Static blog`,
}

export default Header
