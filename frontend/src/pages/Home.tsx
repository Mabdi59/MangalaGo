import { Link } from 'react-router-dom';
import './Home.css';

const Home = () => {
  return (
    <div className="home">
      <section className="hero">
        <div className="hero-content">
          <h1>Welcome to MangalaGo</h1>
          <p className="hero-subtitle">Your Modern Travel Planner</p>
          <p className="hero-description">
            Organize flights, hotels, activities, budgets, and packing lists all in one place.
            With AI-powered suggestions to make your trip planning easier.
          </p>
          <div className="hero-actions">
            <Link to="/create-trip" className="btn btn-primary">Plan Your Trip</Link>
            <Link to="/trips" className="btn btn-secondary">View My Trips</Link>
          </div>
        </div>
      </section>

      <section className="features">
        <h2>Features</h2>
        <div className="features-grid">
          <div className="feature-card">
            <span className="feature-icon">✈️</span>
            <h3>Flight Management</h3>
            <p>Track all your flights with departure times, airlines, and booking details</p>
          </div>
          <div className="feature-card">
            <span className="feature-icon">🏨</span>
            <h3>Hotel Booking</h3>
            <p>Organize your accommodations with check-in dates and confirmation numbers</p>
          </div>
          <div className="feature-card">
            <span className="feature-icon">🎯</span>
            <h3>Activity Planning</h3>
            <p>Schedule activities, tours, and experiences for your trip</p>
          </div>
          <div className="feature-card">
            <span className="feature-icon">💰</span>
            <h3>Budget Tracking</h3>
            <p>Keep track of your expenses and stay within budget</p>
          </div>
          <div className="feature-card">
            <span className="feature-icon">🎒</span>
            <h3>Packing Lists</h3>
            <p>Create and manage packing lists with smart suggestions</p>
          </div>
          <div className="feature-card">
            <span className="feature-icon">🤖</span>
            <h3>AI Suggestions</h3>
            <p>Get intelligent trip ideas, packing tips, and itinerary optimization</p>
          </div>
        </div>
      </section>
    </div>
  );
};

export default Home;
