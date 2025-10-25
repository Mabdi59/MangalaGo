import { Link } from 'react-router-dom';
import './Navigation.css';

const Navigation = () => {
  return (
    <nav className="navigation">
      <div className="nav-container">
        <Link to="/" className="nav-logo">
          <span className="logo-icon">✈️</span>
          <span className="logo-text">MangalaGo</span>
        </Link>
        <ul className="nav-menu">
          <li><Link to="/">Home</Link></li>
          <li><Link to="/trips">My Trips</Link></li>
          <li><Link to="/create-trip">New Trip</Link></li>
        </ul>
      </div>
    </nav>
  );
};

export default Navigation;
