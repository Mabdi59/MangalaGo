import { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { tripAPI, flightAPI, hotelAPI, activityAPI, budgetAPI } from '../services/api';
import type { Trip, Flight, Hotel, Activity, Budget } from '../types';
import './TripDetails.css';

const TripDetails = () => {
  const { id } = useParams<{ id: string }>();
  const navigate = useNavigate();
  const [trip, setTrip] = useState<Trip | null>(null);
  const [flights, setFlights] = useState<Flight[]>([]);
  const [hotels, setHotels] = useState<Hotel[]>([]);
  const [activities, setActivities] = useState<Activity[]>([]);
  const [budget, setBudget] = useState<Budget | null>(null);
  const [loading, setLoading] = useState(true);
  const [activeTab, setActiveTab] = useState<'overview' | 'flights' | 'hotels' | 'activities' | 'budget'>('overview');

  useEffect(() => {
    if (id) {
      fetchTripDetails();
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [id]);

  const fetchTripDetails = async () => {
    try {
      setLoading(true);
      const tripId = Number(id);
      
      const [tripRes, flightsRes, hotelsRes, activitiesRes] = await Promise.all([
        tripAPI.getById(tripId),
        flightAPI.getByTripId(tripId).catch(() => ({ data: [] })),
        hotelAPI.getByTripId(tripId).catch(() => ({ data: [] })),
        activityAPI.getByTripId(tripId).catch(() => ({ data: [] })),
      ]);
      
      setTrip(tripRes.data);
      setFlights(flightsRes.data);
      setHotels(hotelsRes.data);
      setActivities(activitiesRes.data);
      
      try {
        const budgetRes = await budgetAPI.getByTripId(tripId);
        setBudget(budgetRes.data);
      } catch {
        setBudget(null);
      }
    } catch (error) {
      console.error('Error fetching trip details:', error);
      alert('Failed to load trip details');
      navigate('/trips');
    } finally {
      setLoading(false);
    }
  };

  if (loading) {
    return <div className="loading">Loading trip details...</div>;
  }

  if (!trip) {
    return <div className="error">Trip not found</div>;
  }

  return (
    <div className="trip-details-page">
      <div className="trip-header">
        <button onClick={() => navigate('/trips')} className="back-btn">← Back to Trips</button>
        <h1>{trip.name}</h1>
        <p className="destination">📍 {trip.destination}</p>
        <p className="dates">
          📅 {new Date(trip.startDate).toLocaleDateString()} - {new Date(trip.endDate).toLocaleDateString()}
        </p>
        <span className={`status-badge status-${trip.status.toLowerCase()}`}>{trip.status}</span>
      </div>

      <div className="tabs">
        <button 
          className={activeTab === 'overview' ? 'tab active' : 'tab'}
          onClick={() => setActiveTab('overview')}
        >
          Overview
        </button>
        <button 
          className={activeTab === 'flights' ? 'tab active' : 'tab'}
          onClick={() => setActiveTab('flights')}
        >
          Flights ({flights.length})
        </button>
        <button 
          className={activeTab === 'hotels' ? 'tab active' : 'tab'}
          onClick={() => setActiveTab('hotels')}
        >
          Hotels ({hotels.length})
        </button>
        <button 
          className={activeTab === 'activities' ? 'tab active' : 'tab'}
          onClick={() => setActiveTab('activities')}
        >
          Activities ({activities.length})
        </button>
        <button 
          className={activeTab === 'budget' ? 'tab active' : 'tab'}
          onClick={() => setActiveTab('budget')}
        >
          Budget
        </button>
      </div>

      <div className="tab-content">
        {activeTab === 'overview' && (
          <div className="overview">
            <h2>Trip Overview</h2>
            {trip.description && (
              <div className="section">
                <h3>Description</h3>
                <p>{trip.description}</p>
              </div>
            )}
            <div className="stats-grid">
              <div className="stat-card">
                <span className="stat-icon">✈️</span>
                <div className="stat-info">
                  <span className="stat-value">{flights.length}</span>
                  <span className="stat-label">Flights</span>
                </div>
              </div>
              <div className="stat-card">
                <span className="stat-icon">🏨</span>
                <div className="stat-info">
                  <span className="stat-value">{hotels.length}</span>
                  <span className="stat-label">Hotels</span>
                </div>
              </div>
              <div className="stat-card">
                <span className="stat-icon">🎯</span>
                <div className="stat-info">
                  <span className="stat-value">{activities.length}</span>
                  <span className="stat-label">Activities</span>
                </div>
              </div>
              <div className="stat-card">
                <span className="stat-icon">💰</span>
                <div className="stat-info">
                  <span className="stat-value">{budget ? `$${budget.totalBudget}` : 'N/A'}</span>
                  <span className="stat-label">Budget</span>
                </div>
              </div>
            </div>
          </div>
        )}

        {activeTab === 'flights' && (
          <div className="flights">
            <h2>Flights</h2>
            {flights.length === 0 ? (
              <p className="empty-message">No flights added yet.</p>
            ) : (
              <div className="items-list">
                {flights.map((flight) => (
                  <div key={flight.id} className="item-card">
                    <h3>{flight.airline} {flight.flightNumber}</h3>
                    <p><strong>From:</strong> {flight.departureAirport} → <strong>To:</strong> {flight.arrivalAirport}</p>
                    <p><strong>Departure:</strong> {new Date(flight.departureTime).toLocaleString()}</p>
                    <p><strong>Arrival:</strong> {new Date(flight.arrivalTime).toLocaleString()}</p>
                    {flight.confirmationNumber && <p><strong>Confirmation:</strong> {flight.confirmationNumber}</p>}
                    {flight.price && <p><strong>Price:</strong> ${flight.price}</p>}
                  </div>
                ))}
              </div>
            )}
          </div>
        )}

        {activeTab === 'hotels' && (
          <div className="hotels">
            <h2>Hotels</h2>
            {hotels.length === 0 ? (
              <p className="empty-message">No hotels added yet.</p>
            ) : (
              <div className="items-list">
                {hotels.map((hotel) => (
                  <div key={hotel.id} className="item-card">
                    <h3>{hotel.name}</h3>
                    <p><strong>Address:</strong> {hotel.address}</p>
                    {hotel.city && <p><strong>City:</strong> {hotel.city}</p>}
                    <p><strong>Check-in:</strong> {new Date(hotel.checkInDate).toLocaleDateString()}</p>
                    <p><strong>Check-out:</strong> {new Date(hotel.checkOutDate).toLocaleDateString()}</p>
                    {hotel.confirmationNumber && <p><strong>Confirmation:</strong> {hotel.confirmationNumber}</p>}
                    {hotel.pricePerNight && <p><strong>Price per night:</strong> ${hotel.pricePerNight}</p>}
                  </div>
                ))}
              </div>
            )}
          </div>
        )}

        {activeTab === 'activities' && (
          <div className="activities">
            <h2>Activities</h2>
            {activities.length === 0 ? (
              <p className="empty-message">No activities added yet.</p>
            ) : (
              <div className="items-list">
                {activities.map((activity) => (
                  <div key={activity.id} className="item-card">
                    <h3>{activity.name}</h3>
                    {activity.description && <p>{activity.description}</p>}
                    <p><strong>Time:</strong> {new Date(activity.scheduledTime).toLocaleString()}</p>
                    {activity.location && <p><strong>Location:</strong> {activity.location}</p>}
                    {activity.price && <p><strong>Price:</strong> ${activity.price}</p>}
                    {activity.category && <p><strong>Category:</strong> {activity.category}</p>}
                  </div>
                ))}
              </div>
            )}
          </div>
        )}

        {activeTab === 'budget' && (
          <div className="budget">
            <h2>Budget</h2>
            {!budget ? (
              <p className="empty-message">No budget set for this trip.</p>
            ) : (
              <div className="budget-details">
                <div className="budget-summary">
                  <div className="budget-item">
                    <span>Total Budget:</span>
                    <span className="amount">${budget.totalBudget}</span>
                  </div>
                  <div className="budget-item">
                    <span>Spent:</span>
                    <span className="amount spent">${budget.spentAmount}</span>
                  </div>
                  <div className="budget-item">
                    <span>Remaining:</span>
                    <span className="amount remaining">${budget.totalBudget - budget.spentAmount}</span>
                  </div>
                </div>
                <div className="budget-breakdown">
                  <h3>Budget Breakdown</h3>
                  {budget.flightsBudget && <p>Flights: ${budget.flightsBudget}</p>}
                  {budget.hotelsBudget && <p>Hotels: ${budget.hotelsBudget}</p>}
                  {budget.activitiesBudget && <p>Activities: ${budget.activitiesBudget}</p>}
                  {budget.foodBudget && <p>Food: ${budget.foodBudget}</p>}
                  {budget.transportationBudget && <p>Transportation: ${budget.transportationBudget}</p>}
                  {budget.shoppingBudget && <p>Shopping: ${budget.shoppingBudget}</p>}
                  {budget.miscellaneousBudget && <p>Miscellaneous: ${budget.miscellaneousBudget}</p>}
                </div>
              </div>
            )}
          </div>
        )}
      </div>
    </div>
  );
};

export default TripDetails;
